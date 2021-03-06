package controller;

import connector.StudentConnector;
import model.StudentModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "StudentDeleteServlet")
public class StudentDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int studentId = Integer.parseInt(request.getParameter("studentId"));

        StudentModel s1 = new StudentModel();
        s1.setStudentId(studentId);
        StudentConnector st = new StudentConnector();

        try {
            StudentModel st2 = st.updateStudentList(s1);

            //System.out.println("user update servelt pass data and retrive st2");
            RequestDispatcher dispatcher = request.getRequestDispatcher("userDelete.jsp");
            request.setAttribute("st", st2);
            request.setAttribute("studentId", studentId);
            dispatcher.forward(request, response);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
