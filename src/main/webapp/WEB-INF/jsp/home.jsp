<%--
  Created by IntelliJ IDEA.
  User: omsfuk
  Date: 17-4-23
  Time: 下午1:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
            <a href="/tag/${tag.name}" class="tag"><span class="tag-text">${tag.name}</span></a>
        </c:forEach>
    </div>
</div>

<div class="main">
    <c:forEach var="post" items="${posts}">
        <div class="article">
            <div class="title">
                    ${post.title}
            </div>
            <div class="content">
                    ${post.content}
            </div>
            <div class="footer">
                <div class="date">
                    <span class="fa fa-clock-o"> ${post.tdate}</span>
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${post.vtime} 次访问</span>
                </div>
                <div class="tag-list">
                    <span class="fa fa-tags fa-lg"></span>
                    <c:forEach var="tag" items="${fn:split(post.tags,',')}">
                        <a href="/tag/${tag}" class="tag"><span class="tag-text">${tag}</span></a>
                    </c:forEach>
                </div>
                <a class="btn-more" href="/post/${post.url}">More...</a>
            </div>
        </div>
    </c:forEach>
</div>
</body>
<script src="/js/jquery.min.js"></script>
<script src="/js/home.js"></script>
</html>