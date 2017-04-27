<%--
  Created by IntelliJ IDEA.
  User: omsfuk
  Date: 17-4-23
  Time: 下午3:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${post.title}</title>
    <link href="/css/home.css" rel="stylesheet"/>
    <link href="/css/font-awesome.min.css" rel="stylesheet"/>
    <link href="/css/github-markdown.css" rel="stylesheet"/>
</head>
<body>
<div class="navbar">
    <div class="black-bg">
    </div>
    <img class="portrait" src="/images/portrait.png"/>
    <img class="signature" src="/images/signature.png"/>
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
    <div class="article">
        <div class="title">
            ${post.title}
        </div>
        <div class="footer">
            <div class="date">
                <span class="fa fa-clock-o"> ${post.tdate}</span>
            </div>
            <div class="tag-list">
                <span class="fa fa-tags fa-lg"></span>
                <c:forEach var="tag" items="${fn:split(post.tags, ',')}">
                    <a href="/tag/${tag}" class="tag"><span class="tag-text">${tag}</span></a>
                </c:forEach>
            </div>
            <span>&nbsp;&nbsp;${post.vtime} 次访问</span>
        </div>
        <div class="content markdown-body"></div>
    </div>

    <c:if test="${empty previousPost}">
        <a style="float:left" href="#">上一篇： 没有了</a>
    </c:if>
    <c:if test="${!empty previousPost}">
        <a style="float:left" href="/post/${previousPost.url}">上一篇： ${previousPost.title}</a>
    </c:if>

    <c:if test="${empty nextPost}">
        <a style="float:right" href="#">下一篇： 没有了</a>
    </c:if>
    <c:if test="${!empty nextPost}">
        <a style="float:right" href="/post/${nextPost.url}">下一篇： ${nextPost.title}</a>
    </c:if>

</div>
</body>
<script src="/js/jquery.min.js"></script>
<script src="/js/showdown.min.js"></script>
<script src="/js/home.js"></script>
<script>
    $(document).ready(function () {
        $.get("/getPost/${url}", function(data, status) {
            if(data.success == true) {
                var converter = new showdown.Converter();
                var html = converter.makeHtml(data.data.content);
                $("div[class='content markdown-body']").html(html);
            } else {
                alert(data.msg);
            }
        });
    });
</script>
</html>