package room.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import room.svc.RoomListSvc;
import vo.ActionForward;
import vo.RoomBean;

public class RoomListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		RoomListSvc roomListSvc=null;
		ArrayList<RoomBean> list=null;
		try {
		roomListSvc=new RoomListSvc();
		list=roomListSvc.getRoomList();
		request.setAttribute("list", list);
		forward=new ActionForward("/room/roomList.jsp",false);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
