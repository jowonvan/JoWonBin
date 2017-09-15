package com.steppe.nomad.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.steppe.nomad.bean.Member;
import com.steppe.nomad.bean.Project;
import com.steppe.nomad.bean.Project_bookmark;
import com.steppe.nomad.bean.Reply;
import com.steppe.nomad.bean.Report;
import com.steppe.nomad.dao.ProjectDao;
import com.steppe.nomad.dao.Project_bookmarkDao;
import com.steppe.nomad.dao.ReportDao;

@Service
public class ReportManagement {
   
   @Autowired
   private ReportDao rDao;
   
   private ModelAndView mav;

	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletRequest request;

	public ModelAndView execute(int cmd) {
		switch(cmd){
		case 1:
			showProjectList1();
			break;
		case 2:
			showProjectList2();
			break;
		}
		return mav;
	}
	
	@Autowired
	private ProjectDao pDao;
	
	@Autowired
	private Project_bookmarkDao pbDao;
	
	
	//신고 작성 페이지로 이동
	public ModelAndView goReportWrite() {
		mav = new ModelAndView();
		String view=null;
		String m_id=(String) session.getAttribute("m_id");
		
		if(m_id==null){
			view="redirect:/";
			mav.setViewName(view);
		}
		if(m_id!=null){
			
				String user=request.getParameter("m_id");
				String reportUrl=request.getHeader("REFERER");
				System.out.println("이전주소:"+reportUrl);
				System.out.println("신고유저명:"+user);
				mav.addObject("user2",user);
				mav.addObject("reportUrl",reportUrl);
				view="reportWrite";
				mav.setViewName(view);
			
			
		}
		return mav;
	}


   //신고작성
   public ModelAndView InsertReport(Report report) {
      mav = new ModelAndView();
      String view=null;
      
      String m_id=(String) session.getAttribute("m_id");
      report.setR_mid(m_id);
      String r_url=request.getParameter("r_url");
      report.setR_url(r_url);
      String r_kind=request.getParameter("r_kind");
      report.setR_kind(r_kind);
      String r_content=request.getParameter("r_contents");
      report.setR_content(r_content);
      System.out.println(m_id);
      int r_num = rDao.getMaxNum()+1;
      report.setR_num(r_num);
      
      rDao.insertReport(report);
         view="redirect:/goFreelancer";
         mav.setViewName(view);
      
   
      
      return mav;
   }
   //신고 리스트 표출
   public ModelAndView showReportList() {
      mav=new ModelAndView();
      String view=null;
      String m_id=(String)session.getAttribute("m_id");
      String m_kind=(String)session.getAttribute("m_kind");
      System.out.println(m_kind);
      List<Report>rlist=null;
      rlist =rDao.getReportList();
      if(rlist!=null){
         if(m_id.equals("admin")){
            StringBuilder sb=new StringBuilder();
            sb.append("<div class='container' style='text-align:center;'>");
            sb.append("<table class='table table-striped' style='text-align:center; color:black;'");
            sb.append("<tr>");
            sb.append("<th style='text-align:center;'>"+"글 번호"+"</th>");
            sb.append("<th style='text-align:center;'>신고자</th>");
            sb.append("<th style='text-align:center;'>"+"신고종류"+"</th>");
            sb.append("<th style='text-align:center;'>"+"신고내용"+"</th>");
            sb.append("<th style='text-align:center;'>"+"신고대상 주소 "+"</th>");
            sb.append("<th style='text-align:center;'>"+"삭제"+"</th>");
            for(int i=0; i<rlist.size(); i++){
               Report r=rlist.get(i);
               sb.append("<tr>");
               sb.append("<td>"+r.getR_num()+"</td>");
               sb.append("<td>"+r.getR_mid()+"</td>");
               sb.append("<td>"+r.getR_kind()+"</td>");
               sb.append("<td>"+r.getR_content()+"</td>");
               sb.append("<td>"+"<a href="+r.getR_url()+">"+"페이지 이동"+"</a>"+"</td>");
               sb.append("<td>"+"<a href='deleteReport?r_num="+r.getR_num()+"'>"+"<input type='button' value='삭제'>"+"</a>"+"</td>");
               sb.append("</tr>");
            }
            sb.append("</table>");
            sb.append("</div>");
            view="report";
            mav.addObject("rlist",sb.toString());
            mav.setViewName(view);
         }
         else{
            view="home";
            mav.setViewName(view);
         }
      }

      return mav;
   }

