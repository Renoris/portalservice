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
        request.setCharacterEncoding("UTF-8");//한글깨짐방지
        String requestUrl = request.getRequestURL().toString();
        if(requestUrl.contains("/login")){
            return true;
        }
        else if (requestUrl.contains("/createaccount")){
            return true;
        }
        else if (requestUrl.contains("/resources")) {
            return true;
        }

        else {
            HttpSession session = request.getSession();
            UserAccount userAccount = (UserAccount)session.getAttribute("userAccount");//세션에서 유저어카운트정보확인 및 가져오기
            String name= userAccount.getName();
            if(name.equals(null)){
                response.sendRedirect("/login.html");
                return false;
            }
            else{
//                return HandlerInterceptor.super.preHandle(request, response, handler);
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
