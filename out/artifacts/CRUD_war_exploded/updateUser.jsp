<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vishwa_p
  Date: 8/24/2020
  Time: 9:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>update User</title>
    <link rel="stylesheet" type="text/css" href="loginPageStyle.css">
</head>
<body>
<div class="login">
    <form action="studentUpdateServlet" method="post">
        <label >Student ID:</label>
        <input type="text"  name="studentID" value="<c:out value="${st.studentId}"/>" readonly/> <br/>
        <label  >Student Name:</label>
        <input type="text"  name="studentName"  value="<c:out value="${st.studentName}"/>" ><br/>
        <label >NIC:</label>
        <input type="text"  name="nic" minlength="10" maxlength="12" pattern="(?=.*\d{9})(?=.*[V]).{10,}" value="<c:out value="${st.nic}"/>"><br>
        <label >Gender:</label>
        <select name="gender" value="<c:out value="${st.gender}"/>" >
            <option value="male">Male</option>
            <option value="female">Female</option>
        </select>
        <br/>
        <button type="submit" name="updateStudent" class="btn btn-primary btn-block btn-large">submit</button>
    </form>
</div>

</body>
</html>
