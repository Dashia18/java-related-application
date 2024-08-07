package org.daria.serebriakova.util.functionalinterface;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ConverterTest {

    @Test
    public void canImplementAbstractMethodWithGenerics() {
        Converter<String, Integer> stringToInteger = Integer::valueOf;

        int actual = stringToInteger.convert("123");

        assertThat(actual).isEqualTo(123);
    }
}
