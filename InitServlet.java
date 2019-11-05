package servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import dao.GoodsDao;
import java.util.HashMap;
public class InitServlet extends HttpServlet{
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException{
        GoodsDao gDao = new GoodsDao();
        HashMap mall = null;
        try{
            mall = gDao.getAllGoods();  //将查询方法返回的哈希表存入mall
        }catch(Exception e){
            e.printStackTrace();  //打印调用栈
        }
        request.getSession().setAttribute("MALL",mall);  //将mall存入Session
        response.sendRedirect("shop.jsp");  //跳转到shop.jsp页面
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException{
        this.doGet(request,response);
    }
}
