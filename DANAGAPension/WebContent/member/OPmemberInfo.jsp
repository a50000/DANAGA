<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="vo.MemberBean" %>
<jsp:include page="../common/header.jsp"/>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 보기</title>
</head>
<body>
<table class="notice">
	<tr>
		<td>아이디 : </td>
		<td>${member.id }</td>
	</tr>
	<tr>
		<td>이름 : </td>
		<td>${member.name }</td>
	</tr>	
	<tr>
		<td>비밀번호 : </td>
		<td>${member.passwd }</td>
	</tr>
	<tr>
		<td>이메일 주소 : </td>
		<td>${member.email }</td>
	</tr>
	<tr>
		<td>휴대폰 번호 : </td>
		<td>${member.ph_no }</td>
	</tr>
	<tr>
		<td>등급 : </td>
		<td>${member.grade }</td>
	</tr>
	<tr>
		<td colspan ="2"><input type="button" class="btn btn-primary" onclick="location.href='memberModForm.me?id=${member.id }'" value="수정">
		<input type="button" class="btn btn-danger" onclick="javascript:deleteMember('${member.id }','${member.grade }')" value="삭제">
		<input type ="button" class="btn btn-default" value="목록으로" onclick="location.href='memberList.me'"></td>
	</tr>	
</table>
</body>
<script type="text/javascript">
function deleteMember(id,grade) {
	if(confirm('정말 삭제하시겠습니까?')) {
		location.href='memberDel.me?id='+id+'&grade='+grade;
	}
}
</script>
</html>
<%@ include file ="../common/footer.jsp"%>