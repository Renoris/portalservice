package kr.ac.jejunu.user.controller;

import kr.ac.jejunu.user.dao.AccountDao;
import kr.ac.jejunu.user.dao.GalleryDao;
import kr.ac.jejunu.user.data.Gallery;
import kr.ac.jejunu.user.data.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class CreatePostController {
    private final GalleryDao galleryDao;
    private final AccountDao accountDao;
    @GetMapping(path ="/createpost")
    public void postpage(HttpSession session) throws IOException {

    }

    @PostMapping(path ="/createpost")
    public Model createPost(HttpServletResponse response, Model model, HttpSession session, @ModelAttribute Gallery gallery) throws IOException {
        try{
            UserAccount userAccount=(UserAccount) session.getAttribute("userAccount");
            gallery.setName(userAccount.getName());
            gallery.setPostdate(new java.util.Date());
            galleryDao.insert(gallery);
            model.addAttribute("msg", "게시글이 등록되었습니다.");
            response.sendRedirect("/lobby");
            return model;
        }catch (Exception e){
            model.addAttribute("msg", "잘못된 시도입니다.");
            return model;
        }
    }
}
