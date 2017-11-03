package com.jointem.hrm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jointem.hrm.entity.Position;
@Service
public interface PositionService {
	void insertPosition(Position p);
	void setPosition(Position p);
	void deletePosition(int id);
	List<Position> selectPositionByDid(int did);
}
