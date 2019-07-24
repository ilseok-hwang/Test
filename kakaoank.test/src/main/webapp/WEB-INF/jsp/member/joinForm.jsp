<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>


   <div class="container centered text-center">
       <form class="form-join" method="POST" action="/member/joinComplete">
           <h1 class="h3 mb-3 font-weight-normal">회원정보를 입력하세요!</h1>
           <label for="username" class="sr-only">아이디</label>
               <input type="text" id="username" name="userId" class="form-control" placeholder="아이디" >
           <label for="password" class="sr-only">비밀번호</label>
               <input type="password" id="password" name="password" class="form-control" placeholder="비밀번호">
           <label for="name" class="sr-only">name</label>
               <input type="text" id="userName" name="userName" class="form-control" placeholder="이름">
           <label for="eamil" class="sr-only">email</label>
               <input type="email" id="email" name="email" class="form-control" placeholder="E-Mail">
           
           <div align="center" >
               <p style="font-size: 20; color: #FF1C19;" id="errmsg"></p>
           </div>
           <button class="btn btn-primary btn-block"  onclick="javascript:doJoin(); return false;">등록</button>
       </form>
   </div>



<script>
	function doJoin(){
		
		var userNm = $('#username').val();
		var pw = $('#password').val();
		var email = $('#email').val();
		
		if(!(typeof userNm == 'string' && userNm.length > 0)){
			alert('아이디를 입력하세요. 필수 항목입니다.');
			return;
		}
		
		if(!(typeof pw == 'string' && pw.length > 0)){
			alert('비밀번호를 입력하세요.  필수 항목입니다.');
			return;
		}
		
		if((typeof email == 'string' && email.length > 0)){
			var rex = /^[-!#$%&'*+./0-9=?A-Z^_a-z{|}~]+@[-!#$%&'*+/0-9=?A-Z^_a-z{|}~]+.[-!#$%&'*+./0-9=?A-Z^_a-z{|}~]+$/;
			
			if(!rex.test(email)){
				alert('이메일 주소를 확인 하세요');
				return;
			}
		}
		
		ajaxLoad('POST', '/member/joinComplete', 'form-join', null , "errmsg");
	}
</script>	