package top.bluebirds37.scaffold.config.json;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

//@Configuration
public class LocalDateTimeToJacksonConfig {

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
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        // 序列化
        LocalDateTimeSerializer localDateTimeSerializer = new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_FORMAT));
        LocalDateSerializer localDateSerializer = new LocalDateSerializer(DateTimeFormatter.ofPattern(LOCAL_DATE_FORMAT));
        LocalTimeSerializer localTimeSerializer = new LocalTimeSerializer(DateTimeFormatter.ofPattern(LOCAL_TIME_FORMAT));
        // 反序列化
        LocalDateTimeDeserializer localDateTimeDeserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_FORMAT));
        LocalDateDeserializer localDateDeserializer = new LocalDateDeserializer(DateTimeFormatter.ofPattern(LOCAL_DATE_FORMAT));
        LocalTimeDeserializer localTimeDeserializer = new LocalTimeDeserializer(DateTimeFormatter.ofPattern(LOCAL_TIME_FORMAT));
        return builder -> builder
                .serializerByType(LocalDateTime.class, localDateTimeSerializer)
                .serializerByType(LocalDate.class, localDateSerializer)
                .serializerByType(LocalTime.class, localTimeSerializer)
                .deserializerByType(LocalDateTime.class, localDateTimeDeserializer)
                .deserializerByType(LocalDate.class, localDateDeserializer)
                .deserializerByType(LocalTime.class, localTimeDeserializer)
                ;
    }


}
