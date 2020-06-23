package kr.ac.jejunu.user;

import org.springframework.format.annotation.DateTimeFormat;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


@WebFilter(urlPatterns = "*")
public class UserFilter implements Filter { //서블릿 앞단에서 처리

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("*****************filter init******************");
    }

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("*****************filter before******************");
        request.setCharacterEncoding("UTF-8");//한글깨짐방지
        chain.doFilter(request,response);
        System.out.println("*****************filter after******************");
    }

    @Override
    public void destroy() {
        System.out.println("*****************filter destroy******************");
    }
}
