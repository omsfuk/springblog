<%--
  Created by IntelliJ IDEA.
  User: omsfuk
  Date: 17-4-23
  Time: 下午4:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Home</title>
    <link href="/css/home.css" rel="stylesheet" />
    <link href="/css/font-awesome.min.css" rel="stylesheet" />
</head>
<body>
<div class="navbar">
    <div class="black-bg">
    </div>
    <img class="portrait" src="/images/portrait.png" />
    <img class="signature" src="/images/signature.png" />
    <div class="btns">
        <div class="btn-nav">
            <a class="fa fa-paw" href="/home"></a>主页
        </div>
        <div class="btn-nav">
            <a class="fa fa-paper-plane" href="/archive"></a>归档
        </div>
        <div class="btn-nav">
            <a class="fa fa-user-circle" href="/about"></a>关于
        </div>
    </div>
    <div class="tags">
        <c:forEach var="tag" items="${tags}">
            <a href="tag/${tag.name}" class="tag"><span class="tag-text">${tag.name}</span></a>
        </c:forEach>
    </div>
</div>

<div class="about">
    <div class="title">About Me</div>
    <div class="text">后端程序猿一枚</div>
    <div class="text">热衷于前端UI</div>
    <div class="text">所学驳杂</div>
    <div class="text">撰此博客，抒情感怀</div>
</div>
</body>
<script src="/js/jquery.min.js"></script>
<script src="/js/home.js"></script>
</html>