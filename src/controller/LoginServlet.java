package controller;

import connector.StudentConnector;
import model.StudentModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

//@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //System.out.println("login servlet stared working");

        String loginStName = request.getParameter("loginStName");
        String loginStPassword = request.getParameter("loginStPassword");

        //System.out.println("capture data" + loginStName + " " + loginStPassword);

        //creating the object
        StudentModel s1 = new StudentModel();
        s1.setStudentName(loginStName);
        s1.setPassword(loginStPassword);

        try {
            StudentModel s2 = StudentConnector.studentLogin(s1);
            int id = s2.getStudentId();
            if (id == 0) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("invalidLogin.jsp");
                dispatcher.forward(request, response);
            } else {
                //System.out.printf("student log in success" + result);

                HttpSession session = request.getSession(true);
                session.setAttribute("StudentName", loginStName);
                session.setAttribute("sId", id);
                response.sendRedirect(request.getContextPath() + "/DashbordServlet?pageId=1&sort=student");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
