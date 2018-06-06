package com.stuff.bigdata.util.crunch;

import com.stuff.bigdata.util.NcdcRecordParser;
import org.apache.crunch.*;
import org.apache.crunch.fn.Aggregators;
import org.apache.crunch.impl.mr.MRPipeline;
import org.apache.crunch.io.To;
import org.apache.crunch.types.PTypeFamily;
import org.apache.crunch.types.avro.Avros;

/**
 * calculate max temp using crunch api
 */
public class MaxTemperatureCrunch {

    public static void main(String[] args) {

        if(args.length != 2){
            System.err.println("Usage: MaxTemperatureCrunch <ip> <op>");
            System.exit(1);
        }

        Pipeline pipeline = new MRPipeline(MaxTemperatureCrunch.class);

        PCollection<String> lines = pipeline.readTextFile(args[0]);

        PTypeFamily tf = lines.getTypeFamily();
        // PTable is multimap
        PTable<String, Integer> yearTemp = lines.parallelDo(toYearTempPairsFn(),tf.tableOf(tf.strings(), tf.ints()));

        PTable<String, Integer> maxTemps = yearTemp.groupByKey().combineValues(Aggregators.MAX_INTS());
        maxTemps.write(To.textFile(args[1]));

        PipelineResult result = pipeline.done();
        System.exit(result.succeeded()? 0 : 1);
    }

    /**
     * DoFn has another subclass MapFn and can be used when 1:1 mapping, i.e. output items are same as input items
     * FilterFn another implementation where we can filter inputs not needed, by implementing accept method
     *
     */
    private static DoFn<String, Pair<String, Integer>> toYearTempPairsFn() {
        return new DoFn<String, Pair<String, Integer>>() {
            NcdcRecordParser parser = new NcdcRecordParser();
            @Override
            public void process(String line, Emitter<Pair<String, Integer>> emitter) {
                parser.parse(line);
                if(parser.isValidTemperature()){
                    emitter.emit(Pair.of(parser.getYear(), parser.getAirTemp()));
                }
            }
        };
    }

    /**
     * always prefer to use records over tuple as more readable/concise
     */
    private PCollection<WeatherRecord> generateRecords(PCollection<String> lines){

        PCollection<WeatherRecord> weatherRecordPCollection = lines.parallelDo(new DoFn<String, WeatherRecord>() {

            NcdcRecordParser parser = new NcdcRecordParser();

            @Override
            public void process(String input, Emitter<WeatherRecord> emitter) {

                parser.parse(input);
                if (parser.isValidTemperature()) {
                    emitter.emit(new WeatherRecord(parser.getYear(), parser.getAirTemp(), parser.getQuality()));
                }
            }
        }, Avros.records(WeatherRecord.class));

        return weatherRecordPCollection;
    }

    private static class WeatherRecord {

        private String year;
        private int temperature;
        private String quality;

        public WeatherRecord() {
        }

        public WeatherRecord(String year, int temperature, String quality) {
            this.year = year;
            this.temperature = temperature;
            this.quality = quality;
        }
    }
}
