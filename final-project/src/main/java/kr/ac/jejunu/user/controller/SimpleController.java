package kr.ac.jejunu.user.controller;

import kr.ac.jejunu.user.dao.UserDao;
import kr.ac.jejunu.user.data.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class SimpleController implements Controller {
    private final UserDao userDao;
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = userDao.get(Integer.valueOf(request.getParameter("id")));
        ModelAndView modelAndView = new ModelAndView("user");
        modelAndView.addObject("user",user);
        return modelAndView;
    }
}
