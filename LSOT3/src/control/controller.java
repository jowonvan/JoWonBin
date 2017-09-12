package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Forward;
import model.GiveAndTake;
import model.GiveM;
import model.MemberManager;
import model.MovingPage;
import model.MypageM;
import model.ReviewM;

@WebServlet({"/Index","/login","/logout","/joinIndi","/joinGroup","/findId","/findPw","/review","/reviewMain",
	"/rvAdd","/insertReview","/reviewPage","/reviewDetail","/writeCmt","/reviewChangePage","/reviewChange",
	"/mypage","/myDetail","/myInfo","/requestDelete","/myRequest","/reviewcomment","/reviewDelete","/addsupport",
	"/giveMain","/giveAdd","/giveAddPage","/giveDetail","/givecomment","/givedelete","/givechange","/giveupdate",
	"/gcDelete","/rcDelete","/tgAdd", "/tgchange", "/toDetailG", "/tgDetail", "/tgAddPage", "/titleBtn", "/idBtn", 
	"/tgDelete", "/tgUpdate", "/requestList", "/requestAgree", "/requestrefusal", "/requestDo","/toDetailP", 
	"/tiAddPage","/table","/takeComment","/tcDelete","/gtitleBtn","/gidBtn","/rtitleBtn","/ridBtn","/myInfochange",
"/myUpdate","/requestsuccess"})
public class controller extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri=request.getRequestURI();
		String conPath=request.getContextPath();
		String url=uri.substring(conPath.length());
		System.out.println("url="+url);

		MemberManager mm;
		ReviewM rm =null;
		Forward fw=null;
		MypageM mp= null;
		GiveM gm=null;
		GiveAndTake gt=null;
		MovingPage move = null;
		HttpSession ss=request.getSession();

		//===========================================로그인 로그아웃
		if(url.equals("/Index")){
			fw=new Forward();
			fw.setPath("template.jsp");
		}else if(url.equals("/login")){
			mm = new MemberManager(request, response);
			fw = mm.execute(1);
		}else if(url.equals("/logout")){
			mm = new MemberManager(request, response);
			fw = mm.execute(2);

		}else if(url.equals("/joinIndi")){
			mm = new MemberManager(request, response);
			fw = mm.execute(3);

		}else if(url.equals("/joinGroup")){
			mm = new MemberManager(request, response);
			fw = mm.execute(4);

		}else if(url.equals("/findId")){
			mm = new MemberManager(request, response);
			fw = mm.execute(5);
		}else if(url.equals("/findPw")){
			mm = new MemberManager(request, response);
			fw = mm.execute(6);

		}else if(url.equals("/myInfochange")){
			move=new MovingPage(request, response);
			fw=move.execute(4);
		}else if(url.equals("/myUpdate")){
			mm = new MemberManager(request, response);
			fw = mm.execute(7);
		}

		//===========================================리뷰
		else if(url.equals("/reviewMain")){
			rm=new ReviewM(request, response);
			fw=rm.execute(1);
		}else if(url.equals("/rvAdd")){
			fw=new Forward();
			fw.setRedirect(false);
			fw.setPath("rvAddPage.jsp");
		} else if(url.equals("/reviewChangePage")){
			move=new MovingPage(request, response);
			fw=move.execute(5);
		} else if(url.equals("/insertReview")){
			rm=new ReviewM(request, response);
			fw=rm.execute(2);
		}else if(url.equals("/reviewPage")){
			rm=new ReviewM(request, response);
			fw=rm.execute(3);
		}else if(url.equals("/reviewChange")){
			rm=new ReviewM(request, response);
			fw=rm.execute(4);
		}else if(url.equals("/reviewcomment")){
			rm=new ReviewM(request, response);
			fw=rm.execute(5);
		}else if(url.equals("/reviewDelete")){
			rm=new ReviewM(request, response);
			fw=rm.execute(6);
		}else if(url.equals("/rcDelete")){
			rm=new ReviewM(request, response);
			fw=rm.execute(7);
		}else if(url.equals("/table")){
			rm=new ReviewM(request, response);
			fw=rm.execute(8);
		}else if(url.equals("/rtitleBtn")){
			rm=new ReviewM(request, response);
			fw=rm.execute(9);
		}else if(url.equals("/ridBtn")){
			rm=new ReviewM(request, response);
			fw=rm.execute(10);
		}

		//========================================마이페이지
		else if(url.equals("/myDetail")){
			mp=new MypageM(request,response);
			fw=mp.execute(1);
		}else if(url.equals("/myInfo")){
			mp=new MypageM(request,response);
			fw=mp.execute(2);
		}else if(url.equals("/requestDelete")){
			mp=new MypageM(request,response);
			fw=mp.execute(3);
		}else if(url.equals("/myRequest")){
			mp=new MypageM(request,response);
			fw=mp.execute(4);
		}else if(url.equals("/addsupport")){
			mp=new MypageM(request,response);
			fw=mp.execute(5);
		}else if(url.equals("/addsupport")){
			mp=new MypageM(request,response);
			fw=mp.execute(5);
		}

		//========================================give
		else if(url.equals("/giveMain")){
			gm=new GiveM(request,response);
			fw=gm.execute(1);
		}else if(url.equals("/giveAdd")){
			fw=new Forward();
			fw.setRedirect(false);
			if(ss.getAttribute("id")==null){
				request.setAttribute("msg", "로그인 후 이용 가능합니다.");
				fw.setPath("/giveMain");
			}else{
				fw.setPath("giveAddPage.jsp");
			}
		}else if(url.equals("/giveAddPage")){
			gm=new GiveM(request,response);
			fw=gm.execute(2);
		}else if(url.equals("/giveDetail")){
			gm=new GiveM(request,response);
			fw=gm.execute(3);
		}else if(url.equals("/givecomment")){
			gm=new GiveM(request,response);
			fw=gm.execute(4);
		}else if(url.equals("/givedelete")){
			gm=new GiveM(request,response);
			fw=gm.execute(5);
		}else if(url.equals("/givechange")){
			move=new MovingPage(request, response);
			fw=move.execute(3);
		}else if(url.equals("/giveupdate")){
			gm=new GiveM(request,response);
			fw=gm.execute(6);
		}else if(url.equals("/gcDelete")){
			gm=new GiveM(request,response);
			fw=gm.execute(7);
		}else if(url.equals("/gtitleBtn")){
			gm=new GiveM(request,response);
			fw=gm.execute(8);
		}else if(url.equals("/gidBtn")){
			gm=new GiveM(request,response);
			fw=gm.execute(9);
		}

		//===============================================take
		else if(url.equals("/tgAdd")){
			move=new MovingPage(request, response);
			fw=move.execute(1);
		}else if(url.equals("/tgchange")){
			move=new MovingPage(request, response);
			fw=move.execute(2);
		}else if(url.equals("/toDetailG")){
			gt = new GiveAndTake(request, response);
			fw = gt.execute(1);
		}else if(url.equals("/toDetailP")){
			gt = new GiveAndTake(request, response);
			fw = gt.execute(2);
		}else if(url.equals("/tgDetail")){
			gt = new GiveAndTake(request, response);
			fw = gt.execute(3);
		}else if(url.equals("/tgAddPage")){
			gt = new GiveAndTake(request, response);
			fw = gt.execute(4);
		}else if(url.equals("/titleBtn")){
			gt = new GiveAndTake(request, response);
			fw = gt.execute(5);
		}else if(url.equals("/idBtn")){
			gt = new GiveAndTake(request, response);
			fw = gt.execute(6);
		}else if(url.equals("/tgDelete")){
			gt = new GiveAndTake(request, response);
			fw = gt.execute(7);
		}else if(url.equals("/tgUpdate")){
			gt = new GiveAndTake(request, response);
			fw = gt.execute(8);
		}else if(url.equals("/requestList")){
			gt = new GiveAndTake(request, response);
			fw = gt.execute(9);
		}else if(url.equals("/requestAgree")){
			gt = new GiveAndTake(request, response);
			fw = gt.execute(10);
		}else if(url.equals("/requestrefusal")){
			gt = new GiveAndTake(request, response);
			fw = gt.execute(11);
		}else if(url.equals("/requestDo")){
			gt = new GiveAndTake(request, response);
			fw = gt.execute(12);
		}else if(url.equals("/tiAddPage")){
			gt = new GiveAndTake(request, response);
			fw = gt.execute(13);
		}else if(url.equals("/takeComment")){
			gt = new GiveAndTake(request, response);
			fw = gt.execute(14);
		}else if(url.equals("/tcDelete")){
			gt = new GiveAndTake(request, response);
			fw = gt.execute(15);
		}else if(url.equals("/requestsuccess")){
			gt = new GiveAndTake(request, response);
			fw = gt.execute(16);
		}

		if(fw!=null){
			if(fw.isRedirect()){
				System.out.println("path="+fw.getPath());
				response.sendRedirect(fw.getPath());
			}else{
				System.out.println("path="+fw.getPath());
				RequestDispatcher dis=
						request.getRequestDispatcher(fw.getPath());
				dis.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
}
