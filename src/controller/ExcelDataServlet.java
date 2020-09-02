package controller;

import connector.StudentConnector;
import model.StudentModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ExcelDataServlet")
public class ExcelDataServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String DOWNLOAD_FILE_NAME = "postReport.xlsx"; //file name of the downloadable file
        String FILE_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

        StudentConnector ps = new StudentConnector();
        List<StudentModel> studentData = new ArrayList<>();
        String reportPath;
        OutputStream outputStream;
        JasperReport jasperReport;
        JasperDesign jasperDesign;
        JRDataSource reportSource;
        Map reportParameters;
        JasperPrint jasperPrint = null;

        try {
            studentData = ps.PrintRecords(); //get the data
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        reportSource = new JRBeanCollectionDataSource(studentData); //set the database values to the reportSource
        reportPath = request.getServletContext().getRealPath("/ireport") + "\\report.jrxml";

        try {
            reportParameters = new HashMap();
            reportParameters.put("studentId", "studentId");

            jasperDesign = JRXmlLoader.load(new FileInputStream(reportPath));
            jasperReport = JasperCompileManager.compileReport(jasperDesign);

            jasperPrint = JasperFillManager.fillReport(jasperReport, reportParameters, reportSource);//fill the report

            //response
            response.setHeader("Content-Disposition", "attachement; filename=" + DOWNLOAD_FILE_NAME);
            response.setContentType(FILE_TYPE);
            response.setContentLength(4096);

            //outputstream
            outputStream = response.getOutputStream();

            //export the excel file
            JRXlsxExporter exporterXLS = new JRXlsxExporter();
            exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, outputStream);
            exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, DOWNLOAD_FILE_NAME);

            exporterXLS.exportReport();
        } catch (JRException e) {
            e.printStackTrace();
        }


    }
}
