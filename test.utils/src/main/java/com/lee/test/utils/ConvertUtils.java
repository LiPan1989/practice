package com.lee.test.utils;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * po和vo之间转换
 *
 * @author LIPAN
 */
public class ConvertUtils {
    /**
     * 不设值转换，即源已经有值，不需要在转换时候进行设值
     *
     * @param source  源
     * @param target  目标
     * @param convert 转换器
     */
    public static void convert(Object source, Object target, Converter convert) {
        // 初始化转换工具
        BeanCopier coper = BeanCopier.create(source.getClass(), target.getClass(), true);
        // 如果没有指定转换器则使用默认转换器
        if (convert == null) {
            convert = new DefaultConverter(target.getClass());
        }
        // 进行转换
        coper.copy(source, target, convert);
    }

    /**
     * 设值转换，即源的值需要动态设值，则把需要设置的值用map传进来进行一个动态设值并转换
     * 一定要注意map的key必须是你vo或者po的字段名，否则无法进行转换
     *
     * @param source  源
     * @param target  目标
     * @param convert 转换器
     * @param param   需要给源设置的值
     */
    public static void convertByMap(Object source, Object target, Converter convert, Map<String, Object> param) {
        // 初始化转换工具
        BeanCopier coper = BeanCopier.create(source.getClass(), target.getClass(), true);
        // 如果没有指定转换器则使用默认转换器
        if (convert == null) {
            convert = new DefaultConverter(target.getClass(), param);
        }
        // 进行转换
        coper.copy(source, target, convert);
    }

    /**
     * 不设值并使用默认转换器转换，即源已经有值，不需要在转换时候进行设值
     *
     * @param source 源
     * @param target 目标
     */
    public static void convert(Object source, Object target) {
        convert(source, target, null);
    }

    /**
     * 设值并使用默认转换器转换，即源的值需要动态设值，则把需要设置的值用map传进来进行一个动态设值并转换
     * 一定要注意map的key必须是你vo或者po的字段名，否则无法进行转换
     *
     * @param source 源
     * @param target 目标
     * @param param  需要给源设置的值
     */
    public static void convertByMap(Object source, Object target, Map<String, Object> param) {
        convertByMap(source, target, null, param);
    }

    /**
     * List和Set转换
     *
     * @param source      源
     * @param target      目标
     * @param convert     转换器
     * @param useConvert  是否使用转换器
     * @param tartgetType 目标class
     */
    public static void convertByCollection(Object source, Object target, Converter convert, boolean useConvert, Class<?> tartgetType) {
        BeanCopier coper = BeanCopier.create(source.getClass(), target.getClass(), useConvert);

        if (convert == null) {
            convert = new DefaultConverter(tartgetType);
        }
        coper.copy(source, target, convert);
    }

    /**
     * List转换
     *
     * @param source      源
     * @param target      目标
     * @param tartgetType 目标类型class，例如List<CodeVo>转换成List<CodePo>，tartgetType就是CodePo.class
     * @return list 注意：这里的转换有返回值了
     */
    @SuppressWarnings("unchecked")
    public static <E> List<E> convertByList(Object source, Object target, Class<?> tartgetType) {
        // 转换器不能识别集合类型，因此需要把集合封装到vo或者po里面
        CollectionVo from = new CollectionVo();
        CollectionVo to = new CollectionVo();
        if (target instanceof List) {
            from.setList((List<E>) source);
            to.setList((List<E>) target);
            convertByCollection(from, to, null, true, tartgetType);
        }

        return (List<E>) to.getList();

    }

    /**
     * Set转换
     * @param source      源
     * @param target      目标
     * @param tartgetType 目标类型class，例如Set<CodeVo>转换成Set<CodePo>，tartgetType就是CodePo.class
     * @return Set 注意：这里的转换有返回值了
     */
    @SuppressWarnings("unchecked")
    public static <E> Set<E> convertBySet(Object source, Object target, Class<?> tartgetType) {
        CollectionVo from = new CollectionVo();
        CollectionVo to = new CollectionVo();

        if ((source instanceof Set) && (target instanceof Set)) {
            from.setSet((Set<E>) source);
            to.setSet((Set<E>) target);
            convertByCollection(from, to, null, true, tartgetType);
        }
        return (Set<E>) to.getSet();
    }

}
