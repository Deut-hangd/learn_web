package com.test;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
public class AddServlet extends HttpServlet{
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,IOException{
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);  //获取session
        String goodsName = request.getParameter("goodsName");  //获取商品名
        float goodsPrice = Float.valueOf(request.getParameter("goodsPrice"));  //获取商品价格
        int goodsNumber = Integer.valueOf(request.getParameter("goodsNumber"));  //获得商品库存
        int demandNumber = Integer.valueOf(request.getParameter("number"));  //获取客户的订单量
        ServletContext application = getServletContext();  //获取application
        HashMap mall = (HashMap) application.getAttribute("Mall");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html charset=UTF-8");
        PrintWriter out = response.getWriter();
        //更新商城中商品的库存
        if (goodsNumber < demandNumber){  //库存不足
            out.print("您需要的量太大,库存不足.<br>");
            out.print(goodsName +":&nbsp&nbsp" + "现在的存量为" + goodsNumber + "<br>");
            out.print("<a href='shopping.jsp'>返回购物界面</a>");
        }else {
            GoodsServlet goods = null;
            GoodsServlet copyGoods = null;
            String mark = null;
            Set set = mall.keySet();
            Iterator ite = set.iterator();
            while (ite.hasNext()) {
                mark = (String) ite.next();
                goods = (GoodsServlet) mall.get(mark);
                if (goodsName.equals(goods.getName())) {  //匹配商品id
                    copyGoods = new GoodsServlet();
                    copyGoods.setName(goods.getName());
                    copyGoods.setPrice(goods.getPrice());
                    int number = goodsNumber - demandNumber;  //计算剩余库存量
                    copyGoods.setNumber(number);  //修改库存量
                    break;
                }
            }
            mall.remove(goods.getName());
            mall.put(goods.getName(), copyGoods);
            application.setAttribute("Mall", mall);  //将更新过的商品表单存入application
            //设置购物车
            float sumer;  //保存总金额
            boolean flag = false;
            Object sum = session.getAttribute("SUM");  //获取sum的值
            if (sum == null) {  //用户第一次使用本系统
                sumer = 0;
            } else {
                sumer = ((Float) sum).floatValue();
            }
            sumer += goodsPrice * demandNumber;  //统计价格
            session.setAttribute("SUM", new Float(sumer));  //将更新过的sum存入session
            HashMap cart = (HashMap) session.getAttribute("Cart");
            if (cart == null){//若购物车为空
                cart = new HashMap();
                copyGoods = new GoodsServlet();
                copyGoods.setName(goodsName);
                copyGoods.setPrice(goodsPrice);
                copyGoods.setNumber(demandNumber);
                cart.put(goods.getName(), copyGoods);  //将商品加入购物车清单
                session.setAttribute("Cart", cart);
            }else {
                set = cart.keySet();
                ite = set.iterator();
                while (ite.hasNext()) {
                    mark = (String) ite.next();
                    goods = (GoodsServlet) cart.get(mark);
                    if (goodsName.equals(goods.getName())) {
                        demandNumber += goods.getNumber();
                        copyGoods = new GoodsServlet(goods.getName(), goods.getPrice(), demandNumber);
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    cart.remove(goods.getName());
                    cart.put(goods.getName(), copyGoods); //将商品加入购物车清单
                    session.setAttribute("Cart", cart);

                } else {
                    copyGoods = new GoodsServlet(goodsName, goodsPrice, demandNumber);
                    cart.put(goodsName, copyGoods);  //将商品加入购物车清单
                    session.setAttribute("Cart", cart);
                }
            }
            response.sendRedirect("showCart.jsp");
        }

        }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,IOException{
        this.doGet(request,response);
    }

}
