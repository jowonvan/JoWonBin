package model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import bean.Comment;
import bean.Forward;
import bean.Give;
import bean.Request;
import bean.Take;
import dao.AdminGTDao;
import dao.GiveAndTakeDao;
public class GiveAndTake {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Forward fw;
	private String msg;
	private GiveAndTakeDao gtDao;
	HttpSession ss;
	String htmlStr;
	String html;
	public GiveAndTake(HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
		ss=request.getSession();
	}
	public Forward execute(int code) {
		switch(code){
		case 1:
			TgroupSelectG(); break;
		case 2:
			TgroupSelectP(); break;
		case 3:
			TgroupResult(); break;
		case 4:
			TgroupInput(); break;
		case 5:
			TgroupSearchT(); break;
		case 6:
			TgroupSearchI(); break;
		case 7:
			TgroupDelete(); break;
		case 8:
			TgroupUpdate(); break;
		case 9:
			TgroupRequest(); break;
		case 10:
			TgroupAgree(); break;
		case 11:
			TgroupRefusal(); break;
		case 12:
			TgroupApplication(); break;
		case 13:
			TpersonInput(); break;
		case 14:
			TakeComment(); break;
		case 15:
			commentDelete(); break;
		case 16:
			requestSuccess(); break;
		default:

		}
		return fw;
	}
	private void requestSuccess() {
		String q_code = request.getParameter("q_code");
		String code = request.getParameter("t_code");
		String kind = request.getParameter("t_kind");
		int i = 3;
		gtDao = new GiveAndTakeDao();
		fw = new Forward();
		if(gtDao.requestAgree(i, q_code)){
			if(gtDao.requestPoint(code)){
				msg = "봉사활동 완료로 포인트 지급되었습니다.";
			}else{
				msg = "포인트가 지급되지않았습니다.";
			}
			request.setAttribute("msg", msg);
			ArrayList<Request> List = null;
			List = gtDao.searchRequest(code);
			String list = null;
			list = makeRequest(List, code, kind);
			request.setAttribute("Requesthtml", list);
		}
		fw.setPath("/tgDetail?t_code="+code);
		fw.setRedirect(false);
	}
	//================================신청하기 ss세션 아이디 받기!
	private void TgroupApplication() {
		String id = ss.getAttribute("id").toString();
		String t_mid = request.getParameter("t_mid");
		String t_code = request.getParameter("t_code");
		String t_kind = request.getParameter("t_kind");
		int t_personnel = Integer.valueOf(request.getParameter("t_personnel"));
		int count = Integer.valueOf(request.getParameter("count"));
		System.out.println("kind="+t_kind);
		System.out.println(t_personnel);
		System.out.println(count);
		gtDao = new GiveAndTakeDao();
		Request q = new Request(id, t_code);
		if(count < t_personnel){
			if(id.equals(t_mid)){
				System.out.println("아이디 중복");
				msg = "신청할 수 없습니다.";
				request.setAttribute("msg", msg);
			}else{
				if(gtDao.requestSelect(q)){
					msg = "이미 신청되었습니다.";
					request.setAttribute("msg", msg);
				}else{
					System.out.println("????");
					if(gtDao.requestInsert(q)){
						msg = "신청되었습니다.";
						request.setAttribute("msg", msg);
					}
				}
			}
		}else{
			msg = "신청마감되었습니다.";
			request.setAttribute("msg", msg);
		}
		fw = new Forward();
		fw.setPath("/tgDetail?t_code="+t_code+"&t_kind="+t_kind);
		fw.setRedirect(false);
	}
	//================================신청자 보기-> 거절버튼
	private void TgroupRefusal() {
		String q_code = request.getParameter("q_code");
		String code = request.getParameter("t_code");
		String kind = request.getParameter("t_kind");
		System.out.println(code);
		int i = 2;
		gtDao = new GiveAndTakeDao();
		fw = new Forward();
		if(gtDao.requestAgree(i, q_code)){
			msg = "거절되었습니다.";
			request.setAttribute("msg", msg);
			ArrayList<Request> List = null;
			List = gtDao.searchRequest(code);
			String list = null;
			list = makeRequest(List, code, kind);
			request.setAttribute("Requesthtml", list);
		}
		fw.setPath("/tgDetail?t_code="+code);
		fw.setRedirect(false);
	}
	//================================신청자 보기-> 수락버튼
	private void TgroupAgree() {
		String q_code = request.getParameter("q_code");
		String code = request.getParameter("t_code");
		String kind = request.getParameter("t_kind");
		System.out.println(code);
		int i = 1;
		gtDao = new GiveAndTakeDao();
		if(gtDao.requestAgree(i, q_code)){
			request.setAttribute("msg", "수락되었습니다.");
			ArrayList<Request> List = null;
			List = gtDao.searchRequest(code);
			String list = null;
			list = makeRequest(List, code, kind);
			request.setAttribute("Requesthtml", list);
		}
		fw = new Forward();
		fw.setPath("/tgDetail?t_code="+code);
		fw.setRedirect(false);

		gtDao.close();
	}
	//==========================================신청자 보기
	private void TgroupRequest() {
		String t_mid = request.getParameter("t_mid");
		String code = request.getParameter("t_code");
		String kind = request.getParameter("t_kind");
		String id = ss.getAttribute("id").toString();
		gtDao = new GiveAndTakeDao();

		if(id.equals(t_mid)){
			ArrayList<Request> List = null;
			List = gtDao.searchRequest(code);
			String list = null;
			list = makeRequest(List, code, kind);
			request.setAttribute("Requesthtml", list);
		}else{
			msg = "접근 권한이 없습니다.";
			request.setAttribute("msg", msg);
		}
		fw = new Forward();
		fw.setPath("/tgDetail?t_code="+code);
		fw.setRedirect(false);
	}

