package com.bjb.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bjb.mapper.MApkMapper;
import com.bjb.model.MApk;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class MApkService {
	@Resource
	private MApkMapper mApkMapper;

	/**
	 * 新增应用
	 * 
	 * @param order
	 * @throws SQLException
	 */
	public int insert(MApk mApk) throws SQLException {
		return mApkMapper.insert(mApk);
	}

	/**
	 * 更新应用
	 * 
	 * @param order
	 * @throws SQLException
	 */
	public int update(MApk mApk) throws SQLException {
		return mApkMapper.updatePartial(mApk);
	}

	/**
	 * 查询应用
	 * 
	 * @param order
	 * @throws SQLException
	 */
	public List<MApk> findByDto(MApk mApk) throws SQLException {
		PageHelper.startPage(mApk.getPageNum(), mApk.getPageSize());
		return mApkMapper.findByDto(mApk);
	}

	/**
	 * 应用详情
	 * 
	 * @param order
	 * @throws SQLException
	 */
	public MApk findById(Integer id) throws SQLException {
		return mApkMapper.findById(id);
	}
}
