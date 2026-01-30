package com.invoice.example.domain.dto;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class InvoiceInputWSDTO implements Serializable {

    public static class BaseDTO {
        @NotNull(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @Size(max = 20, message = "BAD_REQUEST_TAX_CODE_MAX_LENGTH")
        private String supplierTaxCode;
        @NotNull(message = "BAD_REQUEST_INVOICE_NO_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_INVOICE_NO_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_INVOICE_NO_REQUIRED")
        @Size(min = 7, message = "BAD_REQUEST_INVOICE_NO_MIN_LENGTH")
        @Size(max = 17, message = "BAD_REQUEST_INVOICE_NO_MAX_LENGTH")
        @Pattern(regexp = "^[a-zA-Z0-9/-]*$", message = "BAD_REQUEST_INVOICE_NO_INVALID")
        private String invoiceNo;

        @Size(max = 20, message = "BAD_REQUEST_TEMPLATE_CODE_MAX_LENGTH")
        @NotNull(message = "BAD_REQUEST_TEMPLATE_CODE_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_TEMPLATE_CODE_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_TEMPLATE_CODE_REQUIRED")
        private String templateCode;

        public BaseDTO() {
        }
        public String getSupplierTaxCode() {
            return supplierTaxCode;
        }

        public void setSupplierTaxCode(String supplierTaxCode) {
            this.supplierTaxCode = supplierTaxCode;
        }

        public String getInvoiceNo() {
            return invoiceNo;
        }

        public void setInvoiceNo(String invoiceNo) {
            this.invoiceNo = invoiceNo;
        }

        public String getTemplateCode() {
            return templateCode;
        }

        public void setTemplateCode(String templateCode) {
            this.templateCode = templateCode;
        }
    }

    @XmlRootElement(name = "getInvoiceInput")
    public static class GetInvoiceInput {

        @Size(min = 7, message = "BAD_REQUEST_INVOICE_NO_MIN_LENGTH")
        @Size(max = 17, message = "BAD_REQUEST_INVOICE_NO_MAX_LENGTH")
        @Pattern(regexp = "^[a-zA-Z0-9/-]*$", message = "BAD_REQUEST_INVOICE_NO_INVALID")
        private String invoiceNo;

        @NotNull(message = "BAD_REQUEST_START_DATE_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_START_DATE_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_START_DATE_REQUIRED")
        @Size(max = 50, message = "BAD_REQUEST_START_DATE_MAX_LENGTH")
        private String startDate;

        @NotNull(message = "BAD_REQUEST_END_DATE_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_END_DATE_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_END_DATE_REQUIRED")
        @Size(max = 50, message = "BAD_REQUEST_END_DATE_MAX_LENGTH")
        private String endDate;
        private String invoiceType;

        @NotNull(message = "BAD_REQUEST_ROW_PER_PAGE_REQUIRED")
        @Min(value = 1, message = "BAD_REQUEST_ROW_PER_PAGE_MIN_VALUE")
        private Integer rowPerPage;

        @NotNull(message = "BAD_REQUEST_PAGE_NUM_REQUIRED")
        @Min(value = 0, message = "BAD_REQUEST_PAGE_NUM_MIN_VALUE")
        private Integer pageNum;

        @Size(max = 20, message = "BAD_REQUEST_BUYER_TAX_CODE_MAX_LENGTH")
        private String buyerTaxCode; // Long
        private String buyerIdNo;

        @Size(max = 20, message = "BAD_REQUEST_TEMPLATE_CODE_MAX_LENGTH")
        private String templateCode;

        @Size(max = 9, message = "BAD_REQUEST_INVOICE_SERIAL_MAX_LENGTH")
        private String invoiceSeri;

        private Boolean getAll;

        @Size(max = 50, message = "BAD_REQUEST_ISSUE_START_DATE_MAX_LENGTH")
        private String issueStartDate;

        @Size(max = 50, message = "BAD_REQUEST_ISSUE_END_DATE_MAX_LENGTH")
        private String issueEndDate;

        public GetInvoiceInput() {
        }

        ;

        public String getInvoiceNo() {
            return invoiceNo;
        }

        public void setInvoiceNo(String invoiceNo) {
            this.invoiceNo = invoiceNo;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getInvoiceType() {
            return invoiceType;
        }

        public void setInvoiceType(String invoiceType) {
            this.invoiceType = invoiceType;
        }

        public Integer getRowPerPage() {
            return rowPerPage;
        }

        public void setRowPerPage(Integer rowPerPage) {
            this.rowPerPage = rowPerPage;
        }

        public Integer getPageNum() {
            return pageNum;
        }

        public void setPageNum(Integer pageNum) {
            this.pageNum = pageNum;
        }

        public String getBuyerTaxCode() {
            return buyerTaxCode;
        }

        public void setBuyerTaxCode(String buyerTaxCode) {
            this.buyerTaxCode = buyerTaxCode;
        }

        public String getBuyerIdNo() {
            return buyerIdNo;
        }

        public void setBuyerIdNo(String buyerIdNo) {
            this.buyerIdNo = buyerIdNo;
        }

        public String getTemplateCode() {
            return templateCode;
        }

        public void setTemplateCode(String templateCode) {
            this.templateCode = templateCode;
        }

        public String getInvoiceSeri() {
            return invoiceSeri;
        }

        public void setInvoiceSeri(String invoiceSeri) {
            this.invoiceSeri = invoiceSeri;
        }

        public Boolean getGetAll() {
            return getAll;
        }

        public void setGetAll(Boolean getAll) {
            this.getAll = getAll;
        }

        public String getIssueStartDate() {
            return issueStartDate;
        }

        public void setIssueStartDate(String issueStartDate) {
            this.issueStartDate = issueStartDate;
        }

        public String getIssueEndDate() {
            return issueEndDate;
        }

        public void setIssueEndDate(String issueEndDate) {
            this.issueEndDate = issueEndDate;
        }
    }

    public static class CancelTransactionWSDTO {

        @NotNull(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @Size(max = 20, message = "BAD_REQUEST_TAX_CODE_MAX_LENGTH")
        private String supplierTaxCode;

        @NotNull(message = "BAD_REQUEST_INVOICE_NO_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_INVOICE_NO_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_INVOICE_NO_REQUIRED")
        @Size(min = 7, message = "BAD_REQUEST_INVOICE_NO_MIN_LENGTH")
        @Size(max = 17, message = "BAD_REQUEST_INVOICE_NO_MAX_LENGTH")
        @Pattern(regexp = "^[a-zA-Z0-9/-]*$", message = "BAD_REQUEST_INVOICE_NO_INVALID")
        private String invoiceNo;

        @Size(max = 20, message = "BAD_REQUEST_TEMPLATE_CODE_MAX_LENGTH")
        private String templateCode;

        @NotNull(message = "BAD_REQUEST_ISSUE_DATE_REQUIRED")
        private Long strIssueDate;

        @NotNull(message = "BAD_REQUEST_ADDITIONAL_REFER_DESC_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_ADDITIONAL_REFER_DESC_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_ADDITIONAL_REFER_DESC_REQUIRED")
        @Size(max = 400, message = "BAD_REQUEST_ADDITIONAL_REFER_DESC_LENGTH")
        private String additionalReferenceDesc;

        @NotNull(message = "BAD_REQUEST_ADDITIONAL_REFER_DATE_REQUIRED")
        private Long additionalReferenceDate;

        @Size(max = 255, message = "BAD_REQUEST_REASON_DELETE_LENGTH")
        private String reasonDelete;

        public CancelTransactionWSDTO() {
        }

        public String getSupplierTaxCode() {
            return supplierTaxCode;
        }

        public void setSupplierTaxCode(String supplierTaxCode) {
            this.supplierTaxCode = supplierTaxCode;
        }

        public String getInvoiceNo() {
            return invoiceNo;
        }

        public void setInvoiceNo(String invoiceNo) {
            this.invoiceNo = invoiceNo;
        }

        public String getTemplateCode() {
            return templateCode;
        }

        public void setTemplateCode(String templateCode) {
            this.templateCode = templateCode;
        }

        public Long getStrIssueDate() {
            return strIssueDate;
        }

        public void setStrIssueDate(Long strIssueDate) {
            this.strIssueDate = strIssueDate;
        }

        public String getAdditionalReferenceDesc() {
            return additionalReferenceDesc;
        }

        public void setAdditionalReferenceDesc(String additionalReferenceDesc) {
            this.additionalReferenceDesc = additionalReferenceDesc;
        }

        public Long getAdditionalReferenceDate() {
            return additionalReferenceDate;
        }

        public void setAdditionalReferenceDate(Long additionalReferenceDate) {
            this.additionalReferenceDate = additionalReferenceDate;
        }

        public String getReasonDelete() {
            return reasonDelete;
        }

        public void setReasonDelete(String reasonDelete) {
            this.reasonDelete = reasonDelete;
        }
    }


    @XmlRootElement(name = "commonDataInput")
    public static class CommonDataInput {

        @NotNull(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @Size(max = 20, message = "BAD_REQUEST_TAX_CODE_MAX_LENGTH")
        private String supplierTaxCode;

        @NotNull(message = "BAD_REQUEST_ISSUE_DATE_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_ISSUE_DATE_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_ISSUE_DATE_REQUIRED")
        private String strIssueDate;

        public CommonDataInput() {
        }

        public String getSupplierTaxCode() {
            return supplierTaxCode;
        }

        public void setSupplierTaxCode(String supplierTaxCode) {
            this.supplierTaxCode = supplierTaxCode;
        }

        public String getStrIssueDate() {
            return strIssueDate;
        }

        public void setStrIssueDate(String strIssueDate) {
            this.strIssueDate = strIssueDate;
        }
    }

    @XmlRootElement(name = "commonDataInput")
    public static class CommonDataInput2 {
        @NotNull(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @Size(max = 20, message = "BAD_REQUEST_TAX_CODE_MAX_LENGTH")
        private String supplierTaxCode;

        @NotNull(message = "BAD_REQUEST_TEMPLATE_CODE_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_TEMPLATE_CODE_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_TEMPLATE_CODE_REQUIRED")
        @Size(max = 20, message = "BAD_REQUEST_TEMPLATE_CODE_MAX_LENGTH")
        private String templateCode;

        @NotNull(message = "BAD_REQUEST_INVOICE_SERIAL_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_INVOICE_SERIAL_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_INVOICE_SERIAL_REQUIRED")
        @Size(max = 9, message = "BAD_REQUEST_INVOICE_SERIAL_MAX_LENGTH")
        private String serial;

        public CommonDataInput2() {
        }

        public String getSupplierTaxCode() {
            return supplierTaxCode;
        }

        public void setSupplierTaxCode(String supplierTaxCode) {
            this.supplierTaxCode = supplierTaxCode;
        }

        public String getTemplateCode() {
            return templateCode;
        }

        public void setTemplateCode(String templateCode) {
            this.templateCode = templateCode;
        }

        public String getSerial() {
            return serial;
        }

        public void setSerial(String serial) {
            this.serial = serial;
        }
    }

    @XmlRootElement(name = "commonDataInput")
    public static class CommonDataInput3 {
        @NotNull(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @Size(max = 20, message = "BAD_REQUEST_TAX_CODE_MAX_LENGTH")
        private String supplierTaxCode;

        @NotNull(message = "BAD_REQUEST_FROM_DATE_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_FROM_DATE_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_FROM_DATE_REQUIRED")
        private String fromDate; // dd/MM/yyyy

        @NotNull(message = "BAD_REQUEST_TO_DATE_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_TO_DATE_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_TO_DATE_REQUIRED")
        private String toDate; // dd/MM/yyyy

        public CommonDataInput3() {
        }

        ;

        public String getSupplierTaxCode() {
            return supplierTaxCode;
        }

        public void setSupplierTaxCode(String supplierTaxCode) {
            this.supplierTaxCode = supplierTaxCode;
        }

        public String getFromDate() {
            return fromDate;
        }

        public void setFromDate(String fromDate) {
            this.fromDate = fromDate;
        }

        public String getToDate() {
            return toDate;
        }

        public void setToDate(String toDate) {
            this.toDate = toDate;
        }
    }

    @XmlRootElement(name = "commonDataInput")
    public static class CommonDataInput4 {
        @NotNull(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @Size(max = 20, message = "BAD_REQUEST_TAX_CODE_MAX_LENGTH")
        private String supplierTaxCode;

        @NotNull(message = "BAD_REQUEST_LST_TRANSACTION_UUID_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_LST_TRANSACTION_UUID_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_LST_TRANSACTION_UUID_REQUIRED")
        private String lstTransactionUuid;

        public CommonDataInput4() {
        }

        public String getSupplierTaxCode() {
            return supplierTaxCode;
        }

        public void setSupplierTaxCode(String supplierTaxCode) {
            this.supplierTaxCode = supplierTaxCode;
        }

        public String getLstTransactionUuid() {
            return lstTransactionUuid;
        }

        public void setLstTransactionUuid(String lstTransactionUuid) {
            this.lstTransactionUuid = lstTransactionUuid;
        }
    }

    public static class UpdatePaymentWSDTO {
        @NotNull(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @Size(max = 20, message = "BAD_REQUEST_TAX_CODE_MAX_LENGTH")
        private String supplierTaxCode;

        @NotNull(message = "BAD_REQUEST_INVOICE_NO_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_INVOICE_NO_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_INVOICE_NO_REQUIRED")
        @Pattern(regexp = "^[a-zA-Z0-9/-]*$", message = "BAD_REQUEST_INVOICE_NO_INVALID")
        @Size(min = 7, max = 17, message = "BAD_REQUEST_INVOICE_NO_MAX_LENGTH")
        private String invoiceNo;

        @NotNull(message = "BAD_REQUEST_TEMPLATE_CODE_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_TEMPLATE_CODE_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_TEMPLATE_CODE_REQUIRED")
        @Size(max = 20, message = "BAD_REQUEST_TEMPLATE_CODE_MAX_LENGTH")
        private String templateCode;
        private Long strIssueDate;


        @Size(max = 2000, message = "BAD_REQUEST_BUYER_EMAIL_MAX_LENGTH")
        private String buyerEmailAddress;

        private String paymentType;
        private String paymentTypeName;

        public UpdatePaymentWSDTO() {
        }

        public String getSupplierTaxCode() {
            return supplierTaxCode;
        }

        public void setSupplierTaxCode(String supplierTaxCode) {
            this.supplierTaxCode = supplierTaxCode;
        }

        public String getInvoiceNo() {
            return invoiceNo;
        }

        public void setInvoiceNo(String invoiceNo) {
            this.invoiceNo = invoiceNo;
        }

        public String getTemplateCode() {
            return templateCode;
        }

        public void setTemplateCode(String templateCode) {
            this.templateCode = templateCode;
        }

        public Long getStrIssueDate() {
            return strIssueDate;
        }

        public void setStrIssueDate(Long strIssueDate) {
            this.strIssueDate = strIssueDate;
        }

        public String getBuyerEmailAddress() {
            return buyerEmailAddress;
        }

        public void setBuyerEmailAddress(String buyerEmailAddress) {
            this.buyerEmailAddress = buyerEmailAddress;
        }

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

        public String getPaymentTypeName() {
            return paymentTypeName;
        }

        public void setPaymentTypeName(String paymentTypeName) {
            this.paymentTypeName = paymentTypeName;
        }

    }

    public static class CancelPaymentWSDTO {

        @NotNull(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @Size(max = 20, message = "BAD_REQUEST_TAX_CODE_MAX_LENGTH")
        private String supplierTaxCode;

        @NotNull(message = "BAD_REQUEST_INVOICE_NO_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_INVOICE_NO_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_INVOICE_NO_REQUIRED")
        @Size(min = 7, message = "BAD_REQUEST_INVOICE_NO_MIN_LENGTH")
        @Size(max = 17, message = "BAD_REQUEST_INVOICE_NO_MAX_LENGTH")
        @Pattern(regexp = "^[a-zA-Z0-9/-]*$", message = "BAD_REQUEST_INVOICE_NO_INVALID")
        private String invoiceNo;

        @NotNull(message = "BAD_REQUEST_ISSUE_DATE_REQUIRED")
        private Long strIssueDate;

        public CancelPaymentWSDTO() {
        }

        ;

        public String getSupplierTaxCode() {
            return supplierTaxCode;
        }

        public void setSupplierTaxCode(String supplierTaxCode) {
            this.supplierTaxCode = supplierTaxCode;
        }

        public Long getStrIssueDate() {
            return strIssueDate;
        }

        public void setStrIssueDate(Long strIssueDate) {
            this.strIssueDate = strIssueDate;
        }

        public String getInvoiceNo() {
            return invoiceNo;
        }

        public void setInvoiceNo(String invoiceNo) {
            this.invoiceNo = invoiceNo;
        }
    }

    @XmlRootElement(name = "commonInvoiceInput")
    public static class CreateInvoiceWSDTO {
        @NotNull(message = "BAD_REQUEST_GENERAL_INVOICE_INFO_REQUIRED")
        private @Valid GeneralInvoiceInfo generalInvoiceInfo;

        private @Valid SellerInfo sellerInfo;
        private @Valid BuyerInfo buyerInfo;

        @NotNull(message = "BAD_REQUEST_PAYMENT_INFO_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_PAYMENT_INFO_REQUIRED")
        private List<@Valid PaymentInfo> payments;
        private List<@Valid ItemInfo> itemInfo;
        private List<TaxBreakDownsInfo> taxBreakdowns;
        private @Valid SummarizeInfo summarizeInfo;
        private List<MetaDataInfo> metadata;
        private List<@Valid MeterReadingInfo> meterReading;
        private List<@Valid FuelReadingInfo> fuelReading;

        private QrCodeInfo qrCodeInfo;

        public CreateInvoiceWSDTO() {
        }

        public GeneralInvoiceInfo getGeneralInvoiceInfo() {
            return generalInvoiceInfo;
        }

        public void setGeneralInvoiceInfo(GeneralInvoiceInfo generalInvoiceInfo) {
            this.generalInvoiceInfo = generalInvoiceInfo;
        }

        public SellerInfo getSellerInfo() {
            return sellerInfo;
        }

        public void setSellerInfo(SellerInfo sellerInfo) {
            this.sellerInfo = sellerInfo;
        }

        public BuyerInfo getBuyerInfo() {
            return buyerInfo;
        }

        public void setBuyerInfo(BuyerInfo buyerInfo) {
            this.buyerInfo = buyerInfo;
        }


        public SummarizeInfo getSummarizeInfo() {
            return summarizeInfo;
        }

        public void setSummarizeInfo(SummarizeInfo summarizeInfo) {
            this.summarizeInfo = summarizeInfo;
        }

        public List<PaymentInfo> getPayments() {
            return payments;
        }

        public void setPayments(List<PaymentInfo> payments) {
            this.payments = payments;
        }

        public List<ItemInfo> getItemInfo() {
            return itemInfo;
        }

        public void setItemInfo(List<ItemInfo> itemInfo) {
            this.itemInfo = itemInfo;
        }

        public List<TaxBreakDownsInfo> getTaxBreakdowns() {
            return taxBreakdowns;
        }

        public void setTaxBreakdowns(List<TaxBreakDownsInfo> taxBreakdowns) {
            this.taxBreakdowns = taxBreakdowns;
        }

        public List<MetaDataInfo> getMetadata() {
            return metadata;
        }

        public void setMetadata(List<MetaDataInfo> metadata) {
            this.metadata = metadata;
        }

        public List<MeterReadingInfo> getMeterReading() {
            return meterReading;
        }

        public void setMeterReading(List<MeterReadingInfo> meterReading) {
            this.meterReading = meterReading;
        }

        public QrCodeInfo getQrCodeInfo() {
            return qrCodeInfo;
        }

        public void setQrCodeInfo(QrCodeInfo qrCodeInfo) {
            this.qrCodeInfo = qrCodeInfo;
        }

        public List<FuelReadingInfo> getFuelReading() {
            return fuelReading;
        }

        public void setFuelReading(List<FuelReadingInfo> fuelReading) {
            this.fuelReading = fuelReading;
        }
    }

    public static class GeneralInvoiceInfo {
        private String invoiceType;
        @NotNull(message = "BAD_REQUEST_TEMPLATE_CODE_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_TEMPLATE_CODE_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_TEMPLATE_CODE_REQUIRED")
        @Size(max = 20, message = "BAD_REQUEST_TEMPLATE_CODE_MAX_LENGTH")
        private String templateCode;
        @NotNull(message = "BAD_REQUEST_INVOICE_SERIAL_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_INVOICE_SERIAL_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_INVOICE_SERIAL_REQUIRED")
        @Size(max = 9, message = "BAD_REQUEST_INVOICE_SERIAL_MAX_LENGTH")
        private String invoiceSeries;
        private Long invoiceIssuedDate;

        @NotNull(message = "BAD_REQUEST_CURRENCY_CODE_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_CURRENCY_CODE_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_CURRENCY_CODE_REQUIRED")
        @Size(min = 3, message = "BAD_REQUEST_CURRENCY_CODE_MINLENGTH")
        @Size(max = 3, message = "BAD_REQUEST_CURRENCY_CODE_MAXLENGTH")
        @Pattern(regexp = "[A-Z]+", message = "BAD_REQUEST_CURRENCY_CODE_INVALID")
        private String currencyCode;

        @Pattern(regexp = "^$|[1,3,5,7]", message = "BAD_REQUEST_ADJUSTMENT_TYPE_INVALID")
        @Size(max = 1, message = "BAD_REQUEST_ADJUSTMENT_TYPE_MAX_LENGTH")
        private String adjustmentType;

        @Size(max = 1, message = "BAD_REQUEST_ADJUSTMENT_INVOICE_TYPE_MAX_LENGTH")
        @Pattern(regexp = "^$|[1,2]", message = "BAD_REQUEST_ADJUSTMENT_INVOICE_TYPE_INVALID")
        private String adjustmentInvoiceType;

        @Size(max = 17, min = 1, message = "BAD_REQUEST_ORIGIN_INVOICE_NO_INVALID_LENGTH")
        @Pattern(regexp = "^[a-zA-Z0-9/-]*$", message = "BAD_REQUEST_ORIGIN_INVOICE_NO_INVALID")
        private String originalInvoiceId;

        @Size(max = 50, message = "BAD_REQUEST_ORIGIN_INVOICE_ISSUE_DATE_INVALID")
        private String originalInvoiceIssueDate; // yyyy-MM-dd

        @Size(max = 400, message = "BAD_REQUEST_ADDITIONAL_REFER_DESC_LENGTH")
        private String additionalReferenceDesc;
        private Long additionalReferenceDate;

        @NotNull(message = "BAD_REQUEST_PAYMENT_STATUS_REQUIRED")
        private Boolean paymentStatus; // max length 1, boolean

        @Digits(integer = 11, fraction = 2, message = "BAD_REQUEST_EXCHANGE_RATE_MAX_LENGTH")
        private BigDecimal exchangeRate; // maxlength 13

        @Size(min = 10, max = 36, message = "BAD_REQUEST_TRANSACTION_UUID_LENGTH_INVALID")
        private String transactionUuid;

        private String userName;

        @Size(max = 100, message = "BAD_REQUEST_CERTIFICATE_SERIAL_MAX_LENGTH")
        private String certificateSerial;

        private String transactionId;
        @Size(max = 4000, message = "BAD_REQUEST_INVOICE_NOTE_LENGTH_INVALID")
        private String invoiceNote;

        @Size(max = 1, message = "BAD_REQUEST_ADJUST_AMOUNT20_MAX_LENGTH")
        @Pattern(regexp = "^$|[0,1,2,3,5]", message = "BAD_REQUEST_ADJUST_AMOUNT20_INVALID")
        private String adjustAmount20;

        @Size(max = 1, message = "BAD_REQUEST_ORIGINAL_INVOICE_TYPE_MAX_LENGTH")
        @Pattern(regexp = "^$|[0,1,2,3,4]", message = "BAD_REQUEST_ORIGINAL_INVOICE_TYPE_INVALID")
        private String originalInvoiceType;

        @Size(max = 20, message = "BAD_REQUEST_ORIGINAL_TEMPLATE_CODE_MAX_LENGTH")
        private String originalTemplateCode;

        @Size(max = 255, message = "BAD_REQUEST_ADJUSTED_NOTE_MAX_LENGTH")
        private String adjustedNote;

        @Size(max = 100, message = "BAD_REQUEST_RESERVATION_CODE_MAX_LENGTH")
        private String reservationCode;

        private Integer validation; // 0: Bỏ validate, khác 0 sẽ validate
        private Long typeId; // 0: Bỏ validate, khác 0 sẽ validate
        private Long classifyId; // 0: Bỏ validate, khác 0 sẽ validate

        @Override
        public String toString() {
            return "GeneralInvoiceInfo{" +
                "invoiceType='" + invoiceType +
                ", templateCode='" + templateCode +
                ", invoiceSeries='" + invoiceSeries +
                ", invoiceIssuedDate=" + invoiceIssuedDate +
                ", currencyCode='" + currencyCode +
                ", adjustmentType='" + adjustmentType +
                ", adjustmentInvoiceType='" + adjustmentInvoiceType +
                ", originalInvoiceId='" + originalInvoiceId +
                ", originalInvoiceIssueDate='" + originalInvoiceIssueDate +
                ", additionalReferenceDesc='" + additionalReferenceDesc +
                ", additionalReferenceDate=" + additionalReferenceDate +
                ", paymentStatus=" + paymentStatus +
                ", exchangeRate=" + exchangeRate +
                ", transactionUuid='" + transactionUuid +
                ", userName='" + userName +
                ", certificateSerial='" + certificateSerial +
                ", transactionId='" + transactionId +
                ", invoiceNote='" + invoiceNote +
                ", validation='" + validation +
                '}';
        }

        public GeneralInvoiceInfo() {
        }

        public String getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }

        public String getInvoiceType() {
            return invoiceType;
        }

        public void setInvoiceType(String invoiceType) {
            this.invoiceType = invoiceType;
        }

        public String getTemplateCode() {
            return templateCode;
        }

        public void setTemplateCode(String templateCode) {
            this.templateCode = templateCode;
        }

        public String getInvoiceSeries() {
            return invoiceSeries;
        }

        public void setInvoiceSeries(String invoiceSeries) {
            this.invoiceSeries = invoiceSeries;
        }

        public Long getInvoiceIssuedDate() {
            return invoiceIssuedDate;
        }

        public void setInvoiceIssuedDate(Long invoiceIssuedDate) {
            this.invoiceIssuedDate = invoiceIssuedDate;
        }

        public String getCurrencyCode() {
            return currencyCode;
        }

        public void setCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
        }

        public String getAdjustmentType() {
            return adjustmentType;
        }

        public void setAdjustmentType(String adjustmentType) {
            this.adjustmentType = adjustmentType;
        }

        public String getAdjustmentInvoiceType() {
            return adjustmentInvoiceType;
        }

        public void setAdjustmentInvoiceType(String adjustmentInvoiceType) {
            this.adjustmentInvoiceType = adjustmentInvoiceType;
        }

        public String getOriginalInvoiceId() {
            return originalInvoiceId;
        }

        public void setOriginalInvoiceId(String originalInvoiceId) {
            this.originalInvoiceId = originalInvoiceId;
        }

        public String getOriginalInvoiceIssueDate() {
            return originalInvoiceIssueDate;
        }

        public void setOriginalInvoiceIssueDate(String originalInvoiceIssueDate) {
            this.originalInvoiceIssueDate = originalInvoiceIssueDate;
        }

        public String getAdditionalReferenceDesc() {
            return additionalReferenceDesc;
        }

        public void setAdditionalReferenceDesc(String additionalReferenceDesc) {
            this.additionalReferenceDesc = additionalReferenceDesc;
        }

        public Long getAdditionalReferenceDate() {
            return additionalReferenceDate;
        }

        public void setAdditionalReferenceDate(Long additionalReferenceDate) {
            this.additionalReferenceDate = additionalReferenceDate;
        }

        public Boolean getPaymentStatus() {
            return paymentStatus;
        }

        public void setPaymentStatus(Boolean paymentStatus) {
            this.paymentStatus = paymentStatus;
        }

        public BigDecimal getExchangeRate() {
            return exchangeRate;
        }

        public void setExchangeRate(BigDecimal exchangeRate) {
            this.exchangeRate = exchangeRate;
        }

        public String getTransactionUuid() {
            return transactionUuid;
        }

        public void setTransactionUuid(String transactionUuid) {
            this.transactionUuid = transactionUuid;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getCertificateSerial() {
            return certificateSerial;
        }

        public void setCertificateSerial(String certificateSerial) {
            this.certificateSerial = certificateSerial;
        }

        public String getInvoiceNote() {
            return invoiceNote;
        }

        public void setInvoiceNote(String invoiceNote) {
            this.invoiceNote = invoiceNote;
        }

        public String getAdjustAmount20() {
            return adjustAmount20;
        }

        public void setAdjustAmount20(String adjustAmount20) {
            this.adjustAmount20 = adjustAmount20;
        }

        public String getOriginalInvoiceType() {
            return originalInvoiceType;
        }

        public void setOriginalInvoiceType(String originalInvoiceType) {
            this.originalInvoiceType = originalInvoiceType;
        }

        public String getOriginalTemplateCode() {
            return originalTemplateCode;
        }

        public void setOriginalTemplateCode(String originalTemplateCode) {
            this.originalTemplateCode = originalTemplateCode;
        }

        public String getAdjustedNote() {
            return adjustedNote;
        }

        public void setAdjustedNote(String adjustedNote) {
            this.adjustedNote = adjustedNote;
        }

        public String getReservationCode() {
            return reservationCode;
        }

        public void setReservationCode(String reservationCode) {
            this.reservationCode = reservationCode;
        }

        public Integer getValidation() {
            return validation;
        }

        public void setValidation(Integer validation) {
            this.validation = validation;
        }

        public Long getTypeId() {
            return typeId;
        }

        public void setTypeId(Long typeId) {
            this.typeId = typeId;
        }

        public Long getClassifyId() {
            return classifyId;
        }

        public void setClassifyId(Long classifyId) {
            this.classifyId = classifyId;
        }
    }

    public static class SellerInfo {

        @Size(max = 255, message = "BAD_REQUEST_SELLER_LEGAL_NAME_MAXLENGTH")
        private String sellerLegalName;

        @Size(max = 20, message = "BAD_REQUEST_SELLER_TAX_CODE_MAX_LENGTH")
        private String sellerTaxCode;

        @Size(max = 255, message = "BAD_REQUEST_SELLER_ADDRESS_LINE_MAXLENGTH")
        private String sellerAddressLine;

        @Size(max = 50, message = "BAD_REQUEST_SELLER_PHONE_NUMBER_MAX_LENGTH")
        @Pattern(regexp = "\\s*[0-9]*\\s*", message = "BAD_REQUEST_SELLER_PHONE_NUMBER_INVALID")
        private String sellerPhoneNumber;

        @Size(max = 50, message = "BAD_REQUEST_SELLER_FAX_NUMBER_MAXLENGTH")
        @Pattern(regexp = "\\s*[0-9]*\\s*", message = "BAD_REQUEST_SELLER_FAX_NUMBER_INVALID")
        private String sellerFaxNumber;

        @Size(max = 50, message = "BAD_REQUEST_SELLER_EMAIL_MAX_LENGTH")
        private String sellerEmail;

        @Size(max = 400, message = "BAD_REQUEST_SELLER_BANK_NAME_MAXLENGTH")
        private String sellerBankName;

        @Size(max = 200, message = "BAD_REQUEST_SELLER_BANK_ACCOUNT_MAXLENGTH")
        private String sellerBankAccount;

        @Size(max = 50, message = "BAD_REQUEST_SELLER_DISTRICT_NAME_MAX_LENGTH")
        private String sellerDistrictName;

        @Size(max = 600, message = "BAD_REQUEST_SELLER_CITY_NAME_MAX_LENGTH")
        private String sellerCityName;

        @Size(max = 17, message = "BAD_REQUEST_SELLER_COUNTRY_CODE_MAX_LENGTH")
        private String sellerCountryCode;

        @Size(max = 200, message = "BAD_REQUEST_WEBSITE_MAX_LENGTH")
        private String sellerWebsite;

        private String merchantCode;

        private String merchantName;

        private String merchantCity;

        public SellerInfo() {
        }

        @Override
        public String toString() {
            return "SellerInfo{" +
                "sellerLegalName='" + sellerLegalName +
                ", sellerTaxCode='" + sellerTaxCode +
                ", sellerAddressLine='" + sellerAddressLine +
                ", sellerPhoneNumber='" + sellerPhoneNumber +
                ", sellerFaxNumber='" + sellerFaxNumber +
                ", sellerEmail='" + sellerEmail +
                ", sellerBankName='" + sellerBankName +
                ", sellerBankAccount='" + sellerBankAccount +
                ", sellerDistrictName='" + sellerDistrictName +
                ", sellerCityName='" + sellerCityName +
                ", sellerCountryCode='" + sellerCountryCode +
                ", sellerWebsite='" + sellerWebsite +
                ", merchantCode='" + merchantCode +
                ", merchantName='" + merchantName +
                ", merchantCity='" + merchantCity +
                '}';
        }

        public String getMerchantCode() {
            return merchantCode;
        }

        public void setMerchantCode(String merchantCode) {
            this.merchantCode = merchantCode;
        }

        public String getMerchantName() {
            return merchantName;
        }

        public void setMerchantName(String merchantName) {
            this.merchantName = merchantName;
        }

        public String getMerchantCity() {
            return merchantCity;
        }

        public void setMerchantCity(String merchantCity) {
            this.merchantCity = merchantCity;
        }

        public String getSellerLegalName() {
            return sellerLegalName;
        }

        public void setSellerLegalName(String sellerLegalName) {
            this.sellerLegalName = sellerLegalName;
        }

        public String getSellerTaxCode() {
            return sellerTaxCode;
        }

        public void setSellerTaxCode(String sellerTaxCode) {
            this.sellerTaxCode = sellerTaxCode;
        }

        public String getSellerAddressLine() {
            return sellerAddressLine;
        }

        public void setSellerAddressLine(String sellerAddressLine) {
            this.sellerAddressLine = sellerAddressLine;
        }

        public String getSellerPhoneNumber() {
            return sellerPhoneNumber;
        }

        public void setSellerPhoneNumber(String sellerPhoneNumber) {
            this.sellerPhoneNumber = sellerPhoneNumber;
        }

        public String getSellerFaxNumber() {
            return sellerFaxNumber;
        }

        public void setSellerFaxNumber(String sellerFaxNumber) {
            this.sellerFaxNumber = sellerFaxNumber;
        }

        public String getSellerEmail() {
            return sellerEmail;
        }

        public void setSellerEmail(String sellerEmail) {
            this.sellerEmail = sellerEmail;
        }

        public String getSellerBankName() {
            return sellerBankName;
        }

        public void setSellerBankName(String sellerBankName) {
            this.sellerBankName = sellerBankName;
        }

        public String getSellerBankAccount() {
            return sellerBankAccount;
        }

        public void setSellerBankAccount(String sellerBankAccount) {
            this.sellerBankAccount = sellerBankAccount;
        }

        public String getSellerDistrictName() {
            return sellerDistrictName;
        }

        public void setSellerDistrictName(String sellerDistrictName) {
            this.sellerDistrictName = sellerDistrictName;
        }

        public String getSellerCityName() {
            return sellerCityName;
        }

        public void setSellerCityName(String sellerCityName) {
            this.sellerCityName = sellerCityName;
        }

        public String getSellerCountryCode() {
            return sellerCountryCode;
        }

        public void setSellerCountryCode(String sellerCountryCode) {
            this.sellerCountryCode = sellerCountryCode;
        }

        public String getSellerWebsite() {
            return sellerWebsite;
        }

        public void setSellerWebsite(String sellerWebsite) {
            this.sellerWebsite = sellerWebsite;
        }
    }

    public static class BuyerInfo {

        @Size(max = 800, message = "BAD_REQUEST_BUYER_NAME_MAXLENGTH")
        private String buyerName;

        @Size(max = 400, message = "BAD_REQUEST_BUYER_CODE_MAX_LENGTH")
        private String buyerCode;

        @Size(max = 1200, message = "BAD_REQUEST_BUYER_UNIT_NAME_MAX_LENGTH")
        private String buyerLegalName;

        @Size(max = 20, message = "BAD_REQUEST_BUYER_TAX_CODE_MAX_LENGTH")
        private String buyerTaxCode;

        @Size(max = 1200, message = "BAD_REQUEST_BUYER_ADDRESS_MAX_LENGTH")
        private String buyerAddressLine;

        @Size(max = 35, message = "BAD_REQUEST_BUYER_PHONE_MAX_LENGTH")
        @Pattern(regexp = "[0-9+()&; -]*", message = "BAD_REQUEST_BUYER_PHONE_INVALID")
        private String buyerPhoneNumber;

        //Hệ thống không check
        private String buyerFaxNumber;

        //Được phép nhập nhiều email, mỗi email cách nhau bởi dấu ;
        @Size(max = 2000, message = "BAD_REQUEST_BUYER_EMAIL_MAX_LENGTH")
        private String buyerEmail;

        @Size(max = 200, message = "BAD_REQUEST_BUYER_BANK_NAME_MAX_LENGTH")
        private String buyerBankName;

        @Size(max = 100, message = "BAD_REQUEST_BUYER_BANK_ACCOUNT_MAX_LENGTH")
        private String buyerBankAccount;
        private String buyerIdType;

        @Size(max = 200, message = "BAD_REQUEST_BUYER_ID_NO_MAX_LENGTH")
        @Pattern(regexp = "[a-zA-Z0-9-_ ]*", message = "BAD_REQUEST_BUYER_ID_NO_INVALID")
        private String buyerIdNo;

        private Integer buyerNotGetInvoice;

        @Override
        public String toString() {
            return "BuyerInfo{" +
                "buyerName='" + buyerName +
                ", buyerCode='" + buyerCode +
                ", buyerLegalName='" + buyerLegalName +
                ", buyerTaxCode='" + buyerTaxCode +
                ", buyerAddressLine='" + buyerAddressLine +
                ", buyerPhoneNumber='" + buyerPhoneNumber +
                ", buyerFaxNumber='" + buyerFaxNumber +
                ", buyerEmail='" + buyerEmail +
                ", buyerBankName='" + buyerBankName +
                ", buyerBankAccount='" + buyerBankAccount +
                ", buyerIdType='" + buyerIdType +
                ", buyerIdNo='" + buyerIdNo +
                ", buyerNotGetInvoice=" + buyerNotGetInvoice +
                '}';
        }

        public BuyerInfo() {
        }

        public String getBuyerName() {
            return buyerName;
        }

        public void setBuyerName(String buyerName) {
            this.buyerName = buyerName;
        }

        public String getBuyerCode() {
            return buyerCode;
        }

        public void setBuyerCode(String buyerCode) {
            this.buyerCode = buyerCode;
        }

        public String getBuyerLegalName() {
            return buyerLegalName;
        }

        public void setBuyerLegalName(String buyerLegalName) {
            this.buyerLegalName = buyerLegalName;
        }

        public String getBuyerTaxCode() {
            return buyerTaxCode;
        }

        public void setBuyerTaxCode(String buyerTaxCode) {
            this.buyerTaxCode = buyerTaxCode;
        }

        public String getBuyerAddressLine() {
            return buyerAddressLine;
        }

        public void setBuyerAddressLine(String buyerAddressLine) {
            this.buyerAddressLine = buyerAddressLine;
        }

        public String getBuyerPhoneNumber() {
            return buyerPhoneNumber;
        }

        public void setBuyerPhoneNumber(String buyerPhoneNumber) {
            this.buyerPhoneNumber = buyerPhoneNumber;
        }

        public String getBuyerFaxNumber() {
            return buyerFaxNumber;
        }

        public void setBuyerFaxNumber(String buyerFaxNumber) {
            this.buyerFaxNumber = buyerFaxNumber;
        }

        public String getBuyerEmail() {
            return buyerEmail;
        }

        public void setBuyerEmail(String buyerEmail) {
            this.buyerEmail = buyerEmail;
        }

        public String getBuyerBankName() {
            return buyerBankName;
        }

        public void setBuyerBankName(String buyerBankName) {
            this.buyerBankName = buyerBankName;
        }

        public String getBuyerBankAccount() {
            return buyerBankAccount;
        }

        public void setBuyerBankAccount(String buyerBankAccount) {
            this.buyerBankAccount = buyerBankAccount;
        }

        public String getBuyerIdType() {
            return buyerIdType;
        }

        public void setBuyerIdType(String buyerIdType) {
            this.buyerIdType = buyerIdType;
        }

        public String getBuyerIdNo() {
            return buyerIdNo;
        }

        public void setBuyerIdNo(String buyerIdNo) {
            this.buyerIdNo = buyerIdNo;
        }

        public Integer getBuyerNotGetInvoice() {
            return buyerNotGetInvoice;
        }

        public void setBuyerNotGetInvoice(Integer buyerNotGetInvoice) {
            this.buyerNotGetInvoice = buyerNotGetInvoice;
        }

    }

    public static class ExtAttribute {

        public ExtAttribute() {
        }

    }

    public static class PaymentInfo {
        @Size(max = 50, message = "BAD_REQUEST_PAYMENT_METHOD_NAME_MAX_LENGTH")
        private String paymentMethodName;

        @Pattern(regexp = "\\s*|[1-8]$", message = "BAD_REQUEST_PAYMENT_METHOD_INVALID")
        @Size(max = 50, message = "BAD_REQUEST_PAYMENT_METHOD_MAX_LENGTH")
        public String paymentMethod;

        @Override
        public String toString() {
            return "PaymentInfo{" +
                "paymentMethodName='" + paymentMethodName +
                ", paymentMethod='" + paymentMethod +
                '}';
        }

        public PaymentInfo() {
        }
        public PaymentInfo(String paymentMethodName) {
            this.paymentMethodName = paymentMethodName;
        }
        public String getPaymentMethodName() {
            return paymentMethodName;
        }

        public void setPaymentMethodName(String paymentMethodName) {
            this.paymentMethodName = paymentMethodName;
        }

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }
    }


    public static class ItemInfo {

        @Min(value = 1, message = "BAD_REQUEST_ITEM_SELECTION_INVALID")
        @Max(value = 6, message = "BAD_REQUEST_ITEM_SELECTION_INVALID")
        private Integer selection; // maxlength 1

        private String itemCode;

        private String itemName;

        private String unitCode;

        private String unitName;
        private BigDecimal unitPrice;
        private BigDecimal quantity;

        @Digits(integer = 19, fraction = 9, message = "BAD_REQUEST_ITEM_TOTAL_AMOUNT_WITHOUT_TAX_INVALID")
        private BigDecimal itemTotalAmountWithoutTax;

        private BigDecimal taxPercentage;

        private BigDecimal taxAmount;

        private Boolean isIncreaseItem;

        private String itemNote;

        private String batchNo;

        private String expDate; // Date

        private BigDecimal discount;

        private BigDecimal discount2;

        private BigDecimal itemDiscount;

        private BigDecimal itemTotalAmountAfterDiscount;

        private BigDecimal itemTotalAmountWithTax;

        @Size(max = 1, message = "BAD_REQUEST_ADJUST_RATIO_MAX_LENGTH")
        @Pattern(regexp = "^$|[1,2,3,5]", message = "BAD_REQUEST_ADJUST_RATIO_INVALID")
        private String adjustRatio;

        public ItemInfo() {
        }
        public Integer getSelection() {
            return selection;
        }

        public void setSelection(Integer selection) {
            this.selection = selection;
        }

        public String getItemCode() {
            return itemCode;
        }

        public void setItemCode(String itemCode) {
            this.itemCode = itemCode;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public String getUnitCode() {
            return unitCode;
        }

        public void setUnitCode(String unitCode) {
            this.unitCode = unitCode;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public BigDecimal getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
        }

        public BigDecimal getQuantity() {
            return quantity;
        }

        public void setQuantity(BigDecimal quantity) {
            this.quantity = quantity;
        }

        public BigDecimal getItemTotalAmountWithoutTax() {
            return itemTotalAmountWithoutTax;
        }

        public void setItemTotalAmountWithoutTax(BigDecimal itemTotalAmountWithoutTax) {
            this.itemTotalAmountWithoutTax = itemTotalAmountWithoutTax;
        }

        public BigDecimal getTaxPercentage() {
            return taxPercentage;
        }

        public void setTaxPercentage(BigDecimal taxPercentage) {
            this.taxPercentage = taxPercentage;
        }

        public BigDecimal getTaxAmount() {
            return taxAmount;
        }

        public void setTaxAmount(BigDecimal taxAmount) {
            this.taxAmount = taxAmount;
        }

        public Boolean getIsIncreaseItem() {
            return isIncreaseItem;
        }

        public void setIsIncreaseItem(Boolean isIncreaseItem) {
            this.isIncreaseItem = isIncreaseItem;
        }

        public String getItemNote() {
            return itemNote;
        }

        public void setItemNote(String itemNote) {
            this.itemNote = itemNote;
        }

        public String getBatchNo() {
            return batchNo;
        }

        public void setBatchNo(String batchNo) {
            this.batchNo = batchNo;
        }

        public String getExpDate() {
            return expDate;
        }

        public void setExpDate(String expDate) {
            this.expDate = expDate;
        }

        public BigDecimal getDiscount() {
            return discount;
        }

        public void setDiscount(BigDecimal discount) {
            this.discount = discount;
        }

        public BigDecimal getDiscount2() {
            return discount2;
        }

        public void setDiscount2(BigDecimal discount2) {
            this.discount2 = discount2;
        }

        public BigDecimal getItemDiscount() {
            return itemDiscount;
        }

        public void setItemDiscount(BigDecimal itemDiscount) {
            this.itemDiscount = itemDiscount;
        }

        public BigDecimal getItemTotalAmountAfterDiscount() {
            return itemTotalAmountAfterDiscount;
        }

        public void setItemTotalAmountAfterDiscount(BigDecimal itemTotalAmountAfterDiscount) {
            this.itemTotalAmountAfterDiscount = itemTotalAmountAfterDiscount;
        }

        public BigDecimal getItemTotalAmountWithTax() {
            return itemTotalAmountWithTax;
        }

        public void setItemTotalAmountWithTax(BigDecimal itemTotalAmountWithTax) {
            this.itemTotalAmountWithTax = itemTotalAmountWithTax;
        }

        public String getAdjustRatio() {
            return adjustRatio;
        }

        public void setAdjustRatio(String adjustRatio) {
            this.adjustRatio = adjustRatio;
        }
    }


    public static class TaxBreakDownsInfo {


        private BigDecimal taxPercentage; // maxlength 13

        private BigDecimal taxableAmount; // maxlength 13

        private BigDecimal taxAmount; // maxlength 13

        public TaxBreakDownsInfo() {
        }
        public BigDecimal getTaxPercentage() {
            return taxPercentage;
        }

        public void setTaxPercentage(BigDecimal taxPercentage) {
            this.taxPercentage = taxPercentage;
        }

        public BigDecimal getTaxableAmount() {
            return taxableAmount;
        }

        public void setTaxableAmount(BigDecimal taxableAmount) {
            this.taxableAmount = taxableAmount;
        }

        public BigDecimal getTaxAmount() {
            return taxAmount;
        }

        public void setTaxAmount(BigDecimal taxAmount) {
            this.taxAmount = taxAmount;
        }
    }

    public static class SummarizeInfo {

        private BigDecimal totalAmountWithoutTax; // maxlength 15
        private BigDecimal totalTaxAmount; // maxlength 13
        private BigDecimal totalAmountWithTax; // maxlength 13
        private BigDecimal totalAmountWithTaxFrn; // maxlength 13

        private String totalAmountWithTaxInWords;
        private BigDecimal discountAmount; // maxlength 13
        private BigDecimal settlementDiscountAmount; // maxlength 13

        @Digits(integer = 19, fraction = 9, message = "BAD_REQUEST_TOTAL_AMOUNT_AFTER_DISCOUNT_INVALID")
        private BigDecimal totalAmountAfterDiscount;
        @Digits(integer = 19, fraction = 9, message = "BAD_REQUEST_TOTAL_AMOUNT_AFTER_DISCOUNT_INVALID")
        private BigDecimal totalAmountBeforeDiscount;

        public SummarizeInfo() {
        }

        public BigDecimal getSettlementDiscountAmount() {
            return settlementDiscountAmount;
        }

        public void setSettlementDiscountAmount(BigDecimal settlementDiscountAmount) {
            this.settlementDiscountAmount = settlementDiscountAmount;
        }

        public BigDecimal getTotalAmountWithoutTax() {
            return totalAmountWithoutTax;
        }

        public void setTotalAmountWithoutTax(BigDecimal totalAmountWithoutTax) {
            this.totalAmountWithoutTax = totalAmountWithoutTax;
        }


        public BigDecimal getDiscountAmount() {
            return discountAmount;
        }

        public void setDiscountAmount(BigDecimal discountAmount) {
            this.discountAmount = discountAmount;
        }

        public BigDecimal getTotalTaxAmount() {
            return totalTaxAmount;
        }

        public void setTotalTaxAmount(BigDecimal totalTaxAmount) {
            this.totalTaxAmount = totalTaxAmount;
        }

        public BigDecimal getTotalAmountWithTax() {
            return totalAmountWithTax;
        }

        public void setTotalAmountWithTax(BigDecimal totalAmountWithTax) {
            this.totalAmountWithTax = totalAmountWithTax;
        }

        public BigDecimal getTotalAmountWithTaxFrn() {
            return totalAmountWithTaxFrn;
        }

        public void setTotalAmountWithTaxFrn(BigDecimal totalAmountWithTaxFrn) {
            this.totalAmountWithTaxFrn = totalAmountWithTaxFrn;
        }

        public String getTotalAmountWithTaxInWords() {
            return totalAmountWithTaxInWords;
        }

        public void setTotalAmountWithTaxInWords(String totalAmountWithTaxInWords) {
            this.totalAmountWithTaxInWords = totalAmountWithTaxInWords;
        }

        public BigDecimal getTotalAmountAfterDiscount() {
            return totalAmountAfterDiscount;
        }

        public void setTotalAmountAfterDiscount(BigDecimal totalAmountAfterDiscount) {
            this.totalAmountAfterDiscount = totalAmountAfterDiscount;
        }

        public BigDecimal getTotalAmountBeforeDiscount() {
            return totalAmountBeforeDiscount;
        }

        public void setTotalAmountBeforeDiscount(BigDecimal totalAmountBeforeDiscount) {
            this.totalAmountBeforeDiscount = totalAmountBeforeDiscount;
        }
    }


    public static class MetaDataInfo {

        private String keyTag;
        private String valueType; // text: 1, number: 2, date: 3
        private Long dateValue; // Date

        @Size(max = 13, message = "BAD_REQUEST_STRING_VALUE_MAX")
        private String stringValue;

        @Size(max = 6, message = "BAD_REQUEST_NUMBER_VALUE_MAX")
        private Long numberValue; // maxlength 6
        private String keyLabel;

        private Boolean isRequired; // boolean

        private Boolean isSeller; // boolean

        public MetaDataInfo() {
        }
        public String getKeyTag() {
            return keyTag;
        }

        public void setKeyTag(String keyTag) {
            this.keyTag = keyTag;
        }

        public String getValueType() {
            return valueType;
        }

        public void setValueType(String valueType) {
            this.valueType = valueType;
        }

        public Long getDateValue() {
            return dateValue;
        }

        public void setDateValue(Long dateValue) {
            this.dateValue = dateValue;
        }

        public String getStringValue() {
            return stringValue;
        }

        public void setStringValue(String stringValue) {
            this.stringValue = stringValue;
        }

        public Long getNumberValue() {
            return numberValue;
        }

        public void setNumberValue(Long numberValue) {
            this.numberValue = numberValue;
        }

        public String getKeyLabel() {
            return keyLabel;
        }

        public void setKeyLabel(String keyLabel) {
            this.keyLabel = keyLabel;
        }

        public Boolean getIsRequired() {
            return isRequired;
        }

        public void setIsRequired(Boolean isRequired) {
            this.isRequired = isRequired;
        }

        public Boolean getIsSeller() {
            return isSeller;
        }

        public void setIsSeller(Boolean isSeller) {
            this.isSeller = isSeller;
        }
    }

    public static class FuelReadingInfo {

        @Size(max = 50, message = "BAD_REQUEST_ID_LOG_MAX_LENGTH")
        private String idLog;

        @Size(max = 50, message = "BAD_REQUEST_PUMP_CODE_MAX_LENGTH")
        private String pumpCode;

        @Size(max = 50, message = "BAD_REQUEST_PUMP_NAME_MAX_LENGTH")
        private String pumpName;

        @Size(max = 50, message = "BAD_REQUEST_PRODUCT_CODE_MAX_LENGTH")
        private String productCode;

        @Size(max = 50, message = "BAD_REQUEST_PRODUCT_NAME_MAX_LENGTH")
        private String productName;


        private BigDecimal qtyLog;


        private BigDecimal priceLog;


        private BigDecimal thanhTienLog;

        private Long startDate;

        private Long endDate;
        @Size(max = 50, message = "BAD_REQUEST_BATCH_MAX_LENGTH")
        private String batch;
        @Size(max = 100, message = "BAD_REQUEST_NOTE_LOG_MAX_LENGTH")
        private String noteLog;

        public FuelReadingInfo() {
        }

        public String getIdLog() {
            return idLog;
        }

        public void setIdLog(String idLog) {
            this.idLog = idLog;
        }

        public String getPumpCode() {
            return pumpCode;
        }

        public void setPumpCode(String pumpCode) {
            this.pumpCode = pumpCode;
        }

        public String getPumpName() {
            return pumpName;
        }

        public void setPumpName(String pumpName) {
            this.pumpName = pumpName;
        }

        public String getProductCode() {
            return productCode;
        }

        public void setProductCode(String productCode) {
            this.productCode = productCode;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public BigDecimal getQtyLog() {
            return qtyLog;
        }

        public void setQtyLog(BigDecimal qtyLog) {
            this.qtyLog = qtyLog;
        }

        public BigDecimal getPriceLog() {
            return priceLog;
        }

        public void setPriceLog(BigDecimal priceLog) {
            this.priceLog = priceLog;
        }

        public BigDecimal getThanhTienLog() {
            return thanhTienLog;
        }

        public void setThanhTienLog(BigDecimal thanhTienLog) {
            this.thanhTienLog = thanhTienLog;
        }

        public Long getStartDate() {
            return startDate;
        }

        public void setStartDate(Long startDate) {
            this.startDate = startDate;
        }

        public Long getEndDate() {
            return endDate;
        }

        public void setEndDate(Long endDate) {
            this.endDate = endDate;
        }

        public String getBatch() {
            return batch;
        }

        public void setBatch(String batch) {
            this.batch = batch;
        }

        public String getNoteLog() {
            return noteLog;
        }

        public void setNoteLog(String noteLog) {
            this.noteLog = noteLog;
        }
    }
    public static class MeterReadingInfo {

        private String previousIndex;

        private String currentIndex;

        private String factor;

        private String amount; // (currentIndex - previousIndex) * factor

        private String meterName;

        public MeterReadingInfo() {
        }
        public String getPreviousIndex() {
            return previousIndex;
        }

        public void setPreviousIndex(String previousIndex) {
            this.previousIndex = previousIndex;
        }

        public String getCurrentIndex() {
            return currentIndex;
        }

        public void setCurrentIndex(String currentIndex) {
            this.currentIndex = currentIndex;
        }

        public String getFactor() {
            return factor;
        }

        public void setFactor(String factor) {
            this.factor = factor;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getMeterName() {
            return meterName;
        }

        public void setMeterName(String meterName) {
            this.meterName = meterName;
        }
    }

    public static class InvoiceFileInfo {

        private String fileContent;

        private Double fileType; // double

        private String fileExtension;

        public InvoiceFileInfo() {
        }

        public String getFileContent() {
            return fileContent;
        }

        public void setFileContent(String fileContent) {
            this.fileContent = fileContent;
        }

        public Double getFileType() {
            return fileType;
        }

        public void setFileType(Double fileType) {
            this.fileType = fileType;
        }

        public String getFileExtension() {
            return fileExtension;
        }

        public void setFileExtension(String fileExtension) {
            this.fileExtension = fileExtension;
        }
    }
    //

    public static class ConvertFontDTO {

        @NotNull(message = "BAD_REQUEST_CONVERT_FONT_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_CONVERT_FONT_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_CONVERT_FONT_REQUIRED")
        private String font;

        @NotNull(message = "BAD_REQUEST_CONVERT_FONT_DATA_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_CONVERT_FONT_DATA_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_CONVERT_FONT_DATA_REQUIRED")
        private String data;

        public ConvertFontDTO() {
        }

        public String getFont() {
            return font;
        }

        public void setFont(String font) {
            this.font = font;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }

    public static class CreateExchangeInvoiceFileWSDTO {

        @NotNull(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @Size(max = 20, message = "BAD_REQUEST_TAX_CODE_MAX_LENGTH")
        private String supplierTaxCode;

        @NotNull(message = "BAD_REQUEST_INVOICE_NO_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_INVOICE_NO_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_INVOICE_NO_REQUIRED")
        @Size(min = 7, message = "BAD_REQUEST_INVOICE_NO_MIN_LENGTH")
        @Size(max = 17, message = "BAD_REQUEST_INVOICE_NO_MAX_LENGTH")
        @Pattern(regexp = "^[a-zA-Z0-9/-]*$", message = "BAD_REQUEST_INVOICE_NO_INVALID")
        private String invoiceNo;

        @Size(max = 20, message = "BAD_REQUEST_TEMPLATE_CODE_MAX_LENGTH")
        private String templateCode;

        @NotNull(message = "BAD_REQUEST_ISSUE_DATE_REQUIRED")
        private Long strIssueDate;

        @NotNull(message = "BAD_REQUEST_EXCHANGE_USER_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_EXCHANGE_USER_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_EXCHANGE_USER_REQUIRED")
        @Size(max = 100, message = "BAD_REQUEST_EXCHANGE_USER_MAX_LENGTH")
        private String exchangeUser;

        public CreateExchangeInvoiceFileWSDTO() {
        }

        public String getSupplierTaxCode() {
            return supplierTaxCode;
        }

        public void setSupplierTaxCode(String supplierTaxCode) {
            this.supplierTaxCode = supplierTaxCode;
        }

        public String getInvoiceNo() {
            return invoiceNo;
        }

        public void setInvoiceNo(String invoiceNo) {
            this.invoiceNo = invoiceNo;
        }

        public Long getStrIssueDate() {
            return strIssueDate;
        }

        public void setStrIssueDate(Long strIssueDate) {
            this.strIssueDate = strIssueDate;
        }

        public String getExchangeUser() {
            return exchangeUser;
        }

        public void setExchangeUser(String exchangeUser) {
            this.exchangeUser = exchangeUser;
        }

        public String getTemplateCode() {
            return templateCode;
        }

        public void setTemplateCode(String templateCode) {
            this.templateCode = templateCode;
        }
    }

    @XmlRootElement(name = "getInvoiceFilePortalDTO")
    public static class GetInvoiceFilePortalDTO extends BaseDTO {
        @Size(max = 100, message = "BAD_REQUEST_BUYER_ID_NO_MAX_LENGTH_100")
        private String buyerIdNo;

        @NotNull(message = "BAD_REQUEST_RESERVATION_CODE_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_RESERVATION_CODE_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_RESERVATION_CODE_REQUIRED")
        @Size(max = 100, message = "BAD_REQUEST_RESERVATION_CODE_MAX_LENGTH")
        private String reservationCode;

        @NotNull(message = "BAD_REQUEST_FILE_TYPE_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_FILE_TYPE_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_FILE_TYPE_REQUIRED")
        @Size(max = 100, message = "BAD_REQUEST_FILE_TYPE_LENGTH_INVALID_100")
        private String fileType;

        @NotNull(message = "BAD_REQUEST_ISSUE_DATE_REQUIRED")
        private Long strIssueDate;

        public GetInvoiceFilePortalDTO() {
        }

        public String getBuyerIdNo() {
            return buyerIdNo;
        }

        public void setBuyerIdNo(String buyerIdNo) {
            this.buyerIdNo = buyerIdNo;
        }

        public String getReservationCode() {
            return reservationCode;
        }

        public void setReservationCode(String reservationCode) {
            this.reservationCode = reservationCode;
        }

        public String getFileType() {
            return fileType;
        }

        public void setFileType(String fileType) {
            this.fileType = fileType;
        }

        public Long getStrIssueDate() {
            return strIssueDate;
        }

        public void setStrIssueDate(Long strIssueDate) {
            this.strIssueDate = strIssueDate;
        }
    }

    @XmlRootElement(name = "commonDataInput")
    public static class GetInvoiceRepreDTO extends BaseDTO {
        @Size(min = 10, max = 36, message = "BAD_REQUEST_TRANSACTION_UUID_LENGTH_INVALID")
        private String transactionUuid;
        @Size(max = 3, min = 3, message = "BAD_REQUEST_FILE_TYPE_LENGTH_INVALID")
        private String fileType;

        private Boolean paid;

        private String pattern;

        public GetInvoiceRepreDTO() {
        }

        public String getTransactionUuid() {
            return transactionUuid;
        }

        public void setTransactionUuid(String transactionUuid) {
            this.transactionUuid = transactionUuid;
        }

        public String getFileType() {
            return fileType;
        }

        public void setFileType(String fileType) {
            this.fileType = fileType;
        }

        public Boolean getPaid() {
            return paid;
        }

        public void setPaid(Boolean paid) {
            this.paid = paid;
        }

        public String getPattern() {
            return pattern;
        }

        public void setPattern(String pattern) {
            this.pattern = pattern;
        }
    }

    public static class SearchByTransUUIDDTO {
        @NotNull(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @Size(max = 20, message = "BAD_REQUEST_TAX_CODE_MAX_LENGTH")
        private String supplierTaxCode;

        @NotNull(message = "BAD_REQUEST_TRANSACTION_UUID_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_TRANSACTION_UUID_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_TRANSACTION_UUID_REQUIRED")
        @Size(min = 10, max = 36, message = "BAD_REQUEST_TRANSACTION_UUID_LENGTH_INVALID")
        private String transactionUuid;

        public SearchByTransUUIDDTO() {
        }

        public String getSupplierTaxCode() {
            return supplierTaxCode;
        }

        public void setSupplierTaxCode(String supplierTaxCode) {
            this.supplierTaxCode = supplierTaxCode;
        }

        public String getTransactionUuid() {
            return transactionUuid;
        }

        public void setTransactionUuid(String transactionUuid) {
            this.transactionUuid = transactionUuid;
        }
    }

    public static class AfterSignInvoiceUSB {
        @NotNull(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @Size(max = 20, message = "BAD_REQUEST_TAX_CODE_MAX_LENGTH")
        private String supplierTaxCode;
        @Size(max = 20, message = "BAD_REQUEST_TEMPLATE_CODE_MAX_LENGTH")
        @NotNull(message = "BAD_REQUEST_TEMPLATE_CODE_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_TEMPLATE_CODE_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_TEMPLATE_CODE_REQUIRED")
        private String templateCode;
        @NotNull(message = "BAD_REQUEST_HAS_STRING_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_HAS_STRING_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_HAS_STRING_REQUIRED")
        private String hashString;
        @NotNull(message = "BAD_REQUEST_SIGNATURE_VALUE_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_SIGNATURE_VALUE_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_SIGNATURE_VALUE_REQUIRED")
        private String signature;

        public AfterSignInvoiceUSB() {
        }

        public String getSupplierTaxCode() {
            return supplierTaxCode;
        }

        public void setSupplierTaxCode(String supplierTaxCode) {
            this.supplierTaxCode = supplierTaxCode;
        }

        public String getTemplateCode() {
            return templateCode;
        }

        public void setTemplateCode(String templateCode) {
            this.templateCode = templateCode;
        }

        public String getHashString() {
            return hashString;
        }

        public void setHashString(String hashString) {
            this.hashString = hashString;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }
    }

    @XmlRootElement(name = "listCommonInvoiceInput")
    public static class CreateMultiInvoice {
        private List<@Valid CreateInvoiceWSDTO> commonInvoiceInputs;

        public List<CreateInvoiceWSDTO> getCommonInvoiceInputs() {
            return commonInvoiceInputs;
        }

        public void setCommonInvoiceInputs(List<CreateInvoiceWSDTO> commonInvoiceInputs) {
            this.commonInvoiceInputs = commonInvoiceInputs;
        }
    }

    public static class QrCodeInfo {

        @NotNull
        @Min(value = 0, message = "BAD_REQUEST_TOTAL_SCAN_MIN")
        @Max(value = 99, message = "BAD_REQUEST_TOTAL_SCAN_MAX")
        private Integer totalScan;

        @NotNull
        @Min(value = 0, message = "BAD_REQUEST_REMAIN_SCAN_MIN")
        @Max(value = 99, message = "BAD_REQUEST_REMAIN_SCAN_MAX")
        private Integer remainScan;

        @NotNull
        private Long startDateQrcode;

        @NotNull
        private Long endDateQrcode;

        @Size(max = 200, message = "BAD_REQUEST_TEMPOS_TYPE_OVER_LENGTH")
        private String temposType;


        public Integer getTotalScan() {
            return totalScan;
        }

        public void setTotalScan(Integer totalScan) {
            this.totalScan = totalScan;
        }

        public Integer getRemainScan() {
            return remainScan;
        }

        public void setRemainScan(Integer remainScan) {
            this.remainScan = remainScan;
        }

        public Long getStartDateQrcode() {
            return startDateQrcode;
        }

        public void setStartDateQrcode(Long startDateQrcode) {
            this.startDateQrcode = startDateQrcode;
        }

        public Long getEndDateQrcode() {
            return endDateQrcode;
        }

        public void setEndDateQrcode(Long endDateQrcode) {
            this.endDateQrcode = endDateQrcode;
        }

        public String getTemposType() {
            return temposType;
        }

        public void setTemposType(String temposType) {
            this.temposType = temposType;
        }

        @Override
        public String toString() {
            return "QrCodeInfo{" +
                "totalScan=" + totalScan +
                ", remainScan=" + remainScan +
                ", startDateQrcode=" + startDateQrcode +
                ", endDateQrcode=" + endDateQrcode +
                '}';
        }
    }

    public static class QrCodeInfoDto {

        @NotNull
        private String taxCode;

        @NotNull
        private String templateCode;

        @NotNull
        private String invoiceNo;

        @Min(value = 0, message = "BAD_REQUEST_PRINT_STATUS_VALUE")
        @Max(value = 1, message = "BAD_REQUEST_PRINT_STATUS_VALUE")
        private Integer printStatus;

        public String getTaxCode() {
            return taxCode;
        }

        public void setTaxCode(String taxCode) {
            this.taxCode = taxCode;
        }

        public String getTemplateCode() {
            return templateCode;
        }

        public void setTemplateCode(String templateCode) {
            this.templateCode = templateCode;
        }

        public String getInvoiceNo() {
            return invoiceNo;
        }

        public void setInvoiceNo(String invoiceNo) {
            this.invoiceNo = invoiceNo;
        }

        public Integer getPrintStatus() {
            return printStatus;
        }

        public void setPrintStatus(Integer printStatus) {
            this.printStatus = printStatus;
        }
    }
    public static class CreateExchangeInvoiceFileWSV1DTO {

        @NotNull(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @Size(max = 20, message = "BAD_REQUEST_TAX_CODE_MAX_LENGTH")
        private String supplierTaxCode;

        @NotNull(message = "BAD_REQUEST_INVOICE_NO_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_INVOICE_NO_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_INVOICE_NO_REQUIRED")
        @Size(min = 7, message = "BAD_REQUEST_INVOICE_NO_MIN_LENGTH")
        @Size(max = 17, message = "BAD_REQUEST_INVOICE_NO_MAX_LENGTH")
        @Pattern(regexp = "([a-zA-Z0-9/-]+|( [a-zA-Z0-9]*$))", message = "BAD_REQUEST_INVOICE_NO_INVALID")
        private String invoiceNo;

        @Size(max = 20, message = "BAD_REQUEST_TEMPLATE_CODE_MAX_LENGTH")
        private String templateCode;

        @NotNull(message = "BAD_REQUEST_ISSUE_DATE_REQUIRED")
        private String strIssueDate;

        @NotNull(message = "BAD_REQUEST_EXCHANGE_USER_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_EXCHANGE_USER_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_EXCHANGE_USER_REQUIRED")
        @Size(max = 100, message = "BAD_REQUEST_EXCHANGE_USER_MAX_LENGTH")
        private String exchangeUser;

        public CreateExchangeInvoiceFileWSV1DTO() {
        }


        public String getSupplierTaxCode() {
            return supplierTaxCode;
        }

        public void setSupplierTaxCode(String supplierTaxCode) {
            this.supplierTaxCode = supplierTaxCode;
        }

        public String getInvoiceNo() {
            return invoiceNo;
        }

        public void setInvoiceNo(String invoiceNo) {
            this.invoiceNo = invoiceNo;
        }

        public String getStrIssueDate() {
            return strIssueDate;
        }

        public void setStrIssueDate(String strIssueDate) {
            this.strIssueDate = strIssueDate;
        }

        public String getExchangeUser() {
            return exchangeUser;
        }

        public void setExchangeUser(String exchangeUser) {
            this.exchangeUser = exchangeUser;
        }

        public String getTemplateCode() {
            return templateCode;
        }

        public void setTemplateCode(String templateCode) {
            this.templateCode = templateCode;
        }
    }

    public static class CancelPaymentWSV1DTO {

        @NotNull(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_TAX_CODE_REQUIRED")
        @Size(max = 20, message = "BAD_REQUEST_TAX_CODE_MAX_LENGTH")
        private String supplierTaxCode;

        @NotNull(message = "BAD_REQUEST_INVOICE_NO_REQUIRED")
        @NotBlank(message = "BAD_REQUEST_INVOICE_NO_REQUIRED")
        @NotEmpty(message = "BAD_REQUEST_INVOICE_NO_REQUIRED")
        @Size(min = 7, message = "BAD_REQUEST_INVOICE_NO_MIN_LENGTH")
        @Size(max = 17, message = "BAD_REQUEST_INVOICE_NO_MAX_LENGTH")
        @Pattern(regexp = "([a-zA-Z0-9/-]+|( [a-zA-Z0-9]*$))", message = "BAD_REQUEST_INVOICE_NO_INVALID")
        private String invoiceNo;

        @NotNull(message = "BAD_REQUEST_ISSUE_DATE_REQUIRED")
        private String strIssueDate;

        public CancelPaymentWSV1DTO() {
        }
        public String getSupplierTaxCode() {
            return supplierTaxCode;
        }

        public void setSupplierTaxCode(String supplierTaxCode) {
            this.supplierTaxCode = supplierTaxCode;
        }

        public String getStrIssueDate() {
            return strIssueDate;
        }

        public void setStrIssueDate(String strIssueDate) {
            this.strIssueDate = strIssueDate;
        }

        public String getInvoiceNo() {
            return invoiceNo;
        }

        public void setInvoiceNo(String invoiceNo) {
            this.invoiceNo = invoiceNo;
        }
    }

    public static class UpdateInvoiceExplanationDTO {

        private String supplierTaxCode;

        private String templateCode;

        private String invoiceNo;

        private Long strIssueDate;

        private String reason;

        public String getSupplierTaxCode() {
            return supplierTaxCode;
        }

        public void setSupplierTaxCode(String supplierTaxCode) {
            this.supplierTaxCode = supplierTaxCode;
        }

        public String getTemplateCode() {
            return templateCode;
        }

        public void setTemplateCode(String templateCode) {
            this.templateCode = templateCode;
        }

        public String getInvoiceNo() {
            return invoiceNo;
        }

        public void setInvoiceNo(String invoiceNo) {
            this.invoiceNo = invoiceNo;
        }

        public Long getStrIssueDate() {
            return strIssueDate;
        }

        public void setStrIssueDate(Long strIssueDate) {
            this.strIssueDate = strIssueDate;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }
}
