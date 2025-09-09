package org.example.exam_module_3.dao;

import org.example.exam_module_3.model.MatBang;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Date.valueOf;

public class MatBangDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/rental_space_management?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "dinhduy561";

    private static final String INSERT_SQL =
            "INSERT INTO matbang(ma_mat_bang, trang_thai, dien_tich, tang, loai, gia_tien, ngay_bat_dau, ngay_ket_thuc) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM matbang";
    private static final String CHECK_DUPLICATE = "SELECT id FROM matbang WHERE ma_mat_bang = ?";
    private static final String DELETE_SQL = "DELETE FROM matbang WHERE id = ?";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public boolean existsByMaMatBang(String maMatBang) {
        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(CHECK_DUPLICATE)
        ) {
            ps.setString(1, maMatBang);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void insert(MatBang matBang) {
        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(INSERT_SQL)
            )
        {
            ps.setString(1, matBang.getMaMatBang());
            ps.setString(2, matBang.getTrangThai());
            ps.setDouble(3, matBang.getDienTich());
            ps.setInt(4, matBang.getTang());
            ps.setString(5, matBang.getLoai());
            ps.setDouble(6, matBang.getGiaTien());
            ps.setDate(7, new java.sql.Date(matBang.getNgayBatDau().getTime()));
            ps.setDate(8, new java.sql.Date(matBang.getNgayKetThuc().getTime()));
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<MatBang> findAll() {
        List<MatBang> list = new ArrayList<>();
        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(SELECT_ALL)
            ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MatBang mb = new MatBang();
                mb.setId(rs.getInt("id"));
                mb.setMaMatBang(rs.getString("ma_mat_bang"));
                mb.setTrangThai(rs.getString("trang_thai"));
                mb.setDienTich(rs.getDouble("dien_tich"));
                mb.setTang(rs.getInt("tang"));
                mb.setLoai(rs.getString("loai"));
                mb.setGiaTien(rs.getDouble("gia_tien"));
                mb.setNgayBatDau(rs.getDate("ngay_bat_dau"));
                mb.setNgayKetThuc(rs.getDate("ngay_ket_thuc"));
                list.add(mb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<MatBang> search(String loai, Double giaTien, Integer tang) {
        List<MatBang> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM matbang WHERE 1=1");

        if (loai != null && !loai.isEmpty()) {
            sql.append(" AND loai = ?");
        }
        if (giaTien != null) {
            sql.append(" AND gia_tien <= ?");
        }
        if (tang != null) {
            sql.append(" AND tang = ?");
        }

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql.toString())) {

            int index = 1;
            if (loai != null && !loai.isEmpty()) {
                ps.setString(index++, loai);
            }
            if (giaTien != null) {
                ps.setDouble(index++, giaTien);
            }
            if (tang != null) {
                ps.setInt(index++, tang);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MatBang mb = new MatBang(
                        rs.getString("ma_mat_bang"),
                        rs.getDouble("dien_tich"),
                        rs.getString("trang_thai"),
                        rs.getInt("tang"),
                        rs.getString("loai"),
                        rs.getDouble("gia_tien"),
                        rs.getDate("ngay_bat_dau"),
                        rs.getDate("ngay_ket_thuc")
                );
                list.add(mb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteById(int id) {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
