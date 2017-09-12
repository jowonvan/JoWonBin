package model;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Age;
import bean.Comment;
import bean.Forward;
import bean.Give;
import bean.Notice;
import bean.Review;
import bean.Take;
import dao.AdminGTDao;
import dao.AdminMDao;

public class AdminGT {
	private HttpServletRequest request;
	private HttpServletResponse response;
	Forward fw;
	AdminGTDao gtd;
	String html;
	Give g;
	Take t;
	AdminMDao amd;
	HttpSession ss;
	public AdminGT(HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
		ss = request.getSession();
	}

	public Forward execute(int i) {
		switch(i){
		case 1:
			giveList();break;
		case 2:
			giveDetail();break;
		case 3:
			comment();break;
		case 4:
			adminTiList();break;
		case 5:
			takedetail();break;
		case 6:
			adminTgList();break;
		case 7:
			notice();break;
		case 8:
			noticedetail();break;
		case 9:
			delete();break;
		case 10:
			noticeAdd();break;
		case 11:
			noticeUpdate();break;
		case 12:
			noticePageMove();break;
		case 13:
			pwUpdate();break;
		case 14:
			reviewList();break;
		case 15:
			reviewdetail();break;
		case 16:
			login();break;
		default :
			break;
		}
		return fw;
	}


	private void login() {
		if(ss.getAttribute("id")!=null){
			fw=new Forward();
			fw.setRedirect(false);
			String id=ss.getAttribute("id").toString();
			if(id.equals("admin")){
				fw.setPath("adminTemplate.jsp");
			}else{
				fw.setPath("/Index");
			}
		}else{
			fw.setPath("/Index");
		}
		/*String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		System.out.println(id);
		System.out.println(pw);
		amd=new AdminMDao();
		fw=new Forward();
		fw.setRedirect(false);
		if(id.equals("admin")){
			if(pw.equals(amd.ismember(id))){
				fw.setPath("adminTemplate.jsp");
			}else{
				request.setAttribute("msg", "비밀번호가 틀렸습니다.");
				fw.setPath("/adminlogin.jsp");
			}
		}else{
			request.setAttribute("msg", "접근 권한이 없습니다.");
			fw.setPath("/adminlogin.jsp");
		}*/
	}

	private void reviewdetail() {
		String r_code=request.getParameter("code");
		String kind=null;
		gtd=new AdminGTDao();
		//============================================notice상페내역 가져오기
		Review r=new Review();
		r=gtd.reviewdetailselect(r_code);
		String detail=reviewdetail(r);
		request.setAttribute("detail", detail);
		//============================================조회수 올리기
		String sql="UPDATE REVIEW SET R_HITS=(R_HITS+1) WHERE R_CODE=?";
		if(gtd.hitsupdate(sql,r_code)){
			System.out.println("조회수 증가 성공");
		}else{
			System.out.println("조회수 증가 실패");
		}
		//============================================댓글 리스트 불러오기
		sql="SELECT * FROM COMMENTS WHERE c_rcode=?";
		ArrayList<Comment> cList=gtd.commentselect(sql,r_code);
		if(cList.size()!=0){
			html=makehtml_comment(cList,kind);
			request.setAttribute("commentList", html);
		}
		/*gtd.close();
		fw=new Forward();
		fw.setRedirect(false);*/
		forward();
		fw.setPath("gtDetail.jsp");

	}

	private String reviewdetail(Review r) {
		StringBuilder sb=new StringBuilder();
		sb.append("<h3> 제목 : "+r.getR_title()+"</h3>");
		sb.append("이름 : "+r.getR_name()+"</br>");
		sb.append("날짜 : "+r.getR_when()+"</br>");
		sb.append("내용 : "+r.getR_content()+"</br>");
		sb.append("장소 : "+r.getR_place()+"</br>");
		sb.append("느낀점 : "+r.getR_feeling()+"</br>");
		if(r.getR_filename1()!=null)
			sb.append("<img src='upload"+"/" + r.getR_filename1()+"' width='150' height='150'>");
		if(r.getR_filename2()!=null)
			sb.append("<img src='upload"+"/" + r.getR_filename2()+"' width='150' height='150'>");
		if(r.getR_filename3()!=null)
			sb.append("<img src='upload"+"/" + r.getR_filename3()+"' width='150' height='150'>");
		sb.append("<a href='/adminReview'><input type='button' value='목록'/></a>");
		sb.append("<form action='/comment?code="+r.getR_code()+"' method='post'>");
		sb.append("<input type='text' name='comment'/>");
		sb.append("<input type='submit' value='댓글등록'/></form>");

		return sb.toString();
	}

