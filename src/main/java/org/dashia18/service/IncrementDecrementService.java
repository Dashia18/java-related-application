package org.dashia18.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IncrementDecrementService {

    public int calculatePreIncrement(int c) {
        return ++c;
    }

    public int calculatePostIncrement(int c) {
        return c++;
    }

    public int calculatePrePostIncrement(int a, int b) {
        int result = a + b + a++ + b++ + ++a + ++b;
        log.info("a after a + b + a++ + b++ + ++a + ++b: {}", a);
        log.info("b after a + b + a++ + b++ + ++a + ++b: {}", b);
        return result;
    }

    public int calculateSumPrePostIncrement(int i) {
        int result = i++ + ++i;
        log.info("i after i++ + ++i: {}", i);
        return result;
    }

    public int calculatePrePostIncrementDecrement(int a, int b) {
        int result = --b - ++a + ++b - --a;
        log.info("a after --b - ++a + ++b - --a: {}", a);
        log.info("b after --b - ++a + ++b - --a: {}", b);
        return result;
    }

    public int calculatePrePostIncrementDecrement(int i) {
        int result = i++ - --i + ++i - i--;
        log.info("i after i++ - --i + ++i - i--: {}", i);
        return result;
    }

    public int calculatePostDecrement(int i, int j, int k) {
        int result = i-- - j-- - k--;
        log.info("i after i-- - j-- - k--: {}", i); //0
        log.info("j after i-- - j-- - k--: {}", j); //1
        log.info("k after i-- - j-- - k--: {}", k); //2
        return result;
    }
}
