package com.bjb.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bjb.model.ApiResponse;
import com.bjb.model.MUser;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * session过滤器
 * 
 * @author wujie
 *
 */
@Component
public class SessionFilter extends AbstractFilter {
	@Autowired
	HttpSession session;

	@Override
	protected void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException, SQLException {

		// ---------------权限过滤处理逻辑
		logger.info("session过滤器执行过滤业务...");
		String str = "/api/muser/adminLogin.do,/api/muser/teacherLogin.do,/api/muser/teacherLogOut.do,/api/muser/isLogin.do,/api/muser/isTeacherLogin.do,/api/mapk/getapk.show,/api/mapk/getapkdetail.show,/api/history/getHistoryList.show,/api/history/getHistoryDetail.show,/api/machine/updateip.do,/api/mfilecate/list.show,/api/mfilecate/detail.show,/api/mfilesubcate/list.show,/api/mfilesubcate/detail.show,/api/mfile/list.show,/api/mfile/listbymachine.show,/api/mfile/detail.show,/api/mfile/browse.do,/api/mfile/download.do,/api/mfile/love.do,/api/mfile/reject7day.do,/api/mfilecomment/list.show,/api/mfilecomment/detail.show";
		String url = request.getServletPath();
		MUser user = (MUser) session.getAttribute("LOGIN_INFO");
		if (user == null && session.getAttribute("TEACHER_LOGIN_INFO") == null && str.indexOf(url.substring(url.lastIndexOf("api"))) == -1) {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.setCharacterEncoding("UTF-8");
			httpResponse.setContentType("application/json; charset=utf-8");
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

			ObjectMapper mapper = new ObjectMapper();
			ApiResponse ret = null;
			ret = ApiResponse.error("REQUIRE LOGIN");
			httpResponse.getWriter().write(mapper.writeValueAsString(ret));
			return;
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	protected void doInit(FilterConfig config) {

		logger.info("session过滤器被创建！");
	}

}
