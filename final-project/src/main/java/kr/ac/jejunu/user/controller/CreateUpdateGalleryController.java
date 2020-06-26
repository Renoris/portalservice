package kr.ac.jejunu.user.controller;

import kr.ac.jejunu.user.dao.AccountDao;
import kr.ac.jejunu.user.dao.GalleryDao;
import kr.ac.jejunu.user.data.Comment;
import kr.ac.jejunu.user.data.Gallery;
import kr.ac.jejunu.user.data.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class CreateUpdateGalleryController {
    private final GalleryDao galleryDao;
    private final AccountDao accountDao;
    @GetMapping(path ="/creategallery")
    public void postpage(HttpSession session) throws IOException {

    }

    @PostMapping(path ="/creategallery")
    public Model createPost(Model model, HttpSession session, @ModelAttribute Gallery gallery) throws IOException {
        try{
            UserAccount userAccount=(UserAccount) session.getAttribute("userAccount");
            gallery.setName(userAccount.getName());
            gallery.setPostdate(new java.util.Date());
            galleryDao.insert(gallery);
            model.addAttribute("msg", "게시글이 등록되었습니다.");
            return model;
        }catch (Exception e){
            model.addAttribute("msg", "잘못된 시도입니다.");
            return model;
        }
    }
    @GetMapping(path ="/updategallery")
    public Model updatepostview(@RequestParam("id") Integer id, Model model) throws IOException {
        Gallery gallery=galleryDao.get(id);
        model.addAttribute("gallery",gallery);
        return model;
    }

    @PostMapping(path ="/updategallery")
    public Model updatepost(@RequestParam("id") Integer id, Model model, @ModelAttribute Gallery gallery, HttpSession session) throws IOException {
        try{
            UserAccount userAccount=(UserAccount) session.getAttribute("userAccount");
            gallery.setId(id);
            gallery.setName(userAccount.getName());
            gallery.setPostdate(new java.util.Date());
            galleryDao.update(gallery);
            model.addAttribute("msg", "수정 되었습니다.");
            return model;
        }catch (Exception e){
            model.addAttribute("msg", "잘못된 시도입니다.");
            return model;
        }
    }
    @ExceptionHandler(Exception.class)
    public ModelAndView error(Exception e){
        ModelAndView modelAndView=new ModelAndView("error");
        modelAndView.addObject("e",e);
        return modelAndView;
    }
}
