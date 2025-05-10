package cn.edu.zju.servlet;

import cn.edu.zju.bean.UploadedData;
import cn.edu.zju.dao.UploadedDataDao;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ViewUploadedDataServlet", urlPatterns = "/viewUploadedData")
public class ViewUploadedDataServlet extends HttpServlet {
    private UploadedDataDao uploadedDataDao = new UploadedDataDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int sampleId = Integer.parseInt(request.getParameter("sampleId"));
        List<UploadedData> uploadedDataList = uploadedDataDao.findBySampleId(sampleId);
        //test error
        System.out.println("Uploaded Data List: " + uploadedDataList); // 打印列表内容// 测试输出

        request.setAttribute("uploadedDataList", uploadedDataList);
        request.getRequestDispatcher("/views/view_uploaded_data.jsp").forward(request, response);

    }
}