package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.MemberModProSvc;
import vo.ActionForward;
import vo.MemberBean;

public class MemberAlterProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		MemberBean member = null;
		MemberModProSvc memberModProSvc = null;
		boolean isModifySuccess =false;
		if(session.getAttribute("id")==null) {
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원페이지입니다.')");
			out.println("location.href='loginForm.log'");
			out.println("</script>");
		} else {
			member = new MemberBean();
			memberModProSvc = new MemberModProSvc();
			member.setId(request.getParameter("id"));
			member.setName(request.getParameter("name"));
			member.setPasswd(request.getParameter("pass"));
			member.setEmail(request.getParameter("email"));
			member.setPh_no(request.getParameter("ph_no"));
			member.setGrade(request.getParameter("grade"));
			isModifySuccess = memberModProSvc.modifyMember(member);
			
			if(isModifySuccess) {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("memberInfo.me");
			}else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정실패')");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		return forward;
	}

}