	private void reviewList() {
		gtd=new AdminGTDao();
		ArrayList<Review> rList=gtd.reviewselect();
		html=makehtml_review(rList);
		request.setAttribute("reviewList", html);
		/*fw=new Forward();
		fw.setRedirect(false);*/
		forward();
		fw.setPath("giveTake.jsp");
	}

	private String makehtml_review(ArrayList<Review> rList) {
		StringBuilder sb=new StringBuilder();
		sb.append("<h2>공지사항</h2>");
		sb.append("<table border='1'><tr><td>번호</td><td>제목</td><td>작성자</td><td>작성일</td><td>조회수</td></tr>");
		for(int i=0; i<rList.size(); i++){
			Review r=rList.get(i);
			sb.append("<tr><td>"+(i+1)+"</td>");
			sb.append("<td><a href='/adreviewDetail?code="+r.getR_code()+"'>"+r.getR_title()+"</a></td>");
			sb.append("<td>"+r.getR_mid()+"</td>");
			sb.append("<td>"+r.getR_date()+"</td>");
			sb.append("<td>"+r.getR_hits()+"</td></tr>");
		}
		sb.append("</table>");
		sb.append("<a href='/home'><input type='button' value='돌아가기'/></a>");
		return sb.toString();
	}

	private void pwUpdate() {
		HttpSession session=request.getSession();
		String id=session.getAttribute("id").toString();
		String pw= request.getParameter("nowPW");
		String newpw = request.getParameter("newPW");
		String newpwchk = request.getParameter("newPWchk");
		gtd=new AdminGTDao();
		if(gtd.pwcheck(id,pw)){
			System.out.println("비밀번호 일치합니다.");
			if(newpw.equals(newpwchk)){
				System.out.println("새 비밀번호와 확인이 일치합니다.");
				if(gtd.pwupdate(id,newpw)){
					System.out.println("비밀번호 변경 성공");
				}else{
					System.out.println("비밀번호 변경 실패");
				}
			}else{
				System.out.println("새 비밀번호와 확인이 일치하지 않습니다.");
			}
		}else{
			System.out.println("비밀번호 일치하지 않습니다.");
		}
		/*fw=new Forward();
		fw.setRedirect(false);*/
		forward();
		fw.setPath("/home");
	}

	private void noticePageMove() {
		String code=request.getParameter("code");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		request.setAttribute("code", code);
		request.setAttribute("title", title);
		request.setAttribute("content", content);
		fw=new Forward();
		fw.setRedirect(false);
		if(ss.getAttribute("id")==null){
			request.setAttribute("msg", "로그인 후 이용 가능합니다.");
			fw.setPath("/adminNotice");
		}else{
			if(ss.getAttribute("id").toString().equals("admin")){
				fw.setPath("noticeAdd.jsp");
			}else{
				request.setAttribute("msg", "관리자만 작성할 수 있습니다.");
				fw.setPath("/adminNotice");
			}
		}

	}

	private void noticeUpdate() {
		String code=request.getParameter("code");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		System.out.println(code);
		gtd=new AdminGTDao();
		if(gtd.noticeupdate(code,title,content)){
			System.out.println("수정 성공");
		}else{
			System.out.println("수정 실패");
		}
		/*fw=new Forward();
		fw.setRedirect(false);*/
		forward();
		fw.setPath("/noticeDetail?code="+code);
	}

	private void noticeAdd() {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		gtd=new AdminGTDao();
		if(gtd.noticeinsert(title,content)){
			System.out.println("등록 성공");
		}else
			System.out.println("등록 실패");
		/*gtd.close();
			fw=new Forward();
			fw.setRedirect(false);*/
		forward();
		fw.setPath("/adminNotice");
	}

