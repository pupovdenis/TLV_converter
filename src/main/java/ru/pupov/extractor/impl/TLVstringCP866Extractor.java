package ru.pupov.extractor.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.pupov.extractor.Extractor;

import java.io.UnsupportedEncodingException;

import static sun.security.util.ArrayUtil.reverse;

public class TLVstringCP866Extractor implements Extractor {

    Logger logger = LoggerFactory.getLogger(TLVstringCP866Extractor.class);

    /**
     * Максимальная длина обрабатываемого масива байтов.
     */
    private static final int MAX_LENGTH = 1000;

    public static final String CP866 = "CP866";

    @Override
    public String extract(byte[] bytes, boolean isLittleEndian) {
        if (bytes.length > MAX_LENGTH) {
            logger.warn("Incorrect data: length = " + bytes.length);
            return null;
        }
        if (isLittleEndian) {
            reverse(bytes);
        }
        String result;
        try {
            result = new String(bytes, CP866);
        } catch (UnsupportedEncodingException e) {
            logger.warn(e.getMessage());
            return null;
        }
        return result;
    }
}
