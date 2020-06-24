package kr.ac.jejunu.user.controller;

import kr.ac.jejunu.user.dao.*;
import kr.ac.jejunu.user.data.Comment;
import kr.ac.jejunu.user.data.Gallery;
import kr.ac.jejunu.user.data.Mypicture;
import kr.ac.jejunu.user.data.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class MypictureController {
    private final MypictureDao mypictureDao;
    @GetMapping(path ="/mypicture")
    public Model viewmypicture(Model model, HttpSession session) throws IOException {
        model.addAttribute("mypicturelist",mypictureDao.getAll());
        return model;
    }

    @PostMapping(path ="/mypicture")
    public ModelAndView createmypicture(@RequestParam("file") MultipartFile file, HttpServletRequest request,HttpSession session) throws IOException {
        File path = new File(request.getServletContext().getRealPath("/")+"/WEB-INF/static/resources/images/"+file.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        bufferedOutputStream.write(file.getBytes());
        bufferedOutputStream.close();//여기까지 저장
        ModelAndView modelAndView=new ModelAndView();
        Mypicture mypicture=new Mypicture();
        mypicture.setPicturetitle(request.getParameter("picturetitle"));
        mypicture.setPicturedate(new java.util.Date());
        mypicture.setPictureurl("/resources/images/"+file.getOriginalFilename());
        UserAccount userAccount=(UserAccount) session.getAttribute("userAccount");
        mypicture.setName(userAccount.getName());
        mypictureDao.insert(mypicture);
        modelAndView.addObject("mypicturelist",mypictureDao.getAll());
        return modelAndView;
    }

    @PostMapping("/upload")
    public ModelAndView upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        File path = new File(request.getServletContext().getRealPath("/")+"/WEB-INF/static/resources/images/"+file.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        bufferedOutputStream.write(file.getBytes());
        bufferedOutputStream.close();//여기까지 저장
        System.out.println(request.getParameter("test"));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url", "/resources/images/"+file.getOriginalFilename());//여기는 보여주는건가
        return modelAndView;
    }


    @GetMapping(path="/deletemypicture")
    public Model deletemypicture(@RequestParam Integer id, HttpSession session,Model model){
        try {
            UserAccount userAccount = (UserAccount) session.getAttribute("userAccount");
            Mypicture mypicture = mypictureDao.get(id);
            if(mypicture.getName().equals(userAccount.getName())){
                mypictureDao.delete(id);
                model.addAttribute("msg", "정상적으로 삭제되었습니다.");
                return model;
            }
            else{
                model.addAttribute("msg", "사진 게시자가 아닙니다.");
                return model;
            }
        } catch (Exception e) {
            model.addAttribute("msg", "잘못된 시도입니다.");
            return model;
        }
    }


//    @ExceptionHandler(Exception.class)
//    public ModelAndView error(Exception e){
//        ModelAndView modelAndView=new ModelAndView("error");
//        modelAndView.addObject("e",e);
//        return modelAndView;
//    }
}
