<%@ page import="java.util.ArrayList" %>
<%@ page import="kr.ac.jejunu.user.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<body>
<h1>
    <%
        String alist = "";
        ArrayList<User> userlist = (ArrayList<User>) request.getAttribute("userList");
        for (User user : userlist) { //foreach 문
            alist += "hello";
            alist += user.getName() + "<br>";
        }
    %>
    <%=alist%>!!!inneruser.jsp
</h1>
</body>
</html>
