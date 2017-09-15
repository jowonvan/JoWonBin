/* 외래키 삭제 */
ALTER TABLE project
	DROP
		CONSTRAINT FK_pj_ca2_TO_pj
		CASCADE;

ALTER TABLE project
	DROP
		CONSTRAINT FK_member_TO_project
		CASCADE;

ALTER TABLE project_catagory2
	DROP
		CONSTRAINT FK_pj_ca1_TO_pj_ca2
		CASCADE;

ALTER TABLE report
	DROP
		CONSTRAINT FK_member_TO_report
		CASCADE;

ALTER TABLE notice
	DROP
		CONSTRAINT FK_member_TO_notice
		CASCADE;

ALTER TABLE profile
	DROP
		CONSTRAINT FK_member_TO_profile
		CASCADE;

ALTER TABLE skill
	DROP
		CONSTRAINT FK_member_TO_skill
		CASCADE;

ALTER TABLE career
	DROP
		CONSTRAINT FK_member_TO_career
		CASCADE;

ALTER TABLE portfolio
	DROP
		CONSTRAINT FK_member_TO_portfolio
		CASCADE;

ALTER TABLE answer
	DROP
		CONSTRAINT FK_member_TO_answer
		CASCADE;

ALTER TABLE answer
	DROP
		CONSTRAINT FK_test_TO_answer
		CASCADE;

ALTER TABLE volunteer
	DROP
		CONSTRAINT FK_member_TO_volunteer
		CASCADE;

ALTER TABLE volunteer
	DROP
		CONSTRAINT FK_project_TO_volunteer
		CASCADE;

ALTER TABLE purchase
	DROP
		CONSTRAINT FK_member_TO_purchase
		CASCADE;

ALTER TABLE purchase
	DROP
		CONSTRAINT FK_project_TO_purchase
		CASCADE;

ALTER TABLE purchase_detail
	DROP
		CONSTRAINT FK_pur_TO_pur_detail
		CASCADE;

ALTER TABLE purchase_detail
	DROP
		CONSTRAINT FK_m_TO_pur_detail
		CASCADE;

ALTER TABLE reply
	DROP
		CONSTRAINT FK_project_TO_reply
		CASCADE;

ALTER TABLE reply
	DROP
		CONSTRAINT FK_member_TO_reply
		CASCADE;

ALTER TABLE evaluate
	DROP
		CONSTRAINT FK_member_TO_evaluate
		CASCADE;

ALTER TABLE evaluate
	DROP
		CONSTRAINT FK_project_TO_evaluate
		CASCADE;

ALTER TABLE chat
	DROP
		CONSTRAINT FK_project_TO_chat
		CASCADE;

ALTER TABLE result
	DROP
		CONSTRAINT FK_member_TO_result
		CASCADE;

ALTER TABLE memberfile
	DROP
		CONSTRAINT FK_member_TO_mfile
		CASCADE;

ALTER TABLE verylike
	DROP
		CONSTRAINT FK_member_TO_verylike
		CASCADE;

ALTER TABLE project_bookmark
	DROP
		CONSTRAINT FK_pj_TO_pj_bookmark
		CASCADE;

ALTER TABLE portfoliofile
	DROP
		CONSTRAINT FK_pf_TO_pffile
		CASCADE;

/* 기본키 삭제 */
ALTER TABLE member
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

ALTER TABLE project
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

ALTER TABLE project_catagory1
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

ALTER TABLE project_catagory2
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

ALTER TABLE report
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

ALTER TABLE notice
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

ALTER TABLE profile
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

ALTER TABLE skill
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

ALTER TABLE career
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

ALTER TABLE portfolio
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

ALTER TABLE test
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

ALTER TABLE answer
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

ALTER TABLE volunteer
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

ALTER TABLE purchase
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

ALTER TABLE purchase_detail
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

ALTER TABLE reply
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

ALTER TABLE evaluate
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

ALTER TABLE chat
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

ALTER TABLE result
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

ALTER TABLE memberfile
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

ALTER TABLE verylike
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

ALTER TABLE project_bookmark
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

ALTER TABLE portfoliofile
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

ALTER TABLE required_skills
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

/* 테이블 삭제 */
/* 회원 */
DROP TABLE member 
	CASCADE CONSTRAINTS;

/* 프로젝트 */
DROP TABLE project 
	CASCADE CONSTRAINTS;

/* 프로젝트 1차 카테고리 */
DROP TABLE project_catagory1 
	CASCADE CONSTRAINTS;

/* 프로젝트 2차 카테고리 */
DROP TABLE project_catagory2 
	CASCADE CONSTRAINTS;

/* 신고 */
DROP TABLE report 
	CASCADE CONSTRAINTS;

/* 공지사항 */
DROP TABLE notice 
	CASCADE CONSTRAINTS;

/* 프로필 */
DROP TABLE profile 
	CASCADE CONSTRAINTS;

/* 기술 */
DROP TABLE skill 
	CASCADE CONSTRAINTS;

/* 경력 */
DROP TABLE career 
	CASCADE CONSTRAINTS;

/* 포트폴리오 */
DROP TABLE portfolio 
	CASCADE CONSTRAINTS;

/* 자격 시험 */
DROP TABLE test 
	CASCADE CONSTRAINTS;

/* 프리랜서 입력 답 */
DROP TABLE answer 
	CASCADE CONSTRAINTS;

/* 프로젝트 지원자 */
DROP TABLE volunteer 
	CASCADE CONSTRAINTS;

/* 결제 */
DROP TABLE purchase 
	CASCADE CONSTRAINTS;

/* 결제 내역 */
DROP TABLE purchase_detail 
	CASCADE CONSTRAINTS;

/* 프로젝트 댓글 */
DROP TABLE reply 
	CASCADE CONSTRAINTS;

/* 평가 */
DROP TABLE evaluate 
	CASCADE CONSTRAINTS;

/* 채팅 */
DROP TABLE chat 
	CASCADE CONSTRAINTS;

/* 자격시험 문제 풀이 결과 */
DROP TABLE result 
	CASCADE CONSTRAINTS;

/* 프로필 사진 파일명 */
DROP TABLE memberfile 
	CASCADE CONSTRAINTS;

/* 프리랜서 좋아요 */
DROP TABLE verylike 
	CASCADE CONSTRAINTS;

/* 프로젝트 즐겨찾기 */
DROP TABLE project_bookmark 
	CASCADE CONSTRAINTS;

/* 포트폴리오 파일명 */
DROP TABLE portfoliofile 
	CASCADE CONSTRAINTS;

/* 필요 언어 세부 */
DROP TABLE required_skills 
	CASCADE CONSTRAINTS;

