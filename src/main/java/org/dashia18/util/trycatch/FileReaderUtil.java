package org.dashia18.util.trycatch;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileReaderUtil {
    private final ResourceLoader resourceLoader;

    public List<Person> parsePersonsFromFile(String fileName) {
        List<Person> persons = new ArrayList<>();

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(fileName)) {
            if (is == null) {
                return Collections.emptyList();
            }
            try (InputStreamReader isr = new InputStreamReader(is); BufferedReader reader = new BufferedReader(isr)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    log.info("Person line: {}", line);
                    Iterable<String> split = Splitter.on(":")
                            .omitEmptyStrings()
                            .trimResults()
                            .split(line);
                    List<String> stringValues = Lists.newArrayList(split);
                    if (stringValues.size() != 2) {
                        log.error("Skipp row ...");
                    }
                    persons.add(Person.builder()
                            .name(stringValues.get(0))
                            .date(LocalDate.parse(stringValues.get(1), DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                            .build());
                }
            }
        } catch (IOException e) {
            log.error("Some error occurred");
        }
        return persons;
    }

    public static Object createJsonFromResource(String path, Class clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try (InputStream stream = FileReaderUtil.class
                .getResourceAsStream(path)) {
            return mapper.readValue(stream, clazz);
        }
    }

    @Data
    @Builder
    public static class Person {
        private String name;
        private LocalDate date;
    }
}
