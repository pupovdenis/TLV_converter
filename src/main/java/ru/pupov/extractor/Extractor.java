package ru.pupov.extractor;

public interface Extractor {
    public Object extract(byte[] bytes, boolean isLittleEndian);
}
