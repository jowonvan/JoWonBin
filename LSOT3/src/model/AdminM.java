package model;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Forward;
import bean.Give;
import bean.Member;
import bean.Request;
import bean.Review;
import bean.Support;
import bean.Take;
import dao.AdminGTDao;
import dao.AdminMDao;
import bean.Age;
import bean.Comment;

public class AdminM {
	private HttpServletRequest request;
	private HttpServletResponse response;
	Forward fw;
	AdminMDao ad;
	Member m;
	String html=null;
	String sql=null;
	Take t;
	Give g;
	Review r;
	Comment c;
	Request q;
	Support s;

	public AdminM(HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
	}

	public Forward execute(int i) {
		switch(i){
		case 1:
			adminMember();
			break;
		case 2:
			blacklist();
			break;
		case 3:
			blacklistAdd();
			break;
		case 4:
			blacklistClear();
			break;
		case 5:
			adminDelete();
			break;
		default:
			break;
		}
		return fw;
	}

	private void adminDelete() {
		//===========================id 세션으로 변경하기=======================
		String id=request.getParameter("id");
		ad=new AdminMDao();
		m=ad.selectMdetail(id);
	
		if(ad.memberdelete(id)){
			System.out.println("회원 삭제 성공");
			request.setAttribute("msg", "회원 삭제 성공");
		}else{
			System.out.println("회원 삭제 실패");
			request.setAttribute("msg", "회원 삭제 실패");
		}
		ad.close();
		fw=new Forward();
		if(m.getM_blacklist()==0){
			fw.setRedirect(true);
			fw.setPath("/myInfo");
		}else{
			fw.setRedirect(true);
			fw.setPath("/myInfo");
		}
		
	}

	private void blacklistClear() {
		String id=request.getParameter("id");
		ad=new AdminMDao();
		String sql="UPDATE MEMBERS SET M_BLACKLIST=0 WHERE M_ID=?";
		if(ad.updateBlackList(sql,id)){
			System.out.println("블랙리스트 삭제 성공");
			request.setAttribute("msg", "블랙리스트 삭제 성공");
		}else{
			System.out.println("블랙리스트 삭제 실패");
			request.setAttribute("msg", "블랙리스트 삭제 실패");
		}
		ad.close();
		fw=new Forward();
		fw.setRedirect(true);
		fw.setPath("/myInfo");
	}

	private void blacklistAdd() {
		String id=request.getParameter("id");
		ad=new AdminMDao();
		String sql="UPDATE MEMBERS SET M_BLACKLIST=1 WHERE M_ID=?";
		if(ad.updateBlackList(sql,id)){
			System.out.println("블랙리스트 추가성공");
			request.setAttribute("msg", "블랙리스트에 추가에 성공했습니다.");
		}else{
			System.out.println("블랙리스트 추가 실패");
			request.setAttribute("msg", "블랙리스트에 추가에 실패했습니다.");
		}
		ad.close();
		fw=new Forward();
		fw.setRedirect(true);
		fw.setPath("/myInfo");
	}

	private void blacklist() {
		ad=new AdminMDao();
		String sql="SELECT * FROM MEMBERS WHERE M_BLACKLIST=1";
		ArrayList<Member> bList;
		bList=ad.memberselect(sql);
		html=makeHtml_blackList(bList);
		request.setAttribute("blacklist", html);
		String id=request.getParameter("id");
		if(id!=null){
			ad=new AdminMDao();
			m=ad.selectMdetail(id);
			html = makeHtml_memberDetail(m);
			request.setAttribute("memberdetail", html);
		}
		ad.close();
		fw=new Forward();
		fw.setRedirect(false);
		fw.setPath("adminMember.jsp");
	}

	private String makeHtml_blackList(ArrayList<Member> bList) {
		StringBuilder sb=new StringBuilder();
		sb.append("<h2>블랙리스트</h2>");
		sb.append("<table ><tr><td>아이디</td><td>이름</td><td>연락처</td></tr>");
		for(int i=0;i<bList.size();i++){
			Member m = bList.get(i);
			sb.append("<tr><td>"+m.getM_id()+"</td>");
			sb.append("<td><a href='/blackDetail?id="+m.getM_id()+"'>"+m.getM_name()+"</a></td>");
			sb.append("<td>"+m.getM_phone()+"</td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}

	private void adminMember() {
		ad=new AdminMDao();
		String sql="SELECT * FROM MEMBERS WHERE M_BLACKLIST=0";
		ArrayList<Member> mList;
		mList=ad.memberselect(sql);
		html=makeHtml_memberList(mList);
		request.setAttribute("memberlist", html);
		String id=request.getParameter("id");
		if(id!=null){
			ad=new AdminMDao();
			m=ad.selectMdetail(id);
			html = makeHtml_memberDetail(m);
			request.setAttribute("memberdetail", html);
		}
		ad.close();
		fw=new Forward();
		fw.setRedirect(false);
		fw.setPath("adminMember.jsp");
	}

	private String makeHtml_memberList(ArrayList<Member> mList) {
		StringBuilder sb=new StringBuilder();
		sb.append("<h2>일반회원리스트</h2>");
		sb.append("<tabl><tr><td>아이디</td><td>이름</td><td>연락처</td></tr>");
		for(int i=0;i<mList.size();i++){
			Member m = mList.get(i);
			sb.append("<tr><td>"+m.getM_id()+"</td>");
			sb.append("<td><a href='/admyDetail?id="+m.getM_id()+"'>"+m.getM_name()+"</a></td>");
			sb.append("<td>"+m.getM_phone()+"</td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}

	private String makeHtml_memberDetail(Member m) {
		StringBuilder sb=new StringBuilder();
		Age ageInst = new Age();
		int age = ageInst.operAge(m.getM_birth());
		sb.append("<table><tr><td>아이디</td><td>이름</td><td>연락처</td><td>이메일</td><td>주소</td>"
				+ "<td>성별</td><td>나이</td><td>포인트</td></tr>");
		sb.append("<tr><td>"+m.getM_id()+"</td><td>"+m.getM_name()+"</td><td>"+m.getM_phone()+"</td>");
		sb.append("<td>"+m.getM_email()+"</td><td>"+m.getM_loc()+"</td><td>"+m.getM_gender()+"</td>");
		sb.append("<td>"+age+"</td><td>"+m.getM_point()+"</td></tr></table></br>");
		if(m.getM_blacklist()==0){
			sb.append("<a href='/blackListAdd?id="+m.getM_id()+"'><input type='button' value='블랙리스트 추가'/></a>");
		}else{
			sb.append("<a href='/blackListClear?id="+m.getM_id()+"'><input type='button' value='블랙리스트 해제'/></a>");
		}
		sb.append("<a href='/adminDelete?id="+m.getM_id()+"'><input type='button' value='회원삭제'/></a>");

		return sb.toString();
	}

}//class End
