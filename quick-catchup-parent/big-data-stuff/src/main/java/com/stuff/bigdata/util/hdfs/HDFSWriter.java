package com.stuff.bigdata.util.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import java.io.*;
import java.net.URI;

public class HDFSWriter {

    public static void main(String[] args) throws Exception {

        String localSrc = args[0];
        String dst = args[1];

        writeUsingHDFS(localSrc, dst);
    }

    private static void writeUsingHDFS(String localSrc, String dst) throws IOException {
        InputStream in = new BufferedInputStream(new FileInputStream(localSrc));

        Configuration conf = new Configuration();

        FileSystem fs = FileSystem.get(URI.create(dst), conf);

        FSDataOutputStream out = fs.create(new Path(dst), new Progressable() {
            public void progress() {
                System.out.println(".");
            }
        });

        IOUtils.copyBytes(in, out, 4096, true);
    }

}
