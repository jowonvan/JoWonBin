package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import bean.Comment;
import bean.Request;
import bean.Take;

public class GiveAndTakeDao {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds; //異붽�
	public GiveAndTakeDao(){
		try{
			Context init=new InitialContext();
			ds=(DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
			con=ds.getConnection();
			System.out.println("커넥션연결 성공");
		}catch(Exception ex){
			System.out.println("커넥션연결 실패");
		}
	}
	public void close(){  
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean insertTake(Take t) {
		String sql = "INSERT INTO TAKE "
				+ "VALUES('T'||T_SEQ.NEXTVAL,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT, 0)";
		int result = 0;
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, t.getT_mid());
			pstmt.setNString(2, t.getT_kind());
			pstmt.setNString(3, t.getT_title());
			System.out.println(t.getT_content());
			pstmt.setNString(4, t.getT_content());
			pstmt.setNString(5, t.getT_request_period_s());
			pstmt.setNString(6, t.getT_request_period_e());
			pstmt.setNString(7, t.getT_service_period());
			pstmt.setNString(8, t.getT_gender());
			pstmt.setNString(9, t.getT_age());
			pstmt.setNString(10, t.getT_personnel());
			pstmt.setNString(11, t.getT_etc());
			pstmt.setNString(12, t.getT_filename());
			result = pstmt.executeUpdate();
			if(result != 0){
				System.out.println("Take insert 성공");
				return true;	//상품 등록 성공
			}
		} catch(SQLException e){
			System.out.println("Take insert 실패");
			e.printStackTrace();
		} close();
		return false;
	}
	public ArrayList<Take> selectTake(String kind) {
		String sql = "SELECT * FROM TAKE WHERE T_KIND= ? ORDER BY T_DATE DESC";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, kind);
			rs = pstmt.executeQuery();
			ArrayList<Take> tList = new ArrayList<Take>();
			while(rs.next()){
				Take tBean = new Take();
				tBean.setT_code(rs.getNString("t_code"));
				tBean.setT_title(rs.getNString("t_title"));
				tBean.setT_mid(rs.getNString("t_mid"));
				tBean.setT_kind(rs.getNString("t_kind"));				
				tBean.setT_date(rs.getNString("t_date"));
				tBean.setT_hits(rs.getInt("t_hits"));
				tList.add(tBean);
			}
			System.out.println("검색 성공");
			return tList;
		} catch (SQLException e) {
			System.out.println("검색 실패");
			e.printStackTrace();
		}finally{
			close();
		}return null;

	}
	public Take groupResult(String code) {
		String sql = "SELECT * FROM TAKE INNER JOIN MEMBERS ON T_MID = M_ID WHERE T_CODE = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, code);
			rs = pstmt.executeQuery();
			Take t = null;
			while(rs.next()){
				t = new Take();
				t.setT_code(rs.getNString("t_code"));
				t.setT_title(rs.getNString("t_title"));
				t.setT_mid(rs.getNString("t_mid"));
				t.setT_kind(rs.getNString("t_kind"));
				t.setT_content(rs.getNString("t_content"));
				t.setT_request_period_s(rs.getNString("t_request_period_s"));
				t.setT_request_period_e(rs.getNString("t_request_period_e"));
				t.setT_service_period(rs.getNString("t_service_period"));
				t.setT_gender(rs.getNString("t_gender"));
				t.setT_age(rs.getNString("t_age"));
				t.setT_personnel(rs.getNString("t_personnel"));
				t.setT_etc(rs.getNString("t_etc"));
				t.setT_filename(rs.getNString("t_filename"));
				t.setTm_phone(rs.getNString("m_phone"));
				t.setTm_name(rs.getNString("m_name"));
				t.setTm_email(rs.getNString("m_email"));
				t.setTm_gender(rs.getNString("m_gender"));
			}
			return t;
		} catch (SQLException e) {
			System.out.println("sql 연동실패");
			e.printStackTrace();
		}finally{
			close();
		}return null;
	}
	public ArrayList<Take> searchTakeT(String t_title, String t_kind) {
		String sql = "SELECT * FROM TAKE WHERE T_KIND = ? AND T_TITLE LIKE '%'||?||'%'";
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, t_kind);
			pstmt.setNString(2, t_title);
			rs = pstmt.executeQuery();
			ArrayList<Take> tList = new ArrayList<Take>();
			while(rs.next()){
				Take tBean = new Take();
				tBean.setT_code(rs.getNString("t_code"));
				tBean.setT_title(rs.getNString("t_title"));
				tBean.setT_mid(rs.getNString("t_mid"));
				tBean.setT_date(rs.getNString("t_date"));
				tBean.setT_hits(rs.getInt("t_hits"));
				tList.add(tBean);
			}
			return tList;
		} catch (SQLException e) {
			System.out.println("sql �삁�쇅");
			e.printStackTrace();
		}finally{
			close();
		}return null;
	}

	public ArrayList<Take> searchTakeI(String t_mid, String t_kind) {
		String sql = "SELECT * FROM TAKE WHERE T_MID = ? AND T_KIND =?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, t_mid);
			pstmt.setNString(2, t_kind);
			rs = pstmt.executeQuery();
			ArrayList<Take> tList = new ArrayList<Take>();
			while(rs.next()){
				Take tBean = new Take();
				tBean.setT_code(rs.getNString("t_code"));
				tBean.setT_title(rs.getNString("t_title"));
				tBean.setT_mid(rs.getNString("t_mid"));
				tBean.setT_date(rs.getNString("t_date"));
				tBean.setT_hits(rs.getInt("t_hits"));
				tList.add(tBean);
			}
			return tList;
		} catch (SQLException e) {
			System.out.println("sql �삁�쇅");
			e.printStackTrace();
		}finally{
			close();
		}return null;
	}
	public boolean groupDelete(String code) {
		try {
			System.out.println(code);
			String sql="DELETE FROM TAKE WHERE T_CODE = ?";
			System.out.println(code);
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, code);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return false;
	}
	public boolean groupUpdate(String code, String content, String t_request_period_s, String t_request_period_e, String s_period,
			String gender, String age, String personnel, String etc, String t_fileName) {
		String sql="UPDATE TAKE"
				+ " SET t_content = ?, t_request_period_S = ?, t_request_period_E=?, t_service_period = ?, t_gender = ?, t_age = ?, t_personnel = ?, t_etc = ?, t_filename = ?"
				+ " WHERE T_CODE = ?";
		Take t = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, content);
			pstmt.setNString(2, t_request_period_s);
			pstmt.setNString(3, t_request_period_e);
			pstmt.setNString(4, s_period);
			pstmt.setNString(5, gender);
			pstmt.setNString(6, age);
			pstmt.setNString(7, personnel);
			pstmt.setNString(8, etc);
			pstmt.setNString(9, t_fileName);
			pstmt.setNString(10, code);
			int count=pstmt.executeUpdate();
			if(count != 0){
				System.out.println("update 성공");
				return true;
			}
		} catch (SQLException e) {
			System.out.println("update 실패");
			e.printStackTrace();
		}close();
		return false;
	}
	public ArrayList<Request> searchRequest(String code) {
		String sql = "SELECT * FROM REQUEST WHERE Q_TCODE = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, code);
			rs = pstmt.executeQuery();
			ArrayList<Request> rList = new ArrayList<Request>();
			while(rs.next()){
				Request rBean = new Request();
				rBean.setQ_code(rs.getNString("q_code"));
				rBean.setQ_mid(rs.getNString("q_mid"));
				rBean.setQ_tcode(rs.getNString("q_tcode"));
				rBean.setQ_progress(rs.getInt("q_progress"));
				rList.add(rBean);
			}
			return rList;
		} catch (SQLException e) {
			System.out.println("sql 성공");
			e.printStackTrace();
		}finally{
			close();
		}return null;
	}
	public boolean requestAgree(int i, String q_code) {
		String sql="UPDATE REQUEST"
				+ " SET Q_PROGRESS = ?"
				+ " WHERE Q_CODE = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, i);
			pstmt.setNString(2, q_code);
			int count=pstmt.executeUpdate();
			if(count != 0){
				System.out.println("update 성공");

				return true;
			}

		} catch (SQLException e) {
			System.out.println("update 실패");
			e.printStackTrace();
		}close();
		return false;
	}
	public boolean requestDelete(String q_code) {
		try {
			String sql="DELETE FROM REQUEST WHERE Q_CODE = ?";
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, q_code);
			int count = pstmt.executeUpdate();
			if(count != 0){
				System.out.println("delete 성공");
				return true;
			}
		} catch (SQLException e) {
			System.out.println("delete 실패");
			e.printStackTrace();
		}finally{
			close();
		}
		return false;
	}

	public boolean requestSelect(Request q) {
		String sql = "SELECT * FROM REQUEST WHERE Q_TCODE = ? AND Q_MID = ?";
		System.out.println("?");
		try {
			System.out.println("??");
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, q.getQ_tcode());
			pstmt.setNString(2, q.getQ_mid());
			rs = pstmt.executeQuery();
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			System.out.println("sql 성공");
			e.printStackTrace();
		}finally{
			close();
		}
		return false;
	}
	public boolean requestInsert(Request q) {
		String sql = "INSERT INTO REQUEST "
				+ "VALUES('Q'||Q_SEQ.NEXTVAL,?, ?, DEFAULT)";
		int result = 0;
		System.out.println(q.getQ_mid());
		try{
			con=ds.getConnection();
			System.out.println("진입성공");
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, q.getQ_mid());
			pstmt.setNString(2, q.getQ_tcode());
			result = pstmt.executeUpdate();
			if(result != 0){
				System.out.println("REQUEST insert 성공");
				return true;	//상품 등록 성공
			}
		} catch(SQLException e){
			System.out.println("REQUEST insert 실패");
			e.printStackTrace();
		} close();
		return false;
	}
	public boolean PrequestDelete(String t_code) {
		try {
			String sql="DELETE FROM REQUEST WHERE Q_TCODE = ?";
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, t_code);
			int count = pstmt.executeUpdate();
			if(count != 0){
				System.out.println("delete 성공");
				return true;
			}
		} catch (SQLException e) {
			System.out.println("delete 실패");
			e.printStackTrace();
		}finally{
			close();
		}
		return false;
	}
	public Integer countSelect(String code) {
		String sql = "SELECT COUNT(*) COUNT FROM REQUEST WHERE Q_TCODE=? AND (Q_PROGRESS = 1 OR Q_PROGRESS = 3)";
		int total_count =0;
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, code);
			rs = pstmt.executeQuery();
			rs.next();
			total_count = rs.getInt("count");
			return total_count;
		} catch (SQLException e) {
			System.out.println("sql 실패");
			e.printStackTrace();
		}finally{
			close();
		}
		return total_count;
	}
	public boolean commentinsert(String sql, String code, String id, String content) {
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, id);
			pstmt.setNString(2, content);
			pstmt.setNString(3, code);
			int cnt=pstmt.executeUpdate();
			if(cnt!=0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}

		return false;
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
		}close();
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
			Comment c=null;
			while(rs.next()){
				c=new Comment();
				c.setC_code(rs.getNString("c_code"));
				c.setC_mid(rs.getNString("c_mid"));
				c.setC_date(rs.getNString("c_date"));
				c.setC_content(rs.getNString("c_content"));
				c.setC_gcode(rs.getNString("c_gcode"));
				c.setC_rcode(rs.getNString("c_rcode"));
				c.setC_tcode(rs.getNString("c_tcode"));
				cList.add(c);
			}return cList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}close();

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
			e.printStackTrace();
		}close();
		return false;
	}
	public String selectKind(String id) {
		String sql = "SELECT M_KIND FROM MEMBERS WHERE M_ID= ?";
		String kind = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				kind = rs.getNString("m_kind");
			}
			System.out.println("검색 성공");
			return kind;
		} catch (SQLException e) {
			System.out.println("검색 실패");
			e.printStackTrace();
		}finally{
			close();
		}return null;

	}
	public boolean requestPoint(String code) {
		String sql= "UPDATE MEMBERS"
				+ " SET M_POINT = (M_POINT + 50)"
				+ " WHERE M_ID = (SELECT Q_MID " 
				+ " FROM REQUEST"
				+ " WHERE Q_TCODE=?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, code);
			int count=pstmt.executeUpdate();
			if(count != 0){
				System.out.println("update 성공");

				return true;
			}

		} catch (SQLException e) {
			System.out.println("update 실패");
			e.printStackTrace();
		}close();
		return false;
	}
}
