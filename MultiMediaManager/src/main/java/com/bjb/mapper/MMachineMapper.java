package com.bjb.mapper;

import org.springframework.stereotype.Repository;

import com.bjb.model.MMachine;

@Repository(value="mMachineMapper")
public interface MMachineMapper extends BasicMapper<MMachine> {

	void deleteFlg(int id);
	MMachine getByMac(String mac);
}
