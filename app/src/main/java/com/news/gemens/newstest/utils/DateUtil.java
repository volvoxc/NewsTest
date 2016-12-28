package com.news.gemens.newstest.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Gemens on 2016/12/28/0028.
 */

public class DateUtil {

    public static String getDateBeforeByDay(int beforeDay) {

        SimpleDateFormat sDateFormat =  new SimpleDateFormat("yyyyMMdd");

        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        ca.add(Calendar.DAY_OF_YEAR, -beforeDay);
        return sDateFormat.format(ca.getTime());
    }

}
