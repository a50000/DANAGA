package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.action.IdCheckProAction;
import member.action.JoinProAction;
import member.action.LoginAction;
import member.action.MemberAlterFormAction;
import member.action.MemberAlterProAction;
import member.action.MemberDelAction;
import member.action.OPMemberInfoAction;
import member.action.MemberListAction;
import member.action.MemberModFormAction;
import member.action.MemberModProAction;
import member.action.MemberResignAction;
import member.action.MemberInfoAction;
import notice.action.NoticeDeleteAction;
import notice.action.NoticeInfoAction;
import notice.action.NoticeListAction;
import notice.action.NoticeWriteProAction;
import notice.action.OPNoticeInfoAction;
import notice.action.OPNoticeListAction;
import reservation.action.ReservationAppAction;
import reservation.action.ReservationAppListAction;
import reservation.action.ReservationCheckAction;
import reservation.action.ReservationDeleteAppAction;
import reservation.action.ReservationInfoAction;
import reservation.action.ReservationPayAction;
import reservation.action.ReservationStateAction;
import reservation.action.ReservationProAction;
import room.action.OPRoomListAction;
import room.action.RoomInfoAction;
import room.action.RoomListAction;
import room.action.RoomModAction;
import room.action.RoomModProAction;
import vo.ActionForward;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = { "*.log", "*.me", "*.no", "*.re", "*.ro" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String RequsetURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequsetURI.substring(contextPath.length());
		System.out.println(command);
		ActionForward forward = null;
		Action action = null;
		// 방
		if (command.equals("/roomList.ro")) {//방 목록
			action = new RoomListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/OProomList.ro")) {//관리자 방 목록
			action = new OPRoomListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/roomMod.ro")) {//관리자 방 수정 폼
			action = new RoomModAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/roomModPro.ro")) {//관리자 방 수정 처리
			action = new RoomModProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/roomInfo.ro")) {// 방 사진 불러오기
	          
            action = new RoomInfoAction();
            try {
               forward = action.execute(request, response);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
		// 공지사항
		else if (command.equals("/noticeList.no")) {//공지사항 목록
			action = new NoticeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/OPnoticeList.no")) {//관리자 공지사항 목록
			action = new OPNoticeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/noticeWrite.no")) {//공지사항 작성 폼
			forward = new ActionForward();
			forward.setPath("/notice/noticeWrite.jsp");
		} else if (command.equals("/noticeWritePro.no")) {//공지사항 작성 처리
			action = new NoticeWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/OPnoticeInfo.no")) {//관리자 공지사항 상세보기
			action = new OPNoticeInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/noticeInfo.no")) {//공지사항 상세보기
			action = new NoticeInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/noticeDelete.no")) {//관리자 공지사항 삭제
			action = new NoticeDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 예약
		else if (command.equals("/reservationState.re")) {//예약 목록
			action = new ReservationStateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/reservationForm.re")) {//예약 폼
			HttpSession session = request.getSession();
			if (session.getAttribute("id") == null) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('로그인 후 이용가능한 페이지입니다.')");
				out.println("location.href='loginForm.log'");
				out.println("</script>");
			} else {
				forward = new ActionForward();
				forward.setPath("/reservation/reservationForm.jsp");
			}
		} else if (command.equals("/reservationProcess.re")) {//예약처리
			action = new ReservationProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/reservationInfo.re")) {
			action = new ReservationInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/reservationApp.re")) {
	         action = new ReservationAppAction();
	         try {
	            forward = action.execute(request, response);
	         }catch(Exception e){
	            e.printStackTrace();
	         }
	      }else if(command.equals("/reservationAppList.re")) {
	          action = new ReservationAppListAction();
	          try {
	             forward = action.execute(request, response);
	          }catch(Exception e){
	             e.printStackTrace();
	          }
	       }
	      else if(command.equals("/reservationDeleteApp.re")) {
	          action = new ReservationDeleteAppAction();
	          try {
	             forward = action.execute(request, response);
	          }catch(Exception e){
	             e.printStackTrace();
	          }
	       }
	      else if(command.equals("/reservationCheck.re")) {
	          action = new ReservationCheckAction();
	          try {
	             forward = action.execute(request, response);
	          }catch(Exception e){
	             e.printStackTrace();
	          }
	       }
	      else if(command.equals("/reservationPay.re")) {
	          action = new ReservationPayAction();
	          try {
	             forward = action.execute(request, response);
	          }catch(Exception e){
	             e.printStackTrace();
	          }
	       }
		// 회원관리
		else if (command.equals("/loginForm.log")) {
			forward = new ActionForward();
			forward.setPath("/member/loginForm.jsp");
		} else if (command.equals("/loginProcess.log")) {
			action = new LoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/idCheck.log")) {
			forward = new ActionForward();
			request.setAttribute("openInit", request.getParameter("openInit"));
			forward.setPath("/member/idCheck.jsp");
		} else if (command.equals("/idCheckPro.log")) {
			action = new IdCheckProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/logout.log")) {
			HttpSession session = request.getSession();
			session.invalidate();
			// session.removeAttribute("id");
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("index.jsp");
		} else if (command.equals("/welcome.log")) {
			forward = new ActionForward();
			forward.setPath("/welcome/welcome.jsp");
		}
		else if (command.equals("/joinForm.me")) {
			forward = new ActionForward();
			forward.setPath("/member/joinForm.jsp");
		} else if (command.equals("/joinProcess.me")) {
			action = new JoinProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/memberList.me")) {
			action = new MemberListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/OPmemberInfo.me")) {
			action = new OPMemberInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/memberInfo.me")) {
			action = new MemberInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/memberModForm.me")) {
			action = new MemberModFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/memberAlterForm.me")) {
			action = new MemberAlterFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/memberModPro.me")) {
			action = new MemberModProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/memberAlterPro.me")) {
			action = new MemberAlterProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/memberDel.me")) {
			action = new MemberDelAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/memberResign.me")) {
			action = new MemberResignAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

}
