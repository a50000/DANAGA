<%@page import="vo.NoticeBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@include file ="../common/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항(관리자)</title>
</head>
<script>
	function go(a){
		document.getElementById(a).submit();
	}
</script>
<body>
<div class="table-responsive">
<div class="notice">
<c:choose>
<c:when test="${notice ne null && pageInfo.listCount > 0 }">
<table class="table table-striped">
	<h1>공지사항</h1>
		<tr><td><b>글 번호</b></td><td><b>제목</b></td><td><b>날짜</b></td><td><b>조회수</b></td></tr>
		<c:forEach var="list" items="${notice}">
			<form action="OPnoticeInfo.no" id="${list.board_no }" method="post">
				<tr><td>${list.board_no }<input type="hidden" name="board_no" value="${list.board_no }"/></td><td><a href="#" onclick="go(${list.board_no })">${list.board_subject }</a></td><td>${list.board_date }</td><td>${list.board_readcount }</td></tr>
			</form>
		</c:forEach>
		<tr align=right><td colspan=4><input type="button" value="작성하기" class="btn btn-success" onclick="location.href='noticeWrite.no'"></td></tr>
	</table>
		<ul class="pagination">
	<c:choose>
	<c:when test="${pageInfo.page <= 1 }">
			<li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
		</c:when>
		<c:otherwise>
			<li><a href="OPnoticeList.no?page=${pageInfo.page-1 }" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
		</c:otherwise>
	</c:choose>
	<c:forEach var="now" begin="${pageInfo.startPage }"
		end="${pageInfo.endPage }" step="1">
		<c:choose>
			<c:when test="${pageInfo.page eq now }">
				<li class="active"><a href="#">${now } <span class="sr-only">(current)</span></a></li>
			</c:when>
			<c:otherwise>
				<li><a href="OPnoticeList.no?page=${now }">${now }</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${pageInfo.page >= pageInfo.maxPage}">
			<li class="disabled"><a href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
		</c:when>
		<c:otherwise>
			<li>
      <a href="OPnoticeList.no?page=${pageInfo.page+1 }" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
		</c:otherwise>
	</c:choose>
	</ul>
<%-- 	<c:choose>
		<c:when test="${pageInfo.page <= 1 }">
			[이전]&nbsp;
		</c:when>
		<c:otherwise>
			<a href="OPnoticeList.no?page=${pageInfo.page-1 }">[이전]</a>
		</c:otherwise>
	</c:choose>
	<c:forEach var="now" begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1">
		<c:choose>
			<c:when test="${pageInfo.page eq now }">
				[${now }]
			</c:when>
			<c:otherwise>
				<a href="OPnoticeList.no?page=${now }">[${now }]</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${pageInfo.page >= pageInfo.maxPage}">
			&nbsp;[다음]
		</c:when>
		<c:otherwise>
			<a href="OPnoticeList.no?page=${pageInfo.page+1 }">[다음]</a>
		</c:otherwise>
	</c:choose> --%>
</c:when>
<c:otherwise>
	등록된 공지사항이 없습니다.<br>
	<input type="button" value="작성하기" class="btn btn-primary btn-sm" onclick="location.href='noticeWrite.no'"/>
</c:otherwise>
</c:choose>
</div>
</div>
</body>
</html>

<%@ include file ="../common/footer.jsp"%>