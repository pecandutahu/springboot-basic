package com.seventonine.order.services;

import com.seventonine.order.models.Orders;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PdfReportService {

    public byte[] generateReport(List<Orders> orders) throws JRException {
        // Compile the Jasper report from .jrxml to .jasper
        JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/orderReport.jrxml"));

        // Get your data source
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(orders);

        // Add parameters
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ReportTitle", "Order Report");
        parameters.put("DataCollection", dataSource);

        // Fill the report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export the report to a PDF file
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
        exporter.exportReport();

        return byteArrayOutputStream.toByteArray();
    }
}
