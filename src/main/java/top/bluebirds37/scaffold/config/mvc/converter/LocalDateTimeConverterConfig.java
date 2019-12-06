package top.bluebirds37.scaffold.config.mvc.converter;

import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverterConfig {

    /**
     * LocalDateTime格式化
     */
    private static final String LOCAL_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * LocalDate格式化
     */
    private static final String LOCAL_DATE_FORMAT = "yyyy-MM-dd";

    private static final String LOCAL_TIME_FORMAT = "HH:mm:ss";

    @Bean
    public StringToLocalDateTimeConverter localDateTimeConverter() {
        return new StringToLocalDateTimeConverter();
    }

    @Bean
    public StringToLocalDateConverter localDateConverter() {
        return new StringToLocalDateConverter();
    }

    @Bean
    public StringToLocalTimeConverter localTimeConverter() {
        return new StringToLocalTimeConverter();
    }

    private static class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {
        @Override
        public LocalDateTime convert(String source) {
            return LocalDateTime.parse(source, DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_FORMAT));
        }
    }

    private static class StringToLocalDateConverter implements Converter<String, LocalDate> {
        @Override
        public LocalDate convert(String source) {
            return LocalDate.parse(source, DateTimeFormatter.ofPattern(LOCAL_DATE_FORMAT));
        }
    }

    private static class StringToLocalTimeConverter implements Converter<String, LocalTime> {
        @Override
        public LocalTime convert(String source) {
            return LocalTime.parse(source, DateTimeFormatter.ofPattern(LOCAL_TIME_FORMAT));
        }
    }


}
