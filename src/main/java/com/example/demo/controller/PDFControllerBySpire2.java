package com.example.demo.controller;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PDFControllerBySpire2 {

    public static void main(String[] args) {
        String targetPDF="E:\\新视云\\开发需求\\20200616 禅道任务\\立案登记表\\0049_立案管理登记、审判流程管理信息表1.pdf";

        //创建PdfDocument实例
        PdfDocument doc = new PdfDocument();
        //加载PDF文件
        doc.loadFromFile(targetPDF);

        //获取第一页
        PdfPageBase page = doc.getPages().get(0);
//        page.
//        new Rectangle
        doc.close();
    }
}