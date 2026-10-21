# Ứng dụng Quản lý sách Android

Project bài tập Android dùng **Java + XML**, gồm bốn Activity:

1. `LoginActivity`: đăng nhập bằng tài khoản cố định `admin` / `123456`, kiểm tra trường rỗng và thông tin sai.
2. `MainActivity`: hiển thị danh sách sách, trạng thái, đánh giá và thống kê tổng sách / đã đọc / chưa đọc xong.
3. `AddBookActivity`: thêm sách, chọn thể loại, trạng thái đọc, đánh giá 0–5 sao và ghi chú; bắt buộc nhập tên sách và tác giả.
4. `BookDetailActivity`: xem chi tiết, đánh dấu đã đọc, quay lại và xóa sách có xác nhận.

Dữ liệu mẫu gồm ba sách. Dữ liệu được giữ trong `ArrayList<Book>` ở bộ nhớ theo yêu cầu đề bài; không dùng SQLite, Room hoặc Firebase. Khi tiến trình ứng dụng bị Android kết thúc hoàn toàn, dữ liệu người dùng thêm trong phiên chạy có thể mất và danh sách mẫu sẽ được khởi tạo lại.

## Cần cài gì?

- Android Studio bản ổn định mới nhất: https://developer.android.com/studio
- Kết nối Internet lần đầu để Android Studio tải Android SDK, Android Build Tools, Gradle và các plugin build.
- Không cần cài Java/JDK riêng thông thường; dùng JDK đi kèm Android Studio. Project đặt Java compatibility 17.
- Có thể chạy bằng Android Emulator hoặc điện thoại Android bật Developer options → USB debugging.

## Mở và chạy trên Windows

1. Giải nén thư mục `QuanLySachAndroid` vào một nơi dễ tìm.
2. Nếu project chưa có các tệp Gradle Wrapper, mở PowerShell tại thư mục này và chạy:

   ```powershell
   powershell -ExecutionPolicy Bypass -File .\setup-wrapper.ps1
   ```

   Script chỉ tải các tệp Gradle Wrapper từ kho chính thức `gradle/gradle`, nhánh/tag `v8.11.1`. Cần Internet.

3. Mở Android Studio → **Open** → chọn thư mục `QuanLySachAndroid` (thư mục có `settings.gradle`).
4. Chờ **Gradle Sync** hoàn tất. Nếu Android Studio hỏi tải Android SDK Platform 35 hoặc Build Tools, chấp nhận cài đặt.
5. Chọn thiết bị ở thanh trên cùng. Nếu chưa có máy ảo: **Tools → Device Manager → Create Device**, chọn một thiết bị như Pixel, tải system image rồi khởi động máy ảo.
6. Bấm nút **Run ▶**.
7. Đăng nhập: tên `admin`, mật khẩu `123456`.

## Tạo APK để cài lên điện thoại

Trong Android Studio, chọn **Build → Build APK(s)**. Khi build thành công, chọn thông báo **locate** để mở thư mục chứa APK, thường nằm trong `app/build/outputs/apk/debug/`.

## Cấu trúc chính

```text
QuanLySachAndroid/
├── app/
│   ├── build.gradle
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/example/quanlysach/
│       │   ├── LoginActivity.java
│       │   ├── MainActivity.java
│       │   ├── AddBookActivity.java
│       │   ├── BookDetailActivity.java
│       │   ├── data/BookRepository.java
│       │   └── model/Book.java
│       └── res/layout/        # giao diện XML
├── build.gradle
├── settings.gradle
└── gradle.properties
```

## Ghi chú

- Tài khoản `admin` / `123456` chỉ là tài khoản mẫu hard-code cho bài tập, không dùng cho xác thực thật.
- Project không thêm thư viện bên ngoài; giao diện dùng các thành phần Android cơ bản.
