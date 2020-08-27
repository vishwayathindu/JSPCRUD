package controller;

import connector.StudentConnector;
import model.StudentModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

//@WebServlet(name = "dashbordServlet")
public class DashbordServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StudentConnector studentCon = new StudentConnector();


        int pageid = Integer.parseInt(request.getParameter("pageId"));
        //System.out.println("pageid" + pageid);

        int total = 5;
        if (pageid == 1) {
        } else {
            pageid = pageid - 1;
            pageid = pageid * total + 1;
        }
        //System.out.printf("pageid" + pageid);
        int noOfRecords = StudentConnector.NoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / total);


        List<StudentModel> listUser = null;
        try {
            listUser = studentCon.getRecords(pageid, total);
            request.setAttribute("listUser", listUser);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", pageid);


            RequestDispatcher dispatcher = request.getRequestDispatcher("dashBord.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
