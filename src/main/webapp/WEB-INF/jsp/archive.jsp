<%--
  Created by IntelliJ IDEA.
  User: omsfuk
  Date: 17-4-23
  Time: 下午4:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Archive</title>
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
            <a href="/tag/${tag.name}" class="tag"><span class="tag-text">${tag.name}</span></a>
        </c:forEach>
    </div>
</div>

<div class="timeline-main">
    <div class="timeline">
        <c:forEach var="post" items="${posts}">
            <div class="a-piece">
                <div class="date">${post.tdate}</div>
                <span class="fa-stack fa-2x">
						  <i class="fa fa-circle fa-stack-2x" style="color:#D2E9FF"></i>
						  <i class="fa fa-check fa-stack-1x" style="color:#7D7DFF"></i>
						</span>
                <a href="/post/${post.url}"><div class="title">${post.title}</div></a>
            </div>
        </c:forEach>
    </div>
</div>
</body>
<script src="/js/jquery.min.js"></script>
<script src="/js/home.js"></script>
</html>
