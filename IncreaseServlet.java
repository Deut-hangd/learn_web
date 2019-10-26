package com.test;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
public class IncreaseServlet extends HttpServlet{
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        String mark = request.getParameter("mark");
        float price = Integer.valueOf(request.getParameter("price")).floatValue();
        int number = Integer.valueOf(request.getParameter("number"));
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        GoodServlet good = new GoodServlet(mark,price,number);  //实例化商品
        ServletContext application = getServletContext();
        ArrayList goods = (ArrayList)application.getAttribute("goods");
        if (goods == null){
            goods = new ArrayList();
            application.setAttribute("goods",goods);
        }
        if (mark != null) {
            goods.add(good);
            application.setAttribute("goods",goods);
            out.print("三秒后返回提交界面.");
            response.setHeader("refresh", "3;url=submit.jsp");
        }
    }
    public void doPost(HttpServletRequest request,
                       HttpServletResponse reponse) throws ServletException, IOException{
        this.doGet(request,reponse);
    }

}
