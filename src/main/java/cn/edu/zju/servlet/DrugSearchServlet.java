package cn.edu.zju.servlet;

import cn.edu.zju.bean.Drug;
import cn.edu.zju.dao.DrugDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/searchDrug")  // ✅ link to form action="/searchDrug"
public class DrugSearchServlet extends HttpServlet {  // ✅ fix typo + extend HttpServlet
    private DrugDao drugDao = new DrugDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<Drug> matchedDrugs = new ArrayList<>();

        if (keyword != null && !keyword.trim().isEmpty()) {
            keyword = keyword.trim().toLowerCase();
            List<Drug> allDrugs = drugDao.findAll();

            for (Drug drug : allDrugs) {
                if ((drug.getId() != null && drug.getId().toLowerCase().contains(keyword)) ||
                        (drug.getName() != null && drug.getName().toLowerCase().contains(keyword))) {
                    matchedDrugs.add(drug);
                }
            }
        }

        request.setAttribute("searchResult", matchedDrugs);
        request.getRequestDispatcher("/views/drugs.jsp").forward(request, response);
    }
}

