package Utils;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateParser {
    SimpleDateFormat parser;

    public DateParser(String format) {
        parser = new SimpleDateFormat(format);
    }

    public void setFormat(String format) {
        parser = new SimpleDateFormat(format);
    }

    public Date parseDate(String s) {
        try{
            Date d = parser.parse(s);
            return d;
        } catch (ParseException e) {
            return null;
        }
    }

    public Date parseDate(String s, String format) {
        setFormat(format);
        try{
            Date d = parser.parse(s);
            return d;
        } catch (ParseException e) {
            return null;
        }
    }

    public String formatDate(Date d) {
        return parser.format(d);
    }

    public String formatDate(Date d, String format) {
        setFormat(format);
        return parser.format(d);
    }
}