   public ModelAndView showProjectList() {
      mav=new ModelAndView();
      String view=null;
      List<Project> plist=null;
      List<Project_bookmark> pblist = null;
      List<Project_bookmark> allPblist = null;
      String mid = null;
      
      plist=rDao.getProjectList();
      allPblist = pbDao.allBookmarkList();
      if(session.getAttribute("m_id") != null){
         StringBuilder sb=new StringBuilder();
         mid = session.getAttribute("m_id").toString();
         pblist = pbDao.bookmarkList(mid);
         System.out.println("plist사이즈:"+plist.size());
         System.out.println("pblist사이즈:"+pblist.size());
      sb.append("<div class='container'>");
      sb.append("<div class='row'>");
      sb.append("<form action='searchProjectList' id='search' class='pull-right'>");
      sb.append("<input type='text' id='keyword'  name='keyword' placeholder='프로젝트명을 입력하세요'/>");
      sb.append("<input type='button' id='searchBtn' value='검색'>");
      sb.append("</form>");
      sb.append("</div>");
      //for(int i=plist.size()-1; i>=0; i--){
      for(int i=0; i<plist.size(); i++){
         Project p=plist.get(i);
         System.out.println("title:"+p.getP_title());
         String p1=p.getP_plnum0();
            String p2=p.getP_plnum1();
            String p3=p.getP_plnum2();
			Project_bookmark loginPb = null;
			Project_bookmark pb = null;
			System.out.println("몇번도는지...");
			/*if(pblist.size() != 0){
				pb = pblist.get(i);
			}*/
			sb.append("<div class='col-sm-4 col-lg-4 col-md-4'>");
			sb.append("<div class='thumbnail'>");
			sb.append("<a href='goProjectDetail?p_num="+p.getP_num()+"'>");
			sb.append("<img style='height:250px; width:100%;' src='resources/upload/"+p.getP_filename()+"' alt=''>");
			sb.append("</a>");
			sb.append("<div class='caption'>");
			sb.append("<div><h4><a href='goProjectDetail?p_num="+p.getP_num()+"'>"+p.getP_title()+"</a>");
			System.out.println("index2:"+i);
			for(int j=i; j<i+1; j++){
				loginPb = allPblist.get(j);
				System.out.println("몇번실행 ");
				System.out.println("title:"+p.getP_title());
				if(loginPb.getPb_mid().equals(session.getAttribute("m_id")))
					if(loginPb.getPb_flag() != 0){
						//sb.append("<a href='#' style='float:right' id='bookmarkBtn' onclick='javascript:bookmarkOn(\""+p.getP_num()+"\")'><img id='bookmarkImg"+p.getP_num()+"' src='resources/img/on.png' />");
						sb.append("<a href='#' style='float:right' id='bookmarkBtn' onclick='javascript:bookmarkOn(\""+p.getP_num()+"\")'>");
					}else{
						//sb.append("<a href='#' style='float:right' id='bookmarkBtn' onclick='javascript:bookmarkOn(\""+p.getP_num()+"\")'><img id='bookmarkImg"+p.getP_num()+"' src='resources/img/off.png' />");
						sb.append("<a href='#' style='float:right' id='bookmarkBtn' onclick='javascript:bookmarkOn(\""+p.getP_num()+"\")'>");
				}else{
					sb.append("<a href='#' style='float:right' id='bookmarkBtn' onclick='javascript:bookmarkOn(\""+p.getP_num()+"\")'>");
					//sb.append("<a href='#' style='float:right' id='bookmarkBtn' onclick='javascript:bookmarkOn(\""+p.getP_num()+"\")'><img id='bookmarkImg"+p.getP_num()+"' src='resources/img/off.png' />");
				}
			}
			sb.append("</a></h4></div>");
			sb.append("<span calss='pull-right'>지원자 : "+p.getP_vol()+"명 / 필요 인원 : "+p.getP_person()+"명</span>");
			if(p1==null)
	               sb.append("<p>"+p.getP_plnum1()+" "+p.getP_plnum2()+"</p>");
	            if(p2==null)
	               sb.append("<p>"+p.getP_plnum0()+" "+p.getP_plnum2()+"</p>");
	            if(p3==null)
	               sb.append("<p>"+p.getP_plnum0()+" "+p.getP_plnum1()+"</p>");
	            if(p1==null && p2==null)
	               sb.append("<p>"+p.getP_plnum2()+"</p>");
	            if(p2==null && p3==null)
	               sb.append("<p>"+p.getP_plnum0()+"</p>");
	            if(p1==null && p3==null)
	               sb.append("<p>"+p.getP_plnum1()+"</p>");
	            if(p1!=null && p2!=null && p3!=null)
	            sb.append("<p>"+p.getP_plnum0()+" "+p.getP_plnum1()+" "+p.getP_plnum2()+"</p>");
			sb.append("<span calss='pull-right'>지원 마감 : "+p.getP_deadline()+" 예산 금액 : "+p .getP_budget()+"만원</span>");
			sb.append("</div>");
			sb.append("</div>");
			sb.append("</div>");
		}
		sb.append("</div>");
		mav.addObject("plist",sb.toString());
		
		}else{
			if(plist!=null){
				StringBuilder sb=new StringBuilder();
					//pblist = pbDao.bookmarkList(mid);
				
				sb.append("<div class='container'>");
				sb.append("<div class='row'>");
				sb.append("<form action='searchProjectList' id='search' class='pull-right'>");
				sb.append("<input type='text' id='keyword'  name='keyword' placeholder='프로젝트명을 입력하세요'/>");
				sb.append("<input type='button' id='searchBtn' value='검색'>");
				sb.append("</form>");
				sb.append("</div>");
				for(int i=0; i<plist.size(); i++){
					Project p=plist.get(i);
					String p1=p.getP_plnum0();
		            String p2=p.getP_plnum1();
		            String p3=p.getP_plnum2();
					Project_bookmark pb = null;
					/*if(pblist != null){
						pb = pblist.get(i);
					}*/
					sb.append("<div class='col-sm-4 col-lg-4 col-md-4'>");
					sb.append("<div class='thumbnail'>");
					sb.append("<a href='goProjectDetail?p_num="+p.getP_num()+"'>");
					sb.append("<img style='height:250px; width:100%;' src='resources/upload/"+p.getP_filename()+"' alt=''>");
					sb.append("</a>");
					sb.append("<div class='caption'>");
					//sb.append("<div><h4><a href='goProjectDetail?p_num="+p.getP_num()+"'>"+p.getP_title()+"</a><a href='#' style='float:right' id='bookmarkBtn' onclick='javascript:bookmarkOn(\""+p.getP_num()+"\")'><img id='bookmarkImg' src='resources/img/off.png' />");
					sb.append("<div><h4><a href='goProjectDetail?p_num="+p.getP_num()+"'>"+p.getP_title()+"</a><a href='#' style='float:right' id='bookmarkBtn' onclick='javascript:bookmarkOn(\""+p.getP_num()+"\")'>");
					sb.append("</a></h4></div>");
					sb.append("<span calss='pull-right'>지원자 : "+p.getP_vol()+"명 / 필요 인원 : "+p.getP_person()+"명</span>");
					if(p1==null)
			               sb.append("<p>"+p.getP_plnum1()+" "+p.getP_plnum2()+"</p>");
			            if(p2==null)
			               sb.append("<p>"+p.getP_plnum0()+" "+p.getP_plnum2()+"</p>");
			            if(p3==null)
			               sb.append("<p>"+p.getP_plnum0()+" "+p.getP_plnum1()+"</p>");
			            if(p1==null && p2==null)
			               sb.append("<p>"+p.getP_plnum2()+"</p>");
			            if(p2==null && p3==null)
			               sb.append("<p>"+p.getP_plnum0()+"</p>");
			            if(p1==null && p3==null)
			               sb.append("<p>"+p.getP_plnum1()+"</p>");
			            if(p1!=null && p2!=null && p3!=null)
			            sb.append("<p>"+p.getP_plnum0()+" "+p.getP_plnum1()+" "+p.getP_plnum2()+"</p>");
					sb.append("<span calss='pull-right'>지원 마감 : "+p.getP_deadline()+" 예산 금액 : "+p .getP_budget()+"만원</span>");
					sb.append("</div>");
					sb.append("</div>");
					sb.append("</div>");
				}
				sb.append("</div>");
				mav.addObject("plist",sb.toString());
			
			}
		}
		view="project";
		mav.setViewName(view);
		return mav;
		
	}
	
