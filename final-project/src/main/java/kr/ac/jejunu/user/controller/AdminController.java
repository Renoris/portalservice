package kr.ac.jejunu.user.controller;

import kr.ac.jejunu.user.dao.AccountDao;
import kr.ac.jejunu.user.dao.CommentDao;
import kr.ac.jejunu.user.dao.GalleryDao;
import kr.ac.jejunu.user.dao.MypictureDao;
import kr.ac.jejunu.user.data.Mypicture;
import kr.ac.jejunu.user.data.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final AccountDao accountDao;
    private final CommentDao commentDao;
    private final GalleryDao galleryDao;

    @GetMapping(path ="/admin")
    public void viewadminpage(HttpServletRequest request,Model model, HttpSession session){
        UserAccount userAccount=(UserAccount)session.getAttribute("userAccount");

        if(userAccount.isAdmin()){
            request.setAttribute("gallerylist",galleryDao.getAll());
            request.setAttribute("commentlist",commentDao.getAll_admin());
            request.setAttribute("userlist",accountDao.getUserAll());
        }
        else{

        }
    }

    @GetMapping(path="/deleteuser")
    public String deleteuser(@RequestParam Integer id, HttpSession session){
        UserAccount userAccount=(UserAccount)session.getAttribute("userAccount");
        if(userAccount.isAdmin()){
            accountDao.delete(id);
            return "redirect:/admin";
        }
        else{
            return "redirect:/lobby";
        }


    }

    @ExceptionHandler(Exception.class)
    public ModelAndView error(Exception e){
        ModelAndView modelAndView=new ModelAndView("adminerror");
        modelAndView.addObject("e",e);
        return modelAndView;
    }
}