/* 테이블 생성 및 기본키 생성 */
/* 회원 */
CREATE TABLE member (
	m_id NVARCHAR2(20) NOT NULL, /* 회원 아이디 */
	m_pw NVARCHAR2(20) NOT NULL, /* 회원 비밀번호 */
	m_name NVARCHAR2(20) NOT NULL, /* 회원 이름 */
	m_email NVARCHAR2(30) NOT NULL, /* 회원 이메일 */
	m_kind NCHAR(1) NOT NULL, /* 회원 종류 */
	m_status NCHAR(1) NOT NULL /* 회원 상태 */
);

ALTER TABLE member
	ADD
		CONSTRAINT PK_member
		PRIMARY KEY (
			m_id
		);

/* 프로젝트 */
CREATE TABLE project (
	p_num NUMBER NOT NULL, /* 프로젝트 번호 */
	p_pc1name NVARCHAR2(20) NOT NULL, /* 1차 카테고리 이름 */
	p_pc2name NVARCHAR2(20) NOT NULL, /* 2차 카테고리 이름 */
	p_mid NVARCHAR2(20) NOT NULL, /* 회원 아이디 */
	p_budget NUMBER NOT NULL, /* 프로젝트 예산 */
	p_term NVARCHAR2(20) NOT NULL, /* 프로젝트 기간 */
	p_title NVARCHAR2(20) NOT NULL, /* 프로젝트 제목 */
	p_content NCLOB NOT NULL, /* 프로젝트 내용 */
	p_filename NVARCHAR2(20), /* 프로젝트 첨부파일 */
	p_deadline NVARCHAR2(20) NOT NULL, /* 프로젝트 마감일 */
	p_plnum0 NVARCHAR2(20) NOT NULL, /* 프로젝트 필요 언어0 */
	p_plnum1 NVARCHAR2(20), /* 프로젝트 필요 언어1 */
	p_plnum2 NVARCHAR2(20), /* 프로젝트 필요 언어2 */
	p_person NUMBER NOT NULL, /* 프로젝트 인원 */
	p_status NUMBER NOT NULL, /* 상태 아이디 */
	p_vol NUMBER NOT NULL, /* 프로젝트 지원자수 */
    p_bookmark NUMBER default 0 /* 프로젝트 즐겨찾기 */
);

select * from project_bookmark;
alter table project modify p_vol default 0;
alter table project modify p_bookmark default 0;
CREATE TABLE project_bookmark (
	pb_num NUMBER NOT NULL, /* 즐겨찾기 번호 */
	pb_pnum NUMBER NOT NULL, /* 프로젝트 번호 */
	pb_id NVARCHAR2(20) default ' ', /* 프로젝트 즐겨찾기 한 아이디 */
	pb_mid NVARCHAR2(20) NOT NULL, /* 프로젝트 등록한 아이디 */
	pb_flag NUMBER NOT NULL /* 플래그 */
);
alter table project_bookmark modify pb_id default ' ';
alter table project_bookmark modify pb_flag default 0;

ALTER TABLE project_bookmark
	ADD
		CONSTRAINT PK_project_bookmark
		PRIMARY KEY (
			pb_num
		);
ALTER TABLE project_bookmark
	ADD
		CONSTRAINT FK_pj_TO_pj_bookmark
		FOREIGN KEY (
			pb_pnum
		)
		REFERENCES project (
			p_num
		)ON DELETE CASCADE;

ALTER TABLE project
	ADD
		CONSTRAINT PK_project
		PRIMARY KEY (
			p_num
		);

/* 프로젝트 1차 카테고리 */
CREATE TABLE project_catagory1 (
	pc1_id NUMBER NOT NULL, /* 1차 카테고리 아이디 */
	pc1_name NVARCHAR2(20) NOT NULL /* 1차 카테고리 이름 */
);

ALTER TABLE project_catagory1
	ADD
		CONSTRAINT PK_project_catagory1
		PRIMARY KEY (
			pc1_id
		);

/* 프로젝트 2차 카테고리 */
CREATE TABLE project_catagory2 (
	pc2_id NUMBER NOT NULL, /* 2차 카테고리 아이디 */
	pc2_pc1id NUMBER NOT NULL, /* 1차 카테고리 아이디 */
	pc2_name NVARCHAR2(20) NOT NULL /* 2차 카테고리 이름 */
);

ALTER TABLE project_catagory2
	ADD
		CONSTRAINT PK_project_catagory2
		PRIMARY KEY (
			pc2_id,
			pc2_pc1id
		);

/* 신고 */
CREATE TABLE report (
	r_num NUMBER(20) NOT NULL, /* 신고 번호 */
	r_mid NVARCHAR2(20) NOT NULL, /* 회원 아이디 */
	r_kind NVARCHAR2(20) NOT NULL, /* 신고 종류 */
	r_content NCLOB NOT NULL, /* 신고 내용 */
	r_url NVARCHAR2(200) NOT NULL /* 원글 URL */
);

ALTER TABLE report
	ADD
		CONSTRAINT PK_report
		PRIMARY KEY (
			r_num
		);

/* 공지사항 */
CREATE TABLE notice (
	n_num NUMBER NOT NULL, /* 공지사항 번호 */
	n_mid NVARCHAR2(20) NOT NULL, /* 회원 아이디 */
	n_title NVARCHAR2(20) NOT NULL, /* 공지사항 제목 */
	n_content NCLOB NOT NULL, /* 공지사항 내용 */
	n_date NVARCHAR2(20) NOT NULL, /* 공지사항 날짜 */
	n_mname NVARCHAR2(20) NOT NULL /* 회원 이름 */
);

ALTER TABLE notice
	ADD
		CONSTRAINT PK_notice
		PRIMARY KEY (
			n_num
		);

/* 프로필 */
CREATE TABLE profile (
	pro_num number NOT NULL, /* 프로필 번호 */
	pro_mid NVARCHAR2(20) NOT NULL, /* 회원 아이디 */
	pro_content NCLOB NOT NULL /* 프로필 내용 */
);

ALTER TABLE profile
	ADD
		CONSTRAINT PK_profile
		PRIMARY KEY (
			pro_num
		);

/* 기술 */
CREATE TABLE skill (
	sk_num number NOT NULL, /* 기술 번호 */
	sk_mid NVARCHAR2(20) NOT NULL, /* 회원 아이디 */
	sk_name NVARCHAR2(20) NOT NULL, /* 기술 이름 */
	sk_grade NVARCHAR2(20) NOT NULL, /* 기술 등급 */
	sk_career NVARCHAR2(20) NOT NULL /* 기술 경력 */
);

ALTER TABLE skill
	ADD
		CONSTRAINT PK_skill
		PRIMARY KEY (
			sk_num
		);

/* 경력 */
CREATE TABLE career (
	ca_num NUMBER NOT NULL, /* 경력 번호 */
	ca_mid NVARCHAR2(20) NOT NULL, /* 회원 아이디 */
	ca_term NVARCHAR2(20) NOT NULL, /* 경력 기간 */
	ca_company NVARCHAR2(20) NOT NULL, /* 근무 회사 */
	ca_rank NVARCHAR2(20) NOT NULL /* 근무 직급 */
);

