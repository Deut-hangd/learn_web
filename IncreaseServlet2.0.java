package com.test;
import java.io.IOException;
//import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
public class IncreaseServlet extends HttpServlet{
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String name = request.getParameter("commodity");
        float price = Integer.valueOf(request.getParameter("price")).floatValue();
        int number = Integer.valueOf(request.getParameter("number"));
        //response.setCharacterEncoding("UTF-8");
        //PrintWriter out = response.getWriter();
        ServletContext application = getServletContext();
        HashMap mall = (HashMap)application.getAttribute("Mall");
        if (mall == null){  //商品表单为空
            mall = new HashMap();  //新建一个商品清单
        }
        if (name != null) {  //商品名不为空
            GoodsServlet goods = new GoodsServlet(name,price,number);  //实例化商品
            mall.put(goods.getName(),goods);
            application.setAttribute("Mall",mall);
            response.sendRedirect("shopping.jsp");
            //out.print("三秒后返回提交界面.");
            //response.setHeader("refresh", "3;url=submit.jsp");
        }
    }
    public void doPost(HttpServletRequest request,
                       HttpServletResponse reponse) throws ServletException, IOException{
        this.doGet(request,reponse);
    }

}

