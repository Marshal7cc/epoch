package org.epoch.starter.core.convert.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.epoch.starter.core.exception.DateConvertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 自定义 Date 转换
 * </p>
 *
 * @author Marshal
 */
public class DateConverter implements Converter<String, Date> {
    private static final Logger logger = LoggerFactory.getLogger(DateConverter.class);
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Override
    public Date convert(String date) {
        if (!StringUtils.hasText(date)) {
            return null;
        }
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat(DATE_FORMAT);
        try {
            return dateFormatGmt.parse(date);
        } catch (ParseException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            throw new DateConvertException(String.format("Can't convert %s to %s", date, DATE_FORMAT), e);
        }
    }
}