	private void delete() {
		if(ss.getAttribute("id")==null){
			request.setAttribute("msg", "작성자만 삭제할 수 있습니다.");
			fw=new Forward();
			fw.setRedirect(false);
			fw.setPath("/adminNotice");
		}else{
			String code = request.getParameter("code");
			String kind = request.getParameter("kind");
			System.out.println(kind);
			String codekind = request.getParameter("codekind");
			gtd=new AdminGTDao();
			fw=new Forward();
			fw.setRedirect(false);
			if(code.startsWith("G")){
				String sql="DELETE FROM GIVE WHERE G_CODE=?";
				if(gtd.dbdelete(sql,code)){
					System.out.println("삭제 성공");
					fw.setPath("/adminGive");
				}else
					System.out.println("삭제 실패");
			}else if(code.startsWith("T")){
				String sql="DELETE FROM TAKE WHERE T_CODE=?";
				if(gtd.dbdelete(sql,code)){
					System.out.println("삭제 성공");
					if(kind.equals("단체"))
						fw.setPath("/adminTg");
					else if(kind.equals("개인"))
						fw.setPath("/adminTi");
				}else
					System.out.println("삭제 실패");
			}else if(code.startsWith("R")){
				String sql="DELETE FROM REVIEW WHERE R_CODE=?";
				if(gtd.dbdelete(sql,code)){
					System.out.println("삭제 성공");
					fw.setPath("/adminReview");//=========================후기만들고 바꾸기
				}
				else
					System.out.println("삭제 실패");
			}else if(code.startsWith("N")){
				String sql="DELETE FROM NOTICE WHERE N_CODE=?";
				if(gtd.dbdelete(sql,code)){
					System.out.println("삭제 성공");
					fw.setPath("/adminNotice");
				}
				else
					System.out.println("삭제 실패");
			}else if(code.startsWith("C")){
				String sql="DELETE FROM COMMENTS WHERE C_CODE=?";
				System.out.println(code);
				System.out.println(codekind);
				if(gtd.dbdelete(sql,code)){
					System.out.println("삭제 성공");
					if(codekind.startsWith("G"))
						fw.setPath("/adgiveDetail?code="+codekind);
					else if(codekind.startsWith("T")){
						fw.setPath("/adtakeDetail?code="+codekind+"&kind="+kind);
					}else if(codekind.startsWith("R"))
						fw.setPath("/adreviewDetail?code="+codekind);//====================후기 디테일 바꾸기
				}
				else
					System.out.println("삭제 실패");
			}
			gtd.close();
		}
	}

	private void noticedetail() {
		String n_code=request.getParameter("code");
		gtd=new AdminGTDao();
		//============================================notice상페내역 가져오기
		Notice n=new Notice();
		n=gtd.noticedetailselect(n_code);
		String detail=ndetail(n);
		request.setAttribute("detail", detail);
		//============================================조회수 올리기
		String sql="UPDATE NOTICE SET N_HITS=(N_HITS+1) WHERE N_CODE=?";
		if(gtd.hitsupdate(sql,n_code)){
			System.out.println("조회수 증가 성공");
		}else{
			System.out.println("조회수 증가 실패");
		}
		//============================================댓글 리스트 불러오기
		/*gtd.close();
		fw=new Forward();
		fw.setRedirect(false);*/
		forward();
		fw.setPath("gtDetail.jsp");
	}

	private String ndetail(Notice n) {
		StringBuilder sb=new StringBuilder();
		sb.append("<h3>"+n.getN_title()+"</h3>");
		sb.append("<h5>"+n.getN_content()+"</h5>");
		sb.append("<a href='/Delete?code="+n.getN_code()+"'><input type='button' value='삭제'/></a>");
		sb.append("<a href='/adminNotice'><input type='button' value='목록'/></a>");
		sb.append("<a href='./addNotice?code="+n.getN_code()+"&title="+n.getN_title()+"&content="+n.getN_content()+"'>"
				+ "<input type='button' value='수정'/></a>");
		return sb.toString();
	}

	private void notice() {
		gtd=new AdminGTDao();
		ArrayList<Notice> nList=gtd.noticeselect();
		html=makehtml_notice(nList);
		request.setAttribute("noticeList", html);
		/*gtd.close();
			fw=new Forward();
			fw.setRedirect(false);*/
		forward();
		fw.setPath("notice.jsp");
	}

