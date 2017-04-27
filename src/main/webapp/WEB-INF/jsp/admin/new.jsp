<%--
  Created by IntelliJ IDEA.
  User: omsfuk
  Date: 17-4-23
  Time: 下午8:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>New</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <ul class="nav nav-tabs">
        <li role="presentation"><a href="/admin/index">Index</a></li>
        <li role="presentation" class="active"><a href="/admin/new">New</a></li>
        <li role="presentation"><a href="/admin/posts">Posts</a></li>
        <li role="presentation"><a href="/admin/tags">Tags</a></li>
        <li role="presentation"><a href="/admin/upload">Upload</a></li>
        <li role="presentation"><a href="/admin/settings">Settings</a></li>
    </ul>
    <div class="jumbotron">
        <div class="container">
            <form action="/admin/post/add" method="post">
                <div class="form-group input-group">
                    <span class="input-group-addon" id="basic-addon1">Title</span>
                    <input name="title" type="text" class="form-control" placeholder="title" aria-describedby="basic-addon1">
                </div>
                <div class="form-group input-group">
                    <span class="input-group-addon" id="basic-addon2">Tags</span>
                    <input name="tags" type="text" class="form-control" placeholder="tags"
                           aria-describedby="basic-addon2">
                </div>
                <div class="form-group input-group">
                    <span class="input-group-addon" id="basic-addon3">Url</span>
                    <input name="url" type="text" class="form-control" placeholder="url-pattern"
                           aria-describedby="basic-addon3">
                </div>
                <div class="form-group">
                    <textarea name="content" style="width: 100%" rows="20" placeholder="请输入文章内容"></textarea>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-default">提交</button>
                </div>
            </form>
        </div>

    </div>
</div>
<script src="/js/jquery.min.js"/>
</body>
</html>