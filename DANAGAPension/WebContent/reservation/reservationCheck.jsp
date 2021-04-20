<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@include file ="../common/header.jsp" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${list ne null }">
			<table class="notice">
				<tr><td colspan=7><h1>예약목록</h1></td></tr>
				<tr><td><h4>예약자</h4></td><td><h4>방 번호</h4></td><td><h4>예약일</h4></td><td><h4>추가인원</h4></td><td><h4>가격</h4></td><td><h4>결제 여부</h4></td><td><h4>결제 지연</h4></td></tr>
				<c:forEach var="reservation" items="${list }">
					<tr><td>${reservation.re_name }</td><td>${reservation.r_no }</td><td>${reservation.re_date }</td><td>${reservation.re_men }</td><td>${reservation.re_price }</td>
					<c:set var="now" value="<%=new java.util.Date()%>" />
							 <c:set var="today"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/></c:set>
							<fmt:parseDate value="${reservation.delay }" var="str" pattern="yyyy-MM-dd"/>
							<fmt:parseNumber value="${str.time / (1000*60*60*24)}" integerOnly="true" var="strDate"></fmt:parseNumber>
							<fmt:parseDate value="${today }" var="end" pattern="yyyy-MM-dd"/>
							<fmt:parseNumber value="${end.time / (1000*60*60*24)}" integerOnly="true" var="endDate"></fmt:parseNumber>
							<td>${reservation.pay_yn eq 'n'?'결제 확인중':'결제 완료' }</td>
							<td><c:choose>
								<c:when test="${reservation.pay_yn eq 'n'}">
									${endDate-strDate}일
								</c:when>
								<c:otherwise>
								-</c:otherwise>
							</c:choose>
							</td>
							
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
		<table class="notice">
				<tr><td colspan=7><h1>예약내역이 없습니다.</h1></td></tr>
		</table>
		</c:otherwise>
	</c:choose>
</body>
</html>

<%@ include file ="../common/footer.jsp"%>