	public ModelAndView showProjectList1() {
		mav=new ModelAndView();
		String view=null;
		List<Project> plist2=null;
		System.out.println("개발");
		plist2=rDao.getProjectList3();
		System.out.println(plist2);
		if(plist2!=null){
			StringBuilder sb=new StringBuilder();
			sb.append("<div class='container'>");
			sb.append("<div class='row'>");
			sb.append("<form action='searchProjectList' id='search' class='pull-right'>");
			sb.append("<input type='text' id='keyword'  name='keyword' placeholder='프로젝트명을 입력하세요'/>");
			sb.append("<input type='button' id='searchBtn' value='검색'>");
			sb.append("</form>");
			sb.append("</div>");
			for(int i=0; i<plist2.size(); i++){
				Project p=plist2.get(i);
				sb.append("<div class='col-sm-4 col-lg-4 col-md-4'>");
				sb.append("<div class='thumbnail'>");
				sb.append("<a href='goProjectDetail?p_num="+p.getP_num()+"'>");
				sb.append("<img style='height:250px; width:100%;' src='resources/upload/"+p.getP_filename()+"' alt=''>");
				sb.append("</a>");
				sb.append("<div class='caption'>");
				sb.append("<h4>"+"<a href='goProjectDetail?p_num="+p.getP_num()+"'>"+p.getP_title()+"</a></h4>");
				sb.append("<span calss='pull-right'>지원자 : "+p.getP_vol()+"명 / 필요 인원 : "+p.getP_person()+"명</span>");
				sb.append("<p>"+p.getP_plnum0()+" "+p.getP_plnum1()+" "+p.getP_plnum2()+"</p>");
				sb.append("<span calss='pull-right'>지원 마감 : "+p.getP_deadline()+" 예산 금액 : "+p .getP_budget()+"만원</span>");
				sb.append("</div>");
				sb.append("</div>");
				sb.append("</div>");
			}
			sb.append("</div>");
			mav.addObject("plist2",sb.toString());
		}
		view="project";
		mav.setViewName(view);
		return mav;
		
	}
	public ModelAndView showProjectList2() {
		mav=new ModelAndView();
		String view=null;
		List<Project> plist3=null;
		System.out.println("디자인");
		plist3=rDao.getProjectList4();
		if(plist3!=null){
			StringBuilder sb=new StringBuilder();
			sb.append("<div class='container'>");
			sb.append("<div class='row'>");
			sb.append("<form action='searchProjectList' id='search' class='pull-right'>");
			sb.append("<input type='text' id='keyword'  name='keyword' placeholder='프로젝트명을 입력하세요'/>");
			sb.append("<input type='button' id='searchBtn' value='검색'>");
			sb.append("</form>");
			sb.append("</div>");
			for(int i=0; i<plist3.size(); i++){
				Project p=plist3.get(i);
				sb.append("<div class='col-sm-4 col-lg-4 col-md-4'>");
				sb.append("<div class='thumbnail'>");
				sb.append("<a href='goProjectDetail?p_num="+p.getP_num()+"'>");
				sb.append("<img style='height:250px; width:100%;' src='resources/upload/"+p.getP_filename()+"' alt=''>");
				sb.append("</a>");
				sb.append("<div class='caption'>");
				sb.append("<h4>"+"<a href='goProjectDetail?p_num="+p.getP_num()+"'>"+p.getP_title()+"</a></h4>");
				sb.append("<span calss='pull-right'>지원자 : "+p.getP_vol()+"명 / 필요 인원 : "+p.getP_person()+"명</span>");
				sb.append("<p>"+p.getP_plnum0()+" "+p.getP_plnum1()+" "+p.getP_plnum2()+"</p>");
				sb.append("<span calss='pull-right'>지원 마감 : "+p.getP_deadline()+" 예산 금액 : "+p .getP_budget()+"만원</span>");
				sb.append("</div>");
				sb.append("</div>");
				sb.append("</div>");
			}
			sb.append("</div>");
			mav.addObject("plist3",sb.toString());
		}
		view="project";
		mav.setViewName(view);
		return mav;
		
	}
	//프로젝트 상세 페이지
	public ModelAndView showProjcetDetail() {
		mav=new ModelAndView();
		String view=null;
		String m_id=(String) session.getAttribute("m_id");
		if(m_id==null){
			mav.addObject("msg","로그인이 필요한 서비스입니다.");
			view="login";
			mav.setViewName(view);
			
		}
		else{
			//프로젝트 번호
			int p_num=Integer.parseInt(request.getParameter("p_num"));
			rDao.getProjectDetail(p_num);
			mav.addObject("project",rDao.getProjectDetail(p_num));
			showReplyList();
			view="projectDetail";
			mav.setViewName(view);
			
		}
		return mav;
	}
	
