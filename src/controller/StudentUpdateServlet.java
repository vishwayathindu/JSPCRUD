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
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "StudentUpdateServlet")
public class StudentUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentID = Integer.parseInt(request.getParameter("studentID"));
        String studentName = request.getParameter("studentName");
        String nic = request.getParameter("nic");
        String gender = request.getParameter("gender");

        // matching nic
        Pattern patt = Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$");
        Matcher nicM = patt.matcher(nic);
        boolean matches= nicM.matches();

        if(studentName =="" || nic== ""){
//            System.out.printf("student name"+ studentName);
            response.sendRedirect(request.getContextPath() + "/DashbordServlet?pageId=1");
        }else if(!matches == true){
//            System.out.println("nic did n't matches"+matches);
            response.sendRedirect(request.getContextPath() + "/DashbordServlet?pageId=1");

        }else{
            StudentModel s1 = new StudentModel();
            s1.setStudentName(studentName);
            s1.setGender(gender);
            s1.setStudentId(studentID);
            s1.setNic(nic);

            try {
                StudentConnector.studentUpdated(s1);
                response.sendRedirect(request.getContextPath() + "/DashbordServlet?pageId=1");

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
