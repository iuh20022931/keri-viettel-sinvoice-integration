import com.invoice.example.services.InvoiceMilkSampleService;
import com.invoice.example.services.InvoicePXKMilkSampleService;

public class Main {
    public static void main(String[] args) {
        InvoiceMilkSampleService invoiceSampleService = new InvoiceMilkSampleService();
        InvoicePXKMilkSampleService invoicePXKMilkSampleService = new InvoicePXKMilkSampleService();
        try {
            // Login lay token
            System.out.println("--- 1. Login ---");

            String token = null;
            // Bọc try-catch để nếu login lỗi mạng/server thì vẫn chạy tiếp xuống phần dùng
            // token thủ công
            try {
                token = invoiceSampleService.login("0100109106-504", "2wsxCDE#");
            } catch (Exception e) {
                System.out.println("Login tu dong gap loi (se dung token thu cong): " + e.getMessage());
            }

            if (token == null) {
                System.out.println("Login that bai/Loi. Su dung token thu cong...");
                // Token ban vua cung cap (het han luc ~05:15 30/01/2026)
                token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiIwMTAwMTA5MTA2LTUwNCIsInNjb3BlIjpbIm9wZW5pZCJdLCJleHAiOjE3Njk3NTAxMDcsInR5cGUiOjEsImlhdCI6MTc2OTc0ODkwNywiaW52b2ljZV9jbHVzdGVyIjoiY2x1c3RlcjUiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiYjkxMWUxMjUtMDg3ZC00NmY5LWFhYWYtNTZkY2YwNzBlZWFkIiwiY2xpZW50X2lkIjoid2ViX2FwcCJ9.GVoNUtamZTXNpN0nwy2Bqw55P9yGYu8Wxj1FmY55941gjmxLcY5INtcctl46iNMd3g9Q1ysxY02fVb6dyxytxI3i-i2MTWoPBnK38p4YwUM4zWk3feIOVkG4-qN96jHwYQtnByHCBk4PSCKEst48CseEN-CeV3XMUNvAenIM4VTKmizLNjNspRNE8U3XngTOS1N04axKUNWIMz3AcgszpKgn0jzTy4qz2ezJk3aIoPW1Yqos2JsCpamocYc-zXxoXWIpwPtWiNpyjoSa8OA0Hear2HgqGE1l8tcEHaNptEh36glVPVnwprHuEgDd3IbKSv1yaj5Hu3UMtk7kxvMSoQ";
                invoiceSampleService.setAccessToken(token);
            }

            System.out.println("Token su dung: "
                    + (token != null && token.length() > 15 ? token.substring(0, 15) + "..." : "null"));
            // invoiceSampleService da tu luu token khi login thanh cong
            invoicePXKMilkSampleService.setAccessToken(token);

            // --- NHIEM VU 1: XUAT HOA DON (CREATE INVOICE) ---
            System.out.println("--- TASK 1: Create Invoice GTGT ---");
            invoiceSampleService.createInvoiceGTGT();

            // (Optional) Tao Phieu Xuat Kho neu can
            // System.out.println("--- TASK 1 (Extra): Create Invoice PXK ---");
            // invoicePXKMilkSampleService.createInvoicePXK();

            // --- NHIEM VU 2: GOM HOA DON THEO MST (SEARCH INVOICE) ---
            System.out.println("--- TASK 2: Search Invoice By MST ---");
            // Tim hoa don cua khach hang co MST 0100109106-990 tu ngay 01/01/2025 den nay
            invoiceSampleService.searchInvoiceByCustomerTaxCode("0100109106-990", "01/01/2025", "30/01/2026");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
