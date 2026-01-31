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
   - Đã tích hợp thành công API tạo hóa đơn GTGT (`createInvoiceGTGT`).
   - Đã cập nhật đúng mẫu hóa đơn (`1/770`) và ký hiệu (`K26TXM`) theo môi trường Test.
   - Đã bổ sung các trường thiếu trong DTO: `cusGetInvoiceRight`, `lineNumber`, `sumOfTotalLineAmountWithoutTax`.
   - Đã xử lý cấu trúc JSON phức tạp của Viettel (GeneralInfo, BuyerInfo, ItemInfo, SummarizeInfo...).

3. **Nhiệm vụ 2 - Tìm kiếm hóa đơn:**
   - Đã hoàn thiện hàm `searchInvoiceByCustomerTaxCode` và `searchInvoiceUtilsAll`.
   - Đã khắc phục lỗi 404 bằng cách chuyển sang endpoint đúng: `InvoiceUtilsWS/getAllInvoices`.
   - Đã khắc phục lỗi 500 bằng cách chỉnh `pageNum` bắt đầu từ 1.
   - Đã chuẩn hóa định dạng ngày tháng sang `yyyy-MM-dd`.
   - Kết quả trả về danh sách hóa đơn chi tiết (bao gồm cả tên file PDF nếu có).

4. **Kiểm thử (Testing):**
   - Có file `Main.java` để chạy thử luồng nghiệp vụ: Login -> Tạo hóa đơn -> Tìm kiếm.
   - Đã verify thành công việc tạo hóa đơn và tra cứu lại chính hóa đơn đó.

### 🚧 Chưa làm được / Cần cải thiện (Pending / To-do)

1. **Dữ liệu động (Dynamic Data):**
   - Hiện tại dữ liệu hóa đơn (Tên khách, Sản phẩm, Giá tiền...) đang được gán cứng (Hardcoded) trong code (`genWSBodyInputNewGTGT`).
   - **Cần làm:** Refactor các hàm tạo hóa đơn để nhận tham số đầu vào (DTO) từ hệ thống bên ngoài thay vì tự tạo dữ liệu giả.

2. **Cấu hình (Configuration):**
   - Các thông tin như URL API, Mã số thuế người bán, Tài khoản đăng nhập đang nằm lẫn trong code Java.
   - **Cần làm:** Chuyển các thông tin này ra file cấu hình riêng (ví dụ: `application.properties`) để dễ dàng thay đổi môi trường (Dev/Prod).

### Ghi chú các vấn đề kỹ thuật đã giải quyết (Technical Notes & Resolved Issues)

1. **Sai lệch Endpoint API Tìm kiếm:**
   - **Vấn đề:** Endpoint `InvoiceWS/getInvoices` trả về 404 hoặc không hoạt động như mong đợi.
   - **Giải pháp:** Chuyển sang sử dụng `InvoiceUtilsWS/getAllInvoices` (hoặc `InvoiceUtilsWS/getInvoicesAll`) để lấy dữ liệu chi tiết và chính xác hơn.

2. **Lỗi Phân trang (Pagination Error):**
   - **Vấn đề:** Gặp lỗi 500 "Page index must not be less than zero" khi truyền `pageNum = 0`.
   - **Giải pháp:** Viettel API yêu cầu `pageNum` bắt đầu từ 1. Đã cập nhật code mặc định là 1.

3. **Định dạng ngày tháng:**
   - **Vấn đề:** API yêu cầu định dạng ngày cụ thể, sai định dạng dẫn đến không tìm thấy dữ liệu.
   - **Giải pháp:** Thống nhất sử dụng định dạng `yyyy-MM-dd` cho các trường `startDate`, `endDate`.

4. **Không tương thích giao thức xác thực (Authentication Mismatch):**
   - **Vấn đề ban đầu:** Gặp lỗi `415 Unsupported Media Type` khi gọi API đăng nhập.
   - **Giải pháp:** Sử dụng phương thức `postData` (gửi JSON) thay vì `postXFormData` cho API login.
