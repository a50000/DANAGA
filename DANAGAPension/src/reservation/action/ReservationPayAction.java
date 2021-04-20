package reservation.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import reservation.svc.ReservationPaySvc;
import vo.ActionForward;

public class ReservationPayAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		ReservationPaySvc reservationPaySvc=null;
		boolean paySuccess=false;
		try {
			int no=Integer.parseInt(request.getParameter("re_no"));
			reservationPaySvc=new ReservationPaySvc();
			paySuccess=reservationPaySvc.updatePay(no);
			if(paySuccess) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('결제 확인되었습니다.')");
				out.println("location.href='reservationAppList.re'");
				out.println("</script>");
			}else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('오류 발생.')");
				out.println("history.back();");
				out.println("</script>");
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
