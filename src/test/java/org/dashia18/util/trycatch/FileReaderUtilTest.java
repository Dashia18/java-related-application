package org.dashia18.util.trycatch;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FileReaderUtilTest {

    @InjectMocks
    private FileReaderUtil fileReaderUtil;

    @Test
    public void canParsePersons() {
        List<FileReaderUtil.Person> actual = fileReaderUtil.parsePersonsFromFile("name-to-date-of-birth.txt");

        assertThat(actual).hasSize(5);
    }
}
