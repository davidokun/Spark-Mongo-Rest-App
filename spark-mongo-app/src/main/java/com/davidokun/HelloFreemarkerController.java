package com.davidokun;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.halt;

/**
 * Created by David Cuellar.
 */
public class HelloFreemarkerController {

    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloSparkController.class, "/");

        get("/helloFreemarker", (req, res) -> {

            StringWriter writer = new StringWriter();
            try {

                Template helloTemplate = configuration.getTemplate("hello.ftl");

                Map<String, Object> helloMap = new HashMap<>();

                helloMap.put("name", "Freemarker");
                helloTemplate.process(helloMap, writer);

            } catch (IOException | TemplateException e) {
                halt(500);
                e.printStackTrace();
            }

            return writer;
        });
    }
}
