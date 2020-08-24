package controller;

import connector.studentConnector;
import model.studentModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "studentUpdateServlet")
public class studentUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentID= Integer.parseInt(request.getParameter("studentID"));
        String studentName= request.getParameter("studentName");
        String nic= request.getParameter("nic");
        String gender= request.getParameter("gender");
        String password= request.getParameter("password");

        studentModel s1= new studentModel(studentID,studentName,nic,gender,password);

        try {
            int results= studentConnector.studentUpdated(s1);
            System.out.printf("studenent update working results" + results);
            response.sendRedirect(request.getContextPath()+"/dashbordServlet");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
