<%@page import="java.sql.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="vo.Room_StatBean"%>
<%@page import="vo.MemberBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file ="../common/header.jsp" %>
<script>
	function reserv(re){
		re.submit();
	}
</script>
    <%	
    	int[] list=(int[])request.getAttribute("list");//방 리스트
    	ArrayList<Room_StatBean> stat=(ArrayList<Room_StatBean>)request.getAttribute("stat");// 예약 상태
    	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");//캘린더 > date 포맷
    	Calendar c=Calendar.getInstance();//달력 날짜 계산
    	Calendar n=Calendar.getInstance();//현재 날짜
    	c.add(Calendar.MONTH,3);//현재보다 두달 뒤 날짜 표현(캘린더는 월이 0~11월 이므로 현재로 부터 두달은 3을 더해야함)
    	//System.out.println(c.get(Calendar.MONTH));
    	int year=c.get(Calendar.YEAR);
    	int month=c.get(Calendar.MONTH);
    	int date=c.get(Calendar.DATE);

    	
    	try{
    		year=Integer.parseInt(request.getParameter("year"));//년도 파라미터가 없으면 캘린더에서 받아오기
    	}catch(Exception e){
    		year=c.get(Calendar.YEAR);
    	}
    	try{
    		month=Integer.parseInt(request.getParameter("month"));//월 파라미터가 없으면 캘린더에서 받아오기
    	}catch(Exception e){
    		month=c.get(Calendar.MONTH);
    	}
    	if(month==13){//월이 13이라면 년도를 늘리고 1로 초기화
    		year++;
    		month=1;
    	}
    	if(month==0){//월이 0이라면 년도를 줄이고 12로 초기화
    		year--;
    		month=12;
    	}
    	c.set(year,month-1,1);//달력 날짜 설정(1일)
    	int w=c.get(Calendar.DAY_OF_WEEK);//달력에 나타난 월의 1일의 요일
    	int lastday=c.getActualMaximum(Calendar.DATE);//달력 달의 마지막 날
    	
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약 목록</title>
</head>
<body>
<div class="table-responsive">
	<table class="table">
		<tr align="center"><td colspan=7><a href="?year=<%=year-1 %>&month=<%=month%>">◀</a><a href="?year=<%=year %>&month=<%=month-1%>">◁</a><%=year %>년 <%=month %>월<a href="?year=<%=year %>&month=<%=month+1%>">▷</a><a href="?year=<%=year+1 %>&month=<%=month%>">▶</a></td></tr>
		<tr align="center"><td>일</td><td>월</td><td>화</td><td>수</td><td>목</td><td>금</td><td>토</td></tr>
		<tr>
		<%
		if(list==null&&stat==null)out.println("<td colspan=7>점검중입니다.</td></tr>");
		else if(list!=null&&stat==null){
			for(int i=1;i<w;i++){
				out.println("<td>&nbsp;</td>");
			}
			for(int i=1;i<=lastday;i++){
				c.set(year, month-1,i);
				w=c.get(Calendar.DAY_OF_WEEK);
				if(w==7) {
					out.print("<td class='center'><font color='blue'>"+i+"일</font><br><br>");
					int ny=n.get(Calendar.YEAR);//현재 년
					int nm=n.get(Calendar.MONTH);//현재 월
					int nd=n.get(Calendar.DATE);//현재 일
					int cy=c.get(Calendar.YEAR);//달력 년
					int cm=c.get(Calendar.MONTH);//달력 월
					int cd=c.get(Calendar.DATE);//달력 일
					if(ny!=cy||ny==cy&&nm+2>cm||ny==cy&&nm+3<cm) out.println("<button class='btn btn-danger btn-xs disabled'>예약불가</button><br>");//예약 기간 제한(현재 기준 2달 이후~4달 전)
					else {
						String c_date=df.format(c.getTimeInMillis());
						for(int j=0;j<list.length;j++){
							out.print(list[j]+"호 : ");
							out.println("<form class='rw' name='re' action='reservationForm.re' method='post'><input type='hidden' name='r_no' value='"+list[j]+"'/><input type='hidden' name='name' value='"+session.getAttribute("name")+"'/><input type='hidden' name='re_date' value='"+c_date+"'/><button class='btn btn-success btn-xs' onclick='reserv(re)'>예약가능</button></form><br>");
						}
					}
					out.println("</td></tr>");
					
				}else if(w==1) {
					out.print("<td class='center'><font color='red'>"+i+"일</font><br><br>");
					int ny=n.get(Calendar.YEAR);
					int nm=n.get(Calendar.MONTH);
					int nd=n.get(Calendar.DATE);
					int cy=c.get(Calendar.YEAR);
					int cm=c.get(Calendar.MONTH);
					int cd=c.get(Calendar.DATE);
					if(ny!=cy||ny==cy&&nm+2>cm||ny==cy&&nm+3<cm) out.println("<button class='btn btn-danger btn-xs disabled'>예약불가</button><br>");
					else{
						String c_date=df.format(c.getTimeInMillis());
						for(int j=0;j<list.length;j++){
							out.print(list[j]+"호 : ");
							out.println("<form class='rw' name='re' action='reservationForm.re' method='post'><input type='hidden' name='r_no' value='"+list[j]+"'/><input type='hidden' name='name' value='"+session.getAttribute("name")+"'/><input type='hidden' name='re_date' value='"+c_date+"'/><button class='btn btn-success btn-xs' onclick='reserv(re)'>예약가능</button></form><br>");
							
						}}
					out.println("</td>");
					
				}else{
				out.println("<td class='center'>"+i+"일<br><br>");
				String c_date=df.format(c.getTimeInMillis());
				int ny=n.get(Calendar.YEAR);
				int nm=n.get(Calendar.MONTH);
				int nd=n.get(Calendar.DATE);
				int cy=c.get(Calendar.YEAR);
				int cm=c.get(Calendar.MONTH);
				int cd=c.get(Calendar.DATE);
				if(ny!=cy||ny==cy&&nm+2>cm||ny==cy&&nm+3<cm) out.println("<button class='btn btn-danger btn-xs disabled'>예약불가</button><br>");
				else{
					for(int j=0;j<list.length;j++){
						out.print(list[j]+"호 : ");
						out.println("<form class='rw' name='re' action='reservationForm.re' method='post'><input type='hidden' name='r_no' value='"+list[j]+"'/><input type='hidden' name='name' value='"+session.getAttribute("name")+"'/><input type='hidden' name='re_date' value='"+c_date+"'/><button class='btn btn-success btn-xs' onclick='reserv(re)'>예약가능</button></form><br>");
					}
				}out.println("</td>");
				}				
			}if(w!=7){
				for(int i=w;i<7;i++){
					out.println("<td>&nbsp;</td>");
				}
			}
		}
	 	else{
			for(int i=1;i<w;i++){
				out.println("<td>&nbsp;</td>");
			}
			for(int i=1;i<=lastday;i++){
				c.set(year, month-1,i);
				w=c.get(Calendar.DAY_OF_WEEK);
				if(w==7) {
					out.print("<td class='center'><font color='blue'>"+i+"일</font><br><br>");
					int ny=n.get(Calendar.YEAR);//현재 년
					int nm=n.get(Calendar.MONTH);//현재 월
					int nd=n.get(Calendar.DATE);//현재 일
					int cy=c.get(Calendar.YEAR);//달력 년
					int cm=c.get(Calendar.MONTH);//달력 월
					int cd=c.get(Calendar.DATE);//달력 일
					if(ny!=cy||ny==cy&&nm+2>cm||ny==cy&&nm+3<cm) out.println("<button class='btn btn-danger btn-xs disabled'>예약불가</button><br>");//예약 기간 제한(현재 기준 2달 이후~4달 전)
					else {
						String c_date=df.format(c.getTimeInMillis());
						for(int j=0;j<list.length;j++){
							out.print(list[j]+"호 : ");
							for(int k=0;k<stat.size();k++){
								if(stat.get(k).getR_no()==list[j]&&stat.get(k).getDate().equals(c_date)){
									out.println("<button class='btn btn-danger btn-xs disabled'>예약불가</button><br>");
									break;
								}
								if(k==stat.size()-1){
									out.println("<form class='rw' name='re' action='reservationForm.re' method='post'><input type='hidden' name='r_no' value='"+list[j]+"'/><input type='hidden' name='name' value='"+session.getAttribute("name")+"'/><input type='hidden' name='re_date' value='"+c_date+"'/><button class='btn btn-success btn-xs' onclick='reserv(re)'>예약가능</button></form><br>");
								}//"+list[j]+","+session.getAttribute("name")+","+c_date+"
							}
						
						}
					}
					out.println("</td></tr>");
					
				}else if(w==1) {
					out.print("<td class='center'><font color='red'>"+i+"일</font><br><br>");
					int ny=n.get(Calendar.YEAR);
					int nm=n.get(Calendar.MONTH);
					int nd=n.get(Calendar.DATE);
					int cy=c.get(Calendar.YEAR);
					int cm=c.get(Calendar.MONTH);
					int cd=c.get(Calendar.DATE);
					if(ny!=cy||ny==cy&&nm+2>cm||ny==cy&&nm+3<cm) out.println("<button class='btn btn-danger btn-xs disabled'>예약불가</button><br>");
					else{
						String c_date=df.format(c.getTimeInMillis());
						for(int j=0;j<list.length;j++){
							out.print(list[j]+"호 : ");
							for(int k=0;k<stat.size();k++){
								if(stat.get(k).getR_no()==list[j]&&stat.get(k).getDate().equals(c_date)){
									out.println("<button class='btn btn-danger btn-xs disabled'>예약불가</button><br>");
									break;
								}
								if(k==stat.size()-1){
									out.println("<form class='rw' name='re' action='reservationForm.re' method='post'><input type='hidden' name='r_no' value='"+list[j]+"'/><input type='hidden' name='name' value='"+session.getAttribute("name")+"'/><input type='hidden' name='re_date' value='"+c_date+"'/><button class='btn btn-success btn-xs' onclick='reserv(re)'>예약가능</button></form><br>");
								}
							}
						}}
					out.println("</td>");
					
				}else{
				out.println("<td class='center'>"+i+"일<br><br>");
				String c_date=df.format(c.getTimeInMillis());
				int ny=n.get(Calendar.YEAR);
				int nm=n.get(Calendar.MONTH);
				int nd=n.get(Calendar.DATE);
				int cy=c.get(Calendar.YEAR);
				int cm=c.get(Calendar.MONTH);
				int cd=c.get(Calendar.DATE);
				if(ny!=cy||ny==cy&&nm+2>cm||ny==cy&&nm+3<cm) out.println("<button class='btn btn-danger btn-xs disabled'>예약불가</button><br>");
				else{
					for(int j=0;j<list.length;j++){
						out.print(list[j]+"호 : ");
						for(int k=0;k<stat.size();k++){
							if(stat.get(k).getR_no()==list[j]&&stat.get(k).getDate().equals(c_date)){
								out.println("<button class='btn btn-danger btn-xs disabled'>예약불가</button><br>");
								break;
							}
							if(k==stat.size()-1){
								out.println("<form class='rw' name='re' action='reservationForm.re' method='post'><input type='hidden' name='r_no' value='"+list[j]+"'/><input type='hidden' name='name' value='"+session.getAttribute("name")+"'/><input type='hidden' name='re_date' value='"+c_date+"'/><button class='btn btn-success btn-xs' onclick='reserv(re)'>예약가능</button></form><br>");
							}
						}
					}
				}out.println("</td>");
				}				
			}
			if(w!=7){
				for(int i=w;i<7;i++){
					out.println("<td>&nbsp;</td>");
				}
			}
		} 
		%>
	</table>
</div>
</body>
</html>

<%@ include file ="../common/footer.jsp"%>