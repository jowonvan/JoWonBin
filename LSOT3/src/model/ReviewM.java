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
import bean.Review;
import bean.Take;
import dao.AdminGTDao;
import dao.GiveAndTakeDao;
import dao.ReviewDao;

public class ReviewM {
	HttpServletRequest request;
	HttpServletResponse response;
	Forward fw;
	ReviewDao rDao;
	AdminGTDao gtd;
	HttpSession ss;


	public ReviewM(HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
		ss=request.getSession();
	}
	public Forward execute(int code) {
		switch(code){
		case 1:
			rMain(); break;
		case 2:
			insert(); break;
		case 3:
			reviewDetail(); break;
		case 4:
			reviewChange(); break;
		case 5:
			reviewComment(); break;
		case 6:
			reviewDelete(); break;
		case 7:
			commentDelete(); break;
		case 8:
			rMain(); break;
		case 9:
			rtitleBtn(); break;
		case 10:
			ridBtn(); break;
		default:   
		}
		return fw;
	}


	private void ridBtn() {
		String t_mid = request.getParameter("inputSerch");
		rDao= new ReviewDao();
		ArrayList<Review> List = null;
		String sql="SELECT * FROM REVIEW WHERE R_MID LIKE '%'||?||'%'";
		List = rDao.searchTake(sql,t_mid);

		String list = null;
		int page=1;
		list = makeReview(List,page);
		request.setAttribute("html", list);
		fw = new Forward();
		fw.setPath("review.jsp");
		fw.setRedirect(false);

	}
	private void rtitleBtn() {
		String g_title = request.getParameter("inputSerch");
		rDao = new ReviewDao();
		ArrayList<Review> List = null;
		String sql="SELECT * FROM REVIEW WHERE R_TITLE LIKE '%'||?||'%'";
		List = rDao.searchTake(sql,g_title);
		String list = null;
		int page=1;
		list = makeReview(List,page);
		request.setAttribute("html", list);
		fw = new Forward();
		fw.setPath("review.jsp");
		fw.setRedirect(false);

	}
	private void commentDelete() {
		String code=request.getParameter("code");
		String codekind=request.getParameter("codekind");
		String sql="DELETE FROM COMMENTS WHERE C_CODE=?";
		System.out.println(code);
		System.out.println(codekind);
		gtd=new AdminGTDao();
		if(gtd.dbdelete(sql,code)){
			System.out.println("삭제 성공");
		}
		else{
			System.out.println("삭제 실패");
		}
		gtd.close();
		fw=new Forward();
		fw.setRedirect(false);
		fw.setPath("/reviewPage?code="+codekind);
	}
	private void reviewDelete() {
		String code = request.getParameter("code");

		System.out.println(code);

		gtd=new AdminGTDao();
		String r_mid=request.getParameter("id");
		String id=ss.getAttribute("id").toString();
		if(r_mid.equals(id) || id.equals("admin")){
			if(code.startsWith("R")){
				String sql="DELETE FROM REVIEW WHERE R_CODE=?";
				if(gtd.dbdelete(sql,code)){
					System.out.println("삭제 성공");

					fw=new Forward();
					fw.setRedirect(false);
					fw.setPath("/reviewMain");
				}
				else
					System.out.println("삭제 실패");
			}
			gtd.close();
		}else{
			request.setAttribute("msg", "삭제할 권한이 없습니다.");
			fw=new Forward();
			fw.setRedirect(false);
			fw.setPath("/reviewMain");
			
		}

	}
	private void reviewComment() {
		System.out.println("리뷰코멘트 이동");

		String id=ss.getAttribute("id").toString();
		String title=request.getParameter("title");
		String code=request.getParameter("code");
		String content=request.getParameter("comment");

		System.out.println(title);

		//@@@@@라디오버튼 별점 넣기
		int rdvalue = Integer.parseInt(request.getParameter("star"));
		System.out.println("rdvalue"+rdvalue);
		///////////////@@@@@@@@@@@@@@




		gtd=new AdminGTDao();
		String sql="INSERT INTO COMMENTS VALUES('C'||C_SEQ.NEXTVAL,?,DEFAULT,?,'','',?,?)";
		if(gtd.commentinsert(sql,code,id,content,rdvalue)){
			System.out.println("insert 성공");
		}else{
			System.out.println("insert 실패");
		}
		fw=new Forward();
		fw.setRedirect(false);
		fw.setPath("/reviewPage?code="+code+"&title="+title);

	}
	private void reviewChange() {
		System.out.println("리뷰체인지에러");
		ReviewDao rDao = new ReviewDao();

		try {
			int size = 10 * 1024 * 1024;   //1MB
			String uploadPath = request.getRealPath("upload");   //업로드 폴더 만듬.

			MultipartRequest mult = new MultipartRequest(

					request, 
					uploadPath, //업로드 경로
					size,    //업로드 이미지 최대크기
					"UTF-8", 
					new DefaultFileRenamePolicy()
					);

			System.out.println("uploadPath:"+uploadPath); //그냥
			//String code =;


			String code = request.getParameter("code");

			String title = mult.getParameter("rc_title");
			String name = mult.getParameter("rc_name");
			String when = mult.getParameter("rc_when");
			String content = mult.getParameter("rc_content");
			String feeling = mult.getParameter("rc_feeling");
			String place = mult.getParameter("rc_place");

			String filename1 = mult.getFilesystemName("rc_filename1");
			String filename2 = mult.getFilesystemName("rc_filename2");
			String filename3 = mult.getFilesystemName("rc_filename3");       


			rDao.changeReview(code,title,name,when,content,feeling,place,filename1,filename2,filename3);
			fw = new Forward();
			fw.setPath("/reviewMain");
			fw.setRedirect(false);

		} catch (IOException e) {
			System.out.println("업로드 에러");
			e.printStackTrace();
		}
	}

