package com.bjb.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bjb.mapper.MMachineMapper;
import com.bjb.model.MMachine;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor=Exception.class)
public class MMachineService {
	@Resource
	private MMachineMapper mMachineMapper;

	public void insert(MMachine machine) throws SQLException {
		mMachineMapper.insert(machine);
	}
	public int update(MMachine machine) throws SQLException{
		MMachine tmpmachine = getMachineDetail(machine.getId());
		List<MMachine> list = mMachineMapper.findAll();
		int count = 0;
		for(int i = 0; i < list.size(); i++){
			if(tmpmachine.getMac().equals(list.get(i).getMac())){
				tmpmachine = list.get(i);
			}
		}
		list.remove(tmpmachine);
		for(int i = 0; i < list.size(); i++){
			if(machine.getMac().equals(list.get(i).getMac())){
				count++;
			}
		}
		if(count > 0){
			return 0;
		} else {
			return mMachineMapper.update(machine);
		}
	}
	public void deleteFlg(int id) throws SQLException {
		mMachineMapper.deleteFlg(id);
	}
	public List<MMachine> getMachineList(MMachine machine) throws SQLException{
		PageHelper.startPage(machine.getPageNum(), machine.getPageSize());
		return mMachineMapper.findByDto(machine);
	}
	public List<MMachine> getAllMachine() throws SQLException{
		return mMachineMapper.findAll();
	}
	public MMachine getMachineDetail(int id) throws SQLException{
		return mMachineMapper.findById(id);
	}
	public MMachine getMachine(int id) throws SQLException{
		return mMachineMapper.findById(id);
	}
	public int count(MMachine machine1) throws SQLException{
		return mMachineMapper.countByDto(machine1);
	}
	public MMachine getMachineByMac(String mac) throws SQLException{
		return mMachineMapper.getByMac(mac);
	}
	public int updateIp(MMachine machine) throws SQLException{
		return mMachineMapper.updatePartial(machine);
	}

}
