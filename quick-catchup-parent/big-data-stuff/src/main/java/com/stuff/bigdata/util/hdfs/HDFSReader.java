package com.stuff.bigdata.util.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

public class HDFSReader {

    static {
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }

    public static void main(String[] args) throws Exception {

        //readUsingURL(args);

        readByHDFS(args[0]);


    }

    private static void readByHDFS(String arg) throws IOException {

        System.out.println("HDFS Method");
        String uri = arg;

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        InputStream in = null;

        try{

            in = fs.open(new Path(uri));
            IOUtils.copyBytes(in, System.out, 4096, false);
        } finally {
            IOUtils.closeStream(in);
        }
    }

    // We still need to run this using hadoop command, <hadoop jar classname params>
    private static void readUsingURL(String[] args) throws IOException {

        System.out.println("JAVA URI Method");
        InputStream in = null;

        try{
            in = new URL(args[0]).openStream();
            IOUtils.copyBytes(in, System.out, 4096, false);
        } finally {
            IOUtils.closeStream(in);
        }
    }


}
