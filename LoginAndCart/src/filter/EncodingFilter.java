package filter;
import java.io.IOException;
import javax.servlet.*;

public class EncodingFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {}
    public void destroy(){}
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException{
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request,response);
    }
}
