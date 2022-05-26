package cn.mldn.shop.filter;


import cn.mldn.shop.factory.ServiceFrontFactory;
import cn.mldn.shop.vo.Member;
import cn.mldn.util.CookieUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebFilter(filterName = "AutoLoginFilter", urlPatterns = {"/index.jsp", "/pages/front/*"})
public class AutoLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;  // 取得session
        HttpSession ses = request.getSession();
        if (ses.getAttribute("mid") == null) {  // 现在还没有登录过，则应该取出全部的Cookie数据
            Map<String, String> map = CookieUtil.load(request);
            // 如果存在有保存的mid与password两个Cookie数据，那么就可以进行业务验证
            if (map.containsKey("mid") && map.containsKey("password")) {
                Member vo = new Member();
                vo.setMid(map.get("mid"));
                vo.setPassword(map.get("password"));
                try {
                    if (ServiceFrontFactory.getIMemberServiceFrontInstance().login(vo)) {
                        ses.setAttribute("mid", vo.getMid());
                        ses.setAttribute("photo", vo.getPhoto());
                    }
                } catch (Exception e) {
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
