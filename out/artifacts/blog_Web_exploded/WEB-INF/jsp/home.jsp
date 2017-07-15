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
    <script src="/js/angular.min.js"></script>
    <link href="/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/css/font-awesome.min.css" rel="stylesheet" />
    <link href="/css/home.css" rel="stylesheet" />
</head>
<body ng-app="HomeApp" ng-controller="HomeController">
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
            <a class="fa fa-username-circle" href="/about"></a>关于
        </div>
    </div>
    <div class="tags">
        <c:forEach var="tag" items="${tags}">
            <a href="/tag/${tag.name}" class="tag"><span class="tag-text">${tag.name}</span></a>
        </c:forEach>
    </div>
</div>

<div class="main">
    <div class="article" ng-repeat="note in notes">
        <div class="title">
            {{note.title}}
        </div>
        <div class="content">
           {{note.content}}
        </div>
        <div class="footer">
            <div class="date">
                <span class="fa fa-clock-o"> {{note.tdate}}</span>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{note.vtime}} 次访问</span>
            </div>
            <div class="tag-list">
                <span class="fa fa-tags fa-lg"></span>
                <%--<c:forEach var="tag" items="${fn:split(note.tags,',')}">--%>
                    <%--<a href="/tag/${tag}" class="tag"><span class="tag-text">${tag}</span></a>--%>
                <%--</c:forEach>--%>
            </div>
            <a class="btn-more" href="/note/{{note.url}}">More...</a>
        </div>
    </div>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:if test="${page != 0}">
                <li>
                    <a href="/home?page=${page - 1}" aria-label="Previous">
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
                    <li><a href="/home?page=${i-1}" >${i}</a></li>
                </c:if>
            </c:forEach>

            <c:if test="${page != totalPages - 1}">
                <li>
                    <a href="/home?page=${page + 1}" aria-label="Next">
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
</body>
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"/>
<script src="/js/home.js"></script>
<script>
    var app = angular.module('HomeApp', [])
    app.controller('HomeController', function($scope, $http) {
        $http({
            method: 'GET',
            url: 'api/note/all'
        }).success(function(data, status, headers, config) {
            $scope.notes = data;
        }).error(function(data, status, headers, config) {

        })
    })
</script>
</html>