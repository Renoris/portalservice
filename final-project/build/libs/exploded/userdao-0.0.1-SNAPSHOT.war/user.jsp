<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.annotation.AnnotationConfigApplicationContext" %>
<%@ page import="kr.ac.jejunu.user.dao.UserDao" %>
<%@ page import="kr.ac.jejunu.user.data.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    ApplicationContext applicationContext= new AnnotationConfigApplicationContext("kr.ac.jejunu.user");
    UserDao userDao = applicationContext.getBean("userDao", UserDao.class);
    User user = userDao.get(1);
%>
<html>

<body>
<h1>
    Hello <%=user.getName()%>!!!! outuser.jsp
</h1>
</body>
</html>
