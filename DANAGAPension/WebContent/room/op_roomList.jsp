<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@include file ="../common/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<style type="text/css">
table{
	text-align:center;
	width:1200px;
	margin:auto;
}</style>
<meta charset="UTF-8">
<title>방 목록(관리자)</title>
</head>
<body>
<div class="table-responsive">
<table border=1>
	<tr>	
	<c:forEach var="room" begin="0" end="2" items="${list }">
	<td>객실 번호</td><td>${room.no }</td>
	</c:forEach>
	</tr>
	<tr>
	
	<c:forEach var="room" begin="0" end="2" items="${list }">
	<td>객실 타입</td><td>${room.type }</td>
	</c:forEach>
	</tr>
	<tr>
	
	<c:forEach var="room" begin="0" end="2" items="${list }">
	<td>평수</td><td>${room.size }</td>
	</c:forEach>
	</tr>
	<tr>
	
	<c:forEach var="room" begin="0" end="2" items="${list }">
	<td>침대 객실</td><td>${room.bedroom }</td>
	</c:forEach>
	</tr>
	<tr>
	
	<c:forEach var="room" begin="0" end="2" items="${list }">
	<td>최대 이용 인원</td><td>${room.men }</td>
	</c:forEach>
	</tr>
	<tr>
	
	<c:forEach var="room" begin="0" end="2" items="${list }">
	<td>비품 안내</td><td>${room.content }</td>
	</c:forEach>
	</tr>
	<tr>
	<c:forEach var="room" begin="0" end="2" items="${list }">
	<td colspan=2><input type="button" class="btn btn-primary" onclick="location.href='roomMod.ro?no=${room.no}'" value="수정하기"/></td>
	</c:forEach>
	</tr>
	<tr>
	<c:forEach var="room" begin="3" end="5" items="${list }">
	<td>객실 번호</td><td>${room.no }</td>
	</c:forEach>
	</tr>
	<tr>
	
	<c:forEach var="room" begin="3" end="5" items="${list }">
	<td>객실 타입</td><td>${room.type }</td>
	</c:forEach>
	</tr>
	<tr>
	
	<c:forEach var="room" begin="3" end="5" items="${list }">
	<td>평수</td><td>${room.size }</td>
	</c:forEach>
	</tr>
	<tr>
	
	<c:forEach var="room" begin="3" end="5" items="${list }">
	<td>침대 객실</td><td>${room.bedroom }</td>
	</c:forEach>
	</tr>
	<tr>
	
	<c:forEach var="room" begin="3" end="5" items="${list }">
	<td>최대 이용 인원</td><td>${room.men }</td>
	</c:forEach>
	</tr>
	<tr>
	
	<c:forEach var="room" begin="3" end="5" items="${list }">
	<td>비품 안내</td><td>${room.content }</td>
	</c:forEach>
	</tr>
	<tr>
	<c:forEach var="room" begin="3" end="5" items="${list }">
	<td colspan=2><input type="button" class="btn btn-primary" onclick="location.href='roomMod.ro?no=${room.no}'" value="수정하기"/></td>
	</c:forEach>
	</tr>
</table>
</div>
</body>
</html>

<%@ include file ="../common/footer.jsp"%>