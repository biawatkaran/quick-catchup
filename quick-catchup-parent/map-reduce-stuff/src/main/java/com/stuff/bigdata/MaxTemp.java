package com.stuff.bigdata;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;

public class MaxTemp {

    public static void main(String[] args) throws Exception{

        if(args.length != 2){
            System.err.println("Usage: MaxTemp <input path> <output path>");
            System.exit(1);
        }

        JobConf jobConf = new JobConf();
        jobConf.setJobName("Max Temperature");

        FileInputFormat.addInputPath(jobConf, new Path(args[0]));
        FileOutputFormat.setOutputPath(jobConf, new Path(args[1]));

        Job job = Job.getInstance(jobConf);
        job.setJarByClass(MaxTemp.class);
        job.setMapperClass(MaxTempMapper.class);
        job.setReducerClass(MaxTempReducer.class);

        jobConf.setOutputKeyClass(Text.class);
        jobConf.setOutputValueClass(IntWritable.class);

        System.exit(job.waitForCompletion(true)? 0 :1);
    }
}
