package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.MemberDelSvc;
import vo.ActionForward;

public class MemberDelAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		MemberDelSvc memberDelSvc = null;
		boolean isDeleteSuccess = false;
		System.out.println(request.getParameter("grade"));
		if(request.getParameter("grade").equals("S")) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자 계정은 삭제할 수 없습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			String id = request.getParameter("id");
			memberDelSvc = new MemberDelSvc();
			 isDeleteSuccess = memberDelSvc.deleteMember(id);
			if(isDeleteSuccess) {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("memberList.me");
			}else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 실패')");
				out.println("history.back()");
				out.println("</script>");
			}
		}
		
		return forward;
	}

}
