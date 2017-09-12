<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>

#findForm{
	width:40%;
	margin-left: 30%;
	text-align: center;
}

#findId, #findPw{
	margin-top:1%;
	margin-bottom: 1%;
}

.sizing {
	height: 4%;
	margin-bottom: 1%;
	border-radius: 3px;
}
</style>
</head>
<body>
	<div id="findForm">
		<div id="findId">
			<form id="idFindForm">
				<p>
					이름 : <input type="text" name="name" class="sizing" placeholder="아이디 찾기" />
				</p>
				<p>
					이메일 : <input type="text" name="email" class="sizing" /> 
				</p>
				<input type="button" id="idFinder" value="아이디 찾기" class="sizing" />
			</form>
		</div>
		<hr />
		<div id="findPw">
			<form id="pwFindForm">
				<p>
					아이디 : <input type="text" name="id" class="sizing" placeholder="비밀번호 찾기" />
				</p>
				<p>
					질문 : <select name="pwQuestion" class="sizing">
						<option value="Q1">당신의 보물 1호는?</option>
						<option value="Q2">반려동물의 이름은?</option>
						<option value="Q3">가장 존경하는 인물은?</option>
						<option value="Q4">가장 기억에 남는 장소는?</option>
						<option value="Q5">처음으로 간 해외여행 장소는?</option>
						<option value="Q6">가장 즐겨했던 게임은?</option>
						<option value="Q7">가장 좋아하는 연예인은?</option>
					</select>
				</p>
				<p>
					답 : <input type="text" name="pwAnswer" class="sizing" />
				</p> 
				<input type="button" id="pwFinder" value="비밀번호 찾기" class="sizing" />
			</form>
		</div>
		<hr />
		<div id="findResult">
			<p>
				찾은 값 : <span>${yours}</span>
			</p>
			<input type="button" value="홈으로" id="toHome" class="sizing" />
		</div>
	</div>
</body>
<script>
	$(function() {
		$('#idFinder').click(function() {
			var formData = $('#idFindForm').serialize();
			Ajf('findId', formData, '#section');
		});
		$('#pwFinder').click(function() {
			var formData = $('#pwFindForm').serialize();
			Ajf('findPw', formData, '#section');
		});

		$('#toHome').click(function() {
			location.href = 'Index';
		});
	});
</script>
</html>