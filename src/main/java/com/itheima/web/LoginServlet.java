package com.itheima.web;

import com.itheima.pojo.User;
import com.itheima.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        User user = userService.login(name, password);
        if (user != null){
            if ("1".equals(remember)){
                Cookie c_name = new Cookie("name",name);
                Cookie c_password = new Cookie("password",password);
                c_name.setMaxAge(60 * 60 *24);
                c_password.setMaxAge(60 * 60 *24);
                response.addCookie(c_name);
                response.addCookie(c_password);
            }
            HttpSession session = request.getSession();
            session.setAttribute("user",user);


            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/selectAllServlet");
        }else {
            request.setAttribute("login_msg","用户名或者密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
