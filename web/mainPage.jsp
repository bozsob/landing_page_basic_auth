<%--
  Created by IntelliJ IDEA.
  User: trixi
  Date: 2017.05.01.
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Best Gift ever</title>
    <link rel="stylesheet" href="style.css">
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css?family=Lobster|Playfair+Display|Raleway" rel="stylesheet">
</head>
<body>

<h1>Best Gift ever</h1>

<div id="container">

    <div id="good_list">
        <ul>
            <li>It is good for her</li>
            <li>It is good for him</li>
            <li>It is good for you</li>
        </ul>
    </div>

    <div id="content">

        <p class="present"><img src="images/gold_box.png" alt="Gold gift box" width="30" height="30">
            Did you ever hear from someone, that <b>"I want <em>nothing</em> for my birthday"</b>?</p>
        <p class="present"><img src="images/green_box.png" alt="Green gift box" width="30" height="30">
            Did you ever hear from someone, that <b>"I want <em>nothing</em> for Christmas"</b>?</p>
        <p class="present"><img src="images/red_box.png" alt="Red gift box" width="30" height="30"/>
            Do you know the predicament of what to buy a person who has everything?</p>
        <p class="yes">Yes</p>
        <p class="present"><img src="images/gold_box.png" alt="Gold gift box" width="30" height="30"/>
            Well now you are in the right place at the right time.</p> <br />
        <p id="know_more">If you wanna know more, give your email address below and we'll notify you when we open the store.</p>

    </div>

    <div id="email">
        <form action="ServletEmailHandling" method="post">
            Name: <input type="text" name="name" size="30">
            Email: <input type="text" name="email" size="30">
            <input type="submit" value="Submit">
        </form>
    </div>
    <div style="text-align: right;">
        <form action="ServletEmailHandling" method="get">
            <input type="submit" value="Admin">
        </form>
    </div>
    ${thankYouForSubscription}
</div>
</body>
</html>
