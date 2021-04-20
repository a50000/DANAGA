package member.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.MemberListSvc;
import notice.svc.NoticeListSvc;
import vo.ActionForward;
import vo.MemberBean;
import vo.PageInfo;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		MemberListSvc memberListSvc = null;
		ArrayList<MemberBean> memberlist = null;
		int page=1;
		int limit=10;
		int limitPage=10;
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		if(request.getParameter("limit")!=null) {
			limit=Integer.parseInt(request.getParameter("limit"));
		}
		PageInfo pageInfo=null;
		try {
			if (session.getAttribute("id").equals(null) || !session.getAttribute("grade").equals("S")) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('관리자페이지입니다.')");
				out.println("location.href='index.jsp'");
				out.println("</script>");
			} else {
				memberListSvc = new MemberListSvc();
				int listCount=MemberListSvc.getListCount();
				int maxPage=(listCount%limit==0?(listCount/limit):(listCount/limit)+1);
				int startPage=(((int) ((double)page/limitPage+0.9))-1)*limitPage+1;
				int endPage=limitPage+startPage-1;
				if(endPage>maxPage) {
					endPage=maxPage;
				}
				pageInfo=new PageInfo();
				pageInfo.setPage(page);
				pageInfo.setListCount(listCount);
				pageInfo.setMaxPage(maxPage);
				pageInfo.setStartPage(startPage);
				pageInfo.setEndPage(endPage);
				memberlist = memberListSvc.getMemberList(page,limit);

				request.setAttribute("memberList", memberlist);
				request.setAttribute("pageInfo", pageInfo);
				request.setAttribute("limit", limit);
				forward = new ActionForward();
				forward.setPath("/member/memberList.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
