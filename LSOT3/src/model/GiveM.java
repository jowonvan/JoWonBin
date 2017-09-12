package model;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Age;
import bean.Comment;
import bean.Forward;
import bean.Give;
import bean.Take;
import dao.AdminGTDao;
import dao.GiveAndTakeDao;

public class GiveM {
	private HttpServletRequest request;
	private HttpServletResponse response;
	HttpSession ss;
	Forward fw;
	AdminGTDao gtd;
	String html;
	Give g;

	public GiveM(HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
		ss=request.getSession();
	}

	public Forward execute(int i) {
		switch(i){
		case 1:
			giveList();break;
		case 2:
			giveAddPage();break;
		case 3:
			giveDetail();break;
		case 4:
			giveComment();break;
		case 5:
			giveDelete();break;
		case 6:
			giveUpdate();break;
		case 7:
			commentDelete();break;
		case 8:
			searchGiveT();break;
		case 9:
			searchGiveI();break;
		default:
			break;
		}
		return fw;
	}

	private void searchGiveI() {
		String t_mid = request.getParameter("inputSerch");
		gtd= new AdminGTDao();
		ArrayList<Give> List = null;
		String sql="SELECT * FROM GIVE WHERE G_MID LIKE '%'||?||'%'";
		List = gtd.searchTake(sql,t_mid);

		String list = null;
		list = makehtml_give(List);
		request.setAttribute("html", list);
		gtd.close();
		fw = new Forward();
		fw.setPath("give.jsp");
		fw.setRedirect(false);

	}

	private void searchGiveT() {
		String g_title = request.getParameter("inputSerch");
		gtd = new AdminGTDao();
		ArrayList<Give> List = null;
		String sql="SELECT * FROM GIVE WHERE G_TITLE LIKE '%'||?||'%'";
		List = gtd.searchTake(sql,g_title);
		String list = null;
		list = makehtml_give(List);
		request.setAttribute("html", list);
		gtd.close();
		fw = new Forward();
		fw.setPath("give.jsp");
		fw.setRedirect(false);

	}

	private String makeHtml_GT(ArrayList<Give> list) {
		StringBuilder sa=new StringBuilder();
		sa.append("<table border='1' width = '500px'>");
		sa.append("<tr><td>번호</td><td>제목</td><td>등록자</td><td>작성일</td><td>조회수</td></tr>");
		for(int a=0; a < list.size(); a++){
			Give g= list.get(a);
			sa.append("<tr><td>"+ (a+1) +"</td>");
			sa.append("<td><a href='/giveDetail?code=" + g.getG_code() +"'>" + g.getG_title() + "</a></td>");
			sa.append("<td>"+ g.getG_mid() +"</td>");
			sa.append("<td>"+ g.getG_date() +"</td>");
			sa.append("<td>"+ g.getG_hits() +"</td></tr>");
		}
		sa.append("</table>");
		return sa.toString();
	}

	private void commentDelete() {
		String code=request.getParameter("code");
		String codekind=request.getParameter("codekind");
		String g_mid=request.getParameter("id");
		String id=ss.getAttribute("id").toString();
		String sql="DELETE FROM COMMENTS WHERE C_CODE=?";
		System.out.println(code);
		System.out.println(codekind);
		gtd=new AdminGTDao();
		if(id.equals(g_mid) || id.equals("admin")){
			if(gtd.dbdelete(sql,code)){
				System.out.println("삭제 성공");
			}
			else{
				System.out.println("삭제 실패");
			}
		}else{
			request.setAttribute("msg", "삭제할 권한이 없습니다.");
		}
		gtd.close();
		fw=new Forward();
		fw.setRedirect(false);
		fw.setPath("/giveDetail?code="+codekind);

	}

	private void giveUpdate() {
		g=new Give();
		g.setG_code(request.getParameter("code"));
		g.setG_title(request.getParameter("title"));
		System.out.println(g.getG_code());
		g.setG_bigtalent(request.getParameter("first"));
		g.setG_smalltalent(request.getParameter("second"));
		g.setG_loc(request.getParameter("location"));
		g.setG_period_s(request.getParameter("DateStart"));
		g.setG_period_e(request.getParameter("DateEnd"));
		g.setG_content(request.getParameter("content"));

		gtd=new AdminGTDao();
		if(gtd.giveupdate(g)){
			System.out.println("업데이트 실패");
		}else{
			System.out.println("업데이트 실패");
		}
		gtd.close();
		fw=new Forward();
		fw.setRedirect(false);
		fw.setPath("/giveDetail?code="+g.getG_code()+"&title="+g.getG_title());
	}

