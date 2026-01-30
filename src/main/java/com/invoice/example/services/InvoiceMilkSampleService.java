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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;

public class InvoiceMilkSampleService {

    private String accessToken;
    protected final Logger log = LogManager.getLogger(InvoiceMilkSampleService.class);

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String login(String username, String password) throws Exception {
        Map<String, String> loginParams = new HashMap<>();
        loginParams.put("username", username);
        loginParams.put("password", password);

        String loginUrl = "https://vinvoice.viettel.vn/api/services/einvoiceuaa/api/authenticate";

        LinkedHashMap response = (LinkedHashMap) postData(loginUrl, null, loginParams,
                new TypeReference<LinkedHashMap>() {
                });

        if (response != null) {
            // API này trả về token trong key "id_token"
            if (response.get("id_token") != null) {
                this.accessToken = (String) response.get("id_token");
                return this.accessToken;
            } else if (response.get("access_token") != null) {
                this.accessToken = (String) response.get("access_token");
                return this.accessToken;
            }
        }
        return null;
    }

    /* API tra cứu hóa đơn theo transaction uuid */
    public void searchInvoiceByTransactionUuid() throws Exception {
        InvoiceInputWSDTO.SearchByTransUUIDDTO searchByTransUUIDDTO = new InvoiceInputWSDTO.SearchByTransUUIDDTO();
        searchByTransUUIDDTO.setSupplierTaxCode("0100109106-504");
        searchByTransUUIDDTO.setTransactionUuid("4849decf-02a4-435b-8949-3b89b10167df");
        LinkedHashMap object = (LinkedHashMap) postXFormData(
                "https://vinvoice.viettel.vn/api/services/einvoiceapplication/api/InvoiceAPI/InvoiceWS/searchInvoiceByTransactionUuid",
                this.accessToken, searchByTransUUIDDTO, new TypeReference<LinkedHashMap>() {
                });
        if (object != null && object.containsKey("result")) {
            Gson gson = new GsonBuilder().create();
            String json = gson.toJson(object.get("result"));
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            JavaTimeModule module = new JavaTimeModule();
            objectMapper.registerModule(module);
            List<InvoiceOutputDTO.SearchUUIDInvoiceResult> fieldMap = objectMapper.readValue(json,
                    new TypeReference<List<InvoiceOutputDTO.SearchUUIDInvoiceResult>>() {
                    });
            if (fieldMap != null && !fieldMap.isEmpty()) {
                System.out.println("Invoice No: " + fieldMap.get(0).getInvoiceNo());
            } else {
                System.out.println("Khong tim thay hoa don voi UUID nay.");
            }
        }
    }

    /* API tra cứu danh sách hóa đơn theo MST Khách hàng (Nhiệm vụ 2) */
    public void searchInvoiceByCustomerTaxCode(String buyerTaxCode, String startDate, String endDate) throws Exception {
        InvoiceInputWSDTO.GetInvoiceInput input = new InvoiceInputWSDTO.GetInvoiceInput();
        input.setBuyerTaxCode(buyerTaxCode);
        input.setStartDate(startDate); // Format: dd/MM/yyyy
        input.setEndDate(endDate); // Format: dd/MM/yyyy
        input.setRowPerPage(10);
        input.setPageNum(0);

        // URL API lay danh sach hoa don (Endpoint thuong dung cua Viettel)
        String url = "https://vinvoice.viettel.vn/api/services/einvoiceapplication/api/InvoiceAPI/InvoiceWS/getInvoices/"
                + "0100109106-504";

        InvoiceOutputDTO.SearchInvoiceResp response = (InvoiceOutputDTO.SearchInvoiceResp) postData(url,
                this.accessToken, input, new TypeReference<InvoiceOutputDTO.SearchInvoiceResp>() {
                });

        if (response != null && response.getInvoices() != null) {
            System.out.println("Tim thay " + response.getTotalRows() + " hoa don cho MST: " + buyerTaxCode);
            for (InvoiceOutputDTO.invoicesOutput inv : response.getInvoices()) {
                System.out.println(" - So HD: " + inv.getInvoiceNo() + " | Ngay: " + inv.getIssueDateStr()
                        + " | Tong tien: " + inv.getTotal());
            }
        } else {
            System.out.println("Khong tim thay hoa don nao hoac co loi xay ra.");
        }
    }

