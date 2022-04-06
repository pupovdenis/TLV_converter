package ru.pupov.parser;

import ru.pupov.model.TLVOrder;

public interface Parser {
    TLVOrder parse(byte[] bytes);
}
