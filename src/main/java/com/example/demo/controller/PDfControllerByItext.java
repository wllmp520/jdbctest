package com.example.demo.controller;

/*import com.duallab.pdf2data.Pdf2DataExtractor;
import com.duallab.pdf2data.result.ParsingResult;
import com.duallab.pdf2data.result.ResultElement;
import com.duallab.pdf2data.result.ResultTable;
import com.duallab.pdf2data.template.Template;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.licensekey.LicenseKey;*/

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class PDfControllerByItext {
   /* public static void main(String[] args) {
        String templatePDF="E:\\新视云\\开发需求\\20200616 禅道任务\\立案登记表\\模板\\一审1.2.pdf";
        String targetPDF="E:\\新视云\\开发需求\\20200616 禅道任务\\立案登记表\\0049_立案管理登记、审判流程管理信息表1.pdf";
        try {
            LicenseKey.loadLicenseFile("E:\\新视云\\开发需求\\20200616 禅道任务\\立案登记表\\itextkey_wl.xml");

            Template template = Pdf2DataExtractor.parseTemplateFromPDF(templatePDF);
            Pdf2DataExtractor extractor = new Pdf2DataExtractor(template);
//// Make sure to load license file before invoking any code
//            LicenseKey.loadLicenseFile(pathToLicenseFile);
//
//// Parse template into an object that will be used later on
//            Template template = Pdf2DataExtractor.parseTemplateFromPDF(pathToPdfTemplate);
//
//// Create an instance of Pdf2DataExtractor for the parsed template
//            Pdf2DataExtractor extractor = new Pdf2DataExtractor(template);

// Feed file to be parsed against the template. Can be called multiple times for different files
            ParsingResult result = extractor.recognize(targetPDF);
            ResultTable firstResultAsTable = result.getFirstResultAsTable(" 当事人基本情况");
            final String s = firstResultAsTable.toString();
            final Map<String, List<List<ResultElement>>> allResults = result.getAllResults();
            System.out.println("maybe OK");
// Save result to XML or explore the ParsingResult object to fetch information programmatically
//            result.saveToXML("E:\\新视云\\开发需求\\20200616 禅道任务\\立案登记表\\模板\\一审1.pdf.xml");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
*/
}