    /* API Huy hoa don */
    public void cancelInvoice() throws Exception {
        InvoiceInputWSDTO.CancelTransactionWSDTO cancelTransactionWSDTO = new InvoiceInputWSDTO.CancelTransactionWSDTO();
        cancelTransactionWSDTO.setSupplierTaxCode("0100109106-504");
        cancelTransactionWSDTO.setInvoiceNo("K25TII20");
        cancelTransactionWSDTO.setTemplateCode("1/0230");
        cancelTransactionWSDTO.setStrIssueDate(1736818200000l);
        cancelTransactionWSDTO.setAdditionalReferenceDesc("TEN VAN BAN THOA THUAN");
        cancelTransactionWSDTO.setAdditionalReferenceDate(1587797116000l);
        cancelTransactionWSDTO.setReasonDelete("Ly do xoa bo");
        postXFormData(
                "https://vinvoice.viettel.vn/api/services/einvoiceapplication/api/InvoiceAPI/InvoiceWS/cancelTransactionInvoice",
                this.accessToken, cancelTransactionWSDTO, new TypeReference<Object>() {
                });
    }

    /* API su dung chung thu so server */
    // - Tao moi hoa don
    public void createInvoiceGTGT() throws Exception {
        InvoiceInputWSDTO.CreateInvoiceWSDTO invoiceWSDTO = genWSBodyInputNewGTGT();
        postData("https://vinvoice.viettel.vn/api/services/einvoiceapplication/api/InvoiceAPI/InvoiceWS/createInvoice/"
                + "0100109106-504", this.accessToken, invoiceWSDTO, new TypeReference<Object>() {
                });
    }

    // - Thay the hoa don
    public void createInvoiceReplaceGTGT() throws Exception {
        InvoiceInputWSDTO.CreateInvoiceWSDTO invoiceWSDTO = genWSBodyInputReplaceGTGT();
        postData("https://vinvoice.viettel.vn/api/services/einvoiceapplication/api/InvoiceAPI/InvoiceWS/createInvoice/"
                + "0100109106-504", this.accessToken, invoiceWSDTO, new TypeReference<Object>() {
                });
    }

    // - Dieu chinh thong tin
    public void createInvoiceAdjustInfoGTGT() throws Exception {
        InvoiceInputWSDTO.CreateInvoiceWSDTO invoiceWSDTO = genWSBodyInputAdjustInfoGTGT();
        postData("https://vinvoice.viettel.vn/api/services/einvoiceapplication/api/InvoiceAPI/InvoiceWS/createInvoice/"
                + "0100109106-504", this.accessToken, invoiceWSDTO, new TypeReference<Object>() {
                });
    }

    // - Dieu chinh tien
    public void createInvoiceAdjustMoneyGTGT() throws Exception {
        InvoiceInputWSDTO.CreateInvoiceWSDTO invoiceWSDTO = genWSBodyInputAdjustMoneyGTGT();
        postData("https://vinvoice.viettel.vn/api/services/einvoiceapplication/api/InvoiceAPI/InvoiceWS/createInvoice/"
                + "0100109106-504", this.accessToken, invoiceWSDTO, new TypeReference<Object>() {
                });
    }

    // HOA DON THAY THE
    private InvoiceInputWSDTO.CreateInvoiceWSDTO genWSBodyInputReplaceGTGT() {
        InvoiceInputWSDTO.CreateInvoiceWSDTO invoiceWSDTO = new InvoiceInputWSDTO.CreateInvoiceWSDTO();

        // Thong tin chung hoa don
        InvoiceInputWSDTO.GeneralInvoiceInfo generalInvoiceInfo = new InvoiceInputWSDTO.GeneralInvoiceInfo();
        generalInvoiceInfo.setInvoiceType("1");
        generalInvoiceInfo.setTemplateCode("1/0173");
        generalInvoiceInfo.setInvoiceSeries("K24TJS");
        generalInvoiceInfo.setCurrencyCode("VND");
        generalInvoiceInfo.setAdjustmentType("1");
        generalInvoiceInfo.setPaymentStatus(true);
        generalInvoiceInfo.setTransactionUuid(UUID.randomUUID().toString());
        // - Thong tin hóa đơn bị thay thế
        generalInvoiceInfo.setAdjustmentType("3");
        generalInvoiceInfo.setAdjustedNote("Thay thế hóa đơn bị sai");
        generalInvoiceInfo.setOriginalInvoiceId("K25TJS1");
        generalInvoiceInfo.setOriginalInvoiceIssueDate("1736913619000");
        generalInvoiceInfo.setOriginalTemplateCode("1/0173");
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
        itemInfo.setTaxPercentage(new BigDecimal(10));
        itemInfo.setTaxAmount(new BigDecimal(3000000));
        itemInfo.setItemTotalAmountWithTax(new BigDecimal(33000000));
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
        info.setStringValue("Thong tin bo sung chuoi");
        info.setKeyTag("invoiceNote");
        info.setValueType("text");
        infoUpdateList.add(info);
        invoiceWSDTO.setMetadata(infoUpdateList);

        // Tong tien hoa don
        InvoiceInputWSDTO.SummarizeInfo sum = new InvoiceInputWSDTO.SummarizeInfo();
        sum.setDiscountAmount(new BigDecimal(0));
        sum.setTotalAmountWithoutTax(new BigDecimal(30000000));
        sum.setTotalTaxAmount(new BigDecimal(3000000));
        sum.setTotalAmountWithTax(new BigDecimal(33000000));
        sum.setTotalAmountAfterDiscount(new BigDecimal(30000000));
        invoiceWSDTO.setSummarizeInfo(sum);

        return invoiceWSDTO;
    }