ALTER TABLE career
	ADD
		CONSTRAINT PK_career
		PRIMARY KEY (
			ca_num
		);

/* 포트폴리오 */
CREATE TABLE portfolio (
	pf_num NUMBER NOT NULL, /* 포트폴리오 번호 */
	pf_mid NVARCHAR2(20) NOT NULL, /* 회원 아이디 */
	pf_title NVARCHAR2(30) NOT NULL, /* 포트폴리오 제목 */
	pf_term NVARCHAR2(20) NOT NULL, /* 포트폴리오 기간 */
	pf_contribute NVARCHAR2(20) NOT NULL, /* 포트폴리오 기여도 */
	pf_content NCLOB NOT NULL /* 포트폴리오 내용 */
);

ALTER TABLE portfolio
	ADD
		CONSTRAINT PK_portfolio
		PRIMARY KEY (
			pf_num
		);

/* 자격 시험 */
CREATE TABLE test (
	t_num NUMBER NOT NULL, /* 문제 번호 */
	t_name NVARCHAR2(20), /* 문제 이름 */
	t_content NCLOB NOT NULL, /* 문제 내용 */
	t_answer NUMBER NOT NULL, /* 문제 답 */
	t_no1 NVARCHAR2(100) NOT NULL, /* 문제 보기1 */
	t_no2 NVARCHAR2(100) NOT NULL, /* 문제 보기2 */
	t_no3 NVARCHAR2(100) NOT NULL, /* 문제 보기3 */
	t_no4 NVARCHAR2(100) NOT NULL /* 문제 보기4 */
);

ALTER TABLE test
	ADD
		CONSTRAINT PK_test
		PRIMARY KEY (
			t_num
		);

/* 프리랜서 입력 답 */
CREATE TABLE answer (
	a_num NUMBER NOT NULL, /* 답변 번호 */
	a_mid NVARCHAR2(20) NOT NULL, /* 회원 아이디 */
	a_tnum NUMBER NOT NULL, /* 문제 번호 */
	a_insertnum NUMBER NOT NULL, /* 입력한 답 */
	a_check NUMBER NOT NULL, /* 답 확인 */
	a_tname NVARCHAR2(20) NOT NULL /* 문제 이름 */
);

ALTER TABLE answer
	ADD
		CONSTRAINT PK_answer
		PRIMARY KEY (
			a_num
		);

/* 프로젝트 지원자 */
CREATE TABLE volunteer (
	v_num NUMBER NOT NULL, /* 지원자 번호 */
    v_ptteam NUMBER NOT NULL, /* 팀 결성 여부 */
	v_pnum NUMBER NOT NULL, /* 프로젝트 번호 */
	v_mid NVARCHAR2(20) NOT NULL, /* 회원 아이디 */
	v_bid NUMBER NOT NULL, /* 입찰 가격 */
	v_time DATE NOT NULL /* 입찰 시간 */
);
ALTER TABLE volunteer MODIFY v_time DEFAULT SYSDATE;
ALTER TABLE volunteer
	ADD
		CONSTRAINT PK_volunteer
		PRIMARY KEY (
			v_num
		);

/* 결제 */
CREATE TABLE purchase (
	pu_num NUMBER NOT NULL, /* 결제 번호 */
	pu_money NUMBER NOT NULL, /* 결제 금액 */
	pu_mid NVARCHAR2(20) NOT NULL, /* 회원 아이디 */
	pu_pnum NUMBER NOT NULL /* 프로젝트 번호 */
);
select * from purchase;
ALTER TABLE purchase
	ADD
		CONSTRAINT PK_purchase
		PRIMARY KEY (
			pu_num
		);

/* 결제 내역 */
CREATE TABLE purchase_detail (
	pd_num NUMBER NOT NULL, /* 결제 내역 번호 */
	pd_punum NUMBER NOT NULL, /* 결제 번호 */
	pd_mid NVARCHAR2(20) NOT NULL, /* 회원 아이디 */
	pd_money NUMBER NOT NULL, /* 결제 금액 */
	pd_catagory NCHAR(1) NOT NULL /* 결제 구분 */
);

ALTER TABLE purchase_detail
	ADD
		CONSTRAINT PK_purchase_detail
		PRIMARY KEY (
			pd_num
		);

/* 프로젝트 댓글 */
CREATE TABLE reply (
	r_num NUMBER NOT NULL, /* 댓글 번호 */
	r_mid NVARCHAR2(20) NOT NULL, /* 회원 아이디 */
	r_pnum NUMBER NOT NULL, /* 프로젝트 번호 */
	r_content NCLOB NOT NULL, /* 댓글 내용 */
	r_date NVARCHAR2(20) NOT NULL /* 작성 날짜 */
);

ALTER TABLE reply
	ADD
		CONSTRAINT PK_reply
		PRIMARY KEY (
			r_num
		);

ALTER TABLE reply MODIFY r_date DEFAULT SYSDATE;

/* 평가 */
CREATE TABLE evaluate (
	e_num NUMBER NOT NULL, /* 평가 번호 */
	e_score NUMBER NOT NULL, /* 평가 점수 */
	e_content NVARCHAR2(20) NOT NULL, /* 평가 내용 */
	e_mid NVARCHAR2(20) NOT NULL, /* 회원 아이디 */
	e_pnum NUMBER NOT NULL /* 프로젝트 번호 */
);

ALTER TABLE evaluate
	ADD
		CONSTRAINT PK_evaluate
		PRIMARY KEY (
			e_num
		);

/* 채팅 */
CREATE TABLE chat (
	c_id NUMBER NOT NULL, /* 채팅 아이디 */
	c_pnum NUMBER NOT NULL, /* 프로젝트 번호 */
	c_mid NVARCHAR2(20) NOT NULL, /* 채팅 입력 아이디 */
	c_date DATE NOT NULL, /* 채팅 입력 시간 */
	c_content NCLOB NOT NULL /* 채팅 내용 */
);

ALTER TABLE CHAT MODIFY c_date DEFAULT SYSDATE;

ALTER TABLE chat
	ADD
		CONSTRAINT PK_chat
		PRIMARY KEY (
			c_id
		);

/* 자격시험 문제 풀이 결과 */
CREATE TABLE result (
	rs_num NUMBER NOT NULL, /* 결과 번호 */
	rs_mid NVARCHAR2(20) NOT NULL, /* 회원 아이디 */
	rs_tname NVARCHAR2(20) NOT NULL, /* 문제 이름 */
	rs_pc NUMBER NOT NULL /* 정답 비율 */
);

ALTER TABLE result
	ADD
		CONSTRAINT PK_result
		PRIMARY KEY (
			rs_num
		);

/* 프로필 사진 파일명 */
CREATE TABLE memberfile (
	mf_num NUMBER NOT NULL, /* 멤버 파일 번호 */
	mf_mid NVARCHAR2(20) NOT NULL, /* 회원 아이디 */
	mf_sysname NVARCHAR2(100) NOT NULL /* 파일명 */
);

