package member.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.svc.IdCheckProSvc;
import vo.ActionForward;

public class IdCheckProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		IdCheckProSvc idCheckProSvc=null;
		boolean isExistID=false;
		try {
			idCheckProSvc=new IdCheckProSvc();
			isExistID=idCheckProSvc.getID(request.getParameter("id"));
			forward=new ActionForward();
			if(isExistID==false) {
				forward.setPath("idCheck.log?id="+request.getParameter("id")+"&useable=yes");
			}else {
				forward.setPath("idCheck.log?id="+request.getParameter("id")+"&useable=no");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
