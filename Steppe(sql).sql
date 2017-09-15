/* �ܷ�Ű ���� */
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

/* �⺻Ű ���� */
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

/* ���̺� ���� */
/* ȸ�� */
DROP TABLE member 
	CASCADE CONSTRAINTS;

/* ������Ʈ */
DROP TABLE project 
	CASCADE CONSTRAINTS;

/* ������Ʈ 1�� ī�װ� */
DROP TABLE project_catagory1 
	CASCADE CONSTRAINTS;

/* ������Ʈ 2�� ī�װ� */
DROP TABLE project_catagory2 
	CASCADE CONSTRAINTS;

/* �Ű� */
DROP TABLE report 
	CASCADE CONSTRAINTS;

/* �������� */
DROP TABLE notice 
	CASCADE CONSTRAINTS;

/* ������ */
DROP TABLE profile 
	CASCADE CONSTRAINTS;

/* ��� */
DROP TABLE skill 
	CASCADE CONSTRAINTS;

/* ��� */
DROP TABLE career 
	CASCADE CONSTRAINTS;

/* ��Ʈ������ */
DROP TABLE portfolio 
	CASCADE CONSTRAINTS;

/* �ڰ� ���� */
DROP TABLE test 
	CASCADE CONSTRAINTS;

/* �������� �Է� �� */
DROP TABLE answer 
	CASCADE CONSTRAINTS;

/* ������Ʈ ������ */
DROP TABLE volunteer 
	CASCADE CONSTRAINTS;

/* ���� */
DROP TABLE purchase 
	CASCADE CONSTRAINTS;

/* ���� ���� */
DROP TABLE purchase_detail 
	CASCADE CONSTRAINTS;

/* ������Ʈ ��� */
DROP TABLE reply 
	CASCADE CONSTRAINTS;

/* �� */
DROP TABLE evaluate 
	CASCADE CONSTRAINTS;

/* ä�� */
DROP TABLE chat 
	CASCADE CONSTRAINTS;

/* �ڰݽ��� ���� Ǯ�� ��� */
DROP TABLE result 
	CASCADE CONSTRAINTS;

/* ������ ���� ���ϸ� */
DROP TABLE memberfile 
	CASCADE CONSTRAINTS;

/* �������� ���ƿ� */
DROP TABLE verylike 
	CASCADE CONSTRAINTS;

/* ������Ʈ ���ã�� */
DROP TABLE project_bookmark 
	CASCADE CONSTRAINTS;

/* ��Ʈ������ ���ϸ� */
DROP TABLE portfoliofile 
	CASCADE CONSTRAINTS;

/* �ʿ� ��� ���� */
DROP TABLE required_skills 
	CASCADE CONSTRAINTS;

/* ���̺� ���� �� �⺻Ű ���� */
/* ȸ�� */
CREATE TABLE member (
	m_id NVARCHAR2(20) NOT NULL, /* ȸ�� ���̵� */
	m_pw NVARCHAR2(20) NOT NULL, /* ȸ�� ��й�ȣ */
	m_name NVARCHAR2(20) NOT NULL, /* ȸ�� �̸� */
	m_email NVARCHAR2(30) NOT NULL, /* ȸ�� �̸��� */
	m_kind NCHAR(1) NOT NULL, /* ȸ�� ���� */
	m_status NCHAR(1) NOT NULL /* ȸ�� ���� */
);

ALTER TABLE member
	ADD
		CONSTRAINT PK_member
		PRIMARY KEY (
			m_id
		);

/* ������Ʈ */
CREATE TABLE project (
	p_num NUMBER NOT NULL, /* ������Ʈ ��ȣ */
	p_pc1name NVARCHAR2(20) NOT NULL, /* 1�� ī�װ� �̸� */
	p_pc2name NVARCHAR2(20) NOT NULL, /* 2�� ī�װ� �̸� */
	p_mid NVARCHAR2(20) NOT NULL, /* ȸ�� ���̵� */
	p_budget NUMBER NOT NULL, /* ������Ʈ ���� */
	p_term NVARCHAR2(20) NOT NULL, /* ������Ʈ �Ⱓ */
	p_title NVARCHAR2(20) NOT NULL, /* ������Ʈ ���� */
	p_content NCLOB NOT NULL, /* ������Ʈ ���� */
	p_filename NVARCHAR2(20), /* ������Ʈ ÷������ */
	p_deadline NVARCHAR2(20) NOT NULL, /* ������Ʈ ������ */
	p_plnum0 NVARCHAR2(20) NOT NULL, /* ������Ʈ �ʿ� ���0 */
	p_plnum1 NVARCHAR2(20), /* ������Ʈ �ʿ� ���1 */
	p_plnum2 NVARCHAR2(20), /* ������Ʈ �ʿ� ���2 */
	p_person NUMBER NOT NULL, /* ������Ʈ �ο� */
	p_status NUMBER NOT NULL, /* ���� ���̵� */
	p_vol NUMBER NOT NULL, /* ������Ʈ �����ڼ� */
    p_bookmark NUMBER default 0 /* ������Ʈ ���ã�� */
);

