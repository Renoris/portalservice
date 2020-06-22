package kr.ac.jejunu.user.controller;

import kr.ac.jejunu.user.dao.AccountDao;
import kr.ac.jejunu.user.dao.UserDao;
import kr.ac.jejunu.user.data.User;
import kr.ac.jejunu.user.data.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class CreateAccountController {
    private final AccountDao accountdao;

    @GetMapping(path ="/createaccount")
    public void account(){

    }

    @PostMapping(path ="/createaccount")
    public String InsertUserAccount(HttpServletRequest request){
        UserAccount userAccount=new UserAccount();
        String name = request.getParameter("name");
        System.out.println(name);
        String password = request.getParameter("password");
        System.out.println(password);
        userAccount.setName(name);
        userAccount.setPassword(password);
        userAccount.setAdmin(false);
        accountdao.insert(userAccount);
        return "redirect:/login";
    }


    @ExceptionHandler(Exception.class)
    public ModelAndView error(Exception e){
        ModelAndView modelAndView=new ModelAndView("error");
        modelAndView.addObject("e",e);
        return modelAndView;
    }
}
