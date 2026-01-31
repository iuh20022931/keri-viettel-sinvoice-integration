package com.invoice.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.invoice.example.domain.dto.InvoiceInputWSDTO;
import com.invoice.example.domain.dto.InvoiceOutputDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;

public class InvoicePXKMilkSampleService {

    private String accessToken;
    protected final Logger log = LogManager.getLogger(InvoicePXKMilkSampleService.class);

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /* API su dung chung thu so server */
    // - Tao moi hoa don
    public void createInvoicePXK() throws Exception {
        InvoiceInputWSDTO.CreateInvoiceWSDTO invoiceWSDTO = genWSBodyInputNewPXK();
        postData("https://api-vinvoice.viettel.vn/services/einvoiceapplication/api/InvoiceAPI/InvoiceWS/createInvoice/"
                + "0100109106-507", this.accessToken, invoiceWSDTO, new TypeReference<Object>() {
                });
    }

    // - Thay the hoa don
    public void createInvoiceReplacePXK() throws Exception {
        InvoiceInputWSDTO.CreateInvoiceWSDTO invoiceWSDTO = genWSBodyInputReplacePXK();
        postData("https://api-vinvoice.viettel.vn/services/einvoiceapplication/api/InvoiceAPI/InvoiceWS/createInvoice/"
                + "0100109106-507", this.accessToken, invoiceWSDTO, new TypeReference<Object>() {
                });
    }

    // - Dieu chinh thong tin
    public void createInvoiceAdjustInfoPXK() throws Exception {
        InvoiceInputWSDTO.CreateInvoiceWSDTO invoiceWSDTO = genWSBodyInputAdjustInfoPXK();
        postData("https://api-vinvoice.viettel.vn/services/einvoiceapplication/api/InvoiceAPI/InvoiceWS/createInvoice/"
                + "0100109106-507", this.accessToken, invoiceWSDTO, new TypeReference<Object>() {
                });
    }

    // - Dieu chinh tien
    public void createInvoiceAdjustMoneyPXK() throws Exception {
        InvoiceInputWSDTO.CreateInvoiceWSDTO invoiceWSDTO = genWSBodyInputAdjustMoneyPXK();
        postData("https://api-vinvoice.viettel.vn/services/einvoiceapplication/api/InvoiceAPI/InvoiceWS/createInvoice/"
                + "0100109106-507", this.accessToken, invoiceWSDTO, new TypeReference<Object>() {
                });
    }

