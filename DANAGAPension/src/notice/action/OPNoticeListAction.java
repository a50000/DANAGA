package notice.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import notice.svc.NoticeListSvc;
import notice.svc.OPNoticeListSvc;
import vo.ActionForward;
import vo.NoticeBean;
import vo.PageInfo;

public class OPNoticeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		OPNoticeListSvc OPnoticeListSvc = null;
		PageInfo pageInfo=null;
		HttpSession session=request.getSession();
		int page=1;
		int limit=10;
		int limitPage=10;
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		try {
			if (session.getAttribute("id").equals(null) || !session.getAttribute("grade").equals("S")) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('관리자페이지입니다.')");
				out.println("location.href='index.jsp'");
				out.println("</script>");
			} else {
				ArrayList<NoticeBean> notice = new ArrayList<NoticeBean>();
				forward = new ActionForward();
				OPnoticeListSvc = new OPNoticeListSvc();
				int listCount=NoticeListSvc.getListCount();
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
				notice = OPnoticeListSvc.getNoticeList(page, limit);
				request.setAttribute("notice", notice);
				request.setAttribute("pageInfo", pageInfo);
				forward.setPath("/notice/OPnoticeList.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
