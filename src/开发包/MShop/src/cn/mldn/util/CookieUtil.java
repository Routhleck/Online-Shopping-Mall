package cn.mldn.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CookieUtil {
    /**
     * 定义一个专门负责保存Cookie的方法，可以设置Cookie的名字与保存时间
     * @param response 主要可以使用addCookie()方法保存Cookie对象
     * @param request 主要设置Cookie的保存路径，如果不设置保存不上
     * @param name     要保存的Cookie名字
     * @param value    要保存的Cookie的内容
     * @param expiry   Cookie的失效时间
     */
    public static void save(HttpServletResponse response, HttpServletRequest request, String name, String value, int expiry) {
        Cookie c = new Cookie(name, value);
        c.setMaxAge(expiry);
        // c.setPath(request.getContextPath()); // 设置保存路径
        c.setPath("/"); // 设置保存路径
        response.addCookie(c);
    }

    public static Map<String,String> load(HttpServletRequest request) {
        Map<String,String> map = new HashMap<String,String>() ;
        Cookie c [] = request.getCookies() ;
        if (c!=null) {
            for (int x = 0; x < c.length; x++) {
                if (!"JSESSIONID".equals(c[x].getName())) {
                    map.put(c[x].getName(), c[x].getValue());
                }
            }
        }
        return map ;
    }
    public static void clear(HttpServletRequest request,HttpServletResponse response) {
        Map<String,String> map = load(request) ;
        Iterator<Map.Entry<String,String>> iter = map.entrySet().iterator() ;
        while (iter.hasNext()) {
            Map.Entry<String,String> me = iter.next() ;
            Cookie c = new Cookie(me.getKey() , "") ;
            c.setPath("/");
            c.setMaxAge(0);
            response.addCookie(c);
        }
    }
}
