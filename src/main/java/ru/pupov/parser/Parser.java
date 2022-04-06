package ru.pupov.parser;

import ru.pupov.model.Data;
import ru.pupov.model.TLVOrder;

import java.util.List;

public interface Parser {
    TLVOrder parse(byte[] bytes);
}
