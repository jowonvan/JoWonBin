package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import bean.Comment;
import bean.Give;
import bean.Member;
import bean.Notice;
import bean.Request;
import bean.Review;
import bean.Support;
import bean.Take;

public class AdminGTDao {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs=null;
	DataSource ds;
	ResultSetMetaData rsmd;
	Member m;
	Give g;
	Comment c;
	Take t;
	Notice n;

	public AdminGTDao(){
		try{
			Context init=new InitialContext();
			ds=(DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
			con=ds.getConnection();
		}catch(Exception ex){
			System.out.println("커넥션풀 연결 실패");
		}
	}

	public void close(){	//커넥션풀에 커넥션 반납
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Give> giveselect() {
		String sql="SELECT * FROM GIVE ORDER BY G_DATE DESC";
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<Give> gList=new ArrayList<Give>();
			while(rs.next()){
				g=new Give();
				g.setG_code(rs.getNString("g_code"));
				g.setG_title(rs.getNString("g_title"));
				g.setG_mid(rs.getNString("g_mid"));
				g.setG_date(rs.getNString("g_date"));
				g.setG_hits(rs.getInt("g_hits"));
				gList.add(g);
			}
			return gList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Give givedetail(String g_code) {
		String sql="SELECT * FROM MEMBERS M INNER JOIN GIVE G ON M_ID=G_MID WHERE G_CODE=?";
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1,g_code);
			rs=pstmt.executeQuery();
			if(rs.next()){
				g=new Give();
				g.setG_code(rs.getNString("g_code"));
				g.setG_mid(rs.getNString("g_mid"));
				g.setG_title(rs.getNString("g_title"));
				g.setGm_name(rs.getNString("m_name"));
				g.setGm_phone(rs.getNString("m_phone"));
				g.setGm_email(rs.getNString("m_email"));
				g.setGm_gender(rs.getNString("m_gender"));
				g.setGm_birth(rs.getNString("m_birth"));
				g.setG_bigtalent(rs.getNString("g_bigtalent"));
				g.setG_smalltalent(rs.getNString("g_smalltalent"));
				g.setG_loc(rs.getNString("g_loc"));
				g.setG_period_s(rs.getNString("g_period_s"));
				g.setG_period_e(rs.getNString("g_period_e"));
				g.setG_bigtalent(rs.getNString("g_bigtalent"));
				g.setG_hits(rs.getInt("g_hits"));
				g.setG_content(rs.getNString("g_content"));
				
				return g;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean hitsupdate(String sql,String g_code) {
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, g_code);
			int cnt=pstmt.executeUpdate();
			if(cnt!=0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Comment> commentselect(String sql,String g_code) {
		//String sql="SELECT * FROM COMMENTS WHERE c_gcode=?";
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, g_code);
			rs=pstmt.executeQuery();
			ArrayList<Comment> cList=new ArrayList<Comment>();
			while(rs.next()){
				c=new Comment();
				
				c.setC_code(rs.getNString("c_code"));
				c.setC_mid(rs.getNString("c_mid"));
				c.setC_date(rs.getNString("c_date"));
				c.setC_content(rs.getNString("c_content"));
				c.setC_gcode(rs.getNString("c_gcode"));
				c.setC_rcode(rs.getNString("c_rcode"));
				c.setC_tcode(rs.getNString("c_tcode"));
				
				//별점 저장
				c.setRstar(rs.getInt("rstar"));
				cList.add(c);
			}return cList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public boolean commentinsert(String sql,String code, String id, String content,int rdvalue) {
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, id);
			pstmt.setNString(2, content);
			pstmt.setNString(3, code);
			pstmt.setInt(4, rdvalue);
			int cnt=pstmt.executeUpdate();
			if(cnt!=0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public ArrayList<Take> takeselect(String kind) {
		String sql="SELECT * FROM TAKE WHERE T_KIND=?";
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, kind);
			rs=pstmt.executeQuery();
			ArrayList<Take> tList=new ArrayList<Take>();
			while(rs.next()){
				t=new Take();
				t.setT_code(rs.getNString("t_code"));
				t.setT_mid(rs.getNString("t_mid"));
				t.setT_title(rs.getNString("t_title"));
				t.setT_kind(rs.getNString("t_kind"));
				//t.setT_content(rs.getNString("t_content"));
				//t.setT_request_period_s(rs.getNString("t_request_period_S"));
				//t.setT_request_period_e(rs.getNString("t_request_period_E"));
				//t.setT_service_period(rs.getNString("t_service_period"));
				//t.setT_gender(rs.getNString("t_gender"));
				//t.setT_age(rs.getNString("t_age"));
				//t.setT_personnel(rs.getNString("t_personnel"));
				//t.setT_etc(rs.getNString("t_etc"));
				t.setT_date(rs.getNString("t_date"));
				t.setT_hits(rs.getInt("t_hits"));
				tList.add(t);
			}
			return tList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Take takedetailselect(String t_code) {
		String sql="SELECT * FROM MEMBERS M INNER JOIN TAKE T ON M.M_ID=T.T_MID WHERE T_CODE=?";
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1,t_code);
			rs=pstmt.executeQuery();
			if(rs.next()){
				System.out.println(rs.getNString("m_birth"));
				t=new Take();
				t.setT_code(rs.getNString("t_code"));
				t.setT_mid(rs.getNString("t_mid"));
				t.setT_title(rs.getNString("t_title"));
				t.setTm_name(rs.getNString("m_name"));
				t.setTm_phone(rs.getNString("m_phone"));
				t.setTm_email(rs.getNString("m_email"));
				t.setTm_gender(rs.getNString("m_gender"));
				t.setTm_birth(rs.getNString("m_birth"));
				t.setT_content(rs.getNString("t_content"));
				t.setT_request_period_s(rs.getNString("t_request_period_S"));
				t.setT_request_period_e(rs.getNString("t_request_period_E"));
				t.setT_service_period(rs.getNString("t_service_period"));
				t.setT_gender(rs.getNString("t_gender"));
				t.setT_age(rs.getNString("t_age"));
				t.setT_personnel(rs.getNString("t_personnel"));
				t.setT_etc(rs.getNString("t_etc"));
				if(rs.getNString("t_filename")!=null){
					t.setT_filename(rs.getNString("t_filename"));
				}
				t.setT_date(rs.getNString("t_date"));
				t.setT_hits(rs.getInt("t_hits"));
				t.setT_kind(rs.getNString("t_kind"));
				
				return t;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Notice> noticeselect() {
		String sql="SELECT * FROM NOTICE";
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<Notice> nList=new ArrayList<Notice>();
			while(rs.next()){
				n=new Notice();
				n.setN_code(rs.getNString("n_code"));
				n.setN_title(rs.getNString("n_title"));
				n.setN_content(rs.getNString("n_content"));
				n.setN_date(rs.getNString("n_date"));
				n.setN_hits(rs.getInt("n_hits"));
				nList.add(n);
			}
			return nList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Notice noticedetailselect(String n_code) {
		String sql="SELECT * FROM NOTICE WHERE N_CODE=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, n_code);
			rs=pstmt.executeQuery();
			Notice n=null;
			if(rs.next()){
				n=new Notice();
				n.setN_code(rs.getNString("n_code"));
				n.setN_title(rs.getNString("n_title"));
				n.setN_content(rs.getNString("n_content"));
				n.setN_date(rs.getNString("n_date"));
				n.setN_hits(rs.getInt("n_hits"));
			}
			return n;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean dbdelete(String sql, String code) {
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, code);
			int cnt=pstmt.executeUpdate();
			if(cnt!=0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean noticeinsert(String title, String content) {
		String sql="INSERT INTO NOTICE VALUES('N'||N_SEQ.NEXTVAL,?,?,DEFAULT,0)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, title);
			pstmt.setNString(2, content);
			int cnt=pstmt.executeUpdate();
			if(cnt!=0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean noticeupdate(String code, String title, String content) {
		String sql="UPDATE NOTICE SET N_TITLE=?, N_CONTENT=? WHERE N_CODE=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, title);
			pstmt.setNString(2, content);
			pstmt.setNString(3, code);
			int cnt=pstmt.executeUpdate();
			if(cnt!=0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean pwcheck(String id, String pw) {
		String sql="SELECT * FROM MEMBERS WHERE M_ID=? AND M_PW=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, id);
			pstmt.setNString(2, pw);
			rs=pstmt.executeQuery();
			if(rs.next())
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean pwupdate(String id, String newpw) {
		String sql="UPDATE MEMBERS SET M_PW=? WHERE M_ID=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, newpw);
			pstmt.setNString(2, id);
			int cnt=pstmt.executeUpdate();
			if(cnt!=0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Review> reviewselect() {
		String sql="SELECT * FROM REVIEW";
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<Review> rList=new ArrayList<Review>();
			Review r=null;
			while(rs.next()){
				r=new Review();
				r.setR_code(rs.getNString("r_code"));
				r.setR_title(rs.getNString("r_title"));
				r.setR_mid(rs.getNString("r_mid"));
				r.setR_date(rs.getNString("r_date"));
				r.setR_hits(rs.getInt("r_hits"));
				rList.add(r);
			}
			return rList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Review reviewdetailselect(String r_code) {
		String sql="SELECT * FROM REVIEW WHERE R_CODE=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, r_code);
			rs=pstmt.executeQuery();
			Review r=null;
			if(rs.next()){
				r=new Review();
				r.setR_code(rs.getNString("r_code"));
				r.setR_mid(rs.getNString("r_mid"));
				r.setR_title(rs.getNString("r_title"));
				r.setR_name(rs.getNString("r_name"));
				r.setR_when(rs.getNString("r_when"));
				r.setR_content(rs.getNString("r_content"));
				r.setR_place(rs.getNString("r_place"));
				r.setR_feeling(rs.getNString("r_feeling"));
				r.setR_filename1(rs.getNString("r_filename1"));
				r.setR_filename2(rs.getNString("r_filename2"));
				r.setR_filename3(rs.getNString("r_filename3"));
				r.setR_date(rs.getNString("R_DATE"));
				r.setR_hits(rs.getInt("r_hits"));
			}
			return r;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Support> supportselect(String id) {
		String sql="SELECT * FROM SUPPORT WHERE S_MID=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs=pstmt.executeQuery();
			ArrayList<Support> sList=new ArrayList<Support>();
			Support s;
			while(rs.next()){
				s=new Support();
				s.setS_code(rs.getNString("s_code"));
				s.setS_mid(rs.getNString("s_mid"));
				s.setS_total(rs.getNString("s_total"));
				s.setS_date(rs.getNString("S_DATE"));
				sList.add(s);
			}
			return sList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Request> requestselect(String sql,String id) {
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs=pstmt.executeQuery();
			ArrayList<Request> qList=new ArrayList<Request>();
			Request q;
			while(rs.next()){
				q=new Request();
				q.setQ_code(rs.getNString("q_code"));
				q.setQ_mid(rs.getNString("q_mid"));
				q.setQ_tcode(rs.getNString("q_tcode"));
				q.setQ_progress(rs.getInt("q_progress"));
				q.setQ_ttitle(rs.getNString("t_title"));
				q.setQ_tdate(rs.getNString("t_date"));
				q.setQ_tkind(rs.getNString("t_kind"));
				qList.add(q);
			}
			return qList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean giveinsert(Give g) {
		String sql="INSERT INTO GIVE VALUES('G'||G_SEQ.NEXTVAL,?,?,?,?,?,?,?,DEFAULT,0,?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, g.getG_mid());
			pstmt.setNString(2, g.getG_title());
			pstmt.setNString(3, g.getG_bigtalent());
			pstmt.setNString(4, g.getG_smalltalent());
			pstmt.setNString(5, g.getG_loc());
			pstmt.setNString(6, g.getG_period_s());
			pstmt.setNString(7, g.getG_period_e());
			pstmt.setNString(8, g.getG_content());
			int cnt=pstmt.executeUpdate();
			if(cnt!=0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean giveupdate(Give g) {
		String sql="UPDATE GIVE SET g_bigtalent=?,g_smalltalent=?,g_loc=?,g_period_S=?,g_period_E=? WHERE G_CODE=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, g.getG_bigtalent());
			pstmt.setNString(2, g.getG_smalltalent());
			pstmt.setNString(3, g.getG_loc());
			pstmt.setNString(4, g.getG_period_s());
			pstmt.setNString(5, g.getG_period_e());
			pstmt.setNString(6, g.getG_code());
			int cnt=pstmt.executeUpdate();
			if(cnt!=0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Give> searchTake(String sql, String g_title) {
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, g_title);
			rs = pstmt.executeQuery();
			ArrayList<Give> tList = new ArrayList<Give>();
			while(rs.next()){
				Give gBean = new Give();
				gBean.setG_code(rs.getNString("g_code"));
				gBean.setG_title(rs.getNString("g_title"));
				gBean.setG_mid(rs.getNString("g_mid"));
				gBean.setG_date(rs.getNString("g_date"));
				gBean.setG_hits(rs.getInt("g_hits"));
				tList.add(gBean);
			}
			return tList;
		} catch (SQLException e) {
			System.out.println("sql �삁�쇅");
			e.printStackTrace();
		}finally{
			close();
		}return null;
	}
}//class end