ALTER TABLE memberfile
	ADD
		CONSTRAINT PK_memberfile
		PRIMARY KEY (
			mf_num
		);

/* 프리랜서 좋아요 */
CREATE TABLE verylike (
	l_num NUMBER NOT NULL, /* 번호 */
	l_msetid NVARCHAR2(20) NOT NULL, /* 좋아요 받는 아이디 */
	l_mGetId NVARCHAR2(20) NOT NULL /* 회원 아이디 */
);

ALTER TABLE verylike
	ADD
		CONSTRAINT PK_verylike
		PRIMARY KEY (
			l_num
		);

/* 포트폴리오 파일명 */
CREATE TABLE portfoliofile (
	pt_num NUMBER NOT NULL, /* 파일명 번호 */
	pt_pfnum NUMBER NOT NULL, /* 포트폴리오 번호 */
	pt_sysname NVARCHAR2(50) NOT NULL /* 파일명 */
);


ALTER TABLE portfoliofile
	ADD
		CONSTRAINT PK_portfoliofile
		PRIMARY KEY (
			pt_num
		);

/* 필요 언어 세부 */
CREATE TABLE required_skills (
	rs_id NUMBER NOT NULL, /* 세부 언어 번호 */
	rs_plnum NVARCHAR2(20) NOT NULL /* 세부 언어 종류  */
);

ALTER TABLE required_skills
	ADD
		CONSTRAINT PK_required_skills
		PRIMARY KEY (
			rs_id
		);

/* 외래키 생성 */
ALTER TABLE project
	ADD
		CONSTRAINT FK_pj_ca2_TO_pj
		FOREIGN KEY (
			p_pc2name,
			p_pc1name
		)
		REFERENCES project_catagory2 (
			pc2_id,
			pc2_pc1id
		)ON DELETE CASCADE;

ALTER TABLE project
	ADD
		CONSTRAINT FK_member_TO_project
		FOREIGN KEY (
			p_mid
		)
		REFERENCES member (
			m_id
		)ON DELETE CASCADE;

ALTER TABLE project_catagory2
	ADD
		CONSTRAINT FK_pj_ca1_TO_pj_ca2
		FOREIGN KEY (
			pc2_pc1id
		)
		REFERENCES project_catagory1 (
			pc1_id
		)ON DELETE CASCADE;

ALTER TABLE report
	ADD
		CONSTRAINT FK_member_TO_report
		FOREIGN KEY (
			r_mid
		)
		REFERENCES member (
			m_id
		)ON DELETE CASCADE;

ALTER TABLE notice
	ADD
		CONSTRAINT FK_member_TO_notice
		FOREIGN KEY (
			n_mid
		)
		REFERENCES member (
			m_id
		)ON DELETE CASCADE;

ALTER TABLE profile
	ADD
		CONSTRAINT FK_member_TO_profile
		FOREIGN KEY (
			pro_mid
		)
		REFERENCES member (
			m_id
		)ON DELETE CASCADE;

ALTER TABLE skill
	ADD
		CONSTRAINT FK_member_TO_skill
		FOREIGN KEY (
			sk_mid
		)
		REFERENCES member (
			m_id
		)ON DELETE CASCADE;

ALTER TABLE career
	ADD
		CONSTRAINT FK_member_TO_career
		FOREIGN KEY (
			ca_mid
		)
		REFERENCES member (
			m_id
		)ON DELETE CASCADE;

ALTER TABLE portfolio
	ADD
		CONSTRAINT FK_member_TO_portfolio
		FOREIGN KEY (
			pf_mid
		)
		REFERENCES member (
			m_id
		)ON DELETE CASCADE;
CREATE SEQUENCE portfolioFile_SEQ;
ALTER TABLE answer
	ADD
		CONSTRAINT FK_member_TO_answer
		FOREIGN KEY (
			a_mid
		)
		REFERENCES member (
			m_id
		)ON DELETE CASCADE;

ALTER TABLE answer
	ADD
		CONSTRAINT FK_test_TO_answer
		FOREIGN KEY (
			a_tnum
		)
		REFERENCES test (
			t_num
		)ON DELETE CASCADE;

ALTER TABLE volunteer
	ADD
		CONSTRAINT FK_member_TO_volunteer
		FOREIGN KEY (
			v_mid
		)
		REFERENCES member (
			m_id
		)ON DELETE CASCADE;

ALTER TABLE volunteer
	ADD
		CONSTRAINT FK_project_TO_volunteer
		FOREIGN KEY (
			v_pnum
		)
		REFERENCES project (
			p_num
		)ON DELETE CASCADE;

ALTER TABLE purchase
	ADD
		CONSTRAINT FK_member_TO_purchase
		FOREIGN KEY (
			pu_mid
		)
		REFERENCES member (
			m_id
		)ON DELETE CASCADE;

ALTER TABLE purchase
	ADD
		CONSTRAINT FK_project_TO_purchase
		FOREIGN KEY (
			pu_pnum
		)
		REFERENCES project (
			p_num
		)ON DELETE CASCADE;

ALTER TABLE purchase_detail
	ADD
		CONSTRAINT FK_pur_TO_pur_detail
		FOREIGN KEY (
			pd_punum
		)
		REFERENCES purchase (
			pu_num
		)ON DELETE CASCADE;

ALTER TABLE purchase_detail
	ADD
		CONSTRAINT FK_m_TO_pur_detail
		FOREIGN KEY (
			pd_mid
		)
		REFERENCES member (
			m_id
		)ON DELETE CASCADE;

ALTER TABLE reply
	ADD
		CONSTRAINT FK_project_TO_reply
		FOREIGN KEY (
			r_pnum
		)
		REFERENCES project (
			p_num
		)ON DELETE CASCADE;

ALTER TABLE reply
	ADD
		CONSTRAINT FK_member_TO_reply
		FOREIGN KEY (
			r_mid
		)
		REFERENCES member (
			m_id
		)ON DELETE CASCADE;

ALTER TABLE evaluate
	ADD
		CONSTRAINT FK_member_TO_evaluate
		FOREIGN KEY (
			e_mid
		)
		REFERENCES member (
			m_id
		)ON DELETE CASCADE;

ALTER TABLE evaluate
	ADD
		CONSTRAINT FK_project_TO_evaluate
		FOREIGN KEY (
			e_pnum
		)
		REFERENCES project (
			p_num
		)ON DELETE CASCADE;

ALTER TABLE chat
	ADD
		CONSTRAINT FK_project_TO_chat
		FOREIGN KEY (
			c_pnum
		)
		REFERENCES project (
			p_num
		)ON DELETE CASCADE;

ALTER TABLE result
	ADD
		CONSTRAINT FK_member_TO_result
		FOREIGN KEY (
			rs_mid
		)
		REFERENCES member (
			m_id
		)ON DELETE CASCADE;

