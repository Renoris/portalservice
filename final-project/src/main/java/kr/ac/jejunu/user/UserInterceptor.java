package kr.ac.jejunu.user;

import kr.ac.jejunu.user.dao.AccountDao;
import kr.ac.jejunu.user.data.UserAccount;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UserInterceptor implements HandlerInterceptor { //servelt에서 핸들러로 가기전에 처리
    private AccountDao accountDao=new AccountDao();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            HttpSession session = request.getSession();
            UserAccount userAccount = (UserAccount)session.getAttribute("userAccount");//세션에서 유저어카운트정보확인 및 가져오기
            UserAccount confirmAccount=accountDao.getmatch(userAccount.getId());//세션에잇는 유저와 데이터베이스의 유저를 대조하기 위한것
            String name= userAccount.getName();
            String confirmname= confirmAccount.getName();
            String requestUrl = request.getRequestURL().toString();
            if(requestUrl.contains("/login.html")){
                return true;
            }
            else if (requestUrl.contains("/createaccount.html")){
                return true;
            }
            else {
                if (name.equals(confirmname)) {
                    return true;
                }
                else{
                    response.sendRedirect("/login.html");
                    return HandlerInterceptor.super.preHandle(request, response, handler);
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
