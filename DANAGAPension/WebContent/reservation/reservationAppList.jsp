<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, vo.*"%>
<%@include file ="../common/header.jsp" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약 목록보기</title>
<style>
</style>
</head>
<body>
<div class="table-responsive">
<div class="notice">
	<table border="1">
		<tr>
			<td colspan=8 align="center">예약 목록</td>
		</tr>
		<tr>
			<td>예약 번호</td>
			<td>예약자</td>
			<td>방 번호</td>
			<td>날짜</td>
			<td>추가 인원</td>
			<td>가격</td>
			<td>결제 지연</td>			
			<td>예약 처리</td>
		</tr>
		<c:choose>
			<c:when test="${reservationlist ne null }">
				<c:forEach var="reservation" items="${reservationlist }">
					<form action="reservationDeleteApp.re" method="post"
						onsubmit="return delchk()">
						<tr>
							<td>${reservation.re_no }<input type="hidden" name="re_no"
								value="${reservation.re_no }"></td></td>
							<td>${reservation.re_name }</td>
							<td>${reservation.r_no }<input type="hidden" name="r_no"
								value="${reservation.r_no }"></td>
							<td>${reservation.re_date }<input type="hidden"
								name="re_date" value="${reservation.re_date }"></td>
							<td>${reservation.re_men }명</td><td>${reservation.re_price }</td>
							 <c:set var="now" value="<%=new java.util.Date()%>" />
							 <c:set var="today"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/></c:set>
							<fmt:parseDate value="${reservation.delay }" var="str" pattern="yyyy-MM-dd"/>
							<fmt:parseNumber value="${str.time / (1000*60*60*24)}" integerOnly="true" var="strDate"></fmt:parseNumber>
							<fmt:parseDate value="${today }" var="end" pattern="yyyy-MM-dd"/>
							<fmt:parseNumber value="${end.time / (1000*60*60*24)}" integerOnly="true" var="endDate"></fmt:parseNumber>
							<td><c:choose>
								<c:when test="${reservation.pay_yn eq 'n'}">
									${endDate-strDate}일
								</c:when>
								<c:otherwise>
								-</c:otherwise>
							</c:choose></td>
							<c:choose>
								<c:when test="${reservation.pay_yn eq 'n' }">
									<td><input type="button" onclick="pay('${reservation.re_no}')" class="btn btn-primary btn-sm" value="결제 확인">&nbsp;<input type="submit" class="btn btn-danger btn-sm" value="취소"/></td>
								</c:when>
								<c:otherwise><td>
								<c:choose>
									<c:when test="${today > reservation.re_date }">
										<input type="submit" class="btn btn-warning btn-sm" value="삭제"/>
									</c:when>
									<c:otherwise>
										<input type="submit" class="btn btn-danger btn-sm" value="취소"/>
									</c:otherwise>
								</c:choose>
								</td></c:otherwise>
							</c:choose>
					</tr>
					</form>
				</c:forEach>
	</table>
	<ul class="pagination">
	<c:choose>
	<c:when test="${pageInfo.page <= 1 }">
			<li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
		</c:when>
		<c:otherwise>
			<li><a href="reservationAppList.re?page=${pageInfo.page-1 }&type=${type}&search=${search}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
		</c:otherwise>
	</c:choose>
	<c:forEach var="now" begin="${pageInfo.startPage }"
		end="${pageInfo.endPage }" step="1">
		<c:choose>
			<c:when test="${pageInfo.page eq now }">
				<li class="active"><a href="#">${now } <span class="sr-only">(current)</span></a></li>
			</c:when>
			<c:otherwise>
				<li><a href="reservationAppList.re?page=${now }&type=${type}&search=${search}">${now }</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${pageInfo.page >= pageInfo.maxPage}">
			<li class="disabled"><a href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
		</c:when>
		<c:otherwise>
			<li>
      <a href="reservationAppList.re?page=${pageInfo.page+1 }&type=${type}&search=${search}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
		</c:otherwise>
	</c:choose>
	</ul>
	</c:when>
	<c:otherwise>
		<tr>
			<td colspan=8>예약 내역이 없습니다.</td>
		</tr>
	</table>
	</c:otherwise>
	</c:choose>
	
	<form action="reservationAppList.re" method="post">
		<select name="type">
			<option value="">=검색=</option>
			<option value="예약자">예약자</option>
			<option value="날짜">날짜</option>
		</select>
		<input type="text" name="search">
		<input type="submit" value="검색"/> 
	</form>
	</div>
</div>
	<script>
	function pay(a) {
		if (confirm('결제 확인하시겠습니까?') == true) {
			location.href="reservationPay.re?re_no="+a;
		}
	}
		function delchk() {
			if (confirm('취소 하시겠습니까?') == false) {
				return false;
			}
		}
	</script>
</body>
</html>

<%@ include file ="../common/footer.jsp"%>