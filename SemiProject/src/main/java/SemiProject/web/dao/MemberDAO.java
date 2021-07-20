package SemiProject.web.dao;


import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import SemiProject.web.vo.MemberVO;


@Mapper
@Repository("memberDAO")
public interface MemberDAO {
	
	//@Autowired
	//SqlSession sqlSession;
	
	public void insertMember(MemberVO memberVO)  throws DataAccessException;

	public MemberVO loginById(MemberVO memberVO) throws DataAccessException;
	
	public void deleteMember(String id ) throws DataAccessException;
	
	public void updateMember(MemberVO memberVO) throws DataAccessException;
	
	public List<MemberVO> selectAllMemberList() throws DataAccessException;
}
