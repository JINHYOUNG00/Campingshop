package com.camcam.delivery.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.camcam.common.DataSource;
import com.camcam.delivery.mapper.DeliveryMapper;
import com.camcam.delivery.service.DeliveryService;
import com.camcam.delivery.vo.DeliveryVO;

public class DeliveryServiceImpl implements DeliveryService {
	
	SqlSession session = DataSource.getInstance().openSession(true);
	DeliveryMapper mapper = session.getMapper(DeliveryMapper.class);
	
	@Override
	public List<DeliveryVO> deliveryList() {
		return mapper.selectDeliveryList();
	}

	@Override
	public boolean addDelivery(DeliveryVO dvo) {
		return mapper.insertDelivery(dvo)==1;
	}

	@Override
	public boolean modifyDelivery(int deliNo) {
		// TODO Auto-generated method stub
		return mapper.updateDelivery(deliNo) == 1;
	}

}
