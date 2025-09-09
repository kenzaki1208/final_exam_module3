package org.example.exam_module_3.controller;

import org.example.exam_module_3.model.MatBang;
import org.example.exam_module_3.service.MatBangService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "matBangServlet", value = "/mat-bang-servlet")
public class MatBangServlet extends HttpServlet {
    private MatBangService matBangService;

    @Override
    public void init() {
        matBangService = new MatBangService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                req.getRequestDispatcher("/yeu_cau_1/create.jsp").forward(req, resp);
                break;
            case "delete":
                int id = Integer.parseInt(req.getParameter("id"));
                matBangService.deleteMatBang(id);
                resp.sendRedirect("/mat-bang-servlet");
                break;
            case "search":
                String loai = req.getParameter("loaiMatBang");
                String giaTienStr = req.getParameter("giaTien");
                String tangStr = req.getParameter("tang");

                Double giaTien = null;
                Integer tang = null;

                if (giaTienStr != null && !giaTienStr.isEmpty()) {
                    giaTien = Double.parseDouble(giaTienStr);
                }
                if (tangStr != null && !tangStr.isEmpty()) {
                    tang = Integer.parseInt(tangStr);
                }

                List<MatBang> listSearch = matBangService.search(loai, giaTien, tang);
                req.setAttribute("list", listSearch);
                req.getRequestDispatcher("/yeu_cau_1/list.jsp").forward(req, resp);
                break;
            default:
                List<MatBang> list = matBangService.findAll();
                req.setAttribute("list", list);
                req.getRequestDispatcher("/yeu_cau_1/list.jsp").forward(req, resp);
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        if ("create".equals(action)) {
            String error = matBangService.addMatBang(req);
            if (error != null) {
                req.setAttribute("error", error);
                req.getRequestDispatcher("/yeu_cau_1/create.jsp").forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/mat-bang-servlet");
            }
        }
    }
}
