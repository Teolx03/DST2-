package cn.edu.zju.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import cn.edu.zju.bean.Sample;
import cn.edu.zju.dao.SampleDao;
import cn.edu.zju.dao.UploadedDataDao;

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
        String uploadedBy = request.getParameter("uploaded_by");
        String dataType = request.getParameter("data_type"); // 数据类型

        // 读取文件内容
        InputStream inputStream = filePart.getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] temp = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(temp)) != -1) {
            buffer.write(temp, 0, bytesRead);
        }
        String content = new String(buffer.toByteArray(), "UTF-8");

        // 保存样本信息
        SampleDao sampleDao = new SampleDao();
        int sampleId = sampleDao.save(uploadedBy);

        // 保存文件内容到 uploaded_data 表
        UploadedDataDao uploadedDataDao = new UploadedDataDao();
        uploadedDataDao.save(sampleId, dataType, content);

        // 重定向到样本页面
        response.sendRedirect(request.getContextPath() + "/samples");
    }
}