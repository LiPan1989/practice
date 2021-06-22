package com.lee.test.normal.module;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.Set;

/**
 * 处理步骤枚举
 *
 * @author cdlipan5
 * @date 2019-03-22 17:41
 * @description
 **/

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ProcessStep {
    /**
     * 校验步骤
     **/
    COMMON_PARAM_CHECK(2001, "通用校验", true),
    JDSPUIDS_NOT_EMPTY_CHECK(2002, "京东spuid不能为空", true),
    BIZ_CHANNEL_VALIDATE_CHECK(2003, "校验商品spu渠道", true),
    AUDIT_STATE_NOT_REJECT_CHECK(2004, "校验商品审核是否不是驳回状态", true),
    AUDIT_STATE_PENDING_AUDIT_CHECK(2005, "校验商品审核是否是等待审核状态", true),
    /**
     * 2006:SPU渠道校验
     */
    SPU_CHANNEL_CHECK(2006, "SPU渠道校验", true),

    VENDERID_CHECK(2007, "商家id正确性校验", true),
    DEL_OR_REC_AUDIT_STATE_CHECK(2008, "删除恢复商品审核状态校验", true),
    FILTRATE_CHANNEL_SKU(2009, "过滤非渠道SKU", true),
    JDSKUIDS_NOT_EMPTY_CHECK(2010, "京东skuid不能为空", true),
    FILTRATE_CHANNEL_SKU_WITH_RST(2011, "过滤非渠道sku并返回错误信息", true),
    SKU_STATE_NOT_DELETE_CHECK(2012, "过滤已失效sku", true),
    SPU_STATE_NOT_DELETE_CHECK(2013, "过滤已失效spu", true),
    AUDIT_PASS_AUDIT_STATE_CHECK(2014, "操作审核通过判断是否待审核", true),
    IMAGE_CONTENT_CHECK(2015, "图片内容合法性校验", true),
    ATTRS_CONTENT_CHECK(2016, "商品属性内容合法性校验", true),
    VENDER_ID_NOT_NULL_CHECK(2017,"venderId不能为null",true),
    RISK_CONTROL_CHECK(2018,"风控检查校验节点",true),
    /**
     * 渠道业务校验-用于渠道特有业务校验,所有写接口都可能需要做的校验逻辑
     */
    PLATFORM_BIZ_CHECK(2019, "渠道业务校验", true),
    FILTRATE_INVALID_SKU(2020, "过滤无效sku", true),
    FILTRATE_INVALID_SPU(2021, "过滤无效spu", true),
    IDEMPOTENT_CHECK(2022, "幂等校验", true),
    /**
     * 写商品信息步骤
     **/
    SAVE_WARE_TO_POP(3001, "保存至POP侧", true),
    SAVE_WARE_TO_BWARE(3002, "保存至B商品平台侧", false),
    SAVE_WARE_TO_MCW(3003, "保存至多渠道商品平台侧", true),
    SAVE_WARE_TO_MS(3039, "保存商品信息到主站多渠道商品平台", true),
    SAVE_B_PRICE(3004, "保存B价格", true),
    SAVE_B_AD_WORD(3005, "保存广告词", true),
    SAVE_BAFFLE_SKU(3006, "保存sku维度挡板", true),
    SAVE_BAFFLE_SPU(3007, "保存spu维度挡板", true),
    SEND_DATA_CHANGE_MQ(3008, "发送数据变更MQ", true),
    SEND_BIZ_LINE_MARK_MQ(3009, "发送业务线打标MQ", true),
    MAIN_DATA_MARK(3012, "主数据打标", true),
    SET_DEFAULT_INFO(3013, "设置默认信息", true),
    /**
     * 渠道fieldMark默认值处理,处理成全部数据都由渠道维护，
     * 不使用未修改同步主站逻辑
     */
    DEFAULT_FIELD_MARK(3014, "渠道fieldMark默认值处理", true),
    COPY_ZY_WARE_TO_POP(3015, "拷贝自营到POP", true),

    SET_THIRD_CID_SALE_ATTRS(3016, "设置三级类目销售属性", true),

    SET_UNLIMIT_CID_SALE_ATTRS(3017, "设置末级类目销售属性", true),

    SET_DEFAULT_SPU_B2B_SPEC_INFO(3018, "设置默认的spu维度b2b自定义属性", true),
    SAVE_SPU_SHELF_STATE(3019,"保存SPU商品上下架状态到多渠道", false),
    BATCH_SAVE_WARE_AUDIT(3020,"保存商品审核状态到多渠道", false),
    BATCH_DEL_OR_RECOVER_WARE(3021,"批量删除或恢复商品到多渠道", false),
    SAVE_AREA_LIMIT(3022,"保存区域限售信息到多渠道", false),
    SAVE_BIG_FIELD(3023,"保存大字段信息到多渠道", false),
    SAVE_SKU_SHELF_STATE(3024,"保存SKU商品上下架状态到多渠道", false),
    /** 商品属性修改 */
    UPDATE_MC_ATTRS(3025, "修改多渠道商品属性", false),
    UPDATE_POP_ATTRS(3026,"修改pop商品属性", true),
    UPDATE_LABRADOR_ATTRS(3037,"修改Labrador商品属性", true),
    UPDATE_SPU_ATTRS_CHECK(3038, "修改商品属性前检查属性信息", true),

    BATCH_DEL_OR_RECOVER_POP_WARE(3027,"批量删除或恢复商品到POP", true),
    BATCH_DEL_OR_RECOVER_LABRADOR_WARE(3035,"批量删除或恢复商品到labrador", true),

    SAVE_SPU_SHELF_STATE_POP(3028,"保存SPU商品上下架状态到POP", true),
    SAVE_SPU_SHELF_STATE_LABRADOR(3036,"保存SPU商品上下架状态到Labrador", true),
    SAVE_SKU_SHELF_STATE_CETUS(3055,"保存SKU商品上下架状态到cetus", false),
    SAVE_SKU_SHELF_STATE_MS(3056, "保存SKU商品上下架状态到主站多渠道", true),
    /**
     *通过LABRADOR接口保存商品信息
     */
    SAVE_WARE_TO_LABRADOR(3029, "保存商品信息到LABRADOR", true),

    MARK_SKU_PROPS_CHECK(3030, "对比渠道是否允许打标", true),
    MARK_SKU_PROPS_PRE_COMP(3031, "修改打标数据前对比", true),
    MARK_SKU_PROPS_SAVE(3032,"保存商品打标数据", false),

    FILL_BACK_SPU_FROM_LABRADOR(3033, "回填labrador的sku信息", true),
    FILL_BACK_SKU_FROM_LABRADOR(3034, "回填labrador的spu信息", true),

    BATCH_DEL_OR_RECOVER_SKU(3040,"批量删除或恢复sku到多渠道", false),
    GENERATE_JDSPUID_AND_JDSKUID(3045, "自定义生成jdSpuId和jdSkuId", true),
    /**
     * 图片写入相关
     */
    UPDATE_MC_IMAGE(3050, "保存多渠道图片", true),
    UPDATE_LABRADOR_IMAGE(3051, "保存Labrador图片", true),
    /** 没有这个节点 **/
    UPDATE_POP_IMAGE(3052, "保存POP图片", true),
    /** 这个节点后续不再使用了，逻辑内嵌到列表更新里了 **/
    UPDATE_SKU_PRIMARY_IMAGE(3053, "更新sku主图", true),
    UPDATE_SPU_PRIMARY_IMAGE(3054, "更新spu主图", true),


    /** 商品销售属性修改 */
    UPDATE_POP_SALE_ATTRS(3100,"修改pop商品销售属性", true),
    UPDATE_MC_SALE_ATTRS(3101, "修改多渠道商品销售属性", false),
    UPDATE_LABRADOR_SALE_ATTRS(3102, "修改多渠道商品销售属性", true),

    SYNC_KA_SHELF_STATE(3041,"掌柜宝同步大客户同售商品", true),
    MARK_SKU_PROPS_SYNC_KA(3042,"掌柜宝同步大客户同售商品", true),
    SYNC_KA_SHELF_STATE_WITH_OTHER_PLAT(3043,"极光同步大客户同售商品", true),


    /** 类目迁移 3130~3150 （预留20个编号）**/
    TRANSFER_CATEGORY_LABRADOR(3130, "POP组件化的类目迁移", true),
    TRANSFER_CATEGORY_MC(3131, "多渠道的类目迁移", true),

    TRANSFER_CHILD_CATEGORY_LABRADOR(3140, "POP组件化的子商品类目迁移", true),
    TRANSFER_CHILD_CATEGORY_MC(3141, "多渠道的子商品类目迁移", true),


    /** 商品发布处理节点 3300 ~ 3399 （预留100个编号） [start] **/
    WARE_PUBLISH_DATA_TYPE_FILTER(3300, "skuDataType拦截", true),
    WARE_PUBLISH_RULE_FILTER(3301, "发布规则拦截", true),
    /**
     * 渠道校验
     * 1.productsFrom 允许入参哪些值
     */
    WARE_PUBLISH_productsFrom(3303, "渠道校验", true),
    WARE_PUBLISH_DAL_COPY(3302, "执行发布", false),
    WARE_PUBLISH_TO_POOL(3304,"发布至商品池sku表", false),
    /** 商品发布处理节点 3300 ~ 3399 （预留100个编号） [end]**/

    /**
     * 读商品信息步骤
     */
    BATCH_GET_SKU_INFO_ASSUME_RETURN_FIELD(4001, "聚合查询商品基本信息列表", false),
    BATCH_GET_SKU_INFO_GET_WARE_BASE_INFO(4002, "查询商品基本信息", false),
    BATCH_GET_SKU_INFO_GET_WARE_IMAGE_INFO(4003, "查询商品图片信息", false),
    BATCH_GET_SKU_INFO_GET_SKU_B2B_SPEC_INFO(4004, "查询sku特殊属性信息", false),
    BATCH_GET_SKU_INFO_GET_SKU_B2B_BIZ_SPEC_INFO(4005, "查询sku业务特殊属性信息", false),
    BATCH_GET_SKU_INFO_GET_SKU_B2B_BIZ_ARRAY_SPEC_INFO(4006, "查询sku业务数组类特殊属性信息", false),
    BATCH_GET_SKU_INFO_GET_SPU_AREA_LIMIT_INFO(4007, "查询商品区域限制信息", false),
    BATCH_GET_SKU_INFO_GET_SPU_BIG_FIELD_INFO(4008, "查询大字段信息", false),
    BATCH_GET_SKU_INFO_GET_SPU_QUALIFICATION_INFO(4009, "查询商品规格参数信息", false),
    BATCH_GET_SKU_INFO_GET_SPU_GMS_SPEC_INFO(4010, "查询商品主站特殊属性信息", false),
    BATCH_GET_SKU_INFO_GET_SPU_B2B_SPEC_INFO(4011, "查询spuB2b特殊属性信息", false),
    BATCH_GET_SKU_INFO_GET_CHILD_SKU_INFO(4012, "查询虚拟组套子商品信息", false),
    BATCH_GET_SKU_INFO_FILL_CHANNEL_FIELD_INFO(4013, "填充渠道字段", true),
    GET_WARE_ATTR_FROM_REDIS_INFO(4014, "从本地缓存获取商品属性", true),
    GET_WARE_ATTR_FROM_GMS_INFO(4015, "从主站gms获取商品属性", true),
    AREA_LIMIT_READ(4016, "查询区域限售信息", false),
    PRODUCT_ATTRS_READ(4018, "查询商品属性信息", false),
    PRODUCE_DATE_AREA_ID_VALIDATE(4019, "生产日期查询的省市id校验", true),
    /**
     * 4033(生产日期查询的库存入参校验)
     */
    PRODUCE_DATE_STOCK_INFO_INPUT_VALIDATE(4033, "生产日期查询的库存入参校验", true),

    PRODUCE_DATE_GET_STOCK_INFO(4020,"查询库房信息", true),
    PRODUCE_DATE_GET(4021,"生产日期查询", false),
    BATCH_GET_SKU_INFO_GET_SPU_B2B_BIZ_INFO(4022, "查询商品spu的b2b业务属性", false),
    BATCH_GET_SKU_INFO_GET_SPU_B2B_BIZ_ARRAY_INFO(4023, "查询spu的B2b属性数组", false),
    BATCH_GET_SKU_INFO_GET_SKU_GMS_SPEC_INFO(4024,"查询skuPOP商品属性",false),
    BATCH_GET_SPU_INFO_GET_BASE_INFO_FROM_POP(4025,"查询skuPop基本信息",true),
    BATCH_GET_SKU_INFO_FIX_POP_IMAGE_PATH(4026,"查询修复pop主图",true),
    BATCH_GET_SKU_INFO_CONVERT_CETUS_STATUS(4027, "cetusWare转换状态", true),
    BATCH_GET_SPU_IMAGE_FROM_SKU(4028, "从sku维度图片列表填充spu主图", true),
    FROM_GMS_FILL_BASE_INFO(4029, "从主站去补齐部分商品信息", true),
    //FROM_B2BWARE_FILL_BASE_INFO(4030, "从B商品去补齐商品信息", true),
    FROM_CRS_FILL_BASE_INFO(4035, "从主站组件化接口补齐商品信息", true),
    FROM_LABRADOR_FILL_MULTI_SITE_INFO(4037, "从Labrador补齐站点商品信息", true),
    FROM_CRS_FILL_MULTI_SITE_INFO(4039, "从主站读服务补齐站点商品信息", true),

    FETCH_MISS_WARE_INFO_BY_GMS(4031, "从主站去查询渠道不存在的商品信息", true),
    //FETCH_MISS_WARE_INFO_BY_B2BWARE(4032, "从B商品去查询渠道不存在的商品信息", true),
    FETCH_JD_SKU_ID_BY_THIRD_SKU_ID(4034, "根据三方SkuId获取对应的京东SkuId", true),
    MERGE_CHANNEL_INFO(4036, "合并渠道自己维护的信息", true),
    FETCH_TERMINAL_SKU_ID(4038, "抓取属于主站多渠道商品的skuID", true),
    /**
     * 读取商品spu信息步骤
     */
    BATCH_GET_SPU_INFO_ASSUME_RETURN_FIELD(5001, "聚合查询商品基本信息列表", false),
    BATCH_GET_SPU_INFO_GET_WARE_BASE_INFO(5002, "查询spu商品基本信息", false),
    BATCH_GET_SPU_INFO_GET_SKU_WARE_BASE_INFO(5003, "查询sku商品基本信息", false),
    BATCH_GET_SPU_INFO_GET_WARE_IMAGE_INFO(5004, "查询商品图片信息", false),
    BATCH_GET_SPU_INFO_GET_SPU_B2B_SPEC_INFO(5005, "查询spuB2B特殊属性信息", false),
    BATCH_GET_SPU_INFO_GET_SPU_B2B_BIZ_INFO(5006, "查询spuB2B业务特殊属性信息", false),
    BATCH_GET_SPU_INFO_GET_SPU_QUALIFY_CATION_PROPERTY_INFO(5007, "查询spu规格参数信息", false),
    BATCH_GET_SPU_INFO_GET_SPU_B2B_BIZ_ARRAY_INFO(5008, "查询spuB2B业务数组属性信息", false),
    BATCH_GET_SPU_INFO_GET_SPU_SPEC_INFO(5009, "查询spu主站特殊属性信息", false),
    BATCH_GET_SPU_INFO_GET_SPU_INFO_FROM_POP(5010, "查询POPspu信息", true),
    BATCH_GET_SPU_INFO_GET_SPU_BIG_FIELD_INFO(5011,"查询商品大字段信息",true)

    ;
    /**
     * code
     */
    private Integer code;
    /**
     * name
     */
    private String name;
    /**
     * skipEnable
     */
    private Boolean skipEnable;


    private static Map<Integer, ProcessStep> Cache;


    static {
        Cache = Maps.newHashMap();
        Set<Integer> codes = Sets.newHashSetWithExpectedSize(values().length);
        for (ProcessStep step : values()) {
            if (codes.contains(step.code)) {
                throw new RuntimeException("step code:" + step.code + ",有重的");
            }
            codes.add(step.code);
            Cache.put(step.code, step);
        }
    }

    public static ProcessStep getByCode(Integer code) {
        return Cache.get(code);
    }

    private final String 从主站读服务补齐站点商品信息 = "从主站读服务补齐站点商品信息";

    public static void main(String[] args) {
        for (ProcessStep step : values()) {
            System.out.println(step.getCode()+":"+step.name+","+step.name());
        }
    }

}
