package com.dort.spark.edu;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("HelloSpark");
        JavaSparkContext context = new JavaSparkContext(conf);
        JavaRDD<String> stringJavaRDD = context.textFile("spark_hello/src/main/resources/issues.txt");
        JavaRDD<Integer> integerJavaRDD = context.parallelize(Arrays.asList(1, 2, 3, 4, 5));
        JavaRDD<Integer> mapRDD = integerJavaRDD.map(x -> x * 2);
        System.out.println("text file count line");
        System.out.println(stringJavaRDD.count());
        System.out.println("map");
        mapRDD.foreach(x -> System.out.println(x));
        JavaRDD<Integer> filterRDD = mapRDD.filter(x -> x != 2);
        System.out.println("filter");
        filterRDD.foreach(x -> System.out.println(x));
        JavaRDD<Integer> flatMapRDD = filterRDD.flatMap(x -> new ArrayList(Arrays.asList(x, x * 2)).iterator());
        System.out.println("flat map");
        flatMapRDD.foreach(x -> System.out.println(x));
        System.out.println("distinct");
        JavaRDD<Integer> list1 = context.parallelize(Arrays.asList(1, 1, 2, 3, 3));
        JavaRDD<Integer> list2 = context.parallelize(Arrays.asList(1, 2, 3, 1, 2));
        list1.distinct().foreach(x -> System.out.println(x));
        list1.union(list2).foreach(x -> System.out.println(" - " + x));
    }
}