    // HOA DON THAY THE
    private InvoiceInputWSDTO.CreateInvoiceWSDTO genWSBodyInputReplacePXK() {
        InvoiceInputWSDTO.CreateInvoiceWSDTO invoiceWSDTO = new InvoiceInputWSDTO.CreateInvoiceWSDTO();

        // Thong tin chung hoa don
        InvoiceInputWSDTO.GeneralInvoiceInfo generalInvoiceInfo = new InvoiceInputWSDTO.GeneralInvoiceInfo();
        generalInvoiceInfo.setInvoiceType("6");
        generalInvoiceInfo.setTemplateCode("6/1103");
        generalInvoiceInfo.setInvoiceSeries("K24NAF");
        generalInvoiceInfo.setCurrencyCode("VND");
        generalInvoiceInfo.setAdjustmentType("1");
        generalInvoiceInfo.setPaymentStatus(true);
        generalInvoiceInfo.setTransactionUuid(UUID.randomUUID().toString());
        // - Thong tin hóa đơn bị thay thế
        generalInvoiceInfo.setAdjustmentType("3");
        generalInvoiceInfo.setAdjustedNote("Thay thế hóa đơn bị sai");
        generalInvoiceInfo.setOriginalInvoiceId("K25NAF3");
        generalInvoiceInfo.setOriginalInvoiceIssueDate("1736913619000");
        generalInvoiceInfo.setOriginalTemplateCode("6/1103");
        generalInvoiceInfo.setAdditionalReferenceDesc("VĂN BAN THOA THUAN");
        generalInvoiceInfo.setAdditionalReferenceDate(1736818200000l);

        // generalInvoiceInfo.setTypeId(1l);//Nếu phát hành tem vé, truyền id loại vé
        // generalInvoiceInfo.setClassifyId(1l);//Nếu phát hành tem vé, truyền id phân
        // loại vé (nếu có)

        invoiceWSDTO.setGeneralInvoiceInfo(generalInvoiceInfo);

        // Thong tin nguoi mua
        InvoiceInputWSDTO.BuyerInfo buyerInfo = new InvoiceInputWSDTO.BuyerInfo();
        buyerInfo.setBuyerName("Nguyen Van An");
        buyerInfo.setBuyerLegalName("Cong Ty TNHH ABC");
        buyerInfo.setBuyerTaxCode("0100109106-990");
        buyerInfo.setBuyerAddressLine("Duong Le Trong Tan, Ha Dong");
        buyerInfo.setBuyerPhoneNumber("0912345678");
        buyerInfo.setBuyerEmail("abc@gmail.com");
        buyerInfo.setBuyerIdNo("030081002099");
        buyerInfo.setBuyerIdType("1");
        buyerInfo.setBuyerCode("NO_CODE");
        buyerInfo.setBuyerBankName("Ngan Hang TMCP XYZ");
        buyerInfo.setBuyerBankAccount("000193651773658");
        buyerInfo.setBuyerNotGetInvoice(1);
        invoiceWSDTO.setBuyerInfo(buyerInfo);

        // Hinh thuc thanh toan: Truyền theo đúng hình thức của hóa đơn
        InvoiceInputWSDTO.PaymentInfo paymentInfo = new InvoiceInputWSDTO.PaymentInfo();
        paymentInfo.setPaymentMethodName("TM/CK");
        invoiceWSDTO.setPayments(Collections.singletonList(paymentInfo));

        // Thong tin hang hoa: Thêm danh sách hàng hóa tương ứng
        List<InvoiceInputWSDTO.ItemInfo> list = new ArrayList<>();
        InvoiceInputWSDTO.ItemInfo itemInfo = new InvoiceInputWSDTO.ItemInfo();
        itemInfo.setItemCode("HH001");
        itemInfo.setItemName("May tinh");
        itemInfo.setUnitName("Chiec");
        itemInfo.setItemNote("Chi chu hang hoa");
        itemInfo.setUnitPrice(new BigDecimal(15000000));
        itemInfo.setQuantity(new BigDecimal(2));
        itemInfo.setItemTotalAmountWithoutTax(new BigDecimal(30000000));
        itemInfo.setItemTotalAmountWithTax(new BigDecimal(30000000));
        itemInfo.setItemTotalAmountAfterDiscount(new BigDecimal(0));
        itemInfo.setDiscount(new BigDecimal(0));
        itemInfo.setDiscount2(new BigDecimal(0));
        itemInfo.setItemDiscount(new BigDecimal(0));
        itemInfo.setSelection(1);
        list.add(itemInfo);
        invoiceWSDTO.setItemInfo(list);

        // Thong tin bo sung:
        // - Nếu không có thông tin bổ sung thì bỏ qua
        // - Nếu có thì thêm theo các thông tin bổ sung của mẫu hóa đơn
        List<InvoiceInputWSDTO.MetaDataInfo> infoUpdateList = new ArrayList<>();
        InvoiceInputWSDTO.MetaDataInfo info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Lệnh điều động nội bộ");
        info.setKeyTag("economicContractNo");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Tên người vận chuyển");
        info.setKeyTag("transformer");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Phương tiện vận chuyển");
        info.setKeyTag("vehicle");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Hợp đồng số (Hợp đồng vận chuyển)");
        info.setKeyTag("contractNo");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Họ và tên người xuất hàng");
        info.setKeyTag("exporterName");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Xuất tại kho");
        info.setKeyTag("exportAt");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Nhập tại kho");
        info.setKeyTag("importAt");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Về việc");
        info.setKeyTag("commandDes");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Ngày tháng năm lệnh điều động");
        info.setKeyTag("commandDate");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Của");
        info.setKeyTag("cua");
        info.setValueType("text");
        infoUpdateList.add(info);
        invoiceWSDTO.setMetadata(infoUpdateList);

        // Tong tien hoa don
        InvoiceInputWSDTO.SummarizeInfo sum = new InvoiceInputWSDTO.SummarizeInfo();
        sum.setDiscountAmount(new BigDecimal(0));
        sum.setTotalAmountWithoutTax(new BigDecimal(30000000));
        sum.setTotalAmountWithTax(new BigDecimal(30000000));
        sum.setTotalAmountAfterDiscount(new BigDecimal(30000000));
        invoiceWSDTO.setSummarizeInfo(sum);

        return invoiceWSDTO;
    }

