package com.bjb.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bjb.mapper.MHistoryMapper;
import com.bjb.model.MHistory;
import com.bjb.model.MVersion;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor=Exception.class)
public class MHistoryService {
	@Resource
	private MHistoryMapper mHistoryMapper;

	public void insert(MHistory history) throws SQLException {
		mHistoryMapper.insert(history);
	}
	public void updatePushTime(MVersion version) throws SQLException{
		mHistoryMapper.updatePushTime(version);
	}
	public List<MHistory> getHistoryList(MHistory history) throws SQLException{
		PageHelper.startPage(history.getPageNum(), history.getPageSize());
		return mHistoryMapper.findByDto(history);
	}
	public MHistory getHistoryDetail(int id) throws SQLException {
		return mHistoryMapper.findById(id);
	}
}