ALTER TABLE memberfile
	ADD
		CONSTRAINT FK_member_TO_mfile
		FOREIGN KEY (
			mf_mid
		)
		REFERENCES member (
			m_id
		)ON DELETE CASCADE;

ALTER TABLE verylike
	ADD
		CONSTRAINT FK_member_TO_verylike
		FOREIGN KEY (
			l_mid
		)
		REFERENCES member (
			m_id
		)ON DELETE CASCADE;

ALTER TABLE project_bookmark
	ADD
		CONSTRAINT FK_pj_TO_pj_bookmark
		FOREIGN KEY (
			pb_pnum
		)
		REFERENCES project (
			p_num
		)ON DELETE CASCADE;

ALTER TABLE portfoliofile
	ADD
		CONSTRAINT FK_pf_TO_pffile
		FOREIGN KEY (
			pf_num
		)
		REFERENCES portfolio (
			pf_num
		)ON DELETE CASCADE;
    
/* 뷰생성 */
CREATE OR REPLACE VIEW MLIST 
AS SELECT ROWNUM AS RNUM,M_ID,M_PW,M_NAME,M_EMAIL,M_KIND,M_STATUS,MF_SYSNAME FROM MEMBER INNER JOIN MEMBERFILE
ON MEMBERFILE.MF_MID=MEMBER.M_ID;

CREATE OR REPLACE VIEW PORTLIST AS SELECT ROWNUM AS RNUM,PF_NUM,PF_MID,PF_TITLE,PF_TERM,PF_CONTRIBUTE,PF_CONTENT,PT_SYSNAME,PT_NUM
FROM PORTFOLIO INNER JOIN PORTFOLIOFILE ON PORTFOLIOFILE.PT_PFNUM=PORTFOLIO.PF_NUM;

CREATE OR REPLACE VIEW NLIST AS
SELECT ROWNUM AS RNUM, N_NUM, N_MID, N_MNAME, N_TITLE, N_CONTENT, N_DATE 
FROM NOTICE;

CREATE OR REPLACE VIEW PLIST AS
SELECT ROWNUM AS RNUM, P_NUM, P_PC1NAME, P_PC2NAME, P_MID, 
P_BUDGET, P_TERM, P_TITLE, P_DEADLINE, P_PLNUM0, P_PLNUM1, 
P_PLNUM2, P_STATUS, P_PERSON, P_VOL
FROM PROJECT;

CREATE OR REPLACE VIEW MLIST_1 AS
SELECT ROWNUM AS RNUM, M_ID, M_PW, M_NAME, M_EMAIL, M_KIND, M_STATUS
FROM MEMBER;

CREATE OR REPLACE VIEW CLLIST AS
SELECT ROWNUM AS RNUM, M_ID, M_PW, M_NAME, M_EMAIL, M_KIND, M_STATUS
FROM MEMBER;

CREATE OR REPLACE VIEW FRLIST AS
SELECT ROWNUM AS RNUM, M_ID, M_PW, M_NAME, M_EMAIL, M_KIND, M_STATUS
FROM MEMBER;

CREATE OR REPLACE VIEW PRLIST AS
SELECT ROWNUM AS RNUM, PU_NUM, PU_MONEY, PU_MID, PU_PNUM
FROM PURCHASE;
    
/* 더미 데이터 */
-- member 테이블
INSERT INTO member(M_ID, M_PW, M_NAME,M_EMAIL,M_KIND,M_STATUS)
VALUES('admin','admin','관리자','admin@steppe.com', 'A', 'N');

INSERT INTO member(M_ID, M_PW, M_NAME,M_EMAIL,M_KIND,M_STATUS)
VALUES('lsw','lsw','lsw','jekiel2764@naver.com', 'C', 'N');

INSERT INTO member(M_ID, M_PW, M_NAME,M_EMAIL,M_KIND,M_STATUS)
VALUES('free','free','lsw','cjhpho@naver.com', 'F', 'N');

-- test 테이블
INSERT INTO TEST VALUES(1,'java','피카츄가 진화하면?',2,'파이리','라이츄','꼬부기','이상해씨');
INSERT INTO TEST VALUES(2,'java','꼬부기가 진화하면?',1,'어니부기','라이츄','모부기','이상해씨');
INSERT INTO TEST VALUES(3,'java','파이리가 진화하면?',1,'리자드','라이츄','리자몽','이상해씨');
INSERT INTO TEST VALUES(4,'java','문제 낸 사람의 성별은?',3,'무성','아메바','남자','여자');
INSERT INTO TEST VALUES(5,'java','고라파덕이 진화하면?',3,'양덕','오덕','골덕','이상해씨');
INSERT INTO TEST VALUES(6,'java','이브이의 진화가 아닌것은?',3,'부스터','샤미드','라인하르트','쥬피썬더');
INSERT INTO TEST VALUES(7,'java','지훈이의 별명은?',1,'핫산','핫삼','임모텝','아드레날린');
INSERT INTO TEST VALUES(8,'java','캐터피가 진화하면?',2,'구구간식','단데기','딱충이','버터플');
INSERT INTO TEST VALUES(9,'java','송강호 주연의 5.18영화?',2,'살인의추억','택시운전사','괴물','설국열차');
INSERT INTO TEST VALUES(10,'java','1 + 3 x 2 = ?',1,'7','8','9','10');
INSERT INTO TEST VALUES(11,'java','한솥 도시락 메뉴가 아닌것은?',3,'육개장','해피박스','코스모스','개나리');
INSERT INTO TEST VALUES(12,'java','나쓰메 소세키의 달이 아름답네요의 뜻은?',2,'아름답다','사랑한다','달이밝다','작별인사');
INSERT INTO TEST VALUES(13,'java','해적왕을 꿈꾸는 만화는?',3,'나루토','토리코','원피스','블리치');
INSERT INTO TEST VALUES(14,'java','암세포도 생명이다라는 대사의 작가는?',1,'임성한','임창정','다니엘','임모텝');
INSERT INTO TEST VALUES(15,'java','미국의 대통령은?',2,'오바마','트럼프','잭블랙','트랭크스');
INSERT INTO TEST VALUES(16,'java','짱구의 바지 색상은?',4,'파랑','빨강','초록','노랑');
INSERT INTO TEST VALUES(17,'java','문제내기 귀찮다.. 이럴때?',4,'Delete','Ctrl + z','Enter','Ctrl + v');
INSERT INTO TEST VALUES(18,'java','배고프다 뭐할까?',3,'몰라','굶어','밥먹어','죽어');
INSERT INTO TEST VALUES(19,'java','야옹~ 울음소리의 주인공은?',3,'강아지','호랑이','고양이','원숭이');
INSERT INTO TEST VALUES(20,'java','위대한 차도둑',2,'GTO','GTA','GG','G-DRAGON');

