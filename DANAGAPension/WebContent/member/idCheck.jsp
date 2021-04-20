<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복확인</title>
</head>
<script>
function init(){
	if(${param.openInit ne null}){
		document.getElementById("id").value=opener.document.getElementById("id").value;
	}
}
function ok(v){
	opener.idcheck=v.trim();
	opener.document.getElementById("id").value=v.trim();
	opener.chkId=true;
	window.close();
}
</script>
<body onload="init()">
	<form action="idCheckPro.log" method="post">
		<input type="text" id="id" pattern="^[A-Za-z0-9]{4,16}$" required name="id">&nbsp;<input type="submit" value="중복 확인">
	</form>
<%--  <%
		if(request.getParameter("id")!=null){
		if(request.getParameter("useable").equals("yes")){%>
		${param.id}는 사용 가능한 아이디입니다.
		<input type='button' value='사용하기' onclick="ok('${param.id}');"/>
<%			
		}else{
			out.println(request.getParameter("id")+"는 사용 불가능한 아이디입니다.");
		}
		}
%>  --%>
<c:if test="${param.id ne null}">
	<c:choose>
		<c:when test="${param.useable eq 'yes' }">
			${param.id}는 사용 가능한 아이디입니다.
			<input type='button' value='사용하기' onclick="ok('${param.id}');"/>
		</c:when>
		<c:otherwise>
			${param.id }는 사용 불가능한 아이디입니다.
		</c:otherwise>
	</c:choose>
</c:if>
</body>
</html>