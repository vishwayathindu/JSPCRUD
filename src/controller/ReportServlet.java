package controller;

import com.sun.javafx.collections.MappingChange;
import connector.StudentConnector;
import model.StudentModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

//@WebServlet(name = "ReportServlet")
public class ReportServlet extends HttpServlet {
    private String DOWNLOAD_FILE_NAME = "postReport.pdf"; //file name of the downloadable file
    private String FILE_TYPE = "application/pdf"; //file type of the file(pdf)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentConnector studentCon = new StudentConnector();
        List<StudentModel> listItems = null;
        try {
            listItems= studentCon.PrintRecords();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<StudentModel> postData = new ArrayList<>();
        String reportPath;
        OutputStream outputStream = null;
        JasperReport jasperReport;
        JasperDesign jasperDesign;
        JRDataSource reportSource;
        Map reportParameters;

        try {

            reportPath = request.getServletContext().getRealPath("/ireport") + "\\report.jrxml";

            reportParameters = new HashMap();
            reportParameters.put("title", "Post Feeds");

            jasperDesign = JRXmlLoader.load(reportPath);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);

            postData= studentCon.PrintRecords();
            reportSource = new JRBeanCollectionDataSource(postData,false); //set the database values to the reportSource

            //byteStream
            byte[] byteStream;
            byteStream = JasperRunManager.runReportToPdf(jasperReport,reportParameters, reportSource);

            //response
            response.setHeader("Content-Disposition", "attachement; filename=" + DOWNLOAD_FILE_NAME);
            response.setContentType(FILE_TYPE);
            response.setContentLength(byteStream.length);

            //outputstream
            outputStream = response.getOutputStream();

            //byteStream = data, 0 = starting offset, byteStream.length = length
            outputStream.write(byteStream, 0, byteStream.length);

        } catch (JRException ex) {
            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                outputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}
