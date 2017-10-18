package com.bjb.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bjb.mapper.MVersionMapper;
import com.bjb.model.MVersion;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class MVersionService {
	@Resource
	private MVersionMapper mVersionMapper;

	/**
	 * 新增版本
	 * 
	 * @param
	 * @throws SQLException
	 */
	public int insert(MVersion mVersion) throws SQLException {
		return mVersionMapper.insert(mVersion);
	}

	/**
	 * 更新版本
	 * 
	 * @param
	 * @throws SQLException
	 */
	public int update(MVersion mVersion) throws SQLException {
		return mVersionMapper.updatePartial(mVersion);
	}

	/**
	 * 查询版本
	 * 
	 * @param
	 * @throws SQLException
	 */
	public List<MVersion> findByDto(MVersion mVersion) throws SQLException {
		PageHelper.startPage(mVersion.getPageNum(), mVersion.getPageSize());
		return mVersionMapper.findByDto(mVersion);
	}

	/**
	 * 版本详情
	 * 
	 * @param
	 * @throws SQLException
	 */
	public MVersion findById(Integer id) throws SQLException {
		return mVersionMapper.findById(id);
	}
}
