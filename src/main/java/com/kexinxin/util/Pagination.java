package com.kexinxin.util;

/**
 * 分页，在分页挡截中使用
 *
 * @author elvis.xu
 * @since 2015-05-19 20:34
 */
public interface Pagination {

	/**
	 * @return 当前页码
	 */
	int getPageNo();

	/**
	 * @return 每页记录数
	 */
	int getPageCount();

	/**
	 * @return 总记录数: 负数表尚未设置, 挡截器会使用count语句统计总数; 0或正整数表总数已设置, 挡截器将不会统计总数.
	 */
	int getTotalCount();

	/**
	 * @param totalCount 设置记录总数
	 */
	void setTotalCount(int totalCount);
}
