package kr.ac.jejunu.user.controller;

import kr.ac.jejunu.user.dao.AccountDao;
import kr.ac.jejunu.user.dao.CommentDao;
import kr.ac.jejunu.user.dao.GalleryDao;
import kr.ac.jejunu.user.data.Comment;
import kr.ac.jejunu.user.data.Gallery;
import kr.ac.jejunu.user.data.Mydaily;
import kr.ac.jejunu.user.data.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class GalleryController {
    private final GalleryDao galleryDao;
    private final AccountDao accountDao;
    private final CommentDao commentDao;
    @GetMapping(path ="/gallery")
    public Model viewpost(@RequestParam("id") Integer id, Model model, HttpSession session) throws IOException {

        Gallery gallery=galleryDao.get(id);
        ArrayList<Comment> comments=commentDao.getAll(id);
        model.addAttribute("commentlist",comments);
        model.addAttribute("gallery", gallery);
        return model;
    }

    @PostMapping(path ="/gallery")
    public String createcomment(@RequestParam Integer id, HttpSession session, @ModelAttribute Comment comment) throws IOException {
        comment.setCommentdate(new java.util.Date());
        comment.setGalleryid(id);
        UserAccount userAccount=(UserAccount) session.getAttribute("userAccount");
        comment.setName(userAccount.getName());
        commentDao.insert(comment);
        System.out.println(id);
        return "redirect:/gallery?id="+id;
    }

    @GetMapping(path="deletegallery")
    public Model deletegallery(@RequestParam Integer id, HttpSession session,Model model){
        try {
            UserAccount userAccount = (UserAccount) session.getAttribute("userAccount");


            model.addAttribute("msg", "정상적으로 등록되었습니다.");
            return model;
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
