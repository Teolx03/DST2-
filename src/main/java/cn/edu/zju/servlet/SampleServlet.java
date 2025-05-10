package cn.edu.zju.servlet;

import cn.edu.zju.bean.Sample;
import cn.edu.zju.dao.SampleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SampleServlet", urlPatterns = "/samples")
public class SampleServlet extends HttpServlet {
    private SampleDao sampleDao = new SampleDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Sample> samples = sampleDao.findAll();
        request.setAttribute("samples", samples);
        request.getRequestDispatcher("/views/sample.jsp").forward(request, response);
    }
}