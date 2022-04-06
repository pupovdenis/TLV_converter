package ru.pupov.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.pupov.model.TLVOrder;
import ru.pupov.parser.Parser;
import ru.pupov.parser.impl.TLVParser;

public class TLVConverter implements Converter{

    private final Parser parser = new TLVParser();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String getJson(byte[] bytes) throws JsonProcessingException {

        TLVOrder tlvOrder = parser.parse(bytes);

        return mapper.writeValueAsString(tlvOrder);

    }
}
