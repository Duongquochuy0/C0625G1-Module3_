# 🏠 Quản Lý Phòng Trọ

Ứng dụng web giúp **chủ trọ quản lý khách thuê phòng** một cách nhanh chóng, chính xác và thuận tiện.  
Dự án được phát triển với mục tiêu hỗ trợ số hóa quá trình quản lý nhà trọ, thay thế các phương pháp thủ công truyền thống bằng hệ thống trực tuyến dễ sử dụng.

---

## 📘 Giới thiệu

**Quản Lý Phòng Trọ** là một ứng dụng web quản lý thông tin khách thuê, phòng trọ, và các dữ liệu liên quan.  
Hệ thống cho phép chủ trọ dễ dàng theo dõi tình trạng phòng, danh sách khách thuê, và tìm kiếm thông tin khi cần thiết.  
Ứng dụng được xây dựng theo mô hình **MVC (Model – View – Controller)** nhằm đảm bảo khả năng mở rộng và bảo trì lâu dài.

---

## 🧩 Công nghệ sử dụng

- ☕ **Jakarta EE 9.1** — nền tảng phát triển ứng dụng web Java hiện đại  
- 🧰 **Gradle** — công cụ quản lý build và dependency  
- 💾 **MySQL 8** — hệ quản trị cơ sở dữ liệu quan hệ  
- 💻 **JSP / Servlet** — xử lý logic và giao diện phía server  
- 🎨 **HTML / CSS / Bootstrap** — xây dựng giao diện người dùng thân thiện  

---

## ⚙️ Chức năng chính

- Quản lý **danh sách khách thuê phòng**
- Thêm, sửa, xóa thông tin khách thuê
- Quản lý **phòng trọ** (tên phòng, tình trạng, số lượng người thuê)
- Tìm kiếm khách thuê theo **tên hoặc số điện thoại**
- Giao diện thân thiện, dễ sử dụng cho người không chuyên CNTT

---

## 🧠 Kiến trúc hệ thống

Ứng dụng được thiết kế theo mô hình **MVC**:

src/
├── controller/ # Xử lý các request, điều hướng trang
├── model/ # Chứa các lớp mô hình (entity)
├── repository/ # Thao tác với cơ sở dữ liệu
├── service/ # Xử lý logic nghiệp vụ
└── webapp/
├── pages/ # JSP giao diện người dùng
└── assets/ # CSS, JS, hình ảnh

markdown
Sao chép mã

**Luồng hoạt động:**  
1. Người dùng thao tác qua giao diện JSP.  
2. Request được gửi đến Controller (Servlet).  
3. Controller gọi Service để xử lý logic.  
4. Service tương tác với Repository để truy xuất hoặc lưu dữ liệu.  
5. Kết quả được trả về View (JSP) để hiển thị.

---

## 🚀 Hướng dẫn cài đặt & chạy dự án

### 🔧 Yêu cầu môi trường
- **JDK 17 trở lên**
- **MySQL 8**
- **Gradle**
- **IntelliJ IDEA / Eclipse / VS Code (có plugin Java)**

### 🔹 Các bước thực hiện

1. **Clone dự án**
   ```bash
   git clone https://github.com/<your-username>/quan-ly-phong-tro.git
Cấu hình cơ sở dữ liệu

Tạo database quan_ly_phong_tro trong MySQL.

Cập nhật thông tin trong application.properties:

properties
Sao chép mã
spring.datasource.url=jdbc:mysql://localhost:3306/quan_ly_phong_tro
spring.datasource.username=root
spring.datasource.password=yourpassword
Chạy ứng dụng

bash
Sao chép mã
gradle bootRun
Hoặc mở project trong IntelliJ IDEA và chạy file DemoApplication.java.

Truy cập ứng dụng
👉 http://localhost:8080

🧩 Hướng phát triển tương lai
Thêm chức năng tính tiền điện nước tự động

Hỗ trợ xuất hóa đơn / báo cáo

Quản lý tài khoản người dùng và phân quyền

Tích hợp API RESTful cho ứng dụng mobile

👨‍💻 Tác giả
Dương Quốc Huy

💬 Lập trình viên Java yêu thích phát triển ứng dụng web thực tế
📧 (Thêm email hoặc GitHub của bạn nếu muốn xuất hiện trên README)

📄 Giấy phép
Dự án được phát triển phục vụ mục đích học tập và nghiên cứu.
Bạn được phép sử dụng, chỉnh sửa hoặc mở rộng mã nguồn cho các dự án cá nhân.

🧠 “Quản lý dễ dàng — tiết kiệm thời gian — chính xác dữ liệu.”