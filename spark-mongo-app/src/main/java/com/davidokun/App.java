package com.davidokun;

import static spark.Spark.get;

/**
 * Created by David Marin
 */
public class App 
{
    public static void main( String[] args ) {

        get("/hello", (req, res) -> "Hello world Spark and Mongo - 2015");

    }
}
