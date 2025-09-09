<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách mặt bằng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="mb-4 text-center">Danh sách mặt bằng</h2>

    <form action="/mat-bang-servlet" method="get" class="row g-3 mb-4">
        <input type="hidden" name="action" value="search">

        <div class="col-md-4">
            <label class="form-label">Loại mặt bằng</label>
            <select name="loaiMatBang" class="form-select">
                <option value="">--Tất cả--</option>
                <option value="văn phòng chia sẻ">Văn phòng chia sẻ</option>
                <option value="văn phòng trọn gói">Văn phòng trọn gói</option>
            </select>
        </div>

        <div class="col-md-4">
            <label class="form-label">Giá tiền (tối đa)</label>
            <input type="number" name="giaTien" class="form-control" placeholder="Nhập giá">
        </div>

        <div class="col-md-4">
            <label class="form-label">Tầng</label>
            <select name="tang" class="form-select">
                <option value="">--Tất cả--</option>
                <c:forEach begin="1" end="15" var="i">
                    <option value="${i}">${i}</option>
                </c:forEach>
            </select>
        </div>

        <div class="col-12 d-flex justify-content-between">
            <button type="submit" class="btn btn-primary">Tìm kiếm</button>
            <a href="/yeu_cau_1/create.jsp" class="btn btn-success">Thêm mới</a>
        </div>
    </form>

    <div class="table-responsive">
        <table class="table table-bordered table-hover align-middle text-center bg-white shadow-sm">
            <thead class="table-dark">
            <tr>
                <th>Mã MB</th>
                <th>Diện tích</th>
                <th>Trạng thái</th>
                <th>Tầng</th>
                <th>Loại</th>
                <th>Giá tiền</th>
                <th>Ngày bắt đầu</th>
                <th>Ngày kết thúc</th>
                <th>Xóa</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="mb" items="${list}">
                <tr>
                    <td>${mb.maMatBang}</td>
                    <td>${mb.dienTich}</td>
                    <td>${mb.trangThai}</td>
                    <td>${mb.tang}</td>
                    <td>${mb.loai}</td>
                    <td>${mb.giaTien}</td>
                    <td>${mb.ngayBatDau}</td>
                    <td>${mb.ngayKetThuc}</td>
                    <td>
                        <a href="/mat-bang-servlet?action=delete&id=${mb.id}"
                           class="btn btn-danger btn-sm"
                           onclick="return confirm('Bạn có chắc chắn muốn xóa mặt bằng này?');">
                            Xóa
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