	//신고 삭제
	public ModelAndView deleteReport() {
		mav=new ModelAndView();
		String view=null;
		int r_num=Integer.parseInt(request.getParameter("r_num"));
		rDao.deleteReport(r_num);
		view="redirect:/goReportList";
		mav.setViewName(view);
		return mav;
	}
	//프로젝트 검색
	public ModelAndView searchProjectList() {
		mav = new ModelAndView();
		String view=null;
		String keyowrd=request.getParameter("keyword");
		List<Project> splist=null;
		splist=rDao.searchProject(keyowrd);
		if(splist!=null){
			StringBuilder sb = new StringBuilder();
			sb.append("<div class='container'>");
			sb.append("<div class='row'>");
			sb.append("<form action='searchProjectList' id='search' class='pull-right'>");
			sb.append("<input type='text' id='keyword' name='keyword' placeholder='프로젝트명을 입력하세요'/>");
			sb.append("<input type='button' id='searchBtn' value='검색'>");
			sb.append("</form>");
			sb.append("</div>");
			for(int i=0;i<splist.size();i++){
				Project p=splist.get(i);
				sb.append("<div class='col-sm-6 col-lg-6 col-md-6'>");
				sb.append("<div class='thumbnail'>");
				sb.append("<a href='goProjectDetail?p_num="+p.getP_num()+"'>");
				sb.append("<img style='height:250px; width:100%;' src='resources/upload/"+p.getP_filename()+"' alt=''>");
				sb.append("</a>");
				sb.append("<div class='caption'>");
				sb.append("<h4>"+"<a href='goProjectDetail?p_num="+p.getP_num()+"'>"+p.getP_title()+"</a></h4>");
				sb.append("<span calss='pull-right'>"+p.getP_vol()+"</span>");
				sb.append("<p>"+p.getP_plnum0()+" "+p.getP_plnum1()+" "+p.getP_plnum2()+"</p>");
				sb.append("</div>");
				sb.append("</div>");
				sb.append("</div>");
			}
			sb.append("</div>");
			mav.addObject("splist",sb.toString());
		}
		view="project";
		mav.setViewName(view);
		return mav;
   }
   //프로젝트 댓글삽입
   public ModelAndView insertComment(Reply reply) {
      //작성자 아이디
      mav=new ModelAndView();
      String r_mid=(String) session.getAttribute("m_id");
      reply.setR_mid(r_mid);
      //프로젝트 번호
      int r_pnum=Integer.parseInt(request.getParameter("p_num"));
      System.out.println("프로젝트 번호:"+r_pnum);
      reply.setR_pnum(r_pnum);
      //댓글의 내용
      String r_content=request.getParameter("r_content");
      System.out.println("댓글내용:"+r_content);
      reply.setR_content(r_content);
      
      int r_num=rDao.getReplyMaxNum()+1;
      System.out.println("댓글번호:"+r_num);
      reply.setR_num(r_num);
      
      rDao.insertReply(reply);
      
      mav.setViewName("redirect:goProjectDetail?p_num="+r_pnum);
      return mav;
   }
   
