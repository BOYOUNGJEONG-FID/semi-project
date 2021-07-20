package SemiProject.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import SemiProject.web.vo.OrderVO;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class OrderDAO {
	@Autowired
	SqlSession sqlSession;
	
	public long ordersInsert(ArrayList<OrderVO> list) {
		//System.out.println(list.size());
		long order_group_no=getOrder_group_no();
		
		for(OrderVO orderVO:list) {		
			long order_no=getOrder_no();
			orderVO.setOrder_group_no(order_group_no);
			orderVO.setOrderno(order_no);
			System.out.println(">>>"+orderVO);
			sqlSession.insert("mapper.order.insert",orderVO);
		}
		return order_group_no;
	}
	
	private long getOrder_group_no() {
		long order_group_no=sqlSession.selectOne("mapper.order.order_group_no");
		//System.out.println(order_group_no);
		return order_group_no;
	}
	
	private long getOrder_no() {
		long order_no=sqlSession.selectOne("mapper.order.order_no");
		//System.out.println(order_group_no);
		return order_no;
	}
}
