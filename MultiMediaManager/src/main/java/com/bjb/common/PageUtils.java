package com.bjb.common;

import java.util.HashMap;

import com.github.pagehelper.Page;

/**
 * 分页共通
 * @author CAOZQ
 *
 */
public class PageUtils {
	
	/**
	 * 类型转换：将Page转换成Map
	 * @param obj
	 * @return HashMap<String, Object> 或 NULL
	 */
	public static HashMap<String, Object> pageToMap(Object obj) {
		return pageToMap((Page<?>) obj);
	}
	
	/**
	 * 类型转换：将Page转换成Map
	 * @param page
	 * @return HashMap<String, Object> 或 NULL
	 */
	public static HashMap<String, Object> pageToMap(Page<?> page) {
		HashMap<String, Object> map = null;
		if (page != null) {
			map = new HashMap<String, Object>();
			map.put("pages", page.getPages());
			map.put("pageNum", page.getPageNum());
			map.put("pageSize", page.getPageSize());
			map.put("total", page.getTotal());
			map.put("list", page);
		}
		return map;
	}
	
}
