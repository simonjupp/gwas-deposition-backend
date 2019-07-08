package uk.ac.ebi.spot.gwas.deposition.util;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class DateTimeCommon {

    private static DateTimeFormatter ISO_DATE_TIME_FORMATTER = ISODateTimeFormat.dateTime();

    public static DateTimeFormatter getIsoDateTimeFormatter() {
        return ISO_DATE_TIME_FORMATTER;
    }
}
