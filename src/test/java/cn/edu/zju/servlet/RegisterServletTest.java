package cn.edu.zju.servlet;

import cn.edu.zju.bean.User;
import cn.edu.zju.dao.RegisterDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)  // JUnit 5与Mockito集成
public class RegisterServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private RegisterDao registerDao;

    @InjectMocks  // 自动注入mock对象
    private RegisterServlet registerServlet;

    @BeforeEach
    void setUp() throws Exception {
        // 通过反射注入mock的DAO（因为原字段是final）
        Field daoField = RegisterServlet.class.getDeclaredField("userDao");
        daoField.setAccessible(true);
        daoField.set(registerServlet, registerDao);
    }

    @Test
    void doGet_ShouldForwardToRegisterPage() throws Exception {
        when(request.getRequestDispatcher("/views/register.jsp")).thenReturn(requestDispatcher);

        registerServlet.doGet(request, response);

        verify(request).getRequestDispatcher("/views/register.jsp");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void doPost_ValidRegistration_ShouldRedirectToLogin() throws Exception {
        when(request.getParameter("username")).thenReturn("newUser");
        when(request.getParameter("password")).thenReturn("password123");
        when(request.getContextPath()).thenReturn("");

        registerServlet.doPost(request, response);

        verify(registerDao).registerUser(argThat(user ->
                user.getUsername().equals("newUser") &&
                        user.getPassword().equals("password123")
        ));
        verify(response).sendRedirect("/signin");
    }

    @Test
    void doPost_DuplicateUsername_ShouldShowError() throws Exception {
        when(request.getParameter("username")).thenReturn("existingUser");
        when(request.getParameter("password")).thenReturn("password123");
        when(request.getRequestDispatcher("/views/register.jsp")).thenReturn(requestDispatcher);
        doThrow(new IllegalArgumentException("用户名已存在"))
                .when(registerDao).registerUser(any(User.class));

        registerServlet.doPost(request, response);

        verify(request).setAttribute("error", "用户名已存在");
        verify(requestDispatcher).forward(request, response);
    }
}