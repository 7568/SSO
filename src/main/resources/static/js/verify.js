/**
 * 
 */
$(function(){
	
	$.ajax({
		   async:false,
		   url: $('#SSOurl').val()+'/getUserTicket',
		   type: 'GET',
		   dataType: 'jsonp',
		   jsonp: 'callback',
		   data: "",
		   timeout: 5000,
		   success: function (backdata) {
		    if(backdata.result.length==0){
		    	window.location.href=$('#SSOurl').val()+"?redicrect="+window.location.href;
		    }else{
		    	document.cookie="ticket="+backdata.result;
		    	window.location.href = window.location.href;
		    }
		   }
		});
});