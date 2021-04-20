<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@include file ="../common/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방 목록 수정</title>
</head>
<body>
<form method="post" action="roomModPro.ro">
	<table border=1 align=center width=850px>
			<tr><td>객실 번호</td><td><input type="hidden" name="no1" value="${Info.get(0).getNo() }" size=1/><input type="text" name="no" value="${Info.get(0).getNo() }" readonly size=1/></td></tr>
			<tr><td>객실 타입</td><td><input type="text" name="type" value="${Info.get(0).getType() }" maxlength="100" size=100/></td></tr>
			<tr><td>평수</td><td><input type="text" name="size" value="${Info.get(0).getSize() }" size=1/></td></tr>
			<tr><td>침대 객실</td><td><input type="text" name="bedroom" value="${Info.get(0).getBedroom() }" size=1/></td></tr>
			<tr><td>최대 이용 인원</td><td><input type="text" name="men" value="${Info.get(0).getMen() }" size=1/></td></tr>
			<tr><td>비품 안내</td><td><input type="text" name="content" value="${Info.get(0).getContent() }" maxlength="100" size=100/></td></tr>
		<tr align=right><td colspan=6><input type="submit" class="btn btn-primary" value="수정하기"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="btn btn-default" onclick="history.back()" value="목록으로">&nbsp;&nbsp;</td></tr>
	</table>
</form>
</body>
</html>

<%@ include file ="../common/footer.jsp"%>