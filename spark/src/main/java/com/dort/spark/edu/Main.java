package com.dort.spark.edu;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.storage.StorageLevel;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("HelloSpark");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> stringJavaRDD = sc.textFile("spark_hello/src/main/resources/issues.txt");
        JavaRDD<Integer> integerJavaRDD = sc.parallelize(Arrays.asList(1, 2, 3, 4, 5));
        JavaRDD<Integer> mapRDD = integerJavaRDD.map(x -> x * 2);
        System.out.println("text file count line");
        System.out.println(stringJavaRDD.count());

        //lesson 1 transformations : map filter flatMap distinct intersection subtract
        System.out.println("map");
        mapRDD.foreach(x -> System.out.println(x));
        JavaRDD<Integer> filterRDD = mapRDD.filter(x -> x != 2);
        System.out.println("filter");
        filterRDD.foreach(x -> System.out.println(x));
        JavaRDD<Integer> flatMapRDD = filterRDD.flatMap(x -> new ArrayList(Arrays.asList(x, x * 2)).iterator());
        System.out.println("flat map");
        flatMapRDD.foreach(x -> System.out.println(x));
        System.out.println("distinct");
        JavaRDD<Integer> list1 = sc.parallelize(Arrays.asList(1, 1, 2, 3, 3));
        JavaRDD<Integer> list2 = sc.parallelize(Arrays.asList(1, 2, 3, 1, 2));
        list1.distinct().foreach(x -> System.out.println(x));
        list1.union(list2).foreach(x -> System.out.println(" - " + x));

        //lesson 2 actions : reduce collect take top takeSample count countByValue
        System.out.println(list1.reduce((x,y) -> x + y));
        System.out.println(list1.collect());
        System.out.println(list1.countByValue());
        System.out.println(list1.take(2));
        System.out.println(list1.top(2));
        System.out.println(list1.takeSample(true, 2));

        //lesson3 cached необходим, если требуется сохранить значения к себе
        list1.persist(StorageLevel.MEMORY_ONLY());
        list1.count();
        list1.collect();
        list1.unpersist();

        //lesson4 pared RDD
        Tuple2<String, String> tuple2 = new Tuple2<>("one", "two");
        System.out.println(tuple2._1());
        System.out.println(tuple2._2());

        JavaRDD<String> linesString = sc.parallelize(Arrays.asList("one", "two", "three", "four"));
        JavaPairRDD<String, String> mapToPair = linesString.mapToPair(x -> new Tuple2<>(x, x));
        System.out.println(mapToPair.collect());

        List<Tuple2<String, String>> tuple2List = Arrays.asList(new Tuple2<>("1", "one"), new Tuple2<>("2", "two"), new Tuple2<>("1", "rty"));
        JavaPairRDD<String, String> mapToPairTuple = sc.parallelizePairs(tuple2List);
        JavaPairRDD<String, String> filterReduce = mapToPairTuple.reduceByKey((x, y) -> x + y);
        System.out.println(mapToPairTuple.collect());
        System.out.println(filterReduce.collect());
        JavaPairRDD<String, Iterable<String>> groupByKey = mapToPairTuple.groupByKey();
        System.out.println(groupByKey.collect());
        //lesson 5 чтение и запись файлов
        JavaPairRDD<String, String> wholeTextFile = sc.wholeTextFiles("spark_hello/src/main/resources/issues.txt");
        System.out.println(stringJavaRDD.collect());
        System.out.println(wholeTextFile.collect());
        //save
        stringJavaRDD.saveAsObjectFile("spark_hello/src/main/resources/issues2");
        JavaRDD<Object> objectJavaRDD = sc.objectFile("spark_hello/src/main/resources/issues2");
        System.out.println(objectJavaRDD.collect());

    }
}