   //댓글 표출 메소드
      private void showReplyList() {
         int p_num=Integer.parseInt(request.getParameter("p_num"));
         List<Reply> replyList=null;
         replyList=rDao.showReply(p_num);
         if(replyList!=null){
            StringBuilder sb=new StringBuilder();

            for(int i=0;i<replyList.size();i++){
               Reply r=replyList.get(i);
               sb.append("<tr>");
               sb.append("<td style='text-align:center;'>"+"<input type='hidden'  name='r_mid' value='"+r.getR_mid()+"'/>"+r.getR_mid()+"</td>");
               sb.append("<td colspan='5' style='text-align:center;'>"+r.getR_content()+"</td>");
               sb.append("<td style='text-align:center;'><input type='hidden' name='p_num' value='"+p_num+"'/>"+r.getR_date()+"</td>");
               sb.append("<td>");
               //sb.append("<a href='deleteReply?r_num="+r.getR_num()+"'><input type='button' value='삭제'/></a>");
               sb.append("<input type='button' onclick='deleteReply("+r.getR_num() +")'  value='삭제'/>");
               /*sb.append("<input type='button' value='삭제'/></a>");*/
               sb.append("</td>");
               
               sb.append("</tr>");
               //System.out.println(r.getR_num());
            }
            mav.addObject("p_num", p_num);
            mav.addObject("reply",sb.toString());
         }
      }
      
      
      //댓글 삭제
      public ModelAndView deleteReply() {
         String m_id=(String) session.getAttribute("m_id");
         String r_mid=request.getParameter("r_mid");
         System.out.println("현재접속:"+m_id);
         System.out.println("작성자:"+r_mid);
         int p_num=Integer.parseInt(request.getParameter("p_num"));
         System.out.println(p_num);
         int r_num=Integer.parseInt(request.getParameter("r_num"));
         System.out.println("리플 번호:"+r_num);
         //int p_num=Integer.parseInt(request.getParameter("p_num").trim());
         /*String reportUrl=request.getHeader("REFERER");
         System.out.println(reportUrl);
         String CutreportUrl=reportUrl.substring(reportUrl.lastIndexOf("/")+23, reportUrl.length());
         System.out.println(CutreportUrl);*/
         if(m_id.equals(r_mid)){
            rDao.deleteReply(r_num);
            showReplyList();
            System.out.println("삭제성공");
         }
         else{
            System.out.println("삭제실패");
            
         }
         mav.setViewName("projectDetail");
         
         //mav.setViewName("redirect:goProjectDetail?p_num="+CutreportUrl);
         
         return mav;
      }
      public ModelAndView showHomeList() {
            mav=new ModelAndView();
            String view=null;
            List<Project> hplist=null;
            hplist=rDao.getHomeProjectList();
            List<Member> flist=null;
            flist=rDao.getHomeFreelancerList();
            if(hplist.size()!=0){
               StringBuilder sb=new StringBuilder();
               sb.append("<div class='container'>");
               sb.append("<h1 style='color:black; text-align:center;'>프로젝트</h1>");
               sb.append("<hr/>");
               for(int i=0; i<hplist.size(); i++){
                  if(i<3){
                     Project p=hplist.get(i);
                     sb.append("<div class='col-sm-4 col-lg-4 col-md-4'>");
                     sb.append("<div class='thumbnail'>");
                     sb.append("<a href='goProjectDetail?p_num="+p.getP_num()+"'>");
                     sb.append("<img style='height:250px; width:100%;' src='resources/upload/"+p.getP_filename()+"' alt=''>");
                     sb.append("</a>");
                     sb.append("<div class='caption'>");
                     sb.append("<h4>"+"<a href='goProjectDetail?p_num="+p.getP_num()+"'>"+p.getP_title()+"</a></h4>");
                     sb.append("<span calss='pull-right'>지원자 : "+p.getP_vol()+"명 / 필요 인원 : "+p.getP_person()+"명</span>");
                     sb.append("<p>"+p.getP_plnum0()+" "+p.getP_plnum1()+" "+p.getP_plnum2()+"</p>");
                     sb.append("<span calss='pull-right'>지원 마감 : "+p.getP_deadline()+" 예산 금액 : "+p .getP_budget()+"만원</span>");
                     sb.append("</div>");
                     sb.append("</div>");
                     sb.append("</div>");
                  }
               }
               sb.append("</div>");
               mav.addObject("plist",sb.toString());
            }else{
              mav.addObject("plist"," ");
            }
            if(flist.size()!=0){
               StringBuilder sb=new StringBuilder();
               
               sb.append("<div class='container'>");
               sb.append("<h1 style='color:black; text-align:center;'>프리랜서</h1>");
               sb.append("<hr/>");
               for(int i=0; i<flist.size(); i++){
                  Member f=flist.get(i);
                  sb.append("<div class='col-md-4 col-sm-6 hero-feature'>");
                  sb.append("<div class='thumbnail'>");
                  sb.append("<img src='resources/upload/"+f.getMf_sysname()+"' alt=''>");
                  System.out.println(f.getMf_sysname());
                  sb.append("<div class='caption'>");
                  sb.append("<h3 style='text-align:center;'>"+f.getM_name()+"</h3>");
                  sb.append("<p style='text-align:center;'>"+f.getM_email()+"</p>");
                  sb.append("<p style='text-align:center;'><a style='color:white;' class='btn btn-default' href='goFreelancerDetail?m_id="+f.getM_id()+"'>"+"상세보기"+"</a>"+"</p>");
                  sb.append("</div>");
                  sb.append("</div>");
                  sb.append("</div>");
               }
               sb.append("</div>");
               mav.addObject("flist", sb.toString());
                    
            }else{
              mav.addObject("flist", "");
            }
            view="home";
               mav.setViewName(view);
            return mav;

            

//<<<<<<< HEAD
		   }
		
