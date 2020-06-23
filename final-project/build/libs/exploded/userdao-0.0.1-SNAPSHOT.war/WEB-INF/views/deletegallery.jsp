<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
<%
    String msg=null;
    try{
        msg=request.getAttribute("msg").toString();
        System.out.println(msg);
    }
    catch (Exception e){
        msg="";
    }
%>
<script type="text/javascript">
    var f= "<%=msg%>";
    if(!(f=="")){
        alert(f);
    }
    location.href="/lobby";

</script>
</body>

</html>