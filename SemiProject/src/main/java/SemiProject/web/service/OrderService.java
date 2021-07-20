package SemiProject.web.service;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SemiProject.web.dao.OrderDAO;
import SemiProject.web.vo.OrderVO;



@Service
public class OrderService {
	@Autowired
	OrderDAO orderDAO;
	
	public long ordersInsert(ArrayList<OrderVO> list) throws Exception{
		return orderDAO.ordersInsert(list);
	}


}
