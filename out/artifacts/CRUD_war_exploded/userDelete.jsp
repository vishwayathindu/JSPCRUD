<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vishwa_p
  Date: 8/27/2020
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>user Delete</title>
    <link rel="stylesheet" type="text/css" href="loginPageStyle.css">
</head>
<body>
<div class="login">
    <form action="UserDeleteServlet" method="post">
        <label>Student ID:</label>
        <input type="text" name="studentID" value="<c:out value="${st.studentId}"/>" readonly/> <br/>
        <label>Student Name:</label>
        <input type="text" name="studentName" value="<c:out value="${st.studentName}"/>" readonly><br/>
        <label>NIC:</label>
        <input type="text" name="nic" minlength="10" maxlength="12" pattern="^([0-9]{9}[x|X|v|V]|[0-9]{12})$"
               value="<c:out value="${st.nic}"/>" readonly><br>
        <label>Gender:</label>
        <select name="gender" value="<c:out value="${st.gender}"/>" readonly>
            <option value="male">Male</option>
            <option value="female">Female</option>
        </select>
        <br/>
        <button type="submit" name="DeleteStudent" class="btn btn-primary btn-block btn-large">Delete</button>
    </form>
</div>

</body>
</html>
