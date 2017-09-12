package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Forward;
import bean.Member;
import dao.MemberDao;

public class MemberManager {

	private HttpServletRequest request;
	private HttpServletResponse response;
	Member mb = null;
	HttpSession ss = null;
	Forward fw=new Forward();
	String msg = null;
	String msglogin = null;

	public MemberManager(HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
		ss = request.getSession();
	}

	public Forward execute(int code) {
		switch(code){
		case 1:
			login();
			break;
		case 2:
			logout();
			break;
		case 3:
			indi();
			break;
		case 4:
			group();
			break;
		case 5:
			findId();
			break;
		case 6:
			findPw();
			break;
		case 7:
			update();
			break;
		default:
			break;
		}
		return fw;
	}

	private void update() {
		String id=ss.getAttribute("id").toString();
		mb = new Member();
		MemberDao mdao=new MemberDao();
		mb.setM_pw(request.getParameter("pw"));
		mb.setM_name(request.getParameter("name"));
		mb.setM_phone(request.getParameter("phoneValue")+"-"+request.getParameter("second")+"-"+request.getParameter("third"));
		mb.setM_email(request.getParameter("email"));
		mb.setM_loc(request.getParameter("num")+","+request.getParameter("doro"));
		mb.setM_gender(request.getParameter("gender"));
		mb.setM_birth(request.getParameter("birth"));
		mb.setM_pw_question(request.getParameter("pwQuestion"));
		mb.setM_pw_answer(request.getParameter("pwAnswer"));
		mb.setM_kind(request.getParameter("kind"));
		mb.setM_point(Integer.valueOf(request.getParameter("point")));
		
		fw=new Forward();
		fw.setRedirect(false);
		if(mdao.memberupdate(id,mb)){
			System.out.println("update 성공");
			request.setAttribute("msg", "수정되었습니다.");
			fw.setPath("/myInfo");
		}else{
			System.out.println("update 실패");
			request.setAttribute("msg", "수정 실패했습니다.");
			fw.setPath("/myInfo");
		}
		
	}

	private void login() {
		MemberDao mdao=new MemberDao();
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		String msglogin = null;
		int result=mdao.isMember(id, pw);
		if(result==-1){
			msglogin="unknownpw";
			fw.setPath("template.jsp");
		}if(result==1){
			msglogin="unknownid";
			fw.setPath("template.jsp");
		}if(result==0){
			ss.setAttribute("id", id);
			fw.setPath("/Index");
		}
		request.setAttribute("msglogin", msglogin);
		fw.setRedirect(false);
		mdao.close();
	}//login end 

	private void logout() {
		ss.invalidate();
		fw.setRedirect(true);
		fw.setPath("/Index");
	}
	
	private void indi() {
		mb = new Member();
		MemberDao mdao=new MemberDao();
		mb.setM_id(request.getParameter("id"));
		mb.setM_pw(request.getParameter("pw"));
		mb.setM_name(request.getParameter("name"));
		mb.setM_phone(request.getParameter("phoneValue")+"-"+request.getParameter("second")+"-"+request.getParameter("third"));
		mb.setM_email(request.getParameter("email"));
		mb.setM_loc(request.getParameter("num")+","+request.getParameter("doro"));
		mb.setM_gender(request.getParameter("gender"));
		mb.setM_birth(request.getParameter("birth"));
		mb.setM_pw_question(request.getParameter("pwQuestion"));
		mb.setM_pw_answer(request.getParameter("pwAnswer"));
		mb.setM_kind(request.getParameter("kind"));
		mb.setM_point(Integer.valueOf(request.getParameter("point")));
		System.out.println(request.getParameter("num"));
		System.out.println(request.getParameter("doro"));
		String msg = null;
		
		// 아이디 중복체크
		if (mdao.isId(mb.getM_id())) {
			// 아이디 존재하면 true
			msg = "idoverlap"; 
			fw.setPath("/AjaxIndi");
		} else if (mdao.isEmail(mb.getM_email())) {
			msg = "emailoverlap";
			fw.setPath("/AjaxIndi");
		} else{ 
			if (mdao.insertIndi(mb)) {
				request.setAttribute("msg", "success");
				fw.setPath("/Index");
			} else { // 회원가입 실패
				request.setAttribute("msg", "fail");
				fw.setPath("/AjaxIndi");
			}
		}
		request.setAttribute("msg", msg);
		mdao.close();
	}
	
	
	private void group() {
		mb = new Member();
		MemberDao mdao=new MemberDao();
		mb.setM_id(request.getParameter("id"));
		mb.setM_pw(request.getParameter("pw"));
		mb.setM_name(request.getParameter("name"));
		mb.setM_phone(request.getParameter("phoneValue")+"-"+request.getParameter("second")+"-"+request.getParameter("third"));
		mb.setM_office_name(request.getParameter("officeName"));
		mb.setM_office_tel(request.getParameter("telValue")+"-"+request.getParameter("telsecond")+"-"+request.getParameter("telthird"));
		mb.setM_email(request.getParameter("email"));
		mb.setM_gender(request.getParameter("gender"));
		mb.setM_birth(request.getParameter("birth"));
		mb.setM_pw_question(request.getParameter("pwQuestion"));
		mb.setM_pw_answer(request.getParameter("pwAnswer"));
		mb.setM_kind(request.getParameter("kind"));
		mb.setM_point(Integer.valueOf(request.getParameter("point")));
		mb.setM_loc(request.getParameter("num")+","+request.getParameter("doro"));
		
		// 아이디 중복체크
		if (mdao.isId(mb.getM_id())) {
			// 아이디 존재하면 true
			msg = "idoverlap";
			request.setAttribute("msg", msg);
			fw.setPath("/AjaxGroup");
		} else if (mdao.isEmail(mb.getM_email())) {
			msg = "emailoverlap";
			request.setAttribute("msg", msg);
			fw.setPath("/AjaxGroup");
		} else{ 
			if (mdao.insertGroup(mb)) {
				fw.setPath("/Index");
			} else { // 회원가입 실패
				request.setAttribute("msg", "fail");
				fw.setPath("/AjaxGroup");
			}
			request.setAttribute("msg", "success");
		}
		mdao.close();
	}
	

	private void findId() {
		MemberDao mdao=new MemberDao();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String yours = "해당하는 아이디가 없습니다.";
		
		if(mdao.isName(name) && mdao.isEmail(email)){
			yours = mdao.yourId(name, email);
		}
		request.setAttribute("yours", yours);
		fw.setPath("/AjaxFind");
		mdao.close();
	}
	
	private void findPw() {
		MemberDao mdao=new MemberDao();
		String id = request.getParameter("id");
		String pQ = request.getParameter("pwQuestion");
		String pA = request.getParameter("pwAnswer");
		String yours = "해당하는 비밀번호가 없습니다.";
		
		if(mdao.isId(id)){
			yours = mdao.checkQA(pQ, pA);
		}
		request.setAttribute("yours", yours);
		fw.setPath("/AjaxFind");
		mdao.close();
	}
}
