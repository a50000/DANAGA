<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="vo.MemberBean" %>
<%@include file ="../common/header.jsp" %>
 
<!DOCTYPE html>
<html>
<head>
<script>
	function delChk(id){
		if(confirm('정말 탈퇴하시겠습니까?')==true) {
			location.href="memberResign.me?id="+id;
		}
	}
</script>
<meta charset="UTF-8">
<title>회원 정보 보기</title>
</head>
<body>
<form action="memberAlterForm.me" method="post">
<table class="memberinfo">
	<tr>
		<th class="thead">
			<h1>마이페이지</h1>
		</th>
	</tr>
	<tr>
		<td class="thema"><h3>&nbsp;&nbsp;&nbsp;아이디</h3></td>
	</tr>
	<tr>
		<td class="cont"><h4>&nbsp;&nbsp;아이디 : ${member.id }</h4></td>
	</tr>
	<tr>
		<td class="thema"><h3>&nbsp;&nbsp;&nbsp;이름</h3></td>
	</tr>
	<tr>
		<td class="cont"><h4>&nbsp;&nbsp;이름 : ${member.name }</td>
	</tr>	
	<tr>
		<td class="thema"><h3>&nbsp;&nbsp;&nbsp;비밀번호</h3></td>
	</tr>
	<tr>
		<td class="cont"><h4>&nbsp;&nbsp;비밀번호 : ${member.passwd }</td>
	</tr>
	<tr>
		<td class="thema"><h3>&nbsp;&nbsp;&nbsp;이메일</h3></td>
	</tr>
	<tr>
		<td class="cont"><h4>&nbsp;&nbsp;이메일 : ${member.email }</td>
	</tr>
	<tr>
		<td class="thema"><h3>&nbsp;&nbsp;&nbsp;휴대폰 번호</h3></td>
	</tr>
	<tr>
		<td class="cont"><h4>&nbsp;&nbsp;휴대폰 번호 : ${member.ph_no }</td>
	</tr>
	<tr>
	<td colspan=2 class="ttail"><br><input type="hidden" name="id" value="${member.id }"/><input type="submit" class="btn btn-primary" value="수정하기"/>
	<input type="button" class="btn btn-danger" onclick="delChk('${member.id}')" value="탈퇴하기"></td></tr>
</table>
</form>
</body>
</html>
<%@ include file ="../common/footer.jsp"%>