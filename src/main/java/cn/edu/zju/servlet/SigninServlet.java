package cn.edu.zju.servlet;

import cn.edu.zju.bean.User;
import cn.edu.zju.dao.RegisterDao;
import cn.edu.zju.filter.AuthenticationFilter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SigninServlet", urlPatterns = "/signin")
public class SigninServlet extends HttpServlet {

    private final RegisterDao userDao = new RegisterDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // 验证数据库中的用户
            if (userDao.validateUser(username, password)) {
                HttpSession session = request.getSession();
                session.setAttribute(AuthenticationFilter.ROLE_VIEW_DOSING_GUIDELINE, 1);
                session.setAttribute(AuthenticationFilter.USERNAME, username);
                response.sendRedirect("index");
            } else {
                showError(request, response, "用户名或密码错误");
            }
        } catch (Exception e) {
            showError(request, response, "系统错误，请稍后重试");
        }
    }

    private void showError(HttpServletRequest request, HttpServletResponse response, String error)
            throws ServletException, IOException {
        request.setAttribute("error", error);
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/signin.jsp").forward(request, response);
    }
}