<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>


   <div class="container centered text-center">
       <form class="form-join" method="POST" action="/member/joinComplete">
           <h1 class="h3 mb-3 font-weight-normal">ȸ�������� �Է��ϼ���!</h1>
           <label for="username" class="sr-only">���̵�</label>
               <input type="text" id="username" name="userId" class="form-control" placeholder="���̵�" >
           <label for="password" class="sr-only">��й�ȣ</label>
               <input type="password" id="password" name="password" class="form-control" placeholder="��й�ȣ">
           <label for="name" class="sr-only">name</label>
               <input type="text" id="userName" name="userName" class="form-control" placeholder="�̸�">
           <label for="eamil" class="sr-only">email</label>
               <input type="email" id="email" name="email" class="form-control" placeholder="E-Mail">
           
           <div align="center" >
               <p style="font-size: 20; color: #FF1C19;" id="errmsg"></p>
           </div>
           <button class="btn btn-primary btn-block"  onclick="javascript:doJoin(); return false;">���</button>
       </form>
   </div>



<script>
	function doJoin(){
		
		var userNm = $('#username').val();
		var pw = $('#password').val();
		var email = $('#email').val();
		
		if(!(typeof userNm == 'string' && userNm.length > 0)){
			alert('���̵� �Է��ϼ���. �ʼ� �׸��Դϴ�.');
			return;
		}
		
		if(!(typeof pw == 'string' && pw.length > 0)){
			alert('��й�ȣ�� �Է��ϼ���.  �ʼ� �׸��Դϴ�.');
			return;
		}
		
		if((typeof email == 'string' && email.length > 0)){
			var rex = /^[-!#$%&'*+./0-9=?A-Z^_a-z{|}~]+@[-!#$%&'*+/0-9=?A-Z^_a-z{|}~]+.[-!#$%&'*+./0-9=?A-Z^_a-z{|}~]+$/;
			
			if(!rex.test(email)){
				alert('�̸��� �ּҸ� Ȯ�� �ϼ���');
				return;
			}
		}
		
		ajaxLoad('POST', '/member/joinComplete', 'form-join', null , "errmsg");
	}
</script>	