    // HÓA ĐƠN ĐIỀU CHỈNH THÔNG TIN
    private InvoiceInputWSDTO.CreateInvoiceWSDTO genWSBodyInputAdjustInfoPXK() {
        InvoiceInputWSDTO.CreateInvoiceWSDTO invoiceWSDTO = new InvoiceInputWSDTO.CreateInvoiceWSDTO();

        // Thong tin chung hoa don
        InvoiceInputWSDTO.GeneralInvoiceInfo generalInvoiceInfo = new InvoiceInputWSDTO.GeneralInvoiceInfo();
        generalInvoiceInfo.setInvoiceType("6");
        generalInvoiceInfo.setTemplateCode("6/1103");
        generalInvoiceInfo.setInvoiceSeries("K24NAF");
        generalInvoiceInfo.setCurrencyCode("VND");
        generalInvoiceInfo.setAdjustmentType("1");
        generalInvoiceInfo.setPaymentStatus(true);
        generalInvoiceInfo.setTransactionUuid(UUID.randomUUID().toString());
        // - Thong tin hóa đơn bị điều chỉnh thông tin
        generalInvoiceInfo.setAdjustmentType("5");
        generalInvoiceInfo.setAdjustmentInvoiceType("2");
        generalInvoiceInfo.setAdjustedNote("Điều chỉnh thông tin hóa đơn do bi nham");
        generalInvoiceInfo.setOriginalInvoiceId("K25NAF4");
        generalInvoiceInfo.setOriginalInvoiceIssueDate("1736926518000");
        generalInvoiceInfo.setOriginalTemplateCode("6/1103");
        generalInvoiceInfo.setAdditionalReferenceDesc("VĂN BAN THOA THUAN");
        generalInvoiceInfo.setAdditionalReferenceDate(1605682860000l);

        invoiceWSDTO.setGeneralInvoiceInfo(generalInvoiceInfo);

        // Thong tin nguoi mua
        InvoiceInputWSDTO.BuyerInfo buyerInfo = new InvoiceInputWSDTO.BuyerInfo();
        buyerInfo.setBuyerName("Nguyen Van An");
        buyerInfo.setBuyerLegalName("Cong Ty TNHH ABC");
        buyerInfo.setBuyerTaxCode("0100109106-990");
        buyerInfo.setBuyerAddressLine("Duong Le Trong Tan, Ha Dong");
        buyerInfo.setBuyerPhoneNumber("0912345678");
        buyerInfo.setBuyerEmail("abc@gmail.com");
        buyerInfo.setBuyerIdNo("030081002099");
        buyerInfo.setBuyerIdType("1");
        buyerInfo.setBuyerCode("NO_CODE");
        buyerInfo.setBuyerBankName("Ngan Hang TMCP XYZ");
        buyerInfo.setBuyerBankAccount("000193651773658");
        buyerInfo.setBuyerNotGetInvoice(1);
        invoiceWSDTO.setBuyerInfo(buyerInfo);

        // Hinh thuc thanh toan: Truyền theo đúng hình thức của hóa đơn
        InvoiceInputWSDTO.PaymentInfo paymentInfo = new InvoiceInputWSDTO.PaymentInfo();
        paymentInfo.setPaymentMethodName("TM/CK");
        invoiceWSDTO.setPayments(Collections.singletonList(paymentInfo));

        // Thong tin bo sung:
        // - Nếu không có thông tin bổ sung thì bỏ qua
        // - Nếu có thì thêm theo các thông tin bổ sung của mẫu hóa đơn
        List<InvoiceInputWSDTO.MetaDataInfo> infoUpdateList = new ArrayList<>();
        InvoiceInputWSDTO.MetaDataInfo info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Lệnh điều động nội bộ");
        info.setKeyTag("economicContractNo");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Tên người vận chuyển");
        info.setKeyTag("transformer");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Phương tiện vận chuyển");
        info.setKeyTag("vehicle");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Hợp đồng số (Hợp đồng vận chuyển)");
        info.setKeyTag("contractNo");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Họ và tên người xuất hàng");
        info.setKeyTag("exporterName");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Xuất tại kho");
        info.setKeyTag("exportAt");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Nhập tại kho");
        info.setKeyTag("importAt");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Về việc");
        info.setKeyTag("commandDes");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Ngày tháng năm lệnh điều động");
        info.setKeyTag("commandDate");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Của");
        info.setKeyTag("cua");
        info.setValueType("text");
        infoUpdateList.add(info);
        invoiceWSDTO.setMetadata(infoUpdateList);

        return invoiceWSDTO;
    }

