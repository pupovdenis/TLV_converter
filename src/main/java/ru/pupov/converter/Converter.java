package ru.pupov.converter;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface Converter {
    String getJson(byte[] bytes) throws JsonProcessingException;
}
