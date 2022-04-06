package ru.pupov.extractor.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.pupov.extractor.Extractor;
import ru.pupov.utils.Utils;

public class TLVvlnExtractor implements Extractor {

    Logger logger = LoggerFactory.getLogger(TLVvlnExtractor.class);

    /**
     * Максимальная длина обрабатываемого масива байтов.
     */
    private static final int MAX_LENGTH = 8;

    @Override
    public Long extract(byte[] bytes, boolean isLittleEndian) {
        if (bytes.length > MAX_LENGTH) {
            logger.warn("Incorrect data: length = " + bytes.length);
            return null;
        }
        if (bytes.length == 0) {
            return null;
        }
        long longFromBinaryArray = Utils.getLongFromBinaryArray(bytes, isLittleEndian);
        return longFromBinaryArray;
    }
}
