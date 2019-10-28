package com.test;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class RemoveServlet extends HttpServlet{
    //doGet方法
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();  //获取session
        ServletContext application = getServletContext();  //获取application
        String name = new String(request.getParameter("name").getBytes("ISO-8859-1"),
                "utf-8");  //获取要删除的商品名
        HashMap goods = (HashMap) session.getAttribute("sessionGoods");  //获取购物车中的清单
        Object sum = session.getAttribute("SUM");  //获取客户的总消费金额
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (goods == null){
            out.print("抱歉,您的购物车为空.");
        }else{
        //将商品从购物车中移除
        float sumer;
        Set set = goods.keySet();
        Iterator ite = set.iterator();
        if (sum == null) {
            sumer = 0;
        } else {
            sumer = ((Float) sum).floatValue();
            int demandnumber = 0;  //商品的订单量
            float price = 0;  //商品的价钱
            GoodServlet good = null;
            GoodServlet copyGood = new GoodServlet();
            while(ite.hasNext()) {
                String mark = (String)ite.next();
                good = (GoodServlet) goods.get(mark);
                if (good.getName().equals(name)) {  //找到用户要退的商品
                    demandnumber = good.getNumber();  //保存客户退货量
                    price = good.getPrice();  //保存商品价格
                    sumer -= demandnumber * price;  //将该类商品的花费减去
                   // goods.remove(mark);  //移除该商品
                    break;
                }
            }
            session.setAttribute("SUM", new Float(sumer));  //将更新过的总金额存入session
            session.setAttribute("sessionGoods", goods);  //将更新过的商品表单存入session
            //更新商城中商品的库存
            goods = (HashMap) application.getAttribute("applicationGoods");  //获取商城中商品的清单
            set = goods.keySet();
            ite = set.iterator();
           while(ite.hasNext()) {
               String mark = (String) ite.next();
                good = (GoodServlet) goods.get(mark);
                if (good.getName().equals(name)) {
                    int number = good.getNumber();
                    copyGood.setName(name);
                    copyGood.setPrice(price);
                    copyGood.setNumber(number + demandnumber);
                    //goods.remove(mark);
                    goods.put(copyGood.getName(),copyGood);
                    break;
                }
            }
            application.setAttribute("applicationGoods", goods);  //将更新过的商城商品表单存入application
            response.sendRedirect("showCart.jsp");
        }
        }
    }
    //doPost方法
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,IOException{
        this.doGet(request,response);
    }
}
