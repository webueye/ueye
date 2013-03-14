package net.ueye.commons.bean;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;

/**
 * @author rubys@vip.qq.com
 * @since 2013-2-16
 */
@SuppressWarnings("serial")
public class Page implements Serializable {

	public static final String ASC = "asc";
	public static final String DESC = "desc";

	private int pageSize = 10;
	private long totalCount;
	private int pageNum = 1;
	private int totalPages;
	private List<?> datas = Lists.newArrayList();
	private boolean autoCount = true;

	protected String orderBy = "id";
	protected String order = ASC;

	private String className = "sorting-asc";

	public Page() {

	}

	public Page(boolean autoCount) {
		this.autoCount = autoCount;
	}

	public Page(int pageNum) {
		this.pageNum = pageNum;
	}

	public Page(int pageNum, int pageSize) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public boolean isOrderBySetted() {
		return (StringUtils.isNotBlank(orderBy) && StringUtils
				.isNotBlank(order));
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		if (totalCount <= 0) {
			this.totalCount = 0;
		} else {
			this.totalCount = totalCount;

			if (this.totalCount % pageSize == 0) {
				setPageCount(Long.valueOf(this.totalCount / pageSize)
						.intValue());
			} else {
				setPageCount(Long.valueOf(this.totalCount / pageSize + 1)
						.intValue());
			}
		}
	}

	public int getPageNum() {
		if (pageNum > totalPages) {
			return totalPages;
		}
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
		if (pageNum < 1) {
			this.pageNum = 1;
		}
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setPageCount(int totalPages) {
		if (totalPages < 0) {
			this.totalPages = 0;
		} else {
			this.totalPages = totalPages;
		}
	}

	public int getBeginIndex() {
		if (pageNum <= 1 || totalPages <= 1) {
			return 0;
		} else if (pageNum > totalPages) {
			return (totalPages - 1) * pageSize;
		} else {
			return (pageNum - 1) * pageSize;
		}
	}

	public int getEndIndex() {
		return (getBeginIndex() + pageSize);
	}

	public void setDatas(List<?> datas) {
		this.datas = datas;
	}

	public List<?> getDatas() {
		return datas;
	}

	public boolean isAutoCount() {
		return autoCount;
	}

	public void setAutoCount(boolean autoCount) {
		this.autoCount = autoCount;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getOrder() {
		return order;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String toString() {
		return "Page [pageSize=" + pageSize + ", totalCount=" + totalCount
				+ ", pageNum=" + pageNum + ", totalPages=" + totalPages
				+ ", autoCount=" + autoCount + ", orderBy=" + orderBy
				+ ", order=" + order + "]";
	}

}
