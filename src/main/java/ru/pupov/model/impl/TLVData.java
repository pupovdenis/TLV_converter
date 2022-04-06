package ru.pupov.model.impl;

import ru.pupov.model.Data;

import java.util.Arrays;

public class TLVData implements Data{
    private int type;
    private byte[] value;

    public TLVData() {
    }

    public TLVData(int type, byte[] value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TLVData)) return false;

        TLVData tlvData = (TLVData) o;

        if (type != tlvData.type) return false;
        return Arrays.equals(value, tlvData.value);
    }

    @Override
    public int hashCode() {
        int result = type;
        result = 31 * result + Arrays.hashCode(value);
        return result;
    }

    @Override
    public String toString() {
        return "TLVData{" +
                "type=" + type +
                ", value=" + Arrays.toString(value) +
                '}';
    }
}
