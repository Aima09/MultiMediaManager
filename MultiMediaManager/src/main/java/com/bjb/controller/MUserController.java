package com.bjb.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bjb.common.MD5Util;
import com.bjb.model.ApiResponse;
import com.bjb.model.MUser;
import com.bjb.service.MUserService;

@RestController
@RequestMapping(value="**/api/muser")
public class MUserController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpSession session;
	@Resource
	private MUserService mUserService;
	/**
	 * 添加用户的接口实现
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value="/adduser.do")
	public ApiResponse addUser() throws SQLException{
		ApiResponse ret = null;
		MUser logUser = (MUser) session.getAttribute("LOGIN_INFO");
		MUser user = new MUser();
		user.setAccount(request.getParameter("account"));
		user.setPwd(MD5Util.MD5(request.getParameter("pwd")));
		user.setName(request.getParameter("name"));
		user.setPhone(request.getParameter("phone"));
		user.setRole(Integer.parseInt(request.getParameter("role")));
		user.setStatus(Integer.parseInt(request.getParameter("status")));
		user.setDelFlg(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String creDate = sdf.format(new Date());
		user.setCreateDatetime(Timestamp.valueOf(creDate));
		user.setUpdateDatetime(Timestamp.valueOf(creDate));
		user.setCreateUserId(logUser.getId());//应该是当前登录用户的ID
		user.setUpdateUserId(logUser.getId());//应该是当前登录用户的ID
		int count = mUserService.addUser(user);
		if(count == 0){
			ret = ApiResponse.error("当前账号已被使用，请重新输入!");
		} else {
			ret = ApiResponse.success("成功!");
		}
		return ret;
	}
	/**
	 * 删除用户的接口的实现
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value="/deluser.do")
	public ApiResponse delUser() throws SQLException{
		ApiResponse ret = null;
		MUser user = new MUser();
		user.setId(Integer.parseInt(request.getParameter("id")));
		user.setDelFlg(1);
		mUserService.deleteUser(user);
		ret = ApiResponse.success("成功!");
		return ret;
	}
	/**
	 * 更新用户信息的接口实现
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value="/updateuser.do")
	public ApiResponse updateUser()throws SQLException{
		ApiResponse ret = null;
		MUser logUser = (MUser) session.getAttribute("LOGIN_INFO");
		MUser user = new MUser();
		user.setId(Integer.parseInt(request.getParameter("id")));
		if(request.getParameter("pwd") != null && request.getParameter("pwd") != ""){
			user.setPwd(MD5Util.MD5(request.getParameter("pwd")));
		}
		user.setName(request.getParameter("name"));
		user.setPhone(request.getParameter("phone"));
		user.setRole(Integer.parseInt(request.getParameter("role")));
		user.setStatus(Integer.parseInt(request.getParameter("status")));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String updDate = sdf.format(new Date());
		user.setUpdateDatetime(Timestamp.valueOf(updDate));
		user.setUpdateUserId(logUser.getId());//应该是当前登录用户的ID
		int flg = mUserService.updateUser(user);
		if(flg == 0){
			ret = ApiResponse.error("当前账号已被使用，请重新输入!");
		} else {
			ret = ApiResponse.success("成功!");
		}
		return ret;
	}
	/**
	 * 查询用户列表接口的实现
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value="/getuserlist.show")
	public ApiResponse getUserList() throws SQLException{
		ApiResponse ret = null;
		MUser user = new MUser();
		List<MUser> list = null;
		user.setPageNum(Integer.parseInt(request.getParameter("pageNum")));
		user.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
		if (request.getParameter("account") != null && !"".equals(request.getParameter("account")) ) {
			user.setAccount(request.getParameter("account"));
		}
		if (request.getParameter("account") != null && !"".equals(request.getParameter("account"))) {
			user.setName(request.getParameter("account"));
		}
		if (request.getParameter("account") != null && !"".equals(request.getParameter("account"))) {
			user.setPhone(request.getParameter("account"));
		}
		list = mUserService.getUserList(user);
		ret = ApiResponse.success().put("userlist", list);
		return ret;
	}
	/**
	 * 查询用户信息详情的接口的实现
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value="/getuser.show")
	public ApiResponse getUser() throws SQLException{
		ApiResponse ret = null;
		int id = Integer.parseInt(request.getParameter("id"));
		MUser user = mUserService.getUser(id);
		ret = ApiResponse.success().put("user", user);
		return ret;
	}
	
	/**
	 * 管理员登录
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/adminLogin.do")
	public ApiResponse adminLogin() throws SQLException{
		ApiResponse ret = null;
		session.removeAttribute("LOGIN_INFO");
		MUser user = new MUser();
		user.setAccount(request.getParameter("userName"));
		String userPwd = request.getParameter("userPwd");
		userPwd = MD5Util.MD5(userPwd);
		user.setPwd(userPwd);
		user.setRole(1);
		MUser tmpUser = mUserService.LoginByDto(user);
		if (tmpUser == null) {
			ret = ApiResponse.error("用户名或密码错误!");
		} else {
			if(tmpUser.getStatus() == 0) {
				ret = ApiResponse.error("该账户禁止登录该系统!");
			} else {
				if(tmpUser.getStatus() == 1) {
					ret = ApiResponse.success("登录成功!");
					session.setAttribute("LOGIN_INFO", tmpUser);
				} 
			}
		}
		return ret;
	}
	
	/**
	 * 教师登录
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/teacherLogin.do")
	public ApiResponse teacherLogin() throws SQLException{
		ApiResponse ret = null;
		session.removeAttribute("TEACHER_LOGIN_INFO");
		MUser user = new MUser();
		user.setAccount(request.getParameter("userName"));
		String userPwd = request.getParameter("userPwd");
		userPwd = MD5Util.MD5(userPwd);
		user.setPwd(userPwd);
		user.setRole(2);
		MUser tmpUser = mUserService.LoginByDto(user);
		if (tmpUser == null) {
			ret = ApiResponse.error("用户名或密码错误!");
		} else {
			if(tmpUser.getStatus() == 0) {
				ret = ApiResponse.error("该账户禁止登录该系统!");
			} else {
				if(tmpUser.getStatus() == 1) {
					session.setAttribute("TEACHER_LOGIN_INFO", tmpUser);
					ret = ApiResponse.success("登录成功!").put("user", session.getAttribute("TEACHER_LOGIN_INFO"));
				} 
			}
		}
		return ret;
	}

	/**
	 * 判断是否登录
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value="/isLogin.do")
	public ApiResponse isLogin() throws SQLException{
		ApiResponse ret = null;
		MUser user =  (MUser) session.getAttribute("LOGIN_INFO");
		if (user == null) {
			ret = ApiResponse.error("REQUIRE LOGIN");
		} else {
			ret = ApiResponse.success("LOGIN SUCCESS").put("data", user);
		}
		return ret;
	}
	
	/**
	 * 判断教师是否登录
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value="/isTeacherLogin.do")
	public ApiResponse isTeacherLogin() throws SQLException{
		ApiResponse ret = null;
		MUser user =  (MUser) session.getAttribute("TEACHER_LOGIN_INFO");
		if (user == null) {
			ret = ApiResponse.error("REQUIRE LOGIN");
		} else {
			ret = ApiResponse.success("LOGIN SUCCESS").put("data", user);
		}
		return ret;
	}
	
	/**
	 * 退出登录
	 * @return
	 * @throws 
	 */
	@RequestMapping(value="/logOut.do")
	public ApiResponse logOut(){
		ApiResponse ret = null;
		session.removeAttribute("LOGIN_INFO");
		ret = ApiResponse.success("LOGOUT SUCCESS");
		return ret;
	}
	
	/**
	 * 教师退出登录
	 * @return
	 * @throws 
	 */
	@RequestMapping(value="/teacherLogOut.do")
	public ApiResponse teacherLogOut(){
		ApiResponse ret = null;
		session.removeAttribute("TEACHER_LOGIN_INFO");
		ret = ApiResponse.success("LOGOUT SUCCESS");
		return ret;
	}
}
