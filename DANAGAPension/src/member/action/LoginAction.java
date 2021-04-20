package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.LoginSvc;
import vo.ActionForward;
import vo.MemberBean;

public class LoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		LoginSvc loginSvc = null;
		MemberBean member = null;
		try {
			String id = request.getParameter("id");
			String pass = request.getParameter("pass");
			loginSvc = new LoginSvc();
			member = loginSvc.getMember(id);
			if (member != null) {
				if (member.getPasswd().equals(pass)) {
					HttpSession session = request.getSession();
					session.setAttribute("id", member.getId());
					session.setAttribute("grade", member.getGrade());
					session.setAttribute("name", member.getName());
					forward = new ActionForward();
					forward.setPath("index.jsp");
				}else {
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('계정이 없거나 비밀번호가 일치하지 않습니다.')");
					out.println("history.back()");
					out.println("</script>");
				}
			} else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('계정이 없거나 비밀번호가 일치하지 않습니다.')");
				out.println("history.back()");
				out.println("</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;

	}

}
