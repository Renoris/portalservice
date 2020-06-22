package kr.ac.jejunu.user.controller;

import kr.ac.jejunu.user.dao.AccountDao;
import kr.ac.jejunu.user.dao.CommentDao;
import kr.ac.jejunu.user.dao.GalleryDao;
import kr.ac.jejunu.user.data.Comment;
import kr.ac.jejunu.user.data.Gallery;
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
        ArrayList<Comment> comments=commentDao.getAll();
        model.addAttribute("commentlist",comments);
        model.addAttribute("gallery", gallery);
        return model;
    }

    @PostMapping(path ="/gallery")
    public void createcomment(@RequestParam ("id") Integer id, HttpServletResponse response, HttpSession session, @ModelAttribute Comment comment) throws IOException {
        try{
            UserAccount userAccount=(UserAccount) session.getAttribute("userAccount");
            UserAccount confirmAccount=accountDao.get(userAccount.getName(), userAccount.getPassword());
            comment.setName(userAccount.getName());
            if (confirmAccount== null) {
                response.sendRedirect("/login.html");
            }
        }catch (Exception e){
            response.sendRedirect("/login.html");
        }
        comment.setCommentdate(new java.util.Date());
        comment.setGalleryid(id);
        commentDao.insert(comment);
        response.sendRedirect("/viewpost/{id}");
    }


//    @ExceptionHandler(Exception.class)
//    public ModelAndView error(Exception e){
//        ModelAndView modelAndView=new ModelAndView("error");
//        modelAndView.addObject("e",e);
//        return modelAndView;
//    }
}
