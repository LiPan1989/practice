package com.lee.test.utils;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 默认转换器
 * 
 * @author LIPAN
 * 
 */
public class DefaultConverter implements Converter {

	// 目标calss
	Class<?> to;
	// 源值参数，注意map的key值必须和vo或者po的字段名相同
	Map<String, Object> param;

	public DefaultConverter(Class<?> to) {
		this.to = to;
	}

	public DefaultConverter(Class<?> to, Map<String, Object> param) {
		this.to = to;
		this.param = param;
	}

	@Override
	public Object convert(Object value, Class target, Object context) {
		// 给源设值
		if (value == null && param != null) {
			// 通过字段的set方法并且转换大小写来获取到vo或者po的字段名称
			String str = context.toString().substring(3);
			String body = str.substring(1);
			String head = str.substring(0, 1).toLowerCase();
			str = head + body;

			if (param.get(str) != null) {
				// 给源设值
				value = param.get(str);
				// 设值完后丢弃参数值，否则会进入无限循环
				param.put(str, null);
			}
		}

		// 设置不同属性的转换规则
		if (value != null) {
			if (value instanceof Integer) {
				return (Integer) value;
			} else if (value instanceof String) {
				return (String) value;
			} else if (value instanceof Boolean) {
				return (Boolean) value;
			} else if (value instanceof Long) {
				return (Long) value;
			} else if (value instanceof Short) {
				return (Short) value;
			} else if (value instanceof Float) {
				return (Float) value;
			} else if (value instanceof Double) {
				return (Double) value;
			} else if (value instanceof Enum) {
				return (Enum) value;
			} else if (value instanceof Byte) {
				return (Byte) value;
			} else if (value instanceof Timestamp) {
				// 日期格式
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = null;
				try {
					// 转换日期
					date = sdf.parse(sdf.format(value));
				} catch (ParseException e) {
					throw new RuntimeException("format date failed!", e);
				}
				return date;
			} else if (value instanceof Date) {
				// 日期格式
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = null;
				try {
					// 转换日期
					date = sdf.parse(sdf.format(value));
				} catch (ParseException e) {
					throw new RuntimeException("format date failed!", e);
				}

				return date;
			} else if (value instanceof BigDecimal) {
				BigDecimal bd = (BigDecimal) value;
				return bd;
				// vo里面包含其它vo的转换
			} else if ((value.toString().contains("Vo") || value.toString().contains("VO")
					|| value.toString().contains("vo") || value.toString().contains("vO"))
					&& !(value instanceof Collection) && !(value instanceof Map)) {
				Object po = null;
				try {
					// 用反射来实例化目标po
					po = target.newInstance();
				} catch (Exception e) {
					throw new RuntimeException("create object error", e);
				}

				// 初始化转换工具
				BeanCopier coper = BeanCopier.create(value.getClass(), target, true);
				// 通过回调来转换vo里面包含的其它vo
				coper.copy(value, po, this);
				return po;
				// po里面包含其它po的转换
			} else if ((value.toString().contains("Po") || value.toString().contains("PO")
					|| value.toString().contains("po") || value.toString().contains("pO"))
					&& !(value instanceof Collection) && !(value instanceof Map)) {
				Object vo = null;
				try {
					// 用反射来实例化目标vo
					vo = target.newInstance();
				} catch (Exception e) {
					throw new RuntimeException("create object error", e);
				}

				// 初始化转换工具
				BeanCopier coper = BeanCopier.create(value.getClass(), target, true);
				// 通过回调来转换po里面包含的其它po
				coper.copy(value, vo, this);
				return vo;
			} else if (value instanceof List) {
				List toList = new ArrayList();
				List fromList = (List) value;
				for (int i = 0; i < fromList.size(); i++) {
					Object vo = fromList.get(i);
					Object po = null;
					try {
						po = to.newInstance();
					} catch (Exception e) {
						throw new RuntimeException("create object error", e);
					}
					BeanCopier coper = BeanCopier.create(vo.getClass(), to, true);
					coper.copy(vo, po, this);
					toList.add(po);
				}
				return toList;
			} else if (value instanceof Set) {
				Set set = new HashSet();
				Set setvo = (Set) value;
				Iterator it = setvo.iterator();
				while (it.hasNext()) {
					Object vo = it.next();
					Object po = null;
					try {
						po = to.newInstance();
					} catch (Exception e) {
						throw new RuntimeException("create object error", e);
					}
					BeanCopier coper = BeanCopier.create(vo.getClass(), to, true);
					coper.copy(vo, po, this);
					set.add(po);
				}
				return set;
			}
		}
		return null;
	}
}
