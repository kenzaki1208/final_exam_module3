package org.example.exam_module_3.service;

import org.example.exam_module_3.dao.MatBangDAO;
import org.example.exam_module_3.model.MatBang;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MatBangService {
    private MatBangDAO matBangDAO = new MatBangDAO();

    public List<MatBang> findAll() {
        return matBangDAO.findAll();
    }

    public String addMatBang(HttpServletRequest request) {
        try {
            String maMatBang = request.getParameter("maMatBang");
            String trangThai = request.getParameter("trangThai");
            double dienTich = Double.parseDouble(request.getParameter("dienTich"));
            int tang = Integer.parseInt(request.getParameter("tang"));
            String loai = request.getParameter("loaiMatBang");
            double giaTien = Double.parseDouble(request.getParameter("giaTien"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date ngayBD = sdf.parse(request.getParameter("ngayBatDau"));
            Date ngayKT = sdf.parse(request.getParameter("ngayKetThuc"));

            if (matBangDAO.existsByMaMatBang(maMatBang)) {
                return "Mã mặt bằng đã tồn tại";
            }
            long diffMonths = (ngayKT.getTime() - ngayBD.getTime()) / (1000L*60*60*24*30);
            if (diffMonths < 6) {
                return "Ngày kết thúc phải cách ngày bắt đầu ít nhất 6 tháng";
            }
            MatBang mb = new MatBang(maMatBang, trangThai, dienTich, tang, loai, giaTien, ngayBD, ngayKT);
            matBangDAO.insert(mb);
        } catch (Exception e) {
            e.printStackTrace();
            return "Dữ liệu không hợp lệ";
        }
        return null;
    }

    public void deleteMatBang(int id) {
        matBangDAO.deleteById(id);
    }
}
