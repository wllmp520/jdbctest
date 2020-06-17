package com.example.demo.controller;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class PDFControllerBySpire {

    public static void main(String[] args) {
        String targetPDF="E:\\新视云\\开发需求\\20200616 禅道任务\\立案登记表\\0049_立案管理登记、审判流程管理信息表1.pdf";

        //创建PdfDocument实例
        PdfDocument doc = new PdfDocument();
        //加载PDF文件
        doc.loadFromFile(targetPDF);

        List<String> list = new ArrayList<>();
        PdfPageBase page;
        //遍历PDF页面，获取每个页面的文本并添加到StringBuilder对象
        for(int i= 0;i<doc.getPages().getCount();i++){
            page = doc.getPages().get(i);
            list.add(page.extractText(true));
        }
        List<String> includeList = new ArrayList<>();
        Stream<List<String>> stream = Stream.of(list);
//        stream.

//        Iterator<String> iterator = list.iterator();
//        while(iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
        doc.close();
    }
}