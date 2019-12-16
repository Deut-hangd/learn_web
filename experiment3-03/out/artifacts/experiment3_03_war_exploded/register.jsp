<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/12/12
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register.jsp</title>
</head>
<body>
<form action="output.jsp" method="post">
    <table>
        <tr>
            <td>
                欢迎注册
            </td>
        </tr>
        <tr>
            <td>
                输入姓名:
            </td>
            <td>
                <input type="text" name="userName">
            </td>
        </tr>
        <tr>
            <td>
                选择性别:
            </td>
            <td>
                <input type="radio" name="sex" value="男" checked>男
                <input type="radio" name="sex" value="女">女
            </td>
        </tr>
        <tr>
            <td>
                出生年月:
            </td>
            <td>
                <input type="text" name="birthday">
            </td>
        </tr>
        <tr>
            <td>
                民族:
            </td>
            <td>
                <input type="text" name="nation">
            </td>
        </tr>
    </table>
    <table>
        <tr>
            <td>
                个人介绍:
            </td>
            <td>
                <textarea rows="5" cols="20" name="describe"></textarea>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="注册">
                <input type="reset" value="清空">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
