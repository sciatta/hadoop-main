package com.sciatta.dev.bigdata.spark.example.rdd.wordcount;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yangxiaoyu on 2020/3/11<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * OnLocalByJava
 */
public class OnLocalByJava {
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setMaster("local[2]").setAppName(OnLocalByJava.class.getName());

        JavaSparkContext context = new JavaSparkContext(sparkConf);

        JavaRDD<String> rdd = context.textFile("/Users/yangxiaoyu/work/test/mrdatas/wordcount/input");

        JavaRDD<String> rddWord = rdd.flatMap((FlatMapFunction<String, String>) line -> {
            String[] words = line.split(",");
            return Arrays.asList(words).iterator();
        });

        JavaPairRDD<String, Integer> rddWordOne = rddWord.mapToPair((PairFunction<String, String, Integer>) word -> new Tuple2<>(word, 1));


        JavaPairRDD<String, Integer> rddReduce = rddWordOne.reduceByKey((Function2<Integer, Integer, Integer>) Integer::sum);

        // 元组的元素交换顺序
        JavaPairRDD<Integer, String> rddReverse =
                rddReduce.mapToPair((PairFunction<Tuple2<String, Integer>, Integer, String>) t -> new Tuple2<>(t._2, t._1));

        // 只能对key排序
        JavaPairRDD<String, Integer> sortedRDD =
                rddReverse.sortByKey(false).mapToPair((PairFunction<Tuple2<Integer, String>, String, Integer>) t -> new Tuple2<>(t._2, t._1));

        List<Tuple2<String, Integer>> collect = sortedRDD.collect();

        for (Tuple2<String, Integer> c : collect) {
            System.out.println(c._1 + " " + c._2);
        }

        context.close();

    }
}
