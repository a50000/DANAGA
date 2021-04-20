<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="../common/header.jsp"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항(관리자)</title>
</head>
<script>
function del(a){
	if(confirm('삭제하시겠습니까?')!=false)
		location.href="noticeDelete.no?board_no="+a;
}
</script>
<body>
	<table class="simple_table">
		<tr height=50px><td width=10% class="gray">제목</td><td width=40%>${notice.board_subject }</td><td width=10% class="gray">날짜</td><td width=20%>${notice.board_date }</td><td width=10% class="gray">조회수</td><td width=10%>${notice.board_readcount }</td></tr>
		<tr height=700px><td class="gray">내용</td><td colspan=5 >${notice.board_content }</td></tr>
		<tr><td colspan=6 class="right"><input type="button" class="btn btn-primary btn-sm" onclick="location.href='OPnoticeList.no'" value="목록으로"/>&nbsp;&nbsp;&nbsp;
		<input type="button" onclick="del(${notice.board_no})" class="btn btn-warning btn-sm" value="삭제하기"/></td></tr>
	</table>
</body> 
<%@ include file ="../common/footer.jsp"%>
</html>