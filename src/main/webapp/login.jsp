<%--
  Created by IntelliJ IDEA.
  User: baige
  Date: 2018/5/3
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
<div> <h3>用户登录 </h3> <a href="register.jsp">注册</a> </div>

<span id="tip" style="color: red; font-weight: bold"></span>
<form  action="login.action" method="post">
    <table>
        <tr>
            <td>用户名：</td>
            <td><input type="text"  name="name"/></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="password" name="psw"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" name="登录"/>
                <input type="reset" name="重置"/>
            </td>
        </tr>
    </table>

</form>
</body>
</html>