		public ModelAndView showLb() {
		      String view = null;
		      mav = new ModelAndView();
		      String pt_sysname=request.getParameter("Pt_sysname");
		      System.out.println(rDao.getPortfolioview(pt_sysname));
		      mav.addObject("port",rDao.getPortfolioview(pt_sysname));
		      view="portView";
		      mav.setViewName(view);
		      return mav;
		   }
		
		public String bookmarkOnOff(){
			String jsonObj = null;
			//프로젝트 넘버, 아이디, 플래그
			Map<String, String> map = new HashMap<String, String>();
			//프로젝트 넘버, 아이디 
			Map<String, String> map2 = new HashMap<String, String>();
			int pNum = Integer.parseInt(request.getParameter("pNum"));
			
			map2.put("bmNum", String.valueOf(pNum));
			map2.put("mid", session.getAttribute("m_id").toString());
			//프로젝트 넘버
			//프로젝트 넘버를 이용해서 해당 프로젝트 행을 가져옴
			Project_bookmark pb = pbDao.bookmarkFlag(map2);
			int bookmarkNum = 0;
			map.put("pb_num", String.valueOf(bookmarkNum));			
			map.put("mid", session.getAttribute("m_id").toString());
			map.put("pb_pnum", String.valueOf(pNum));
			
			if(pbDao.bookmarkCount()!=0){
				bookmarkNum = pbDao.bookmarkMaxNum()+1;
			}else{
				bookmarkNum = 1;
			}
			
			if(pb.getPb_flag() != 0){
				pbDao.bookmarkDelete(map2);
			}else{				
				map.put("pb_flag", String.valueOf(1));
				pbDao.bookmarkInsert(map);
			}
			//pbDao.bookmarkUpdate(map);
			
			Project_bookmark pb1 = pbDao.bookmarkSelect(map);
			jsonObj = String.valueOf(pb1.getPb_flag());
			return jsonObj;
		}
	
}
/*=======
         }
      
      public ModelAndView showLb() {
            String view = null;
            mav = new ModelAndView();
            String pt_sysname=request.getParameter("Pt_sysname");
            System.out.println(rDao.getPortfolioview(pt_sysname));
            mav.addObject("port",rDao.getPortfolioview(pt_sysname));
            view="portView";
            mav.setViewName(view);
            return mav;
         }
      
      public String bookmarkOnOff(){
         String jsonObj = null;
         Map<String, String> map = new HashMap<String, String>();
         int bmNum = Integer.parseInt(request.getParameter("bmNum"));
         Project_bookmark pb = pbDao.bookmarkFlag(bmNum);
         if(pb.getPb_flag() != 0){
            map.put("pb_flag", String.valueOf(0));            
         }else{            
            map.put("pb_flag", String.valueOf(1));
         }
         //map.put("pb_pnum", String.valueOf(bmNum));         
         map.put("mid", session.getAttribute("m_id").toString());
         map.put("pb_pnum", String.valueOf(bmNum));
         pbDao.bookmarkUpdate(map);
         Project_bookmark pb1 = pbDao.bookmarkSelect(map);
         jsonObj = String.valueOf(pb1.getPb_flag());
         return jsonObj;
      }
   
}
>>>>>>> 4ad4861b4fea60f8ac70dbb69f5ae01a5afff112
*/