package ru.pupov.enums;

import ru.pupov.extractor.Extractor;
import ru.pupov.extractor.impl.TLVfvlnExtractor;
import ru.pupov.extractor.impl.TLVstringCP866Extractor;
import ru.pupov.extractor.impl.TLVuint32Extractor;
import ru.pupov.extractor.impl.TLVvlnExtractor;

public enum TLVItemTypes {

    NAME(11, "name", new TLVstringCP866Extractor()),
    PRICE(12, "price", new TLVvlnExtractor()),
    QUANTITY(13, "quantity", new TLVfvlnExtractor()),
    SUM(14, "sum", new TLVvlnExtractor());

    private int code;
    private String name;
    private Extractor extractor;

    TLVItemTypes(int code, String name, Extractor extractor) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Extractor getExtractor() {
        return extractor;
    }

    public void setExtractor(Extractor extractor) {
        this.extractor = extractor;
    }

    public static Extractor getExtractorByCode(int code) {
        for (TLVItemTypes tlvOrderTypes : TLVItemTypes.values()) {
            if (tlvOrderTypes.code == code) {
                return tlvOrderTypes.getExtractor();
            }
        }
        throw new RuntimeException("Can't find extractor for type code = " + code);
    }
}
