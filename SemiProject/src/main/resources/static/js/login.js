$(document).ready(function(){
	$("#loginBtn").click(function(){
		const id =$("#id").val();
		const pw =$("#pw").val();
		$.post(
			"loginById",
			{id, pw},
			function(data){
				if(data){
					data=JSON.parse(data);
					if(data.status=="Success"){
						$.cookie("logined", data.id);
						document.getElementById("loginSpan").innerHTML=data.id+"님 환영합니다 <button id='logoutBtn'>로그아웃</button> <button id='memberDeleteBtn'>회원탈퇴</button>";
					}else{
						alert("다시 로그인해주세요");
						location.reload();
					}
				}else{
					alert("다시 로그인해주세요");
				}
			}
		)
		
	})
});