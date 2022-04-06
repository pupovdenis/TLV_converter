package ru.pupov.enums;

import ru.pupov.extractor.Extractor;
import ru.pupov.extractor.impl.TLVfvlnExtractor;
import ru.pupov.extractor.impl.TLVstringCP866Extractor;
import ru.pupov.extractor.impl.TLVuint32Extractor;
import ru.pupov.extractor.impl.TLVvlnExtractor;

public enum TLVOrderTypes {

    DATE_TIME(1, "dateTime", new TLVuint32Extractor()),
    ORDER_NUMBER(2, "orderNumber", new TLVvlnExtractor()),
    CUSTOMER_NAME(3, "customerName", new TLVstringCP866Extractor());

    private int code;
    private String name;
    private Extractor extractor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    TLVOrderTypes(int code, String name, Extractor extractor) {
        this.code = code;
        this.name = name;
        this.extractor = extractor;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Extractor getExtractor() {
        return extractor;
    }

    public void setExtractor(Extractor extractor) {
        this.extractor = extractor;
    }

    public static Extractor getExtractorByCode(int code) {
        for (TLVOrderTypes tlvOrderTypes : TLVOrderTypes.values()) {
            if (tlvOrderTypes.code == code) {
                return tlvOrderTypes.getExtractor();
            }
        }
        throw new RuntimeException("Can't find extractor for type code = " + code);
    }
}