INSERT INTO TEST VALUES(21,'html','피카츄가 진화하면?',2,'파이리','라이츄','꼬부기','이상해씨');
INSERT INTO TEST VALUES(22,'html','꼬부기가 진화하면?',1,'어니부기','라이츄','모부기','이상해씨');
INSERT INTO TEST VALUES(23,'html','파이리가 진화하면?',1,'리자드','라이츄','리자몽','이상해씨');
INSERT INTO TEST VALUES(24,'html','문제 낸 사람의 성별은?',3,'무성','아메바','남자','여자');
INSERT INTO TEST VALUES(25,'html','고라파덕이 진화하면?',3,'양덕','오덕','골덕','이상해씨');
INSERT INTO TEST VALUES(26,'html','이브이의 진화가 아닌것은?',3,'부스터','샤미드','라인하르트','쥬피썬더');
INSERT INTO TEST VALUES(27,'html','지훈이의 별명은?',1,'핫산','핫삼','임모텝','아드레날린');
INSERT INTO TEST VALUES(28,'html','캐터피가 진화하면?',2,'구구간식','단데기','딱충이','버터플');
INSERT INTO TEST VALUES(29,'html','송강호 주연의 5.18영화?',2,'살인의추억','택시운전사','괴물','설국열차');
INSERT INTO TEST VALUES(30,'html','1 + 3 x 2 = ?',1,'7','8','9','10');
INSERT INTO TEST VALUES(31,'html','한솥 도시락 메뉴가 아닌것은?',3,'육개장','해피박스','코스모스','개나리');
INSERT INTO TEST VALUES(32,'html','나쓰메 소세키의 달이 아름답네요의 뜻은?',2,'아름답다','사랑한다','달이밝다','작별인사');
INSERT INTO TEST VALUES(33,'html','해적왕을 꿈꾸는 만화는?',3,'나루토','토리코','원피스','블리치');
INSERT INTO TEST VALUES(34,'html','암세포도 생명이다라는 대사의 작가는?',1,'임성한','임창정','다니엘','임모텝');
INSERT INTO TEST VALUES(35,'html','미국의 대통령은?',2,'오바마','트럼프','잭블랙','트랭크스');
INSERT INTO TEST VALUES(36,'html','짱구의 바지 색상은?',4,'파랑','빨강','초록','노랑');
INSERT INTO TEST VALUES(37,'html','문제내기 귀찮다.. 이럴때?',4,'Delete','Ctrl + z','Enter','Ctrl + v');
INSERT INTO TEST VALUES(38,'html','배고프다 뭐할까?',3,'몰라','굶어','밥먹어','죽어');
INSERT INTO TEST VALUES(39,'html','야옹~ 울음소리의 주인공은?',3,'강아지','호랑이','고양이','원숭이');
INSERT INTO TEST VALUES(40,'html','위대한 차도둑',2,'GTO','GTA','GG','G-DRAGON');

INSERT INTO TEST VALUES(41,'jsp','웹 또는 웹 서비스와 직접적인 관련이 없는 것은 무엇인가?',4,'인터넷','HTML','HTTP','클래스');
INSERT INTO TEST VALUES(42,'jsp','동적인 웹 문서를 작성하는 기술과 관련이 없는 것은 무엇 인가?',2,'JavaScript','JDBC','JSP','CGI');
INSERT INTO TEST VALUES(43,'jsp','HTTP 프로토콜에서 클라이언트의 요청에 서버가 응답하면 접속이 해제된다. 이것을 무엇이라 하는가?',4,'connectedness','server-push','client-pull','connection-oriented & stateless');
INSERT INTO TEST VALUES(44,'jsp','JSP 언어에 관한 설명으로 잘못된 것은?',4,'스크립트 방식으로 프로그램 작성할 수 있다.','Java 언어의 특성을 활용할 수 있다.','서블릿 기술에 기초한다.','웹 어플리케이션 서버의 하나이다.');
INSERT INTO TEST VALUES(45,'jsp','원하는 방식으로 인코딩 된 데이터를 메시지 몸체에 포함하여 전송하면서 파일을 요청하고자 하는 경우 사용된다',3,'GET','HEAD','POST','OPTIONS');
INSERT INTO TEST VALUES(46,'jsp','Java 언어에서 제공되는 기본 자료형이 아닌 것은?',1,'Integer','char','boolean','double');
INSERT INTO TEST VALUES(47,'jsp','키워드 final의 사용 예를 설명한 것이다. 잘못된 것은?',3,'상속될 수 없는 클래스를 선언한다.','재정의될 수 없는 메소드를 선언한다.','오버로딩될 수 없는 메소드를 선언한다.','상수로 사용되는 변수를 선언한다.');
INSERT INTO TEST VALUES(48,'jsp','System.out.print(‘$’+1000+.0)와 System.out.print(“$”+1000+.0)의 출력 결과는 각각 무엇인가?',2,'$1000.0 $1000.0','1036.0 $10000.0','1036.0 $1000.0','$1000.0 $10000.0');
INSERT INTO TEST VALUES(49,'jsp','어떤 클래스에서 protected 접근성을 가지는 필드와 메소드가 선언되어 있다. 이러한 필드나 메소드를 사용할 수 있는 위치가 아닌 것은?',3,'같은 클래스','같은 패키지','클래스 외부 임의의 곳','자식 클래스');
INSERT INTO TEST VALUES(50,'jsp','interface A { int method(); }를 구현하는 클래스 B의 가장 간단한 정의는 무엇인가?',1,'class B implements A { public int method(){ } }','class B implements A { int method(){ } }','class B extends A { public int method(){ } }','class B extends A { int method(){ } }');
INSERT INTO TEST VALUES(51,'jsp','Java에서 패키지 사용에 관한 설명이다. 잘못된 것은?',1,'프로그램에서 특정 패키지에 포함된 클래스를 사용할 때, package 구문이 필요하다.','컴파일 옵션 -d를 사용하여 프로그램에서 만들고자 하는 사용자 패키지의 위치를 지정한다.','환경 변수 CLASSPATH는 기존 패키지의 위치를 저장하고 있다.','Java 소스 프로그램은 java.lang.*를 자동으로 import한다.');
INSERT INTO TEST VALUES(52,'jsp','Java 프로그램의 실행 중 보기와 같은 유형의 예외가 발생하였다고 가정하자. 예외 처리가 반드시 필요한 것은 무엇인가?',3,'RuntimeException','Error','IOException','NullPointerException');
INSERT INTO TEST VALUES(53,'jsp','JSP 기술을 사용하여 웹 어플리케이션을 개발하고자 할 때 설치해야 하는 것이 아닌 것은?',4,'Java SDK','Eclipse','Tomcat','ASP.NET Framework');
INSERT INTO TEST VALUES(54,'jsp','WAR 파일에 대한 설명으로 잘못된 것은?',2,'JSP 페이지, 서블릿, Java 클래스 파일, HTML 파일 등을 묶은 것이다.','확장자는 .jar이다.','Eclipse를 사용하면 손쉽게 WAR 파일을 만들 수 있다.','웹 어플리케이션을 웹 컨테이너에 배포하기 위한 것이다.');
INSERT INTO TEST VALUES(55,'jsp','Tomcat에 대한 설명으로 정확한 것은?',1,'Apache Software Foundation에서 개발한 서블릿/JSP 컨테이너','C 언어로 구현된 HTTP 웹 서버','오픈 소스의 통합 개발 환경','Java 플랫폼');
INSERT INTO TEST VALUES(56,'jsp','JSP 태그의 종류와 형태가 잘못 짝지어진 것은?',2,'지시어 <%@ ... %>','표현식 <%! ... %>','스크립트릿 <% ... %>','주석 <%-- ... --%>');
INSERT INTO TEST VALUES(57,'jsp','무엇일까?<%@  taglib  prefix=“c” uri=“http://java.sun.com/jsp/jstl/core”  %>',3,'JSP 페이지의 특정 영역에 다른 문서를 포함하는 것이다.','JSP가 생성하는 문서의 타입이나 출력 버퍼의 크기를 정하는 것이다.','JSP 페이지에서 사용할 태그 라이브러리를 지정한 것이다.','JSP 페이지에서 사용할 내장 객체를 선언한 것이다.');
INSERT INTO TEST VALUES(58,'jsp','다음은 스크립트릿을 사용하지 않고 변수나 수식의 값을 표현하는 것이다. 무엇이라 하는가? <%= expr  %>',1,'표현식','표현 언어','태그 속성','탬플릿 데이터');
INSERT INTO TEST VALUES(59,'jsp','액션은 요청을 처리할 때 특정 기능을 수행하는 것이다. 모든 JSP 컨테이너가 구현해야 하는 표준 액션을 표현할 때, 액션 태그의 접두어로 사용되는 단어는 무엇인가?',4,'function','servlet','action','jsp');
INSERT INTO TEST VALUES(60,'jsp','JSP 기술에 기초하여 웹 어플리케이션을 구축할 때, 구성 요소로 보기 힘든 것은?',4,'JSP 페이지','서블릿','서버 측 JavaBeans 컴포넌트','클라이언트 측 액션 스크립트N');

