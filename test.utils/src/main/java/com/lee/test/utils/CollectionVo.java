package com.lee.test.utils;

import java.util.List;
import java.util.Set;
/**
 * 转换器不能识别集合类型，因此需要把集合封装到vo或者po里面
 * @author LIPAN
 *
 */
public class CollectionVo {
	
	private List<String> list;
	
	private Set<String> set;

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public Set<String> getSet() {
		return set;
	}

	public void setSet(Set<String> set) {
		this.set = set;
	}
}
