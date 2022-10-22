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
        Date d = null;
        try{
            d = parser.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return d;
    }

    public String formatDate(Date d) {
        return parser.format(d);
    }
}
