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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public Model createmydaily(@ModelAttribute Mydaily mydaily,Model model, HttpServletResponse response, HttpSession session) throws IOException {
        try {
//            Mydaily mydaily = new Mydaily();
            UserAccount userAccount = (UserAccount) session.getAttribute("userAccount");
            mydaily.setName(userAccount.getName());
//            mydaily.setDailycontent(request.getParameter("dailycontent"));
//            mydaily.setDailytitle(request.getParameter("dailytitle"));
//            mydaily.setDailydate(request.getParameter("dailydate"));
//            mydaily.setDailytime(request.getParameter("dailytime"));
            mydailyDao.insert(mydaily);
            model.addAttribute("msg", "정상적으로 등록되었습니다.");
            ArrayList<Mydaily> mydailies=mydailyDao.getMydailyAll(userAccount.getName());
            model.addAttribute("mydailylist",mydailies);
            return model;
        } catch (Exception e) {
            model.addAttribute("msg", "잘못된 시도입니다.");
            return model;
        }
    }
    @GetMapping(path="/deletemydaily")
    public Model deletcomment(@RequestParam Integer id, HttpSession session,Model model){
        try {
            UserAccount userAccount = (UserAccount) session.getAttribute("userAccount");
            Mydaily mydaily = mydailyDao.get(id);
            if(mydaily.getName().equals(userAccount.getName())){
                mydailyDao.delete(id);
                model.addAttribute("msg", "정상적으로 삭제되었습니다.");
                return model;
            }
            else{
                model.addAttribute("msg", "당신은 일정 작성자가 아닙니다. 옳지 않은 요청을 보내지 마십시오");
                return model;
            }
        } catch (Exception e) {
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
