package controller;

import connector.StudentConnector;
import model.StudentModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int studentID = Integer.parseInt(request.getParameter("studentID"));
        StudentModel st = new StudentModel();
        st.setStudentId(studentID);
        //System.out.println("user name need to delete the data" + stName);

        try {

            int result = StudentConnector.deleteStudent(st);
            //System.out.printf("user delete servelet executed" + result);
            response.sendRedirect(request.getContextPath() + "/DashbordServlet?pageId=1");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