	private String makehtml_notice(ArrayList<Notice> nList) {
		StringBuilder sb=new StringBuilder();
		sb.append("</br></br></br>");
		sb.append("<h1>공지사항</h1>");
		sb.append("<table><tr><th class='col'>번호</th><th class='col'>제목</th><th class='col'>작성자</th><th  class='col'>작성일</th><th  class='col'>조회수</th></tr>");
		for(int i=0; i<nList.size(); i++){
			Notice n=nList.get(i);
			sb.append("<tr><td>"+(i+1)+"</td>");
			sb.append("<td><a href='/noticeDetail?code="+n.getN_code()+"'>"+n.getN_title()+"</a></td>");
			sb.append("<td>관리자</td>");
			sb.append("<td>"+n.getN_date()+"</td>");
			sb.append("<td>"+n.getN_hits()+"</td></tr>");
		}
		sb.append("</table>");
		sb.append("<a id='write' href='/addNotice'><input type='button' value='글쓰기'/></a>");
		return sb.toString();
	}

	private void adminTgList() {
		String kind="단체";
		gtd=new AdminGTDao();
		ArrayList<Take> tList=gtd.takeselect(kind);
		html=makehtml_take(kind,tList);
		request.setAttribute("TgList", html);
		/*gtd.close();
		fw=new Forward();
		fw.setRedirect(false);*/
		forward();
		fw.setPath("giveTake.jsp");
	}

	//=========================================================================기부받기상세내역
	private void takedetail() {
		String t_code=request.getParameter("code");
		String kind=request.getParameter("kind");
		gtd=new AdminGTDao();
		//============================================take상페내역 가져오기
		t=new Take();
		t=gtd.takedetailselect(t_code);
		System.out.println(t);
		String detail=tdetail(t,kind);
		request.setAttribute("detail", detail);
		//============================================조회수 올리기
		String sql="UPDATE TAKE SET T_HITS=(T_HITS+1) WHERE T_CODE=?";
		if(gtd.hitsupdate(sql,t_code)){
			System.out.println("조회수 증가 성공");
		}else{
			System.out.println("조회수 증가 실패");
		}
		//============================================댓글 리스트 불러오기
		sql="SELECT * FROM COMMENTS WHERE c_tcode=?";
		ArrayList<Comment> cList=gtd.commentselect(sql,t_code);
		if(cList.size()!=0){
			html=makehtml_comment(cList,kind);
			request.setAttribute("commentList", html);
		}
		/*gtd.close();
		fw=new Forward();
		fw.setRedirect(false);*/
		forward();
		fw.setPath("gtDetail.jsp");

	}

	private String tdetail(Take t, String kind) {
		System.out.println(kind);
		StringBuilder sb=new StringBuilder();
		Age a=new Age();
		int age=a.operAge(t.getTm_birth());
		System.out.println(age);
		sb.append("<h3>제목:"+t.getT_title()+"</h3>");
		sb.append("이름 : "+t.getTm_name()+"</br>");
		sb.append("연락처 : "+t.getTm_phone()+"</br>");
		sb.append("이메일 : "+t.getTm_email()+"</br>");
		sb.append("성별 : "+t.getTm_gender()+"</br>");
		sb.append("나이 : "+age+"</br>");
		sb.append("필요 기부 내용 : "+t.getT_content()+"</br>");
		sb.append("신청 기간 : "+t.getT_request_period_s()+"~"+t.getT_request_period_e()+"</br>");
		sb.append("봉사 기간 : "+t.getT_service_period()+"</br>");
		sb.append("재능기부자 성별 : "+t.getT_gender()+"</br>");
		sb.append("재능기부자 나이 : "+t.getT_age()+"</br>");
		sb.append("재능기부자 인원 : "+t.getT_personnel()+"</br>");
		if(t.getT_etc()==null){
			sb.append("기타 : </br>");
		}else{
			sb.append("기타 : "+t.getT_etc()+"</br>");
		}
		if(t.getT_filename()!=null){
			sb.append("<img src='upload"+"/" + t.getT_filename()+"' width='150' height='150'></br>");
		}
		sb.append("<a href='/Delete?code="+t.getT_code()+"&kind="+kind+"'><input type='button' value='삭제'/></a>");
		if(kind.equals("개인"))
			sb.append("<a href='/adminTi'><input type='button' value='목록'/></a>");
		else if(kind.equals("단체"))
			sb.append("<a href='/adminTg'><input type='button' value='목록'/></a>");
		sb.append("<form action='/comment?code="+t.getT_code()+"' method='post'>");
		sb.append("<input type='text' name='comment'/>");
		sb.append("<input type='submit' value='댓글등록'/></form>");
		return sb.toString();
	}

