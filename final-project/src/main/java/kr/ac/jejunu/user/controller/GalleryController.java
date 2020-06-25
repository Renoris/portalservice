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
        UserAccount userAccount=(UserAccount) session.getAttribute("userAccount");
        ArrayList<Comment> comments=commentDao.getAll(id);
        model.addAttribute("commentlist",comments);
        model.addAttribute("gallery", gallery);
        model.addAttribute("username",userAccount.getName());
        return model;
    }

    @PostMapping(path ="/gallery")
    public Model createcomment(@RequestParam Integer id,Model model ,HttpSession session, @ModelAttribute Comment comment) throws IOException {
        Gallery gallery=galleryDao.get(id);
        comment.setCommentdate(new java.util.Date());
        comment.setGalleryid(id);
        UserAccount userAccount=(UserAccount) session.getAttribute("userAccount");
        comment.setName(userAccount.getName());
        commentDao.insert(comment);
        ArrayList<Comment> comments=commentDao.getAll(id);
        model.addAttribute("username",userAccount.getName());
        model.addAttribute("commentlist",comments);
        model.addAttribute("msg","댓글이 등록되었습니다");
        model.addAttribute("gallery", gallery);
        return model;
    }

    @GetMapping(path="/deletegallery")
    public Model deletegallery(@RequestParam Integer id, HttpSession session,Model model){
        try {
            UserAccount userAccount = (UserAccount) session.getAttribute("userAccount");
            Gallery gallery = galleryDao.get(id);
            if(gallery.getName().equals(userAccount.getName())){
                galleryDao.delete(id);
                model.addAttribute("msg", "정상적으로 삭제되었습니다.");
                return model;
            }
            else{
                model.addAttribute("msg", "게시글 작성자가 아닙니다.");
                return model;
            }
        } catch (Exception e) {
            model.addAttribute("msg", "잘못된 시도입니다.");
            return model;
        }
    }

    @GetMapping(path="/deletecomment")
    public Model deletcomment(@RequestParam Integer id, HttpSession session,Model model){
        try {
            UserAccount userAccount = (UserAccount) session.getAttribute("userAccount");
            Comment comment = commentDao.get(id);
            if(comment.getName().equals(userAccount.getName())){
                commentDao.delete(id);
                model.addAttribute("msg", "정상적으로 삭제되었습니다.");
                return model;
            }
            else{
                model.addAttribute("msg", "코멘트 작성자가 아닙니다.");
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