    // HOA DON THAY THE
    private InvoiceInputWSDTO.CreateInvoiceWSDTO genWSBodyInputAdjustMoneyPXK() {
        InvoiceInputWSDTO.CreateInvoiceWSDTO invoiceWSDTO = new InvoiceInputWSDTO.CreateInvoiceWSDTO();

        // Thong tin chung hoa don
        InvoiceInputWSDTO.GeneralInvoiceInfo generalInvoiceInfo = new InvoiceInputWSDTO.GeneralInvoiceInfo();
        generalInvoiceInfo.setInvoiceType("6");
        generalInvoiceInfo.setTemplateCode("6/1103");
        generalInvoiceInfo.setInvoiceSeries("K24NAF");
        generalInvoiceInfo.setCurrencyCode("VND");
        generalInvoiceInfo.setAdjustmentType("1");
        generalInvoiceInfo.setPaymentStatus(true);
        generalInvoiceInfo.setTransactionUuid(UUID.randomUUID().toString());
        // - Thong tin hóa đơn bị điều chỉnh tiền
        generalInvoiceInfo.setAdjustmentType("5");
        generalInvoiceInfo.setAdjustmentInvoiceType("1");
        generalInvoiceInfo.setAdjustedNote("Điều chỉnh tiền do cong nham");
        generalInvoiceInfo.setOriginalInvoiceId("K25NAF6");
        generalInvoiceInfo.setOriginalInvoiceIssueDate("1736926518000");
        generalInvoiceInfo.setOriginalTemplateCode("6/1103");
        generalInvoiceInfo.setAdditionalReferenceDesc("VĂN BAN THOA THUAN");
        generalInvoiceInfo.setAdditionalReferenceDate(1605682860000l);

        // generalInvoiceInfo.setTypeId(1l);//Nếu phát hành tem vé, truyền id loại vé
        // generalInvoiceInfo.setClassifyId(1l);//Nếu phát hành tem vé, truyền id phân
        // loại vé (nếu có)

        invoiceWSDTO.setGeneralInvoiceInfo(generalInvoiceInfo);

        // Thong tin nguoi mua
        InvoiceInputWSDTO.BuyerInfo buyerInfo = new InvoiceInputWSDTO.BuyerInfo();
        buyerInfo.setBuyerName("Nguyen Van An");
        buyerInfo.setBuyerLegalName("Cong Ty TNHH ABC");
        buyerInfo.setBuyerTaxCode("0100109106-990");
        buyerInfo.setBuyerAddressLine("Duong Le Trong Tan, Ha Dong");
        buyerInfo.setBuyerPhoneNumber("0912345678");
        buyerInfo.setBuyerEmail("abc@gmail.com");
        buyerInfo.setBuyerIdNo("030081002099");
        buyerInfo.setBuyerIdType("1");
        buyerInfo.setBuyerCode("NO_CODE");
        buyerInfo.setBuyerBankName("Ngan Hang TMCP XYZ");
        buyerInfo.setBuyerBankAccount("000193651773658");
        buyerInfo.setBuyerNotGetInvoice(1);
        invoiceWSDTO.setBuyerInfo(buyerInfo);

        // Hinh thuc thanh toan: Truyền theo đúng hình thức của hóa đơn
        InvoiceInputWSDTO.PaymentInfo paymentInfo = new InvoiceInputWSDTO.PaymentInfo();
        paymentInfo.setPaymentMethodName("TM/CK");
        invoiceWSDTO.setPayments(Collections.singletonList(paymentInfo));

        // Thong tin hang hoa: Thêm danh sách hàng hóa tương ứng
        List<InvoiceInputWSDTO.ItemInfo> list = new ArrayList<>();
        InvoiceInputWSDTO.ItemInfo itemInfo = new InvoiceInputWSDTO.ItemInfo();
        itemInfo.setItemCode("HH001");
        itemInfo.setItemName("May tinh");
        itemInfo.setUnitName("Chiec");
        itemInfo.setItemNote("Chi chu hang hoa");
        itemInfo.setUnitPrice(new BigDecimal(15000000));
        itemInfo.setQuantity(new BigDecimal(2));
        itemInfo.setItemTotalAmountWithoutTax(new BigDecimal(30000000));
        itemInfo.setItemTotalAmountWithTax(new BigDecimal(30000000));
        itemInfo.setItemTotalAmountAfterDiscount(new BigDecimal(0));
        itemInfo.setDiscount(new BigDecimal(0));
        itemInfo.setDiscount2(new BigDecimal(0));
        itemInfo.setItemDiscount(new BigDecimal(0));
        itemInfo.setIsIncreaseItem(true);
        itemInfo.setSelection(1);
        list.add(itemInfo);
        invoiceWSDTO.setItemInfo(list);

        // Thong tin bo sung:
        // - Nếu không có thông tin bổ sung thì bỏ qua
        // - Nếu có thì thêm theo các thông tin bổ sung của mẫu hóa đơn
        List<InvoiceInputWSDTO.MetaDataInfo> infoUpdateList = new ArrayList<>();
        InvoiceInputWSDTO.MetaDataInfo info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Lệnh điều động nội bộ");
        info.setKeyTag("economicContractNo");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Tên người vận chuyển");
        info.setKeyTag("transformer");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Phương tiện vận chuyển");
        info.setKeyTag("vehicle");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Hợp đồng số (Hợp đồng vận chuyển)");
        info.setKeyTag("contractNo");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Họ và tên người xuất hàng");
        info.setKeyTag("exporterName");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Xuất tại kho");
        info.setKeyTag("exportAt");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Nhập tại kho");
        info.setKeyTag("importAt");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Về việc");
        info.setKeyTag("commandDes");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Ngày tháng năm lệnh điều động");
        info.setKeyTag("commandDate");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Của");
        info.setKeyTag("cua");
        info.setValueType("text");
        infoUpdateList.add(info);
        invoiceWSDTO.setMetadata(infoUpdateList);

        // Tong tien hoa don
        InvoiceInputWSDTO.SummarizeInfo sum = new InvoiceInputWSDTO.SummarizeInfo();
        sum.setDiscountAmount(new BigDecimal(0));
        sum.setTotalAmountWithoutTax(new BigDecimal(30000000));
        sum.setTotalAmountWithTax(new BigDecimal(30000000));
        sum.setTotalAmountAfterDiscount(new BigDecimal(30000000));
        invoiceWSDTO.setSummarizeInfo(sum);

        return invoiceWSDTO;
    }

