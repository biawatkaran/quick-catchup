package com.stuff.bigdata.runner;

import com.stuff.bigdata.util.avro.AvroReader;
import com.stuff.bigdata.util.avro.AvroWriter;
import org.apache.avro.Schema;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * run avro stuff
 * 1. using generic mapping, using existing avro file we write/read using GenericDatum
 * 2. using specific mapping, maven tool is used to generate classes from avro schema file
 */
public class AvroRunner {

    public static void main(String[] args) throws IOException {

        Schema schema = new AvroRunner().getSchema();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        System.out.println("================== Generic Mapping =========================");
        AvroWriter writer = new AvroWriter(schema, out);
        writer.writeToStreamUsingGenericMapping();

        AvroReader reader = new AvroReader(schema, out);
        reader.readFromStreamUsingGenericMapping();

        System.out.println("================== Specific Mapping =========================");
        writer.writeToStreamUsingSpecificMapping();
        reader.readFromStreamUsingSpecificMapping();
    }

    private Schema getSchema() throws IOException {
        Schema.Parser parser = new Schema.Parser();
        return parser.parse(getClass().getResourceAsStream("/StringPairs.avsc"));
    }
}
