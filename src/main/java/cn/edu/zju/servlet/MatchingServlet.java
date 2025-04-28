package cn.edu.zju.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import cn.edu.zju.bean.Sample;
import cn.edu.zju.dao.SampleDao;

@WebServlet(name = "MatchingServlet", urlPatterns = "/matching")
@MultipartConfig
public class MatchingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 转发到 matching.jsp 页面
        request.getRequestDispatcher("/views/matching.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取上传的文件信息
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        String uploadedBy = request.getParameter("uploaded_by");


        Sample sample = new Sample();
        sample.setUploadedBy(uploadedBy);
        sample.setCreatedAt(new java.util.Date());


        SampleDao sampleDao = new SampleDao();
        sampleDao.save(sample.getUploadedBy());

        response.sendRedirect(request.getContextPath() + "/samples");

    }
}