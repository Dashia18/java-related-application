package org.daria.serebriakova.util.functionalinterface;

@FunctionalInterface
public interface Converter<T, R> {
    R convert(T input);
}
