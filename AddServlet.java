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
        String goodname = request.getParameter("goodname");  //获取商品名
        float goodprice = Float.valueOf(request.getParameter("goodprice"));  //获取商品价格
        int goodnumber = Integer.valueOf(request.getParameter("goodnumber"));  //获得商品库存
        int demandnumber = Integer.valueOf(request.getParameter("number"));  //获取客户的订单量
        ServletContext application = getServletContext();  //获取application
        HashMap goods = (HashMap) application.getAttribute("applicationGoods");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html charset=UTF-8");
        PrintWriter out = response.getWriter();
        //更新商城中商品的库存
        if (goodnumber < demandnumber){  //库存不足
            out.print("您需要的量太大,库存不足.<br>");
            out.print(goodname +":&nbsp&nbsp" + "现在的存量为" + goodnumber + "<br>");
            out.print("<a href='shopping.jsp'>返回购物界面</a>");
        }else {
            GoodServlet good = null;
            Set set = goods.keySet();
            Iterator ite = set.iterator();
            while (ite.hasNext()) {
                String mark = (String) ite.next();
                good = (GoodServlet) goods.get(mark);
            if (goodname.equals(good.getName())) {  //匹配商品名
                GoodServlet copyGood = new GoodServlet();
                copyGood.setName(good.getName());
                copyGood.setPrice(good.getPrice());
                int number = goodnumber - demandnumber;  //计算剩余库存量
                copyGood.setNumber(number);  //修改库存量
                goods.remove(mark);
                goods.put(mark,copyGood);
            }
        }
            application.setAttribute("applicationGoods",goods);  //将更新过的商品表单存入application
            //设置购物车
            float sumer;  //保存总金额
            Object sum = session.getAttribute("SUM");  //获取sum的值
            if (sum == null){  //用户第一次使用本系统
                sumer = 0;
            }else{
                sumer = ((Float)sum).floatValue();
            }
            sumer += goodprice * demandnumber;  //统计价格
            session.setAttribute("SUM",new Float(sumer));  //将更新过的sum存入session
            HashMap goods1 = (HashMap) session.getAttribute("sessionGoods");
            GoodServlet good1 =null;
            String mark = goodname;
            GoodServlet copyGood1 = new GoodServlet();
            if (goods1 == null){  //若购物车为空
                goods1 = new HashMap();
                copyGood1.setName(goodname);
                copyGood1.setPrice(goodprice);
                copyGood1.setNumber(demandnumber);
                goods1.put(mark,copyGood1);  //将商品加入购物车清单
                session.setAttribute("sessionGoods",goods1);
            }else{
                 set = goods1.keySet();
                 ite = set.iterator();
                while (ite.hasNext()) {
                     mark = (String) ite.next();
                     good1 = (GoodServlet)goods1.get(mark);
                    if (goodname.equals(good1.getName())) {  //匹配商品名
                        copyGood1.setName(good1.getName());
                        copyGood1.setPrice(good1.getPrice());
                        copyGood1.setNumber(demandnumber);
                        goods1.remove(mark);
                        goods1.put(mark,copyGood1);  //将商品加入购物车清单
                    }
                }
                session.setAttribute("sessionGoods",goods1);
            }
            response.sendRedirect("showCart.jsp");
        }
    }
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,IOException{
        this.doGet(request,response);
    }

}
