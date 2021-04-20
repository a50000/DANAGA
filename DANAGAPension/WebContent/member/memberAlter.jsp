<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="vo.MemberBean" %> 
<%@include file ="../common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
<style>

</style>
</head>
<body>
<form action ="memberAlterPro.me" method ="post">
<table class="notice">
	<tr>
		<td class="ttail"><h1>개인 정보 수정</h1></td>
	</tr>
	<tr>
	<td class="cont">아이디</td>
	</tr>
	<tr>
		<td class="thema"><input disabled type="text" class="mod"  value="${member.id }">
		<input type="hidden" name="id" value="${member.id }"></td> 
	</tr>
	<tr>
		<td class="cont">이름</td>
	</tr>
	<tr>		
		<td class="thema"><input type="text" class="mod" name="name" value="${member.name }"></td> 
	</tr>	
	<tr>
	<td class="cont">비밀번호</td>
	</tr>
	<tr>
		<td class="thema"><input type="text" class="mod" name = "pass" value= "${member.passwd }"></td> 
	</tr>
	<tr>
	<td class="cont">이메일 주소</td>
	</tr>
	<tr>
		<td class="thema"><input type="text" class="mod" name ="email" value = '${member.email }'/></td>
	</tr>
	<tr>
	<td class="cont">휴대폰 번호</td>
	</tr>
	<tr>
		<td class="thema"><input type="text" class="mod" name = "ph_no" pattern="(010)-\d{4}-\d{4}" title="010-0000-0000" value= "${member.ph_no}"><input type="hidden" name = "grade" value= "M"/></td>		 
	</tr>
	<tr>
		<td class="thead">
			<input type ="submit" class="btn btn-primary" value="수정하기">&nbsp;&nbsp;
			<input type ="button" class="btn btn-default" value="돌아가기" onclick="history.back();">
		</td>
	</tr>		
</table>
</form>
</body>
</html>
<%@ include file ="../common/footer.jsp"%>