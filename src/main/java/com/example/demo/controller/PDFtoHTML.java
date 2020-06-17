package com.example.demo.controller;

import com.spire.pdf.*;
import java.io.*;

public class PDFtoHTML {
    public static void main(String[] args) throws FileNotFoundException {
        String targetPDF="E:\\新视云\\开发需求\\20200616 禅道任务\\立案登记表\\0049_立案管理登记、审判流程管理信息表1.pdf";

        String inputFile = targetPDF;
        File file = new File("toHTML_out.html");
        String outputFile = "toHTML_out.html";

        //加载示例文档
        PdfDocument pdf = new PdfDocument();

        pdf .loadFromFile(inputFile);

        //设置useEmbeddedSvg和 useEmbeddedImg布尔值为true 
        pdf .getConvertOptions().setPdfToHtmlOptions(true);

        //保存到流
        File outFile = new File(outputFile);
        OutputStream outputStream = new FileOutputStream(outFile);
        pdf.saveToStream(outputStream, FileFormat.HTML);
        pdf.close();
    }
}