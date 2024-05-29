package com.seventonine.order.services;

import com.seventonine.order.models.Orders;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PdfReportService {

    public byte[] generateReport(List<Orders> orders) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Order Report").setFontSize(18).setBold().setFontColor(ColorConstants.BLACK));
        document.add(new Paragraph("\n"));

        Table table = new Table(new float[]{1, 2, 2, 2});
        // table.setWidthPercent(100);

        table.addHeaderCell(new Cell().add(new Paragraph("Order Code").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Customer Name").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Order Date").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Total Price").setBold()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (Orders order : orders) {
            table.addCell(new Cell().add(new Paragraph(order.getOrderCode())));
            table.addCell(new Cell().add(new Paragraph(order.getCustomer().getCustomerName())));
            table.addCell(new Cell().add(new Paragraph(order.getOrderDate().format(formatter))));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(order.getTotalPrice()))));
        }

        document.add(table);
        document.close();

        return byteArrayOutputStream.toByteArray();
    }
}
