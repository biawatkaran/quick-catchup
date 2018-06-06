package com.stuff.bigdata.util.avro;

import com.stuff.bigdata.AvroStringPair;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * avro reader
 */
public class AvroReader {

    private Schema schema;
    private ByteArrayOutputStream out;

    public AvroReader(Schema schema, ByteArrayOutputStream out){
        this.schema = schema;
        this.out = out;
    }

    public void readFromStreamUsingGenericMapping() throws IOException {

        DatumReader<GenericRecord> reader = new GenericDatumReader<GenericRecord>(schema);
        Decoder decoder = DecoderFactory.get().binaryDecoder(out.toByteArray(), null);
        GenericRecord result = reader.read(null, decoder);

        System.out.println(result.get("left"));
        System.out.println(result.get("right"));

    }

    public void readFromStreamUsingSpecificMapping() throws IOException {

        DatumReader<AvroStringPair> reader = new SpecificDatumReader<AvroStringPair>(AvroStringPair.class);
        Decoder decoder = DecoderFactory.get().binaryDecoder(out.toByteArray(), null);
        AvroStringPair result = reader.read(null, decoder);

        System.out.println(result.getLeft());
        System.out.println(result.getRight());
    }
}