INSERT INTO TEST VALUES(61,'c','피카츄가 진화하면?',2,'파이리','라이츄','꼬부기','이상해씨');
INSERT INTO TEST VALUES(62,'c','꼬부기가 진화하면?',1,'어니부기','라이츄','모부기','이상해씨');
INSERT INTO TEST VALUES(63,'c','파이리가 진화하면?',1,'리자드','라이츄','리자몽','이상해씨');
INSERT INTO TEST VALUES(64,'c','문제 낸 사람의 성별은?',3,'무성','아메바','남자','여자');
INSERT INTO TEST VALUES(65,'c','고라파덕이 진화하면?',3,'양덕','오덕','골덕','이상해씨');
INSERT INTO TEST VALUES(66,'c','이브이의 진화가 아닌것은?',3,'부스터','샤미드','라인하르트','쥬피썬더');
INSERT INTO TEST VALUES(67,'c','지훈이의 별명은?',1,'핫산','핫삼','임모텝','아드레날린');
INSERT INTO TEST VALUES(68,'c','캐터피가 진화하면?',2,'구구간식','단데기','딱충이','버터플');
INSERT INTO TEST VALUES(69,'c','송강호 주연의 5.18영화?',2,'살인의추억','택시운전사','괴물','설국열차');
INSERT INTO TEST VALUES(70,'c','1 + 3 x 2 = ?',1,'7','8','9','10');
INSERT INTO TEST VALUES(71,'c','한솥 도시락 메뉴가 아닌것은?',3,'육개장','해피박스','코스모스','개나리');
INSERT INTO TEST VALUES(72,'c','나쓰메 소세키의 달이 아름답네요의 뜻은?',2,'아름답다','사랑한다','달이밝다','작별인사');
INSERT INTO TEST VALUES(73,'c','해적왕을 꿈꾸는 만화는?',3,'나루토','토리코','원피스','블리치');
INSERT INTO TEST VALUES(74,'c','암세포도 생명이다라는 대사의 작가는?',1,'임성한','임창정','다니엘','임모텝');
INSERT INTO TEST VALUES(75,'c','미국의 대통령은?',2,'오바마','트럼프','잭블랙','트랭크스');
INSERT INTO TEST VALUES(76,'c','짱구의 바지 색상은?',4,'파랑','빨강','초록','노랑');
INSERT INTO TEST VALUES(77,'c','문제내기 귀찮다.. 이럴때?',4,'Delete','Ctrl + z','Enter','Ctrl + v');
INSERT INTO TEST VALUES(78,'c','배고프다 뭐할까?',3,'몰라','굶어','밥먹어','죽어');
INSERT INTO TEST VALUES(79,'c','야옹~ 울음소리의 주인공은?',3,'강아지','호랑이','고양이','원숭이');
INSERT INTO TEST VALUES(80,'c','위대한 차도둑',2,'GTO','GTA','GG','G-DRAGON');

INSERT INTO TEST VALUES(81,'android','피카츄가 진화하면?',2,'파이리','라이츄','꼬부기','이상해씨');
INSERT INTO TEST VALUES(82,'android','꼬부기가 진화하면?',1,'어니부기','라이츄','모부기','이상해씨');
INSERT INTO TEST VALUES(83,'android','파이리가 진화하면?',1,'리자드','라이츄','리자몽','이상해씨');
INSERT INTO TEST VALUES(84,'android','문제 낸 사람의 성별은?',3,'무성','아메바','남자','여자');
INSERT INTO TEST VALUES(85,'android','고라파덕이 진화하면?',3,'양덕','오덕','골덕','이상해씨');
INSERT INTO TEST VALUES(86,'android','이브이의 진화가 아닌것은?',3,'부스터','샤미드','라인하르트','쥬피썬더');
INSERT INTO TEST VALUES(87,'android','지훈이의 별명은?',1,'핫산','핫삼','임모텝','아드레날린');
INSERT INTO TEST VALUES(88,'android','캐터피가 진화하면?',2,'구구간식','단데기','딱충이','버터플');
INSERT INTO TEST VALUES(89,'android','송강호 주연의 5.18영화?',2,'살인의추억','택시운전사','괴물','설국열차');
INSERT INTO TEST VALUES(90,'android','1 + 3 x 2 = ?',1,'7','8','9','10');
INSERT INTO TEST VALUES(91,'android','한솥 도시락 메뉴가 아닌것은?',3,'육개장','해피박스','코스모스','개나리');
INSERT INTO TEST VALUES(92,'android','나쓰메 소세키의 달이 아름답네요의 뜻은?',2,'아름답다','사랑한다','달이밝다','작별인사');
INSERT INTO TEST VALUES(93,'android','해적왕을 꿈꾸는 만화는?',3,'나루토','토리코','원피스','블리치');
INSERT INTO TEST VALUES(94,'android','암세포도 생명이다라는 대사의 작가는?',1,'임성한','임창정','다니엘','임모텝');
INSERT INTO TEST VALUES(95,'android','미국의 대통령은?',2,'오바마','트럼프','잭블랙','트랭크스');
INSERT INTO TEST VALUES(96,'android','짱구의 바지 색상은?',4,'파랑','빨강','초록','노랑');
INSERT INTO TEST VALUES(97,'android','문제내기 귀찮다.. 이럴때?',4,'Delete','Ctrl + z','Enter','Ctrl + v');
INSERT INTO TEST VALUES(98,'android','배고프다 뭐할까?',3,'몰라','굶어','밥먹어','죽어');
INSERT INTO TEST VALUES(99,'android','야옹~ 울음소리의 주인공은?',3,'강아지','호랑이','고양이','원숭이');
INSERT INTO TEST VALUES(100,'android','위대한 차도둑',2,'GTO','GTA','GG','G-DRAGON');

