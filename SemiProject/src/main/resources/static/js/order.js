$(document).ready(function(){
	$("#orderBtn").click(function(){
		let confirm_data=confirm("다음과 같이 주문하시겠습니까?\n"+items);
		if(confirm_data){
			const basket = $.cookie("basket");

			$.post("../orderProducts",
				  basket,
				  function(data, status){	
				  	console.log(data);
				  	const obj=JSON.parse(data);
				  	if(obj.order_group_no){	
				  		alert("주문완료:[주문번호]"+obj.order_group_no);	
				  		$.removeCookie("basket", { path: '/' });// 장바구니 쿠키 삭제	
				  	}else{
				  		alert(obj.msg);
				  	}
				  	window.close();			   
				  }
			);//end post() 			
		}		

	});
	
	$("#anotherBtn").click(function(){
		window.close();
	});
	
	$("#cancelBtn").click(function(){
		alert("장바구니를 모두 비웁니다");
		$.removeCookie("basket", {path:'/'});
		window.close();
	});

	$(".orderForm").click(function(event){
		let choice_product_name=event.target.name;
		let basket=$.cookie("basket");
		let obj=null;
		if(basket){ //쿠키에 장바구니가 있으면
			obj=JSON.parse(basket);//json을 javascript 객체로 바꿔서
			let flag=true;
			obj.product.forEach(function(item){ // 하나씩의 아이템(품목)을 꺼내서
				
				if(item.name==choice_product_name){//품목 이름이 클릭한 상품의 이름과 같으면
					item.quantity +=1; //상품의 수량을 1증가
					//alert("if:"+item.name+":"+item.quantity);
					flag=false;
				}
				});
				if(flag){//품목 이름이 클릭한 상품의 이름과 다르면
					obj.product.push({name:choice_product_name,quantity:1});//새 품목 추가
					//alert("else:"+obj.product[obj.product.length-1].name+":"+obj.product[obj.product.length-1].quantity);
				}
			 
			
		}else{ //쿠키에 장바구니가 없으면 
			//alert("choice_product_name: "+choice_product_name);
			
			obj={product:[{name:event.target.name,quantity:1}]
			};
		}
		basket=JSON.stringify(obj);
		$.cookie("basket", basket, {path:'/'});
		window.open('html/orderForm.html','_blank', 'toolbar=yes, scrollbars=yes, resizable=yes,top=50, left=500,width=600,height=350');
		
	});
});