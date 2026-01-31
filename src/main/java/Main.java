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
                token = invoiceSampleService.login("0100109106-507", "2wsxCDE#");
            } catch (Exception e) {
                System.out.println("Login tu dong gap loi (se dung token thu cong): " + e.getMessage());
            }

            if (token == null) {
                System.out.println("Login that bai/Loi. Su dung token thu cong...");
                // Token moi cap nhat (MST 0100109106-507)
                token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiIwMTAwMTA5MTA2LTUwNyIsInNjb3BlIjpbIm9wZW5pZCJdLCJleHAiOjE3Njk4Mjc1NDQsInR5cGUiOjEsImlhdCI6MTc2OTgyNjM0NCwiaW52b2ljZV9jbHVzdGVyIjoiY2x1c3RlcjciLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiNmY5NTI1ZDAtNzU1NC00Yjc5LWJiMGEtZGNlMjQ4NzQxZWEzIiwiY2xpZW50X2lkIjoid2ViX2FwcCJ9.dPiKuanbwUucyJGFgVDOofmucWBG018Zd0GxTBb-whqyZLjZT4uWOudD6i2xjxuQQJ7jAEa0eMAFsnSK1F1FefJgspKmKLBjZ2gkBJ6NMn90ySMvGpU17yVBoFIYy6qWqvuLHKoVOhJU3mZ8MWIcbjCAFwSJf4BAQp8w-qwLAEdZ1Wc_DjSVNGpJwf4pD-ZjxtUn8yhRiglmVfgtYFBm3I8nkjySNSy57xRGpaiIE4RUMq4MrqHWXNUNTmGHNQRgf30PVRMDFnckBJLNGVZ3pr0ST3JqxZ4KoZrMuBbOM1LRKBOzboJK4zjvfv_-XRbaKcxtSQ3eFRWfDqXvsd_ksg";
                invoiceSampleService.setAccessToken(token);
            }

            System.out.println("Token su dung: "
                    + (token != null && token.length() > 15 ? token.substring(0, 15) + "..." : "null"));
            // invoiceSampleService da tu luu token khi login thanh cong
            invoicePXKMilkSampleService.setAccessToken(token);

            // --- NHIEM VU 1: XUAT HOA DON (CREATE INVOICE) ---
            System.out.println("--- TASK 1: Create Invoice GTGT ---");
            invoiceSampleService.createInvoiceGTGT();
            System.out.println("✅ TASK 1: XUAT HOA DON THANH CONG!");

            // (Optional) Tao Phieu Xuat Kho neu can
            // System.out.println("--- TASK 1 (Extra): Create Invoice PXK ---");
            // invoicePXKMilkSampleService.createInvoicePXK();

            // --- NHIEM VU 2: GOM HOA DON THEO MST (SEARCH INVOICE) ---
            System.out.println("--- TASK 2: Search Invoice By MST ---");
            // Tim hoa don cua khach hang co MST 0100109106-507 tu ngay 01/01/2026 den nay
            // Tham so: MST Nguoi Ban, MST Nguoi Mua, Tu Ngay, Den Ngay
            invoiceSampleService.searchInvoiceByCustomerTaxCode("0100109106-507", "0100109106-507", "2026-01-01",
                    "2026-01-31");
            System.out.println("✅ TASK 2: TRA CUU HOA DON THANH CONG!");

            System.out.println("--- TASK 2 (Advanced): Search Invoice Utils All (Link 1) ---");
            // Su dung API getInvoicesAll de lay them thong tin file va chi tiet
            invoiceSampleService.searchInvoiceUtilsAll("0100109106-507", "0100109106-507", "2026-01-01", "2026-01-31");
            System.out.println("✅ TASK 2 (Advanced): TRA CUU CHI TIET THANH CONG!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