	private String makeRequest(ArrayList<Request> list, String code, String kind) {
		StringBuilder sa=new StringBuilder();
		sa.append("<table id='requesters'>");
		sa.append("<tr><th class='col'>번호</th><th class='col'>ID</th><th class='col'>게시물번호</th><th class='col'>신청현황</th><th class='col'>수락</th><th class='col'>거절</th><th class='col'>봉사완료</th></tr>");
		for(int a=0; a < list.size(); a++){
			Request t= list.get(a);
			sa.append("<tr><td class='col'>"+ (a+1) +"</td>");
			sa.append("<td class='col'>"+ t.getQ_mid() +"</td>");
			sa.append("<td class='col'>"+ t.getQ_tcode() +"</td>");
			if(t.getQ_progress()==0){
			sa.append("<td class='col'>신청대기</td>");
			}else if(t.getQ_progress()==1){
				sa.append("<td class='col'>신청완료</td>");
			}else if(t.getQ_progress()==2){
				sa.append("<td class='col'>신청거절</td>");
			}else if(t.getQ_progress()==3){
				sa.append("<td class='col'>봉사완료</td>");
			}
			sa.append("<td class='col'><a href='/requestAgree?q_code="+ t.getQ_code()+"&t_code=" + code + "&t_kind="+kind+"'><input type='button' value='수락'/></a></td>");
			sa.append("<td class='col'><a href='/requestrefusal?q_code="+ t.getQ_code()+"&t_code=" + code + "&t_kind="+kind+"'><input type='button' value='거절'/></a></td>");
			sa.append("<td class='col'><a href='/requestsuccess?q_code="+ t.getQ_code()+"&t_code=" + code + "&t_kind="+kind+"'><input type='button' value='봉사완료'/></a></td></tr>");
		}
		sa.append("</table>");
		return sa.toString();
	}
	//==========================================검색 (등록자)
	private void TgroupSearchI() {
		String t_mid = request.getParameter("inputSerch");
		String kind = request.getParameter("gpkind");
		System.out.println(kind);
		gtDao = new GiveAndTakeDao();
		ArrayList<Take> List = null;
		List = gtDao.searchTakeI(t_mid, kind);

		String list = null;
		list = makeHtml(List, kind);
		request.setAttribute("html", list);
		request.setAttribute("t_kind", kind);      
		fw = new Forward();
		fw.setPath("takeGroup.jsp");
		fw.setRedirect(false);

	}
	//==========================================검색 (제목)
	private void TgroupSearchT() {
		String t_title = request.getParameter("inputSerch");
		String t_kind = request.getParameter("gpkind");
		System.out.println(t_kind);
		gtDao = new GiveAndTakeDao();
		ArrayList<Take> List = null;
		List = gtDao.searchTakeT(t_title, t_kind);
		String list = null;
		list = makeHtml(List, t_kind);
		request.setAttribute("html", list);
		request.setAttribute("kind", t_kind);
		fw = new Forward();
		fw.setPath("takeGroup.jsp");
		fw.setRedirect(false);
	}
	//============================ 기부받기 수정 (update)
	private void TgroupUpdate() {
		String id = ss.getAttribute("id").toString();

		String code = request.getParameter("t_code");
		String title = request.getParameter("t_title");
		String kind = request.getParameter("t_kind");
		String mid = request.getParameter("t_mid");
		String content = request.getParameter("content");
		String t_request_period_s = request.getParameter("start_request_period");
		String t_request_period_e = request.getParameter("end_request_period");
		String s_period = request.getParameter("service_period");
		String age = request.getParameter("selectValue");
		String gender = request.getParameter("gender");
		String personnel = request.getParameter("personnel");
		String etc = request.getParameter("etc");
		String t_fileName = request.getParameter("t_fileName");
		Take t = new Take();
		gtDao = new GiveAndTakeDao();
		if(gtDao.groupUpdate(code, content, t_request_period_s, t_request_period_e, s_period, gender, age, personnel, etc, t_fileName)){
			msg = "수정하였습니다.";
			request.setAttribute("msg", msg);
			fw=new Forward();
			fw.setPath("/tgDetail?t_kind="+kind);
			fw.setRedirect(false);
		}
	}
	//============================ 기부받기 삭제
	private void TgroupDelete() {
		String code = request.getParameter("t_code");
		String mid = request.getParameter("t_mid");
		String kind = request.getParameter("t_kind");
		String id = ss.getAttribute("id").toString();
		gtDao = new GiveAndTakeDao();
		System.out.println(code);
		System.out.println(mid);
		if(id.equals(mid) || id.equals("admin")){
			gtDao.PrequestDelete(code);
			if(gtDao.groupDelete(code)){
				System.out.println("삭제 성공");
			}
			else{
				System.out.println("삭제 실패");
			}
			msg = "삭제되었습니다.";
			request.setAttribute("msg", msg);
		}else{
			msg = "작성자만 삭제할 수 있습니다.";
			request.setAttribute("msg", msg);
		}
		if(kind.equals("단체")){
			TgroupSelectG();
			fw = new Forward();
			fw.setPath("/toDetailG?t_code="+code);
			fw.setRedirect(false);
		}else if(kind.equals("개인")){
			TgroupSelectP();
			fw = new Forward();
			fw.setPath("/toDetailP?t_code="+code);
			fw.setRedirect(false);
		}
	}
	//============================ 목록 자세히
	private void TgroupResult() {
		String code = request.getParameter("t_code");
		String kind = request.getParameter("t_kind");
		fw=new Forward();
		fw.setRedirect(false);
		if(ss.getAttribute("id")!=null){
			gtDao = new GiveAndTakeDao();
			System.out.println(code);
			Take t = new Take();
			t = gtDao.groupResult(code);
			int count = gtDao.countSelect(code);
			System.out.println(count);
			System.out.println(kind);
			if(kind.equals("단체")){
				htmlStr = groupDetail(t, count);
			}else if(kind.equals("개인")){
				htmlStr = personDetail(t, count);
			}

			//====================조회수 올리기
			String sql="UPDATE TAKE SET T_HITS=(T_HITS+1) WHERE T_CODE=?";
			if(gtDao.hitsupdate(sql, code)){
				System.out.println("조회수 증가 성공");
			}else{
				System.out.println("조회수 증가 실패");
			}
			//=====================댓글 리스트 불러오기
			sql="SELECT * FROM COMMENTS WHERE c_tcode=?";
			ArrayList<Comment> cList=gtDao.commentselect(sql,code);
			if(cList.size()!=0){
				html=makehtml_comment(cList, kind);
				request.setAttribute("commentList", html);
			}
			request.setAttribute("html", htmlStr);
			fw.setPath("tgDetail.jsp?t_kind="+kind);
		}else{
			request.setAttribute("msg", "로그인 후 이용 가능합니다.");
			if(kind.equals("단체"))
				fw.setPath("/toDetailG");
			else if (kind.equals("개인"))
				fw.setPath("/toDetailP");
		}
	}
	private String personDetail(Take t, int count) {
		StringBuilder sb=new StringBuilder();
		sb.append("<table id='essay' >");
		sb.append("<tr><td class='col'>제목</td><td class='con'>"+ t.getT_title()+"</td><td class='col'>이름</td><td class='con'>"+t.getTm_name()+"</td></tr>");
		sb.append("<tr><td class='col'>연락처</td><td class='con'>"+t.getTm_phone()+"</td><td class='col'>성별</td><td class='con'>"+t.getTm_gender()+"</td></tr>");
		sb.append("<tr><td class='col'>신청 기간</td><td class='con'>"+t.getT_request_period_s() + " - " + t.getT_request_period_e()+"</td><td class='col'>봉사 기간</td><td class='con'>"+t.getT_service_period()+"</td></tr>");
		sb.append("<tr><td class='col'>재능 기부자 성별</td><td class='con'>"+t.getT_gender()+"</td><td class='col'>재능 기부자 나이</td><td class='con'>"+t.getT_age()+"</td></tr>");
		sb.append("<tr><td colspan=4 class='con'>"
				+ "<p>필요 기부 내용 : "+t.getT_content()+"</p>"
				+ "<p>모집 인원 : "+t.getT_personnel()+" 명 | 모집 현황: "+count+"/"+t.getT_personnel()+"</p>"
				+ "</td></tr>");
		sb.append("</table><p>");
		sb.append("<a href='/requestList?t_mid=" + t.getT_mid() + "&t_code="+ t.getT_code() +"&t_kind="+t.getT_kind()+"'><input type='button' name='requestDo' value='신청자 보기'/></a>");
		sb.append("<a href='/requestDo?t_mid=" + t.getT_mid() + "&t_code="+ t.getT_code() +"&t_kind="+t.getT_kind()+"&count="+count+"&t_personnel="+t.getT_personnel()+"'><input type='button' value='신청하기'/></a>");
		sb.append("<a href='/tgDelete?t_code=" + t.getT_code() + "&t_mid="+ t.getT_mid() +"&t_kind="+t.getT_kind()+"'><input type='button' value='삭제'/></a>");
		sb.append("<a href='/tgchange?t_code=" + t.getT_code() + "&t_title="+ t.getT_title() +"&t_kind="+t.getT_kind()+"&t_mid="+ t.getT_mid() +"'><input type='button' value='수정'/></a>");
		sb.append("<a href='/toDetailP'><input type='button' value='목록'/></a></p>");
		sb.append("<p><form action='/takeComment?t_code="+t.getT_code()+"&t_kind="+t.getT_kind()+"' method='post'>");
		sb.append("<input type='text' name='comment'/>");
		sb.append("<input type='submit' value='댓글등록'/></form></p>");
		return sb.toString();
	}
	private String groupDetail(Take t, int count) {
		StringBuilder sb=new StringBuilder();
		sb.append("<table id='essay' >");
		sb.append("<tr><td class='col'>제목</td><td class='con'>"+ t.getT_title()+"</td><td class='col'>기관명</td><td class='con'>"+t.getT_mid()+"</td></tr>");
		sb.append("<tr><td class='col'>연락처</td><td class='con'>"+t.getTm_phone()+"</td><td class='col'>담당자</td><td class='con'>"+t.getTm_name()+"</td></tr>");
		sb.append("<tr><td class='col'>신청 기간</td><td class='con'>"+t.getT_request_period_s() + " - " + t.getT_request_period_e()+"</td><td class='col'>봉사 기간</td><td class='con'>"+t.getT_service_period()+"</td></tr>");
		sb.append("<tr><td class='col'>재능 기부자 성별</td><td class='con'>"+t.getT_gender()+"</td><td class='col'>재능 기부자 나이</td><td class='con'>"+t.getT_age()+"</td></tr>");
		sb.append("<tr><td colspan=4 class='con'>"
				+ "<p><img src='upload" +"/" + t.getT_filename() + "' width='200px'></p>"
				+ "<p>필요 기부 내용 : "+t.getT_content()+"</p>"
				+ "<p>모집 인원 : "+t.getT_personnel()+" 명 | 모집 현황: "+count+"/"+t.getT_personnel()+"</p>"
				+ "</td></tr>");
		sb.append("</table><p>");
		sb.append("<a href='/requestList?t_mid=" + t.getT_mid() + "&t_code="+ t.getT_code() +"&t_kind="+t.getT_kind()+"'><input type='button' name='requestDo' value='신청자 보기'/></a>");
		sb.append("<a href='/requestDo?t_mid=" + t.getT_mid() + "&t_code="+ t.getT_code() +"&t_kind="+t.getT_kind()+"&count="+count+"&t_personnel="+t.getT_personnel()+"'><input type='button' value='신청하기'/></a>");
		sb.append("<a href='/tgDelete?t_code=" + t.getT_code() + "&t_mid="+ t.getT_mid() +"&t_kind="+t.getT_kind()+"'><input type='button' value='삭제'/></a>");
		sb.append("<a href='/tgchange?t_code=" + t.getT_code() + "&t_title="+ t.getT_title() +"&t_kind="+t.getT_kind()+"&t_mid="+ t.getT_mid() +"'><input type='button' value='수정'/></a>");
		sb.append("<a href='/toDetailG'><input type='button' value='목록'/></a></p>");
		sb.append("<p><form action='/takeComment?t_code="+t.getT_code()+"&t_kind="+t.getT_kind()+"' method='post'>");
		sb.append("<input type='text' name='comment'/>");
		sb.append("<input type='submit' value='댓글등록'/></form></p>");

		return sb.toString();
	}
	//============================ 목록 검색
	private void TgroupSelectG() {
		String kind = "단체";
		gtDao = new GiveAndTakeDao();
		ArrayList<Take> List = null;
		List = gtDao.selectTake(kind);

		String list = null;
		list = makeHtml(List, kind);
		request.setAttribute("html", list);
		request.setAttribute("kind", kind);      
		fw = new Forward();
		fw.setPath("takeGroup.jsp");
		fw.setRedirect(false);
	}
	private void TgroupSelectP() {
		String kind = "개인";
		gtDao = new GiveAndTakeDao();
		ArrayList<Take> List = null;
		List = gtDao.selectTake(kind);

		String list = null;
		list = makeHtml(List, kind);
		request.setAttribute("html", list);
		request.setAttribute("kind", kind);      
		fw = new Forward();
		fw.setPath("takeGroup.jsp");
		fw.setRedirect(false);
	}

