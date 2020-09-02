package controller;

import connector.StudentConnector;
import model.StudentModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "SearchUserServlet")
public class SearchUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StudentConnector studentCon = new StudentConnector();
        String studentName = request.getParameter("studentName");
        HttpSession session = request.getSession(false);
        int id = (int) session.getAttribute("sId");
        List<StudentModel> listUser = null;

        if (studentName == "") {

            response.sendRedirect(request.getContextPath() + "/DashbordServlet?pageId=1&sort=student");
        } else {
            try {

                listUser = studentCon.ShowTable(studentName, id);
                request.setAttribute("listUser", listUser);
                RequestDispatcher dispatcher = request.getRequestDispatcher("dashBord.jsp");
                dispatcher.forward(request, response);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
