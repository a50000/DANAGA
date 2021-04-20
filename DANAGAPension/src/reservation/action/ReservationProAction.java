package reservation.action;

import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import reservation.svc.ReservationProSvc;
import vo.ActionForward;
import vo.ReservationBean;

public class ReservationProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		ReservationBean reservation = null;
		ReservationProSvc reservationProSvc = null;
		try {
		reservation = new ReservationBean();	
		
		reservation.setR_no(request.getParameter("r_no"));
		System.out.println(request.getParameter("r_no"));
		reservation.setRe_name(request.getParameter("re_name"));
		System.out.println(request.getParameter("re_name"));
		reservation.setRe_date(request.getParameter("re_date"));
		System.out.println(request.getParameter("re_date"));
		reservation.setRe_men(request.getParameter("re_men"));
		System.out.println(request.getParameter("re_men"));
		reservation.setRe_price(request.getParameter("re_price"));
		System.out.println(request.getParameter("re_price"));
		reservationProSvc = new ReservationProSvc();
		boolean isReservationSuccess = reservationProSvc.reservationRoom(reservation);
		
		if(isReservationSuccess) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('예약 완료되었습니다.');");
			out.println("location.href='reservationCheck.re'");
			out.println("</script>");
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('예약 실패');");
			out.println("history.back();");
			out.println("</script>");
		}
	} catch(Exception e) {
		e.printStackTrace();
	}
		return forward;
	}
}