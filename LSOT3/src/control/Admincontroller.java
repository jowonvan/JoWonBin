package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Forward;
import model.AdminM;
import model.AdminGT;


@WebServlet({"/home","/adminMember","/admyDetail","/adminBlackList","/blackDetail","/blackListAdd","/blackListClear",
	"/adminDelete","/adminGive","/adgiveDetail","/comment","/adminTi","/adtakeDetail","/adminTg","/adminNotice",
	"/noticeDetail","/Delete","/addNotice","/addNoticePage","/noticeUpdate","/pwMove","/pwUpdate","/adminReview",
	"/adreviewDetail","/adminlogin"})
public class Admincontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doStart(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doStart(request, response);
	}
	protected void doStart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri=request.getRequestURI();
		String conPath=request.getContextPath();
		String url=uri.substring(conPath.length());
		System.out.println("url="+url);
		Forward fw = null;
		AdminM am=null;
		AdminGT gt=null;

		switch(url){
		case "/home":
			fw=new Forward();
			fw.setRedirect(false);
			fw.setPath("adminTemplate.jsp");
			break;
		case "/adminMember":
			am=new AdminM(request,response);
			fw=am.execute(1);
			break;
		case "/admyDetail":
			am=new AdminM(request,response);
			fw=am.execute(1);
			break;
		case "/adminBlackList":
			am=new AdminM(request,response);
			fw=am.execute(2);
			break;
		case "/blackDetail":
			am=new AdminM(request,response);
			fw=am.execute(2);
			break;
		case "/blackListAdd":
			am=new AdminM(request,response);
			fw=am.execute(3);
			break;
		case "/blackListClear":
			am=new AdminM(request,response);
			fw=am.execute(4);
			break;
		case "/adminDelete":
			am=new AdminM(request,response);
			fw=am.execute(5);
			break;
		case "/adminGive":
			gt=new AdminGT(request,response);
			fw=gt.execute(1);
			break;
		case "/adgiveDetail":
			gt=new AdminGT(request,response);
			fw=gt.execute(2);
			break;
		case "/comment":
			gt=new AdminGT(request,response);
			fw=gt.execute(3);
			break;
		case "/adminTi":
			gt=new AdminGT(request,response);
			fw=gt.execute(4);
			break;
		case "/adtakeDetail":
			gt=new AdminGT(request,response);
			fw=gt.execute(5);
			break;
		case "/adminTg":
			gt=new AdminGT(request,response);
			fw=gt.execute(6);
			break;
		case "/adminNotice":
			gt=new AdminGT(request,response);
			fw=gt.execute(7);
			break;
		case "/noticeDetail":
			gt=new AdminGT(request,response);
			fw=gt.execute(8);
			break;
		case "/Delete":
			gt=new AdminGT(request,response);
			fw=gt.execute(9);
			break;
		case "/addNoticePage":
			gt=new AdminGT(request,response);
			fw=gt.execute(10);
			break;
		case "/noticeUpdate":
			gt=new AdminGT(request,response);
			fw=gt.execute(11);
			break;
		case "/addNotice":
			gt=new AdminGT(request,response);
			fw=gt.execute(12);
			break;
		case "/pwMove":
			fw=new Forward();
			fw.setRedirect(false);
			fw.setPath("pwUpdate.jsp");
			break;
		case "/pwUpdate":
			gt=new AdminGT(request,response);
			fw=gt.execute(13);
			break;
		case "/adminReview":
			gt=new AdminGT(request,response);
			fw=gt.execute(14);
			break;
		case "/adreviewDetail":
			gt=new AdminGT(request,response);
			fw=gt.execute(15);
			break;
		case "/adminlogin":
			gt=new AdminGT(request,response);
			fw=gt.execute(16);
			break;
			
			
		default:
			break;
		}

		if(fw!=null){
			if(fw.isRedirect()){
				response.sendRedirect(fw.getPath());
			}else{
				//포워딩
				RequestDispatcher dis = request.getRequestDispatcher(fw.getPath());
				dis.forward(request, response); 
			}
		}
	}//doStart End

}//class End
