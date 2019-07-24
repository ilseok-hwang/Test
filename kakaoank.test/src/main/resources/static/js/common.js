/*startsWith IE에서 로드가 안되는 경우가 발생
 * 따라서 프로토 타입을 정의해서 메서드가 없는경우 설정*/
if (!String.prototype.startsWith) { 
    String.prototype.startsWith = function(searchString, position){ 
     position = position || 0; 
     return this.substr(position, searchString.length) === searchString; 
    }; 
}

/**
 * ajax 호출 공통 
 * @param sendMethod (GET or POST)
 * @param url
 * @param form (formid or data)
 * @param sucsessResultId (target element id)
 * @param errorResultId	(print errormessage element id, if errorResultId is null, print error message on alert )
 * @param callback
 * @param isAsync
 * @returns
 */
function ajaxLoad(sendMethod, url, form, sucsessResultId, errorResultId, callback, isAsync){
	if(form == null || typeof form == 'undefined' || form.length == 0 ){
		form = "dummyForm";
	}
	
	if(sendMethod == null || typeof sendMethod == 'undefined' || sendMethod.length == 0 ){
		sendMethod = "GET";
	}
	
	var asyncYn = false;
	
	if(isAsync!=null && isAsync == true){
		asyncYn = true;
	}
	
	var formData = null;
	
	if(typeof form == 'string'){
		formData = $("."+ form).serialize();
	}
	else if(typeof form == 'object'){
		formData = form;
	}

	$.ajax({
		cache : false,
		type:sendMethod, 		// 전송방식
		url:url,				// url
		data:formData, 			// data
		async: asyncYn,			// 비동기 전송 여부
		/**
		 * ajax 호출 성공 공통 처리 메소드 
		 */
		success:function(request, status, error){ // 성공시 자동 호출되는 데이터 매개변수는 들어오는 데이터임.
		
			//ajax request 렌더링 목적  div
			var targetDiv = "";
			if(sucsessResultId != null && typeof sucsessResultId == 'string' && sucsessResultId.length>0){
				targetDiv = '#'+sucsessResultId;
			}
			else{
				//default id
				targetDiv = "#main_container";
			}			
			
			//렌더링
			if(request != null && typeof request == 'string' && !request.startsWith("{", 1)){
				$(targetDiv).html(request);	
			}
			
			/**
			 * 호출 시 콜백이 존재하는 경우 콜백 호출
			 */
			if(callback != null && typeof callback == 'function'){
				callback(request, status);
			}
		
			return;
		},
		
		/**
		 * ajax 호출 오류 처리 메소드
		 */
		error:function(request, status, error){
			var msg = '처리 중 오류가 발생했습니다. ';
			
			if(request != null && typeof request.responseText == 'string'){
				 msg += request.responseText;
			}
			
			if(errorResultId != null && typeof errorResultId == 'string'){
				$('#' + errorResultId).html(msg);
			}else{
				alert(msg);	
			}
			
			
			return;
		}
	});
}
	
commonjs = function(){
	
	return {
		/**
		 * 도서 상세조회를 위해 레이어팝업을 호출
		 */
		openLayerPopup : function(index){
			var targetDiv = "";
			
			var item = commonjs.sarchbook.getListItem(index);
			var author = "";
			for(var i = 0 ; i < item.authors.length; i++){
				author += (((i==0)?  "":",") + item.authors[i]);
			}
			
			/**
			 * 도서 상세를 호출
			 */
			item.author = author;
			ajaxLoad('POST', '/booksearch/inquirydetail', item, "layer-contents" , "errmsg", null, false);
			
			this.createLayerPopup($('#layer2'));
		},
		
		/** 레이어 팝업을 생성한다
		 * @e elelement
		 */
	    createLayerPopup : function(e){
	        var $el = $(e);      						//레이어의 id를 $el 변수에 저장
	        var isDim = $el.prev().hasClass('dimBg');   //dimmed 레이어를 감지하기 위한 boolean 변수

	        //딤 설정여부를 판단하여 fade in 객체를 선택한다
	        isDim ? $('.dim-layer').fadeIn() : $el.fadeIn();

	        //레이어팝업 위치를 계산하기 위해 document 및 elements의 크기를 구한다.
	        var $elWidth = ~~($el.outerWidth()),
	            $elHeight = ~~($el.outerHeight()),
	            docWidth = $(document).width(),
	            docHeight = $(document).height();

	        // 화면의 중앙에 레이어를 띄운다.
	        if ($elHeight < docHeight || $elWidth < docWidth) {
	            $el.css({
	                marginTop: -$elHeight /2,
	                marginLeft: -$elWidth/2
	            })
	        } else {
	            $el.css({top: 0, left: 0});
	        }

	        //close 이벤트 등록
	        $el.find('a.btn-layerClose').click(function(){
	            isDim ? $('.dim-layer').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
	            return false;
	        });
	        //close 이벤트 등록
	        $('.layer .dimBg').click(function(){
	            $('.dim-layer').fadeOut();
	            return false;
	        });
	    },
	    
	    loginOk : function(){
	    	
	    },
		
	    dummy : null
	};
}();

