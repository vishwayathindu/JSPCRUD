package controller;

import connector.StudentConnector;
import model.StudentModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "CsvDataServlet")
public class CsvDataServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        JasperPrint jasperPrint;
        ServletOutputStream outputStream = null;
        StudentConnector ps = new StudentConnector();
        List<StudentModel> studentData = new ArrayList<>();
        String reportPath = request.getServletContext().getRealPath("/ireport") + "\\report.jrxml";
        try {
            studentData = ps.PrintRecords();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {

            JRBeanCollectionDataSource result = new JRBeanCollectionDataSource(studentData);
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
            jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<String, Object>(), result);


            response.setHeader("Content-Disposition", "attachment;filename" + "filename" + ".csv");
            response.setContentType("application/octet-stream");
            response.setContentLength(4096);
            outputStream = response.getOutputStream();
            JRCsvExporter exporter = new JRCsvExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
            exporter.exportReport();
        } catch (JRException e) {
            e.printStackTrace();
        }


    }
}
