package notice.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import notice.svc.NoticeWriteProSvc;
import vo.ActionForward;
import vo.NoticeBean;

public class NoticeWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		NoticeWriteProSvc noticeWriteProSvc=null;
		NoticeBean notice=null;
		try {
			notice=new NoticeBean();
			notice.setBoard_subject(request.getParameter("board_subject"));
			notice.setBoard_content(request.getParameter("board_content").replace("\r\n", "<br>"));
			noticeWriteProSvc= new NoticeWriteProSvc();
			boolean isWriteSuccess=noticeWriteProSvc.noticeWrite(notice);
			
			if(isWriteSuccess) {
				forward=new ActionForward();
				forward.setRedirect(true);
				forward.setPath("OPnoticeList.no");
			}else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('등록실패')");
				out.println("history.back();");
				out.println("</script>");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