    // HÓA ĐƠN ĐIỀU CHỈNH THÔNG TIN
    private InvoiceInputWSDTO.CreateInvoiceWSDTO genWSBodyInputAdjustInfoGTGT() {
        InvoiceInputWSDTO.CreateInvoiceWSDTO invoiceWSDTO = new InvoiceInputWSDTO.CreateInvoiceWSDTO();

        // Thong tin chung hoa don
        InvoiceInputWSDTO.GeneralInvoiceInfo generalInvoiceInfo = new InvoiceInputWSDTO.GeneralInvoiceInfo();
        generalInvoiceInfo.setInvoiceType("1");
        generalInvoiceInfo.setTemplateCode("1/0173");
        generalInvoiceInfo.setInvoiceSeries("K24TJS");
        generalInvoiceInfo.setCurrencyCode("VND");
        generalInvoiceInfo.setAdjustmentType("1");
        generalInvoiceInfo.setPaymentStatus(true);
        generalInvoiceInfo.setTransactionUuid(UUID.randomUUID().toString());
        // - Thong tin hóa đơn bị điều chỉnh thông tin
        generalInvoiceInfo.setAdjustmentType("5");
        generalInvoiceInfo.setAdjustmentInvoiceType("2");
        generalInvoiceInfo.setAdjustedNote("Điều chỉnh thông tin hóa đơn do bi nham");
        generalInvoiceInfo.setOriginalInvoiceId("K24TJS1");
        generalInvoiceInfo.setOriginalInvoiceIssueDate("1736818200000");
        generalInvoiceInfo.setOriginalTemplateCode("1/0173");
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

        // Thong tin bo sung:
        // - Nếu không có thông tin bổ sung thì bỏ qua
        // - Nếu có thì thêm theo các thông tin bổ sung của mẫu hóa đơn
        List<InvoiceInputWSDTO.MetaDataInfo> infoUpdateList = new ArrayList<>();
        InvoiceInputWSDTO.MetaDataInfo info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Thong tin bo sung chuoi");
        info.setKeyTag("invoiceNote");
        info.setValueType("text");
        infoUpdateList.add(info);
        invoiceWSDTO.setMetadata(infoUpdateList);

        return invoiceWSDTO;
    }

    // HOA DON THAY THE
    private InvoiceInputWSDTO.CreateInvoiceWSDTO genWSBodyInputAdjustMoneyGTGT() {
        InvoiceInputWSDTO.CreateInvoiceWSDTO invoiceWSDTO = new InvoiceInputWSDTO.CreateInvoiceWSDTO();

        // Thong tin chung hoa don
        InvoiceInputWSDTO.GeneralInvoiceInfo generalInvoiceInfo = new InvoiceInputWSDTO.GeneralInvoiceInfo();
        generalInvoiceInfo.setInvoiceType("1");
        generalInvoiceInfo.setTemplateCode("1/0173");
        generalInvoiceInfo.setInvoiceSeries("K24TJS");
        generalInvoiceInfo.setCurrencyCode("VND");
        generalInvoiceInfo.setAdjustmentType("1");
        generalInvoiceInfo.setPaymentStatus(true);
        generalInvoiceInfo.setTransactionUuid(UUID.randomUUID().toString());
        // - Thong tin hóa đơn bị điều chỉnh tiền
        generalInvoiceInfo.setAdjustmentType("5");
        generalInvoiceInfo.setAdjustmentInvoiceType("1");
        generalInvoiceInfo.setAdjustedNote("Điều chỉnh tiền do cong nham");
        generalInvoiceInfo.setOriginalInvoiceId("K24TJS1");
        generalInvoiceInfo.setOriginalInvoiceIssueDate("1736818200000");
        generalInvoiceInfo.setOriginalTemplateCode("1/0173");
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
        itemInfo.setTaxPercentage(new BigDecimal(10));
        itemInfo.setTaxAmount(new BigDecimal(3000000));
        itemInfo.setItemTotalAmountWithTax(new BigDecimal(33000000));
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
        info.setStringValue("Thong tin bo sung chuoi");
        info.setKeyTag("invoiceNote");
        info.setValueType("text");
        infoUpdateList.add(info);
        invoiceWSDTO.setMetadata(infoUpdateList);

        // Tong tien hoa don
        InvoiceInputWSDTO.SummarizeInfo sum = new InvoiceInputWSDTO.SummarizeInfo();
        sum.setDiscountAmount(new BigDecimal(0));
        sum.setTotalAmountWithoutTax(new BigDecimal(30000000));
        sum.setTotalTaxAmount(new BigDecimal(3000000));
        sum.setTotalAmountWithTax(new BigDecimal(33000000));
        sum.setTotalAmountAfterDiscount(new BigDecimal(30000000));
        invoiceWSDTO.setSummarizeInfo(sum);

        return invoiceWSDTO;
    }