    // HOA DON MOI
    private InvoiceInputWSDTO.CreateInvoiceWSDTO genWSBodyInputNewPXK() {
        InvoiceInputWSDTO.CreateInvoiceWSDTO invoiceWSDTO = new InvoiceInputWSDTO.CreateInvoiceWSDTO();

        // Thong tin chung hoa don
        InvoiceInputWSDTO.GeneralInvoiceInfo generalInvoiceInfo = new InvoiceInputWSDTO.GeneralInvoiceInfo();
        generalInvoiceInfo.setInvoiceType("6");
        generalInvoiceInfo.setTemplateCode("6/1103");
        generalInvoiceInfo.setInvoiceSeries("K26NAF");
        generalInvoiceInfo.setCurrencyCode("VND");
        generalInvoiceInfo.setAdjustmentType("1");
        generalInvoiceInfo.setPaymentStatus(true);
        generalInvoiceInfo.setTransactionUuid(UUID.randomUUID().toString());
        invoiceWSDTO.setGeneralInvoiceInfo(generalInvoiceInfo);

        // Thong tin nguoi mua
        InvoiceInputWSDTO.BuyerInfo buyerInfo = new InvoiceInputWSDTO.BuyerInfo();
        buyerInfo.setBuyerName("Nguyen Van An");
        buyerInfo.setBuyerLegalName("Cong Ty TNHH ABC");
        buyerInfo.setBuyerTaxCode("0100109106-990");
        buyerInfo.setBuyerAddressLine("Duong Le Trong Tan, Ha Dong");
        buyerInfo.setBuyerPhoneNumber("0912345678");
        buyerInfo.setBuyerEmail("abc@gmail.com");
        buyerInfo.setBuyerIdNo("030081002099");
        buyerInfo.setBuyerIdType("1");
        buyerInfo.setBuyerCode("NO_CODE");
        buyerInfo.setBuyerBankName("Ngan Hang TMCP XYZ");
        buyerInfo.setBuyerBankAccount("000193651773658");
        buyerInfo.setBuyerNotGetInvoice(1);
        invoiceWSDTO.setBuyerInfo(buyerInfo);

        // Hinh thuc thanh toan: Truyền giá trị theo đúng hình thức của hóa đơn
        InvoiceInputWSDTO.PaymentInfo paymentInfo = new InvoiceInputWSDTO.PaymentInfo();
        paymentInfo.setPaymentMethodName("TM/CK");
        invoiceWSDTO.setPayments(Collections.singletonList(paymentInfo));

        // Thong tin hang hoa: Thêm danh sách hàng hóa tương ứng
        List<InvoiceInputWSDTO.ItemInfo> list = new ArrayList<>();
        InvoiceInputWSDTO.ItemInfo itemInfo = new InvoiceInputWSDTO.ItemInfo();
        itemInfo.setItemCode("HH001");
        itemInfo.setItemName("May tinh");
        itemInfo.setUnitName("Chiec");
        itemInfo.setItemNote("Chi chu hang hoa");
        itemInfo.setUnitPrice(new BigDecimal(15000000));
        itemInfo.setQuantity(new BigDecimal(2));
        itemInfo.setItemTotalAmountWithoutTax(new BigDecimal(30000000));
        itemInfo.setItemTotalAmountWithTax(new BigDecimal(30000000));
        itemInfo.setItemTotalAmountAfterDiscount(new BigDecimal(0));
        itemInfo.setDiscount(new BigDecimal(0));
        itemInfo.setDiscount2(new BigDecimal(0));
        itemInfo.setItemDiscount(new BigDecimal(0));
        itemInfo.setSelection(1);
        list.add(itemInfo);
        invoiceWSDTO.setItemInfo(list);

        // Thong tin bo sung:
        // - Nếu không có thông tin bổ sung thì bỏ qua
        // - Nếu có thì thêm theo các thông tin bổ sung của mẫu hóa đơn
        List<InvoiceInputWSDTO.MetaDataInfo> infoUpdateList = new ArrayList<>();
        InvoiceInputWSDTO.MetaDataInfo info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Lệnh điều động nội bộ");
        info.setKeyTag("economicContractNo");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Tên người vận chuyển");
        info.setKeyTag("transformer");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Phương tiện vận chuyển");
        info.setKeyTag("vehicle");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Hợp đồng số (Hợp đồng vận chuyển)");
        info.setKeyTag("contractNo");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Họ và tên người xuất hàng");
        info.setKeyTag("exporterName");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Xuất tại kho");
        info.setKeyTag("exportAt");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Nhập tại kho");
        info.setKeyTag("importAt");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Về việc");
        info.setKeyTag("commandDes");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Ngày tháng năm lệnh điều động");
        info.setKeyTag("commandDate");
        info.setValueType("text");
        infoUpdateList.add(info);
        info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Của");
        info.setKeyTag("cua");
        info.setValueType("text");
        infoUpdateList.add(info);
        invoiceWSDTO.setMetadata(infoUpdateList);

        // Tong tien hoa don
        InvoiceInputWSDTO.SummarizeInfo sum = new InvoiceInputWSDTO.SummarizeInfo();
        sum.setDiscountAmount(new BigDecimal(0));
        sum.setTotalAmountWithoutTax(new BigDecimal(30000000));
        sum.setTotalAmountWithTax(new BigDecimal(30000000));
        sum.setTotalAmountAfterDiscount(new BigDecimal(30000000));
        invoiceWSDTO.setSummarizeInfo(sum);

        return invoiceWSDTO;
    }

