package com.bjb.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bjb.mapper.MFileSubCateMapper;
import com.bjb.model.MFileSubCate;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class MFileSubCateService {
	@Resource
	private MFileSubCateMapper mFileSubCateMapper;

	/**
	 * 新增课件子分类
	 * 
	 * @param 
	 * @throws SQLException
	 */
	public int insert(MFileSubCate mFileSubCate) throws SQLException {
		return mFileSubCateMapper.insert(mFileSubCate);
	}

	/**
	 * 更新课件子分类
	 * 
	 * @param 
	 * @throws SQLException
	 */
	public int update(MFileSubCate mFileSubCate) throws SQLException {
		return mFileSubCateMapper.updatePartial(mFileSubCate);
	}

	/**
	 * 查询课件子分类
	 * 
	 * @param 
	 * @throws SQLException
	 */
	public List<MFileSubCate> findByDto(MFileSubCate mFileSubCate) throws SQLException {
		PageHelper.startPage(mFileSubCate.getPageNum(), mFileSubCate.getPageSize());
		return mFileSubCateMapper.findByDto(mFileSubCate);
	}

	/**
	 * 课件子分类详情
	 * 
	 * @param 
	 * @throws SQLException
	 */
	public MFileSubCate findById(Integer id) throws SQLException {
		return mFileSubCateMapper.findById(id);
	}
}
