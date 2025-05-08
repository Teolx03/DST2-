package cn.edu.zju.servlet;

import cn.edu.zju.bean.User;
import cn.edu.zju.dao.RegisterDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final RegisterDao userDao = new RegisterDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 显示注册页面
        request.getRequestDispatcher("/views/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User newUser = new User(username, password);
            userDao.registerUser(newUser);

            // 注册成功，重定向到登录页
            response.sendRedirect(request.getContextPath() + "/signin");
        } catch (IllegalArgumentException e) {
            // 用户名已存在，返回错误信息
            request.setAttribute("error", "用户名已存在");
            request.getRequestDispatcher("/views/register.jsp").forward(request, response);
        } catch (Exception e) {
            // 系统错误
            request.setAttribute("error", "系统错误，请稍后重试");
            request.getRequestDispatcher("/views/register.jsp").forward(request, response);
        }
    }
}