select * from project_bookmark;
alter table project modify p_vol default 0;
alter table project modify p_bookmark default 0;
CREATE TABLE project_bookmark (
	pb_num NUMBER NOT NULL, /* ���ã�� ��ȣ */
	pb_pnum NUMBER NOT NULL, /* ������Ʈ ��ȣ */
	pb_id NVARCHAR2(20) default ' ', /* ������Ʈ ���ã�� �� ���̵� */
	pb_mid NVARCHAR2(20) NOT NULL, /* ������Ʈ ����� ���̵� */
	pb_flag NUMBER NOT NULL /* �÷��� */
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

/* ������Ʈ 1�� ī�װ� */
CREATE TABLE project_catagory1 (
	pc1_id NUMBER NOT NULL, /* 1�� ī�װ� ���̵� */
	pc1_name NVARCHAR2(20) NOT NULL /* 1�� ī�װ� �̸� */
);

ALTER TABLE project_catagory1
	ADD
		CONSTRAINT PK_project_catagory1
		PRIMARY KEY (
			pc1_id
		);

/* ������Ʈ 2�� ī�װ� */
CREATE TABLE project_catagory2 (
	pc2_id NUMBER NOT NULL, /* 2�� ī�װ� ���̵� */
	pc2_pc1id NUMBER NOT NULL, /* 1�� ī�װ� ���̵� */
	pc2_name NVARCHAR2(20) NOT NULL /* 2�� ī�װ� �̸� */
);

ALTER TABLE project_catagory2
	ADD
		CONSTRAINT PK_project_catagory2
		PRIMARY KEY (
			pc2_id,
			pc2_pc1id
		);

/* �Ű� */
CREATE TABLE report (
	r_num NUMBER(20) NOT NULL, /* �Ű� ��ȣ */
	r_mid NVARCHAR2(20) NOT NULL, /* ȸ�� ���̵� */
	r_kind NVARCHAR2(20) NOT NULL, /* �Ű� ���� */
	r_content NCLOB NOT NULL, /* �Ű� ���� */
	r_url NVARCHAR2(200) NOT NULL /* ���� URL */
);

ALTER TABLE report
	ADD
		CONSTRAINT PK_report
		PRIMARY KEY (
			r_num
		);

/* �������� */
CREATE TABLE notice (
	n_num NUMBER NOT NULL, /* �������� ��ȣ */
	n_mid NVARCHAR2(20) NOT NULL, /* ȸ�� ���̵� */
	n_title NVARCHAR2(20) NOT NULL, /* �������� ���� */
	n_content NCLOB NOT NULL, /* �������� ���� */
	n_date NVARCHAR2(20) NOT NULL, /* �������� ��¥ */
	n_mname NVARCHAR2(20) NOT NULL /* ȸ�� �̸� */
);

ALTER TABLE notice
	ADD
		CONSTRAINT PK_notice
		PRIMARY KEY (
			n_num
		);

/* ������ */
CREATE TABLE profile (
	pro_num number NOT NULL, /* ������ ��ȣ */
	pro_mid NVARCHAR2(20) NOT NULL, /* ȸ�� ���̵� */
	pro_content NCLOB NOT NULL /* ������ ���� */
);

ALTER TABLE profile
	ADD
		CONSTRAINT PK_profile
		PRIMARY KEY (
			pro_num
		);

/* ��� */
CREATE TABLE skill (
	sk_num number NOT NULL, /* ��� ��ȣ */
	sk_mid NVARCHAR2(20) NOT NULL, /* ȸ�� ���̵� */
	sk_name NVARCHAR2(20) NOT NULL, /* ��� �̸� */
	sk_grade NVARCHAR2(20) NOT NULL, /* ��� ��� */
	sk_career NVARCHAR2(20) NOT NULL /* ��� ��� */
);

ALTER TABLE skill
	ADD
		CONSTRAINT PK_skill
		PRIMARY KEY (
			sk_num
		);

/* ��� */
CREATE TABLE career (
	ca_num NUMBER NOT NULL, /* ��� ��ȣ */
	ca_mid NVARCHAR2(20) NOT NULL, /* ȸ�� ���̵� */
	ca_term NVARCHAR2(20) NOT NULL, /* ��� �Ⱓ */
	ca_company NVARCHAR2(20) NOT NULL, /* �ٹ� ȸ�� */
	ca_rank NVARCHAR2(20) NOT NULL /* �ٹ� ���� */
);

ALTER TABLE career
	ADD
		CONSTRAINT PK_career
		PRIMARY KEY (
			ca_num
		);

/* ��Ʈ������ */
CREATE TABLE portfolio (
	pf_num NUMBER NOT NULL, /* ��Ʈ������ ��ȣ */
	pf_mid NVARCHAR2(20) NOT NULL, /* ȸ�� ���̵� */
	pf_title NVARCHAR2(30) NOT NULL, /* ��Ʈ������ ���� */
	pf_term NVARCHAR2(20) NOT NULL, /* ��Ʈ������ �Ⱓ */
	pf_contribute NVARCHAR2(20) NOT NULL, /* ��Ʈ������ �⿩�� */
	pf_content NCLOB NOT NULL /* ��Ʈ������ ���� */
);

ALTER TABLE portfolio
	ADD
		CONSTRAINT PK_portfolio
		PRIMARY KEY (
			pf_num
		);

/* �ڰ� ���� */
CREATE TABLE test (
	t_num NUMBER NOT NULL, /* ���� ��ȣ */
	t_name NVARCHAR2(20), /* ���� �̸� */
	t_content NCLOB NOT NULL, /* ���� ���� */
	t_answer NUMBER NOT NULL, /* ���� �� */
	t_no1 NVARCHAR2(100) NOT NULL, /* ���� ����1 */
	t_no2 NVARCHAR2(100) NOT NULL, /* ���� ����2 */
	t_no3 NVARCHAR2(100) NOT NULL, /* ���� ����3 */
	t_no4 NVARCHAR2(100) NOT NULL /* ���� ����4 */
);

ALTER TABLE test
	ADD
		CONSTRAINT PK_test
		PRIMARY KEY (
			t_num
		);

/* �������� �Է� �� */
CREATE TABLE answer (
	a_num NUMBER NOT NULL, /* �亯 ��ȣ */
	a_mid NVARCHAR2(20) NOT NULL, /* ȸ�� ���̵� */
	a_tnum NUMBER NOT NULL, /* ���� ��ȣ */
	a_insertnum NUMBER NOT NULL, /* �Է��� �� */
	a_check NUMBER NOT NULL, /* �� Ȯ�� */
	a_tname NVARCHAR2(20) NOT NULL /* ���� �̸� */
);

ALTER TABLE answer
	ADD
		CONSTRAINT PK_answer
		PRIMARY KEY (
			a_num
		);

/* ������Ʈ ������ */
CREATE TABLE volunteer (
	v_num NUMBER NOT NULL, /* ������ ��ȣ */
    v_ptteam NUMBER NOT NULL, /* �� �Ἲ ���� */
	v_pnum NUMBER NOT NULL, /* ������Ʈ ��ȣ */
	v_mid NVARCHAR2(20) NOT NULL, /* ȸ�� ���̵� */
	v_bid NUMBER NOT NULL, /* ���� ���� */
	v_time DATE NOT NULL /* ���� �ð� */
);
ALTER TABLE volunteer MODIFY v_time DEFAULT SYSDATE;
ALTER TABLE volunteer
	ADD
		CONSTRAINT PK_volunteer
		PRIMARY KEY (
			v_num
		);

/* ���� */
CREATE TABLE purchase (
	pu_num NUMBER NOT NULL, /* ���� ��ȣ */
	pu_money NUMBER NOT NULL, /* ���� �ݾ� */
	pu_mid NVARCHAR2(20) NOT NULL, /* ȸ�� ���̵� */
	pu_pnum NUMBER NOT NULL /* ������Ʈ ��ȣ */
);
select * from purchase;
ALTER TABLE purchase
	ADD
		CONSTRAINT PK_purchase
		PRIMARY KEY (
			pu_num
		);

/* ���� ���� */
CREATE TABLE purchase_detail (
	pd_num NUMBER NOT NULL, /* ���� ���� ��ȣ */
	pd_punum NUMBER NOT NULL, /* ���� ��ȣ */
	pd_mid NVARCHAR2(20) NOT NULL, /* ȸ�� ���̵� */
	pd_money NUMBER NOT NULL, /* ���� �ݾ� */
	pd_catagory NCHAR(1) NOT NULL /* ���� ���� */
);

ALTER TABLE purchase_detail
	ADD
		CONSTRAINT PK_purchase_detail
		PRIMARY KEY (
			pd_num
		);

/* ������Ʈ ��� */
CREATE TABLE reply (
	r_num NUMBER NOT NULL, /* ��� ��ȣ */
	r_mid NVARCHAR2(20) NOT NULL, /* ȸ�� ���̵� */
	r_pnum NUMBER NOT NULL, /* ������Ʈ ��ȣ */
	r_content NCLOB NOT NULL, /* ��� ���� */
	r_date NVARCHAR2(20) NOT NULL /* �ۼ� ��¥ */
);

ALTER TABLE reply
	ADD
		CONSTRAINT PK_reply
		PRIMARY KEY (
			r_num
		);

ALTER TABLE reply MODIFY r_date DEFAULT SYSDATE;

/* �� */
CREATE TABLE evaluate (
	e_num NUMBER NOT NULL, /* �� ��ȣ */
	e_score NUMBER NOT NULL, /* �� ���� */
	e_content NVARCHAR2(20) NOT NULL, /* �� ���� */
	e_mid NVARCHAR2(20) NOT NULL, /* ȸ�� ���̵� */
	e_pnum NUMBER NOT NULL /* ������Ʈ ��ȣ */
);

ALTER TABLE evaluate
	ADD
		CONSTRAINT PK_evaluate
		PRIMARY KEY (
			e_num
		);

/* ä�� */
CREATE TABLE chat (
	c_id NUMBER NOT NULL, /* ä�� ���̵� */
	c_pnum NUMBER NOT NULL, /* ������Ʈ ��ȣ */
	c_mid NVARCHAR2(20) NOT NULL, /* ä�� �Է� ���̵� */
	c_date DATE NOT NULL, /* ä�� �Է� �ð� */
	c_content NCLOB NOT NULL /* ä�� ���� */
);

ALTER TABLE CHAT MODIFY c_date DEFAULT SYSDATE;

ALTER TABLE chat
	ADD
		CONSTRAINT PK_chat
		PRIMARY KEY (
			c_id
		);

/* �ڰݽ��� ���� Ǯ�� ��� */
CREATE TABLE result (
	rs_num NUMBER NOT NULL, /* ��� ��ȣ */
	rs_mid NVARCHAR2(20) NOT NULL, /* ȸ�� ���̵� */
	rs_tname NVARCHAR2(20) NOT NULL, /* ���� �̸� */
	rs_pc NUMBER NOT NULL /* ���� ���� */
);

ALTER TABLE result
	ADD
		CONSTRAINT PK_result
		PRIMARY KEY (
			rs_num
		);

/* ������ ���� ���ϸ� */
CREATE TABLE memberfile (
	mf_num NUMBER NOT NULL, /* ��� ���� ��ȣ */
	mf_mid NVARCHAR2(20) NOT NULL, /* ȸ�� ���̵� */
	mf_sysname NVARCHAR2(100) NOT NULL /* ���ϸ� */
);

ALTER TABLE memberfile
	ADD
		CONSTRAINT PK_memberfile
		PRIMARY KEY (
			mf_num
		);

/* �������� ���ƿ� */
CREATE TABLE verylike (
	l_num NUMBER NOT NULL, /* ��ȣ */
	l_msetid NVARCHAR2(20) NOT NULL, /* ���ƿ� �޴� ���̵� */
	l_mGetId NVARCHAR2(20) NOT NULL /* ȸ�� ���̵� */
);

ALTER TABLE verylike
	ADD
		CONSTRAINT PK_verylike
		PRIMARY KEY (
			l_num
		);

/* ��Ʈ������ ���ϸ� */
CREATE TABLE portfoliofile (
	pt_num NUMBER NOT NULL, /* ���ϸ� ��ȣ */
	pt_pfnum NUMBER NOT NULL, /* ��Ʈ������ ��ȣ */
	pt_sysname NVARCHAR2(50) NOT NULL /* ���ϸ� */
);


ALTER TABLE portfoliofile
	ADD
		CONSTRAINT PK_portfoliofile
		PRIMARY KEY (
			pt_num
		);

/* �ʿ� ��� ���� */
CREATE TABLE required_skills (
	rs_id NUMBER NOT NULL, /* ���� ��� ��ȣ */
	rs_plnum NVARCHAR2(20) NOT NULL /* ���� ��� ����  */
);

ALTER TABLE required_skills
	ADD
		CONSTRAINT PK_required_skills
		PRIMARY KEY (
			rs_id
		);

/* �ܷ�Ű ���� */
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
    
/* ����� */
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
    
/* ���� ������ */
-- member ���̺�
INSERT INTO member(M_ID, M_PW, M_NAME,M_EMAIL,M_KIND,M_STATUS)
VALUES('admin','admin','������','admin@steppe.com', 'A', 'N');

INSERT INTO member(M_ID, M_PW, M_NAME,M_EMAIL,M_KIND,M_STATUS)
VALUES('lsw','lsw','lsw','jekiel2764@naver.com', 'C', 'N');

INSERT INTO member(M_ID, M_PW, M_NAME,M_EMAIL,M_KIND,M_STATUS)
VALUES('free','free','lsw','cjhpho@naver.com', 'F', 'N');

-- test ���̺�
INSERT INTO TEST VALUES(1,'java','��ī�� ��ȭ�ϸ�?',2,'���̸�','������','���α�','�̻��ؾ�');
INSERT INTO TEST VALUES(2,'java','���αⰡ ��ȭ�ϸ�?',1,'��Ϻα�','������','��α�','�̻��ؾ�');
INSERT INTO TEST VALUES(3,'java','���̸��� ��ȭ�ϸ�?',1,'���ڵ�','������','���ڸ�','�̻��ؾ�');
INSERT INTO TEST VALUES(4,'java','���� �� ����� ������?',3,'����','�Ƹ޹�','����','����');
INSERT INTO TEST VALUES(5,'java','����Ĵ��� ��ȭ�ϸ�?',3,'���','����','���','�̻��ؾ�');
INSERT INTO TEST VALUES(6,'java','�̺����� ��ȭ�� �ƴѰ���?',3,'�ν���','���̵�','�����ϸ�Ʈ','���ǽ��');
INSERT INTO TEST VALUES(7,'java','�������� ������?',1,'�ֻ�','�ֻ�','�Ӹ���','�Ƶ巹����');
INSERT INTO TEST VALUES(8,'java','ĳ���ǰ� ��ȭ�ϸ�?',2,'��������','�ܵ���','������','������');
INSERT INTO TEST VALUES(9,'java','�۰�ȣ �ֿ��� 5.18��ȭ?',2,'�������߾�','�ýÿ�����','����','��������');
INSERT INTO TEST VALUES(10,'java','1 + 3 x 2 = ?',1,'7','8','9','10');
INSERT INTO TEST VALUES(11,'java','�Ѽ� ���ö� �޴��� �ƴѰ���?',3,'������','���ǹڽ�','�ڽ���','������');
INSERT INTO TEST VALUES(12,'java','������ �Ҽ�Ű�� ���� �Ƹ���׿��� ����?',2,'�Ƹ����','����Ѵ�','���̹��','�ۺ��λ�');
INSERT INTO TEST VALUES(13,'java','�������� �޲ٴ� ��ȭ��?',3,'������','�丮��','���ǽ�','��ġ');
INSERT INTO TEST VALUES(14,'java','�ϼ����� �����̴ٶ�� ����� �۰���?',1,'�Ӽ���','��â��','�ٴϿ�','�Ӹ���');
INSERT INTO TEST VALUES(15,'java','�̱��� �������?',2,'���ٸ�','Ʈ����','���','Ʈ��ũ��');
INSERT INTO TEST VALUES(16,'java','¯���� ���� ������?',4,'�Ķ�','����','�ʷ�','���');
INSERT INTO TEST VALUES(17,'java','�������� ������.. �̷���?',4,'Delete','Ctrl + z','Enter','Ctrl + v');
INSERT INTO TEST VALUES(18,'java','������� ���ұ�?',3,'����','����','��Ծ�','�׾�');
INSERT INTO TEST VALUES(19,'java','�߿�~ �����Ҹ��� ���ΰ���?',3,'������','ȣ����','�����','������');
INSERT INTO TEST VALUES(20,'java','������ ������',2,'GTO','GTA','GG','G-DRAGON');

INSERT INTO TEST VALUES(21,'html','��ī�� ��ȭ�ϸ�?',2,'���̸�','������','���α�','�̻��ؾ�');
INSERT INTO TEST VALUES(22,'html','���αⰡ ��ȭ�ϸ�?',1,'��Ϻα�','������','��α�','�̻��ؾ�');
INSERT INTO TEST VALUES(23,'html','���̸��� ��ȭ�ϸ�?',1,'���ڵ�','������','���ڸ�','�̻��ؾ�');
INSERT INTO TEST VALUES(24,'html','���� �� ����� ������?',3,'����','�Ƹ޹�','����','����');
INSERT INTO TEST VALUES(25,'html','����Ĵ��� ��ȭ�ϸ�?',3,'���','����','���','�̻��ؾ�');
INSERT INTO TEST VALUES(26,'html','�̺����� ��ȭ�� �ƴѰ���?',3,'�ν���','���̵�','�����ϸ�Ʈ','���ǽ��');
INSERT INTO TEST VALUES(27,'html','�������� ������?',1,'�ֻ�','�ֻ�','�Ӹ���','�Ƶ巹����');
INSERT INTO TEST VALUES(28,'html','ĳ���ǰ� ��ȭ�ϸ�?',2,'��������','�ܵ���','������','������');
INSERT INTO TEST VALUES(29,'html','�۰�ȣ �ֿ��� 5.18��ȭ?',2,'�������߾�','�ýÿ�����','����','��������');
INSERT INTO TEST VALUES(30,'html','1 + 3 x 2 = ?',1,'7','8','9','10');
INSERT INTO TEST VALUES(31,'html','�Ѽ� ���ö� �޴��� �ƴѰ���?',3,'������','���ǹڽ�','�ڽ���','������');
INSERT INTO TEST VALUES(32,'html','������ �Ҽ�Ű�� ���� �Ƹ���׿��� ����?',2,'�Ƹ����','����Ѵ�','���̹��','�ۺ��λ�');
INSERT INTO TEST VALUES(33,'html','�������� �޲ٴ� ��ȭ��?',3,'������','�丮��','���ǽ�','��ġ');
INSERT INTO TEST VALUES(34,'html','�ϼ����� �����̴ٶ�� ����� �۰���?',1,'�Ӽ���','��â��','�ٴϿ�','�Ӹ���');
INSERT INTO TEST VALUES(35,'html','�̱��� �������?',2,'���ٸ�','Ʈ����','���','Ʈ��ũ��');
INSERT INTO TEST VALUES(36,'html','¯���� ���� ������?',4,'�Ķ�','����','�ʷ�','���');
INSERT INTO TEST VALUES(37,'html','�������� ������.. �̷���?',4,'Delete','Ctrl + z','Enter','Ctrl + v');
INSERT INTO TEST VALUES(38,'html','������� ���ұ�?',3,'����','����','��Ծ�','�׾�');
INSERT INTO TEST VALUES(39,'html','�߿�~ �����Ҹ��� ���ΰ���?',3,'������','ȣ����','�����','������');
INSERT INTO TEST VALUES(40,'html','������ ������',2,'GTO','GTA','GG','G-DRAGON');

INSERT INTO TEST VALUES(41,'jsp','�� �Ǵ� �� ���񽺿� �������� ������ ���� ���� �����ΰ�?',4,'���ͳ�','HTML','HTTP','Ŭ����');
INSERT INTO TEST VALUES(42,'jsp','������ �� ������ �ۼ��ϴ� ����� ������ ���� ���� ���� �ΰ�?',2,'JavaScript','JDBC','JSP','CGI');
INSERT INTO TEST VALUES(43,'jsp','HTTP �������ݿ��� Ŭ���̾�Ʈ�� ��û�� ������ �����ϸ� ������ �����ȴ�. �̰��� �����̶� �ϴ°�?',4,'connectedness','server-push','client-pull','connection-oriented & stateless');
INSERT INTO TEST VALUES(44,'jsp','JSP �� ���� �������� �߸��� ����?',4,'��ũ��Ʈ ������� ���α׷� �ۼ��� �� �ִ�.','Java ����� Ư���� Ȱ���� �� �ִ�.','���� ����� �����Ѵ�.','�� ���ø����̼� ������ �ϳ��̴�.');
INSERT INTO TEST VALUES(45,'jsp','���ϴ� ������� ���ڵ� �� �����͸� �޽��� ��ü�� �����Ͽ� �����ϸ鼭 ������ ��û�ϰ��� �ϴ� ��� ���ȴ�',3,'GET','HEAD','POST','OPTIONS');
INSERT INTO TEST VALUES(46,'jsp','Java ���� �����Ǵ� �⺻ �ڷ����� �ƴ� ����?',1,'Integer','char','boolean','double');
INSERT INTO TEST VALUES(47,'jsp','Ű���� final�� ��� ���� ������ ���̴�. �߸��� ����?',3,'��ӵ� �� ���� Ŭ������ �����Ѵ�.','�����ǵ� �� ���� �޼ҵ带 �����Ѵ�.','�����ε��� �� ���� �޼ҵ带 �����Ѵ�.','����� ���Ǵ� ������ �����Ѵ�.');
INSERT INTO TEST VALUES(48,'jsp','System.out.print(��$��+1000+.0)�� System.out.print(��$��+1000+.0)�� ��� ����� ���� �����ΰ�?',2,'$1000.0 $1000.0','1036.0 $10000.0','1036.0 $1000.0','$1000.0 $10000.0');
INSERT INTO TEST VALUES(49,'jsp','� Ŭ�������� protected ���ټ��� ������ �ʵ�� �޼ҵ尡 ����Ǿ� �ִ�. �̷��� �ʵ峪 �޼ҵ带 ����� �� �ִ� ��ġ�� �ƴ� ����?',3,'���� Ŭ����','���� ��Ű��','Ŭ���� �ܺ� ������ ��','�ڽ� Ŭ����');
INSERT INTO TEST VALUES(50,'jsp','interface A { int method(); }�� �����ϴ� Ŭ���� B�� ���� ������ ���Ǵ� �����ΰ�?',1,'class B implements A { public int method(){ } }','class B implements A { int method(){ } }','class B extends A { public int method(){ } }','class B extends A { int method(){ } }');
INSERT INTO TEST VALUES(51,'jsp','Java���� ��Ű�� ��뿡 ���� �����̴�. �߸��� ����?',1,'���α׷����� Ư�� ��Ű���� ���Ե� Ŭ������ ����� ��, package ������ �ʿ��ϴ�.','������ �ɼ� -d�� ����Ͽ� ���α׷����� ������� �ϴ� ����� ��Ű���� ��ġ�� �����Ѵ�.','ȯ�� ���� CLASSPATH�� ���� ��Ű���� ��ġ�� �����ϰ� �ִ�.','Java �ҽ� ���α׷��� java.lang.*�� �ڵ����� import�Ѵ�.');
INSERT INTO TEST VALUES(52,'jsp','Java ���α׷��� ���� �� ����� ���� ������ ���ܰ� �߻��Ͽ��ٰ� ��������. ���� ó���� �ݵ�� �ʿ��� ���� �����ΰ�?',3,'RuntimeException','Error','IOException','NullPointerException');
INSERT INTO TEST VALUES(53,'jsp','JSP ����� ����Ͽ� �� ���ø����̼��� �����ϰ��� �� �� ��ġ�ؾ� �ϴ� ���� �ƴ� ����?',4,'Java SDK','Eclipse','Tomcat','ASP.NET Framework');
INSERT INTO TEST VALUES(54,'jsp','WAR ���Ͽ� ���� �������� �߸��� ����?',2,'JSP ������, ����, Java Ŭ���� ����, HTML ���� ���� ���� ���̴�.','Ȯ���ڴ� .jar�̴�.','Eclipse�� ����ϸ� �ս��� WAR ������ ���� �� �ִ�.','�� ���ø����̼��� �� �����̳ʿ� �����ϱ� ���� ���̴�.');
INSERT INTO TEST VALUES(55,'jsp','Tomcat�� ���� �������� ��Ȯ�� ����?',1,'Apache Software Foundation���� ������ ����/JSP �����̳�','C ���� ������ HTTP �� ����','���� �ҽ��� ���� ���� ȯ��','Java �÷���');
INSERT INTO TEST VALUES(56,'jsp','JSP �±��� ������ ���°� �߸� ¦������ ����?',2,'���þ� <%@ ... %>','ǥ���� <%! ... %>','��ũ��Ʈ�� <% ... %>','�ּ� <%-- ... --%>');
INSERT INTO TEST VALUES(57,'jsp','�����ϱ�?<%@  taglib  prefix=��c�� uri=��http://java.sun.com/jsp/jstl/core��  %>',3,'JSP �������� Ư�� ������ �ٸ� ������ �����ϴ� ���̴�.','JSP�� �����ϴ� ������ Ÿ���̳� ��� ������ ũ�⸦ ���ϴ� ���̴�.','JSP ���������� ����� �±� ���̺귯���� ������ ���̴�.','JSP ���������� ����� ���� ��ü�� ������ ���̴�.');
INSERT INTO TEST VALUES(58,'jsp','������ ��ũ��Ʈ���� ������� �ʰ� ������ ������ ���� ǥ���ϴ� ���̴�. �����̶� �ϴ°�? <%= expr  %>',1,'ǥ����','ǥ�� ���','�±� �Ӽ�','���ø� ������');
INSERT INTO TEST VALUES(59,'jsp','�׼��� ��û�� ó���� �� Ư�� ����� �����ϴ� ���̴�. ��� JSP �����̳ʰ� �����ؾ� �ϴ� ǥ�� �׼��� ǥ���� ��, �׼� �±��� ���ξ�� ���Ǵ� �ܾ�� �����ΰ�?',4,'function','servlet','action','jsp');
INSERT INTO TEST VALUES(60,'jsp','JSP ����� �����Ͽ� �� ���ø����̼��� ������ ��, ���� ��ҷ� ���� ���� ����?',4,'JSP ������','����','���� �� JavaBeans ������Ʈ','Ŭ���̾�Ʈ �� �׼� ��ũ��ƮN');

INSERT INTO TEST VALUES(61,'c','��ī�� ��ȭ�ϸ�?',2,'���̸�','������','���α�','�̻��ؾ�');
INSERT INTO TEST VALUES(62,'c','���αⰡ ��ȭ�ϸ�?',1,'��Ϻα�','������','��α�','�̻��ؾ�');
INSERT INTO TEST VALUES(63,'c','���̸��� ��ȭ�ϸ�?',1,'���ڵ�','������','���ڸ�','�̻��ؾ�');
INSERT INTO TEST VALUES(64,'c','���� �� ����� ������?',3,'����','�Ƹ޹�','����','����');
INSERT INTO TEST VALUES(65,'c','����Ĵ��� ��ȭ�ϸ�?',3,'���','����','���','�̻��ؾ�');
INSERT INTO TEST VALUES(66,'c','�̺����� ��ȭ�� �ƴѰ���?',3,'�ν���','���̵�','�����ϸ�Ʈ','���ǽ��');
INSERT INTO TEST VALUES(67,'c','�������� ������?',1,'�ֻ�','�ֻ�','�Ӹ���','�Ƶ巹����');
INSERT INTO TEST VALUES(68,'c','ĳ���ǰ� ��ȭ�ϸ�?',2,'��������','�ܵ���','������','������');
INSERT INTO TEST VALUES(69,'c','�۰�ȣ �ֿ��� 5.18��ȭ?',2,'�������߾�','�ýÿ�����','����','��������');
INSERT INTO TEST VALUES(70,'c','1 + 3 x 2 = ?',1,'7','8','9','10');
INSERT INTO TEST VALUES(71,'c','�Ѽ� ���ö� �޴��� �ƴѰ���?',3,'������','���ǹڽ�','�ڽ���','������');
INSERT INTO TEST VALUES(72,'c','������ �Ҽ�Ű�� ���� �Ƹ���׿��� ����?',2,'�Ƹ����','����Ѵ�','���̹��','�ۺ��λ�');
INSERT INTO TEST VALUES(73,'c','�������� �޲ٴ� ��ȭ��?',3,'������','�丮��','���ǽ�','��ġ');
INSERT INTO TEST VALUES(74,'c','�ϼ����� �����̴ٶ�� ����� �۰���?',1,'�Ӽ���','��â��','�ٴϿ�','�Ӹ���');
INSERT INTO TEST VALUES(75,'c','�̱��� �������?',2,'���ٸ�','Ʈ����','���','Ʈ��ũ��');
INSERT INTO TEST VALUES(76,'c','¯���� ���� ������?',4,'�Ķ�','����','�ʷ�','���');
INSERT INTO TEST VALUES(77,'c','�������� ������.. �̷���?',4,'Delete','Ctrl + z','Enter','Ctrl + v');
INSERT INTO TEST VALUES(78,'c','������� ���ұ�?',3,'����','����','��Ծ�','�׾�');
INSERT INTO TEST VALUES(79,'c','�߿�~ �����Ҹ��� ���ΰ���?',3,'������','ȣ����','�����','������');
INSERT INTO TEST VALUES(80,'c','������ ������',2,'GTO','GTA','GG','G-DRAGON');

INSERT INTO TEST VALUES(81,'android','��ī�� ��ȭ�ϸ�?',2,'���̸�','������','���α�','�̻��ؾ�');
INSERT INTO TEST VALUES(82,'android','���αⰡ ��ȭ�ϸ�?',1,'��Ϻα�','������','��α�','�̻��ؾ�');
INSERT INTO TEST VALUES(83,'android','���̸��� ��ȭ�ϸ�?',1,'���ڵ�','������','���ڸ�','�̻��ؾ�');
INSERT INTO TEST VALUES(84,'android','���� �� ����� ������?',3,'����','�Ƹ޹�','����','����');
INSERT INTO TEST VALUES(85,'android','����Ĵ��� ��ȭ�ϸ�?',3,'���','����','���','�̻��ؾ�');
INSERT INTO TEST VALUES(86,'android','�̺����� ��ȭ�� �ƴѰ���?',3,'�ν���','���̵�','�����ϸ�Ʈ','���ǽ��');
INSERT INTO TEST VALUES(87,'android','�������� ������?',1,'�ֻ�','�ֻ�','�Ӹ���','�Ƶ巹����');
INSERT INTO TEST VALUES(88,'android','ĳ���ǰ� ��ȭ�ϸ�?',2,'��������','�ܵ���','������','������');
INSERT INTO TEST VALUES(89,'android','�۰�ȣ �ֿ��� 5.18��ȭ?',2,'�������߾�','�ýÿ�����','����','��������');
INSERT INTO TEST VALUES(90,'android','1 + 3 x 2 = ?',1,'7','8','9','10');
INSERT INTO TEST VALUES(91,'android','�Ѽ� ���ö� �޴��� �ƴѰ���?',3,'������','���ǹڽ�','�ڽ���','������');
INSERT INTO TEST VALUES(92,'android','������ �Ҽ�Ű�� ���� �Ƹ���׿��� ����?',2,'�Ƹ����','����Ѵ�','���̹��','�ۺ��λ�');
INSERT INTO TEST VALUES(93,'android','�������� �޲ٴ� ��ȭ��?',3,'������','�丮��','���ǽ�','��ġ');
INSERT INTO TEST VALUES(94,'android','�ϼ����� �����̴ٶ�� ����� �۰���?',1,'�Ӽ���','��â��','�ٴϿ�','�Ӹ���');
INSERT INTO TEST VALUES(95,'android','�̱��� �������?',2,'���ٸ�','Ʈ����','���','Ʈ��ũ��');
INSERT INTO TEST VALUES(96,'android','¯���� ���� ������?',4,'�Ķ�','����','�ʷ�','���');
INSERT INTO TEST VALUES(97,'android','�������� ������.. �̷���?',4,'Delete','Ctrl + z','Enter','Ctrl + v');
INSERT INTO TEST VALUES(98,'android','������� ���ұ�?',3,'����','����','��Ծ�','�׾�');
INSERT INTO TEST VALUES(99,'android','�߿�~ �����Ҹ��� ���ΰ���?',3,'������','ȣ����','�����','������');
INSERT INTO TEST VALUES(100,'android','������ ������',2,'GTO','GTA','GG','G-DRAGON');

--required_skill ���̺�
INSERT INTO required_skills VALUES(0,'�Ŀ�����Ʈ');
INSERT INTO required_skills VALUES(1,'CAD');
INSERT INTO required_skills VALUES(2,'JAVA');
INSERT INTO required_skills VALUES(3,'C');
INSERT INTO required_skills VALUES(4,'C++');
INSERT INTO required_skills VALUES(5,'�ڹٽ�ũ��Ʈ');
INSERT INTO required_skills VALUES(6,'SQL');
INSERT INTO required_skills VALUES(7,'CSS');
INSERT INTO required_skills VALUES(8,'PHP');
INSERT INTO required_skills VALUES(9,'IONIC');
INSERT INTO required_skills VALUES(10,'PYTHON');
INSERT INTO required_skills VALUES(11,'WPF');
select * from tab;   
select * from member;
--��ü ���̺� ����
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

--��ü ���̺� ����
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

--��ü �� ����
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

--��ü ���̺� ���� ����
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







