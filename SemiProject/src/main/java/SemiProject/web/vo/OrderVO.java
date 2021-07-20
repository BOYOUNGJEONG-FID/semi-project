package SemiProject.web.vo;

import java.util.Date;

public class OrderVO {
	private long orderno;
	private Date orderdate;
	private String ordermethod;
	private String memberid;
	private String prodcode;
	private String prodname;
	private long quantity;
	private long order_group_no;	
	
	public OrderVO(String ordermethod, String prodname, long quantity, long order_group_no) {
		this(ordermethod,prodname,quantity);
		setOrder_group_no(order_group_no);
	}
	public OrderVO(String ordermethod, String prodname, long quantity) {
		super();
		setOrdermethod(ordermethod);
		setProdname(prodname);
		setQuantity(quantity);
		
	}
	public OrderVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getOrderno() {
		return orderno;
	}
	public void setOrderno(long orderno) {
		this.orderno = orderno;
	}
	public Date getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}
	public String getOrdermethod() {
		return ordermethod;
	}
	public void setOrdermethod(String ordermethod) {
		this.ordermethod = ordermethod;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getProdcode() {
		return prodcode;
	}
	public void setProdcode(String prodcode) {
		this.prodcode = prodcode;
	}
	public String getProdname() {
		return prodname;
	}
	public void setProdname(String prodname) {
		this.prodname = prodname;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public long getOrder_group_no() {
		return order_group_no;
	}
	public void setOrder_group_no(long order_group_no) {
		this.order_group_no = order_group_no;
	}
	@Override
	public String toString() {
		return "OrderVO [orderno=" + orderno + ", orderdate=" + orderdate + ", ordermethod=" + ordermethod
				+ ", memberid=" + memberid + ", prodcode=" + prodcode + ", prodname=" + prodname + ", quantity="
				+ quantity + ", order_group_no=" + order_group_no + "]";
	}	
}



