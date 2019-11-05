package dao;
import java.sql.*;
import java.util.HashMap;
import vo.Goods;

public class GoodsDao {
    private Connection conn = null;
    private Statement stat = null;
    private ResultSet rs = null;

    //获取商场中的所有商品信息
    public HashMap getAllGoods() throws Exception{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        HashMap mall = new HashMap();  //实例化一个哈希表
        this.initConnection();
        stat = conn.createStatement();
        String sql = "SELECT 商品编号,商品名,商品价格,商品类型,商品数量 FROM T_GOODS";  //sql查询语句
        rs = stat.executeQuery(sql);
        //遍历数据库查询结果
        while (rs.next()){
            Goods goods = new Goods();  //实例化一个商品对象
            goods.setGoodsId(rs.getString("商品编号"));
            goods.setGoodsName(rs.getString("商品名"));
            goods.setGoodsPrice(rs.getFloat("商品价格"));
            goods.setGoodsType(rs.getString("商品类型"));
            goods.setGoodsNumber(rs.getInt("商品数量"));
            mall.put(goods.getGoodsId(),goods);  //将商品信息存入哈希表
        }
        this.closeResultSet();
        this.closeStatement();
        this.closeConnection();
        return mall;
    }

    //向数据库中添加一件商品
    public int insertGoods(String goodsId, String goodsName, float goodsPrice,
                            String goodsType, int goodsNumber) throws Exception{
        this.initConnection();
        String sql =
                "INSERT INTO T_GOODS(商品编号,商品名,商品价格,商品类型,商品数量) VALUES(?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,goodsId);
        ps.setString(2,goodsName);
        ps.setFloat(3,goodsPrice);
        ps.setString(4,goodsType);
        ps.setInt(5,goodsNumber);
        int i = ps.executeUpdate();
        ps.close();
        conn.close();
        return i;
    }

    public void initConnection() throws Exception{
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Mall";
        conn = DriverManager.getConnection(url,"sa","sa");
    }

    public void closeStatement() throws Exception{
        stat.close();
        stat = null;
    }

    public void closeResultSet() throws Exception{
        rs.close();
        rs = null;
    }

    public void closeConnection() throws Exception{
        conn.close();
        conn = null;
    }
}
