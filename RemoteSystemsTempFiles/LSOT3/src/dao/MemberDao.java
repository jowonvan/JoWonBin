package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import bean.Give;
import bean.Member;
import bean.Review;
import bean.Take;

public class MemberDao {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs=null;
	DataSource ds;
	ResultSetMetaData rsmd;

	public MemberDao(){
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

	private boolean boolConvert(int result) {
		return (result!=0)?true:false;
	}

	//로그인 확인
	public int isMember(String id, String pw) {
		String sql="SELECT * FROM MEMBERS WHERE m_id=?";
		int result=1;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				if(rs.getNString("m_pw").equals(pw)){
					result=0;	//아이디 비번 존재
				}else{
					result=-1;	//비번 존재하지 않음
				}
			}else{
				result=1;	//아이디 존재하지 않음
			}
		} catch (SQLException e) {
			System.out.println("isMember 실패");
			e.printStackTrace();
		}
		return result;
	}//isMember end

	//아이디 존재 여부 판독
	public boolean isId(String id) {
		String sql="SELECT COUNT(*) FROM MEMBERS WHERE m_id=?";
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				result=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boolConvert(result);
	}//isId end

	//이메일존재 여부 판독
	public boolean isEmail(String email) {
		String sql="SELECT COUNT(*) FROM MEMBERS WHERE m_email=?";
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, email);
			rs=pstmt.executeQuery();
			if(rs.next()){
				result=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boolConvert(result);
	}//isEmail end

	public boolean isName(String name) {
		String sql="SELECT COUNT(*) FROM MEMBERS WHERE m_name=?";
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, name);
			rs=pstmt.executeQuery();
			if(rs.next()){
				result=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boolConvert(result);
	}
	
	public boolean insertIndi(Member mb){
		String sql="INSERT INTO MEMBERS VALUES(?,?,?,?,?,?,?,?,?,?,NULL,NULL,?,?,DEFAULT)";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mb.getM_id());
			pstmt.setNString(2, mb.getM_pw());
			pstmt.setNString(3, mb.getM_name());
			pstmt.setNString(4, mb.getM_phone());
			pstmt.setNString(5, mb.getM_email());
			pstmt.setNString(6, mb.getM_loc());
			pstmt.setNString(7, mb.getM_gender());
			pstmt.setNString(8, mb.getM_birth());
			pstmt.setNString(9, mb.getM_pw_question());
			pstmt.setNString(10, mb.getM_pw_answer());
			pstmt.setNString(11, mb.getM_kind());
			pstmt.setInt(12, mb.getM_point());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("members insert 실패");
			e.printStackTrace();
		}
		return boolConvert(result);
	}//insertMembers end

	public boolean insertGroup(Member mb) {
		String sql="INSERT INTO MEMBERS VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,DEFAULT)";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mb.getM_id());
			pstmt.setNString(2, mb.getM_pw());
			pstmt.setNString(3, mb.getM_name());
			pstmt.setNString(4, mb.getM_phone());
			pstmt.setNString(5, mb.getM_email());
			pstmt.setNString(6, mb.getM_loc());
			pstmt.setNString(7, mb.getM_gender());
			pstmt.setNString(8, mb.getM_birth());
			pstmt.setNString(9, mb.getM_pw_question());
			pstmt.setNString(10, mb.getM_pw_answer());
			pstmt.setNString(11, mb.getM_office_name());
			pstmt.setNString(12, mb.getM_office_tel());
			pstmt.setNString(13, mb.getM_kind());
			pstmt.setInt(14, mb.getM_point());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("members insert 실패");
			e.printStackTrace();
		}
		return boolConvert(result);
	}

	public String yourId(String name, String email) {
		String sql="SELECT m_id FROM MEMBERS WHERE m_name=? AND m_email=?";
		String yourId = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, name);
			pstmt.setNString(2, email);
			rs=pstmt.executeQuery();
			if(rs.next()){
				yourId =rs.getNString("m_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return yourId;
	}
	
	public String checkQA(String pQ, String pA) {
		String sql="SELECT m_pw FROM MEMBERS WHERE m_id = (SELECT m_id FROM MEMBERS WHERE m_pw_question=? AND m_pw_answer=?)";
		String yourPw = null;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, pQ);
			pstmt.setNString(2, pA);
			rs=pstmt.executeQuery();
			if(rs.next()){
				yourPw = rs.getNString("m_pw");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return yourPw;
	}

	public ArrayList<Give> giveselect(String id) {
		String sql="SELECT * FROM GIVE WHERE G_MID=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs=pstmt.executeQuery();
			ArrayList<Give> gList=new ArrayList<Give>();
			Give g=null;
			while(rs.next()){
				g=new Give();
				g.setG_code(rs.getNString("g_code"));
				g.setG_mid(rs.getNString("g_mid"));
				g.setG_title(rs.getNString("g_title"));
				g.setG_date(rs.getNString("g_date"));
				gList.add(g);
			}
			return gList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Take> takeselect(String id) {
		String sql="SELECT * FROM TAKE WHERE T_MID=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs=pstmt.executeQuery();
			ArrayList<Take> tList=new ArrayList<Take>();
			Take t=null;
			while(rs.next()){
				t=new Take();
				t.setT_code(rs.getNString("t_code"));
				t.setT_mid(rs.getNString("t_mid"));
				t.setT_title(rs.getNString("t_title"));
				t.setT_date(rs.getNString("t_date"));
				t.setT_kind(rs.getNString("t_kind"));
				tList.add(t);
			}
			return tList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Review> reviewselect(String id) {
		String sql="SELECT * FROM REVIEW WHERE R_MID=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs=pstmt.executeQuery();
			ArrayList<Review> rList=new ArrayList<Review>();
			Review r=null;
			while(rs.next()){
				r=new Review();
				r.setR_code(rs.getNString("r_code"));
				r.setR_mid(rs.getNString("r_mid"));
				r.setR_title(rs.getNString("r_title"));
				r.setR_date(rs.getNString("r_date"));
				rList.add(r);
			}
			return rList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean memberupdate(String id, Member mb) {
		String sql="UPDATE MEMBERS SET M_PW=?,M_NAME=?,M_PHONE=?,M_EMAIL=?,M_LOC=?,M_GENDER=?,M_BIRTH=?,M_PW_QUESTION=?,M_PW_ANSWER=? WHERE M_ID=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, mb.getM_pw());
			pstmt.setNString(2, mb.getM_name());
			pstmt.setNString(3, mb.getM_phone());
			pstmt.setNString(4, mb.getM_email());
			pstmt.setNString(5, mb.getM_loc());
			pstmt.setNString(6, mb.getM_gender());
			pstmt.setNString(7, mb.getM_birth());
			pstmt.setNString(8, mb.getM_pw_question());
			pstmt.setNString(9, mb.getM_pw_answer());
			pstmt.setNString(10, id);
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
}
