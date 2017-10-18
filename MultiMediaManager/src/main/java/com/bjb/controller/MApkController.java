package com.bjb.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bjb.common.APKUtil;
import com.bjb.model.ApiResponse;
import com.bjb.model.MApk;
import com.bjb.model.MUser;
import com.bjb.service.MApkService;

@RestController
@RequestMapping(value = "/api/mapk")
public class MApkController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	MultipartHttpServletRequest multipartHttpServletRequest;
	@Autowired
	HttpSession session;
	@Resource
	private MApkService mApkService;

	/**
	 * 新增应用
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/addapk.do")
	public ApiResponse addApk(@RequestParam(value="coverfile") MultipartFile coverfile, @RequestParam(value="apkfile") MultipartFile apkfile) throws SQLException {
		ApiResponse ret = null;
		MApk mApk = new MApk();
		MUser muser = (MUser) session.getAttribute("LOGIN_INFO");
		mApk.setName(request.getParameter("name"));
		mApk.setSize(Integer.parseInt(request.getParameter("size")));
		mApk.setDes(request.getParameter("des"));
		mApk.setDelFlg(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String creDate = sdf.format(new Date(System.currentTimeMillis()));
		mApk.setCreateDatetime(Timestamp.valueOf(creDate));
		mApk.setCreateUserId(muser.getId());
		mApk.setUpdateDatetime(Timestamp.valueOf(creDate));
		mApk.setUpdateUserId(muser.getId());
		if (mApkService.insert(mApk) == 1) {
			// TODO: 封面和应用存放到相应位置
			String localpath = request.getSession().getServletContext().getRealPath("/");
			File fnewpath = new File(localpath+"upload/apk_cover/");
			if(!fnewpath.exists())
				fnewpath.mkdirs();
			if (!coverfile.isEmpty()) {
	            try {
	                byte[] bytes = coverfile.getBytes();
	                BufferedOutputStream stream =
	                        new BufferedOutputStream(new FileOutputStream(new File(localpath+"upload/apk_cover/apk_cover_"+mApk.getId()+".png")));
	                stream.write(bytes);
	                stream.close();
	            } catch (Exception e) {
	            	e.printStackTrace();
	            }
	        }
			File fapkpath = new File(localpath+"upload/apk/");
			if(!fapkpath.exists())
				fapkpath.mkdirs();
			if (!apkfile.isEmpty()) {
				try {
					byte[] bytes = apkfile.getBytes();
					BufferedOutputStream stream =
							new BufferedOutputStream(new FileOutputStream(new File(localpath+"upload/apk/apk_"+mApk.getId()+".apk")));
					stream.write(bytes);
					stream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			String[] apk = APKUtil.unZip(localpath+"upload/apk/apk_"+mApk.getId()+".apk");
			mApk.setVersion(apk[0]);
			mApk.setPkg(apk[1]);
			mApkService.update(mApk);
			ret = ApiResponse.success("insert success");
		} else {
			ret = ApiResponse.error("insert failed");
		}
		return ret;
	}

	/**
	 * 更新应用
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/updateapk.do")
	public ApiResponse updateApk(@RequestParam(value="coverfile", required=false) MultipartFile[] coverfiles, @RequestParam(value="apkfile", required=false) MultipartFile[] apkfiles) throws SQLException {
		ApiResponse ret = null;
		MApk mApk = new MApk();
		MUser muser = (MUser) session.getAttribute("LOGIN_INFO");
		mApk.setId(Integer.parseInt(request.getParameter("id")));
		if (request.getParameter("name") != null) {
			mApk.setName(request.getParameter("name"));
		}
		if (request.getParameter("size") != null) {
			mApk.setSize(Integer.parseInt(request.getParameter("size")));
		}
		if (request.getParameter("des") != null) {
			mApk.setDes(request.getParameter("des"));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String creDate = sdf.format(new Date(System.currentTimeMillis()));
		mApk.setUpdateDatetime(Timestamp.valueOf(creDate));
		mApk.setUpdateUserId(muser.getId());
		if (mApkService.update(mApk) == 1) {
			// TODO: 封面和应用如果存在就存放到相应位置
			String localpath = request.getSession().getServletContext().getRealPath("/");
			File fnewpath = new File(localpath+"upload/apk_cover/");
			if(!fnewpath.exists())
				fnewpath.mkdirs();
			if (coverfiles != null && coverfiles.length > 0) {
				MultipartFile coverfile = coverfiles[0];
	            try {
	                byte[] bytes = coverfile.getBytes();
	                BufferedOutputStream stream =
	                        new BufferedOutputStream(new FileOutputStream(new File(localpath+"upload/apk_cover/apk_cover_"+mApk.getId()+".png")));
	                stream.write(bytes);
	                stream.close();
	            } catch (Exception e) {
	            	e.printStackTrace();
	            }
	        }
			File fapkpath = new File(localpath+"upload/apk/");
			if(!fapkpath.exists())
				fapkpath.mkdirs();
			if (apkfiles != null && apkfiles.length > 0) {
				MultipartFile apkfile = apkfiles[0];
				try {
					byte[] bytes = apkfile.getBytes();
					BufferedOutputStream stream =
							new BufferedOutputStream(new FileOutputStream(new File(localpath+"upload/apk/apk_"+mApk.getId()+".apk")));
					stream.write(bytes);
					stream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				String[] apk = APKUtil.unZip(localpath+"upload/apk/apk_"+mApk.getId()+".apk");
				mApk.setVersion(apk[0]);
				mApk.setPkg(apk[1]);
				mApkService.update(mApk);
			}
			ret = ApiResponse.success("update success");
		} else {
			ret = ApiResponse.error("update failed");
		}
		return ret;
	}

	/**
	 * 删除应用
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/deleteapk.do")
	public ApiResponse deleteApk() throws SQLException {
		ApiResponse ret = null;
		MApk mApk = new MApk();
		MUser muser = (MUser) session.getAttribute("LOGIN_INFO");
		mApk.setId(Integer.parseInt(request.getParameter("id")));
		mApk.setDelFlg(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String creDate = sdf.format(new Date(System.currentTimeMillis()));
		mApk.setUpdateDatetime(Timestamp.valueOf(creDate));
		mApk.setUpdateUserId(muser.getId());
		if (mApkService.update(mApk) == 1) {
			ret = ApiResponse.success("delete success");
		} else {
			ret = ApiResponse.error("delete failed");
		}
		return ret;
	}

	/**
	 * 查询应用
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/getapk.show")
	public ApiResponse getApk() throws SQLException {
		ApiResponse ret = null;
		List<MApk> list = null;
		MApk mApk = new MApk();
		mApk.setPageNum(Integer.parseInt(request.getParameter("pageNum")));
		mApk.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
		if (request.getParameter("name") != null) {
			mApk.setName(request.getParameter("name"));
		}
		mApk.setDelFlg(0);
		list = mApkService.findByDto(mApk);
		ret = ApiResponse.success().put("data", list);
		return ret;
	}

	/**
	 * 应用详情
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/getapkdetail.show")
	public ApiResponse getApkDetail() throws SQLException {
		ApiResponse ret = null;
		MApk mApk = null;
		mApk = mApkService
				.findById(Integer.parseInt(request.getParameter("id")));
		ret = ApiResponse.success().put("data", mApk);
		return ret;
	}
}
