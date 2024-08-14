package org.dashia18.util.functionalinterface;

@FunctionalInterface
public interface Converter<T, R> {
    R convert(T input);
}
