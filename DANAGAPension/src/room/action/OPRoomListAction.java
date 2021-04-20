package room.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import room.svc.RoomListSvc;
import vo.ActionForward;
import vo.RoomBean;

public class OPRoomListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		RoomListSvc roomListSvc = null;
		ArrayList<RoomBean> list = null;
		HttpSession session=request.getSession();
		try {
			if (session.getAttribute("id").equals(null) || !session.getAttribute("grade").equals("S")) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('관리자페이지입니다.!')");
				out.println("location.href='index.jsp'");
				out.println("</script>");
			} else {
				roomListSvc = new RoomListSvc();
				list = roomListSvc.getRoomList();
				request.setAttribute("list", list);
				forward = new ActionForward("/room/op_roomList.jsp", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
