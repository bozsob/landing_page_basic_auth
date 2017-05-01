<%@ page import="org.w3c.dom.NodeList" %>
<%@ page import="org.w3c.dom.Node" %>
<%@ page import="org.w3c.dom.Element" %><%--
  Created by IntelliJ IDEA.
  User: trixi
  Date: 2017.05.01.
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Page</title>
    <link rel="stylesheet" href="style.css">

    <div id="container">

        <div id="content">
            <h2>Subscribed:</h2>

            <%
                NodeList subscribedUsers = (NodeList)request.getAttribute("subscribedUsers");
                out.println("<br>");
                out.println("<hr>");

                for(int i = 0; i < subscribedUsers.getLength(); i++) {
                    Node node = subscribedUsers.item(i);
                    if(node.getNodeType() == Node.ELEMENT_NODE) {
                        Element subscribed = (Element) node;
                        Element name = (Element)subscribed.getElementsByTagName("name").item(0);
                        out.println("<h3>" + name.getTextContent() + "</h3>");

                        Element email = (Element)subscribed.getElementsByTagName("email").item(0);
                        out.println("<h3>" + email.getTextContent() + "</h3>");
                    }
                    out.println("<hr>");
                }
            %>

            <a style="float:right;" href="mainPage.jsp">Back to main page.</a>
        </div>
    </div>

</head>
<body>

</body>
</html>
