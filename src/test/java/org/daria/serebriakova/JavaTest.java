package org.daria.serebriakova;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JavaTest {


    /*
    The Integer Cache in the JVM is a mechanism to optimize memory usage and improve performance
    by reusing Integer objects for frequently used small values. The default cache range is
    from -128 to 127, but it can be configured at runtime using the -XX:AutoBoxCacheMax=<value> JVM option.
     */
    @Test
    void canCheckIntegerCache() {
        Integer a = -129;
        Integer b = -129;
        Integer i = -128;
        Integer j = -128;
        Integer k = 127;
        Integer l = 127;
        Integer o = 128;
        Integer p = 128;

        Assertions.assertEquals(a, b);
        Assertions.assertEquals(i, j);
        Assertions.assertEquals(k, l);
        Assertions.assertEquals(o, p);

        Assertions.assertNotSame(a, b);
        Assertions.assertSame(i, j);
        Assertions.assertSame(k, l);
        Assertions.assertNotSame(o, p);
    }
}
