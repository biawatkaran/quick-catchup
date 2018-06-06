package com.stuff.bigdata.runner;

import com.stuff.bigdata.util.parquet.ParqReader;
import com.stuff.bigdata.util.parquet.ParqWriter;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.hadoop.fs.Path;
import parquet.schema.MessageType;
import parquet.schema.MessageTypeParser;

import java.io.IOException;

/**
 * test parquet functionality
 */
public class ParquetRunner {

    public static void main(String[] args) throws IOException {

        ParquetRunner parquetRunner = new ParquetRunner();
        MessageType parquetSchema = parquetRunner.defineSchema();
        Path path = new Path("data.parquet");

        System.out.println("======================== 1st Method using parquet ==========================");
        System.out.println("============ Write Parquet File =============");
        ParqWriter writer = new ParqWriter(parquetSchema, path);
        writer.writeParquetFile();

        System.out.println("============ Read Parquet File =============");
        ParqReader reader= new ParqReader(path);
        reader.readParquetFile();

        /**
        System.out.println("======================== 2nd Method using avro ==========================");
        Schema avroSchema = parquetRunner.getSchema();
        parquetRunner.dummyData(avroSchema);
        writer.writeParquetFileUsingAvroSchema(avroSchema);

         */
    }

    private MessageType defineSchema() {
       return MessageTypeParser.parseMessageType(
                "message Pair { \n" +
                        "required binary left (UTF8); \n" +
                        "required binary right (UTF8);" +
                        "}" );
    }

    private Schema getSchema() throws IOException {
        Schema.Parser parser = new Schema.Parser();
        return parser.parse(getClass().getResourceAsStream("/StringPairs.avsc"));
    }

    private GenericRecord dummyData(Schema schema) {
        GenericRecord dataum = new GenericData.Record(schema);
        dataum.put("left", "L");
        dataum.put("right", "R");
        return dataum;
    }
}
