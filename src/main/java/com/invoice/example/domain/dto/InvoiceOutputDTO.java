package com.invoice.example.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class InvoiceOutputDTO {

    @XmlRootElement
    public static class BaseOutputDTO {
        private Integer errorCode;
        private String description;

        public Integer getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(Integer errorCode) {
            this.errorCode = errorCode;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }


    @XmlRootElement(name = "getInvoiceFilePortalResp")
    public static class GetInvoiceFilePortalResp extends BaseOutputDTO {
        private byte[] fileToBytes;
        private boolean paymentStatus;
        private String fileName;

        public GetInvoiceFilePortalResp() {
        }

        public byte[] getFileToBytes() {
            return fileToBytes;
        }

        public void setFileToBytes(byte[] fileToBytes) {
            this.fileToBytes = fileToBytes;
        }

        public boolean isPaymentStatus() {
            return paymentStatus;
        }

        public void setPaymentStatus(boolean paymentStatus) {
            this.paymentStatus = paymentStatus;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }
    }

    public static class UpdateTaxResp extends BaseOutputDTO {
        private String status;
        private int record;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getRecord() {
            return record;
        }

        public void setRecord(int record) {
            this.record = record;
        }
    }

    public static class UpdatePaymentResp extends BaseOutputDTO {
        private Boolean result;
        private Long paymentTime;
        private String paymentMethod;

        public Boolean getResult() {
            return result;
        }

        public void setResult(Boolean result) {
            this.result = result;
        }

        public Long getPaymentTime() {
            return paymentTime;
        }

        public void setPaymentTime(Long paymentTime) {
            this.paymentTime = paymentTime;
        }

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }
    }

    @XmlRootElement(name = "invoicesOutput")
    public static class SearchInvoiceResp extends BaseOutputDTO {
        private Long totalRows;
        private List<invoicesOutput> invoices;

        public Long getTotalRows() {
            return totalRows;
        }

        public void setTotalRows(Long totalRows) {
            this.totalRows = totalRows;
        }

        public List<invoicesOutput> getInvoices() {
            return invoices;
        }

        public void setInvoices(List<invoicesOutput> invoices) {
            this.invoices = invoices;
        }
    }

    @XmlRootElement(name = "createInvoiceOutput")
    public static class CreateDraftResp {
        private String errorCode;
        private String description;
        private EmptyResult result;

        public EmptyResult getResult() {
            return result;
        }

        public void setResult(EmptyResult result) {
            this.result = result;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    @JsonSerialize
    public static class EmptyResult {

    }

    public static class CreateInvoiceDTO {
        private String supplierTaxCode;
        private String invoiceNo;
        private String transactionID;
        private String reservationCode;
        private Long invoiceId;
        private String codeOfTax;

        public String getCodeOfTax() {
            return codeOfTax;
        }

        public void setCodeOfTax(String codeOfTax) {
            this.codeOfTax = codeOfTax;
        }

        @JsonIgnore
        public Long getInvoiceId() {
            return invoiceId;
        }

        public void setInvoiceId(Long invoiceId) {
            this.invoiceId = invoiceId;
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

        public String getTransactionID() {
            return transactionID;
        }

        public void setTransactionID(String transactionID) {
            this.transactionID = transactionID;
        }

        public String getReservationCode() {
            return reservationCode;
        }

        public void setReservationCode(String reservationCode) {
            this.reservationCode = reservationCode;
        }
    }

    public static class CreateInvoiceDTOQrCode {
        private String supplierTaxCode;
        private String invoiceNo;
        private String transactionID;
        private String reservationCode;
        private Long invoiceId;
        private String codeOfTax;

        private String qrCode;

        public String getCodeOfTax() {
            return codeOfTax;
        }

        public void setCodeOfTax(String codeOfTax) {
            this.codeOfTax = codeOfTax;
        }

        @JsonIgnore
        public Long getInvoiceId() {
            return invoiceId;
        }

        public void setInvoiceId(Long invoiceId) {
            this.invoiceId = invoiceId;
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

        public String getTransactionID() {
            return transactionID;
        }

        public void setTransactionID(String transactionID) {
            this.transactionID = transactionID;
        }

        public String getReservationCode() {
            return reservationCode;
        }

        public void setReservationCode(String reservationCode) {
            this.reservationCode = reservationCode;
        }

        public String getQrCode() {
            return qrCode;
        }

        public void setQrCode(String qrCode) {
            this.qrCode = qrCode;
        }
    }

    public static class CreateMultiInvoiceDTO {
        private List<CreateMultiInvoiceDTOInner> createInvoiceOutputs;
        private List<Map<String, String>> lstMapError;
        private Long totalSuccess;
        private Long totalFail;

        public List<Map<String, String>> getLstMapError() {
            return lstMapError;
        }

        public void setLstMapError(List<Map<String, String>> lstMapError) {
            this.lstMapError = lstMapError;
        }

        public Long getTotalSuccess() {
            return totalSuccess;
        }

        public void setTotalSuccess(Long totalSuccess) {
            this.totalSuccess = totalSuccess;
        }

        public Long getTotalFail() {
            return totalFail;
        }

        public void setTotalFail(Long totalFail) {
            this.totalFail = totalFail;
        }

        public List<CreateMultiInvoiceDTOInner> getCreateInvoiceOutputs() {
            return createInvoiceOutputs;
        }

        public void setCreateInvoiceOutputs(List<CreateMultiInvoiceDTOInner> createInvoiceOutputs) {
            this.createInvoiceOutputs = createInvoiceOutputs;
        }
    }

    public static class CreateMultiInvoiceDTOInner {
        private String transactionUuid;
        private Integer errorCode;
        private String description;
        private CreateInvoiceDTO result;


        public CreateMultiInvoiceDTOInner() {
        }

        public String getTransactionUuid() {
            return transactionUuid;
        }

        public void setTransactionUuid(String transactionUuid) {
            this.transactionUuid = transactionUuid;
        }

        public Integer getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(Integer errorCode) {
            this.errorCode = errorCode;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public CreateInvoiceDTO getResult() {
            return result;
        }

        public void setResult(CreateInvoiceDTO result) {
            this.result = result;
        }

//        public List<Map<String, String>> getLstMapError() {
//            return lstMapError;
//        }
//
//        public void setLstMapError(List<Map<String, String>> lstMapError) {
//            this.lstMapError = lstMapError;
//        }
//
//        public Long getTotalSuccess() {
//            return totalSuccess;
//        }
//
//        public void setTotalSuccess(Long totalSuccess) {
//            this.totalSuccess = totalSuccess;
//        }
//
//        public Long getTotalFail() {
//            return totalFail;
//        }
//
//        public void setTotalFail(Long totalFail) {
//            this.totalFail = totalFail;
//        }
    }

    @XmlRootElement(name = "createInvoiceOutput")
    public static class CreateInvoiceResp extends BaseOutputDTO {
        private CreateInvoiceDTO result;

        public CreateInvoiceDTO getResult() {
            return result;
        }

        public void setResult(CreateInvoiceDTO result) {
            this.result = result;
        }

    }

    @XmlRootElement(name = "createInvoiceQrCodeOutput")
    public static class CreateInvoiceQRResp extends BaseOutputDTO {
        private CreateInvoiceDTOQrCode result;

        public CreateInvoiceDTOQrCode getResult() {
            return result;
        }

        public void setResult(CreateInvoiceDTOQrCode result) {
            this.result = result;
        }
    }

    public static class CustomFieldDTO {
        private Long id;

        private Long invoiceTemplatePrototypeId;

        private String keyTag;

        private String valueType;

        private String keyLabel;

        @JsonProperty("isRequired")
        @XmlElement(name = "isRequired")
        private Boolean isRequired;

        @JsonProperty("isSeller")
        @XmlElement(name = "isSeller")
        private Boolean isSeller;

        public CustomFieldDTO() {
        }

        ;

        public CustomFieldDTO(String keyTag, String valueType, String keyLabel, Boolean isRequired, Boolean isSeller, Long invoiceTemplatePrototypeId) {
            this.keyLabel = keyLabel;
            this.keyTag = keyTag;
            this.valueType = valueType;
            this.isRequired = isRequired;
            this.isSeller = isSeller;
            this.invoiceTemplatePrototypeId = invoiceTemplatePrototypeId;
            if (valueType != null) {
                if ("1".equals(valueType.trim())) {
                    this.valueType = "text";
                }
                if ("2".equals(valueType.trim())) {
                    this.valueType = "number";
                }
                if ("3".equals(valueType.trim())) {
                    this.valueType = "date";
                }
            }
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getInvoiceTemplatePrototypeId() {
            return invoiceTemplatePrototypeId;
        }

        public void setInvoiceTemplatePrototypeId(Long invoiceTemplatePrototypeId) {
            this.invoiceTemplatePrototypeId = invoiceTemplatePrototypeId;
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

        public String getKeyLabel() {
            return keyLabel;
        }

        public void setKeyLabel(String keyLabel) {
            this.keyLabel = keyLabel;
        }

        public Boolean getIsRequired() {
            return isRequired;
        }

        public void setIsRequired(Boolean required) {
            isRequired = required;
        }

        public Boolean getIsSeller() {
            return isSeller;
        }

        public void setIsSeller(Boolean seller) {
            isSeller = seller;
        }
    }

    public static class CustomFieldsResp extends BaseOutputDTO {

        private List<CustomFieldDTO> customFields;

        public List<CustomFieldDTO> getCustomFields() {
            return customFields;
        }

        public void setCustomFields(List<CustomFieldDTO> customFields) {
            this.customFields = customFields;
        }
    }

    public static class UsingInvoiceResp extends BaseOutputDTO {

        private Integer status;
        private Long numOfpublishInv;
        private Long totalInv;

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Long getNumOfpublishInv() {
            return numOfpublishInv;
        }

        public void setNumOfpublishInv(Long numOfpublishInv) {
            this.numOfpublishInv = numOfpublishInv;
        }

        public Long getTotalInv() {
            return totalInv;
        }

        public void setTotalInv(Long totalInv) {
            this.totalInv = totalInv;
        }
    }

    public static class ConvertFontResp extends BaseOutputDTO {

        private String result;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }


    public static class SearchUUIDResp extends BaseOutputDTO {
        private String transactionUuid;
        private List<SearchUUIDInvoiceResult> result;

        public String getTransactionUuid() {
            return transactionUuid;
        }

        public void setTransactionUuid(String transactionUuid) {
            this.transactionUuid = transactionUuid;
        }

        public List<SearchUUIDInvoiceResult> getResult() {
            return result;
        }

        public void setResult(List<SearchUUIDInvoiceResult> result) {
            this.result = result;
        }
    }

    public static class SearchUUIDRespTT78 extends BaseOutputDTO {
        private String transactionUuid;
        private List<SearchUUIDInvoiceResultTT78> result;

        public String getTransactionUuid() {
            return transactionUuid;
        }

        public void setTransactionUuid(String transactionUuid) {
            this.transactionUuid = transactionUuid;
        }

        public List<SearchUUIDInvoiceResultTT78> getResult() {
            return result;
        }

        public void setResult(List<SearchUUIDInvoiceResultTT78> result) {
            this.result = result;
        }
    }


    public static class SearchUUIDInvoiceResult implements Serializable {
        private String supplierTaxCode;
        private String invoiceNo;
        private String reservationCode;
        private Long issueDate;
        private String status;
        private String exchangeStatus;

        public SearchUUIDInvoiceResult() {
        }

        public SearchUUIDInvoiceResult(String supplierTaxCode, String invoiceNo, String reservationCode, Long issueDate, String status, String exchangeStatus) {
            this.supplierTaxCode = supplierTaxCode;
            this.invoiceNo = invoiceNo;
            this.reservationCode = reservationCode;
            this.issueDate = issueDate;
            this.status = status;
            this.exchangeStatus = exchangeStatus;
        }

        public String getExchangeStatus() {
            return exchangeStatus;
        }

        public void setExchangeStatus(String exchangeStatus) {
            this.exchangeStatus = exchangeStatus;
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

        public String getReservationCode() {
            return reservationCode;
        }

        public void setReservationCode(String reservationCode) {
            this.reservationCode = reservationCode;
        }

        public Long getIssueDate() {
            return issueDate;
        }

        public void setIssueDate(Long issueDate) {
            this.issueDate = issueDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

    }

    public static class SearchUUIDInvoiceResultTT78 extends SearchUUIDInvoiceResult {
        private String exchangeStatus;
        private String exchangeDes;
        private String codeOfTax;

        public String getExchangeStatus() {
            return exchangeStatus;
        }

        public void setExchangeStatus(String exchangeStatus) {
            this.exchangeStatus = exchangeStatus;
        }

        public String getExchangeDes() {
            return exchangeDes;
        }

        public void setExchangeDes(String exchangeDes) {
            this.exchangeDes = exchangeDes;
        }

        public String getCodeOfTax() {
            return codeOfTax;
        }

        public void setCodeOfTax(String codeOfTax) {
            this.codeOfTax = codeOfTax;
        }

        @Override
        public String toString() {
            return "SearchUUIDInvoiceResultTT78{" +
                "exchangeStatus='" + exchangeStatus + '\'' +
                ", exchangeDes='" + exchangeDes + '\'' +
                ", codeOfTax='" + codeOfTax + '\'' +
                '}';
        }
    }

    public static class HashResultResp extends BaseOutputDTO {
        private Results result;

        public Results getResult() {
            return result;
        }

        public void setResult(Results result) {
            this.result = result;
        }
    }

    public static class Results {
        private String hashString;

        public String getHashString() {
            return hashString;
        }

        public void setHashString(String hashString) {
            this.hashString = hashString;
        }
    }

    //    @XmlRootElement (name="invoicesOutput")
    public static class invoicesOutput implements Serializable {

        private Long invoiceId;

        private String invoiceType;

        private String adjustmentType;

        private String templateCode;

        private String invoiceSeri;

        private String invoiceNumber; //

        private String invoiceNo;

        private String currency;

        private BigDecimal total;

        private Long issueDate;

        // json
        private String issueDateStr; // x

        private Integer state;

        private Long requestDate; // x

        private String description; // x

        private String buyerIdNo;

        private Integer stateCode; // x

        private Long subscriberNumber; // x

        private Integer paymentStatus;

        private Integer viewStatus;

        private Integer downloadStatus;

        private Integer exchangeStatus;

        private Integer numOfExchange;

        private Long createTime;

        private Long contractId;

        private String contractNo;

        private String supplierTaxCode;

        private String buyerTaxCode;

        private BigDecimal totalBeforeTax;

        private BigDecimal taxAmount;

        private String taxRate; // x

        private String paymentMethod;

        private Long paymentTime;

        private Long customerId; // x

        private String no; // x

        private String paymentStatusName;

        // xml
        private String buyerName;

        private String transactionUuid;

        private String originalInvoiceId;

        public String getTransactionUuid() {
            return transactionUuid;
        }

        public void setTransactionUuid(String transactionUuid) {
            this.transactionUuid = transactionUuid;
        }

        public String getAdjustmentType() {
            return adjustmentType;
        }

        public void setAdjustmentType(String adjustmentType) {
            this.adjustmentType = adjustmentType;
        }

        public String getBuyerName() {
            return buyerName;
        }

        public void setBuyerName(String buyerName) {
            this.buyerName = buyerName;
        }

        public String getBuyerTaxCode() {
            return buyerTaxCode;
        }

        public void setBuyerTaxCode(String buyerTaxCode) {
            this.buyerTaxCode = buyerTaxCode;
        }

        public Long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Long createTime) {
            this.createTime = createTime;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public Long getInvoiceId() {
            return invoiceId;
        }

        public void setInvoiceId(Long invoiceId) {
            this.invoiceId = invoiceId;
        }

        public String getInvoiceNo() {
            return invoiceNo;
        }

        public void setInvoiceNo(String invoiceNo) {
            this.invoiceNo = invoiceNo;
        }

        public String getInvoiceNumber() {
            return invoiceNumber;
        }

        public void setInvoiceNumber(String invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
        }

        public String getInvoiceSeri() {
            return invoiceSeri;
        }

        public void setInvoiceSeri(String invoiceSeri) {
            this.invoiceSeri = invoiceSeri;
        }

        public String getInvoiceType() {
            return invoiceType;
        }

        public void setInvoiceType(String invoiceType) {
            this.invoiceType = invoiceType;
        }

        public Long getIssueDate() {
            return issueDate;
        }

        public void setIssueDate(Long issueDate) {
            this.issueDate = issueDate;
        }

        public Integer getPaymentStatus() {
            return paymentStatus;
        }

        public void setPaymentStatus(Integer paymentStatus) {
            this.paymentStatus = paymentStatus;
        }

        public String getSupplierTaxCode() {
            return supplierTaxCode;
        }

        public void setSupplierTaxCode(String supplierTaxCode) {
            this.supplierTaxCode = supplierTaxCode;
        }

        public BigDecimal getTaxAmount() {
            return taxAmount;
        }

        public void setTaxAmount(BigDecimal taxAmount) {
            this.taxAmount = taxAmount;
        }

        public String getTemplateCode() {
            return templateCode;
        }

        public void setTemplateCode(String templateCode) {
            this.templateCode = templateCode;
        }

        public BigDecimal getTotal() {
            return total;
        }

        public void setTotal(BigDecimal total) {
            this.total = total;
        }

        public BigDecimal getTotalBeforeTax() {
            return totalBeforeTax;
        }

        public void setTotalBeforeTax(BigDecimal totalBeforeTax) {
            this.totalBeforeTax = totalBeforeTax;
        }

        public Integer getViewStatus() {
            return viewStatus;
        }

        public void setViewStatus(Integer viewStatus) {
            this.viewStatus = viewStatus;
        }

        public String getIssueDateStr() {
            return issueDateStr;
        }

        public void setIssueDateStr(String issueDateStr) {
            this.issueDateStr = issueDateStr;
        }

        public Integer getState() {
            return state;
        }

        public void setState(Integer state) {
            this.state = state;
        }

        public Long getRequestDate() {
            return requestDate;
        }

        public void setRequestDate(Long requestDate) {
            this.requestDate = requestDate;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getBuyerIdNo() {
            return buyerIdNo;
        }

        public void setBuyerIdNo(String buyerIdNo) {
            this.buyerIdNo = buyerIdNo;
        }

        public Integer getStateCode() {
            return stateCode;
        }

        public void setStateCode(Integer stateCode) {
            this.stateCode = stateCode;
        }

        public Long getSubscriberNumber() {
            return subscriberNumber;
        }

        public void setSubscriberNumber(Long subscriberNumber) {
            this.subscriberNumber = subscriberNumber;
        }

        public Integer getDownloadStatus() {
            return downloadStatus;
        }

        public void setDownloadStatus(Integer downloadStatus) {
            this.downloadStatus = downloadStatus;
        }

        public Integer getExchangeStatus() {
            return exchangeStatus;
        }

        public void setExchangeStatus(Integer exchangeStatus) {
            this.exchangeStatus = exchangeStatus;
        }

        public Integer getNumOfExchange() {
            return numOfExchange;
        }

        public void setNumOfExchange(Integer numOfExchange) {
            this.numOfExchange = numOfExchange;
        }

        public Long getContractId() {
            return contractId;
        }

        public void setContractId(Long contractId) {
            this.contractId = contractId;
        }

        public String getContractNo() {
            return contractNo;
        }

        public void setContractNo(String contractNo) {
            this.contractNo = contractNo;
        }

        public String getTaxRate() {
            return taxRate;
        }

        public void setTaxRate(String taxRate) {
            this.taxRate = taxRate;
        }

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public Long getPaymentTime() {
            return paymentTime;
        }

        public void setPaymentTime(Long paymentTime) {
            this.paymentTime = paymentTime;
        }

        public Long getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Long customerId) {
            this.customerId = customerId;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getPaymentStatusName() {
            return paymentStatusName;
        }

        public void setPaymentStatusName(String paymentStatusName) {
            this.paymentStatusName = paymentStatusName;
        }

        public String getOriginalInvoiceId() {
            return originalInvoiceId;
        }

        public void setOriginalInvoiceId(String originalInvoiceId) {
            this.originalInvoiceId = originalInvoiceId;
        }

        public invoicesOutput() {

        }

        public invoicesOutput(String adjustmentType, String buyerName, String buyerTaxCode, Long createTime, String currency, Long invoiceId, String invoiceNo, String invoiceNumber, String invoiceSeri, String invoiceType, Long issueDate, Integer paymentStatus, String supplierTaxCode, BigDecimal taxAmount, String templateCode, BigDecimal total, BigDecimal totalBeforeTax, Integer viewStatus, String issueDateStr, Integer state, Long requestDate, String description, String buyerIdNo, Integer stateCode, Long subscriberNumber, Integer downloadStatus, Integer exchangeStatus, Integer numOfExchange, Long contractId, String contractNo, String taxRate, String paymentMethod, Long paymentTime, Long customerId, String no, String paymentStatusName, String originalInvoiceNo) {
            this.adjustmentType = adjustmentType;
            this.buyerName = buyerName;
            this.buyerTaxCode = buyerTaxCode;
            this.createTime = createTime;
            this.currency = currency;
            this.invoiceId = invoiceId;
            this.invoiceNo = invoiceNo;
            this.invoiceNumber = invoiceNumber;
            this.invoiceSeri = invoiceSeri;
            this.invoiceType = invoiceType;
            this.issueDate = issueDate;
            this.paymentStatus = paymentStatus;
            this.supplierTaxCode = supplierTaxCode;
            this.taxAmount = taxAmount;
            this.templateCode = templateCode;
            this.total = total;
            this.totalBeforeTax = totalBeforeTax;
            this.viewStatus = viewStatus;
            this.issueDateStr = issueDateStr;
            this.state = state;
            this.requestDate = requestDate;
            this.description = description;
            this.buyerIdNo = buyerIdNo;
            this.stateCode = stateCode;
            this.subscriberNumber = subscriberNumber;
            this.downloadStatus = downloadStatus;
            this.exchangeStatus = exchangeStatus;
            this.numOfExchange = numOfExchange;
            this.contractId = contractId;
            this.contractNo = contractNo;
            this.taxRate = taxRate;
            this.paymentMethod = paymentMethod;
            this.paymentTime = paymentTime;
            this.customerId = customerId;
            this.no = no;
            this.paymentStatusName = paymentStatusName;
            this.originalInvoiceId = originalInvoiceNo;

        }
    }

    public static class SearchAllUUIDResp extends BaseOutputDTO {
        private String transactionUuid;
        private List<SearchAllUUIDInvoiceResult> result;

        public String getTransactionUuid() {
            return transactionUuid;
        }

        public void setTransactionUuid(String transactionUuid) {
            this.transactionUuid = transactionUuid;
        }

        public List<SearchAllUUIDInvoiceResult> getResult() {
            return result;
        }

        public void setResult(List<SearchAllUUIDInvoiceResult> result) {
            this.result = result;
        }
    }

    public static class SearchAllUUIDInvoiceResult {
        private String supplierTaxCode;
        private String serial;
        private String invoiceNo;
        private Long issueDate;
        private Integer invoiceStatus;
        private String adjustmentType;
        private String adjustmentInvoiceType;
        //private String status;

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

        public Long getIssueDate() {
            return issueDate;
        }

        public void setIssueDate(Long issueDate) {
            this.issueDate = issueDate;
        }

        public Integer getInvoiceStatus() {
            return invoiceStatus;
        }

        public void setInvoiceStatus(Integer invoiceStatus) {
            this.invoiceStatus = invoiceStatus;
        }

        public String getSerial() {
            return serial;
        }

        public void setSerial(String serial) {
            this.serial = serial;
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

        /*public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }*/
    }

    public static class CancelDraftResp extends BaseOutputDTO {
        private String transactionUuid;
        private List<CancelDraftInvoiceResult> result;

        public String getTransactionUuid() {
            return transactionUuid;
        }

        public void setTransactionUuid(String transactionUuid) {
            this.transactionUuid = transactionUuid;
        }

        public List<CancelDraftInvoiceResult> getResult() {
            return result;
        }

        public void setResult(List<CancelDraftInvoiceResult> result) {
            this.result = result;
        }
    }

    public static class CancelDraftInvoiceResult {
        private String supplierTaxCode;
        private String serial;
        private String invoiceNo;
        private Long issueDate;
        private Integer invoiceStatus;
        private String adjustmentType;
        private String adjustmentInvoiceType;
        private String invoiceTemplate;
        //private String status;

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

        public Long getIssueDate() {
            return issueDate;
        }

        public void setIssueDate(Long issueDate) {
            this.issueDate = issueDate;
        }

        public Integer getInvoiceStatus() {
            return invoiceStatus;
        }

        public void setInvoiceStatus(Integer invoiceStatus) {
            this.invoiceStatus = invoiceStatus;
        }

        public String getSerial() {
            return serial;
        }

        public void setSerial(String serial) {
            this.serial = serial;
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

        /*public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }*/
        public String getInvoiceTemplate() {
            return invoiceTemplate;
        }

        public void setInvoiceTemplate(String invoiceTemplate) {
            this.invoiceTemplate = invoiceTemplate;
        }
    }

    public static class InvoicesAll extends invoicesOutput {
        private String listProduct;

        private String fileName;

        private String buyerUnitName;

        private String buyerCode;

        private String buyerAddress;

        private BigDecimal exchangeRate;

        private String listInfoUpdate;

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public InvoicesAll() {
            super();
        }

        public InvoicesAll(String adjustmentType, String buyerName, String buyerTaxCode, Long createTime, String currency,
                           Long invoiceId, String invoiceNo, String invoiceNumber, String invoiceSeri, String invoiceType,
                           Long issueDate, Integer paymentStatus, String supplierTaxCode, BigDecimal taxAmount, String templateCode,
                           BigDecimal total, BigDecimal totalBeforeTax, Integer viewStatus, String issueDateStr, Integer state,
                           Long requestDate, String description, String buyerIdNo, Integer stateCode, Long subscriberNumber,
                           Integer downloadStatus, Integer exchangeStatus, Integer numOfExchange, Long contractId, String contractNo,
                           String taxRate, String paymentMethod, Long paymentTime, Long customerId, String no, String paymentStatusName,
                           String listProduct, String fileName,String originalInvoiceNo) {
            super(adjustmentType, buyerName, buyerTaxCode, createTime, currency, invoiceId, invoiceNo, invoiceNumber, invoiceSeri,
                invoiceType, issueDate, paymentStatus, supplierTaxCode, taxAmount, templateCode, total, totalBeforeTax, viewStatus,
                issueDateStr, state, requestDate, description, buyerIdNo, stateCode, subscriberNumber, downloadStatus, exchangeStatus,
                numOfExchange, contractId, contractNo, taxRate, paymentMethod, paymentTime, customerId, no, paymentStatusName,originalInvoiceNo);
            this.listProduct = listProduct;
            this.fileName = fileName;
        }

        public String getListProduct() {
            return listProduct;
        }

        public void setListProduct(String listProduct) {
            this.listProduct = listProduct;
        }

        public String getBuyerUnitName() {
            return buyerUnitName;
        }

        public void setBuyerUnitName(String buyerUnitName) {
            this.buyerUnitName = buyerUnitName;
        }

        public String getBuyerCode() {
            return buyerCode;
        }

        public void setBuyerCode(String buyerCode) {
            this.buyerCode = buyerCode;
        }

        public String getBuyerAddress() {
            return buyerAddress;
        }

        public void setBuyerAddress(String buyerAddress) {
            this.buyerAddress = buyerAddress;
        }

        public BigDecimal getExchangeRate() {
            return exchangeRate;
        }

        public void setExchangeRate(BigDecimal exchangeRate) {
            this.exchangeRate = exchangeRate;
        }

        public String getListInfoUpdate() {
            return listInfoUpdate;
        }

        public void setListInfoUpdate(String listInfoUpdate) {
            this.listInfoUpdate = listInfoUpdate;
        }
    }

    public static class InvoiceSearch extends BaseOutputDTO {
        private Long totalRows;
        private List<InvoicesAll> invoices;

        public Long getTotalRows() {
            return totalRows;
        }

        public void setTotalRows(Long totalRows) {
            this.totalRows = totalRows;
        }

        public List<InvoicesAll> getInvoices() {
            return invoices;
        }

        public void setInvoices(List<InvoicesAll> invoices) {
            this.invoices = invoices;
        }
    }

    public static class InfoViettelAI extends BaseOutputDTO {
        private String url;
        private String tk;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTk() {
            return tk;
        }

        public void setTk(String tk) {
            this.tk = tk;
        }
    }
}
