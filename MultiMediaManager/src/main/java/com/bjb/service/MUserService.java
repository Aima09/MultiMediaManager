package com.bjb.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bjb.mapper.MUserMapper;
import com.bjb.model.MUser;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor=Exception.class)
public class MUserService {
	@Resource
	private MUserMapper mUserMapper;
	/**
	 * 添加用户
	 * @param user
	 * @throws SQLException
	 */
	public int addUser(MUser user) throws SQLException{
		int count = mUserMapper.countByAccount(user.getAccount());
		if(count > 0){
			return 0;
		} else {
			return mUserMapper.insert(user);
		}
	}
	/**
	 * 更新用户
	 * @param user
	 * @throws SQLException
	 */
	public int updateUser(MUser user) throws SQLException{
		MUser tmpuser = getUser(user.getId());
		List<MUser> list = mUserMapper.findAll();
		list.remove(tmpuser);
		int count = 0;
		for(int i = 0; i< list.size(); i++){
			if(list.get(i).getAccount().equals(user.getAccount())){
				count++;
			}
		}
		if(count > 0){
			
			return 0;
			
		} else {
			
			return mUserMapper.updatePartial(user);
		}
	}
	public void deleteUser(MUser user) throws SQLException{
		mUserMapper.updatePartial(user);
	}
	/**
	 * 查询用户列表
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public List<MUser> getUserList(MUser user)throws SQLException{
		PageHelper.startPage(user.getPageNum(),user.getPageSize());
		return mUserMapper.findByDto(user);
	}
	/**
	 * 查询用户的详情
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public MUser getUser(int id) throws SQLException{
		return mUserMapper.findById(id);
	}
	
	/**
	 * 登录
	 * @param
	 * @return
	 * @throws SQLException
	 */
	public MUser LoginByDto(MUser mUser) throws SQLException{
		return mUserMapper.LoginByDto(mUser);
	}
}
