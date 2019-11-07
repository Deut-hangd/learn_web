package servlet;
import dao.GoodsDao;
import vo.Goods;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RemoveServlet extends HttpServlet{
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        //获取session
        HttpSession session = request.getSession();
        //获取application
        ServletContext application = this.getServletConfig().getServletContext();
        //获取表单提交的参数
        String goodsId = request.getParameter("goodsId");
        int removeNumber = Integer.valueOf(request.getParameter("removeNumber")).intValue();
        HashMap cart = (HashMap)session.getAttribute("CART");  //实例化哈希表存储购物车内容
        HashMap mall = (HashMap)application.getAttribute("MALL");  //实例化哈希表存储所有商品信息
        Object sum = session.getAttribute("MONEY");
        //遍历cart,匹配商品id
        Set set = cart.keySet();
        Iterator ite = set.iterator();
        String mark;
        boolean flag1 = false;
        Goods goods = null;
        while (ite.hasNext()){
            mark = (String)ite.next();
            goods = (Goods)cart.get(mark);
            if (goodsId.equals(goods.getGoodsId())){  //匹配成功
                flag1 = true;
                break;
            }
        }
        //若匹配成功
        if (goods != null && flag1){
            String name = URLEncoder.encode(goods.getGoodsName(),"UTF-8");
            int number = goods.getGoodsNumber();  //获取商品数量
            float price = goods.getGoodsPrice();  //获取商品价格
            if (removeNumber > number){  //若要移除的商品数大于实际购买的商品数
                response.sendRedirect("error.jsp?goodsName="+ name +
                "&goodsPrice=" + goods.getGoodsPrice() + "&goodsNumber=" + goods.getGoodsNumber());
                return;
            }else if (removeNumber == number){  //若该商品的购买量为零
                //移除该商品
                cart.remove(goods.getGoodsId());
            }else{
                goods.setGoodsNumber(number - removeNumber);  //设置新的购买量
                cart.put(goods.getGoodsId(),goods);
            }
            //减去该商品钱数
            float money;
            if (sum == null){  //若sum为空
                money = 0;
            }else{
                money = ((Float)sum).floatValue();  //将sum转为float型
            }
            money = money - price * removeNumber;
            session.setAttribute("MONEY",money);  //将更新过的money存入session
            session.setAttribute("CART",cart);  //将更新过的购物车存入session
            //更新商城和数据库中的商品信息
            Goods goods1 = null;
            boolean flag2 = false;
            GoodsDao gDao = new GoodsDao();
            set = mall.keySet();
            ite = set.iterator();
            while(ite.hasNext()){  //遍历匹配商品id
                mark = (String)ite.next();
                goods1 = (Goods)mall.get(mark);
                if (goodsId.equals(goods1.getGoodsId())){
                    flag2 = true;
                    break;
                }
            }
            //若id匹配成功
            if (goods1 != null && flag2){
                goods1.setGoodsNumber(goods1.getGoodsNumber() + removeNumber);
                mall.put(goods1.getGoodsId(),goods1);
                application.setAttribute("MALL",mall);
                try {
                    gDao.updateGoods(goods1);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            response.sendRedirect("showCart.jsp");
            return;
        }else{

        }
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException{
        this.doGet(request,response);
    }
}
