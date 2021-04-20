package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.MemberInfoSvc;
import vo.ActionForward;
import vo.MemberBean;

public class MemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		MemberInfoSvc memberInfoSvc = null;
		MemberBean member = null;
		try {
			if (session.getAttribute("id") == null || !session.getAttribute("grade").equals("M")) {
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('회원페이지입니다.')");
					out.println("location.href='loginForm.log'");
					out.println("</script>");
			} else {
				memberInfoSvc = new MemberInfoSvc();
				member = memberInfoSvc.getMember((String) session.getAttribute("id"));

				if (member != null) {
					request.setAttribute("member", member);
					forward = new ActionForward();
					forward.setPath("/member/memberInfo.jsp");
				} else {
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('회원정보가 없습니다.')");
					out.println("history.back()");
					out.println("</script>");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
