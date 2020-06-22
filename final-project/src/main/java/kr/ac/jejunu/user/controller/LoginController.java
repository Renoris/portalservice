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
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final AccountDao accountdao;

    @GetMapping(path ="/login")
    public void account(){

    }

    @PostMapping(path ="/login")
    public String InsertUserAccount(HttpServletRequest request, Model model, HttpSession session){
        UserAccount userAccount=new UserAccount();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        userAccount=accountdao.get(name);

        if(userAccount.getName().equals(name)&&userAccount.getPassword().equals(password)){
            session.setAttribute("userAccount",userAccount);
            return "redirect:/";
        }

        else{
            model.addAttribute("msg", "로그인 정보가 맞지 않습니다");
            model.addAttribute("url", "login.jsp");
            return "redirect:/login";
        }
    }


    @ExceptionHandler(Exception.class)
    public ModelAndView error(Exception e){
        ModelAndView modelAndView=new ModelAndView("error");
        modelAndView.addObject("e",e);
        return modelAndView;
    }
}
