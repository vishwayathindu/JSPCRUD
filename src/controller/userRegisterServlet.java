package controller;

import connector.studentConnector;
import model.studentModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

//@WebServlet(name = "userRegisterServlet")
public class userRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("userRegisterServlet working");
        // taking input data from form
        String studentName= request.getParameter("studentName");
        String nic= request.getParameter("nic");
        String gender= request.getParameter("gender");
        String password= request.getParameter("password");
        String confirmPassword= request.getParameter("confirmPassword");

        if(!password.equals(confirmPassword)){

            RequestDispatcher dispatcher = request.getRequestDispatcher("invalidLogin.jsp");
            dispatcher.forward(request, response);
        }else {
            //create student object


            //execute sql quary
            try {
                studentModel s1 = new studentModel(0, studentName, nic, gender, password);
                int result = studentConnector.registerUser(s1);
                System.out.printf("new user added" + result);
                RequestDispatcher dispatcher = request.getRequestDispatcher("logIn.jsp");
                dispatcher.forward(request, response);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }





    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