    private InvoiceInputWSDTO.CreateInvoiceWSDTO genWSBodyInputNewGTGT() {
        InvoiceInputWSDTO.CreateInvoiceWSDTO invoiceWSDTO = new InvoiceInputWSDTO.CreateInvoiceWSDTO();

        // Thong tin chung hoa don
        InvoiceInputWSDTO.GeneralInvoiceInfo generalInvoiceInfo = new InvoiceInputWSDTO.GeneralInvoiceInfo();
        generalInvoiceInfo.setTemplateCode("1/0173");
        generalInvoiceInfo.setInvoiceSeries("K26TJS");
        generalInvoiceInfo.setCurrencyCode("VND");
        generalInvoiceInfo.setAdjustmentType("1");
        generalInvoiceInfo.setPaymentStatus(true);
        generalInvoiceInfo.setTransactionUuid(UUID.randomUUID().toString());
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
        itemInfo.setTaxPercentage(new BigDecimal(10));
        itemInfo.setTaxAmount(new BigDecimal(3000000));
        itemInfo.setItemTotalAmountWithTax(new BigDecimal(33000000));
        itemInfo.setItemTotalAmountAfterDiscount(new BigDecimal(0));
        itemInfo.setDiscount(new BigDecimal(0));
        itemInfo.setDiscount2(new BigDecimal(0));
        itemInfo.setItemDiscount(new BigDecimal(0));
        itemInfo.setSelection(1);
        list.add(itemInfo);
        invoiceWSDTO.setItemInfo(list);

        // Thong tin thue: Nếu là thuế dòng thì bỏ qua
        InvoiceInputWSDTO.TaxBreakDownsInfo taxInfo = new InvoiceInputWSDTO.TaxBreakDownsInfo();
        taxInfo.setTaxPercentage(new BigDecimal(10));
        taxInfo.setTaxableAmount(new BigDecimal(30000000));
        taxInfo.setTaxAmount(new BigDecimal(3000000));
        invoiceWSDTO.setTaxBreakdowns(Collections.singletonList(taxInfo));

        // Thong tin bo sung:
        // - Nếu không có thông tin bổ sung thì bỏ qua
        // - Nếu có thì thêm theo các thông tin bổ sung của mẫu hóa đơn
        List<InvoiceInputWSDTO.MetaDataInfo> infoUpdateList = new ArrayList<>();
        InvoiceInputWSDTO.MetaDataInfo info = new InvoiceInputWSDTO.MetaDataInfo();
        info.setStringValue("Thong tin bo sung chuoi");
        info.setKeyTag("invoiceNote");
        info.setValueType("text");
        infoUpdateList.add(info);
        invoiceWSDTO.setMetadata(infoUpdateList);

        // Tong tien hoa don
        InvoiceInputWSDTO.SummarizeInfo sum = new InvoiceInputWSDTO.SummarizeInfo();
        sum.setDiscountAmount(new BigDecimal(0));
        sum.setTotalAmountWithoutTax(new BigDecimal(30000000));
        sum.setTotalTaxAmount(new BigDecimal(3000000));
        sum.setTotalAmountWithTax(new BigDecimal(33000000));
        sum.setTotalAmountAfterDiscount(new BigDecimal(30000000));
        invoiceWSDTO.setSummarizeInfo(sum);

        return invoiceWSDTO;
    }

    private HttpEntity<Object> generatePostRequest(String token, Object request) {
        HttpHeaders headers = new HttpHeaders();
        // Dùng phương thức có sẵn của HttpHeaders để an toàn hơn
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.ALL));

        if (token != null) {
            headers.set("Authorization", "Bearer " + token);
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