	private void reviewDetail() {

		if(ss.getAttribute("id")==null){
			request.setAttribute("msg", "로그인 후 이용 가능합니다.");
			fw=new Forward();
			fw.setRedirect(false);
			fw.setPath("/reviewMain");
		}else{
			String code = request.getParameter("code");
			String html=null;
			String kind=null;

			System.out.println("code:"+code);

			rDao = new ReviewDao();

			ArrayList<Review> list2 = rDao.reviewDetail(code);
			String List2 = detailReview(list2);
			request.setAttribute("rdList", List2);

			//===================조회수 올리기
			rDao = new ReviewDao();
			String sql="UPDATE REVIEW SET R_HITS=(R_HITS+1) WHERE R_CODE=?";
			if(rDao.hitsupdate(sql,code)){
				System.out.println("조회수 증가 성공");
			}else{
				System.out.println("조회수 증가 실패");
			}
			//-===================

			gtd=new AdminGTDao();
			sql="SELECT * FROM COMMENTS WHERE c_rcode=?";
			ArrayList<Comment> cList=gtd.commentselect(sql,code);
			if(cList.size()!=0){
				html=makehtml_comment(cList,kind);
				request.setAttribute("commentList", html);
			}

			fw = new Forward();
			fw.setPath("reviewDetail.jsp");
			fw.setRedirect(false);
		}
	}

