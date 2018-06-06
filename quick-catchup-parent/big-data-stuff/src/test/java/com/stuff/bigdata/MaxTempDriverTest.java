package com.stuff.bigdata;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

/**
 * Driver Tests
 */
public class MaxTempDriverTest {

    @Test
    public void testLocalFileUsingLocalJobRunner() throws Exception {

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "file:///");
        conf.set("mapreduce.framework.name", "local");
        conf.setInt("mapreduce.task.io.sort.mb", 1);

        Path input = new Path("input/data.txt");
        Path output = new Path("output");

        FileSystem fs = FileSystem.getLocal(conf);
        fs.delete(output, true);

        MaxTempDriver driver = new MaxTempDriver();
        driver.setConf(conf);

        int exitCode = driver.run(new String[]{
                input.toString(), output.toString()
        });

        assert(exitCode == 0);

        //checkOutput(conf, output);
    }

    /**
    @Test
    @Ignore
    public void testUsingMiniCluster(){

        hadoop jar $HADOOP_HOME/share/hadoop/mapreduce/hadoop-mapreduce-*-tests.jar minicluster
       ClusterMapReduceTestCase good point to start

    }

    */
}
