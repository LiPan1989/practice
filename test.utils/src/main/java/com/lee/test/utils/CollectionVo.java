package com.lee.test.utils;

import java.util.List;
import java.util.Set;
/**
 * 转换器不能识别集合类型，因此需要把集合封装到vo或者po里面
 * @author LIPAN
 *
 */
public class CollectionVo {
	
	private List<?> list;
	
	private Set<?> set;

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public Set<?> getSet() {
		return set;
	}

	public void setSet(Set<?> set) {
		this.set = set;
	}


}
