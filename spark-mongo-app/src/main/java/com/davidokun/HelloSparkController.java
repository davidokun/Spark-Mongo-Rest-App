package com.davidokun;

import static spark.Spark.get;

/**
 * Created by David Marin
 */
public class HelloSparkController {

    public static void main( String[] args ) {

        get("/hello", (req, res) -> "Hello Spark Java");

    }
}
