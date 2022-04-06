package ru.pupov.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.pupov.model.TLVItem;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static sun.security.util.ArrayUtil.reverse;

public class Utils {

    private static final Logger logger = LoggerFactory.getLogger(Utils.class);

    private Utils() {
    }

    public static long getLongFromBinaryArray(byte[] bytes, boolean isLittleEndian) {
        if (bytes.length > 8) {
            String errorStr = "Incorrect data: length = " + bytes.length;
            logger.error(errorStr);
            throw new RuntimeException(errorStr);
        }
        long longValue = 0;
        if (isLittleEndian) {
            reverse(bytes);
        }
        for (byte b : bytes) {
            longValue = (longValue << 8) + (b & 0xFF);
        }
        return longValue;
    }

    public static int getIntFromBinaryArray(byte[] bytes, boolean isLittleEndian) {
        if (bytes.length > 4) {
            String errorStr = "Incorrect data: length = " + bytes.length;
            logger.error(errorStr);
            throw new RuntimeException(errorStr);
        }
        int intValue = 0;
        if (isLittleEndian) {
            reverse(bytes);
        }
        for (byte b : bytes) {
            intValue = (intValue << 8) + (b & 0xFF);
        }
        return intValue;
    }

    public static <T> List<T> filterDuplicates(List<T> list) {
        Set<T> items = new HashSet<>();
        List<T> collect = list.stream()
                .filter(it -> items.add(it))
                .collect(Collectors.toList());
        return collect;

    }
}
