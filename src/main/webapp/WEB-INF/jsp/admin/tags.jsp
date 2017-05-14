<%--
  Created by IntelliJ IDEA.
  User: omsfuk
  Date: 17-4-23
  Time: 下午10:25
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
        <li role="presentation"><a href="/admin/notes">Posts</a></li>
        <li role="presentation" class="active"><a href="/admin/tags">Tags</a></li>
        <li role="presentation"><a href="/admin/upload">Upload</a></li>
        <li role="presentation"><a href="/admin/settings">Settings</a></li>
    </ul>
    <div class="jumbotron">
        <div class="container">
            <table class="table">
                <tr>
                    <th>id</th>
                    <th>name</th>
                    <th>operate</th>
                </tr>

                <c:forEach var="tag" items="${tags}">
                    <tr>
                        <td>${tag.id}</td>
                        <td>${tag.name}</td>
                        <td>
                            <form action="/admin/tags/update" method="get" style="display:inline-block;">
                                <input type="hidden" name="old" value="${tag.name}"/>
                                <input type="text" name="new" placeholder="新的标签名称"/>
                                <button type="submit" class="btn btn-primary btn-sm">修改</button>
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