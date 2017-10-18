package com.bjb.mapper;

import org.springframework.stereotype.Repository;

import com.bjb.model.MHistory;
import com.bjb.model.MVersion;

@Repository(value="mHistoryMapper")
public interface MHistoryMapper extends BasicMapper<MHistory> {

	void updatePushTime(MVersion version);
}
