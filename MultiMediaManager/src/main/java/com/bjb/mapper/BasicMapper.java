package com.bjb.mapper;

import java.sql.SQLException;
import java.util.List;

/**
 * 基础mapper映射接口
 * @author liuli
 * @param <Dto>
 */
public interface BasicMapper<Dto> {

	/*
	* 插入
	*/
	public int insert(Dto dto) throws SQLException;
	
	/*
	* 批量插入
	*/
	public int insertList(List<Dto> list) throws SQLException;
	
	/*
	* 更新
	*/
	public int update(Dto dto) throws SQLException;
	
	/*局部字段更新*/
	public int updatePartial(Dto dto) throws SQLException;
	
	/*
	* 删除
	*/
	public int delete(Integer id) throws SQLException;
	
	/*
	* 批量删除
	*/
	public int deleteList(List<Integer> list) throws SQLException;
	
	/*
	* 主键查询
	*/
	public Dto findById(Integer id) throws SQLException;
	
	/*
	* 查询所有
	*/
	public List<Dto> findAll() throws SQLException;
	
	/*
	 * 根据对象查询
	 */
	public List<Dto> findByDto(Dto dto) throws SQLException;
	
	
	/*
	* 数量统计
	*/
	public int countByDto(Dto dto) throws SQLException;
	
	/*
	* 总数统计
	*/
	public int count() throws SQLException;

}
