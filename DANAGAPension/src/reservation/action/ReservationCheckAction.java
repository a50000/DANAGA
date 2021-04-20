package reservation.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import reservation.svc.ReservationCheckSvc;
import vo.ActionForward;
import vo.ReservationBean;

public class ReservationCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		HttpSession session=request.getSession();
		String name=(String)session.getAttribute("name");
		ArrayList<ReservationBean> reservationBean=null;
		ReservationCheckSvc reservationCheckSvc=null;
		try {
			if (session.getAttribute("id") == null || !session.getAttribute("grade").equals("M")) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('회원페이지입니다.')");
				out.println("location.href='loginForm.log'");
				out.println("</script>");
		} else {
			reservationBean=new ArrayList<ReservationBean>();
			reservationCheckSvc=new ReservationCheckSvc();
			reservationBean=reservationCheckSvc.chkReservation(name);
			request.setAttribute("list", reservationBean);
			forward=new ActionForward();
			forward.setPath("/reservation/reservationCheck.jsp");
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