	private void giveDelete() {
		String code=request.getParameter("code");
		gtd=new AdminGTDao();
		fw=new Forward();
		fw.setRedirect(false);
		String sql="DELETE FROM GIVE WHERE G_CODE=?";
		String g_mid=request.getParameter("id");
		String id=ss.getAttribute("id").toString();
		if(id.equals(g_mid) || id.equals("admin")){
			if(gtd.dbdelete(sql, code)){
				fw.setPath("/giveMain");
				request.setAttribute("msg", "삭제 성공");
			}else{
				fw.setPath("/giveDetail?code="+code);
				request.setAttribute("msg", "삭제 실패");
			}
		}else{
			request.setAttribute("msg", "삭제할 권한이 없습니다.");
			fw.setPath("/giveDetail?code="+code);
		}
		gtd.close();
	}

	private void giveComment() {
		String code=request.getParameter("code");
		String content=request.getParameter("comment");
		//@@rdvalue
		int rdvalue = 0;
		//==============세션에 담아놓은 아이디값 가져오기=========================
		String id=ss.getAttribute("id").toString();
		gtd=new AdminGTDao();
		String sql="INSERT INTO COMMENTS VALUES('C'||C_SEQ.NEXTVAL,?,DEFAULT,?,?,'','',?)";
		if(gtd.commentinsert(sql,code,id,content,rdvalue)){
			System.out.println("insert 성공");
		}else{
			System.out.println("insert 실패");
		}
		gtd.close();
		fw=new Forward();
		fw.setRedirect(false);
		fw.setPath("/giveDetail?code="+code);
	}

