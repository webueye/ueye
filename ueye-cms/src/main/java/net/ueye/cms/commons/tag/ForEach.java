package net.ueye.cms.commons.tag;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;

import com.google.common.collect.Lists;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-29
 */
@SuppressWarnings("serial")
public class ForEach extends BodyTagSupport {

	private String var;
	private String indexed;
	private String items;
	private List<Object> itemList;
	private Iterator<Object> iterator;
	private int rows = 0;

	@Override
	public int doAfterBody() throws JspException {
		if (iterator.hasNext()) {
			pageContext.setAttribute(var, iterator.next());
			return EVAL_BODY_AGAIN;
		}
		return SKIP_BODY;
	}

	@Override
	@SuppressWarnings("unchecked")
	public int doStartTag() throws JspException {
		itemList = getItemList();
		if (CollectionUtils.isNotEmpty(itemList)) {
			List<Object> list = Lists.newArrayList();
			iterator = loop(list, itemList, rows).iterator();
			pageContext.setAttribute(var, iterator.next());
			return EVAL_BODY_INCLUDE;
		} else {
			return SKIP_BODY;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object> loop(List<Object> list, List<Object> nodes, int rows) {
		for (int i = 0; i < nodes.size(); i++) {
			Object node = nodes.get(i);
			try {
				Method setLevel = node.getClass().getMethod("setLevel", int.class);
				setLevel.invoke(node, rows);

				Method setRows = node.getClass().getMethod("setRows", new Class[] { String[].class });
				setRows.invoke(node, new Object[] { new String[rows] });
				list.add(node);
				if (i == 0) {
					Method setFirst = node.getClass().getMethod("setFirst", boolean.class);
					setFirst.invoke(node, Boolean.TRUE);
				}
				if (i == nodes.size() - 1) {
					Method setLast = node.getClass().getMethod("setLast", boolean.class);
					setLast.invoke(node, Boolean.TRUE);
				}
				Method getChild = node.getClass().getMethod("getChild");
				List<Object> childs = (List<Object>) getChild.invoke(node);

				Method getExpanded = node.getClass().getMethod("getExpanded");
				Boolean expanded = (Boolean) getExpanded.invoke(node);

				if (CollectionUtils.isNotEmpty(childs) && BooleanUtils.isTrue(expanded)) {
					loop(list, childs, rows + 1);
				}
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List getItemList() {
		if (pageContext.getAttribute(items, PageContext.PAGE_SCOPE) != null) {
			return (List) pageContext.getAttribute(items, PageContext.PAGE_SCOPE);
		}
		if (pageContext.getAttribute(items, PageContext.REQUEST_SCOPE) != null) {
			return (List) pageContext.getAttribute(items, PageContext.REQUEST_SCOPE);
		}
		if (pageContext.getAttribute(items, PageContext.SESSION_SCOPE) != null) {
			return (List) pageContext.getAttribute(items, PageContext.SESSION_SCOPE);
		}
		if (pageContext.getAttribute(items, PageContext.APPLICATION_SCOPE) != null) {
			return (List) pageContext.getAttribute(items, PageContext.APPLICATION_SCOPE);
		}
		return null;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getIndexed() {
		return indexed;
	}

	public void setIndexed(String indexed) {
		this.indexed = indexed;
	}

}
