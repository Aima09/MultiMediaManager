package com.bjb.mapper;

import org.springframework.stereotype.Repository;
import com.bjb.model.MVersion;

@Repository(value="mVersionMapper")
public interface MVersionMapper extends BasicMapper<MVersion> {
}
