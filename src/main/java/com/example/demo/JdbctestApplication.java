package com.example.demo;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JdbctestApplication {

   /* public static void main(String[] args) {
        SpringApplication.run(JdbctestApplication.class, args);
        //settingApiDocs();
    }
*/
    public static void main(String[] args){
        DocsConfig config = new DocsConfig();
        config.setProjectPath("E:\\test_wl\\jdbctest"); // 项目根目录
        config.setProjectName("jdbcTest"); // 项目名称
        config.setApiVersion("V2.0");       // 声明该API的版本
        config.setDocsPath("E:\\test_wl\\jdbctest\\src\\main\\resources"); // 生成API 文档所在目录
        config.setAutoGenerate(Boolean.FALSE);  // 配置自动生成
        Docs.buildHtmlDocs(config); // 执行生成文档
    }


}
