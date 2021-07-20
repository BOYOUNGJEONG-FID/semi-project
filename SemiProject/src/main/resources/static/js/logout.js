$(document).ready(function(){

	let logined=$.cookie('logined');
	if(logined){
		//console.log("if문 실행");
		document.getElementById("loginSpan").innerHTML=logined+"님 환영합니다 <button id='logoutBtn'>로그아웃</button> <button id='memberDeleteBtn'>회원탈퇴</button>";
	}
	
	//탈퇴
	$(document).on('click','#memberDeleteBtn',function(){
		const r=confirm("정말 탈퇴하실 건가요?");
		  if (r == true) {
			let logined=$.cookie('logined');
			console.log("logined: "+logined);
		    $.post(
				"deleteMember",
				{"id": logined},
		    	function(data){
		    		alert(data);
		    		if(data=="회원 삭제 완료"){
			    		$.removeCookie("logined");
			    		if($.cookie("basket")){
			    			$.removeCookie("basket");}
			    		location.reload();
			    	}
		    	}
		    );
		  }
	});
	
	//로그아웃
	$(document).on('click','#logoutBtn',function(){
		
		    $.post("logout",
		    	function(data){	
					console.log(data);
		    		alert(data);
		    		$.removeCookie("logined"); 	
		    		if($.cookie("basket")){
		    			$.removeCookie("basket");}
		    		//$.removeCookie('JSESSIONID',{path:'/'});	    		  		
		    		location.reload();
		    	}
		    );
		
	});
});