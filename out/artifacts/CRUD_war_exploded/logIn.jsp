<%--
  Created by IntelliJ IDEA.
  User: vishwa_p
  Date: 8/21/2020
  Time: 1:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log IN</title>
    <link rel="stylesheet" type="text/css" href="loginPageStyle.css">

</head>
<body>
<div class="login">
    <h1>Login</h1>
    <form action="loginServlet" method="post">

        <input type="text" name="loginStName" placeholder="Username"><br>

        <input type="password" name="loginStPassword" placeholder="Password">
        <button type="submit" name="login" class="btn btn-primary btn-block btn-large">login</button>
    </form>
    <a href="userRegister.jsp">Sign Up</a>
</div>


</body>
</html>
