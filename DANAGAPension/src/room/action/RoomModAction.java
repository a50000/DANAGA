package room.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import room.svc.RoomListSvc;
import room.svc.RoomModSvc;
import vo.ActionForward;
import vo.RoomBean;

public class RoomModAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		RoomModSvc roomModSvc=null;
		ArrayList<RoomBean> info=null;
		String no=request.getParameter("no");
		try {
		roomModSvc=new RoomModSvc();
		info=roomModSvc.getRoomInfo(no);
		System.out.println();
		request.setAttribute("Info", info);
		forward=new ActionForward("/room/roomMod.jsp",false);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
