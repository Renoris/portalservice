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

    @PostMapping("/upload")
    public ModelAndView upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        File path = new File(request.getServletContext().getRealPath("/")+"/WEB-INF/static/"+file.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        bufferedOutputStream.write(file.getBytes());
        bufferedOutputStream.close();//여기까지 저장

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url", "/images/"+ file.getOriginalFilename());//여기는 보여주는건가
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView error(Exception e){
        ModelAndView modelAndView=new ModelAndView("error");
        modelAndView.addObject("e",e);
        return modelAndView;
    }
}