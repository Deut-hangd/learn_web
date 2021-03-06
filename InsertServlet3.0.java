package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vo.Goods;
import dao.GoodsDao;

public class InsertServlet extends HttpServlet{
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String goodsId = request.getParameter("goodsId");
        String goodsName = request.getParameter("goodsName");
        Float goodsPrice = Integer.valueOf(request.getParameter("goodsPrice")).floatValue();
        String goodsType = request.getParameter("goodsType");
        Integer goodsNumber = Integer.valueOf(request.getParameter("goodsNumber"));
        PrintWriter out = response.getWriter();
        int i = 0;
        //若要添加的商品名不为空
        if (goodsId != null && goodsName != null && goodsType != null){
            GoodsDao gDao = new GoodsDao();  //实例化一个GoodsDao对象
            Goods goods = new Goods();
            //设置商品的信息
            goods.setGoodsId(goodsId);
            goods.setGoodsName(goodsName);
            goods.setGoodsPrice(goodsPrice);
            goods.setGoodsType(goodsType);
            goods.setGoodsNumber(goodsNumber);
            try {
                i = gDao.insertGoods(goods);
            }catch(Exception e){
                e.printStackTrace();  //打印调用栈
            }finally{
                out.print("成功添加" + i + "行");
            }
        }
        }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,IOException{
        this.doGet(request,response);
    }

}
