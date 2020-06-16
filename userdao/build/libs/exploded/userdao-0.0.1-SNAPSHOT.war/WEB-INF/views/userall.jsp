<%@ page import="java.util.ArrayList" %>
<%@ page import="kr.ac.jejunu.user.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<body>
<h1>
    <%String alist="";
    ArrayList<User> userlist=(ArrayList<User>) request.getAttribute("model");

    for(int i=1; i<userlist.size(); i++){
        alist+="hello";
        if(!(userlist.get(i)==null)){
            alist+=userlist.get(i).getName();
        }
    }
    %>
    <%=alist%>!!!inneruser.jsp
</h1>
</body>
</html>
