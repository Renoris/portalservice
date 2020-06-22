package kr.ac.jejunu.user.controller;

import kr.ac.jejunu.user.dao.AccountDao;
import kr.ac.jejunu.user.data.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final AccountDao accountdao;

    @GetMapping(path ="/login")
    public void account(){

    }

    @PostMapping(path ="/login")
    public Model Login(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws IOException {
        String name = request.getParameter("name");
        System.out.println("name:"+name);
        String password = request.getParameter("password");
        System.out.println("password:"+password);
        try{
            UserAccount userAccount=accountdao.get(name);
            if(userAccount.getName().equals(name)&&userAccount.getPassword().equals(password)){
                session.setAttribute("userAccount",userAccount);
                model.addAttribute("msg", "로그인 성공");
                response.sendRedirect("/lobby.html");
                return model;
            }
            else {
                model.addAttribute("msg", "로그인 정보가 맞지 않습니다");
                return model;
            }
        }catch (Exception e){
            model.addAttribute("msg", "로그인 정보가 맞지 않습니다");
            return model;
        }

    }



}
