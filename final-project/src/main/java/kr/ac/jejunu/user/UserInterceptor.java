package kr.ac.jejunu.user;

import kr.ac.jejunu.user.dao.AccountDao;
import kr.ac.jejunu.user.data.UserAccount;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UserInterceptor implements HandlerInterceptor { //servlet에서 핸들러로 가기전에 처리


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUrl = request.getRequestURL().toString();
        HttpSession session = request.getSession();
        if(requestUrl.contains("/login")){
            return true;
        }
        else if (requestUrl.contains("/createaccount")){
            return true;
        }
        else {
            Object obj = session.getAttribute("userAccount");
            if (obj == null) {
                response.sendRedirect("/login");
                return false; // 더이상 컨트롤러 요청으로 가지 않도록 false로 반환함
            }
            else{
                return true;
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("****** interceptor posthandle **************");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("****** interceptor aftercompletion **************");
    }
}
