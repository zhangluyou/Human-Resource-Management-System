package com.jointem.hrm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointem.hrm.dao.PositionDao;
import com.jointem.hrm.entity.Position;
import com.jointem.hrm.service.PositionService;
@Service("positionService")
public class positionServiceImp implements PositionService{
    @Autowired
    PositionDao positiondao;
	@Override
	public void insertPosition(Position p) {
		List<Position> positions=positiondao.selectAllPosition();
		boolean bu=true;
		for(int i=0;i<positions.size();i++)
		{
			if(positions.get(i).getPositionName().equals(p.getPositionName()))
				bu=false;
		}
		if(bu)
		positiondao.insertPosition(p);
	}

	@Override
	public void setPosition(Position p) {
		positiondao.setPosition(p);
	}

	@Override
	public void deletePosition(int id) {
		positiondao.deletePosition(id);
	}

	@Override
	public List<Position> selectPositionByDid(int did) {
		return positiondao.selectPositionByDid(did);
	}

}
