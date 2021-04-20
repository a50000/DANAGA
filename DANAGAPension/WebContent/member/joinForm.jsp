<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file ="../common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<script>
var chkId=false;
var idcheck;
function checkForm(){
		if(document.getElementById("pw").value.trim()!=document.getElementById("cpw").value.trim()){
			alert("비밀번호가 일치하지 않습니다.");
			document.getElementById("cpw").focus();
			return false;
		}		
		if(!chkId||idcheck!=document.getElementById("id").value.trim()){
			alert("아이디 중복확인을 하세요.");
			return false;
		}
}
</script>
<body>
<form name="joinform" action="joinProcess.me" method="post" onsubmit="return checkForm();">
<div class="join">
<div class="center">
		<h1>DANAGA</h1></div>
		<div class="id">
		<input type="text" class="form-control" placeholder="아이디" name="id" id = "id" required/>
		</div>
		<div class="idchk"><input type="button" class="btn btn-warning" value="중복확인" name="idCheck" onclick="window.open('idCheck.log?openInit=true','','top=300, left=800, width=400,height=400')"/></div><br><br><br>	
		<input type="text" placeholder="이름" class="form-control" title="한글이름으로 작성해주세요" pattern="^[가-힣]*$" name="name" id = "name" required/><br>
		<input type="password" placeholder="비밀번호" class="form-control" name="pass" id = "pw" minlength=6 required/><br>
		<input type="password" placeholder="비밀번호 확인" class="form-control" id = "cpw" minlength=6 required/><br>
		<div class="email">
		<input type="text" placeholder="이메일" class="form-control" width="70%" pattern="^[a-zA-Z0-9]+$" name="email" id = "email" required/>
		</div><font size="5">@</font>
		<div class="email1">
		<select class="form-control" name="email1">
		<option value="@naver.com">naver.com</option>
		<option value="@gamil.com">gmail.com</option>
		<option value="@hanmail.net">hanmail.net</option>		
		</select></div><br><br>
		<input type="text" placeholder="휴대폰 번호" class="form-control" pattern="(010)-\d{4}-\d{4}" title="010-0000-0000" name="ph_no" id = "ph_no" onchange="tel()" required/><br>
		<div class="center">
		<input type="submit" class="btn btn-info btn-lg" value="가입 하기"/>
		</div>
</div>
</form>
</body>
</html>
<%@ include file ="../common/footer.jsp"%>