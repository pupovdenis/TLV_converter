package ru.pupov.extractor.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.pupov.extractor.Extractor;
import ru.pupov.utils.Utils;

public class TLVfvlnExtractor implements Extractor {

    Logger logger = LoggerFactory.getLogger(TLVfvlnExtractor.class);

    /**
     * Максимальная длина обрабатываемого масива байтов.
     */
    private static final int MAX_LENGTH = 8;

    @Override
    public Double extract(byte[] bytes, boolean isLittleEndian) {
        if (bytes.length > MAX_LENGTH) {
            logger.warn("Incorrect data: length = " + bytes.length);
            return null;
        }
        int pointOrder = bytes[0];
        bytes[0] = 0x0;

        return Utils.getLongFromBinaryArray(bytes, isLittleEndian) / Math.pow(10, pointOrder);
    }
}
