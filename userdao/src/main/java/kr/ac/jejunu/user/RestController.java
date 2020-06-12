package kr.ac.jejunu.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class RestController {
    private final UserDao userDao;

    @GetMapping("/{id}")
    public @ResponseBody User get(@PathVariable("id")Integer id){
        return userDao.get(id);
    }

    @PostMapping
    public  @ResponseBody User create(@RequestBody User user){
        userDao.insert(user);
        return user;
    }
}
