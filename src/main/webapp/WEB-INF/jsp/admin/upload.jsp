<%@ taglib prefix="for" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: omsfuk
  Date: 17-4-24
  Time: 下午11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Upload</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <ul class="nav nav-tabs">
        <li role="presentation"><a href="/admin/index">Index</a></li>
        <li role="presentation"><a href="/admin/new">New</a></li>
        <li role="presentation"><a href="/admin/posts">Posts</a></li>
        <li role="presentation"><a href="/admin/tags">Tags</a></li>
        <li role="presentation" class="active"><a href="/admin/upload">Upload</a></li>
        <li role="presentation"><a href="/admin/settings">Settings</a></li>
    </ul>
    <div class="jumbotron">
        <div class="container">
            <form action="/admin/fileUpload" method="post" enctype="multipart/form-data" >
                <div class="form-group">
                    <input name="file" type="file" id="InputFile">
                    <p class="help-block">可以将用到的图片上传</p>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-default">提交</button>
                </div>
            </form>

            <div class="row">
                <c:forEach var="picture" items="${pictures}">
                    <div class="col-sm-6 col-md-3">
                        <a href="#" class="thumbnail">
                            <img src="/upload/${picture.fileName}"
                                 alt="图片略缩图">
                        </a>
                        <form action="/admin/delResource" method="post">
                            <input name="name" type="hidden" value="${picture.fileName}"/>
                            <button type="submit" class="btn btn-danger">删除</button>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>