import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.Date;
public class Welcome extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        int counter = 0;  //记录用户登录次数
        HttpSession hs = request.getSession(true);  //获取Httpsession对象
        Object count = hs.getAttribute("COUNTER");  //获取属性COUNTER的值
        String isOk = (String)hs.getAttribute("isOk");  //获取属性isOk的值
        Cookie[] cookies = request.getCookies();  //获取cookie数组
        String u = request.getParameter("username");  //获取username的值
        String p = request.getParameter("password");  //获取password的值
        response.setCharacterEncoding("UTf-8");  //设置响应头的编码方式
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();  //获得一个向客户端输出数据的输出流
        //统计用户访问网站的次数
        if (count == null){  //若用户是第一次访问本页面
            counter = 1;

        }else{  // 若用户不是第一次访问
            //输出用户上一次访问时间
            for (Cookie cookie : cookies) {  //遍历cookies数组
                if (cookie.getName().equals("lastAccessTime")) {  //找到匹配的cookie
                    pw.print("您上次的访问时间是:");
                    Long lastAccessTime = Long.parseLong(cookie.getValue());  //将cookie的值转化为Long类
                    Date date = new Date(lastAccessTime);  //创建一个Dete类的对象
                    pw.print(date.toLocaleString());  //以字符串类型输出时间
                }
            }
            counter = ((Integer)count).intValue();
            counter++;  //访问记录加1
        }
        //用cookie保存用户本次登录的时间
        Cookie cookieT = new Cookie("lastAccessTime",System.currentTimeMillis() + "");  //创建cookie
        cookieT.setMaxAge(30);
        response.addCookie(cookieT);
        //设置Counter属性的值
        hs.setAttribute("COUNTER",new Integer(counter));
        //判定用户是否是合法用户
        if (isOk == null){
            response.sendRedirect("LoginServlet");  //返回登录界面
            return;
        }else{
            //进入欢迎界面
            pw.println("<h1>主界面</h1>");
            pw.print("欢迎您" + u + "第" + counter +"次访问本页面.<br>");
            pw.print("您的密码是:" + p + "<br>");
            pw.print("是否允许访问本页面? " + isOk + "<br>");
            pw.println("<a href = 'LoginServlet'>返回重新登录.</a>");
        }
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        this.doGet(request,response);
    }
}
