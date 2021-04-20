package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.svc.JoinProSvc;
import vo.ActionForward;
import vo.MemberBean;

public class JoinProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		MemberBean member = null;
		JoinProSvc joinProSvc = null;
		try {
			member = new MemberBean();
			member.setId(request.getParameter("id"));
			member.setName(request.getParameter("name"));
			member.setPasswd(request.getParameter("pass"));
			member.setEmail(request.getParameter("email")+request.getParameter("email1"));
			member.setPh_no(request.getParameter("ph_no"));
			joinProSvc=new JoinProSvc();
			boolean isJoinSuccess = joinProSvc.joinMember(member);
			if (isJoinSuccess) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('환영합니다. 가입한 아이디로 로그인해주세요.');");
				out.println("location.href='loginForm.log'");
				out.println("</script>");
			} else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('회원등록실패');");
				out.println("history.back();");
				out.println("</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
