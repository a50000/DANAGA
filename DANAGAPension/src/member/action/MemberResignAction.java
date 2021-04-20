package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import action.Action;
import member.svc.MemberResignSvc;
import vo.ActionForward;

public class MemberResignAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberResignSvc memberResignSvc=null;
		boolean isDelSuccess=false;
		try {
			memberResignSvc=new MemberResignSvc();
			isDelSuccess=memberResignSvc.resignMember(request.getParameter("id"));
			if(isDelSuccess) {
				HttpSession session=request.getSession();
				session.invalidate();
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제되었습니다.')");
				out.println("location.href='index.jsp'");
				out.println("</script>");
			}else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 실패')");
				out.println("history.back();");
				out.println("</script>");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
