package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.MemberInfoSvc;
import vo.ActionForward;
import vo.MemberBean;

public class OPMemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		MemberInfoSvc memberInfoSvc = null;
		MemberBean member = null;
		try {
			if (session.getAttribute("id") == null || !session.getAttribute("grade").equals("S")) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('관리자페이지입니다.')");
				out.println("location.href='index.jsp'");
				out.println("</script>");
			} else {
				String id = request.getParameter("id");
				memberInfoSvc = new MemberInfoSvc();
				member = memberInfoSvc.getMember(id);
				if (member != null) {
					request.setAttribute("member", member);
					forward = new ActionForward();
					forward.setPath("/member/OPmemberInfo.jsp");
				} else {
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('회원정보가 없습니다.')");
					out.println("location.href=memberList.me;");
					out.println("</script>");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
