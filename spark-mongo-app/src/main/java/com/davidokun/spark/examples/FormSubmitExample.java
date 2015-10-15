package com.davidokun.spark.examples;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

/**
 * Created by David Cuellar.
 */
public class FormSubmitExample {

    public static void main(String[] args) {

        Configuration config = new Configuration();
        config.setClassForTemplateLoading(FormSubmitExample.class, "/");
        Map<String, Object> formMap = new HashMap<>();

        get("/", ((request, response) -> {

            StringWriter writer = new StringWriter();
            Template template = config.getTemplate("basicForm.ftl");
            formMap.clear();
            formMap.put("fruits", Arrays.asList("Apple", "Orange", "Grape", "Pineapple"));

            template.process(formMap, writer);

            return writer;

        }));

        post("/save_fruit", ((request, response) -> {

            String formResponse = "";
            StringWriter writer = new StringWriter();
            Template template = config.getTemplate("formResponse.ftl");
            formMap.clear();

            if (request.queryParams("fruit") == null){
                formResponse = "Pick a fruit";
            }else {
                formResponse = "You like " + request.queryParams("fruit");
            }

            formMap.put("response", formResponse);
            template.process(formMap, writer);

            return writer;
        }));


    }
}
