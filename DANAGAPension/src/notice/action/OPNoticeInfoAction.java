package notice.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import notice.svc.OPNoticeInfoSvc;
import vo.ActionForward;
import vo.NoticeBean;

public class OPNoticeInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		OPNoticeInfoSvc oPNoticeInfoSvc = null;
		NoticeBean notice = null;
		HttpSession session=request.getSession();
		int no = Integer.parseInt(request.getParameter("board_no"));
		try {
			if (session.getAttribute("id").equals(null) || !session.getAttribute("grade").equals("S")) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('관리자페이지입니다.!')");
				out.println("location.href='index.jsp'");
				out.println("</script>");
			} else {
				notice = new NoticeBean();
				oPNoticeInfoSvc = new OPNoticeInfoSvc();
				notice = OPNoticeInfoSvc.getNoticeInfo(no);
				request.setAttribute("notice", notice);
				forward = new ActionForward("/notice/OPnoticeInfo.jsp", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
