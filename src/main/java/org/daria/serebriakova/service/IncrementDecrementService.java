package org.daria.serebriakova.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
/*
        IncrementDecrementService.prePostIncrement(1);
        IncrementDecrementService.sumPrePostIncrement(11);
        IncrementDecrementService.prePostIncrementDecrement(22, 22);
        IncrementDecrementService.prePostIncrement(11, 22);
        IncrementDecrementService.prePostIncrementDecrement(0);
        IncrementDecrementService.postDecrement(1,2,3);
 */
public class IncrementDecrementService {

    public static void prePostIncrement(int c) {
        log.info("c: {}", c); //1
        log.info("post-increment c: {}", c++); //1
        log.info("c: {}", c); //2
        log.info("pre-increment c: {}", ++c); //3
        log.info("c: {}", c); //3
    }

    public static void prePostIncrement(int a, int b) {
        log.info("before a: {}", a); //11
        log.info("before b: {}", b); //22
        log.info("a + b + a++ + b++ + ++a + ++b: {}", a + b + a++ + b++ + ++a + ++b);
        //11 + 22 + 11 (a=12) + 22 (b =23) + 13 (a = 13) + 24 (b = 24) = 103
        log.info("after aj: {}", a); //13
        log.info("after b: {}", b); //24
    }

    public static void sumPrePostIncrement(int i) {
        log.info("before i: {}", i); //11
        log.info("i++ + ++i: {}", i++ + ++i); //11 (i = 12) + 13 (i =13) = 24
        log.info("after i: {}", i); //13
    }

    public static void prePostIncrementDecrement(int a, int b) {
        log.info("before a: {}", a); //22
        log.info("before b: {}", b); //22
        log.info("--b - ++a + ++b - --a: {}", --b - ++a + ++b - --a); //21 - 23 + 22 - 22 = -2
        log.info("after aj: {}", a); //22
        log.info("after b: {}", b); //22
    }

    public static void prePostIncrementDecrement(int i) {
        log.info("before i: {}", i); //0
        log.info("i++ - --i + ++i - i--: {}", i++ - --i + ++i - i--);
        //0 (i = 1) - 0 (i = 0) + 1 (i = 1) - 1 (i = 0) = 0
        log.info("after i: {}", i); //0
    }

    public static void postDecrement(int i, int j, int k) {
        log.info("before i: {}", i); //1
        log.info("before i: {}", j); //2
        log.info("before i: {}", k); //3
        log.info("i-- - j-- - k--: {}", i-- - j-- - k--); //1 (i = 0) - 2 (i = 1) - 3 (i = 2) = -4
        log.info("after i: {}", i); //0
        log.info("after j: {}", j); //1
        log.info("after k: {}", k); //2
    }
}
