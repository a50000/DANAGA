<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ include file ="../common/header.jsp"%> 
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.cajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootom/strap.min.js"></script>   

<meta charset="UTF-8">
<title>방 목록</title>
</head>
<body>
<c:forEach var="room" items="${list }">
	<div id="${room.no }" style="background-image:url(${pageContext.request.contextPath }/roomImg/${room.no}.png); width:1920px; height:1080px;">
		<div class="room">
			객실번호 : ${room.no }<br>
			객실타입 : ${room.type }<br>
			객실평수 : ${room.size }<br>
			침대 객실 : ${room.bedroom }<br>
			최대 이용 인원 : ${room.men }<br>
			비품안내 : ${room.content }<br>
			<a href="roomInfo.ro?r_no=${room.no }" target="_blank"><font color="blue">사진 더보기(클릭)</font></a>
		</div>
	</div>
</c:forEach>
</body>    
<%@ include file ="../common/footer.jsp"%>
</html>