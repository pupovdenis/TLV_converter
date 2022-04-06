package ru.pupov.parser.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.pupov.enums.TLVItemTypes;
import ru.pupov.enums.TLVOrderTypes;
import ru.pupov.extractor.Extractor;
import ru.pupov.model.Data;
import ru.pupov.model.TLVItem;
import ru.pupov.model.TLVOrder;
import ru.pupov.model.impl.TLVData;
import ru.pupov.parser.Parser;
import ru.pupov.utils.Utils;

import javax.xml.bind.DatatypeConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TLVParser implements Parser {

    private static final Logger logger = LoggerFactory.getLogger(TLVParser.class);

    @Override
    public TLVOrder parse(byte[] bytes) {
        logger.info("Start parsing bytes: " + DatatypeConverter.printHexBinary(bytes));
        if (bytes.length < 4) {
            logger.warn("Incorrect data: Length < 4");
            return null;
        }
        List<Data> orderDataList = new ArrayList<>();
        getDataFromBytes(bytes, orderDataList);
        List<Data> filteredDataList = Utils.filterDuplicates(orderDataList);
        TLVOrder tlvOrder = getTLVOrder(filteredDataList);
        return tlvOrder;
    }

    private TLVOrder getTLVOrder(List<Data> orderDataList) {
        TLVOrder tlvOrder = new TLVOrder();

        for (Data orderData : orderDataList) {
            logger.info("Start extracting: type = " + orderData.getType() +
                    ", value = " + DatatypeConverter.printHexBinary(orderData.getValue()));
            switch (orderData.getType()) {
                case 1: {
                    Extractor extractor = TLVOrderTypes.getExtractorByCode(orderData.getType());
                    Long extractedValue = (Long) extractor.extract(orderData.getValue(), true);
                    tlvOrder.setDatetime(extractedValue);
                    break;
                }
                case 2: {
                    Extractor extractor = TLVOrderTypes.getExtractorByCode(orderData.getType());
                    Long extractedValue = (Long) extractor.extract(orderData.getValue(), true);
                    tlvOrder.setOrderNumber(extractedValue);
                    break;
                }
                case 3: {
                    Extractor extractor = TLVOrderTypes.getExtractorByCode(orderData.getType());
                    String extractedValue = (String) extractor.extract(orderData.getValue(), false);
                    tlvOrder.setCustomerName(extractedValue);
                    break;
                }
                case 4: {
                    List<Data> itemDataList = new ArrayList<>();
                    getDataFromBytes(orderData.getValue(), itemDataList);
                    List<TLVItem> tlvItemList = getTLVItemList(itemDataList);
                    List<TLVItem> tlvFilteredItems = Utils.filterDuplicates(tlvItemList);
                    tlvOrder.setTlvItems(tlvFilteredItems);
                    break;
                }
                default: {
                    String errorStr = "Was founded unknown data: " + orderData;
                    logger.error(errorStr);
                    throw new RuntimeException(errorStr);
                }
            }
        }
        logger.info("Object after parsing:" + tlvOrder);
        return tlvOrder;
    }

    private List<TLVItem> getTLVItemList(List<Data> itemDataList) {

        List<TLVItem> tlvItemList = new ArrayList<>();
        TLVItem tlvItem = new TLVItem();

        for (Data itemData : itemDataList) {
            Extractor extractor = TLVItemTypes.getExtractorByCode(itemData.getType());
            logger.info("Start extracting: type = " + itemData.getType() +
                    ", value = " + DatatypeConverter.printHexBinary(itemData.getValue()));
            switch (itemData.getType()) {
                case 11: {
                    String extractedValue = (String) extractor.extract(itemData.getValue(), false);
                    tlvItem.setName(extractedValue);
                    break;
                }
                case 12: {
                    Long extractedValue = (Long) extractor.extract(itemData.getValue(), true);
                    tlvItem.setPrice(extractedValue);
                    break;
                }
                case 13: {
                    Double extractedValue = (Double) extractor.extract(itemData.getValue(), false);
                    tlvItem.setQuantity(extractedValue);
                    break;
                }
                case 14: {
                    Long extractedValue = (Long) extractor.extract(itemData.getValue(), true);
                    tlvItem.setSum(extractedValue);
                    break;
                }
                default: {
                    String errorStr = "Was founded unknown data: " + itemData;
                    logger.error(errorStr);
                    throw new RuntimeException(errorStr);
                }
            }
        }
        tlvItemList.add(tlvItem);
        return tlvItemList;
    }

    private void getDataFromBytes(byte[] bytes, List<Data> dataList) {
        if (bytes.length < 4) {
            return;
        }

        int type = ((bytes[1] & 0xFF) << 8) | (bytes[0] & 0xFF);
        int length = ((bytes[3] & 0xFF) << 8) | (bytes[2] & 0xFF);

        if (bytes.length < 4 + length) {
            String warnMsg = String.format("Lost value for type: %02X %02X, with length: %02X %02X",
                    bytes[0], bytes[1], bytes[2], bytes[3]);
            logger.warn(warnMsg);
            return;
        }

        Data data = new TLVData(type, Arrays.copyOfRange(bytes, 4, 4 + length));

        dataList.add(data);

        getDataFromBytes(Arrays.copyOfRange(bytes, 4 + length, bytes.length), dataList);

    }

}
