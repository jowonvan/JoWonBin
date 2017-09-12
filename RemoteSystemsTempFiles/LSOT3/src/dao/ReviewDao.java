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
import bean.Give;
import bean.Review;

public class ReviewDao {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds; //추가
	
	public ReviewDao(){
		try{
		Context init=new InitialContext();
		ds=(DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
		con=ds.getConnection();
		System.out.println("커넥션풀 연결 성공");
		}catch(Exception ex){
			System.out.println("커넥션풀 연결 실패");
		}
	}
	public void close(){  //커넥션풀에 커넥션 반납
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();  
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean insertReview(Review review) { 
		try {
			String sql = "INSERT INTO REVIEW VALUES('R'||R_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,DEFAULT,0)";
			
			pstmt=con.prepareStatement(sql);
		
			pstmt.setNString(1, review.getR_mid());
			pstmt.setNString(2, review.getR_title());
			pstmt.setNString(3, review.getR_name());
			pstmt.setNString(4, review.getR_when());
			pstmt.setNString(5, review.getR_content());
			pstmt.setNString(6, review.getR_place());
			pstmt.setNString(7, review.getR_feeling());
			pstmt.setNString(8, review.getR_filename1());
			pstmt.setNString(9, review.getR_filename2());
			pstmt.setNString(10, review.getR_filename3());
		
			int result = pstmt.executeUpdate();
			if(result != 0){
				return true;	//상품 등록 성공
			}
		} catch (SQLException e) {
			System.out.println("상품 등록 에러");
			e.printStackTrace();
		}finally{
	         close();
	      }
		return false;	//상품등록 실패
	}

	
	public ArrayList<Review> listReview() {
		 String sql="SELECT * FROM REVIEW ORDER BY R_DATE DESC"; // WHERE R_MID = ?
	      try {
	         ArrayList<Review> List = new ArrayList<>();
	         pstmt=con.prepareStatement(sql);
	        // pstmt.setNString(1, r_mid);
	         rs=pstmt.executeQuery();
	         Review r;
	         while(rs.next())
	         {
	            r = new Review();
	            //id,date,ip
	            r.setR_title(rs.getNString("R_TITLE"));
	            r.setR_name(rs.getNString("R_NAME"));
	            r.setR_date(rs.getNString("R_DATE"));
	            ////////
	            r.setR_code(rs.getNString("R_CODE"));
	            r.setR_mid(rs.getNString("R_MID"));
	            r.setR_when(rs.getNString("R_WHEN"));
	            r.setR_content(rs.getNString("R_CONTENT"));
	            r.setR_place(rs.getNString("R_PLACE"));
	            r.setR_feeling(rs.getNString("R_FEELING"));
	            r.setR_filename1(rs.getNString("R_FILENAME1"));
	            r.setR_filename2(rs.getNString("R_FILENAME2"));
	            r.setR_filename3(rs.getNString("R_FILENAME3"));
	            r.setR_hits(rs.getInt("R_HITS"));   //조회수
	            
	            List.add(r);
	         }
	         return List;
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }finally{
	         close();
	      }
	      return null;

}
	public ArrayList<Review> reviewDetail(String code) {
	
		String sql="SELECT * FROM REVIEW WHERE R_CODE = ?"; // WHERE R_MID = ?
	      try {
		         ArrayList<Review> List2 = new ArrayList<>();
		         pstmt=con.prepareStatement(sql);
		         pstmt.setNString(1, code);
		         rs=pstmt.executeQuery();
		         Review r;
		         
		         while(rs.next()){
		        	 
		            r = new Review();
		            //id,date,ip
		            r.setR_title(rs.getNString("R_TITLE"));
		            r.setR_name(rs.getNString("R_NAME"));
		            r.setR_date(rs.getNString("R_DATE"));
		            ////////
		            r.setR_code(rs.getNString("R_CODE"));
		            r.setR_mid(rs.getNString("R_MID"));
		            r.setR_when(rs.getNString("R_WHEN"));
		            r.setR_content(rs.getNString("R_CONTENT"));
		            r.setR_place(rs.getNString("R_PLACE"));
		            r.setR_feeling(rs.getNString("R_FEELING"));
		            r.setR_filename1(rs.getNString("R_FILENAME1"));
		            r.setR_filename2(rs.getNString("R_FILENAME2"));
		            r.setR_filename3(rs.getNString("R_FILENAME3"));
		            r.setR_hits(rs.getInt("R_HITS"));   //조회수
	
		            List2.add(r);
		         }
		         return List2;
		      } catch (SQLException e) {
		         e.printStackTrace();
		      }finally{
		         close();
		      }
		      return null;
	}
	
	public boolean hitsupdate(String sql, String code) {
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
	      }finally{
		         close();
		      }
	      return false;
	}

	
	public void changeReview(String code,String title, String name, String when, String content, String place, String feeling,
			String filename1, String filename2, String filename3) {
		try {
			String sql="UPDATE REVIEW "
					+ "SET R_TITLE = ?, R_NAME = ?, R_WHEN = ?, R_CONTENT = ?, R_PLACE = ?, R_FEELING = ?, R_FILENAME1 = ?, R_FILENAME2 = ?, R_FILENAME3 = ?"
					+ "WHERE R_CODE=?";
			
			pstmt=con.prepareStatement(sql);
			
			pstmt.setNString(1, title);
			pstmt.setNString(2, name);
			pstmt.setNString(3, when);
			pstmt.setNString(4, content);
			pstmt.setNString(5, place);
			pstmt.setNString(6, feeling);
			pstmt.setNString(7, filename1);
			pstmt.setNString(8, filename2);
			pstmt.setNString(9, filename3);
			pstmt.setNString(10, code);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("리뷰업데이트에러");
			e.printStackTrace();
		}finally{
	         close();
	      }
	}
	public ArrayList<Review> searchTake(String sql, String g_title) {
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, g_title);
			rs = pstmt.executeQuery();
			ArrayList<Review> tList = new ArrayList<Review>();
			while(rs.next()){
				Review gBean = new Review();
				gBean.setR_code(rs.getNString("r_code"));
				gBean.setR_title(rs.getNString("r_title"));
				gBean.setR_mid(rs.getNString("r_mid"));
				gBean.setR_date(rs.getNString("r_date"));
				gBean.setR_hits(rs.getInt("r_hits"));
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

}//class End
