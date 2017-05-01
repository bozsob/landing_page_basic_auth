package mypkg;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by trixi on 2017.04.08..
 */
@WebServlet("/ServletEmailHandling")
public class ServletEmailHandling extends HttpServlet {

    private Map<String, Boolean> validUsers;
    private String usersFilePath;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        validUsers = new HashMap<>();
        validUsers.put("trixi:admin", true);
        usersFilePath = getServletContext().getRealPath("/") + "resources/subscribedUsers.xml";
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            File xmlFile = new File(usersFilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(xmlFile);
            Element subscribedUsers = document.getDocumentElement();

            String userName = request.getParameter("name");
            String userEmail = request.getParameter("email");

            Element subscribed = document.createElement("subscribed");
            Element name = document.createElement("name");
            Element email = document.createElement("email");

            name.setTextContent(userName);
            email.setTextContent(userEmail);

            subscribed.appendChild(name);
            subscribed.appendChild(email);
            subscribedUsers.appendChild(subscribed);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(usersFilePath);

            transformer.transform(source, result);

            request.setAttribute("thankYouForYourSubscription", "Thank you for your subscription! We will inform you by email.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/mainPage.jsp");
            dispatcher.forward(request, response);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String basicAuth = request.getHeader("Authorization");

        if (checkUserIfValid(basicAuth)) {
            try {
                File xmlFile = new File(usersFilePath);
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document document = dBuilder.parse(xmlFile);
                document.getDocumentElement().normalize();

                Element root = document.getDocumentElement();
                NodeList subscribedUsers = root.getElementsByTagName("subscribed");
                request.setAttribute("subscribedUsers", subscribedUsers);
            } catch (Exception e) {
                e.printStackTrace();
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request, response);
        } else {
            response.setHeader("WWW-Authenticate", "BASIC realm=\"Admin Login\"");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }


    private boolean checkUserIfValid(String basicAuth) {
        if(basicAuth == null) {
            return false;
        }
        if(!basicAuth.toUpperCase().startsWith("BASIC ")) {
            return true;
        }
        String decodedUser = new String(Base64.getDecoder().decode(basicAuth.substring(6)));
        return validUsers.get(decodedUser);
    }
}
