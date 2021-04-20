<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>DANAGA</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap-theme.css"/>
<link rel="stylesheet" href="css/bootstrap.css"/>
<link rel="stylesheet" href="css/style.css"/>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
    .row.content {height: auto;}
    
    /* Set gray background color and 100% height */
   
    container {
       padding : 20px;
    }
    
    /* Set black background color, white text and some padding */
    .room{
    margin-top:50px;
	float:right;
    width: 400px;
    color: white;
    text-align: center;
    background-color: rgba(0,0,0,0.1 );
}
.room a{
	color:white;
}
.room a:visited{
	color:white;
}
body {
  margin: 0;
  padding-top:50px;
  padding-bottom:50px;
}
  </style>
<%--       <c:choose>
   <c:when test="${sessionScope ne null }">
      <c:if test="${id ne null }">
         ${id }님 환영합니다.
            <c:if test="${grade eq 'S' }">
               관리자로 접속중.
            </c:if>
      </c:if>
   </c:when>
   <c:otherwise>
   </c:otherwise>
</c:choose> --%>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="index.jsp">DANAGA</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
    <div style="width: 60%; float:none; margin:0 auto" >
      <ul class="nav navbar-nav">
        <li><a href="welcome.log">오시는길</a></li>
        <li><a href="noticeList.no">공지사항</a></li>
        <li class="dropdown">
   <a href="#" class="dropdown-toggle" data-toggle="dropdown" href="roomList.ro" role="button" aria-expanded="false">방정보 <span class="caret"></span></a>
    <ul class="dropdown-menu" role="menu">
       <li><a href="roomList.ro">객실 미리보기</a></li>
       <li><a href="roomList.ro#101">101호</a></li>
       <li><a href="roomList.ro#102">102호</a></li>
       <li><a href="roomList.ro#103">103호</a></li>
       <li><a href="roomList.ro#201">201호</a></li>
       <li><a href="roomList.ro#202">202호</a></li>
       <li><a href="roomList.ro#203">203호</a></li>
    </ul>
</li>
        <li><a href="reservationState.re">예약현황</a></li>
      <c:choose>
         <c:when test="${id ne null}">            
            <c:if test="${grade eq 'M'}">
               <li><a href='memberInfo.me'>마이 페이지</a></li>
               <li><a href='reservationCheck.re'>예약 확인</a></li>
            </c:if>
            <c:if test="${grade eq 'S'}">
               <li><a href='OPnoticeList.no'>관리자 공지사항</a></li>
               <li><a href='OProomList.ro'>관리자 방 정보</a></li>
               <li><a href='memberList.me'>관리자 회원 목록</a></li>
               <li><a href='reservationAppList.re'>관리자 예약 승인</a></li>
            </c:if>
         </c:when>
   </c:choose>
   </ul>
      </div>
      <div>
      <c:choose>
      <c:when test="${id eq null }">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="loginForm.log"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </ul>
      </c:when>
      <c:otherwise>
      <ul class="nav navbar-nav navbar-right">
      <li><a href='logout.log'><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
      </c:otherwise>
      </c:choose>
      </div>
    </div>
  </div>
</nav>
</body>
</html>