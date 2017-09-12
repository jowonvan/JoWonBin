package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Forward;
import bean.Member;
import dao.GiveAndTakeDao;

public class MovingPage {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Forward fw;
	private String msg;
	HttpSession ss;
	public MovingPage(HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
		ss=request.getSession();
	}
	public Forward execute(int code) {
		switch(code){
		case 1:
			takeInsertMoving(); break;
		case 2:
			takeUpdatetMoving(); break;
		case 3:
			giveUpdateMoving(); break;
		case 4:
			myInfoMoving(); break;
		case 5:
			reviewMoving(); break;
		default:
			break;
		}
		return fw;
	}
	private void reviewMoving() {
		String code = request.getParameter("code");
		String title = request.getParameter("title");
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String when = request.getParameter("when");
		String feeling = request.getParameter("feeling");
		//String place = request.getParameter("place");
		String filename1 = request.getParameter("filename1");
		String filename2 = request.getParameter("filename2");
		String filename3 = request.getParameter("filename3");
		
		request.setAttribute("code", code);
		request.setAttribute("title", title);
		request.setAttribute("name", name);
		request.setAttribute("content", content);
		request.setAttribute("when", when);
		//request.setAttribute("place", place);
		request.setAttribute("feeling", feeling);
		request.setAttribute("filename1", filename1);
		request.setAttribute("filename2", filename2);
		request.setAttribute("filename3", filename3);

		fw=new Forward();
		fw.setRedirect(false);
		fw.setPath("reviewChg.jsp");
		
	}
	private void myInfoMoving() {
		String id=ss.getAttribute("id").toString();
		String kind=request.getParameter("kind");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String loc=request.getParameter("loc");
		String gender=request.getParameter("gender");
		String birth=request.getParameter("birth");
		String officename=request.getParameter("officename");
		request.setAttribute("id", id);
		request.setAttribute("name", name);
		request.setAttribute("email", email);
		request.setAttribute("loc", loc);
		request.setAttribute("gender", gender);
		request.setAttribute("birth", birth);
		request.setAttribute("officename", officename);
		System.out.println("생일"+birth);
		fw=new Forward();
		fw.setRedirect(false);
		if(kind.equals("개인")){
			fw.setPath("myUpdateIndi.jsp");
		}else if(kind.equals("단체")){
			fw.setPath("myUpdateGroup.jsp");
		}
	}
	
	private void giveUpdateMoving() {
		String g_mid=request.getParameter("id");
		String id=ss.getAttribute("id").toString();
		System.out.println(g_mid);
		System.out.println(id);
		String code=request.getParameter("code");
		String title=request.getParameter("title");
		fw=new Forward();
		fw.setRedirect(false);
		if(id.equals(g_mid)){
			request.setAttribute("code", code);
			request.setAttribute("title", title);
			fw.setPath("giveUpdate.jsp");
		}else{
			String msg="수정할 권한이 없습니다.";
			request.setAttribute("msg", msg);
			fw.setPath("/giveDetail?code="+code);
		}
		
	}
	private void takeUpdatetMoving() {
		String code = request.getParameter("t_code");
		String title = request.getParameter("t_title");
		String kind = request.getParameter("t_kind");
		String mid = request.getParameter("t_mid");
		String id = ss.getAttribute("id").toString();
		if(id.equals(mid)){
			request.setAttribute("t_title", title);
			request.setAttribute("t_code", code);
			request.setAttribute("t_kind", kind);
			fw=new Forward();
			fw.setRedirect(false);
			if(kind.equals("단체")){
				fw.setPath("tgUpdate.jsp");
			}
			else if(kind.equals("개인")){
				fw.setPath("tiUpdate.jsp");
			}
		}else{
			msg = "작성자만 수정할 수 있습니다.";
			request.setAttribute("msg", msg);
			fw=new Forward();
			fw.setRedirect(false);
			fw.setPath("/tgDetail");
		}
	}
	private void takeInsertMoving() {
		String kind = request.getParameter("gpkind");
	      String id = ss.getAttribute("id").toString();
	      System.out.println(kind);
	      request.setAttribute("kind", kind);
	      GiveAndTakeDao gtDao = new GiveAndTakeDao();
	      String m_kind = gtDao.selectKind(id);
	      fw=new Forward();
	      fw.setRedirect(false);
	      
	      if(kind.equals("단체")){
	    	  if(m_kind.equals(kind)){
	    		  fw.setPath("tgAddPage.jsp");
	    	  }else{
	    		  request.setAttribute("msg", "단체회원만 작성할 수 있습니다.");
	    		  fw.setPath("/toDetailG");
	    	  }
	      }else if(kind.equals("개인")){
	    	  if(m_kind.equals(kind)){
	    		  fw.setPath("tiAddPage.jsp");
	    	  }else{
	    		  request.setAttribute("msg", "일반회원만 작성할 수 있습니다.");
	    		  fw.setPath("/toDetailP");
	    	  }
	    	  
	      }
	}
}//class End