	private String makehtml_comment(ArrayList<Comment> cList, String kind) {
		StringBuilder sb=new StringBuilder();
		sb.append("<table id='dd'><tr><td>별점</td><td>작성자</td><td>내용</td><td>날짜</td><td>삭제</td></tr>");
		for(int i=0; i<cList.size(); i++){
			Comment c=cList.get(i);

			//여기서 밸류값을 가지고 이미지를 뽑아낸다 RSTAR

			int rstar;
			String starIMG = null;

			rstar=c.getRstar();

			if(rstar==1){ 
				starIMG="star1.PNG";
			}else if(rstar==2){
				starIMG="star2.PNG";
			}else if(rstar==3){
				starIMG="star3.PNG";
			}else if(rstar==4){
				starIMG="star4.PNG";
			}else if(rstar==5){
				starIMG="star5.PNG";
			}else{
				starIMG="nostar.PNG";
			}

			System.out.println(starIMG);






			sb.append("<tr><td><img src='img/"+starIMG+"'></td>");
			sb.append("<td>"+c.getC_mid()+"</td>");
			sb.append("<td>"+c.getC_content()+"</td>");
			sb.append("<td>"+c.getC_date()+"</td>");
			if(c.getC_rcode()!=null){
				//sb.append( "<td><input type='button' value='삭제' id='commentDelete' onclick=\"Ajd('rcDelete','code="+c.getC_code()+"&codekind="+c.getC_rcode()+"','#section')\"/></td></tr>" );
				sb.append("<td><a href='./rcDelete?code="+c.getC_code()+"&codekind=" +c.getC_rcode()+ "'><input type='button' value='삭제'/></a></td></tr>");
			}
			sb.append("</tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}
	private void rMain() {

		int page = Integer.parseInt((String)request.getParameter("page"));
		System.out.println("리뷰page번호:"+page);


		rDao = new ReviewDao();
		ArrayList<Review> list = rDao.listReview();

		String List = makeReview(list,page);
		request.setAttribute("List", List);

		fw = new Forward();
		fw.setPath("review.jsp");
		fw.setRedirect(false);

	}

	private String detailReview(ArrayList<Review> list2) {


		StringBuilder sb=new StringBuilder();
		Review r=list2.get(0);


		sb.append("<form name='rvinfoForm' method='post' >");

		sb.append("<table id='essay' >");

		sb.append("<tr><td class='col'>제목</td><td colspan=3 id='tdtle'>"+r.getR_title()+"</td></tr>");



		sb.append("<tr><td class='col'>이름</td><td class='con'>"+r.getR_name()+"</td><td class='col'>언제</td><td class='con'>"+r.getR_when()+"</td></tr>");
		sb.append("<tr><td class='col'>기부내용</td><td class='con'>"+r.getR_content()+"</td><td class='col'>장소</td><td class='con'>"+r.getR_place()+"</td></tr>");


		sb.append("<tr><td colspan=4 class='con'><p>내용  "+r.getR_feeling()+"</p><p><img src='upload" +"/" +r.getR_filename1() + "' width = '500'></p>"
				+ "<p><img src='upload" +"/" +r.getR_filename2() + "' width = '500'></p>"
				+ "<p><img src='upload" +"/" +r.getR_filename3() + "' width = '500'></p>"
				+ "</td></tr>");



		sb.append("</table><p>");
		sb.append("<a href='./reviewDelete?page=1&code="+r.getR_code()+"&id="+r.getR_mid()+"'><input type='button' value='삭제'/></a>");
		sb.append("<a href='./reviewChangePage?page=1&code="+r.getR_code()+"&title="+r.getR_title()+"&when="+r.getR_when()+"&name="+r.getR_name()+
				"&feeling="+r.getR_feeling()+"&filename1="+r.getR_filename1()+"&filename2="+r.getR_filename2()+"&filename3="+r.getR_filename3()+
				"&content="+r.getR_content()+"&place="+r.getR_place()+"'><input type='button' value='수정'/></a>");

		sb.append("<a href='./reviewMain?page=1'><input type='button' value='목록'/></a>");

		sb.append("</p></form>");



		sb.append("<p><form name='commentForm' method='post'>");
		sb.append("<span class='star-input'><span class='input'>"
				+ "<input type='radio' name='star' id='p1' value='1' onclick=''><label for='p1'>1</label>"
				+ "<input type='radio' name='star' id='p2' value='2' ><label for='p2'>2</label>"
				+ "<input type='radio' name='star' id='p3' value='3' ><label for='p3'>3</label>"
				+ "<input type='radio' name='star' id='p4' value='4' ><label for='p4'>4</label>"
				+ "<input type='radio' name='star' id='p5' value='5' ><label for='p5'>5</label>"
				+ "<input type='hidden' name='star' value='0'>"
				+"</span>"
				+ "</span>");

		sb.append("<input type='text' style='width:25%;height:20px;margin-left:2%;margin-right:2%;' placeholder='댓글을 달아주세요' name='comment'/>");
		sb.append("<input type='hidden' name='code' value='"+r.getR_code()+"'/>");
		sb.append("<input type='hidden' name='title' value='"+r.getR_title()+"'/>");
		sb.append("<input type='button' value='댓글등록' onclick='commentbtn()'/></form></p>");




		return sb.toString();

	}

	private String makeReview(ArrayList<Review> list, int page) {


		StringBuilder sa=new StringBuilder();
		sa.append("<table>");
		sa.append("<tr><th class='num'>번호</th><th class='photo'>사진</th><th class='title'>제목</th><th class='uploader'>등록자</th><th class='time'>작성일</th><th class='hits'>조회수</th></tr>");
		////////////////////////////////////////////////

		int num;
		int count;		
		int limit = 0;
		String date=null;
		//  num 게시글번호 limit 게시글페이지제한갯수 count 페이지번호 @@@@@



		limit=list.size();

		if(list.size()<6){
			count=1;
		}else if(list.size()<11){
			count=2;
			limit=list.size();
		}else if(list.size()<16){
			count=3;
			limit=list.size();
		}else{
			count=4;
		}

		if(page==1){
			for(num=0; num<limit && num<5; num++)
			{
				Review r=list.get(num);
				date=r.getR_date().substring(0, 10);
				sa.append("<tr><td class='num'>"+(num+1)+"</td>");
				sa.append("<td class='photo'><img src='upload" +"/" +r.getR_filename1() + "'></td>");	
				sa.append("<td class='title'><a href='reviewPage?title="+r.getR_title()+"&code="+r.getR_code()+"&page=1'>"+r.getR_title()+"</a></td>");
				sa.append("<td class='uploader'>"+r.getR_mid()+"</td>");
				sa.append("<td class='time'>"+date+"</td>");
				sa.append("<td class='hits'>"+r.getR_hits()+"</td></tr>");
				//빈에있는 r_hits받아서 와야한다
			}	

		}
		else if(page==2){
			for(num=5; num<limit && num<10; num++)
			{
				Review r=list.get(num);
				date=r.getR_date().substring(0, 10);
				sa.append("<tr><td class='num'>"+(num+1)+"</td>");
				sa.append("<td class='photo'><img src='upload" +"/" +r.getR_filename1() + "'></td>");	
				sa.append("<td class='title'><a href='reviewPage?title="+r.getR_title()+"&code="+r.getR_code()+"'$page=1'>"+r.getR_title()+"</a></td>");
				sa.append("<td class='uploader'>"+r.getR_mid()+"</td>");
				sa.append("<td class='time'>"+date+"</td>");
				sa.append("<td class='hits'>"+r.getR_hits()+"</td></tr>");
				//빈에있는 r_hits받아서 와야한다
			}	


			//	limit=list.size()-num;
		}else if(page==3){

			for(num=10; num<limit && num<15; num++)
			{
				Review r=list.get(num);
				date=r.getR_date().substring(0, 10);
				sa.append("<tr><td class='num'>"+(num+1)+"</td>");
				sa.append("<td class='photo'><img src='upload" +"/" +r.getR_filename1() + "'></td>");	
				sa.append("<td class='title'><a href='reviewPage?title="+r.getR_title()+"&code="+r.getR_code()+"'&page=1'>"+r.getR_title()+"</a></td>");
				sa.append("<td class='uploader'>"+r.getR_mid()+"</td>");
				sa.append("<td class='time'>"+date+"</td>");
				sa.append("<td class='hits'>"+r.getR_hits()+"</td></tr>");
				//빈에있는 r_hits받아서 와야한다
			}	
		}

		sa.append("</table>");
		sa.append("<p>");



		for(int a=1;a<=count;a++){
			sa.append("[<a href='table?page="+a+"'>"+a+"</a>] ");
		} 
		sa.append("<input type='button' value='리뷰 작성하기' id='review' onclick='reviewAdd()'>");
		sa.append("</p>");



		return sa.toString();
	}   


	private void insert() {
		ReviewDao rDao = new ReviewDao();

		try {
			int size = 10 * 1024 * 1024;   //1MB
			String uploadPath = request.getRealPath("upload");   //업로드 폴더 만듬.

			MultipartRequest multi = new MultipartRequest(

					request, 
					uploadPath, //업로드 경로
					size,    //업로드 이미지 최대크기
					"UTF-8", 
					new DefaultFileRenamePolicy()
					);
			System.out.println("uploadPath:"+uploadPath); //그냥

			String mid = ss.getAttribute("id").toString();

			String title = multi.getParameter("r_title");
			String name = multi.getParameter("r_name");
			String when = multi.getParameter("r_when");
			String content = multi.getParameter("r_content");
			String place = multi.getParameter("r_place");
			String feeling = multi.getParameter("r_feeling");

			String filename1 = multi.getFilesystemName("r_filename1");
			String filename2 = multi.getFilesystemName("r_filename2");
			String filename3 = multi.getFilesystemName("r_filename3");

			/*      HttpSession session = request.getSession();
         String id = session.getAttribute("id").toString();*/
			Review review = new Review(mid, title, name, when, content, place, feeling, filename1, filename2, filename3);

			/*HttpSession ss = request.getSession();
         ss.setAttribute("r_mid", mid);*/

			fw = new Forward();
			if(rDao.insertReview(review)){
				System.out.println("상품등록 성공");
			}else{
				System.out.println("상품등록 실패");
			}

			fw.setPath("/reviewMain");
			fw.setRedirect(false);

		} catch (IOException e) {
			System.out.println("업로드 에러");
			e.printStackTrace();
		}
	}
}//class End


