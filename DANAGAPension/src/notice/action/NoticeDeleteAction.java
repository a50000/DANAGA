package notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import notice.svc.NoticeDeleteSvc;
import vo.ActionForward;

public class NoticeDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		int no=Integer.parseInt(request.getParameter("board_no"));
		NoticeDeleteSvc noticeDeleteSvc=null;
		try {
			noticeDeleteSvc=new NoticeDeleteSvc();
			noticeDeleteSvc.deleteNotice(no);
			forward=new ActionForward();
			forward.setPath("OPnoticeList.no");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
