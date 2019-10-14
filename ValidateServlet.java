package com.test;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ValidateServlet extends HttpServlet{
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,IOException {
        //得到提交的验证码
        String code = request.getParameter("code");
        String u = request.getParameter("username");
        String p = request.getParameter("password");
        //获取session中的验证码
        HttpSession session = request.getSession();
        String randStr = (String)session.getAttribute("randStr");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (code.equals(randStr)){
            response.sendRedirect("/showView.jsp?username=" + u + "&password=" + p);
        }else{
            out.println("验证码错误!");
        }
    }
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,IOException {
        this.doGet(request,response);
    }
}
