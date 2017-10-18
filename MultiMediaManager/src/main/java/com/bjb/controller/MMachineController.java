package com.bjb.controller;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bjb.common.Constants;
import com.bjb.model.ApiResponse;
import com.bjb.model.MMachine;
import com.bjb.model.MUser;
import com.bjb.service.MMachineService;
import com.bjbks.media.bean.MachineRes;
import com.bjbks.media.bean.ResList;

@RestController
@RequestMapping(value="/api/machine")
public class MMachineController {
	@Resource
	MMachineService mMachineService;
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpSession session;
	
	/**
	 * 添加终端
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/addMachine.do")
	public ApiResponse addMachine() throws SQLException{
		ApiResponse ret = null;
		MUser user = (MUser) session.getAttribute("LOGIN_INFO");
		int userId = user.getId();
		if (request.getParameter("mac") == null || request.getParameter("mac") == "") {
			ret = ApiResponse.error("mac is null!");
		} else {
			String mac = request.getParameter("mac");
			MMachine machine1 = new MMachine();
			machine1.setMac(mac);
			int num = mMachineService.count(machine1);
			if (num != 0) {
				ret = ApiResponse.error("mac is repeat!");
			} else {
				MMachine machine = new MMachine();
				if (request.getParameter("address") != null && request.getParameter("address") != "") {
					String address = request.getParameter("address");
					machine.setAddress(address);
				}
				SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
				String creDate = date.format(new Date(System.currentTimeMillis()));
				Timestamp creDate1 = Timestamp.valueOf(creDate.substring(0,creDate.length()-4));
				machine.setMac(mac);
				machine.setDelFlg(0);
				machine.setCreateUserId(userId);
				machine.setCreateDatetime(creDate1);
				machine.setUpdateDatetime(creDate1);
				machine.setUpdateUserId(userId);
				mMachineService.insert(machine);
				ret = ApiResponse.success("insert success!");	
			}	
		}
		
		return ret; 
	}
	
	/**
	 * 更新终端
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/updateMachine.do")
	public ApiResponse updateMachine() throws SQLException{
		ApiResponse ret = null;
		MUser user = (MUser) session.getAttribute("LOGIN_INFO");
		int userId = user.getId();
		int versionId = Integer.parseInt(request.getParameter("versionId"));
		String mac = request.getParameter("mac");
		MMachine machine = new MMachine();
		if (request.getParameter("address") != null && request.getParameter("address") != "") {
			String address = request.getParameter("address");
			machine.setAddress(address);
		}
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		String creDate = date.format(new Date(System.currentTimeMillis()));
		Timestamp creDate1 = Timestamp.valueOf(creDate.substring(0,creDate.length()-4));
		machine.setMac(mac);
		machine.setUpdateDatetime(creDate1);
		machine.setUpdateUserId(userId);
		machine.setId(versionId);
		int flg = mMachineService.update(machine);
		if(flg == 0){
			ret = ApiResponse.error("mac is only!");
		} else {
			ret = ApiResponse.success("update success!");
		}
			
		return ret;
	}
	
	/**
	 * 删除终端
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/deleteMachine.do")
	public ApiResponse deleteMachine() throws SQLException{
		ApiResponse ret = null;
		int id =Integer.parseInt(request.getParameter("id"));
		mMachineService.deleteFlg(id);
		ret = ApiResponse.success("delete success!");
		return ret;
	}
	
	/**
	 * 获取终端列表
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/getMachineList.show")
	public ApiResponse getMachineList() throws SQLException{
		ApiResponse ret = null;
		List<MMachine> list = null;
		MMachine machine = new MMachine();
		if (request.getParameter("mac") != null && request.getParameter("mac") != "") {
			String mac = request.getParameter("mac");
			machine.setMac(mac);
		}
		if (request.getParameter("address") != null && request.getParameter("address") != "") {
			String address = request.getParameter("address");
			machine.setAddress(address);
		}
		machine.setPageNum(Integer.parseInt(request.getParameter("pageNum")));
		machine.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
		list = mMachineService.getMachineList(machine);
		ret = ApiResponse.success("get success!").put("data", list);
		return ret;
	}
	
	/**
	 * 获取终端详情
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/getMachineDetail.show")
	public ApiResponse getMachineDetail() throws SQLException{
		ApiResponse ret = null;
		MMachine machine = null;
		int id = Integer.parseInt(request.getParameter("id"));
		machine = mMachineService.getMachineDetail(id);
		ret = ApiResponse.success("get success!").put("data", machine);
		return ret;
	}
	/**
	 * 获取设备资源接口的实现
	 * @return
	 * @throws SQLException
	 * @throws ParseException 
	 */
	@RequestMapping(value="/getmachineres.show")
	public ApiResponse getMachineRes() throws Exception{
		ApiResponse ret = null;
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int id = Integer.parseInt(request.getParameter("id"));
		MMachine machine = mMachineService.getMachine(id);
		machine.setPageNum(pageNum);
		machine.setPageSize(pageSize);
		if(isOnline(machine)){
			ret = sendSocket(machine, 1);
		} else {
			ret = ApiResponse.error("设备不在线!");
		}
		return ret;
	}
	/**
	 * 删除设备资源接口的实现
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value="/delmachineres.do")
	public ApiResponse delMachineRes() throws Exception{
		ApiResponse ret = null;
		int id = Integer.parseInt(request.getParameter("id"));
		String resId = request.getParameter("resId");
		MMachine machine = mMachineService.getMachine(id);
		machine.setDel_res_id(resId);
		if(isOnline(machine)){
			ret = sendSocket(machine, 2);
		} else {
			ret = ApiResponse.error("设备不在线!");
		}
		return ret;
	}
	/**
	 * 终端截图接口的实现
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/machinescreen.do")
	public ApiResponse machineScreen() throws Exception {
		ApiResponse ret = null;
		int id = Integer.parseInt(request.getParameter("id"));
		MMachine machine = mMachineService.getMachine(id);
		if(isOnline(machine)){
			ret = sendSocket(machine, 3);
		} else {
			ret = ApiResponse.error("设备不在线!");
		}
		return ret;
	}
	/**
	 * 终端锁定接口的实现
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value="/machinelock.do")
	public ApiResponse machineLock() throws Exception{
		ApiResponse ret = null;
		int id = Integer.parseInt(request.getParameter("id"));
		MMachine machine = mMachineService.getMachine(id);
		if(isOnline(machine)){
			ret = sendSocket(machine, 4);
		} else {
			ret = ApiResponse.error("设备不在线!");
		}
		return ret;
	}
	/**
	 * 更新设备的心跳时间以及IP地址的接口
	 * @return
	 */
	@RequestMapping(value="/updateip.do")
	public ApiResponse updateIp() throws Exception{
		ApiResponse ret = null;
		String mac = request.getParameter("mac");
		String ip = request.getParameter("ip");
		MMachine machine = mMachineService.getMachineByMac(mac);
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		String creDate = date.format(new Date(System.currentTimeMillis()));
		Timestamp creDate1 = Timestamp.valueOf(creDate.substring(0,creDate.length()-4));
		if(machine != null){
			machine.setIp(ip);
			machine.setHeartHit(creDate1);
			machine.setUpdateDatetime(creDate1);
			mMachineService.updateIp(machine);
			ret = ApiResponse.success();
		} else {
			ret = ApiResponse.error();
		}
		return ret;
	}
	/**
	 * 批量管控接口实现
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/optequipments.do")
	public ApiResponse optEquipments() throws Exception{
		ApiResponse ret = null;
		List<MMachine> list = new ArrayList<MMachine>();
		list = mMachineService.getAllMachine();
		int type = Integer.parseInt(request.getParameter("type"));
		String content = request.getParameter("content");
		for (MMachine mMachine : list) {
			if(isOnline(mMachine)){
				if(type == 1){
					int time = Integer.parseInt(request.getParameter("time"));
					int size = Integer.parseInt(request.getParameter("size"));
					String color = request.getParameter("color");
					String back_color = request.getParameter("back_color");
					String alpha = request.getParameter("alpha");
					int location = Integer.parseInt(request.getParameter("location"));
					ret = controlSendSocket(mMachine.getIp(),content, type, size, color, back_color, alpha, location, time);
				} else {
					ret = controlSendSocket(mMachine.getIp(),content, type, 0, "", "", "", 0, 0);
				}
			}
		}
		return ret;
	}
	@SuppressWarnings("resource")
	public ApiResponse controlSendSocket(String ip,String content, int type, int size, 
			String color, String back_color, String alpha,int location, int time) throws Exception{
		ApiResponse ret = null;
		Socket socket = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		try {
			socket = new Socket(ip, 8000);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			if(type == 1){
				dos.writeUTF(content + "|" + type + "|" + size + "|" 
						+ color + "|" + back_color + "|" + alpha + "|" + location + "|" + time);
			} else {
				dos.writeUTF(content + "|" + type);
			}
			dos.flush();
			String reply = dis.readUTF();
			ret = ApiResponse.success(reply);
		} catch (Exception e) {
			socket = null;
		} finally {
			if (dos != null) {
				dos.close();
			}
			if (dis != null) {
				dis.close();
			}
			if (socket != null) {
				socket = null;
			}
		}
		return ret;
	}
	/**
	 * 接收文件方法
	 * @param socket
	 * @throws IOException
	 */
	public static String receiveFile(Socket socket) throws IOException {
		byte[] inputByte = null;
		int length = 0;
		DataInputStream dis = null;
		FileOutputStream fos = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		String creDate = sdf.format(new Date(System.currentTimeMillis()));
		String img_id = creDate.replace("-", "").replace(":", "")
				.replace(" ", "");
		img_id = img_id.substring(0, img_id.length() - 2);
		String filePath = Constants.SCREENSHOT_PATH + "/screen_shot_" + img_id
				+ ".jpg";
		try {
			dis = new DataInputStream(socket.getInputStream());
			File f = new File(Constants.SCREENSHOT_PATH);
			if (!f.exists()) {
				f.mkdirs();
			}

			fos = new FileOutputStream(new File(filePath));
			inputByte = new byte[1024];
			System.out.println("开始接收数据...");
			while ((length = dis.read(inputByte, 0, inputByte.length)) > 4) {
				System.out.println(length);
				fos.write(inputByte, 0, length);
				fos.flush();
			}
			System.out.println("完成接收：" + filePath);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null)
				fos.close();
			if (dis != null)
				dis.close();
			if (socket != null)
				socket.close();
		}
		return img_id;
	}
	/**
	 * 获取设备资源的方法
	 * @param socket
	 * @return
	 * @throws IOException
	 */
	public static List<MachineRes> getRes(Socket socket) throws IOException{
		List<MachineRes> list = new ArrayList<MachineRes>();
		ObjectInputStream is = null;  
		try{
			is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			Object obj = is.readObject();
			ResList res_back = (ResList) obj;
			list = res_back.getList();
			for(int i = 0; i < list.size(); i++){
				list.get(i).setPages(res_back.getTotalPage());
				list.get(i).setPageNum(res_back.getCurrentPage());
				list.get(i).setTotal(res_back.getTotal());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(is != null)
				is.close();
			if (socket != null)
				socket.close();
		}
		return list;
	}
	/**
	 * 判断设备是否在线的方法
	 * @param machine
	 * @return
	 */
	public static boolean isOnline(MMachine machine){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		boolean ret = false;
		Long machineHit;
		try {
			machineHit = sdf.parse(machine.getHeartHit()+"").getTime();
			Long nowTime = sdf.parse(sdf.format(new Date())+"").getTime();
			if ((nowTime - machineHit) < 1200000) {
				ret  = true;
			} else {
				ret = false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ret;
	}
	public static ApiResponse sendSocket(MMachine machine, int flg)  throws Exception{
		ApiResponse ret = null;
		Socket socket = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		try {
			socket = new Socket(machine.getIp(), 8000);
			// 锁定的操作
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			if(flg == 1){
				List<MachineRes> list = new ArrayList<MachineRes>();
				dos.writeUTF("1|"+machine.getPageNum()+"|"+machine.getPageSize());
				dos.flush();
				list = getRes(socket);
				ret = ApiResponse.success().put("reslist",list);
			} else if(flg == 2){
				dos.writeUTF(flg + "|" + machine.getDel_res_id());
				dos.flush();
				String reply = dis.readUTF();
				ret = ApiResponse.success(reply);
			} else if(flg == 3){
				dos.writeUTF(flg + "|params");
				dos.flush();
				ret = ApiResponse.success().put("msg_id", receiveFile(socket));
			} else {
				dos.writeUTF(flg + "|params");
				dos.flush();
				String reply = dis.readUTF();
				ret = ApiResponse.success(reply);
			}
		} catch (Exception e) {
			ret = ApiResponse.error("设备不在线!");
			socket = null;
		} finally {
			if (dos != null) {
				dos.close();
			}
			if (dis != null) {
				dis.close();
			}
			if (socket != null) {
				socket = null;
			}
		}
		return ret;
	}
}
