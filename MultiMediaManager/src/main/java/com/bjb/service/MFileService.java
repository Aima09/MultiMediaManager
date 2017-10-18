package com.bjb.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bjb.mapper.MFileMapper;
import com.bjb.model.MFile;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class MFileService {
	@Resource
	private MFileMapper mFileMapper;

	/**
	 * 新增课件
	 * 
	 * @param 
	 * @throws SQLException
	 */
	public int insert(MFile mFile) throws SQLException {
		return mFileMapper.insert(mFile);
	}

	/**
	 * 更新课件
	 * 
	 * @param 
	 * @throws SQLException
	 */
	public int update(MFile mFile) throws SQLException {
		return mFileMapper.updatePartial(mFile);
	}

	/**
	 * 查询课件
	 * 
	 * @param 
	 * @throws SQLException
	 */
	public List<MFile> findByDto(MFile mFile) throws SQLException {
		PageHelper.startPage(mFile.getPageNum(), mFile.getPageSize());
		return mFileMapper.findByDto(mFile);
	}

	/**
	 * 课件详情
	 * 
	 * @param 
	 * @throws SQLException
	 */
	public MFile findById(Integer id) throws SQLException {
		return mFileMapper.findById(id);
	}
	
	/**
	 * 终端查询课件
	 * 
	 * @param 
	 * @throws SQLException
	 */
	public List<MFile> machinefindByDto(MFile mFile) throws SQLException {
		PageHelper.startPage(mFile.getPageNum(), mFile.getPageSize());
		return mFileMapper.machinefindByDto(mFile);
	}
	
	/**
	 * 浏览量+1
	 * 
	 * @param 
	 * @throws SQLException
	 */
	public int browseAdd(Integer id) throws SQLException {
		return mFileMapper.browseAdd(id);
	}
	
	/**
	 * 下载量+1
	 * 
	 * @param 
	 * @throws SQLException
	 */
	public int downloadAdd(Integer id) throws SQLException {
		return mFileMapper.downloadAdd(id);
	}
	
	/**
	 * 点赞量+1
	 * 
	 * @param 
	 * @throws SQLException
	 */
	public int loveAdd(Integer id) throws SQLException {
		return mFileMapper.loveAdd(id);
	}
	
	/**
	 * 课件审核通过
	 * 
	 * @param 
	 * @throws SQLException
	 */
	public int filePass(Integer id) throws SQLException {
		return mFileMapper.filePass(id);
	}
	
	/**
	 * 课件审核拒绝
	 * 
	 * @param 
	 * @throws SQLException
	 */
	public int fileRefuse(Integer id) throws SQLException {
		return mFileMapper.fileRefuse(id);
	}
	
	/**
	 * 拒绝大于等于7日待审核课件
	 * 
	 * @param 
	 * @throws SQLException
	 */
	public int reject7day(MFile mfile) throws SQLException {
		return mFileMapper.reject7day(mfile);
	}
	
	/**
	 * 课件子分类更新，更新子分类的大分类
	 * 
	 * @param 
	 * @throws SQLException
	 */
	public int updateFileCate(MFile mfile) throws SQLException {
		return mFileMapper.updateFileCate(mfile);
	}
}
