package com.camcam.mypage.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.camcam.common.DataSource;
import com.camcam.mypage.mapper.MyPageMapper;
import com.camcam.mypage.service.MyPageService;
import com.camcam.order.vo.OrderVO;
import com.camcam.product.vo.QnAVO;
import com.camcam.user.vo.UserVO;

public class MyPageServiceImpl implements MyPageService {
	SqlSession session = DataSource.getInstance().openSession(true);
	MyPageMapper mapper = session.getMapper(MyPageMapper.class);

	@Override
	public UserVO getMypage(String id) {
		return mapper.selectMypage(id);
	}

	@Override
	public List<QnAVO> getMyboard(String id) {
		return mapper.selectMyboard(id);
	}

	@Override
	public QnAVO getBoardNo(int boardNo) {
		return mapper.selectBoardNo(boardNo, null);
	}

	@Override
	public QnAVO getBoardNo(int boardNo, String pw) {
		return mapper.selectBoardNo(boardNo, pw);
	}

	@Override
	public boolean removeMyboard(int bno, String pw) {
		// QnAVO qvo = mapper.selectBoardNo(vo.getBoardNo());
		String correct = mapper.getBoardpw(bno);
		// System.out.println(correct + "987654" + pw);
		if (correct.equals(pw)) {
			mapper.deleteBoardNo(bno);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean modifyMyboard(QnAVO vo) {
		return mapper.updateBoardNo(vo) == 1;
	}

	@Override
	public List<OrderVO> getMyorder() {
		return mapper.selectMyorder();
	}

}
