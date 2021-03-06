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
        //redirect user to login page if not logged in
        try {
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            int StudenId = (int) session.getAttribute("sId");

            if (StudenId == 0) {
                response.sendRedirect("logIn.jsp");
            }
        } catch (Exception ex) {
            out.println(ex);
        }


    %>


    <table >

        <caption><h1>Dash Board</h1></caption>
        <thead>
        <tr>
            <td colspan="5">
                <form action="SearchUserServlet" method="post">
                    <%--@declare id="studentname"--%><label for="StudentName">Search by Student Name:</label>
                    <input type="text" name="studentName" placeholder="Student Name">
                    <button type="submit" name="searchUser" class="btn btn-primary btn-block btn-large">Search</button>
                </form>
            </td>
        </tr>
        <tr>
            <th>studentId</th>
            <th>

                StudentName
                <br>
                <a href="DashbordServlet?pageId=1&sort=StudentName">sort</a>

            </th>
            <th>nIC</th>
            <th>gender</th>

            <th>
                <form action="logOutServlet" method="get">
                    <button type="submit" name="addUser" class="btn btn-primary btn-block btn-large">log out</button>
                </form>
            </th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="st" items="${listUser}">
            <tr>
                <td style="width:100px"><c:out value="${st.studentId}"/></td>
                <td><c:out value="${st.studentName}"/></td>
                <td><c:out value="${st.nic}"/></td>
                <td><c:out value="${st.password}"/></td>
                <td><a href="userUpdateServlet?studentId=<c:out value='${st.studentId}'/>"
                       class="btn btn-primary btn-block btn-large">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="StudentDeleteServlet?studentId=<c:out value='${st.studentId}'/>"
                       class="btn btn-primary btn-block btn-large">Delete</a></td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="5">
                <table cellpadding="5" cellspacing="5">
                    <tr>
                        <c:forEach begin="1" end="${noOfPages}" var="i">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    <td>${i}</td>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="DashbordServlet?pageId=${i}&sort=${columnName}">${i}</a></td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </tr>
                </table>


            </td>


        </tr>
        <tr>
            <td colspan="5">
                <table cellpadding="5" cellspacing="5">
                    <tr>

                        <td><a href="ReportServlet">Download data PDf</a></td>
                        <td><a href="ExcelDataServlet">Download data Excel</a></td>

                    </tr>
                </table>


            </td>


        </tr>
        </tbody>

    </table>


</body>
</html>
