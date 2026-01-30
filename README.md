# keri-viettel-sinvoice-integration

## Mô tả nhiệm vụ

### Nhiệm vụ 1: Xuất hóa đơn (Create Invoice)

Xây dựng API Client để gửi dữ liệu đơn hàng (từ website Shipper) sang Viettel.
Xử lý các trường dữ liệu bắt buộc: Thông tin người bán (KERI), Thông tin khách hàng (MST), Danh sách phí dịch vụ shipper, Thuế suất GTGT.

### Nhiệm vụ 2: Gom hóa đơn theo MST (Search/Query Invoice)

Xây dựng hàm tìm kiếm hóa đơn dựa trên tham số buyerTaxCode (Mã số thuế của từng công ty khách hàng).
Hệ thống phải trả về danh sách các hóa đơn tương ứng để hiển thị lên Website cho khách hàng xem.

---

## Báo cáo tiến độ (Status Report)

### ✅ Đã làm được (Completed)

1. **Xác thực (Authentication):**
   - Đã xây dựng hàm `login` để lấy Token từ Viettel S-Invoice API.
   - Hỗ trợ cơ chế fallback: Sử dụng Token cứng (Hardcoded) nếu login tự động thất bại (hữu ích cho môi trường Dev/Test).

2. **Nhiệm vụ 1 - Tạo hóa đơn:**
   - Đã tích hợp API tạo hóa đơn GTGT (`createInvoiceGTGT`).
   - Đã tích hợp API tạo Phiếu xuất kho (`createInvoicePXK`).
   - Đã xử lý cấu trúc JSON phức tạp của Viettel (GeneralInfo, BuyerInfo, ItemInfo, SummarizeInfo...).

3. **Nhiệm vụ 2 - Tìm kiếm hóa đơn:**
   - Đã xây dựng hàm `searchInvoiceByCustomerTaxCode` cho phép tìm kiếm theo MST người mua và khoảng thời gian.
   - Kết quả trả về danh sách hóa đơn chi tiết.

4. **Kiểm thử (Testing):**
   - Có file `Main.java` để chạy thử luồng nghiệp vụ: Login -> Tạo hóa đơn -> Tìm kiếm.

### 🚧 Chưa làm được / Cần cải thiện (Pending / To-do)

1. **Dữ liệu động (Dynamic Data):**
   - Hiện tại dữ liệu hóa đơn (Tên khách, Sản phẩm, Giá tiền...) đang được gán cứng (Hardcoded) trong code để phục vụ kiểm thử kết nối.
   - **Cần làm:** Refactor các hàm tạo hóa đơn để nhận tham số đầu vào (DTO) từ hệ thống bên ngoài thay vì tự tạo dữ liệu giả.

2. **Cấu hình (Configuration):**
   - Các thông tin như URL API, Mã số thuế người bán, Tài khoản đăng nhập đang nằm lẫn trong code Java.
   - **Cần làm:** Chuyển các thông tin này ra file cấu hình riêng (ví dụ: `application.properties`) để dễ dàng thay đổi môi trường (Dev/Prod).

### Ghi chú các vấn đề kỹ thuật đã giải quyết (Technical Notes & Resolved Issues)

1. **Không tương thích giao thức xác thực (Authentication Mismatch):**
   - **Vấn đề ban đầu:** Gặp lỗi `415 Unsupported Media Type` khi gọi API đăng nhập.
   - **Nguyên nhân:** API `.../api/authenticate` của Viettel yêu cầu `Content-Type: application/json`, trong khi phiên bản code ban đầu gửi dữ liệu dưới dạng `x-www-form-urlencoded`.
   - **Giải pháp đã thực hiện:** Đã refactor hàm `login` để sử dụng phương thức `postData` (gửi JSON) thay vì `postXFormData`, giúp giải quyết triệt để lỗi 415.

2. **Sai lệch phương thức và Endpoint API (Endpoint & Method Mismatch):**
   - **Vấn đề ban đầu:** Gặp lỗi `405 Method Not Allowed` khi gọi đến URL `/authenticate`.
   - **Nguyên nhân:** Lỗi xảy ra do gọi API bằng phương thức `GET` (khi truy cập bằng trình duyệt) thay vì `POST` theo yêu cầu của server.
   - **Giải pháp đã thực hiện:** Đảm bảo code luôn sử dụng phương thức `POST` khi gọi đến endpoint `/authenticate`.

3. **Vấn đề bảo mật lớp mạng (IP Whitelisting):**
   - **Lưu ý:** Đây là một yếu tố phụ thuộc vào môi trường. Các kết nối từ IP không được khai báo trong Whitelist của Viettel S-Invoice sẽ bị chặn.
   - **Khuyến nghị:** Khi triển khai, cần phối hợp với quản trị viên hệ thống để cấu hình IP của server vào danh sách cho phép.

4. **Khó khăn trong việc tra cứu tài liệu (API Discovery Challenge):**
   - **Thử thách:** Việc thiếu tài liệu API (Swagger/WSDL) công khai và cập nhật đã gây khó khăn trong việc xác định chính xác cấu trúc và endpoint.
   - **Vượt qua:** Đã khắc phục bằng cách phân tích các luồng request/response từ giao diện web và thử nghiệm lặp lại.
