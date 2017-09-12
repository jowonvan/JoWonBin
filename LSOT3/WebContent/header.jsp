<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style> 

#header {
	width: 100%;
	z-index:500;
	text-align : center;
	position: fixed;
	top: 0px;
	left: 0px;
	text-align: center;
	background-color: #082d71;
	color : white;
}
#header ul {
	list-style: none;
}

#header ul li {
	float: left;
	padding-top: 1%;
	padding-bottom: 1%;
	width: 13%;
	cursor: pointer;
	margin-bottom: 1%;
}

#header ul li:HOVER {
	border-bottom : 1px solid white;
	border-radius: 5px;
}

#header ul li a {
	cursor: pointer;
	color:white;
	text-decoration: none;
}
#header ul li#logo{
	width:10%;
}

#header ul li#loginTr{
	cursor: pointer;
}

ul li#takeGiboo ul#selectTaker{
	width:50%;
	float:right;
	display:none;
}


ul li#takeGiboo ul#selectTaker li{
	width:100%;
}

ul li#reviewGiboo ul#selectReview{
	width:60%;
	float:right;
	display:none;
}


ul li#reviewGiboo ul#selectReview li{
	width:100%;
}

#header ul li.login{
	float:right;
	cursor : auto;
	width:20%;
}
</style>
</head>
<body>
<div id="header">
	<ul>
		<li id="logo">홈</li>
		<li>LSOT 란?</li>
		<li id="Give">기부 하기</li>
		<li id="takeGiboo">
			기부 받기
			<ul id="selectTaker">
				<li id="Indi">개인</li>
				<li id="Group">단체</li>
			</ul>
		</li>
		<li id="Review">봉사 후기</li>
		<li id="reviewGiboo">
			정보
			<ul id="selectReview">
				<li id="Notice">공지사항</li>
				<li id="Support">후원문의</li>
			</ul>
		</li>
		<c:if test="${login==0}">
			<li class="login" id="loginTr">로그인 / 회원가입</li>
		</c:if>
		<c:if test="${login==1}">
			<li class="login">
				${id}님 환영합니다.<br/>
				<a id="mypage">마이페이지</a> | <a href="logout">로그아웃</a>			
			</li>
		</c:if>
	</ul>
	</div>
</body>
<script>
	$(function(){
		$('#logo').click(function(){
			location.href='Index';			
		});
		$('#loginTr').click(function(){
			Aj('AjaxLogin','#section');
		});
		$('#takeGiboo').hover(function(){
			$('#selectTaker').toggle();
		});
		$('#reviewGiboo').hover(function(){
			$('#selectReview').toggle();
		});
		$('#mypage').click(function(){
			//Aj('AjaxMyPage','#section');
			location.href='mypage.jsp';
		});
		$('#Review').click(function(){
			//Aj('AjaxReview','#section');
	         location.href='reviewMain?page=1';
		});
		$('#Give').click(function(){
			//Aj('AjaxGive','#section');
			location.href='giveMain';
		});
		$('#Indi').click(function(){
			//Aj('AjaxIndiT','#section');
			location.href='toDetailP';
		});
		$('#Group').click(function(){
			//Aj('AjaxGroupT','#section');
			location.href='toDetailG';
		});
		$('#Notice').click(function(){
			//Aj('AjaxGroupT','#section');
			location.href='adminNotice';
		});
		$('#Support').click(function(){
			//Aj('AjaxGroupT','#section');
			location.href='support.jsp';
		});
	})
</script>
</html>