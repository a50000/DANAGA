<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="../common/header.jsp"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 작성</title>
</head>
<body>
<div class="table-responsive">
	<form method="post" action="noticeWritePro.no">
		<table class="simple_table">
			<tr><th colspan=2 class="thead">공지 쓰기</th></tr>
			<tr><td class="td_right1">제목</td><td class="td_left1"><input required class="form-control" type="text" name="board_subject"/></td></tr>
			<tr><td class="td_right1">내용</td><td class="td_left1"><textarea required class="form-control" name="board_content"></textarea></td></tr>
			<tr><td colspan=2 class="right"><input type="button" class="btn btn-primary btn-sm" onclick="chk()" value="목록으로"/>&nbsp;<input type="submit" class="btn btn-success btn-sm" value="작성하기"/></td>
		</table>
	</form>
</div>
</body>
<script>
	function chk(){
		if(confirm('작성 중인 내용은 저장되지 않습니다. 목록으로 돌아가시겠습니까?')==true)
			location.href="OPnoticeList.no";
	}
</script>   
<%@ include file ="../common/footer.jsp"%>
</html>