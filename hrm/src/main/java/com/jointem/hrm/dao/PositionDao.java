package com.jointem.hrm.dao;

import java.util.List;

import com.jointem.hrm.entity.Position;

public interface PositionDao {
	void insertPosition(Position p);
	void setPosition(Position p);
	void deletePosition(int id);
	List<Position> selectPositionByDid(int did);
	List<Position> selectAllPosition();
}
