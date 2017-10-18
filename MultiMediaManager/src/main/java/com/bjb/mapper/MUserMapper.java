package com.bjb.mapper;

import org.springframework.stereotype.Repository;

import com.bjb.model.MUser;

@Repository(value="mUserMapper")
public interface MUserMapper extends BasicMapper<MUser> {
	public MUser LoginByDto(MUser mUser);
	public int countByAccount(String account);
}