    private HttpEntity<Object> generatePostRequest(String token, Object request) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
        if (token != null) {
            headers.add("Authorization", "Bearer " + token);
        }
        return new HttpEntity<>(request, headers);
    }

    private Object postData(String url, String token, Object bodyObject, TypeReference<?> valueTypeRef)
            throws Exception {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Object> request = generatePostRequest(token, bodyObject);
            ResponseEntity<?> response = restTemplate.postForEntity(url, request, Object.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                Gson gson = new GsonBuilder().create();
                String json = gson.toJson(response.getBody());
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
                JavaTimeModule module = new JavaTimeModule();
                mapper.registerModule(module);
                return mapper.readValue(json, valueTypeRef);
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.err.println("Lỗi khi gọi API (postData): " + url);
            ex.printStackTrace();
            log.error(ex.getMessage(), ex);
            if (ex.getMessage() != null && !"".equals(ex.getMessage())
                    && (ex.getMessage().toLowerCase().contains("No route to host: connect".toLowerCase())
                            || ex.getMessage().toLowerCase().contains("I/O error".toLowerCase()))) {
                throw ex;
            }
            if (ex instanceof HttpClientErrorException) {
                Gson gson = new GsonBuilder().create();
                String json = ((HttpClientErrorException) ex).getResponseBodyAsString().replace("\n", "");
                ObjectMapper mapper = new ObjectMapper();
                try {
                    Map map = mapper.readValue(json, Map.class);
                    String data = gson.toJson(map.get("data"));
                    System.err.println("API Error Data: " + data);
                    return null;
                } catch (Exception exception) {
                    log.error(exception.getMessage(), exception);
                    return null;
                }
            }
            return null;
        }
    }

    private HttpEntity<Object> generateXFormPostRequest(String token, Object request) {
        HttpHeaders headers = new HttpHeaders();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        JavaTimeModule module = new JavaTimeModule();
        objectMapper.registerModule(module);
        MultiValueMap valueMap = new LinkedMultiValueMap();
        Map<String, Object> fieldMap = objectMapper.convertValue(request, new TypeReference<Map<String, Object>>() {
        });
        valueMap.setAll(fieldMap);
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        headers.add("Accept", "*/*");
        if (token != null) {
            headers.add("Authorization", "Bearer " + token);
        }
        return new HttpEntity<>(valueMap, headers);
    }

    private Object postXFormData(String url, String token, Object bodyObject, TypeReference<?> valueTypeRef)
            throws Exception {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Object> request = generateXFormPostRequest(token, bodyObject);
            ResponseEntity<?> response = restTemplate.postForEntity(url, request, Object.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                Gson gson = new GsonBuilder().create();
                String json = gson.toJson(response.getBody());
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
                JavaTimeModule module = new JavaTimeModule();
                mapper.registerModule(module);
                return mapper.readValue(json, valueTypeRef);
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.err.println("Lỗi khi gọi API (postXFormData): " + url);
            ex.printStackTrace();
            log.error(ex.getMessage(), ex);
            if (ex.getMessage() != null && !"".equals(ex.getMessage())
                    && (ex.getMessage().toLowerCase().contains("No route to host: connect".toLowerCase())
                            || ex.getMessage().toLowerCase().contains("I/O error".toLowerCase()))) {
                throw ex;
            }
            if (ex instanceof HttpClientErrorException) {
                Gson gson = new GsonBuilder().create();
                String json = ((HttpClientErrorException) ex).getResponseBodyAsString().replace("\n", "");
                ObjectMapper mapper = new ObjectMapper();
                try {
                    Map map = mapper.readValue(json, Map.class);
                    String data = gson.toJson(map.get("data"));
                    System.err.println("API Error Data: " + data);
                    return null;
                } catch (Exception exception) {
                    log.error(exception.getMessage(), exception);
                    return null;
                }
            }
            return null;
        }
    }
}