	private String makeHtml(ArrayList<Take> List, String t_kind) {
		StringBuilder sa=new StringBuilder();
		sa.append("<table>");
		sa.append("<tr><th class='col'>번호</th><th class='col'>제목</th><th class='col'>작성자</th><th class='col'>작성일</th><th class='col'>조회수</th></tr>");
		for(int a=0; a < List.size(); a++){
			Take t= List.get(a);
			sa.append("<tr><td class='con'>"+ (a+1) +"</td>");
			sa.append("<td class='con'><a href='/tgDetail?t_code=" + t.getT_code() + "&t_kind="+t_kind+"'>" + t.getT_title() + "</a></td>");
			sa.append("<td class='con'>"+ t.getT_mid() +"</td>");
			sa.append("<td class='con'>"+ t.getT_date() +"</td>");
			sa.append("<td class='con'>"+ t.getT_hits() +"</td></tr>");
		}
		sa.append("</table>");
		sa.append("<input type='button' id='write' onclick='groupWrite()' value='글쓰기'/>");
		return sa.toString();
	}
	//=============================== 기부받기(단체) insert
	private void TgroupInput() {
		int size = 10 * 1024 * 1024;   //1MB
		String uploadPath = request.getRealPath("upload");   //업로드 폴더 만듬.
		//String uploadPath = request.getSession().getServletContext().getRealPath("test");

		try {
			MultipartRequest multi = new MultipartRequest(
					request, 
					uploadPath, //업로드 경로
					size,    //업로드 이미지 최대크기
					"UTF-8", 
					//a.jsp / a.jsp -> 이름이 똑같을 때 어떡해 관리 할 것 이냐.
					//a.jsp, a1.jsp ...
					new DefaultFileRenamePolicy()
					);
			String id = ss.getAttribute("id").toString();
			String kind = multi.getParameter("addKind");
			System.out.println("1111");
			System.out.println(kind);
			String title = multi.getParameter("title");
			String content = multi.getParameter("content");
			String t_request_period_s = multi.getParameter("start_request_period");
			String t_request_period_e = multi.getParameter("end_request_period");
			String s_period = multi.getParameter("service_period");
			String gender = multi.getParameter("gender");
			String age = multi.getParameter("ageArea");
			String personnel = multi.getParameter("personnel");
			String etc = multi.getParameter("etc");
			String t_fileName = multi.getFilesystemName("t_fileName");

			Take t = new Take(id, kind, title, content, t_request_period_s, t_request_period_e, s_period, gender, age, personnel, etc, t_fileName);
			gtDao = new GiveAndTakeDao();
			gtDao.insertTake(t);

			TgroupSelectG();
			fw = new Forward();
			fw.setPath("/toDetailG");
			fw.setRedirect(false);
		} catch (IOException e) {
			System.out.println("업로드 실패");
			e.printStackTrace();
		}
	}
	private void TpersonInput() {
		Take t=new Take();
		t.setT_mid( ss.getAttribute("id").toString());
		t.setT_kind(request.getParameter("addKind"));
		t.setT_title(request.getParameter("title"));
		t.setT_content(request.getParameter("content"));
		t.setT_request_period_s(request.getParameter("start_request_period"));
		t.setT_request_period_e(request.getParameter("end_request_period"));
		t.setT_service_period(request.getParameter("service_period"));
		t.setT_gender(request.getParameter("gender"));
		t.setT_age(request.getParameter("ageArea"));
		t.setT_personnel(request.getParameter("personnel"));
		t.setT_etc(request.getParameter("etc"));
		gtDao = new GiveAndTakeDao();
		gtDao.insertTake(t);

		TgroupSelectP();
		fw = new Forward();
		fw.setPath("/toDetailP");
		fw.setRedirect(false);
	}

