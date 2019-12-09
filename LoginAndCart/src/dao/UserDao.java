package dao;
import java.sql.*;
import java.util.HashMap;
import vo.User;
public class UserDao {
    private Connection conn = null;
    private Statement stat = null;
    private ResultSet rs = null;

    //从数据库中获取用户的信息
    public HashMap<String, User> getUser(String name) throws Exception{
        HashMap<String, User> users = new HashMap<>();  //用以存储用户信息
        this.initConnection();  //连接数据库
        String sql = "SELECT 用户名, 密码 FROM T_USERS" + " WHERE 用户名=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        rs = ps.executeQuery();  //获取查询结果集
        while(rs.next()){  //遍历结果集,并将用户信息存入users
            //拷贝查询到的用户信息
            String userName = rs.getString("用户名");
            String password = rs.getString("密码");
            User user = new User();
            user.setUserName(userName.trim());
            user.setPassword(password.trim());
            users.put(userName,user);  //将查询结果存入users
        }
        this.closeResultSet();
        ps.close();
        this.closeConnection();
        return users;
    }

    //将用户的注册信息加入数据库
    public int insertUser(User user) throws Exception{
        this.initConnection();
        String sql = "INSERT INTO T_USERS(用户名,密码)" + "VALUES(?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,user.getUserName());
        ps.setString(2,user.getPassword());
        int i = ps.executeUpdate();
        ps.close();
        this.closeConnection();
        return i;
    }

    //连接数据库
    public void initConnection() throws Exception{
        //加载JDBC驱动程序
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //创建数据库连接
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=User";
        conn = DriverManager.getConnection(url,"sa","sa");
    }

    public void closeResultSet() throws Exception{
        rs.close();
        rs = null;
    }

    public void closeStatment() throws Exception{
        stat.close();
        stat = null;
    }

    public void closeConnection() throws Exception{
        conn.close();
        conn = null;
    }
}
