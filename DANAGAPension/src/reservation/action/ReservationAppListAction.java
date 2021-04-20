package reservation.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vo.ReservationBean;
import action.Action;
import reservation.svc.ReservationAppListSvc;
import vo.ActionForward;
import vo.PageInfo;

public class ReservationAppListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		ArrayList<ReservationBean> reservationlist = null;
		ReservationAppListSvc reservationComfSvc = null;
		int page=1;
		int limit=10;
		int limitPage=10;
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		PageInfo pageInfo=null;
		String type=null;
		String search=null;
		if(request.getParameter("type")!=null) {
			type=(String)request.getParameter("type").trim();
			request.setAttribute("type", type);
		}if(request.getParameter("search")!=null) {
			search=(String)request.getParameter("search").trim();
			request.setAttribute("search", search);
		}
		
		try {			
			if(session.getAttribute("id").equals(null) || !session.getAttribute("grade").equals("S")) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('관리자 페이지입니다.'");
				out.println("location.href='index.jsp'");
				out.println("</script>");
			} else {
				reservationComfSvc = new ReservationAppListSvc();
				reservationComfSvc.updateReservation();
				int listCount=ReservationAppListSvc.getListCount();
				if(type!=null&&type!=""&&search!=""&&search!=null) {
					listCount=ReservationAppListSvc.getListCount1(type,search);
				}
				int maxPage=(listCount%limit==0?(listCount/limit):(listCount/limit)+1);
				int startPage=(((int) ((double)page/limitPage+0.9))-1)*limitPage+1;
				int endPage=limitPage+startPage-1;
				if(endPage>maxPage) {
					endPage=maxPage;
				}
				pageInfo=new PageInfo();
				pageInfo.setPage(page);
				pageInfo.setListCount(listCount);
				pageInfo.setMaxPage(maxPage);
				pageInfo.setStartPage(startPage);
				pageInfo.setEndPage(endPage);
				if(type!=null&&type!=""&&search!=""&&search!=null) {
					reservationlist = reservationComfSvc.getReservationList(page,limit,type,search);
				}else {
					reservationlist = reservationComfSvc.getReservationList(page,limit);
				}
				request.setAttribute("reservationlist", reservationlist);
				request.setAttribute("pageInfo", pageInfo);
				forward = new ActionForward();
				forward.setPath("/reservation/reservationAppList.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
