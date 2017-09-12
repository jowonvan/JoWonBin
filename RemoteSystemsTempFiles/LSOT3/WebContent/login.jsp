<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
#loginForms {
   width: 30%;
   padding-top:8%;
   padding-left:35%;
   text-align: center;
}

.inputs{
   width: 100%;
   height:50px;
   border-radius: 5px;
   
}

.regist{
   width: 48%;
   height:3%;
   border-radius: 3px;
   background-color: white;
   border : 2px solid #082d71;
}
.regist:hover{
   background-color: #082d71;
   color: white;
}

a{
   color: black;
}
a:HOVER {
   text-decoration: blink;
}

#loginBtn{
   background-color: white;
   border : 2px solid #082d71;
}
#loginBtn:hover{
   background-color: #082d71;
   color: white;
}
</style>
</head>
<body>
   <div id="loginForms">
      <h1>LSOT Login</h1>
      <form id="loginForm" action="login" method="post">
         <p>
            <input class="inputs" type="text" placeholder="아이디를 입력하십시오." name="id" /> 
            <input class="inputs" type="password" placeholder="비밀번호를 입력하십시오." name="pw" /> 
         </p>
         <p>
            <input class="inputs" type="submit" id="loginBtn" value="Login" />
         </p>
      </form>
      <p>
         <input class="regist" type="button" onclick="Aj('AjaxIndi', '#section')" value="일반 가입"/> 
         <input class="regist" type="button" onclick="Aj('AjaxGroup', '#section')" value="단체 가입"/> 
      </p>
      <a href="javascript:;" id="findTr" 
      onclick="Aj('AjaxFind','#section')">아이디 / 비밀번호 찾기</a>
   </div>
</body>
<script>
</script>
</html>