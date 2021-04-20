package notice.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import notice.svc.OPNoticeInfoSvc;
import notice.svc.OPNoticeListSvc;
import vo.ActionForward;
import vo.NoticeBean;

public class NoticeInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		OPNoticeInfoSvc oPNoticeInfoSvc=null;
		NoticeBean notice=null;
		int no=Integer.parseInt(request.getParameter("board_no"));
		try {			
			notice=new NoticeBean();
			oPNoticeInfoSvc=new OPNoticeInfoSvc();
			notice=OPNoticeInfoSvc.getNoticeInfo(no);
			request.setAttribute("notice", notice);
			forward=new ActionForward("/notice/noticeInfo.jsp",false);			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return forward;

}
}
