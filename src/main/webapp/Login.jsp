<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <style type="text/css">
        input{
            height: 30px;
            width:400px;
            border: solid 1px;
            border-radius:5% ;
            margin: 10px;
        }
        #sub{
            height: 30px;
            width:200px;
        }
        #res{
            height: 30px;
            width:200px;
        }
    </style>
</head>
<body>
    <form action="/demo02/login" method="post">
        用戶名 : <input type="text" name="userName" placeholder="请输入用户名"/> </br>
        密&nbsp;&nbsp;&nbsp;码  : <input type="password" name="password" placeholder="请输入用户密码"/></br>
        <input id="sub" type="submit" value="提交">
        <input id="res" type="reset" value="重置">
    </form>
</body>
</html>
