package com.stuff.bigdata.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
  * word count using spark
  */
object WordCount {

  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("Word Count")
    val sc = new SparkContext(conf)

    val lines = sc.textFile(args(0))

    val words = lines.flatMap(line => line.split(" "))
    val wordCounts = words.map(word => (word, 1)).reduceByKey{case (x,y) => x + y}

    wordCounts.saveAsTextFile(args(1))
  }

}
