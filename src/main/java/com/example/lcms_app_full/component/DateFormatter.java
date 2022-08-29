package com.example.lcms_app_full.component;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateFormatter {
    public Date stringToDate(String date) throws ParseException {
        Date date1=new SimpleDateFormat("dd.MM.yyyy").parse(date);
        return date1;
    }

}
