package kr.ac.jejunu.user.controller;

import kr.ac.jejunu.user.dao.AccountDao;
import kr.ac.jejunu.user.dao.MydailyDao;
import kr.ac.jejunu.user.dao.ScoreDao;
import kr.ac.jejunu.user.data.GameScore;
import kr.ac.jejunu.user.data.Mydaily;
import kr.ac.jejunu.user.data.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class GamescoreController {
    private final ScoreDao scoreDao;
    private final MydailyDao mydailyDao;

    @GetMapping(path ="/buggame")
    public Model viewgame(Model model, HttpSession session){
        UserAccount userAccount=(UserAccount)session.getAttribute("userAccount");
        ArrayList<GameScore> gameScores=scoreDao.getAll();
        model.addAttribute("scorelist",gameScores);
        return model;
    }
    @PostMapping(path ="/buggame")
    public Model insertscore(@ModelAttribute GameScore score,Model model, HttpSession session) {
        try {
            UserAccount userAccount = (UserAccount) session.getAttribute("userAccount");
            score.setName(userAccount.getName());
            score.setDate(new java.util.Date());
            if(scoreDao.get_name(userAccount.getName())==null){
                scoreDao.insert(score);
            }
            else{
                if(score.getScore()>scoreDao.get_name(userAccount.getName()).getScore()){
                    scoreDao.update(score);
                }
            }
            model.addAttribute("msg", "정상적으로 등록되었습니다.");
            ArrayList<GameScore> gameScores=scoreDao.getAll();
            model.addAttribute("scorelist",gameScores);
            return model;
        } catch (Exception e) {
            model.addAttribute("msg", "잘못된 시도입니다.");
            return model;
        }
    }
}
