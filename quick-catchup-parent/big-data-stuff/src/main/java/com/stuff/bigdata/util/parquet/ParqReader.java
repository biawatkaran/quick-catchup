package com.stuff.bigdata.util.parquet;

import org.apache.avro.generic.GenericRecord;
import org.apache.hadoop.fs.Path;
import parquet.example.data.Group;
import parquet.hadoop.ParquetReader;
import parquet.hadoop.example.GroupReadSupport;

import java.io.IOException;

/**
 * read parquet file
 */
public class ParqReader {

    private Path path;

    public ParqReader(Path path){

        this.path = path;
    }

    public void readParquetFile() throws IOException {

        GroupReadSupport readSupport = new GroupReadSupport();
        ParquetReader<Group> reader = ParquetReader.builder(readSupport, path).build();

        Group record = reader.read();
        while(record != null) {
            String left = record.getString("left", 0);
            String right = record.getString("right", 0);
            System.out.println("Left: " + left + "\n Right: " +right);
            record = reader.read();
        }
    }

    /**
     *
      public void readParquetFileUsingAvroSchema(Schema avroSchema){
        AvroParquetWriter<GenericRecord> writer = new AvroParquetWriter<GenericRecord>(path, avroSchema);
        writer.write(datum);
        writer.close();
    }

     */
}
