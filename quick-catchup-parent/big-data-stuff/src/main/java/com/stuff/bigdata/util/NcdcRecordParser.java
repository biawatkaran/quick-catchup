package com.stuff.bigdata.util;

import org.apache.hadoop.io.Text;

/**
 * Parser for Ncdc Temperature
 */
public class NcdcRecordParser {

    private static final int MISSING = 9999;

    private String year;
    private int airTemp;
    private String quality;

    public void parse(Text value){
        parse(value.toString());
    }

    public void parse(String value) {

        year = value.substring(15, 19);

        if(value.charAt(87) == '+'){
            airTemp = Integer.parseInt(value.substring(88, 92));
        } else {
            airTemp = Integer.parseInt(value.substring(87, 92));
        }

        quality = value.substring(94, 95);
    }

    public boolean isValidTemperature() {
        return airTemp != MISSING && quality.matches("[01459]");
    }

    public String getYear() {
        return year;
    }

    public int getAirTemp() {
        return airTemp;
    }

    public String getQuality() {
        return quality;
    }
}
