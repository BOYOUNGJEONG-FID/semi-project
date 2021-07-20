package SemiProject.web.controller;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import SemiProject.web.service.MemberService;
import SemiProject.web.service.OrderService;
import SemiProject.web.vo.MemberVO;
import SemiProject.web.vo.OrderVO;

@Controller
public class MemberController {
	@Autowired
	MemberService memberService;

	@ResponseBody
	@RequestMapping("/memberInsert")
	public String memberInsert(HttpServletRequest request,HttpServletResponse response) {
		//System.out.println("memberInsert() 호출됨");
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		String name=request.getParameter("name");
		
		MemberVO vo=new MemberVO(id, pw, name);
		//System.out.println(vo);

		try {
			memberService.insertMember(vo);
			return "Success";
		}catch(DataAccessException e) {
			return "fail";
		}		
	}
	
	@ResponseBody
	@RequestMapping("/loginById")
	public String loginById(HttpServletRequest request,HttpServletResponse response){
		try {
			String id=request.getParameter("id");
			String pw=request.getParameter("pw");
			MemberVO memberVO=new MemberVO(id, pw, null);
			memberVO= memberService.loginById(memberVO);
			JSONObject json=new JSONObject();
			if (memberVO!=null) {
				HttpSession session=request.getSession();
				session.setAttribute("member", memberVO);
				json.put("id", id);
				json.put("status", "Success");
				return json.toJSONString();
			}else {
				json.put("id", id);
				json.put("status", null);
				return json.toJSONString();
			}
		}catch(DataAccessException e) {
			e.printStackTrace();
			return "로그인 실패";
		}
	}
	
	@ResponseBody
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response){
		try {
			return "로그아웃 되었습니다";
		}catch(DataAccessException e){
			return "로그아웃 실패";
		}
	}
	
	@ResponseBody
	@RequestMapping("/deleteMember")
	public String deleteMember(HttpServletRequest request,HttpServletResponse response) {
		try {
			String id=request.getParameter("id");
			//System.out.println("deletemember-id: "+id);
			memberService.deleteMember(id);
			return "회원 삭제 완료";
		}catch(DataAccessException e) {
			e.printStackTrace();
			return "회원 삭제 실패";
		}
	}
	
	@ResponseBody
	@RequestMapping("/updateMember")
	public String updateMember(HttpServletRequest request,HttpServletResponse response){
		try {
			String id=request.getParameter("id");
			String pw=request.getParameter("pw");
			String name=request.getParameter("name");
			String address=request.getParameter("address");
			
			MemberVO vo=new MemberVO(id, pw, name,address,0);
			memberService.updateMember(vo);
			return "회원 정보 수정 완료";
		}catch(DataAccessException e) {
			e.printStackTrace();
			return "회원 정보 수정 실패";
		}
	}
	
	
	@RequestMapping("/selectAllMemberList")
	public String selectAllMemberList(HttpServletRequest request,HttpServletResponse response){
		try {
			List<MemberVO> list= memberService.selectAllMemberList();
			return "ok.jsp";
		}catch(DataAccessException e) {
			e.printStackTrace();
			return "fail.jsp";
		}
	}
	
	@Autowired OrderService orderService;
	
	@ResponseBody
	@RequestMapping("/orderProducts")
	public String order(HttpServletRequest request, HttpServletResponse response){
			JSONObject json=null;
			System.out.println("orderProducts 호출");
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
				if(memberVO!=null) {//로그인 된 사용자라면 id를 추가해준다
					orderVO.setMemberid(memberVO.getId());
				}else {
					orderVO.setMemberid("");
				}
				
				list.add(orderVO);
			}
			
			System.out.println("총 "+list.size()+"개 품목을 주문합니다");
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
