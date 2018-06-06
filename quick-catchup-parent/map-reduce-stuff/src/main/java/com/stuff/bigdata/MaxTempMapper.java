package com.stuff.bigdata;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MaxTempMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    public static final int MISSING = 9999;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        String year = line.substring(17, 21);

        int airTemp;
        if(line.charAt(42) == '+'){
            airTemp = Integer.parseInt(line.substring(43, 47));
        } else {
            airTemp = Integer.parseInt(line.substring(42, 47));
        }

        String quality = line.substring(47, 48);

        System.out.println("Karan >>> " + year + ": " + airTemp + ": " + quality);

        if(airTemp != MISSING && quality.matches("[01459]")){
            context.write(new Text(year), new IntWritable(airTemp));
        }
    }
}
