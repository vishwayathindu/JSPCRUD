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
import java.util.List;

//@WebServlet(name = "dashbordServlet")
public class DashbordServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StudentConnector studentCon = new StudentConnector();
        HttpSession session = request.getSession(false);
        int id = (int) session.getAttribute("sId");
        int pageid = Integer.parseInt(request.getParameter("pageId"));
        String sort = request.getParameter("sort");
        int total = 5;
        if (pageid == 1) {
        } else {
            pageid = pageid - 1;
            pageid = pageid * total + 1;
        }
        if (session != null && id != 0) {

            if (!sort.equals("StudentName")) {

                int noOfRecords = StudentConnector.NoOfRecords();
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / total);
                String columnName = "studentId";
                List<StudentModel> listUser = null;
                try {
                    listUser = studentCon.getRecords(pageid, total, columnName, id);
                    request.setAttribute("listUser", listUser);
                    request.setAttribute("noOfPages", noOfPages);
                    request.setAttribute("currentPage", pageid);
                    request.setAttribute("columnName", columnName);

                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } else {

                int noOfRecords = StudentConnector.NoOfRecords();
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / total);
                String columnName = "StudentName";
                List<StudentModel> listUser = null;
                try {
                    listUser = studentCon.getRecords(pageid, total, columnName, id);
                    request.setAttribute("listUser", listUser);
                    request.setAttribute("noOfPages", noOfPages);
                    request.setAttribute("currentPage", pageid);
                    request.setAttribute("columnName", columnName);


                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("dashBord.jsp");
            dispatcher.forward(request, response);

        } else {
            request.getRequestDispatcher("logIn.jsp").include(request, response);
        }


    }
}
