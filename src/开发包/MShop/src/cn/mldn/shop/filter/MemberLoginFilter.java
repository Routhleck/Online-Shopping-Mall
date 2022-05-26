package cn.mldn.shop.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "MemberLoginFilter", urlPatterns = {"/pages/front/shopcar/*","/pages/front/member/*","/pages/front/orders/*"})
public class MemberLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession ses = request.getSession();
        if (ses.getAttribute("mid") != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            request.setAttribute("msg", "您还没有登录，请先进行登录后操作！");
            request.setAttribute("url", "/pages/member_login.jsp");
            request.getRequestDispatcher("/pages/forward.jsp").forward(servletRequest
                    , servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
