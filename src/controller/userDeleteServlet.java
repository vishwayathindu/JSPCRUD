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
import java.sql.SQLException;

@WebServlet(name = "userDeleteServlet")
public class userDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String stName= request.getParameter("studentName");
        studentModel st = new studentModel();
        st.setStudentName(stName);
        System.out.println("user name need to delete the data"+ stName);

        try {
            int result = studentConnector.deleteStudent(st);
            System.out.printf("user delete servelet executed"+ result);
            response.sendRedirect(request.getContextPath()+"/dashbordServlet");

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
