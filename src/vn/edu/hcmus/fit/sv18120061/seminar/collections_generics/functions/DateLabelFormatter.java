package vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.functions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField.AbstractFormatter;

/**
 * @vn.edu.hcmus.fit.sv18120061.seminar.collections_generics.functions
 * @Author: Le Nhut Nam
 * @Date: 27/05/2020
 * @Organization: VNU - FIT HCMUS
 * @Copyright (c) 2020
 * @LICENSE: MIT LICENSE
 */
public class DateLabelFormatter extends AbstractFormatter {

    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }

}