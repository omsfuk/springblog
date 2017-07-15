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
        <li role="presentation" class="active"><a href="/admin/notes">Posts</a></li>
        <li role="presentation"><a href="/admin/tags">Tags</a></li>
        <li role="presentation"><a href="/admin/upload">Upload</a></li>
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

                <c:forEach var="note" items="${notes}">
                    <tr>
                        <td>${note.id}</td>
                        <td>${note.title}</td>
                        <td>${note.tags}</td>
                        <td>${note.url}</td>
                        <td>
                            <form action="/admin/note/edit" method="get" style="display:inline-block;">
                                <input type="hidden" name="id" value="${note.id}"/>
                                <button type="submit" class="btn btn-primary btn-sm">编辑</button>
                            </form>
                            <form action="/admin/note/del" method="note" style="display:inline-block;">
                                <input type="hidden" name="id" value="${note.id}"/>
                                <button type="submit" class="btn btn-danger btn-sm">删除</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <c:if test="${page != 0}">
                        <li>
                            <a href="/admin/notes?page=${page - 1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${page == 0}">
                        <li>
                            <a href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <c:forEach var="i" begin="1" end="${totalPages}" step="1">
                        <c:if test="${i - 1 == page}">
                            <li class="active"><a href="/home?page=${i-1}">${i}</a></li>
                        </c:if>
                        <c:if test="${i - 1 != page}">
                            <li><a href="/admin/notes?page=${i-1}" >${i}</a></li>
                        </c:if>
                    </c:forEach>

                    <c:if test="${page != totalPages - 1}">
                        <li>
                            <a href="/admin/notes?page=${page + 1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${page == totalPages - 1}">
                        <li>
                            <a href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>

                </ul>
            </nav>
        </div>
    </div>
</div>
</body>
<script src="/js/jquery.min.js"/>
</html>