package com.davidokun.spark.examples;

import static spark.Spark.get;

/**
 * Created by David Marin
 */
public class HelloSparkController {

    public static void main( String[] args ) {

        get("/hello", (req, res) -> "Hello Spark Java");

        get("/test", (req, res) -> "Hello test for Spark");

        get("/echo/:something", (req, res) -> req.params(":something"));


    }
}
