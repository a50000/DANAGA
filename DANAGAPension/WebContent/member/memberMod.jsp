<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="vo.MemberBean" %> 
<%@include file ="../common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>

</head>
<body>
<form name = "modform" action ="memberModPro.me" method ="post">
<table class="notice">
	<tr>
		<td colspan="2" class="td_title">회원 수정 페이지</td>
	</tr>
	<tr>
		<td><label for = "name">이름</label></td>
		<td align="left"><input readonly type="text" name="name" value="${member.name }"></td> 
	</tr>
	<tr>
		<td><label for = "id">아이디</label></td>
		<td align="left"><input readonly type="text" name="id" value="${member.id }"></td> 
	</tr>
	<tr>
		<td><label for = "pass">비밀번호</label></td>
		<td align="left"><input type="text" name = "pass" value= "${member.passwd }"></td> 
	</tr>
	<tr>
		<td><label for ="email">이메일 주소</label></td>
		<td align ="left"><input type="text" name ="email" value = '${member.email }'/></td>
	</tr>
	<tr>
		<td><label for = "ph_no">휴대폰 번호</label></td>
		<td align="left"><input type="text" name = "ph_no" value= "${member.ph_no}"></td> 
	</tr>
	<tr>
		<td><label for = "grade">등급</label></td>
		<td align="left"><input type="radio" name = "grade" value= "M" ${member.grade eq 'M'?'checked':''}>회원
		<input type="radio" name = "grade" value= "S" ${member.grade eq 'S'?'checked':''}>관리자
		</td> 
	</tr>
	<tr>
		<td colspan ="2">
			<input type ="submit" class="btn btn-primary" value="수정하기">&nbsp;&nbsp;
			<input type ="button" class="btn btn-default" value="목록으로" onclick="location.href='memberList.me'">
		</td>
	</tr>		
</table>
</form>
</body>
</html>
<%@ include file ="../common/footer.jsp"%>