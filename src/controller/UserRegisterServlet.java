package controller;

import connector.StudentConnector;
import model.StudentModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@WebServlet(name = "UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("UserRegisterServlet working");
        // taking input data from form
        String studentName = request.getParameter("studentName");
        String nic = request.getParameter("nic");
        String gender = request.getParameter("gender");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // matching nic
        Pattern patt = Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$");
        Matcher nicM = patt.matcher(nic);
        boolean matches= nicM.matches();

        //matching password
        Pattern pass = Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[!@#$%^&*])(?=.*[A-Z]).{8,}");
        Matcher passM = pass .matcher(password);
        boolean passMatches= passM.matches();

//        System.out.println("nic matches"+matches);
//        System.out.println("password matches"+passMatches);

        if(studentName =="" || nic== ""){
//            System.out.printf("student name"+ studentName);
            RequestDispatcher dispatcher = request.getRequestDispatcher("invalidLogin.jsp");
            dispatcher.forward(request, response);
        }else if(!matches == true){
//            System.out.println("nic did n't matches"+matches);
            RequestDispatcher dispatcher = request.getRequestDispatcher("invalidLogin.jsp");
            dispatcher.forward(request, response);

        } else if (!passMatches == true){
//            System.out.println("password did n't matches "+passMatches);
            RequestDispatcher dispatcher = request.getRequestDispatcher("invalidLogin.jsp");
            dispatcher.forward(request, response);

        } else if (!password.equals(confirmPassword)) {

            RequestDispatcher dispatcher = request.getRequestDispatcher("invalidLogin.jsp");
            dispatcher.forward(request, response);
        } else {
            try {
                StudentModel s1 = new StudentModel(0, studentName, nic, gender, password);
                int result = StudentConnector.registerUser(s1);
                //System.out.printf("new user added" + result);
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
