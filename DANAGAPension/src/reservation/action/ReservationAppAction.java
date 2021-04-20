package reservation.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import reservation.svc.ReservationAppSvc;
import vo.ActionForward;

public class ReservationAppAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		ReservationAppSvc reservationComfSvc = null;
		if(session.getAttribute("id")==null|| !session.getAttribute("id").equals("op")) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자로 로그인하세요!')");
			out.println("location.href='loginForm.jsp'");
			out.println("</script>");
		}else {
			int r_no = Integer.parseInt(request.getParameter("r_no"));
			String re_date = request.getParameter("re_date");
			int re_no=Integer.parseInt(request.getParameter("re_no"));
			reservationComfSvc = new ReservationAppSvc();
			boolean isCofSuccess = reservationComfSvc.confirmReservation(r_no,re_date);
			if(isCofSuccess) {
				reservationComfSvc.payConf(re_no);
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("reservationAppList.re");
			}else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('예약 확정 실패')");
				out.println("history.back()");
				out.println("</script>");
			}
		} 
		
		return forward;
	}

}
