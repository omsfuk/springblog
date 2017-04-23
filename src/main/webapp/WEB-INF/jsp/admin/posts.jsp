<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: omsfuk
  Date: 17-4-23
  Time: 下午8:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Posts</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <ul class="nav nav-tabs">
        <li role="presentation"><a href="/admin/index">Index</a></li>
        <li role="presentation"><a href="/admin/new">New</a></li>
        <li role="presentation" class="active"><a href="/admin/posts">Posts</a></li>
        <li role="presentation"><a href="/admin/tags">Tags</a></li>
        <li role="presentation"><a href="/admin/settings">Settings</a></li>
    </ul>
    <div class="jumbotron">
        <div class="container">
            <table class="table">
                <tr>
                    <th>id</th>
                    <th>title</th>
                    <th>tags</th>
                    <th>url</th>
                    <th>operate</th>
                </tr>

                <c:forEach var="post" items="${posts}">
                    <tr>
                        <td>${post.id}</td>
                        <td>${post.title}</td>
                        <td>${post.tags}</td>
                        <td>${post.url}</td>
                        <td>
                            <form action="/admin/post/edit" method="get" style="display:inline-block;">
                                <input type="hidden" name="id" value="${post.id}"/>
                                <button type="submit" class="btn btn-primary btn-sm">编辑</button>
                            </form>
                            <form action="/admin/post/del" method="post" style="display:inline-block;">
                                <input type="hidden" name="id" value="${post.id}"/>
                                <button type="submit" class="btn btn-danger btn-sm">删除</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </div>
    </div>
</div>
</body>
<script src="/js/jquery.min.js"/>
</html>