	private void giveDetail() {
		String g_code=request.getParameter("code");
		System.out.println(g_code);
		String g_title=request.getParameter("title");
		System.out.println(g_title);
		fw=new Forward();
		fw.setRedirect(false);
		if(ss.getAttribute("id")==null){
			request.setAttribute("msg", "로그인 후 이용 가능합니다.");
			fw.setPath("/giveMain");
		}else{
			gtd=new AdminGTDao();
			//=====================give상페내역 가져오기
			g=gtd.givedetail(g_code);
			System.out.println(g.getG_title());
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
				html=makehtml_comment(cList);
				request.setAttribute("commentList", html);
			}
			gtd.close();
			fw.setPath("giveDetail.jsp");
		}
	}

	private String makehtml_comment(ArrayList<Comment> cList) {
		StringBuilder sb=new StringBuilder();
		sb.append("<table id='dd'><tr><td>작성자</td><td>내용</td><td>날짜</td><td>삭제</td></tr>");
		for(int i=0; i<cList.size(); i++){
			Comment c=cList.get(i);
			sb.append("<tr><td>"+c.getC_mid()+"</td>");
			sb.append("<td>"+c.getC_content()+"</td>");
			sb.append("<td>"+c.getC_date()+"</td>");
			if(c.getC_gcode()!=null)
				//sb.append( "<td><input type='button' value='삭제' id='commentDelete' onclick=\"Ajd('gcDelete','code="+c.getC_code()+"&codekind="+c.getC_gcode()+"','#section')\"/></td></tr>" );
				sb.append("<td><a href='/gcDelete?code="+c.getC_code()+"&codekind=" + c.getC_gcode() + "&id="+c.getC_mid()+"'><input type='button' value='삭제'/></a></td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}

	private String makehtml_gdetail(Give g) {
		StringBuilder sb=new StringBuilder();
		Age a=new Age();
		int age=a.operAge(g.getGm_birth());

		sb.append("<table id='essay' >");
		sb.append("<tr><td class='col'>제목</td><td class='con'>"+g.getG_title()+"</td><td class='col'>이름</td><td class='con'>"+g.getGm_name()+"</td></tr>");
		sb.append("<tr><td class='col'>연락처</td><td class='con'>"+g.getGm_phone()+"</td><td class='col'>이메일</td><td class='con'>"+g.getGm_email()+"</td></tr>");
		sb.append("<tr><td class='col'>나이</td><td class='con'>"+age+"</td><td class='col'>성별</td><td class='con'>"+g.getGm_gender()+"</td></tr>");
		sb.append("<tr><td colspan=4 class='con'>"
				+ "<p>재능 분야 : "+g.getG_bigtalent()+"-"+g.getG_smalltalent()+"</p>"
				+ "<p>재능 설명 : "+g.getG_content()+"</p>"
				+ "<p>희망 지역 : "+g.getG_loc()+"</p>"
				+ "<p>희망 기간 : "+g.getG_period_s()+"~"+g.getG_period_e()+"</p>"
				+ "</td></tr>");
		sb.append("</table><p>");
		sb.append("<a href='/givechange?code="+g.getG_code()+"&title="+g.getG_title()+"&id="+g.getG_mid()+"'><input type='button' value='수정'/></a>");
		sb.append("<a href='/givedelete?code="+g.getG_code()+"&id="+g.getG_mid()+"'><input type='button' value='삭제'/></a>");
		sb.append("<a href='/giveMain?'><input type='button' value='목록'/></a></p>");
		sb.append("<p><form action='/givecomment?code="+g.getG_code()+"' method='post'>");
		sb.append("<input type='text' style='width:25%;height:20px;margin-left:2%;margin-right:2%;' placeholder='댓글을 달아주세요' name='comment'/>");
		sb.append("<input type='submit' value='댓글등록'/></form></p>");
		return sb.toString();
	}

	private void giveAddPage() {
		g=new Give();
		g.setG_mid(ss.getAttribute("id").toString());
		g.setG_title(request.getParameter("title"));
		g.setG_bigtalent(request.getParameter("first"));
		g.setG_smalltalent(request.getParameter("second"));
		g.setG_loc(request.getParameter("location"));
		g.setG_period_s(request.getParameter("DateStart"));
		g.setG_period_e(request.getParameter("DateEnd"));
		g.setG_content(request.getParameter("content"));

		gtd=new AdminGTDao();
		if(gtd.giveinsert(g)){
			System.out.println("insert 성공");
			request.setAttribute("msg", "등록 성공하였습니다.");
		}else{
			System.out.println("insert 실패");
			request.setAttribute("msg", "등록 실패하였습니다.");
		}
		gtd.close();
		fw=new Forward();
		fw.setRedirect(false);
		fw.setPath("/giveMain");
	}

	private void giveList() {
		gtd=new AdminGTDao();
		ArrayList<Give> gList=gtd.giveselect();
		html=makehtml_give(gList);
		request.setAttribute("giveList", html);
		gtd.close();
		fw=new Forward();
		fw.setRedirect(false);
		fw.setPath("give.jsp");
	}

	private String makehtml_give(ArrayList<Give> gList) {
		StringBuilder sb=new StringBuilder();
		sb.append("<h2>기부 리스트</h2>");
		sb.append("<table><tr><th class='col'>번호</th><th class='col'>제목</th><th class='col'>작성자</th><th class='col'>작성일</th><th class='col'>조회수</th></tr>");
		for(int i=0; i<gList.size(); i++){
			Give g=gList.get(i);
			sb.append("<tr><td class='con'>"+(i+1)+"</td>");
			sb.append("<td class='con'><a href='/giveDetail?code="+g.getG_code()+"'>"+g.getG_title()+"</a></td>");
			//sb.append("<td><a href='#' onclick=\"Ajd('giveDetail','code="+g.getG_code()+"','#section')\">"+g.getG_title()+"</a></td>");
			sb.append("<td class='con'>"+g.getG_mid()+"</td>");
			sb.append("<td class='con'>"+g.getG_date()+"</td>");
			sb.append("<td class='con'>"+g.getG_hits()+"</td></tr>");
		}
		sb.append("</table>");
		sb.append("<a id='write' href='./giveAdd'><input type='button' value='글쓰기'/></a>");
		//sb.append( "<input type='button' value='글쓰기' id='giveAddPage'/>" );
		return sb.toString();
	}

}//class End
