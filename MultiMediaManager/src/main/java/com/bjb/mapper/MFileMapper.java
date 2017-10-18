package com.bjb.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bjb.model.MFile;

@Repository(value="mFileMapper")
public interface MFileMapper extends BasicMapper<MFile> {
	public List<MFile> machinefindByDto(MFile mfile);
	public int browseAdd(Integer id);
	public int downloadAdd(Integer id);
	public int loveAdd(Integer id);
	public int filePass(Integer id);
	public int fileRefuse(Integer id);
	public int reject7day(MFile mfile);
	public int updateFileCate(MFile mfile);
}
