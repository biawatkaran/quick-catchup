package com.stuff.bigdata.util.avro;

import com.stuff.bigdata.AvroStringPair;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * avro writer using schema
 */
public class AvroWriter {

    private Schema schema;
    private ByteArrayOutputStream out;

    public AvroWriter(Schema schema, ByteArrayOutputStream out){
        this.schema = schema;
        this.out = out;
    }

    public void writeToStreamUsingGenericMapping() throws IOException {

        GenericRecord record = dummyData();

        DatumWriter<GenericRecord> writer = new GenericDatumWriter<GenericRecord>(schema);
        Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);
        writer.write(record, encoder);
        encoder.flush();
        out.close();
    }

    private GenericRecord dummyData() {
        GenericRecord dataum = new GenericData.Record(schema);
        dataum.put("left", "L");
        dataum.put("right", "R");
        return dataum;
    }

    // 2nd method using maven plugin
    public void writeToStreamUsingSpecificMapping() throws IOException {

        AvroStringPair record = generateData();
        DatumWriter<AvroStringPair> writer = new SpecificDatumWriter<AvroStringPair>(AvroStringPair.class);
        Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);
        writer.write(record, encoder);
        encoder.flush();
        out.close();
    }

    private AvroStringPair generateData() {

        AvroStringPair asp = new AvroStringPair();
        asp.setLeft("L");
        asp.setRight("R");
        return asp;
    }
}
