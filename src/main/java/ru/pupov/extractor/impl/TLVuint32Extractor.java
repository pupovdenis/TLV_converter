package ru.pupov.extractor.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.pupov.extractor.Extractor;
import ru.pupov.utils.Utils;

public class TLVuint32Extractor implements Extractor {

    Logger logger = LoggerFactory.getLogger(TLVuint32Extractor.class);

    /**
     * Фиксированная длина обрабатываемого масива байтов.
     */
    private static final int FIX_LENGTH = 4;

    @Override
    public Long extract(byte[] bytes, boolean isLittleEndian) {
        if (bytes.length != FIX_LENGTH) {
            logger.warn("Incorrect data: length < " + FIX_LENGTH);
            return null;
        }
        int seconds = Utils.getIntFromBinaryArray(bytes, isLittleEndian);
        return seconds * 1000L;
    }
}
