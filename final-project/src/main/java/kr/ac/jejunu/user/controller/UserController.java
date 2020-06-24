package kr.ac.jejunu.user.controller;

import kr.ac.jejunu.user.dao.UserDao;
import kr.ac.jejunu.user.data.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;

    @RequestMapping(path = "/user")
    public User getUser(@RequestParam("id") Integer id){
        return userDao.get(id);
    }

    @GetMapping("/userall")
    public void getUserALL(Model model){
        model.addAttribute("userList", userDao.getUserAll());
    }





    @RequestMapping("/exception")
    public void exception(){
        throw new RuntimeException("어이쿠!");
    }

    @GetMapping("/upload")
    public void upload(){

    }


    @ExceptionHandler(Exception.class)
    public ModelAndView error(Exception e){
        ModelAndView modelAndView=new ModelAndView("error");
        modelAndView.addObject("e",e);
        return modelAndView;
    }
}
