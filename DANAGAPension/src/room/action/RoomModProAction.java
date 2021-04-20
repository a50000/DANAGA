package room.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import room.svc.RoomModProSvc;
import room.svc.RoomModSvc;
import vo.ActionForward;
import vo.RoomBean;

public class RoomModProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward= null;
		RoomModProSvc roomModProSvc=null;
		try {
			RoomBean room=new RoomBean();
			String no=request.getParameter("no1");
			room.setNo(request.getParameter("no"));
			room.setType(request.getParameter("type"));
			room.setSize(request.getParameter("size"));
			room.setBedroom(request.getParameter("bedroom"));
			room.setMen(request.getParameter("men"));
			room.setContent(request.getParameter("content"));
			roomModProSvc=new RoomModProSvc();
			boolean isModSuccess=roomModProSvc.modRoom(no,room);
			if(isModSuccess) {
					forward=new ActionForward();
					forward.setRedirect(true);
					forward.setPath("OProomList.ro");
			}else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('수정 실패.')");
				out.println("history.back();");
				out.println("</scrip>");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return forward;
	}

}