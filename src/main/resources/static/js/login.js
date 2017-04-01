$(function(){
	$('#login').click(function(){
		var user = new Object();
		user.username=$('#username').val();
		user.pwd = $('#userpwd').val();
		$.post("/validate",user,function(data){
			console.log(data);
			if(data == "true"){
				var localhref = window.location.href;
			
				window.location.href = localhref.split("?")[1].split("&")[0].split('=')[1];
			}else{
				alert('用户名或者密码错误！');
			}
		});
	});
});