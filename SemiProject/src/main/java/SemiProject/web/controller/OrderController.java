package SemiProject.web.controller;

import java.io.BufferedReader;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import SemiProject.web.service.OrderService;
import SemiProject.web.vo.MemberVO;
import SemiProject.web.vo.OrderVO;

@Controller
public class OrderController {
	@Autowired OrderService orderService;
	
	///////////// 주문 처리 //////////////////
	@RequestMapping(value = "orderProducts", 
			method= {RequestMethod.POST},
			produces = "application/text; charset=utf8")			
	@ResponseBody
	public String order(HttpServletRequest request,
			HttpServletResponse response){
			JSONObject json=null;
			//System.out.println("orderProducts 호출");
		try {
			BufferedReader br=request.getReader();
			JSONParser parser=new JSONParser();
			json=(JSONObject) parser.parse(br);
			JSONArray array=(JSONArray) json.get("product");
			
			Object []array2=array.toArray();
			
			ArrayList<OrderVO> list=new ArrayList<OrderVO>();
			for(Object o : array2) {
				
				JSONObject j=(JSONObject) o;
				String prodname=(String) j.get("name");
				long quantity=(Long) j.get("quantity");
				OrderVO orderVO=new OrderVO("web",prodname,quantity);
				HttpSession session=request.getSession();
				MemberVO memberVO=(MemberVO) session.getAttribute("member");
				//System.out.println("session vo :" +memberVO);
				if(memberVO!=null) {//로그인 된 사용자라면 id를 추가해준다
					orderVO.setMemberid(memberVO.getId());
				}else {
					orderVO.setMemberid("");
				}
				
				list.add(orderVO);
			}
			
			System.out.println("총 "+list.size()+"개 품목을 주문합니다");
			System.out.println("list: "+list);
			long order_group_no=orderService.ordersInsert(list);
			
			json=new JSONObject();			
			
			if(true) {		
				json.put("order_group_no", order_group_no);
				
			}else {
				json.put("msg", "주문 실패");
			}
		}catch(Exception e) {
			e.printStackTrace();
			json.put("msg", e.getMessage());
		}	
		return json.toJSONString();		
	}
}