commonjs.sarchbook = function() {
	//도서 조회 리스트
	var CURRENT_LIST = null;
	
	return {
		
		getListItem : function(index){
			if(CURRENT_LIST != null && typeof CURRENT_LIST == 'object' && (CURRENT_LIST.length >= index)){
				return CURRENT_LIST[index];
			}
			return null;
		},
			
		/** 책 조회 
		 * @param page 
		 */
		search : function(page){
			if(page != null && typeof page != 'undefined'){
				$('#page').val(page);
			}
			ajaxLoad('GET', '/booksearch/search', 'search-form', "dummy" , "errmsg", this.search_callback);
		},
		
		

		/** 책 조회 ajax 호출 후, return 되는 json을 렌더링하기 위해
		 * 받는 callback 함수
		 * @request request 
		 * @status status
		 */
		search_callback : function(request, status){
			if(status=='success'){
				if(typeof request == 'object'){
					var html = "";
					
					var meta = request.meta;
					CURRENT_LIST = request.documents;
					
					var totalCnt = meta.pageable_count;
					var pagesize = $('pagesize').val();
					
					if(typeof pagesize == undefined || pagesize == null || pagesize == ""){
						pagesize = 15;
					}
					var totalPageCnt = (totalCnt/pagesize)+1;
					
					//리스트 렌더링
					$('#tbody-contents').html(commonjs.sarchbook.listItemRender(CURRENT_LIST));
					
					//페이징 렌더링
					commonjs.sarchbook .searchPagingRender('paging-contents', 'search-form', $('#page').val(), totalPageCnt, pagesize, "commonjs.sarchbook.search");
					
					//검색어 저장
					ajaxLoad('GET', '/keyword/add', 'search-form', "dummy" , "errmsg", null, true);
				}
			}
		},
		
		
		/** 리스트 아이템 렌더링
		 * */
		listItemRender : function(list) {
			var html = "";
			if(list != null && typeof list == 'object'){
				for(var i = 0 ; i < list.length ; i++){
					var author = '';
					for(var k = 0; k < list[i].authors.length; k++){
						author += ((list[i].authors[k]) + " ");
					}
					
					html += ("<tr onclick='javascript:commonjs.openLayerPopup(" + i + ", this); return false;'>\n");
					html += ("<td class='left'>" + list[i].title + "</td>\n");
					html += ("<td class='active_type'>" + author + "</td>\n");
					html += ("<td class='active_type'>" + list[i].publisher + "</td>\n");
					html += ("</tr>\n");
				}
			}
			return html;
		},
		
		
		/** 페이징 렌더링
		 * 한 화면에 보여줄 페이지 개수(윈도우)만큼 페이징 처리 기능
		 * 처음 이전 다음 끝 버튼으로 이동 기능
		 * 
		 * @targetDivId 렌더링 후 복제될 div id
		 * @formId formid
		 * @currPageNo 현재 페이지 번호
		 * @totalPageNcnt 전체 페이지 개수
		 * @pageSize 페이지크기
		 * @functionNm callback
		 */
		searchPagingRender : function(targetDivId, formId, currPageNo, totalPageNcnt, pageSize, functionNm) {
			var pageNo = 1;				//현재 페이지 번호
			var pageCnt = 1;			//조회된 페이지 총 개수
			var prevPageIndex 	= 1;	//다음버튼 인덱스
			var nextPageIndex 	= 1;	//이전버튼 인덱스
			
			var showPageCnt 	= 5;	// 페이지 표시 개수
			var startPageNo	  	= 1;	// 페이지 번호 시작 인덱스
			var endPageNo   	= 1;	// 페이지 번호 끝 인덱스	
			
			try{
				pageNo  = parseInt(currPageNo);
				pageCnt = parseInt(totalPageNcnt);
				
				//시작 페이지번호를 구한다.
				startPageNo = (((parseInt((pageNo-1) / showPageCnt))*showPageCnt) + 1);
				
				//끝 페이지 번호 구한다.
				if((startPageNo + showPageCnt) > pageCnt){	
					//마지막 페이지 번호가 총 페이지 크기보다 큰경우
					endPageNo = pageCnt;	//총 페이지 크기로 고정
				}
				else{
					endPageNo = (startPageNo + showPageCnt) - 1;
				}
				
				//이전 페이지 인덱스를 구한다
				if((startPageNo - showPageCnt) < 1 ){
					prevPageIndex = 1;
				}
				else{
					prevPageIndex = (startPageNo - showPageCnt);
				}
				
				//다음 페이지 인덱스를 구한다
				if((startPageNo + showPageCnt) > pageCnt ){
					nextPageIndex = pageCnt;
				}
				else{
					nextPageIndex = (startPageNo + showPageCnt);
				}
			}catch(e){
				;
			}
			
			var html = "";

			//make first page btn
			html +=	('<a href="' + this.pagingRenderForMakeFunc(1, 1, pageCnt, pageSize, formId, functionNm) 			 + '" title="처음 페이지로 이동" ><< 처음</a>');
			//make prev page btn				
			html +=	('<a href="' + this.pagingRenderForMakeFunc(2, prevPageIndex, pageCnt, pageSize, formId, functionNm) + '" title="이전 5개 목록으로 이동" >< 이전</a>');
			//페이징
			
			for(var i = startPageNo; i <= endPageNo; i++ ){
				html +=	('<a href="' + this.pagingRenderForMakeFunc(0, i, pageCnt, pageSize, formId, functionNm));
				if(i == currPageNo){
					html += ('" class="current">'+ i +'</a>');
				}
				else{ 
					html += ('">'+ i +'</a>');
				}
			}
			
			//make next page btn
			html +=	('<a href="' + this.pagingRenderForMakeFunc(3, nextPageIndex, pageCnt, pageSize, formId, functionNm) + '" title="다음 5개 목록으로 이동" >다음 ></a>');
			//make end page btn				
			html +=	('<a href="' + this.pagingRenderForMakeFunc(4, pageCnt, pageCnt, pageSize, formId, functionNm) 		 + '" title="끝 페이지로 이동" >끝 >></a>');
			
			//html append
			$('#'+ targetDivId ).html(html);
		},
		
		/** 페이징 href 생성 
		 */
		pagingRenderForMakeFunc : function(type, currPageNo, totalPageNcnt, pageSize, formId, functionNm) {
			var func = "";			
			switch(type){
				case 1:	//처음 페이지 이동
					{
						func =  "javascript:" + functionNm + "(1);";
					}					
					break;
				case 2:	//이전 페이지 이동
					{
						if(currPageNo > 0){
							func =  "javascript:" + functionNm + "(" + currPageNo + ");";
						}
						else{
							func =  "javascript:" + functionNm + "(1);";
						}
					}
					break;
				case 3:	//다음 페이지 이동
					{
						if(currPageNo < totalPageNcnt){
							func =  "javascript:" + functionNm + "(" + currPageNo + ");";
						}
						else{
							func =  "javascript:" + functionNm + "(" + totalPageNcnt + ");";	
						}
					}
					break;
				case 4:	//마지막 페이지 이동
					{
						func =  "javascript:" + functionNm + "(" + totalPageNcnt + ");";
					}
					break;
				case 0:	//지정 페이지 이동
				{
					func =  "javascript:" + functionNm + "(" + currPageNo + ");";
				}
				break;
				default:
					func = "#";
			}
			
			if(func == ""){
				func = "#";
			}
			
			return func;
		},
		
		dummy : null
	};//[end]return
}();