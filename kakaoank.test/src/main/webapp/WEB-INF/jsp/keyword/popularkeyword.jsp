<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

       
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="content" data-content="content-2">
	<h2>�α� �˻���</h2>
	<div class="board_type1_wrap">
		<table class="board_list_type1">
			<thead>
				<tr>
					<th>Ű����</th>
					<th>��ȸ��</th>
				</tr>
			</thead>
			<tbody id=tbody-contents>
				<c:choose>
				<c:when test="${not empty popularlist}">
				  <c:forEach items="${popularlist}" var="vo" varStatus="idx">
				    <tr class="">
				      <td class="active_type"><c:out value="${vo.keyword}"/></td>			
				      <td class="active_type"><c:out value="${vo.inqCnt}"/></td>
				    </tr>
				  </c:forEach>
				</c:when>
				<c:otherwise>
				  <tr>
				    <td colspan="2">��ȸ�� �ڷᰡ �����ϴ�.</td>
				  </tr>
				</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</div>    