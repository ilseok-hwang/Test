<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
 <header class="jumbotron my-4">
    <h1 class="display-3">ȸ�� ���� �Ϸ�</h1>
    <p class="lead">ȯ�� �մϴ�. �α��� �� �̿��� �ּ���</p>
  </header>
  
  <button class="btn btn-primary btn-block"  onclick="javascript:goloin();">�α���</button>
 
</div>


<script>
	function goloin(){
		ajaxLoad('GET', '/loginForm', 'form-join', null , "errmsg");
	}
</script>	