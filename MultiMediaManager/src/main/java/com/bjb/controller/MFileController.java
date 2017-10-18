package com.bjb.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
import com.bjb.common.Constants;
import com.bjb.model.ApiResponse;
import com.bjb.model.MApk;
import com.bjb.model.MFile;
import com.bjb.model.MUser;
import com.bjb.service.MFileService;

@RestController
@RequestMapping(value = "**/api/mfile")
public class MFileController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	MultipartHttpServletRequest multipartHttpServletRequest;
	@Autowired
	HttpSession session;
	@Resource
	private MFileService mFileService;

	/**
	 * 新增课件
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/add.do")
	public ApiResponse addFile(@RequestParam(value="coverfile") MultipartFile coverfile, @RequestParam(value="file") MultipartFile file) throws SQLException {
		ApiResponse ret = null;
		MFile mFile = new MFile();
		MUser muser = (MUser) session.getAttribute("TEACHER_LOGIN_INFO");
		mFile.setFileCateId(Integer.parseInt(request.getParameter("fileCateId")));
		mFile.setFileSubCateId(Integer.parseInt(request.getParameter("fileSubCateId")));
		mFile.setName(request.getParameter("name"));
		mFile.setDes(request.getParameter("des"));
		mFile.setActor(request.getParameter("actor"));
		mFile.setCompany(request.getParameter("company"));
		if (request.getParameter("openFlg") != null) {
			mFile.setOpenFlg(Integer.parseInt(request.getParameter("openFlg")));
		} else {
			mFile.setOpenFlg(0);
		}
		mFile.setCnt(0);
		mFile.setDownloadCnt(0);
		mFile.setLoveCnt(0);
		mFile.setStatus(0);
		mFile.setDelFlg(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String creDate = sdf.format(new Date(System.currentTimeMillis()));
		mFile.setCreateDatetime(Timestamp.valueOf(creDate));
		mFile.setCreateUserId(muser.getId());
		mFile.setUpdateDatetime(Timestamp.valueOf(creDate));
		mFile.setUpdateUserId(muser.getId());
		if (mFileService.insert(mFile) == 1) {
			String localpath = request.getSession().getServletContext().getRealPath("/");
			File fnewpath = new File(localpath+"upload/file_cover/");
			if(!fnewpath.exists())
				fnewpath.mkdirs();
			if (!coverfile.isEmpty()) {
	            try {
	                byte[] bytes = coverfile.getBytes();
	                BufferedOutputStream stream =
	                        new BufferedOutputStream(new FileOutputStream(new File(localpath+"upload/file_cover/file_cover_"+mFile.getId()+".png")));
	                stream.write(bytes);
	                stream.close();
	            } catch (Exception e) {
	            	e.printStackTrace();
	            }
	        }
			File ffilepath = new File(localpath+"upload/file/");
			if(!ffilepath.exists())
				ffilepath.mkdirs();
			String suffix = "";
			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
					System.out.println("filename: "+file.getOriginalFilename()+"---suffix= "+suffix);
					BufferedOutputStream stream =
							new BufferedOutputStream(new FileOutputStream(new File(localpath+"upload/file/file_"+mFile.getId()+"."+suffix)));
					stream.write(bytes);
					stream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (!suffix.equals("")) {
				mFile.setSuffix(suffix);
				List<String> tempList = Arrays.asList(Constants.MIME_Video);
				if (tempList.contains(suffix)) {
					mFile.setType(1);
				} else {
					tempList = Arrays.asList(Constants.MIME_Audio);
					if (tempList.contains(suffix)) {
						mFile.setType(2);
					} else {
						tempList = Arrays.asList(Constants.MIME_Book);
						if (tempList.contains(suffix)) {
							mFile.setType(3);
						} else {
							tempList = Arrays.asList(Constants.MIME_Img);
							if (tempList.contains(suffix)) {
								mFile.setType(4);
							} else {
								mFile.setType(5);
							}
						}
					}
				}
			}
			mFileService.update(mFile);
			ret = ApiResponse.success("insert success");
		} else {
			ret = ApiResponse.error("insert failed");
		}
		return ret;
	}

	/**
	 * 更新课件
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/update.do")
	public ApiResponse updateFile(@RequestParam(value="coverfile", required=false) MultipartFile[] coverfiles, @RequestParam(value="file", required=false) MultipartFile[] files) throws SQLException {
		ApiResponse ret = null;
		MFile mFile = new MFile();
		MUser muser = (MUser) session.getAttribute("TEACHER_LOGIN_INFO");
		mFile.setId(Integer.parseInt(request.getParameter("id")));
		if (request.getParameter("fileCateId") != null) {
			mFile.setFileCateId(Integer.parseInt(request.getParameter("fileCateId")));
		}
		if (request.getParameter("fileSubCateId") != null) {
			mFile.setFileSubCateId(Integer.parseInt(request.getParameter("fileSubCateId")));
		}
		if (request.getParameter("name") != null) {
			mFile.setName(request.getParameter("name"));
		}
		if (request.getParameter("des") != null) {
			mFile.setDes(request.getParameter("des"));
		}
		if (request.getParameter("actor") != null) {
			mFile.setActor(request.getParameter("actor"));
		}
		if (request.getParameter("company") != null) {
			mFile.setCompany(request.getParameter("company"));
		}
		if (request.getParameter("openFlg") != null) {
			mFile.setOpenFlg(Integer.parseInt(request.getParameter("openFlg")));
		}
		if (request.getParameter("status") != null) {
			mFile.setStatus(Integer.parseInt(request.getParameter("status")));
		}
		if (request.getParameter("isOpen") != null) {
			mFile.setOpenFlg(Integer.parseInt(request.getParameter("isOpen")));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String creDate = sdf.format(new Date(System.currentTimeMillis()));
		mFile.setUpdateDatetime(Timestamp.valueOf(creDate));
		mFile.setUpdateUserId(muser.getId());
		if (mFileService.update(mFile) == 1) {
			// TODO: 封面和应用如果存在就存放到相应位置
			String localpath = request.getSession().getServletContext().getRealPath("/");
			File fnewpath = new File(localpath+"upload/file_cover/");
			if(!fnewpath.exists())
				fnewpath.mkdirs();
			if (coverfiles != null && coverfiles.length > 0) {
				MultipartFile coverfile = coverfiles[0];
	            try {
	                byte[] bytes = coverfile.getBytes();
	                BufferedOutputStream stream =
	                        new BufferedOutputStream(new FileOutputStream(new File(localpath+"upload/file_cover/file_cover_"+mFile.getId()+".png")));
	                stream.write(bytes);
	                stream.close();
	            } catch (Exception e) {
	            	e.printStackTrace();
	            }
	        }
			File ffilepath = new File(localpath+"upload/file/");
			if(!ffilepath.exists())
				ffilepath.mkdirs();
			String suffix = "";
			if (files != null && files.length > 0) {
				MultipartFile file = files[0];
				try {
					byte[] bytes = file.getBytes();
					suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
	                BufferedOutputStream stream =
	                        new BufferedOutputStream(new FileOutputStream(new File(localpath+"upload/file_cover/file_cover_"+mFile.getId()+".png")));
	                stream.write(bytes);
	                stream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (!suffix.equals("")) {
					mFile.setSuffix(suffix);
					List<String> tempList = Arrays.asList(Constants.MIME_Video);
					if (tempList.contains(suffix)) {
						mFile.setType(1);
					} else {
						tempList = Arrays.asList(Constants.MIME_Audio);
						if (tempList.contains(suffix)) {
							mFile.setType(2);
						} else {
							tempList = Arrays.asList(Constants.MIME_Book);
							if (tempList.contains(suffix)) {
								mFile.setType(3);
							} else {
								tempList = Arrays.asList(Constants.MIME_Img);
								if (tempList.contains(suffix)) {
									mFile.setType(4);
								} else {
									mFile.setType(5);
								}
							}
						}
					}
				}
				mFileService.update(mFile);
			}
			ret = ApiResponse.success("update success");
		} else {
			ret = ApiResponse.error("update failed");
		}
		return ret;
	}

	/**
	 * 删除课件
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/delete.do")
	public ApiResponse deleteFile() throws SQLException {
		ApiResponse ret = null;
		MFile mFile = new MFile();
		MUser muser = (MUser) session.getAttribute("LOGIN_INFO");
		if (muser == null) {
			muser = (MUser) session.getAttribute("TEACHER_LOGIN_INFO");
		}
		mFile.setId(Integer.parseInt(request.getParameter("id")));
		mFile.setDelFlg(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String creDate = sdf.format(new Date(System.currentTimeMillis()));
		mFile.setUpdateDatetime(Timestamp.valueOf(creDate));
		mFile.setUpdateUserId(muser.getId());
		if (mFileService.update(mFile) == 1) {
			ret = ApiResponse.success("delete success");
		} else {
			ret = ApiResponse.error("delete failed");
		}
		return ret;
	}

	/**
	 * 查询课件
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/list.show")
	public ApiResponse getFiles() throws SQLException {
		ApiResponse ret = null;
		List<MFile> list = null;
		MFile mFile = new MFile();
		mFile.setPageNum(Integer.parseInt(request.getParameter("pageNum")));
		mFile.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
		if (request.getParameter("fileCateId") != null && !request.getParameter("fileCateId").equals("0")) {
			mFile.setFileCateId(Integer.parseInt(request.getParameter("fileCateId")));
		}
		if (request.getParameter("fileSubCateId") != null && !request.getParameter("fileSubCateId").equals("0")) {
			mFile.setFileSubCateId(Integer.parseInt(request.getParameter("fileSubCateId")));
		}
		if (request.getParameter("type") != null && !request.getParameter("type").equals("0")) {
			mFile.setType(Integer.parseInt(request.getParameter("type")));
		}
		if (request.getParameter("status") != null && !request.getParameter("status").equals("-1")) {
			mFile.setStatus(Integer.parseInt(request.getParameter("status")));
		}
		if (request.getParameter("openFlg") != null && !request.getParameter("openFlg").equals("-1")) {
			mFile.setOpenFlg(Integer.parseInt(request.getParameter("openFlg")));
		}
		if (request.getParameter("name") != null) {
			mFile.setName(request.getParameter("name"));
		}
		if (request.getParameter("userId") != null) {
			mFile.setCreateUserId(Integer.parseInt(request.getParameter("userId")));
		}
		mFile.setDelFlg(0);
		list = mFileService.findByDto(mFile);
		ret = ApiResponse.success().put("data", list);
		return ret;
	}
	
	/**
	 * 终端查询课件
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/listbymachine.show")
	public ApiResponse machinefindByDto() throws SQLException {
		ApiResponse ret = null;
		List<MFile> list = null;
		MFile mFile = new MFile();
		mFile.setPageNum(Integer.parseInt(request.getParameter("pageNum")));
		mFile.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
		if (request.getParameter("fileCateId") != null && !request.getParameter("fileCateId").equals("0")) {
			mFile.setFileCateId(Integer.parseInt(request.getParameter("fileCateId")));
		}
		if (request.getParameter("fileSubCateId") != null && !request.getParameter("fileSubCateId").equals("0")) {
			mFile.setFileSubCateId(Integer.parseInt(request.getParameter("fileSubCateId")));
		}
		if (request.getParameter("type") != null && !request.getParameter("type").equals("0")) {
			mFile.setType(Integer.parseInt(request.getParameter("type")));
		}
		if (request.getParameter("status") != null && !request.getParameter("status").equals("-1")) {
			mFile.setStatus(Integer.parseInt(request.getParameter("status")));
		}
		if (request.getParameter("openFlg") != null && !request.getParameter("openFlg").equals("-1")) {
			mFile.setOpenFlg(Integer.parseInt(request.getParameter("openFlg")));
		}
		if (request.getParameter("name") != null) {
			mFile.setName(request.getParameter("name"));
		}
		if (request.getParameter("userId") != null) {
			mFile.setCreateUserId(Integer.parseInt(request.getParameter("userId")));
		} else {
			mFile.setCreateUserId(0);
		}
		mFile.setDelFlg(0);
		list = mFileService.machinefindByDto(mFile);
		ret = ApiResponse.success().put("data", list);
		return ret;
	}

	/**
	 * 课件详情
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/detail.show")
	public ApiResponse getFileDetail() throws SQLException {
		ApiResponse ret = null;
		MFile mFile = null;
		mFile = mFileService
				.findById(Integer.parseInt(request.getParameter("id")));
		ret = ApiResponse.success().put("data", mFile);
		return ret;
	}
	
	/**
	 * 浏览量+1
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/browse.do")
	public ApiResponse browseAdd() throws SQLException {
		ApiResponse ret = null;
		mFileService.browseAdd(Integer.parseInt(request.getParameter("id")));
		ret = ApiResponse.success("browseAdd success");
		return ret;
	}
	
	/**
	 * 下载量+1
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/download.do")
	public ApiResponse downloadAdd() throws SQLException {
		ApiResponse ret = null;
		mFileService.downloadAdd(Integer.parseInt(request.getParameter("id")));
		ret = ApiResponse.success("downloadAdd success");
		return ret;
	}
	
	/**
	 * 点赞量+1
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/love.do")
	public ApiResponse loveAdd() throws SQLException {
		ApiResponse ret = null;
		mFileService.loveAdd(Integer.parseInt(request.getParameter("id")));
		ret = ApiResponse.success("loveAdd success");
		return ret;
	}
	
	/**
	 * 课件审核通过
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/pass.do")
	public ApiResponse filePass() throws SQLException {
		ApiResponse ret = null;
		mFileService.filePass(Integer.parseInt(request.getParameter("id")));
		ret = ApiResponse.success("filePass success");
		return ret;
	}
	
	/**
	 * 课件审核拒绝
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/refuse.do")
	public ApiResponse fileRefuse() throws SQLException {
		ApiResponse ret = null;
		mFileService.fileRefuse(Integer.parseInt(request.getParameter("id")));
		ret = ApiResponse.success("fileRefuse success");
		return ret;
	}
	
	/**
	 * 拒绝大于等于7日待审核课件
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/reject7day.do")
	public ApiResponse reject7day() throws SQLException {
		ApiResponse ret = null;
		MFile mFile = new MFile();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String creDate = sdf.format(new Date(System.currentTimeMillis()));
		mFile.setUpdateDatetime(Timestamp.valueOf(creDate));
		mFileService.reject7day(mFile);
		ret = ApiResponse.success("reject7day success");
		return ret;
	}
}
