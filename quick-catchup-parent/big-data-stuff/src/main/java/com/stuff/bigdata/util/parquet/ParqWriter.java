package com.stuff.bigdata.util.parquet;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import parquet.column.ParquetProperties;
import parquet.example.data.Group;
import parquet.example.data.GroupFactory;
import parquet.example.data.simple.SimpleGroupFactory;
import parquet.hadoop.ParquetWriter;
import parquet.hadoop.example.GroupWriteSupport;
import parquet.schema.MessageType;

import java.io.IOException;

/**
 * parquet file writer
 */
public class ParqWriter {

    private MessageType schema;
    private Path path;

    public ParqWriter(MessageType schema, Path path){

        this.schema = schema;
        this.path = path;
    }

    /**
     *
    public void writeParquetFileUsingAvroSchema(Schema schema){

        AvroParquetWriter<GenericRecord> writer = new AvroParquetWriter<GenericRecord>(path, schema);
        writer.write(datum);
        writer.close();
    }

     */
    public void writeParquetFile() throws IOException {

        Group record = generateData();

        ParquetWriter<Group> writer = config();

        writer.write(record);
        writer.close();
    }

    private Group generateData() {
        GroupFactory groupFactory = new SimpleGroupFactory(schema);
        return groupFactory.newGroup()
                .append("left", "L")
                .append("right", "R");
    }

    private ParquetWriter<Group> config() throws IOException {
        Configuration conf = new Configuration();

        GroupWriteSupport writeSupport = new GroupWriteSupport();
        GroupWriteSupport.setSchema(schema, conf);

        return new ParquetWriter<Group>(path, writeSupport,
                ParquetWriter.DEFAULT_COMPRESSION_CODEC_NAME,
                ParquetWriter.DEFAULT_BLOCK_SIZE,
                ParquetWriter.DEFAULT_PAGE_SIZE,
                ParquetWriter.DEFAULT_PAGE_SIZE,
                ParquetWriter.DEFAULT_IS_DICTIONARY_ENABLED,
                ParquetWriter.DEFAULT_IS_VALIDATING_ENABLED,
                ParquetProperties.WriterVersion.PARQUET_2_0, conf);
    }
}
