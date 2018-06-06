package com.stuff.bigdata.spark

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.Result
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.Predef

/**
  * Created by Beauty on 2/22/16.
  */
object HBaseRunner {

  def main(args: Array[String]) {

    val config: Configuration = HBaseConfiguration.create()
    config.set(TableInputFormat.INPUT_TABLE, "testtable")
    config.set(TableInputFormat.SCAN_COLUMNS, "colfam1:q1")

    val sconf: SparkConf = new SparkConf().setAppName("HBase Runner")
    val sc: SparkContext = new SparkContext(sconf)

    val hbaseRDD: RDD[(ImmutableBytesWritable, Result)] = sc.newAPIHadoopRDD(config, classOf[TableInputFormat], classOf[ImmutableBytesWritable], classOf[Result])

    val resultRDD: RDD[Result] = hbaseRDD.map(tuple => tuple._2)

    val keyValRDD: RDD[(String, Array[Byte])] = resultRDD.map(result => (new Predef.String(result.getRow), result.value()))

    keyValRDD.map(tuple => println("Row: " + tuple._1 + " value: " + (tuple._2).map(x => println(x.toString))))


  }

}
