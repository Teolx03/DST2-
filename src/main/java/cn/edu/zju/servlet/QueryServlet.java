package cn.edu.zju.servlet;

import cn.edu.zju.bean.QueryResultBean;
import cn.edu.zju.dao.QueryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "QueryServlet", urlPatterns = "/query")
public class QueryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 直接转发到 query.jsp
        request.getRequestDispatcher("/views/query.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String drugId = request.getParameter("drug");
        if (drugId == null || drugId.trim().isEmpty()) {
            request.setAttribute("error", "请输入有效的药物ID或名称");
            request.getRequestDispatcher("/views/query.jsp").forward(request, response);
            return;
        }

        QueryDao queryDao = new QueryDao();
        List<QueryResultBean> results = queryDao.findDosingGuidelineByDrugId(drugId);
        request.setAttribute("results", results);
        request.getRequestDispatcher("/views/query_result.jsp").forward(request, response);
    }
}