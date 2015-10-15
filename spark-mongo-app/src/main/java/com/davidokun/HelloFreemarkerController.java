package com.davidokun;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

/**
 * Created by David Cuellar.
 */
public class HelloFreemarkerController {

    public static void main(String[] args) {

        StringWriter writer = new StringWriter();
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloSparkController.class, "/");

        try {
            Template helloTemplate = configuration.getTemplate("hello.ftl");

            Map<String, Object> helloMap = new HashMap<>();

            helloMap.put("name", "Freemarker");
            helloTemplate.process(helloMap, writer);

            System.out.println(writer);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

        get("/helloFreemarker", (req, res) -> writer.toString());
    }
}
