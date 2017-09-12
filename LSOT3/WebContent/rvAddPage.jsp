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
	border: 1px solid gray;
	width: 60%;
	margin-left: 20%; 
}

div.lefter, div.righter {
	display: inline-flex;
	padding-bottom: 5%;
}

div.lefter {
	width: 30%;
	padding-left : 4%;
}

div.righter {
	width: 60%;
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
<script>
	function rvAddBtn() {

		document.rvaddForm.action = 'insertReview?page=1';
		document.rvaddForm.submit();

	}
</script>



<body>
	<div id="contents">
		<div id="header"></div>
		<div id="section">
			<div id="adder">
				<form name="rvaddForm" method="post" enctype="multipart/form-data">
					<h1>리뷰작성 페이지</h1>
					<div class="floor">
						<div class="lefter">아이디</div>
						<div class="righter">${id }</div>
					</div>
					<div class="floor">
						<div class="lefter">제목</div>
						<div class="righter">
							<input id="title" type="text" name="r_title" />
						</div>
					</div>
					<div class="floor">
						<div class="lefter">기부자이름</div>
						<div class="righter">
							<input type="text" name="r_name" />
						</div>
					</div>
					<div class="floor">
						<div class="lefter">봉사일</div>
						<div class="righter">
							<input type="date" name="r_when" />
						</div>
					</div>
					<div class="floor">
						<div class="lefter">기부내용</div>
						<div class="righter">
							<input type="text" style="height: 50px" name="r_content" />
						</div>
					</div>
					<div class="floor">
						<div class="lefter">장소</div>
						<div class="righter">
							<input type="text" name="r_place" />
						</div>
					</div>
					<div class="floor">
						<div class="lefter">느낀점 및 생각</div>
						<div class="righter">
							<textarea rows="5" cols="40" name="r_feeling"></textarea>
						</div>
					</div>
					<div class="floor">
						<div class="lefter">사진첨부1</div>
						<div class="righter">
							<input type="file" name="r_filename1" />
						</div>
					</div>
					<div class="floor">
						<div class="lefter">사진첨부2</div>
						<div class="righter">
							<input type="file" name="r_filename2" />
						</div>
					</div>
					<div class="floor">
						<div class="lefter">사진첨부3</div>
						<div class="righter">
							<input type="file" name="r_filename3" />
						</div>
					</div>
					<p>
						<input type="button" value="리뷰등록하기" class="btn"
							onclick="rvAddBtn()" />
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