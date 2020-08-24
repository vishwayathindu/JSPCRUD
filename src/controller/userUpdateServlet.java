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
import java.util.List;

@WebServlet(name = "userUpdateServlet")
public class userUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("update servelet started working" );
        int studentId = Integer.parseInt(request.getParameter("studentId"));


        studentModel s1 = new studentModel();
        s1.setStudentId(studentId);
        studentConnector st = new studentConnector();
        try {
            studentModel st2 = st.updateStudentList(s1);

            System.out.println("user update servelt pass data and retrive st2");
            RequestDispatcher dispatcher = request.getRequestDispatcher("updateUser.jsp");
            request.setAttribute("st", st2);
            dispatcher.forward(request, response);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
