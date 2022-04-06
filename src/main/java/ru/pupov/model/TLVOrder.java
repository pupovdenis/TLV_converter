package ru.pupov.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@JsonPropertyOrder({ "datetime", "orderNumber", "customerName", "items" })
public class TLVOrder {

    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

//    @JsonProperty("datetime")
    @JsonIgnore
    private long datetime;
    @JsonProperty("orderNumber")
    private long orderNumber;
    @JsonProperty("customerName")
    private String customerName;
    @JsonProperty("items")
    private List<TLVItem> tlvItems;

    public TLVOrder(long datetime, int orderNumber, String customerName, List<TLVItem> tlvItems) {
        this.datetime = datetime;
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.tlvItems = tlvItems;
    }

    public TLVOrder() {
    }


//    @JsonIgnore
    @JsonProperty("datetime")
    public String getDatetimeString() {
        SimpleDateFormat formater = new SimpleDateFormat(DATE_FORMAT);
        Date date = new Date(datetime);
        return formater.format(date);
    }

    public long getDatetime() {
        return datetime;
    }

    public TLVOrder setDatetime(long datetime) {
        this.datetime = datetime;
        return this;
    }

    public long getOrderNumber() {
        return orderNumber;
    }

    public TLVOrder setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public TLVOrder setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public List<TLVItem> getTlvItems() {
        return tlvItems;
    }

    public TLVOrder setTlvItems(List<TLVItem> tlvItems) {
        this.tlvItems = tlvItems;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TLVOrder)) return false;

        TLVOrder tlvOrder = (TLVOrder) o;

        if (datetime != tlvOrder.datetime) return false;
        if (orderNumber != tlvOrder.orderNumber) return false;
        if (customerName != null ? !customerName.equals(tlvOrder.customerName) : tlvOrder.customerName != null)
            return false;
        return tlvItems != null ? tlvItems.equals(tlvOrder.tlvItems) : tlvOrder.tlvItems == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (datetime ^ (datetime >>> 32));
        result = 31 * result + (int) (orderNumber ^ (orderNumber >>> 32));
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        result = 31 * result + (tlvItems != null ? tlvItems.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TLVOrder{" +
                "datetime=" + datetime +
                ", orderNumber=" + orderNumber +
                ", customerName='" + customerName + '\'' +
                ", tlvItems=" + tlvItems +
                '}';
    }
}
