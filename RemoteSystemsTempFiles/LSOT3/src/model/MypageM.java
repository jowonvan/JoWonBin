package model;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Age;
import bean.Forward;
import bean.Give;
import bean.Member;
import bean.Request;
import bean.Review;
import bean.Support;
import bean.Take;
import dao.AdminGTDao;
import dao.AdminMDao;
import dao.MemberDao;

public class MypageM {
	private HttpServletRequest request;
	private HttpServletResponse response;
	Forward fw;
	AdminMDao ad;
	AdminGTDao gtd;
	MemberDao md;
	String msg;
	HttpSession ss;

	public MypageM(HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
		ss=request.getSession();
	}

	public Forward execute(int i) {
		switch(i){
		case 1:
			myDetail();break;
		case 2:
			myInfo();break;
		case 3:
			requestDelete();break;
		case 4:
			myRequest();break;
		case 5:
			addSupport();break;
		default :
			break;
		}
		return fw;
	}

	private void addSupport() {
		String id=ss.getAttribute("id").toString();
		String point=request.getParameter("point");
		ad=new AdminMDao();
		if(ad.supportinsert(id,point)){
			if(ad.pointupdate(id)){
				System.out.println("후원완료");
				request.setAttribute("msg", "후원되었습니다.");
			}else{
				System.out.println("후원실패");
				request.setAttribute("msg", "후원 실패했습니다.");
			}
		}else{
			System.out.println("support insert 실패");
		}
		ad.close();
		forward();
		fw.setPath("/myInfo");
	}

	private void myRequest() {
		String code = request.getParameter("code");

	}

	private void requestDelete() {
		String code=request.getParameter("code");
		gtd=new AdminGTDao();
		String sql="DELETE FROM REQUEST WHERE Q_CODE=?";
		if(gtd.dbdelete(sql, code)){
			System.out.println("신청취소 완료");
			request.setAttribute("msg","신청이 취소되었습니다.");
		}else{
			System.out.println("신청취소 실패");
			request.setAttribute("msg","신청 취소가 실패했습니다.");
		}
		gtd.close();
		forward();
		fw.setPath("/myInfo");
	}

