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
        HashMap mall = new HashMap();  //实例化一个哈希表
        this.initConnection();
        stat = conn.createStatement();
        String sql = "SELECT 商品编号,商品名,商品价格,商品类型,商品数量 FROM T_GOODS";  //sql查询语句
        rs = stat.executeQuery(sql);
        //遍历数据库查询结果
        while (rs.next()){
            Goods goods = new Goods();  //实例化一个商品对象
            goods.setGoodsId(rs.getString("商品编号").trim());
            goods.setGoodsName(rs.getString("商品名").trim());
            goods.setGoodsPrice(rs.getFloat("商品价格"));
            goods.setGoodsType(rs.getString("商品类型").trim());
            goods.setGoodsNumber(rs.getInt("商品数量"));
            mall.put(goods.getGoodsId(),goods);  //将商品信息存入哈希表
        }
        this.closeResultSet();
        this.closeStatement();
        this.closeConnection();
        return mall;
    }

    //向数据库中添加一件商品
    public int insertGoods(Goods goods) throws Exception{
        this.initConnection();
        String sql = "INSERT INTO T_GOODS(商品编号,商品名,商品价格,商品类型,商品数量)" +
                " VALUES(?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,goods.getGoodsId());
        ps.setString(2,goods.getGoodsName());
        ps.setFloat(3,goods.getGoodsPrice());
        ps.setString(4,goods.getGoodsType());
        ps.setInt(5,goods.getGoodsNumber());
        int i = ps.executeUpdate();
        ps.close();
        conn.close();
        return i;
    }

    //修改数据库中的数据
    public int updateGoods(Goods goods) throws Exception{
        this.initConnection();
        stat = conn.createStatement();
        String sql = "UPDATE T_GOODS SET 商品数量 = " + goods.getGoodsNumber() +
                "WHERE 商品编号 = " + goods.getGoodsId();
        int i = stat.executeUpdate(sql);
        stat.close();
        conn.close();
        return i;
    }

    public void initConnection() throws Exception{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
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
