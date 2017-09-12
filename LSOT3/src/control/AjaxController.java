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

@WebServlet({"/AjaxIndi", "/AjaxGroup", "/header", "/AjaxFind", "/AjaxLogin","/AjaxMyPage","/AjaxReview","/AjaxGive",
	"/AjaxIndiT","/AjaxGroupT", "/AjaxWriteG", "/AjaxWriteP"})
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri=request.getRequestURI();
		String conPath=request.getContextPath();
		String url=uri.substring(conPath.length());
		Forward fw=new Forward();
		switch(url){
		case "/header":
			HttpSession ss = request.getSession();
			String id = (String) ss.getAttribute("id");
			if(id==null || id.equals("")){
				ss.setAttribute("login", 0);
			}else{
				ss.setAttribute("login", 1);
			}
			fw.setRedirect(false);
			fw.setPath("header.jsp");
			break;
		case "/AjaxLogin":
			fw.setPath("login.jsp");
			break;
		case "/AjaxIndi":
			fw.setPath("joinIndi.jsp");
			break;
		case "/AjaxGroup":
			fw.setPath("joinGroup.jsp");
			break;
		case "/AjaxFind":
			fw.setPath("findPage.jsp");
			break;
		case "/AjaxMyPage":
			fw.setPath("mypage.jsp");
			break;
		case "/AjaxReview":
			fw.setPath("/reviewMain");
			break;
		case "/AjaxGive":
			fw.setPath("/giveMain");
			break;
		case "/AjaxIndiT":
			fw.setPath("/toDetailP");
			break;
		case "/AjaxGroupT":
			fw.setPath("/toDetailG");
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
	}
}