--required_skill 테이블
INSERT INTO required_skills VALUES(0,'파워포인트');
INSERT INTO required_skills VALUES(1,'CAD');
INSERT INTO required_skills VALUES(2,'JAVA');
INSERT INTO required_skills VALUES(3,'C');
INSERT INTO required_skills VALUES(4,'C++');
INSERT INTO required_skills VALUES(5,'자바스크립트');
INSERT INTO required_skills VALUES(6,'SQL');
INSERT INTO required_skills VALUES(7,'CSS');
INSERT INTO required_skills VALUES(8,'PHP');
INSERT INTO required_skills VALUES(9,'IONIC');
INSERT INTO required_skills VALUES(10,'PYTHON');
INSERT INTO required_skills VALUES(11,'WPF');
select * from tab;   
select * from member;
--전체 테이블 삭제
SELECT  'DROP TABLE ' || object_name || ' CASCADE CONSTRAINTS;'
FROM    user_objects
WHERE   object_type = 'TABLE';

select * from project_bookmark;
delete from project;

commit;

select * from project;


DROP TABLE MEMBER CASCADE CONSTRAINTS;
DROP TABLE PROJECT CASCADE CONSTRAINTS;
DROP TABLE PROJECT_CATAGORY1 CASCADE CONSTRAINTS;
DROP TABLE PROJECT_CATAGORY2 CASCADE CONSTRAINTS;
DROP TABLE REPORT CASCADE CONSTRAINTS;
DROP TABLE NOTICE CASCADE CONSTRAINTS;
DROP TABLE PROFILE CASCADE CONSTRAINTS;
DROP TABLE CHAT_ROOM CASCADE CONSTRAINTS;
DROP TABLE SKILL CASCADE CONSTRAINTS;
DROP TABLE CAREER CASCADE CONSTRAINTS;
DROP TABLE TEST_CATAGORY2 CASCADE CONSTRAINTS;
DROP TABLE TEST_CATAGORY1 CASCADE CONSTRAINTS;
DROP TABLE PORTFOLIO CASCADE CONSTRAINTS;
DROP TABLE TEST CASCADE CONSTRAINTS;
DROP TABLE ANSWER CASCADE CONSTRAINTS;
DROP TABLE VOLUNTEER CASCADE CONSTRAINTS;
DROP TABLE PURCHASE CASCADE CONSTRAINTS;
DROP TABLE PURCHASE_DETAIL CASCADE CONSTRAINTS;
DROP TABLE REPLY CASCADE CONSTRAINTS;
DROP TABLE EVALUATE CASCADE CONSTRAINTS;
DROP TABLE CHAT CASCADE CONSTRAINTS;
DROP TABLE RESULT CASCADE CONSTRAINTS;
DROP TABLE MEMBERFILE CASCADE CONSTRAINTS;
DROP TABLE VERYLIKE CASCADE CONSTRAINTS;
DROP TABLE PROJECT_BOOKMARK CASCADE CONSTRAINTS;
DROP TABLE PORTFOLIOFILE CASCADE CONSTRAINTS;
DROP TABLE REQUIRED_SKILLS CASCADE CONSTRAINTS;

--전체 테이블 보기
SELECT  'SELECT * FROM ' || object_name ||';'
FROM    user_objects
WHERE   object_type = 'TABLE';

SELECT * FROM PROJECT_CATAGORY1;
SELECT * FROM MEMBER;
SELECT * FROM REPORT;
SELECT * FROM NOTICE;
SELECT * FROM PROFILE;
SELECT * FROM SKILL;
SELECT * FROM CAREER;
SELECT * FROM ANSWER;
SELECT * FROM PORTFOLIOFILE;
SELECT * FROM VERYLIKE;
SELECT * FROM PROJECT;
SELECT * FROM PROJECT_BOOKMARK;
SELECT * FROM PURCHASE;
SELECT * FROM VOLUNTEER;
SELECT * FROM PURCHASE_DETAIL;
SELECT * FROM REQUIRED_SKILLS;
SELECT * FROM REPLY;
SELECT * FROM EVALUATE;
SELECT * FROM CHAT;
SELECT * FROM MEMBERFILE;
SELECT * FROM PORTFOLIO;
SELECT * FROM RESULT;
SELECT * FROM PROJECT_CATAGORY2;
SELECT * FROM TEST;

--전체 뷰 보기
SELECT  'SELECT * FROM ' || object_name ||';'
FROM    user_objects
WHERE   object_type = 'VIEW';

SELECT * FROM CLLIST;
SELECT * FROM FRLIST;
SELECT * FROM PRLIST;
SELECT * FROM PORTLIST;
SELECT * FROM MLIST_1;
SELECT * FROM PLIST;
SELECT * FROM NLIST;
SELECT * FROM MLIST;

--전체 테이블 구조 보기
SELECT  'DESC ' || object_name ||';'
FROM    user_objects
WHERE   object_type = 'TABLE';

DESC PROJECT_CATAGORY1;
DESC MEMBER;
DESC REPORT;
DESC NOTICE;
DESC PROFILE;
DESC SKILL;
DESC CAREER;
DESC ANSWER;
DESC PORTFOLIOFILE;
DESC VERYLIKE;
DESC PROJECT;
DESC PROJECT_BOOKMARK;
DESC PURCHASE;
DESC VOLUNTEER;
DESC PURCHASE_DETAIL;
DESC REQUIRED_SKILLS;
DESC REPLY;
DESC EVALUATE;
DESC CHAT;
DESC MEMBERFILE;
DESC PORTFOLIO;
DESC RESULT;
DESC PROJECT_CATAGORY2;
DESC TEST;
select * from project;

select * from project_bookmark;

commit;

select * from volunteer;
INSERT INTO VOLUNTEER VALUES(1,0,b,33,DEFAULT);







