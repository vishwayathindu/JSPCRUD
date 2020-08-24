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

//@WebServlet(name = "dashbordServlet")
public class dashbordServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        studentConnector studentCon = new studentConnector();

        List<studentModel> listUser = null;
        try {
            listUser = studentCon.ShowTable();
            request.setAttribute("listUser", listUser);

            System.out.println("list running");

            RequestDispatcher dispatcher = request.getRequestDispatcher("dashBord.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }










    }
}
