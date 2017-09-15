package com.steppe.nomad.bean;

public class Volunteer {
	private int rownum;
	private int v_num;//지원자 번호
	private int v_ptteam;//팀 결성 여부
	private int v_pnum;//프로젝트 번호
	private String v_mid;//지원자 아이디
	private int v_bid;//입찰액
	private String v_time;//입찰 시간
	private String p_title;//프로젝트 명
	private String p_mid;//프로젝트 아이디
	private int p_person;//인원수
	private String v_mid0;//프로젝트 지원자1
	private String v_mid1;//지원자2
	private String v_mid2;//지원자3
	private String v_mid3;//지원자4
	private String v_mid4;//지원자5
	
	public Volunteer(){
		
	}

	public Volunteer(int rownum, int v_num, String v_mid, int v_bid) {
		//this.rownum=rownum;
		this.v_num=v_num;
		this.v_mid=v_mid;
		this.v_bid=v_bid;
	}
	
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	public int getV_num() {
		return v_num;
	}
	public void setV_num(int v_num) {
		this.v_num = v_num;
	}
	public int getV_ptteam() {
		return v_ptteam;
	}
	public void setV_ptteam(int v_ptteam) {
		this.v_ptteam = v_ptteam;
	}
	public int getV_pnum() {
		return v_pnum;
	}
	public void setV_pnum(int v_pnum) {
		this.v_pnum = v_pnum;
	}
	public String getV_mid() {
		return v_mid;
	}
	public void setV_mid(String v_mid) {
		this.v_mid = v_mid;
	}
	public int getV_bid() {
		return v_bid;
	}
	public void setV_bid(int v_bid) {
		this.v_bid = v_bid;
	}
	public String getV_time() {
		return v_time;
	}
	public void setV_time(String v_time) {
		this.v_time = v_time;

	}
	public String getP_title() {
		return p_title;
	}
	public void setP_title(String p_title) {
		this.p_title = p_title;
	}
	public String getP_mid() {
		return p_mid;
	}
	public void setP_mid(String p_mid) {
		this.p_mid = p_mid;
	}
	public int getP_person() {
		return p_person;
	}
	public void setP_person(int p_person) {
		this.p_person = p_person;
	}

	public String getV_mid0() {
		return v_mid0;
	}

	public void setV_mid0(String v_mid0) {
		this.v_mid0 = v_mid0;
	}

	public String getV_mid1() {
		return v_mid1;
	}

	public void setV_mid1(String v_mid1) {
		this.v_mid1 = v_mid1;
	}

	public String getV_mid2() {
		return v_mid2;
	}

	public void setV_mid2(String v_mid2) {
		this.v_mid2 = v_mid2;
	}

	public String getV_mid3() {
		return v_mid3;
	}

	public void setV_mid3(String v_mid3) {
		this.v_mid3 = v_mid3;
	}

	public String getV_mid4() {
		return v_mid4;
	}

	public void setV_mid4(String v_mid4) {
		this.v_mid4 = v_mid4;
	}
	
}
