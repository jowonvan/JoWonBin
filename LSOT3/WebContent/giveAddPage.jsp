<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
	min-height: 100%;
	position: relative;
	margin: 0px;
	padding: 0px;
}

#section {
	width: 80%;
	margin-left: 10%;
	padding-top: 10%;
	padding-bottom: 15%;
	text-align: center;
}

input[type=text] {
	width: 300px;
	height: 30px;
}

div#adder {
	width: 30%;
	margin-left: 35%; 
}

div.lefter, div.righter {
	display:block;
	padding-bottom: 3%;
}

div.lefter{
	float:left;
	clear:both;
	margin-left: 5%:
}
div.righter{
	float:right;
	clear:both;
}

#submitBtn{ 
	clear:both;
}

#footer {
	position: absolute;
	background-color: #ffffff;
	bottom: 0;
	left: 0;
	width: 100%;
	height: 10%;
}

#footcontLeft {
	float: left;
	width: 20%;
}

#footcontright {
	float: right;
	width: 20%;
}
</style>
</head>
<body>
	<div id="contents">
		<div id="header"></div>
		<div id="section">
			<div id="adder">
				<form id="addPageForm" action="giveAddPage" name="donateForm">
					<h1>재능 기부하기</h1>
					<div class="floor">
						<div class="lefter">제목</div>
						<div class="righter"><input type="text" name="title" /></div>
					</div>
					<div class="floor">
						<div class="lefter">재능 분야</div>
						<div class="righter">
							<select name="first" onchange="firstChange()" size=1>
								<option value="대분류">대분류</option>
								<option value="예술">예술</option>
								<option value="취미">취미</option>
								<option value="건축">건축</option>
								<option value="교육">교육</option>
								<option value="어학">어학</option>
								<option value="전문분야">전문분야</option>
								<option value="의료">의료</option>
								<option value="영상">영상</option>
								<option value="컴퓨터">컴퓨터</option>
							</select> 
							<select name=second size=1>
								<option value='대분류를 먼저 선택하세요'>대분류를 먼저 선택하세요</option>
							</select> 
						</div>
					</div>
					<div class="floor">
						<div class="lefter">재능 설명</div>
						<div class="righter">
							<textarea rows="5" cols="40" name="content"></textarea>
						</div>
					</div>
					<div class="floor">
						 <div class="lefter">희망지역</div>
						 <div class="righter"><input type="text" name="location" /></div>
					</div>
					<div class="floor">	 
						<div class="lefter">시작</div>
						<div class="righter"><input type="date" name="DateStart" /></div>
					</div>	 
					<div class="floor">
						<div class="lefter">종료</div> 
						<div class="righter"><input type="date" name="DateEnd" /></div>
					</div>
					<p id="submitBtn">	 
						<input type="submit" id="giveAdd" value="재능기부하기" />
					</p>
				</form>
			</div>
		</div>
		<div id="footer">
			<hr />
			<div id="footcontLeft">
				<p>LSOT 대표 전화 : 032-111-1111</p>
				<p>LSOT 주소 : 인천 남동구 학익동</p>
			</div>
			<div id="footcontright">
				<a href="adminlogin.jsp"><img style="max-width: 100%;"
					src="./img/logo.png" /></a>
			</div>
		</div>
	</div>
</body>
<script>	
	function firstChange() {// 대분류 변한 경우
	var x = document.donateForm.first.options.selectedIndex;//선택한 인덱스
	var groups = document.donateForm.first.options.length;//대분류 갯수
	var group = new Array(groups);//배열 생성
	for (i = 0; i < groups; i++) {
		group[i] = new Array();
	}//for
	// 옵션(<option>) 생성
	group[0][0] = new Option("대분류를 먼저 선택하세요", "");
	group[1][0] = new Option("예술 선택");
	group[1][1] = new Option("음악");
	group[1][2] = new Option("미술");
	group[1][3] = new Option("무용");
	group[2][0] = new Option("취미 선택");
	group[2][1] = new Option("요리");
	group[2][2] = new Option("악기");
	group[2][3] = new Option("스포츠");
	group[2][4] = new Option("무용");
	group[2][5] = new Option("사진");
	group[2][6] = new Option("문예");
	group[2][7] = new Option("원예");
	group[2][8] = new Option("공예");
	group[3][0] = new Option("건축 선택");
	group[3][1] = new Option("설비");
	group[3][2] = new Option("목공");
	group[3][3] = new Option("도배");
	group[3][4] = new Option("미장");
	group[3][5] = new Option("인테리어");
	group[4][0] = new Option("교육 선택");
	group[4][1] = new Option("컨설턴트");
	group[4][2] = new Option("심리치료");
	group[4][3] = new Option("미술치료");
	group[4][4] = new Option("음악치료");
	group[5][0] = new Option("어학 선택");
	group[5][1] = new Option("한국어");
	group[5][2] = new Option("중국어");
	group[5][3] = new Option("영어");
	group[5][4] = new Option("일본어");
	group[5][5] = new Option("한자");
	group[5][6] = new Option("기타");
	group[6][0] = new Option("전문 분야 선택");
	group[6][1] = new Option("법률");
	group[6][2] = new Option("노무");
	group[6][3] = new Option("세무");
	group[6][4] = new Option("회계");
	group[6][5] = new Option("문서기록");
	group[6][6] = new Option("편집");
	group[7][0] = new Option("의료 선택");
	group[7][1] = new Option("한의학");
	group[7][2] = new Option("양의학");
	group[7][3] = new Option("약학");
	group[7][4] = new Option("취위생");
	group[7][5] = new Option("간호");
	group[8][0] = new Option("영상 선택");
	group[8][1] = new Option("영상촬영");
	group[8][2] = new Option("영상편집");
	group[9][0] = new Option("컴퓨터 선택");
	group[9][1] = new Option("c언어");
	group[9][2] = new Option("c++");
	group[9][3] = new Option("JAVA");
	group[9][4] = new Option("FRONT");
	group[9][5] = new Option("홈페이지 제작");

	temp = document.donateForm.second;//두번 째 셀렉트 얻기(<select name=second>)
	for (m = temp.options.length - 1; m > 0; m--) {//현재 값 지우기
		temp.options[m] = null
	}
	for (i = 0; i < group[x].length; i++) {//값 셋팅
		//예) <option value="ss">삼성</option>
		temp.options[i] = new Option(group[x][i].text, group[x][i].value);
	}
	temp.options[0].selected = true//인덱스 0번째, 즉, 첫번째 선택
}//firstChange
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
</script>
</html>