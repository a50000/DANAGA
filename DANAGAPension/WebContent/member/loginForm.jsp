<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file ="../common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<form name ="loginform" action = "loginProcess.log" class="form-horizontal" method ="post">
<div class="login">
	<h1>DANAGA</h1>
	<div class="form-group">
		<label for ="id" class="col-sm-2 control-label">아이디</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" name ="id" id ="id" placeholder="아이디 입력"/>
		</div>
	</div>
	<div class="form-group">
		<label for ="pass" class="col-sm-2 control-label">비밀번호</label>
		<div class="col-sm-10">
			<input type="password" class="form-control" name ="pass" id ="pass" placeholder="비밀번호 입력"/>
		</div>
	</div>
	<div class="form-group">
		<input type="submit" class="btn btn-default " value="로그인"/>&nbsp;&nbsp;
		<input type="button" class="btn btn-info" value="회원가입" onclick="location.href='joinForm.me'"/>
	</div>
</div>
</form>
<!-- <form name ="loginform" action = "loginProcess.log" method ="post">
<table>
	<tr>
		<td colspan ="2" class ="td_title">
		로그인 페이지
		</td>
	</tr>
	<tr>
		<td><label for ="id">아이디 :</label></td>
		<td><input type="text" name ="id" id ="id"/></td>
	</tr>
	<tr>
		<td><label for ="pass">비밀번호 :</label></td>
		<td><input type="password" name ="pass" id ="pass"/></td>
	</tr>		
	<tr>
		<td colspan ="2">
			<input type="submit" value="로그인"/>&nbsp;&nbsp;
			<input type="button" value="회원가입" onclick="location.href='joinForm.me'"/>
		</td>
	</tr>		
</table>
</form> -->
</body>
</html>
<%@ include file ="../common/footer.jsp"%>