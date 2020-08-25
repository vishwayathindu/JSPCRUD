<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vishwa_p
  Date: 8/21/2020
  Time: 2:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashbord</title>
    <link rel="stylesheet" type="text/css" href="loginPageStyle.css">
</head>
<body>
    <%
        String StudentName=(String)session.getAttribute("StudentName");

        //redirect user to login page if not logged in
        if(StudentName==null){
            response.sendRedirect("logIn.jsp");
        }
    %>


    <table >
        <caption><h1>Dash Board</h1></caption>
        <thead>
        <tr>
            <th>studentId</th>
            <th>StudentName</th>
            <th>nIC</th>
            <th>gender</th>
            <th> password</th>
            <th> <form action="logOutServlet">
                <button type="submit" name="addUser" class="btn btn-primary btn-block btn-large">log out</button>
            </form></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="st" items="${listUser}">
            <tr>
                <td style="width:100px"><c:out value="${st.studentId}"/></td>
                <td><c:out value="${st.studentName}"/></td>
                <td><c:out value="${st.nic}"/></td>
                <td><c:out value="${st.password}"/></td>
                <td><c:out value="${st.gender}"/></td>
                <td><a href="userUpdateServlet?studentId=<c:out value='${st.studentId}'/>" class="btn btn-primary btn-block btn-large">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="userDeleteServlet?studentName=<c:out value='${st.studentName}'/>" class="btn btn-primary btn-block btn-large">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>

    </table>



</body>
</html>
