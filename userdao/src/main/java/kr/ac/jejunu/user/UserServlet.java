package kr.ac.jejunu.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@Controller("/userServlet")
@WebServlet(urlPatterns = "/hello")
public class UserServlet extends GenericServlet {
    @Autowired
    private UserDao userDao;
    @Override
    public void destroy() {
        System.out.println("************************** destroy *****************************");
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("**************************init *****************************");
        ApplicationContext applicationContext= new AnnotationConfigApplicationContext("kr.ac.jejunu.user");
        this.userDao = applicationContext.getBean("userDao", UserDao.class);
        super.init(config);
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("**************************service *****************************");
        User user = userDao.findById(1).get();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<html>");
        stringBuffer.append("<h1>");
        stringBuffer.append(String.format("Hello %s!!!",user.getName()));
        stringBuffer.append("</h1>");
        stringBuffer.append("</html>");
        res.setContentType("text/html; charset=UTF-8");
        res.getWriter().println(stringBuffer.toString());
    }
}
