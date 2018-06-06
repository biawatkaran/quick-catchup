package com.stuff.bigdata;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

/**
 * Test reducer functionality
 */
public class MaxTempReducerTest {

    @Test
    public void returnsMaximumTempForAnYear() throws IOException {

        new ReduceDriver<Text, IntWritable, Text, IntWritable>()
                .withReducer(new MaxTempReducer())
                .withInput(new Text("1950"), Arrays.asList(new IntWritable(5), new IntWritable(10)))
                .withOutput(new Text("1950"), new IntWritable(10))
                .runTest();
    }
}
