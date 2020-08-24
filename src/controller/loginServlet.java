package controller;

import connector.studentConnector;
import model.studentModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

//@WebServlet(name = "loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("login servlet stared working");

        String loginStName= request.getParameter("loginStName");
        String loginStPassword= request.getParameter("loginStPassword");

        System.out.println("capture data"+loginStName+" "+loginStPassword);

        //creating the object
        studentModel s1=new studentModel();
        s1.setStudentName(loginStName);
        s1.setPassword(loginStPassword);

        try {
            int result= studentConnector.studentLogin(s1);
            if (result==0){
                RequestDispatcher dispatcher = request.getRequestDispatcher("invalidLogin.jsp");
                dispatcher.forward(request, response);
            }else{
                System.out.printf("student log in success"+ result);
                HttpSession session=request.getSession();
                session.setAttribute("StudentName",loginStName);
                response.sendRedirect(request.getContextPath()+"/dashbordServlet");
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
