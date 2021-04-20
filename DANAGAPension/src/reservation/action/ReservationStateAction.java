package reservation.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import reservation.svc.reservationStateSvc;
import vo.ActionForward;
import vo.Room_StatBean;

public class ReservationStateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		reservationStateSvc reservationListSvc=null;
		int[] room=null;
		ArrayList<Room_StatBean> stat=null;
		try {
			forward=new ActionForward();
			reservationListSvc=new reservationStateSvc();
			room=reservationListSvc.getRoomList();
			stat=reservationListSvc.getStatList();
			request.setAttribute("list", room);
			request.setAttribute("stat", stat);
			forward.setPath("/reservation/reservationState.jsp");	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
