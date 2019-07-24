<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
 <header class="jumbotron my-4">
    <h1 class="display-3">회원 가입 완료</h1>
    <p class="lead">환영 합니다. 로그인 후 이용해 주세요</p>
  </header>
  
  <button class="btn btn-primary btn-block"  onclick="javascript:goloin();">로그인</button>
 
</div>


<script>
	function goloin(){
		ajaxLoad('GET', '/loginForm', 'form-join', null , "errmsg");
	}
</script>	