	private void TakeComment() {
		String t_code=request.getParameter("t_code");
		String t_kind=request.getParameter("t_kind");
		String content=request.getParameter("comment");
		//==============세션에 담아놓은 아이디값 가져오기=========================
		String id=ss.getAttribute("id").toString();
		AdminGTDao gtd;
		gtDao = new GiveAndTakeDao();
		gtd = new AdminGTDao();
		String sql = "INSERT INTO COMMENTS VALUES('C'||C_SEQ.NEXTVAL,?,DEFAULT,?,'',?,'',0)";
		String sql2 = "UPDATE TAKE SET T_HITS=(T_HITS-1) WHERE T_CODE=?";

		if(gtDao.commentinsert(sql, t_code,id,content)){
			System.out.println("insert 성공");
			if(gtd.hitsupdate(sql2,t_code)){
				System.out.println("update 성공");
			}
		}else{
			System.out.println("insert 실패");
		}
		gtDao.close();
		fw=new Forward();
		fw.setRedirect(false);
		fw.setPath("/tgDetail?t_code="+t_code+"&t_kind="+t_kind);
	}

	private String makehtml_comment(ArrayList<Comment> cList, String kind) {
		StringBuilder sb=new StringBuilder();
		sb.append("<table id='dd'><tr><td>작성자</td><td>내용</td><td>날짜</td><td>삭제</td></tr>");
		for(int i=0; i<cList.size(); i++){
			Comment c=cList.get(i);
			sb.append("<tr><td>"+c.getC_mid()+"</td>");
			sb.append("<td>"+c.getC_content()+"</td>");
			sb.append("<td>"+c.getC_date()+"</td>");
			if(c.getC_tcode()!=null)
				sb.append("<td><a href='/tcDelete?code="+c.getC_code()+"&t_code=" + c.getC_tcode() + "&t_kind="+kind+"&id="+c.getC_mid()+"'><input type='button' value='삭제'/></a></td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}
	private void commentDelete() {
		String c_mid=request.getParameter("id");
		String id=ss.getAttribute("id").toString();
		String t_code=request.getParameter("t_code");
		String code=request.getParameter("code");
		String kind=request.getParameter("t_kind");
		String sql="DELETE FROM COMMENTS WHERE C_CODE=?";
		System.out.println(code);
		System.out.println(t_code);
		gtDao=new GiveAndTakeDao();
		if(id.equals(c_mid) || id.equals("admin")){
			if(gtDao.dbdelete(sql,code)){
				System.out.println("삭제 성공");
			}
			else{
				System.out.println("삭제 실패");
			}
		}else{
			request.setAttribute("msg", "작성자만 삭제할 수 있습니다.");
		}
		gtDao.close();
		fw=new Forward();
		fw.setRedirect(false);
		fw.setPath("/tgDetail?t_code="+t_code+"&t_kind="+kind);

	}
}//class End