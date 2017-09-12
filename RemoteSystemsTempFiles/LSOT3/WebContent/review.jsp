<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>리뷰 게시판</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<style>
html, body {
	width: 100%;
	height: 100%;
	margin: 0px;
	padding: 0px;
}
input[type=button] {
	cursor: pointer;
}
#contents {
	min-height : 100%;
	position: relative;
	margin:0px;
	padding:0px;
}
#section {
	width: 80%;
	margin-left:10%;
	padding-top:10%;
	padding-bottom: 15%;	
	text-align: center;
	position: relative;
}   

#section #abc{
	margin-bottom : 5%;
}

#section p{
	text-align: center;
}
 
#section p #review{
	position: absolute;
	right: 0;
}

#section .sns_wrap{
	position: absolute;
	bottom: 0;
	left: 25%;
	width:50%; 
}

#section .sns_wrap a{
	text-decoration: none;
	color: black;
}

table {
	width: 100%;
	height: 60%;
	border: 0px;
}

table tr td {
	height: 70px;
	border-bottom: 1px solid black;
}

table tr td a {
	text-decoration: none;
	color: black;
	font-size: 20px;
}

table tr th {
	background-color: #082d71;
	height: 50px;
	color : white;
}

table td {
	padding: 1%;
}

table .num {
	width: 10%;
}

table .photo {
	width: 15%;
}

table .title {
	width: 30%;
}

table .uploader {
	width: 10%;
}

table .time {
	width: 15%;
}

table .hits {
	width: 8%;
}

table .photo img {
	max-width: 100%;
	max-height: 100%;
}
#footer {
	position: absolute; 
	background-color : #ffffff;
	bottom: 0;
	left: 0;
	width: 100%;
	height: 10%;
}

#footcontLeft {
	float:left;
	width: 20%;
}

#footcontRight{
	float: right;
	width: 20%;
}
</style>
</head>
<body>
	<div id="contents">
		<div id="header"></div>
		
		<div id="section">
			<!-- <div id="abc">
				<img src="img/thx.jpg">
			</div> -->
			<h2>봉 사 후 기</h2>
			${List}${html }
		
			<form name="searchForm" method="post">
				검색 <select name="selectValue" onChange="searChg()">
						<option value="">선택</option>
						<option value="제목">제목</option>
						<option value="등록자">등록자</option>
					</select> 
				<input type="text" value="" class="inputs" name="inputSerch" /> 
				<input type="button" value="검색" class="inputs" onclick="searchBtn(this)" />
			</form>
			
			<div class="sns_wrap">
				<p>SNS 공유하기</p>
				<p>	
					<a href="javascript:toSNS('facebook','공유제목!','http://http://단축URL')"
						title="페이스북으로 가져가기"><img src="img/facebook.PNG"></a> 
					<a href="javascript:toSNS('twitter','공유제목!','http://http://단축URL')"
							title="트위터로 가져가기"><img src="img/twitter.PNG"></a> 
					<a href="javascript:toSNS('line','공유제목!','http://http://단축URL')"
						title="라인으로 가져가기"><img src="img/naverline.PNG"></a> 
					<a href="javascript:toSNS('pholar','공유제목!','http://단축URL')"
						title="폴라로 가져가기"><img src="img/pholar.PNG"></a> 
					<a href="javascript:toSNS('pinterest','공유제목!','http://단축URL')"
						title="핀터레스트로 가져가기"><img src="img/pinterest.PNG"></a> 
					<a href="javascript:toSNS('band','공유제목!','http://단축URL')"
						title="밴드로 가져가기"><img src="img/band.PNG"></a> 
					<a href="javascript:toSNS('google','공유제목!','http://http://단축URL')" 
						title="구글플러스로 가져가기"><img src="img/googleplus.PNG"></a> 
					<a href="javascript:toSNS('blog','공유제목!','http://http://단축URL')" 
						title="네이버블로그로 가져가기"><img src="img/naver.png"></a> 
				</p> 
				해당 URL <input type="text" value="http://localhost/LSOT4/reviewMain?page=1">
				<a href="javascript:copy_clip('http://localhost/LSOT4/reviewMain?page=1')">
				복사</a> <br />
			</div>
		</div>
		<div id="footer">
			<hr />
			<div id="footcontLeft">
				<p>LSOT 대표 전화 : 032-111-1111</p>
				<p>LSOT 주소 : 인천 남동구 학익동</p>
			</div>
			<div id="footcontRight">
				<a href="adminlogin.jsp"><img style="max-width: 100%;" src="./img/logo.png" /></a>				
			</div>
		</div>
	</div>
</body>
<script>
	Aj('header', '#header');
	function Aj(url, position) {
		$.ajax({
			url : url,
			type : 'get',
			success : function(html) {
				$(position).html(html);
			},
			error : function(error) {
				console.log(error);
			}
		});
	}
	function toSNS(sns, strTitle, strURL) {
		var snsArray = new Array();
		var strMsg = strTitle + " " + strURL;
		var image = "이미지경로";

		snsArray['twitter'] = "http://twitter.com/home?status="
				+ encodeURIComponent(strTitle) + ' '
				+ encodeURIComponent(strURL);
		snsArray['facebook'] = "http://www.facebook.com/sharer/share.php?u="
				+ encodeURIComponent(strURL);
		snsArray['pinterest'] = "http://www.pinterest.com/pin/create/button/?url="
				+ encodeURIComponent(strURL)
				+ "&media="
				+ image
				+ "&description=" + encodeURIComponent(strTitle);
		snsArray['band'] = "http://band.us/plugin/share?body="
				+ encodeURIComponent(strTitle) + "  "
				+ encodeURIComponent(strURL) + "&route="
				+ encodeURIComponent(strURL);
		snsArray['blog'] = "http://blog.naver.com/openapi/share?url="
				+ encodeURIComponent(strURL) + "&title="
				+ encodeURIComponent(strTitle);
		snsArray['line'] = "http://line.me/R/msg/text/?"
				+ encodeURIComponent(strTitle) + " "
				+ encodeURIComponent(strURL);
		snsArray['pholar'] = "http://www.pholar.co/spi/rephol?url="
				+ encodeURIComponent(strURL) + "&title="
				+ encodeURIComponent(strTitle);
		snsArray['google'] = "https://plus.google.com/share?url="
				+ encodeURIComponent(strURL) + "&t="
				+ encodeURIComponent(strTitle);
		window.open(snsArray[sns]);
	}

	function copy_clip(url) {
		var IE = (document.all) ? true : false;
		if (IE) {
			window.clipboardData.setData("Text", url);
			alert("이 글의 단축url이 클립보드에 복사되었습니다.");
		} else {
			temp = prompt("이 글의 단축url입니다. Ctrl+C를 눌러 클립보드로 복사하세요", url);
		}
	}

	function reviewAdd() {
		location.href='rvAdd?page=1';
	}
	$(function() {
		if ('${msg}' == "로그인 후 이용 가능합니다.") {
			alert('${msg}');
		}
	});
	var a = "";
	function searChg() {
		a = searchForm.selectValue.options[document.searchForm.selectValue.selectedIndex].value;
		return a;
	}
	function searchBtn() {
		searChg();
		var t_input = "";
		if (a == '제목') {
			t_input = document.searchForm.inputSerch.value;
			document.searchForm.action = 'rtitleBtn?t_title=' + t_input+'&page=1';
			document.searchForm.submit();
		} else if (a == '등록자') {
			t_input = document.searchForm.inputSerch.value;
			document.searchForm.action = 'ridBtn?t_mid=' + t_input+'&page=1';
			document.searchForm.submit();
		}
	}
</script>
</html>