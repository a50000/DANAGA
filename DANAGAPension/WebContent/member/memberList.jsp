<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*, vo.*" %>  
<%@include file ="../common/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록보기</title>
</head>
<body>
<div class="table-responsive">
<div class="notice">
<c:choose>
<c:when test="${memberList ne null && pageInfo.listCount >0 }">
<table border=1>
	<tr><td colspan ="2" >회원 목록</td></tr>
	<c:forEach var="list" items="${ memberList}">
	<tr>
		<td>
			<a href ="OPmemberInfo.me?id=${list.id }">
				${list.id }
			</a>
			</td>
			<td>
				<input type="button" class="btn btn-primary btn-sm" onclick="location.href='memberModForm.me?id=${list.id }'" value="수정">&nbsp;&nbsp;
				<input type="button" class="btn btn-danger btn-sm" onclick="javascript:deleteMember('${list.id }','${list.grade }')" value="삭제">
			</td>
		</tr>
	</c:forEach>
</table>
	<ul class="pagination">
	<c:choose>
	<c:when test="${pageInfo.page <= 1 }">
			<li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
		</c:when>
		<c:otherwise>
			<li><a href="memberList.me?page=${pageInfo.page-1 }" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
		</c:otherwise>
	</c:choose>
	<c:forEach var="now" begin="${pageInfo.startPage }"
		end="${pageInfo.endPage }" step="1">
		<c:choose>
			<c:when test="${pageInfo.page eq now }">
				<li class="active"><a href="#">${now } <span class="sr-only">(current)</span></a></li>
			</c:when>
			<c:otherwise>
				<li><a href="memberList.me?page=${now }">${now }</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${pageInfo.page >= pageInfo.maxPage}">
			<li class="disabled"><a href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
		</c:when>
		<c:otherwise>
			<li>
      <a href="memberList.me?page=${pageInfo.page+1 }" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
		</c:otherwise>
	</c:choose>
	</ul>
<%-- <c:choose>
		<c:when test="${pageInfo.page <= 1 }">
			[이전]&nbsp;
		</c:when>
		<c:otherwise>
			<a href="memberList.me?page=${pageInfo.page-1 }">[이전]</a>
		</c:otherwise>
</c:choose>
	<c:forEach var="now" begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1">
		<c:choose>
			<c:when test="${pageInfo.page eq now }">
				[${now }]
			</c:when>
			<c:otherwise>
				<a href="memberList.me?page=${now }">[${now }]</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${pageInfo.page >= pageInfo.maxPage}">
			&nbsp;[다음]
		</c:when>
		<c:otherwise>
			<a href="memberList.me?page=${pageInfo.page+1 }">[다음]</a>
		</c:otherwise>
	</c:choose>
 --%></c:when>
<c:otherwise>
	등록한 회원이 없습니다.
</c:otherwise>
</c:choose>
</div>
</div>
<script type="text/javascript">
function deleteMember(id,grade) {
	if(confirm('정말 삭제하시겠습니까?')) {
		location.href='memberDel.me?id='+id+'&grade='+grade;
	}
}
</script>
</body>
</html>
<%@ include file ="../common/footer.jsp"%>