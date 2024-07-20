TCP socket

# Games tìm số
Sử dụng ngôn ngữ chính java, mô hình client-server, giao tiếp bằng TCP socket
* phía Client
- cho phép 2-3 người chơi cùng
- Hiển thị 100 số từ 1 ->  100 ( có thể cấu hình lại từ phía server)
- Thời gian chưi có thể cấu hình phía server
- Số đã tìm được đánh dấu bằng màu của người chơi
- Thông báo số cần tìm kế tiếp khi có người đã tìm ra số hiện tại . Các số cần hiển thị ngẫu nhiên, không cần theo thứ tự 1 -> 100
- Các chức năng tăng cường cho người chơi như: Tăng điểm: cộng  nhiều hơn khi tìm được số may mắn, ưu tiên: chê tất cả số của người khác trong vòng 3s
- Chức năng xếp hạng: liệt kê thứ hạng người chơi trên thành tích người dùng, tra cứu được xếp hạngg, tỉ lệ thắng thua của người chơi 
* phía server xử lí mọi cập nhập từ client
  - thống kê được số lượng người dùng đang online
  - cấu hình được số lượng ván chơi/ thời gian ván
* các client có thể chạy trên nhiều máy tính khác nhau
