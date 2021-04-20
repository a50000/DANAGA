package reservation.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import reservation.svc.ReservationInfoSvc;
import vo.ActionForward;
import vo.ReservationBean;

public class ReservationInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		if(session.getAttribute("id")==null|| !session.getAttribute("grade").equals("S")) {
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자로 로그인하세요!')");
			out.println("location.href = 'loginForm.log'");
			out.println("</script>");
		}else {
			int re_no = Integer.parseInt(request.getParameter("re_no"));
			
			ReservationInfoSvc reservationInfoSvc = new ReservationInfoSvc();
			ReservationBean reservation  = reservationInfoSvc.getReservation(re_no);
			if(reservation != null) {
				request.setAttribute("reservation", reservation);
				forward = new ActionForward();
				forward.setPath("/reservation/reservationInfo.jsp");
			}else {
				response.setContentType("text/html/charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('예약 정보가 없습니다.')");
				out.println("location.href = reservationList.re");
				out.println("</script>");
			}
		}
		return forward;
	}

}
