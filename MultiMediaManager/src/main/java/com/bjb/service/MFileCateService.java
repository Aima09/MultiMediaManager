package com.bjb.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bjb.mapper.MFileCateMapper;
import com.bjb.model.MFileCate;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class MFileCateService {
	@Resource
	private MFileCateMapper mFileCateMapper;

	/**
	 * 新增课件分类
	 * 
	 * @param 
	 * @throws SQLException
	 */
	public int insert(MFileCate mFileCate) throws SQLException {
		return mFileCateMapper.insert(mFileCate);
	}

	/**
	 * 更新课件分类
	 * 
	 * @param 
	 * @throws SQLException
	 */
	public int update(MFileCate mFileCate) throws SQLException {
		return mFileCateMapper.updatePartial(mFileCate);
	}

	/**
	 * 查询课件分类
	 * 
	 * @param 
	 * @throws SQLException
	 */
	public List<MFileCate> findByDto(MFileCate mFileCate) throws SQLException {
		PageHelper.startPage(mFileCate.getPageNum(), mFileCate.getPageSize());
		return mFileCateMapper.findByDto(mFileCate);
	}

	/**
	 * 课件分类详情
	 * 
	 * @param 
	 * @throws SQLException
	 */
	public MFileCate findById(Integer id) throws SQLException {
		return mFileCateMapper.findById(id);
	}
}
