<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019/12/12
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>output.jsp</title>
</head>
<body>
<jsp:useBean id="user" class="beans.User" scope="application"/>
<jsp:setProperty property="name" name="user" param="userName"/>
<jsp:setProperty property="sex" name="user" param="sex"/>
<jsp:setProperty property="birthday" name="user" param="birthday"/>
<jsp:setProperty property="nation" name="user" param="nation"/>
<jsp:setProperty property="describe" name="user" param="describe"/>
<table>
    <tr>
        <td>
            姓名:
        </td>
        <td>
            <jsp:getProperty property="name" name="user"/>
        </td>
    </tr>
    <tr>
        <td>
            性别:
        </td>
        <td>
            <jsp:getProperty property="sex" name="user"/>
        </td>
    </tr>
    <tr>
        <td>
            出生日期:
        </td>
        <td>
            <jsp:getProperty property="birthday" name="user"/>
        </td>
    </tr>

    <tr>
        <td>
            民族:
        </td>
        <td>
            <jsp:getProperty property="nation" name="user"/>
        </td>
    </tr>
    <tr>
        <td>
            个人描述:
        </td>
        <td>
            <jsp:getProperty property="describe" name="user"/>
        </td>
    </tr>
</table>
</body>
</html>