	//===================================================================기부받기(개인)리스트
	private void adminTiList() {
		String kind="개인";
		gtd=new AdminGTDao();
		ArrayList<Take> tList=gtd.takeselect(kind);
		html=makehtml_take(kind,tList);
		request.setAttribute("TiList", html);
		/*gtd.close();
		fw=new Forward();
		fw.setRedirect(false);*/
		forward();
		fw.setPath("giveTake.jsp");
	}

	private String makehtml_take(String kind,ArrayList<Take> tList) {
		StringBuilder sb=new StringBuilder();
		if(kind=="개인"){
			sb.append("</br></br></br>");
			sb.append("<h2>기부받기(개인) 리스트</h2>");
		}else if(kind=="단체"){
			sb.append("</br></br></br>");
			sb.append("<h2>기부받기(단체) 리스트</h2>");
		}
		sb.append("<table border='1'><tr><td>번호</td><td>제목</td><td>작성자</td><td>작성일</td><td>조회수</td></tr>");
		for(int i=0; i<tList.size(); i++){
			Take t=tList.get(i);
			sb.append("<tr><td>"+(i+1)+"</td>");
			sb.append("<td><a href='/adtakeDetail?code="+t.getT_code()+"&kind="+kind+"'>"+t.getT_title()+"</a></td>");
			sb.append("<td>"+t.getT_mid()+"</td>");
			sb.append("<td>"+t.getT_date()+"</td>");
			sb.append("<td>"+t.getT_hits()+"</td></tr>");
		}
		sb.append("</table>");
		sb.append("<a href='/home'><input type='button' value='돌아가기'/></a>");
		return sb.toString();
	}

	//=================댓글달기
	private void comment() {
		String code=request.getParameter("code");
		System.out.println(code);
		String content=request.getParameter("comment");
		System.out.println(content);

		int rdvalue = Integer.parseInt(request.getParameter("star"));
		System.out.println(rdvalue);




		String kind = null;
		//==============세션에 담아놓은 아이디값 가져오기=========================
		HttpSession session=request.getSession();
		String id=session.getAttribute("id").toString();
		gtd=new AdminGTDao();
		if(code.startsWith("G")){
			String sql="INSERT INTO COMMENTS VALUES('C'||C_SEQ.NEXTVAL,?,DEFAULT,?,?,'','',?)";
			if(gtd.commentinsert(sql,code,id,content,rdvalue)){
				System.out.println("insert 성공");
			}else{
				System.out.println("insert 실패");
			}
		}else if(code.startsWith("T")){
			String sql="INSERT INTO COMMENTS VALUES('C'||C_SEQ.NEXTVAL,?,DEFAULT,?,'',?,'',?)";
			if(gtd.commentinsert(sql,code,id,content,rdvalue)){
				System.out.println("insert 성공");
				Take t=gtd.takedetailselect(code);
				kind=t.getT_kind();
				System.out.println("insert kind"+kind);
			}else{
				System.out.println("insert 실패");
			}
		}else if(code.startsWith("R")){
			String sql="INSERT INTO COMMENTS VALUES('C'||C_SEQ.NEXTVAL,?,DEFAULT,?,'','',?,?)";
			if(gtd.commentinsert(sql,code,id,content,rdvalue)){
				System.out.println("insert 성공");
			}else{
				System.out.println("insert 실패");
			}
		}
		/*gtd.close();
		fw=new Forward();
		fw.setRedirect(false);*/
		forward();
		if(code.startsWith("G")){
			fw.setPath("/adgiveDetail?code="+code);
		}else if(code.startsWith("T")){
			fw.setPath("/adtakeDetail?code="+code+"&kind="+kind);
		}else if(code.startsWith("R")){
			fw.setPath("/adreviewDetail?code="+code);
		}
	}

	//================기부하기 상세내역
	private void giveDetail() {
		String g_code=request.getParameter("code");
		String kind=null;
		gtd=new AdminGTDao();
		//=====================give상페내역 가져오기
		g=gtd.givedetail(g_code);
		html=makehtml_gdetail(g);
		request.setAttribute("givedetail", html);
		//====================조회수 올리기
		String sql="UPDATE GIVE SET G_HITS=(G_HITS+1) WHERE G_CODE=?";
		if(gtd.hitsupdate(sql,g_code)){
			System.out.println("조회수 증가 성공");
		}else{
			System.out.println("조회수 증가 실패");
		}
		//=====================댓글 리스트 불러오기
		sql="SELECT * FROM COMMENTS WHERE c_gcode=?";
		ArrayList<Comment> cList=gtd.commentselect(sql,g_code);
		if(cList.size()!=0){
			html=makehtml_comment(cList,kind);
			request.setAttribute("commentList", html);
		}
		/*gtd.close();
		fw=new Forward();
		fw.setRedirect(false);*/
		forward();
		fw.setPath("gtDetail.jsp");
	}

