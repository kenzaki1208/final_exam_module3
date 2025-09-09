<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm mới mặt bằng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="mb-4 text-center">Thêm mới mặt bằng</h2>
    <form action="/mat-bang-servlet?action=create" method="post" class="row g-3 needs-validation" novalidate>
        <div class="col-md-6">
            <label class="form-label">Mã mặt bằng</label>
            <input type="text" name="maMatBang" class="form-control" required>
        </div>

        <div class="col-md-6">
            <label class="form-label">Diện tích</label>
            <input type="number" name="dienTich" class="form-control" required>
        </div>

        <div class="col-md-6">
            <label class="form-label">Trạng thái</label>
            <select name="trangThai" class="form-select" required>
                <option value="trống">Trống</option>
                <option value="hạ tầng">Hạ tầng</option>
                <option value="đầy đủ">Đầy đủ</option>
            </select>
        </div>

        <div class="col-md-6">
            <label class="form-label">Tầng</label>
            <select name="tang" class="form-select" required>
                <c:forEach begin="1" end="15" var="i">
                    <option value="${i}">${i}</option>
                </c:forEach>
            </select>
        </div>

        <div class="col-md-6">
            <label class="form-label">Loại mặt bằng</label>
            <select name="loaiMatBang" class="form-select" required>
                <option value="văn phòng chia sẻ">Văn phòng chia sẻ</option>
                <option value="văn phòng trọn gói">Văn phòng trọn gói</option>
            </select>
        </div>

        <div class="col-md-6">
            <label class="form-label">Giá cho thuê (VNĐ)</label>
            <input type="number" name="giaTien" min="1000001" class="form-control" required>
        </div>

        <div class="col-md-6">
            <label class="form-label">Ngày bắt đầu</label>
            <input type="date" name="ngayBatDau" class="form-control" required>
        </div>

        <div class="col-md-6">
            <label class="form-label">Ngày kết thúc</label>
            <input type="date" name="ngayKetThuc" class="form-control" required>
        </div>

        <div class="col-12 d-flex justify-content-between mt-4">
            <button type="submit" class="btn btn-success">Lưu</button>
            <a href="/mat-bang-servlet" class="btn btn-secondary">Hủy</a>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
