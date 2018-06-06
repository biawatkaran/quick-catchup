package com.stuff.bigdata.runner;

import com.stuff.bigdata.util.hbase.HBaseUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;

import java.io.IOException;

/**
 * Created by Beauty on 2/21/16.
 */
public class HBaseRunner {

    public static void main(String[] args) throws IOException {

        Configuration config = HBaseConfiguration.create();
        config.set(TableInputFormat.SCAN_COLUMNS,"colfam1:q1");
        config.set(TableInputFormat.SCAN_ROW_START, "myrow-1");
        config.set(TableInputFormat.SCAN_ROW_STOP, "myrow-3");

        try(
                Connection connection = ConnectionFactory.createConnection(config);
                Table table = connection.getTable(TableName.valueOf("testtable"));
        ) {

            //HBaseUtil.getByRowKey(table, "myrow-1");

            HBaseUtil.scanTable(table);
        }

    }
}
