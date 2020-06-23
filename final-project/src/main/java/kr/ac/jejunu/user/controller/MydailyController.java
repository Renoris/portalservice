package kr.ac.jejunu.user.controller;

import kr.ac.jejunu.user.dao.AccountDao;
import kr.ac.jejunu.user.dao.CommentDao;
import kr.ac.jejunu.user.dao.GalleryDao;
import kr.ac.jejunu.user.dao.MydailyDao;
import kr.ac.jejunu.user.data.Comment;
import kr.ac.jejunu.user.data.Gallery;
import kr.ac.jejunu.user.data.Mydaily;
import kr.ac.jejunu.user.data.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class MydailyController {

    private final AccountDao accountDao;

    private final MydailyDao mydailyDao;

    @GetMapping(path ="/mydaily")
    public Model viewMydaily(Model model, HttpSession session) throws IOException {
        UserAccount userAccount=(UserAccount)session.getAttribute("userAccount");
        ArrayList<Mydaily> mydailies=mydailyDao.getMydailyAll(userAccount.getName());
        model.addAttribute("mydailylist",mydailies);
        return model;
    }


    @PostMapping(path ="/mydaily")
    public String createmydaily(HttpServletRequest request, HttpSession session) throws IOException {
        Mydaily mydaily=new Mydaily();
        UserAccount userAccount=(UserAccount) session.getAttribute("userAccount");
        mydaily.setName(userAccount.getName());
        mydaily.setDailycontent(request.getParameter("dailycontent"));
        mydaily.setDailytitle(request.getParameter("dailytitle"));
        mydaily.setDailydate(request.getParameter("dailydate"));
        mydaily.setDailytime(request.getParameter("dailytime"));
        mydailyDao.insert(mydaily);
        return "redirect:/mydaily";
    }


//    @ExceptionHandler(Exception.class)
//    public ModelAndView error(Exception e){
//        ModelAndView modelAndView=new ModelAndView("error");
//        modelAndView.addObject("e",e);
//        return modelAndView;
//    }
}
