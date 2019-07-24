<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<form class="login-form" method="POST" action='/login'> 
  <div class="row">
    <div class="input-field col s12">
      <label style='width:70px' for="email"> 아이디 </label>
      <input id="username" type="text" name="username" class="validate"/>
    </div>
  </div>
  <div class="row">
    <div class="input-field col s12">
    <label style='width:70px' for="password">비밀번호</label>
      <input id="password" type="password" name="password" class="validate"/>
    </div>
  </div>
  <div align="center" >
       <p style="font-size: 20; color: #FF1C19;" id="errmsg"></p>
  </div>
   <button class="login-btn waves-effect waves-light btn"  onclick="javascript:login(); return false;">로그인</button>
 <!--  <button class="login-btn waves-effect waves-light btn" type="submit" name="submit">로그인</button>> -->
   <button class="login-btn waves-effect waves-light btn"  onclick="javascript:join(); return false">가입</button>
   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>



<script>
	function login(){
		var userNm = $('#username').val();
		var pw = $('#password').val();
		
		if(!(typeof userNm == 'string' && userNm.length > 0)){
			alert('아이디를 입력하세요');
			return;
		}
		
		if(!(typeof pw == 'string' && pw.length > 0)){
			alert('아이디를 입력하세요');
			return;
		}
		
		ajaxLoad('POST', '/login', 'login-form', null, "errmsg", null, false);
	}
	
	function join(){
		ajaxLoad('GET', '/member/joinForm', '', null, "errmsg");
	}
	
</script>	