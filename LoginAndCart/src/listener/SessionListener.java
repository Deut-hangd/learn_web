package listener;
import java.util.HashMap;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
public class SessionListener implements HttpSessionListener{
    public void sessionCreated(HttpSessionEvent event){
        HttpSession session = event.getSession();
        //初始化购物车
        HashMap cart = new HashMap();
        session.setAttribute("CART",cart);
        //初始化总钱数
        session.setAttribute("MONEY",0);
    }

    public void sessionDestroyed(HttpSessionEvent arg0) {}
}
