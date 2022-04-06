package ru.pupov.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "name", "price", "quantity", "sum" })
public class TLVItem {
    @JsonProperty("name")
    private String name;
    @JsonProperty("price")
    private long price;
    @JsonProperty("quantity")
    private double quantity;
    @JsonProperty("sum")
    private long sum;

    public TLVItem(String name, long price, double quantity, int sum) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.sum = sum;
    }

    public TLVItem() {
    }

    public String getName() {
        return name;
    }

    public TLVItem setName(String name) {
        this.name = name;
        return this;
    }

    public long getPrice() {
        return price;
    }

    public TLVItem setPrice(long price) {
        this.price = price;
        return this;
    }

    public double getQuantity() {
        return quantity;
    }

    public TLVItem setQuantity(double quantity) {
        this.quantity = quantity;
        return this;
    }

    public long getSum() {
        return sum;
    }

    public TLVItem setSum(long sum) {
        this.sum = sum;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TLVItem)) return false;

        TLVItem tlvItem = (TLVItem) o;

        if (price != tlvItem.price) return false;
        if (Double.compare(tlvItem.quantity, quantity) != 0) return false;
        if (sum != tlvItem.sum) return false;
        return name != null ? name.equals(tlvItem.name) : tlvItem.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (int) (price ^ (price >>> 32));
        temp = Double.doubleToLongBits(quantity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (sum ^ (sum >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "TLVItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", sum=" + sum +
                '}';
    }
}
