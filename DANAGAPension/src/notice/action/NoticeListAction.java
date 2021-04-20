package notice.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import notice.svc.NoticeListSvc;
import vo.ActionForward;
import vo.NoticeBean;
import vo.PageInfo;

public class NoticeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int page=1;
		int limit=10;
		int limitPage=10;
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		PageInfo pageInfo=null;
		ActionForward forward=null;
		NoticeListSvc noticeListSvc=null;
		try {			
			noticeListSvc=new NoticeListSvc();
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
			ArrayList<NoticeBean> notice=new ArrayList<NoticeBean>();
			notice=noticeListSvc.getNoticeList(page, limit);
			forward=new ActionForward();
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("notice", notice);
			forward.setPath("/notice/noticeList.jsp");			
		}catch(Exception e) {
			e.printStackTrace();
		}return forward;
	}

}
