package servlet;
import vo.Goods;
import dao.GoodsDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddServlet extends HttpServlet{
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String goodsId = request.getParameter("goodsId");
        int demandNumber = Integer.valueOf(request.getParameter("demandNumber"));
        ServletContext application = getServletConfig().getServletContext();
        HashMap mall = (HashMap)application.getAttribute("MALL");  //获取商场商品表单
        if (goodsId != null) {
            Goods goods = (Goods) mall.get(goodsId);
            //获取参数
            String strGoodsId = goods.getGoodsId();
            String strGoodsName = goods.getGoodsName();
            float strGoodsPrice = goods.getGoodsPrice();
            String strGoodsType = goods.getGoodsType();
            int strGoodsNumber = goods.getGoodsNumber();
            //判定用户订单量是否合理
            if (demandNumber > strGoodsNumber){
                response.sendRedirect("showNumber.jsp?goodsName=" +strGoodsName +
                        "&goodsNumber=" + strGoodsNumber);
            }
            //更新商城和数据库中的数据
            int i = 0;
            goods.setGoodsNumber(strGoodsNumber - demandNumber);
            mall.put(strGoodsId, goods);
            application.setAttribute("MALL",mall);
            GoodsDao gDao = new GoodsDao();  //实例化GoodsDao对象
            try {
                i = gDao.updateGoods(goods);  //修改数据库中的数据
            } catch (Exception e) {
                e.printStackTrace();  //打印调用栈
            }finally{
                //out.print("共有" + i + "行数据被修改.");
            }

            //获取session
            HttpSession session = request.getSession();
            //存入购物车
            HashMap cart = (HashMap)session.getAttribute("CART");
            Goods goods1 = new Goods();
            goods1.setGoodsId(goods.getGoodsId());
            goods1.setGoodsName(goods.getGoodsName());
            goods1.setGoodsPrice(goods.getGoodsPrice());
            goods1.setGoodsType(goods.getGoodsType());
            int existNumber = 0;
            if (cart == null){
                cart = new HashMap();
            }
            Set set = cart.keySet();
            Iterator ite = set.iterator();
            while (ite.hasNext()){
                String mark = (String)ite.next();
                Goods goods2 = (Goods)cart.get(mark);
                if (goodsId.equals(goods2.getGoodsId())){
                    existNumber = goods2.getGoodsNumber();
                }
            }
            goods1.setGoodsNumber(demandNumber + existNumber);
            cart.put(strGoodsId,goods1);
            session.setAttribute("CART", cart);

            //总钱数增加
            float money = 0;
            Object sum = session.getAttribute("MONEY");
            if (sum == null){
                money = 0;
            }else{
                money = ((Float)sum).floatValue();
            }
            money = money + demandNumber * strGoodsPrice;
            session.setAttribute("MONEY",money);
            response.sendRedirect("showCart.jsp");
        }
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,IOException{
        this.doGet(request,response);
    }
}
