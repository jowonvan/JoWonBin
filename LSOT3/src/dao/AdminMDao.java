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

import bean.Give;
import bean.Member;
import bean.Support;

public class AdminMDao {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs=null;
	DataSource ds;
	ResultSetMetaData rsmd;
	Member m;
	Give g;

	public AdminMDao(){
		try{
			Context init=new InitialContext();
			ds=(DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
			con=ds.getConnection();
		}catch(Exception ex){
			System.out.println("而ㅻ꽖�뀡�� �뿰寃� �떎�뙣");
		}
	}

	public void close(){	//而ㅻ꽖�뀡���뿉 而ㅻ꽖�뀡 諛섎궔
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Member> memberselect(String sql) {
		
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<Member> mList = new ArrayList<Member>();
			while(rs.next()){
				m=new Member();
				m.setM_id(rs.getNString("m_id"));
				m.setM_name(rs.getNString("m_name"));
				m.setM_phone(rs.getNString("m_phone"));
				mList.add(m);
			}
			return mList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Member selectMdetail(String id) {
		String sql="SELECT * FROM MEMBERS WHERE M_ID=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				m=new Member();
				m.setM_id(rs.getNString("m_id"));
				m.setM_name(rs.getNString("m_name"));
				m.setM_phone(rs.getNString("m_phone"));
				m.setM_email(rs.getNString("m_email"));
				m.setM_loc(rs.getNString("m_loc"));
				m.setM_gender(rs.getNString("m_gender"));
				m.setM_birth(rs.getNString("m_birth"));
				m.setM_pw_question(rs.getNString("m_pw_question"));
				m.setM_pw_answer(rs.getNString("m_pw_answer"));
				m.setM_office_name(rs.getNString("m_office_name"));
				m.setM_office_tel(rs.getNString("m_office_tel"));
				m.setM_kind(rs.getNString("m_kind"));
				m.setM_point(rs.getInt("m_point"));
				m.setM_blacklist(rs.getInt("m_blacklist"));
			}
			return m;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateBlackList(String sql, String id) {
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, id);
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

	public boolean memberdelete(String id) {
		String sql="DELETE FROM MEMBERS WHERE M_ID=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, id);
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

	public ArrayList<Give> giveselect() {
		String sql="SELECT * FROM GIVE";
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<Give> gList = new ArrayList<Give>();
			while(rs.next()){
				g=new Give();
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
				s.setS_date(rs.getNString("s_date"));
				sList.add(s);
			}
			return sList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean pointupdate(String id) {
		String sql="UPDATE MEMBERS SET M_POINT=0 WHERE M_ID=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, id);
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

	public boolean supportinsert(String id, String point) {
		String sql="INSERT INTO SUPPORT VALUES('S'||S_SEQ.NEXTVAL,?,?,DEFAULT)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, id);
			pstmt.setNString(2, point);
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

	public String ismember(String id) {
		String sql="SELECT M_PW FROM MEMBERS WHERE M_ID=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				return rs.getNString("m_pw");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}//class End