	private void myInfo() {
		Member m;
		String html;
		String id=ss.getAttribute("id").toString();
		if(id.equals("admin")){
			ad=new AdminMDao();
			String sql="SELECT * FROM MEMBERS WHERE M_BLACKLIST=0";
			ArrayList<Member> mList;
			mList=ad.memberselect(sql);
			html=makeHtml_memberList(mList);
			request.setAttribute("memberlist", html);
			String m_id=request.getParameter("id");
			if(m_id!=null){
				ad=new AdminMDao();
				m=ad.selectMdetail(m_id);
				html = makeHtml_memberDetail(m);
				request.setAttribute("memberdetail", html);
			}
			ad=new AdminMDao();
			sql="SELECT * FROM MEMBERS WHERE M_BLACKLIST=1";
			ArrayList<Member> bList;
			bList=ad.memberselect(sql);
			html=makeHtml_blackList(bList);
			request.setAttribute("blacklist", html);
			m_id=request.getParameter("id");
			if(m_id!=null){
				ad=new AdminMDao();
				m=ad.selectMdetail(m_id);
				html = makeHtml_memberDetail(m);
				request.setAttribute("memberdetail", html);
			}
			forward();
			fw.setPath("mydetail.jsp");
			
		}else{

			ad=new AdminMDao();
			m=ad.selectMdetail(id);
			String detail=makehtml_m(m);
			request.setAttribute("detail", detail);

			/*m=ad.selectMdetail(id);
		String point=makehtml_s(m);
		request.setAttribute("point", point);*/

			gtd=new AdminGTDao();
			String sql="SELECT * FROM REQUEST Q INNER JOIN TAKE T ON Q.Q_TCODE=T.T_CODE WHERE Q_MID=?";
			ArrayList<Request> qList=gtd.requestselect(sql,id);
			String RequestList=makehtml_q(qList);
			request.setAttribute("RequestList", RequestList);
			md=new MemberDao();
			ArrayList<Give> gList=md.giveselect(id);
			ArrayList<Take> tList=md.takeselect(id);
			ArrayList<Review> rList=md.reviewselect(id);
			String List=makehtml(gList,tList,rList);
			request.setAttribute("List", List);
			md.close();
			forward();
			fw.setPath("mydetail.jsp");
		}
		ad.close();
	}
	private String makeHtml_blackList(ArrayList<Member> bList) {
		StringBuilder sb=new StringBuilder();
		sb.append("<h2>블랙리스트</h2>");
		sb.append("<table border='1'><tr><td>아이디</td><td>이름</td><td>연락처</td></tr>");
		for(int i=0;i<bList.size();i++){
			Member m = bList.get(i);
			sb.append("<tr><td>"+m.getM_id()+"</td>");
			sb.append("<td><a href='/myInfo?id="+m.getM_id()+"'>"+m.getM_name()+"</a></td>");
			sb.append("<td>"+m.getM_phone()+"</td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}
	private String makeHtml_memberList(ArrayList<Member> mList) {
		StringBuilder sb=new StringBuilder();
		sb.append("<h2>일반회원리스트</h2>");
		sb.append("<table border='1'><tr><td>아이디</td><td>이름</td><td>연락처</td></tr>");
		for(int i=0;i<mList.size();i++){
			Member m = mList.get(i);
			sb.append("<tr><td>"+m.getM_id()+"</td>");
			sb.append("<td><a href='/myInfo?id="+m.getM_id()+"'>"+m.getM_name()+"</a></td>");
			sb.append("<td>"+m.getM_phone()+"</td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}
	private String makeHtml_memberDetail(Member m) {
		StringBuilder sb=new StringBuilder();
		Age ageInst = new Age();
		int age = ageInst.operAge(m.getM_birth());
		sb.append("<table border='1'><tr><td>아이디</td><td>이름</td><td>연락처</td><td>이메일</td><td>주소</td>"
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

	/*private String makehtml_s(Member m) {
		StringBuilder sb=new StringBuilder();
		sb.append("보유한 Gift : "+m.getM_point()+"Point ");
		sb.append("<a href='/addsupport?point="+m.getM_point()+"'><input type='button' value='후원하기'/></a>");
		return sb.toString();
	}*/

	private String makehtml(ArrayList<Give> gList, ArrayList<Take> tList, ArrayList<Review> rList) {
		StringBuilder sb=new StringBuilder();
		sb.append("<h3>내가 올린 게시글</h3>");
		sb.append("<h4>기부받기</h4>");
		if(gList.size()!=0){
			sb.append("<table><tr><td>번호</td><td>제목</td><td>등록자</td><td>작성일</td><tr>");
			for(int i=0;i<gList.size();i++){
				Give g=gList.get(i);
				sb.append("<tr><td>"+(i+1)+"</td>");
				sb.append("<td><a href='/adgiveDetail?code="+g.getG_code()+"'>"+g.getG_title()+"</a></td>");
				sb.append("<td>"+g.getG_mid()+"</td>");
				sb.append("<td>"+g.getG_date()+"</td></tr>");
			}
			sb.append("</table>");
		}
		sb.append("<h4>기부하기</h4>");
		if(tList.size()!=0){
			sb.append("<table><tr><td>번호</td><td>제목</td><td>등록자</td><td>작성일</td><tr>");
			for(int i=0;i<tList.size();i++){
				Take t=tList.get(i);
				sb.append("<tr><td>"+(i+1)+"</td>");
				sb.append("<td><a href='/adtakeDetail?code="+t.getT_code()+"&kind="+t.getT_kind()+"'>"+t.getT_title()+"</a></td>");
				sb.append("<td>"+t.getT_mid()+"</td>");
				sb.append("<td>"+t.getT_date()+"</td></tr>");
			}
			sb.append("</table>");
		}
		sb.append("<h4>리뷰</h4>");
		if(rList.size()!=0){
			sb.append("<table><tr><td>번호</td><td>제목</td><td>등록자</td><td>작성일</td><tr>");
			for(int i=0;i<rList.size();i++){
				Review r=rList.get(i);
				sb.append("<tr><td>"+(i+1)+"</td>");
				sb.append("<td><a href='/adreviewDetail?code="+r.getR_code()+"'>"+r.getR_title()+"</a></td>");
				sb.append("<td>"+r.getR_mid()+"</td>");
				sb.append("<td>"+r.getR_date()+"</td></tr>");
			}
			sb.append("</table>");
		}
		return sb.toString();
	}

	private String makehtml_q(ArrayList<Request> qList) {
		StringBuilder sb=new StringBuilder();
		sb.append("<h3>내가 신청한 게시글</h3>");
		if(qList.size()!=0){
			sb.append("<table><tr><td>번호</td><td>제목</td><td>등록자</td><td>작성일</td><td></td><tr>");
			for(int i=0;i<qList.size();i++){
				Request q=qList.get(i);
				sb.append("<tr><td>"+(i+1)+"</td>");
				sb.append("<td><a href='/adtakeDetail?code="+q.getQ_tcode()+"&kind="+q.getQ_tkind()+"'>"+q.getQ_ttitle()+"</a></td>");
				sb.append("<td>"+q.getQ_mid()+"</td>");
				sb.append("<td>"+q.getQ_tdate()+"</td>");
				sb.append("<td><a href='/requestDelete?code="+q.getQ_code()+"'><input type='button' value='신청취소'/></a></td></tr>");
			}
			sb.append("</table>");
		}
		return sb.toString();
	}

	private String makehtml_m(Member m) {
		StringBuilder sb=new StringBuilder();
		sb.append("<div id='personTable'>");
		sb.append("<h3>개인정보</h3>");
		sb.append("<table><tr><td>아이디</td><td>"+m.getM_id()+"</td></tr>");
		sb.append("<tr><td>이름</td><td>"+m.getM_name()+"</td></tr>");
		sb.append("<tr><td>연락처</td><td>"+m.getM_phone()+"</td></tr>");
		sb.append("<tr><td>이메일</td><td>"+m.getM_email()+"</td></tr>");
		sb.append("<tr><td>주소</td><td>"+m.getM_loc()+"</td></tr>");
		sb.append("<tr><td>생일</td><td>"+m.getM_birth()+"</td></tr>");
		sb.append("<tr><td>보유한 Gift</td><td>"+m.getM_point()+"Point  <a href='/addsupport?point="+m.getM_point()+"'><input type='button' value='후원하기'/></a></td></tr> ");
		sb.append("</table>");
		sb.append("</div>");
		sb.append("<div id='giftPoin'>");

		sb.append("</div>");
		sb.append("<a href='/myInfochange?kind="+m.getM_kind()+"&name="+m.getM_name()+"&phone="+m.getM_phone()+
				"&email="+m.getM_email()+"&loc="+m.getM_loc()+"&gender="+m.getM_gender()+
				"&birth="+m.getM_birth()+"&officetel="+m.getM_office_tel()+"&officename="+m.getM_office_name()+
				"'><input type='button' value='개인정보 수정'/></a>");
		return sb.toString();
	}

	private void myDetail() {
		HttpSession session=request.getSession();
		String id=session.getAttribute("id").toString();
		String pw=request.getParameter("pw");
		gtd=new AdminGTDao();
		if(gtd.pwcheck(id,pw)){
			System.out.println("비밀번호 일치");
			forward();
			fw.setPath("/myInfo");
		}else{
			System.out.println("비밀번호 불일치");
			msg = "비밀번호가 일치하지 않습니다.";
			request.setAttribute("msg", msg);
			gtd.close();
			forward();
			fw.setPath("mypage.jsp");
		}
	}

	private void forward(){
		fw=new Forward();
		fw.setRedirect(false);
	}

}//class End
