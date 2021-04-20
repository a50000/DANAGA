<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="vo.Room_StatBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Calendar"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file ="../common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숙박 예약</title>
<script>
	function sum() {
		if (document.getElementById("men").value) {
			document.getElementById('total').value = 50000 + parseInt(document
					.getElementById('men').value) * 10000;
		}
	}
	function chk() {
		if (confirm('예약하시겠습니까? (3일 내 미입금시 취소처리 됩니다.)') == false) {
			return false;
		}
	}
	function back(){
		if(confirm('작성 중인 내용은 저장되지 않습니다. 이전 페이지로 돌아가시겠습니까?')==true){
			location.href="reservationState.re";
		}
	}
</script>
</head>
<body>
	<form name="reservationForm" action="reservationProcess.re"
		method="post" onsubmit="return chk()">
		<table class="reserv">
			<tr>
				<td colspan="2" align="center">객실 예약하기</td>
			</tr>
			<tr>
				<td align="center">객실</td>
				<td><input type="hidden" name="r_no" value="${param.r_no }" />${param.r_no }호</td>
			</tr>
			<tr>
				<td align="center">예약자 성명</td>
				<td><input type="hidden" name="re_name" value="${param.name }" />${param.name}</td>
			</tr>
			<tr>
				<td align="center">선택일자</td>
				<td><input type="hidden" name="re_date"
					value="${param.re_date }" />${param.re_date}</td>
			</tr>
			<tr>
				<td align="center">인원추가</td>
				<td><select name="re_men" id="men" onchange="sum()">
						<option value="0">0명</option>
						<option value="1">1명</option>
						<option value="2">2명</option>
						<option value="3">3명</option>
						<option value="4">3명</option>
						<option value="5">5명</option>
						<option value="6">6명</option>
						<option value="7">7명</option>
				</select> 추가</td>
			</tr>
			<tr>
				<td align="center">총 결제금액</td>
				<td><input type='text' name='re_price' id='total' value=50000
					readonly><b>원</b></td>
			</tr>
			<tr>
				<td colspan="2" align="center">약관 동의</td>
			</tr>
			<tr>
				<td colspan="2"><input type="checkbox" id="use" required="required" /><label for="use">이용 유의사항에 동의</label>
					<hr> <font size=2>
					<ul>
					<li>기준인원 초과시 성인 추가요금(10,000원)이 부과됩니다.(최대인원 내에 유아 아동포함)</li><li>최대인원 초과시 필히 관리자에게 문의바랍니다.</li>
					<li>입실시간은 오후 15:00시부터, 퇴실시간은 오전 11:00시까지입니다.</li><li>미성년자는 숙박 할 수 없으며 보호자 동반 시에만 입실이 가능합니다.</li><li>애완동물과 함께 입실 하실 수 없습니다.</li>
					<li>전 지역이 금연구역입니다.(바비큐장 앞이나 C동앞 이용바랍니다.) <font color="red">* 객실 내 절대금연</font></li>
					<li>복층객실을 예약하시는 부모님께서는 아동이나 어린이의 안전을 위해 각별한 주의가 필요합니다.</li><li> 스파1회무료(1회초과 사용시 추가요금(20,000원)이 발생됩니다.)</li><li>퇴실하실때에는 키를 꽂아두고 가시면 됩니다</li>
					<li>주방정리정돈해주시고 음식물,재활용품,일반쓰레기 분리 후 버려주시길 바랍니다.</li><li>객실 내 모든 비품은 펜션의 소중한 자산입니다.</li><li>훼손, 분실에 대한 책임은 투숙객에게 있으므로 주의하시길바랍니다.(퇴실 후 젓가락부터 침구, 가전제품 등 모든 비품의 갯수 파손상태 확인합니다.)</li>
					<li>스파사용시 입욕제는 고장의 원인이 되므로 사용을 금합니다.</li>
					</ul>
				</font></td>
			</tr>
			<tr>
				<td colspan="2"><input type="checkbox" id="cancel" required="required" /><label for="cancel">취소 수수료에 동의</label>
					<hr> <font size=2>
					<ul>
					<li>예약취소는 전화를 통해서만 가능합니다.</li>
					<li>취소수수료는 예약시점과는 무관한 이용시작일 기준입니다.</li>
					<li>환불시 결제하신 금액에서 취소수수료를 제외한 금액을 환불해 드립니다.</li>
					<li>취소수수료는 결제금액이 아닌 예약금(객실요금+기타옵션요금) 기준으로 책정됩니다.</li>
					<li>취소수수료가 100%인 경우 전액 환불되지 않습니다.</li>
					<li>수수료 내역은 아래와 같습니다.</li></ul></font></td>
			</tr>
			<tr>
				<td colspan="2">
					<table border=1 align="center">
					<tr><td>기준</td><td>취소 수수료(%)</td><td>환불액(%)</td></tr>
					<tr><td>이용일 <b>당일</b> 취소시</td><td>100%</td><td>0%환불 없음</td></tr>
					<tr><td>이용일 <b>1</b>일전 취소시</td><td>100%</td><td>0%환불 없음</td></tr>
					<tr><td>이용일 <b>2</b>일전취소시</td><td>100%</td><td>0%환불 없음</td></tr>
					<tr><td>이용일 <b>3</b>일전취소시</td><td>70%</td><td>30%환불</td></tr>
					<tr><td>이용일 <b>4</b>일전취소시</td><td>60%</td><td>40%환불</td></tr>
					<tr><td>이용일 <b>5</b>일전취소시</td><td>50%</td><td>50%환불</td></tr>
					<tr><td>이용일 <b>6</b>일전취소시</td><td>40%</td><td>60%환불</td></tr>
					<tr><td>이용일 <b>7</b>일전취소시</td><td>30%</td><td>70%환불</td></tr>
					<tr><td>이용일 <b>8</b>일전취소시</td><td>20%</td><td>80%환불</td></tr>
					<tr><td>이용일 <b>9</b>일전취소시</td><td>10%</td><td>90%환불</td></tr>
					<tr><td>이용일 <b>10</b>일전취소시</td><td>0%</td><td>100%환불</td></tr>
					<tr><td>기본 취소 수수료</td><td>0%</td><td>100%환불</td></tr></font>
					</table>
					</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"	class="btn btn-success btn-sm" value="예약하기">&nbsp;&nbsp; <input type="button" class="btn btn-default btn-sm" value="돌아가기" onclick="back()"/></td>
			</tr>
		</table>
	</form>
</body>
</html>

<%@ include file ="../common/footer.jsp"%>