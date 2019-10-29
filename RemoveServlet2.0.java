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
        String name = new String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
        HashMap cart = (HashMap) session.getAttribute("Cart");  //获取购物车中的清单
        Object sum = session.getAttribute("SUM");  //获取客户的总消费金额
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (cart.isEmpty()){
            out.print("抱歉,您的购物车为空.");
        }else{
        //将商品从购物车中移除
        float sumer;
        Set set = cart.keySet();
        Iterator ite = set.iterator();
        if (sum == null) {
            sumer = 0;
        } else {
            sumer = ((Float) sum).floatValue();
            int demandNumber = 0;  //商品的订单量
            float price = 0;  //商品的价钱
            GoodsServlet goods = null;
            GoodsServlet copyGoods = new GoodsServlet();
            //遍历购物车中的商品
            while(ite.hasNext()) {
                String mark = (String)ite.next();
                goods = (GoodsServlet) cart.get(mark);
                if (name.equals(goods.getName())) {  //找到用户要退的商品
                    //保存匹配到的商品信息
                    demandNumber = goods.getNumber();  //保存客户退货量
                    name = goods.getName();
                    price = goods.getPrice();  //保存商品价格
                    sumer -= demandNumber * price;  //将该类商品的花费减去
                    break;
                }
            }
            cart.remove(goods.getName());  //移除该商品
            session.setAttribute("SUM", new Float(sumer));  //将更新过的总金额存入session
            session.setAttribute("Cart", cart);  //将更新过的商品表单存入session
            //更新商城中商品的库存
            HashMap mall = (HashMap) application.getAttribute("Mall");  //获取商城中商品的清单
            set = mall.keySet();
            ite = set.iterator();
           while(ite.hasNext()) {  //遍历商城中的商品
               String mark = (String) ite.next();
                goods = (GoodsServlet) mall.get(mark);
                if (name.equals(goods.getName())) {  //匹配商品名
                    int number = goods.getNumber();  //获取该商品的剩余量
                    //重新设置该商品的信息
                    copyGoods.setName(name);
                    copyGoods.setPrice(price);
                    copyGoods.setNumber(number + demandNumber);
                    break;
                }
            }
            mall.remove(goods.getName());  //从商城里移除该商品
            mall.put(goods.getName(),copyGoods);  //将该商品重新放入商城
            application.setAttribute("Mall", mall);  //将更新过的商城商品表单存入application
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