	private String makehtml_comment(ArrayList<Comment> cList, String kind) {
		StringBuilder sb=new StringBuilder();
		sb.append("<table border='1'><tr><td>작성자</td><td>내용</td><td>날짜</td><td>삭제</td></tr>");
		for(int i=0; i<cList.size(); i++){
			Comment c=cList.get(i);
			sb.append("<tr><td>"+c.getC_mid()+"</td>");
			sb.append("<td>"+c.getC_content()+"</td>");
			sb.append("<td>"+c.getC_date()+"</td>");
			if(c.getC_gcode()!=null)
				sb.append("<td><a href='/Delete?code="+c.getC_code()+"&codekind="+c.getC_gcode()+"'><input type='button' value='삭제'/></a></td></tr>");
			else if(c.getC_tcode()!=null)
				sb.append("<td><a href='/Delete?code="+c.getC_code()+"&codekind="+c.getC_tcode()+"&kind="+kind+"'><input type='button' value='삭제'/></a></td></tr>");
			else if(c.getC_rcode()!=null)
				sb.append("<td><a href='/Delete?code="+c.getC_code()+"&codekind="+c.getC_rcode()+"'><input type='button' value='삭제'/></a></td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}

	private String makehtml_gdetail(Give g) {
		StringBuilder sb=new StringBuilder();
		Age a=new Age();
		int age=a.operAge(g.getGm_birth());
		sb.append("<h3>제목:"+g.getG_title()+"</h3>");
		sb.append("기부자 이름 : "+g.getGm_name()+"</br>");
		sb.append("연락처 : "+g.getGm_phone()+"</br>");
		sb.append("이메일 : "+g.getGm_email()+"</br>");
		sb.append("재능분야 : "+g.getG_bigtalent()+g.getG_smalltalent()+"</br>");
		sb.append("성별 : "+g.getGm_gender()+"</br>");
		sb.append("나이 : "+age+"</br>");
		sb.append("희망 지역 : "+g.getG_loc()+"</br>");
		sb.append("희망 기간 : "+g.getG_period_s()+"~"+g.getG_period_e()+"</br>");
		sb.append("<a href='/Delete?code="+g.getG_code()+"'><input type='button' value='삭제'/></a>");
		sb.append("<a href='/adminGive'><input type='button' value='목록'/></a>");
		sb.append("<form action='/comment?code="+g.getG_code()+"' method='post'>");
		sb.append("<input type='text' placeholder='댓글을 달아주세요' name='comment'/>");
		sb.append("<input type='submit' value='댓글등록'/></form>");
		return sb.toString();
	}

	//====================기부하기 리스트
	private void giveList() {
		gtd=new AdminGTDao();
		ArrayList<Give> gList=gtd.giveselect();
		html=makehtml_give(gList);
		request.setAttribute("giveList", html);
		/*gtd.close();
		fw=new Forward();
		fw.setRedirect(false);*/
		forward();
		fw.setPath("giveTake.jsp");
	}

	private String makehtml_give(ArrayList<Give> gList) {
		StringBuilder sb=new StringBuilder();
		sb.append("</br></br></br>");
		sb.append("<h2>give리스트</h2>");
		sb.append("<table border='1'><tr><td>번호</td><td>제목</td><td>작성자</td><td>작성일</td><td>조회수</td></tr>");
		for(int i=0; i<gList.size(); i++){
			Give g=gList.get(i);
			sb.append("<tr><td>"+(i+1)+"</td>");
			sb.append("<td><a href='/adgiveDetail?code="+g.getG_code()+"'>"+g.getG_title()+"</a></td>");
			sb.append("<td>"+g.getG_mid()+"</td>");
			sb.append("<td>"+g.getG_date()+"</td>");
			sb.append("<td>"+g.getG_hits()+"</td></tr>");
		}
		sb.append("</table>");
		sb.append("<a href='/home'><input type='button' value='돌아가기'/></a>");
		return sb.toString();
	}

	private void forward(){
		gtd.close();
		fw=new Forward();
		fw.setRedirect(false);
	}

}//class End
