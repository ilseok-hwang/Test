<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<div class="content" data-content="content-2">
	<h2>���� �˻�</h2>
	
	
<div class="board_type1_wrap">
	<div class="board_type1_write_wrap">
		<form class="search-form" id='search-form' name='search-form' method="GET"> 
			<table class="board_write_type1">
				<tr>
					<td class="left">
						<ul class="split_three">
							<li>
								<input type="hidden" name="page" id="page" value='1'/>
								<input type="hidden" name="pagesize" id="page" value='15'/>
								<div class="column_name">�˻���</div>
								<div class="column_desc"><input type="text" id="inqValue" name="inqValue" class="text_type1"/></div>
							</li>
							<li>
								<div class="column_name">�˻�����</div>
								<div class="column_desc">
											<select name="inqType" id="inqType" >
										          <option value="title">����</option>
										          <option value="isbn">ISBN</option>
										          <option value="publisher">���ǻ�</option>
										          <option value="person">����</option>
     										</select>
     							</div>
							</li>
							
							<li>
								<div class="column_name">�ɼ�</div>
								<div class="column_desc">
									<div class="checkwrap_type1">
										<input type="radio" id="radio-1"  name="sort" value="recency" class="check_type1" checked=checked /><label for="radio-1">�ֽż�</label>
									</div>
									<div class="checkwrap_type1">
										<input type="radio" id="radio-2"  name="sort" value=accuracy class="check_type1" /><label for="radio-2">��Ȯ����</label>
									</div>
									<div><button style="width:60px"" onclick="javascript:commonjs.sarchbook.search(); return false;">�˻�</button></div>
								</div>
							</li>	
						</ul>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div class="board_type1_wrap">
		<table class="board_list_type1">
			
			<thead>
				<tr>
					<th class="active_type">����</th>
					<th class="active_type">����</th>
					<th class="active_type">���ǻ�</th>
				</tr>
			</thead>
			<tbody id=tbody-contents>
				
			</tbody>
		</table>
	</div>
</div>
	<div class="margin"   >
		<div class='list_number'>
			<p>
				<!-- ����¡ ���� -->
				<div class="list_n_menu" id='paging-contents'>
				</div>
			</p>
		</div>
	</div>
</div>
<div class="layer">
	<div class="dim-layer">
	    <div class="dimBg"></div>
	    <div id="layer2" class="pop-layer">
	        <div class="pop-container">
	            <div class="pop-conts" >
	            	<div id="layer-contents">
	                <!--content //-->
		           
	                </div>
	                <div class="btn-r">
	                    <a href="#" class="btn-layerClose">Close</a>
	                </div>
	                <!--// content-->
	            </div>
	        </div>
	    </div>
   </div>
</div>
