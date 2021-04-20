package reservation.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import reservation.svc.ReservationDeleteAppSvc;
import reservation.svc.reservationStateSvc;
import vo.ActionForward;

public class ReservationDeleteAppAction  implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		ReservationDeleteAppSvc reservationDeleteSvc=null;
		boolean isDelSuccess=false;
		try {
			int re_no=Integer.parseInt(request.getParameter("re_no"));
			String date=(String)request.getParameter("re_date");
			int r_no=Integer.parseInt(request.getParameter("r_no"));
			reservationDeleteSvc = new ReservationDeleteAppSvc();
			isDelSuccess=reservationDeleteSvc.deleteReservationApp(re_no,r_no,date);
			if(isDelSuccess) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('취소되었습니다.')");
				out.println("location.href='reservationAppList.re'");
				out.println("</script>");
			}else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('취소 실패.')");
				out.println("history.back();");
				out.println("</script>");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
	
}
