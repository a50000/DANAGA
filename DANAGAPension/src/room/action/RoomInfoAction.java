package room.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;

public class RoomInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		try {
			request.getParameter("r_no");
			System.out.println(request.getParameter("r_no"));
			forward = new ActionForward("room/"+request.getParameter("r_no")+".jsp", false);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
