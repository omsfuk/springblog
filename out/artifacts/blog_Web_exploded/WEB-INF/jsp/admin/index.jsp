<%--
  Created by IntelliJ IDEA.
  User: omsfuk
  Date: 17-4-23
  Time: 下午8:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Index</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <ul class="nav nav-tabs">
        <li role="presentation" class="active"><a href="/admin/index">Index</a></li>
        <li role="presentation"><a href="/admin/new">New</a></li>
        <li role="presentation"><a href="/admin/notes">Posts</a></li>
        <li role="presentation"><a href="/admin/tags">Tags</a></li>
        <li role="presentation"><a href="/admin/upload">Upload</a></li>
        <li role="presentation"><a href="/admin/settings">Settings</a></li>
    </ul>
    <div class="jumbotron">
        <div class="container">
            <h2>欢迎回来，omsfuk</h2>
            <h2>总计 ${postCount} 篇博客</h2>
        </div>
    </div>
</div>
</body>
<script src="/js/jquery.min.js"/>
</html>