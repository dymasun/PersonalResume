<%--
  Created by IntelliJ IDEA.
  User: robertsun
  Date: 2018/12/26
  Time: 3:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <script src="html/js/common.js"></script>
    <script src="html/js/jquery-2.1.4.min.js"></script>
    <script>
        //window.location.href = "/html/index.html";
        var role = window.location.search;
        if(role.length==0){
            window.location.href = "html/index.html?r=guest";
        }else{
            window.location.href = "html/index.html" + role;
        }
    </script>
</head>
<body>

</body>
</html>
