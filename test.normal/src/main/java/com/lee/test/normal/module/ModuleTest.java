package com.lee.test.normal.module;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jd.b2b.ware.biz.sdk.enums.BizChannelEnum;
import com.jd.b2b.ware.commons.enums.Platform;
import org.junit.platform.commons.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author cdlipan5
 * @create 2021-03-16 上午11:56
 **/
public class ModuleTest {
//    private static final String env = "yufa";
//    private static final String envName = "预发";

    private static final String env = "production";
    private static final String envName = "生产";

//    private static final String env = "test";
//    private static final String envName = "测试";

    private static Map<String, String> moduleNameMap = Maps.newHashMap();
    private static Map<String, String> moduleConfMap = Maps.newHashMap();
    private static Map<BizChannelEnum, Platform> channleMap = Maps.newHashMap();

    static {
        moduleNameMap.put("McWareAdd", "AddWare-新增商品");
        moduleNameMap.put("McWarePublish", "WarePublish-商品发布");

        moduleConfMap.put("warePublishRulesName", "WarePublishRulesName-发布规则名称");
        moduleConfMap.put("allowDataTypes", "WarePublishAllowDataTypes-允许发布的商品类型");
        moduleConfMap.put("ware_publish_process_flow", "WarePublishFlowConfig-商品发布流程");
        moduleConfMap.put("force_validate_sku_images", "ForceValidateSkuImagesSwitch-强制校验sku图片");
        moduleConfMap.put("ware_add_process_flow", "WareAddFlowConfig-商品新增流程");
        moduleConfMap.put("force_validate_spu_images", "ForceValidateSpuImagesSwitch-强制校验spu图片");

        //主站商品
        channleMap.put(BizChannelEnum.ALL, Platform.Gms);
        //易售平台
        channleMap.put(BizChannelEnum.YISHOU, Platform.YiShou);
        //医药城
        channleMap.put(BizChannelEnum.YAO, Platform.Yyc);
        //新通路
        channleMap.put(BizChannelEnum.XTL, Platform.Xtl);
        //分销
        channleMap.put(BizChannelEnum.FX, Platform.Fx);
        //供销分销
        channleMap.put(BizChannelEnum.GXPT_FX, Platform.GxFx);
        //供销代销
        channleMap.put(BizChannelEnum.GXPT_DX, Platform.GxDx);
        //托盘
        channleMap.put(BizChannelEnum.TUOPAN, Platform.TuoPan);
        //大客户极光
        channleMap.put(BizChannelEnum.JiGuang, Platform.JiGuang);
        //全球购
        channleMap.put(BizChannelEnum.FX_GLOBAL, Platform.Global);
        //大客户
        channleMap.put(BizChannelEnum.KA, Platform.Ka);
        //家电京采-大客户
        channleMap.put(BizChannelEnum.JIADIAN_KA, Platform.JiaDianKa);
        //家电京采 BizChannelEnum.JIADIAN_JC 已废弃,同BizChannelEnum.JIADIAN_XC
        channleMap.put(BizChannelEnum.JIADIAN_JC, Platform.JiaDianXc);
        //家电京采-渠道下沉
        channleMap.put(BizChannelEnum.JIADIAN_XC, Platform.JiaDianXc);
        //新通路-分销宝
        channleMap.put(BizChannelEnum.FXB, Platform.Fxb);
        //整车SAAS
        channleMap.put(BizChannelEnum.Car, Platform.Car);
        //万家
        channleMap.put(BizChannelEnum.WJ, Platform.WJ);

        //Gcy(17,"工采云Sku")
        channleMap.put(BizChannelEnum.Gcy, Platform.Gcy);

        //GcyM(18,"工采云Mku")
        channleMap.put(BizChannelEnum.GcyM, Platform.GcyM);

        //Mm(19, "B2B买卖平台")
        channleMap.put(BizChannelEnum.Mm, Platform.Mm);
    }

    public static void main(String[] args) {
        String json = "[\n" +
                "  {\n" +
                "    \"name\": \"McWarePublish\",\n" +
                "    \"alias\": \"MC发布商品组件\",\n" +
                "    \"type\": 1,\n" +
                "    \"items\": {\n" +
                "      \"Mm\": {\n" +
                "        \"id\": 237,\n" +
                "        \"identity\": \"Mm\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 5,\n" +
                "        \"items\": {\n" +
                "          \"allowDataTypes\": \"999\",\n" +
                "          \"ware_publish_process_flow\": \"3300->3302\",\n" +
                "          \"warePublishRulesName\": \"\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"YAO_selfUpdate\": {\n" +
                "        \"id\": 187,\n" +
                "        \"identity\": \"YAO_selfUpdate\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 17,\n" +
                "        \"items\": {\n" +
                "          \"allowDataTypes\": \"10\",\n" +
                "          \"ware_publish_process_flow\": \"3300->3302\",\n" +
                "          \"warePublishRulesName\": \"\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"ALL\": {\n" +
                "        \"id\": 190,\n" +
                "        \"identity\": \"ALL\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 16,\n" +
                "        \"items\": {\n" +
                "          \"allowDataTypes\": \"88\",\n" +
                "          \"ware_publish_process_flow\": \"3300->3302\",\n" +
                "          \"warePublishRulesName\": \"\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"YAO\": {\n" +
                "        \"id\": 165,\n" +
                "        \"identity\": \"YAO\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 55,\n" +
                "        \"items\": {\n" +
                "          \"allowDataTypes\": \"10\",\n" +
                "          \"ware_publish_process_flow\": \"3300->3302\",\n" +
                "          \"warePublishRulesName\": \"\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"FX-Wans\": {\n" +
                "        \"id\": 164,\n" +
                "        \"identity\": \"FX-Wans\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 33,\n" +
                "        \"items\": {\n" +
                "          \"allowDataTypes\": \"\",\n" +
                "          \"ware_publish_process_flow\": \"3302\",\n" +
                "          \"warePublishRulesName\": \"\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"GXPT_DX\": {\n" +
                "        \"id\": 205,\n" +
                "        \"identity\": \"GXPT_DX\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 25,\n" +
                "        \"items\": {\n" +
                "          \"allowDataTypes\": \"10\",\n" +
                "          \"ware_publish_process_flow\": \"3300->3302\",\n" +
                "          \"warePublishRulesName\": \"\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"Global\": {\n" +
                "        \"id\": 199,\n" +
                "        \"identity\": \"Global\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 15,\n" +
                "        \"items\": {\n" +
                "          \"allowDataTypes\": \"\",\n" +
                "          \"ware_publish_process_flow\": \"3302\",\n" +
                "          \"warePublishRulesName\": \"\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"GXPT_FX\": {\n" +
                "        \"id\": 204,\n" +
                "        \"identity\": \"GXPT_FX\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 23,\n" +
                "        \"items\": {\n" +
                "          \"allowDataTypes\": \"\",\n" +
                "          \"ware_publish_process_flow\": \"3302\",\n" +
                "          \"warePublishRulesName\": \"\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"XTL\": {\n" +
                "        \"id\": 166,\n" +
                "        \"identity\": \"XTL\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 42,\n" +
                "        \"items\": {\n" +
                "          \"allowDataTypes\": \"\",\n" +
                "          \"ware_publish_process_flow\": \"3302\",\n" +
                "          \"warePublishRulesName\": \"\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"GcyM\": {\n" +
                "        \"id\": 232,\n" +
                "        \"identity\": \"GcyM\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 25,\n" +
                "        \"items\": {\n" +
                "          \"allowDataTypes\": \"\",\n" +
                "          \"ware_publish_process_flow\": \"3302\",\n" +
                "          \"warePublishRulesName\": \"\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"FXB\": {\n" +
                "        \"id\": 176,\n" +
                "        \"identity\": \"FXB\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 26,\n" +
                "        \"items\": {\n" +
                "          \"allowDataTypes\": \"\",\n" +
                "          \"ware_publish_process_flow\": \"3302\",\n" +
                "          \"warePublishRulesName\": \"\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"FX\": {\n" +
                "        \"id\": 162,\n" +
                "        \"identity\": \"FX\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 43,\n" +
                "        \"items\": {\n" +
                "          \"allowDataTypes\": \"10,11,12,13\",\n" +
                "          \"ware_publish_process_flow\": \"3300->3302\",\n" +
                "          \"warePublishRulesName\": \"\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"WJ\": {\n" +
                "        \"id\": 226,\n" +
                "        \"identity\": \"WJ\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 4,\n" +
                "        \"items\": {\n" +
                "          \"allowDataTypes\": \"\",\n" +
                "          \"ware_publish_process_flow\": \"3302\",\n" +
                "          \"warePublishRulesName\": \"\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"Car\": {\n" +
                "        \"id\": 220,\n" +
                "        \"identity\": \"Car\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 5,\n" +
                "        \"items\": {\n" +
                "          \"allowDataTypes\": \"\",\n" +
                "          \"ware_publish_process_flow\": \"3302\",\n" +
                "          \"warePublishRulesName\": \"\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"KA\": {\n" +
                "        \"id\": 167,\n" +
                "        \"identity\": \"KA\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 46,\n" +
                "        \"items\": {\n" +
                "          \"allowDataTypes\": \"\",\n" +
                "          \"ware_publish_process_flow\": \"3303->3302->3304\",\n" +
                "          \"warePublishRulesName\": \"\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"XTL_fxbAddWare\": {\n" +
                "        \"id\": 179,\n" +
                "        \"identity\": \"XTL_fxbAddWare\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 20,\n" +
                "        \"items\": {\n" +
                "          \"allowDataTypes\": \"\",\n" +
                "          \"ware_publish_process_flow\": \"3302\",\n" +
                "          \"warePublishRulesName\": \"\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"FX_GLOBAL\": {\n" +
                "        \"id\": 203,\n" +
                "        \"identity\": \"FX_GLOBAL\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 23,\n" +
                "        \"items\": {\n" +
                "          \"allowDataTypes\": \"10\",\n" +
                "          \"ware_publish_process_flow\": \"3300->3302\",\n" +
                "          \"warePublishRulesName\": \"\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"Gcy\": {\n" +
                "        \"id\": 231,\n" +
                "        \"identity\": \"Gcy\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 19,\n" +
                "        \"items\": {\n" +
                "          \"allowDataTypes\": \"\",\n" +
                "          \"ware_publish_process_flow\": \"3302\",\n" +
                "          \"warePublishRulesName\": \"\"\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    \"toolConfItems\": {}\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"McWareAdd\",\n" +
                "    \"alias\": \"MC新增商品组件\",\n" +
                "    \"type\": 1,\n" +
                "    \"items\": {\n" +
                "      \"Mm\": {\n" +
                "        \"id\": 237,\n" +
                "        \"identity\": \"Mm\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 5,\n" +
                "        \"items\": {\n" +
                "          \"force_validate_sku_images\": \"true\",\n" +
                "          \"ware_add_process_flow\": \"2001->3013->2015->2016->3017->3029->3003->3006,3007\",\n" +
                "          \"force_validate_spu_images\": \"true\",\n" +
                "          \"warePublishRulesName\": \"\",\n" +
                "          \"allowDataTypes\": \"999\",\n" +
                "          \"ware_publish_process_flow\": \"3300->3302\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"YAO_selfUpdate\": {\n" +
                "        \"id\": 187,\n" +
                "        \"identity\": \"YAO_selfUpdate\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 17,\n" +
                "        \"items\": {\n" +
                "          \"force_validate_sku_images\": \"true\",\n" +
                "          \"ware_add_process_flow\": \"2001->3003\",\n" +
                "          \"force_validate_spu_images\": \"true\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"ALL\": {\n" +
                "        \"id\": 190,\n" +
                "        \"identity\": \"ALL\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 16,\n" +
                "        \"items\": {\n" +
                "          \"force_validate_sku_images\": \"false\",\n" +
                "          \"ware_add_process_flow\": \"2001->3013->3016->3015->3003->3004,3005,3006,3007->3012\",\n" +
                "          \"force_validate_spu_images\": \"false\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"YAO\": {\n" +
                "        \"id\": 165,\n" +
                "        \"identity\": \"YAO\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 55,\n" +
                "        \"items\": {\n" +
                "          \"force_validate_sku_images\": \"true\",\n" +
                "          \"ware_add_process_flow\": \"2001->3013->2015->2016->3016->3029->3003->3006,3007->3012\",\n" +
                "          \"force_validate_spu_images\": \"false\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"FX-Wans\": {\n" +
                "        \"id\": 164,\n" +
                "        \"identity\": \"FX-Wans\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 33,\n" +
                "        \"items\": {\n" +
                "          \"force_validate_sku_images\": \"false\",\n" +
                "          \"ware_add_process_flow\": \"2001->3013->3017->3029->3014->3003->3004,3005,3006,3007\",\n" +
                "          \"force_validate_spu_images\": \"false\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"GXPT_DX\": {\n" +
                "        \"id\": 205,\n" +
                "        \"identity\": \"GXPT_DX\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 25,\n" +
                "        \"items\": {\n" +
                "          \"force_validate_sku_images\": \"false\",\n" +
                "          \"ware_add_process_flow\": \"2001->3017->3029->3033->3034->3003->3006,3007\",\n" +
                "          \"force_validate_spu_images\": \"false\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"Global\": {\n" +
                "        \"id\": 199,\n" +
                "        \"identity\": \"Global\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 15,\n" +
                "        \"items\": {\n" +
                "          \"force_validate_sku_images\": \"false\",\n" +
                "          \"ware_add_process_flow\": \"2001->3013->2015->3016->3015->3003->3004,3005,3006,3007->3012\",\n" +
                "          \"force_validate_spu_images\": \"false\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"GXPT_FX\": {\n" +
                "        \"id\": 204,\n" +
                "        \"identity\": \"GXPT_FX\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 23,\n" +
                "        \"items\": {\n" +
                "          \"force_validate_sku_images\": \"false\",\n" +
                "          \"ware_add_process_flow\": \"2001->3017->3029->3033->3034->3003->3006,3007\",\n" +
                "          \"force_validate_spu_images\": \"false\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"XTL\": {\n" +
                "        \"id\": 166,\n" +
                "        \"identity\": \"XTL\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 42,\n" +
                "        \"items\": {\n" +
                "          \"force_validate_sku_images\": \"false\",\n" +
                "          \"ware_add_process_flow\": \"2001->3003\",\n" +
                "          \"force_validate_spu_images\": \"false\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"GcyM\": {\n" +
                "        \"id\": 232,\n" +
                "        \"identity\": \"GcyM\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 25,\n" +
                "        \"items\": {\n" +
                "          \"force_validate_sku_images\": \"false\",\n" +
                "          \"ware_add_process_flow\": \"2001->3013->2015->2019->3045->3003->3006,3007\",\n" +
                "          \"force_validate_spu_images\": \"false\",\n" +
                "          \"warePublishRulesName\": \"\",\n" +
                "          \"allowDataTypes\": \"\",\n" +
                "          \"ware_publish_process_flow\": \"3302\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"FXB\": {\n" +
                "        \"id\": 176,\n" +
                "        \"identity\": \"FXB\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 26,\n" +
                "        \"items\": {\n" +
                "          \"force_validate_sku_images\": \"false\",\n" +
                "          \"ware_add_process_flow\": \"2001->3003\",\n" +
                "          \"force_validate_spu_images\": \"false\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"FX\": {\n" +
                "        \"id\": 162,\n" +
                "        \"identity\": \"FX\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 43,\n" +
                "        \"items\": {\n" +
                "          \"force_validate_sku_images\": \"false\",\n" +
                "          \"ware_add_process_flow\": \"2001->3003\",\n" +
                "          \"force_validate_spu_images\": \"false\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"Car\": {\n" +
                "        \"id\": 220,\n" +
                "        \"identity\": \"Car\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 5,\n" +
                "        \"items\": {\n" +
                "          \"force_validate_sku_images\": \"false\",\n" +
                "          \"ware_add_process_flow\": \"2001->3013->2015->2016->3017->3029->3003->3006,3007\",\n" +
                "          \"force_validate_spu_images\": \"true\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"KA\": {\n" +
                "        \"id\": 167,\n" +
                "        \"identity\": \"KA\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 46,\n" +
                "        \"items\": {\n" +
                "          \"force_validate_sku_images\": \"false\",\n" +
                "          \"ware_add_process_flow\": \"2001->3013->3003\",\n" +
                "          \"force_validate_spu_images\": \"false\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"XTL_fxbAddWare\": {\n" +
                "        \"id\": 179,\n" +
                "        \"identity\": \"XTL_fxbAddWare\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 20,\n" +
                "        \"items\": {\n" +
                "          \"force_validate_sku_images\": \"false\",\n" +
                "          \"ware_add_process_flow\": \"2001->3015->3003\",\n" +
                "          \"force_validate_spu_images\": \"false\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"FX_GLOBAL\": {\n" +
                "        \"id\": 203,\n" +
                "        \"identity\": \"FX_GLOBAL\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 23,\n" +
                "        \"items\": {\n" +
                "          \"force_validate_sku_images\": \"false\",\n" +
                "          \"ware_add_process_flow\": \"2001->3013->2015->3016->3015->3003->3004,3005,3006,3007->3012\",\n" +
                "          \"force_validate_spu_images\": \"false\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"Gcy\": {\n" +
                "        \"id\": 231,\n" +
                "        \"identity\": \"Gcy\",\n" +
                "        \"domain\": \"\",\n" +
                "        \"version\": 19,\n" +
                "        \"items\": {\n" +
                "          \"force_validate_sku_images\": \"false\",\n" +
                "          \"ware_add_process_flow\": \"2001->3013->3017->2019->3029->3003->3006,3007\",\n" +
                "          \"force_validate_spu_images\": \"false\",\n" +
                "          \"warePublishRulesName\": \"\",\n" +
                "          \"allowDataTypes\": \"\",\n" +
                "          \"ware_publish_process_flow\": \"3302\"\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "]";
        List<Module> modules = JSONArray.parseArray(json, Module.class);
        BizCfg bizCfg = new BizCfg();
        List<Topic> topics = Lists.newArrayListWithCapacity(modules.size());
        bizCfg.setTopicList(topics);
        List<String> configList = Lists.newArrayList();
        modules.forEach(module -> {
            String moduleName = module.getName();
            String cjgModuleName = moduleNameMap.get(moduleName);
            String[] cjgModuleNameArr = cjgModuleName.split("-");
            Topic topic = new Topic();
            topics.add(topic);
            topic.setTopicCode(cjgModuleNameArr[0]+"-"+env);
            topic.setTopicName(cjgModuleNameArr[1]+"("+envName+")");
            List<Map<String, ModuleItem>> moduleItems = module.getItems();
            List<Group> groupList = Lists.newArrayListWithCapacity(moduleItems.size());
            topic.setGroupList(groupList);
            moduleItems.forEach(moduleItem -> {
                Map<BizChannelEnum, Group> groupMap = Maps.newHashMap();
                for (Map.Entry<String, ModuleItem> entry : moduleItem.entrySet()) {
                    String key = entry.getKey();
                    ModuleItem value = entry.getValue();
                    String[] keyArr;
                    if (!key.startsWith("GXPT") && !"FX_GLOBAL".equals(key)) {
                        if (key.indexOf("-") > 0) {
                            keyArr = key.split("-");
                        } else {
                            keyArr = key.split("_");
                        }
                    } else {
                        keyArr = new String[]{key};
                    }
                    String sceneId = (keyArr.length > 1) ? keyArr[1] : null;

                    String channelKey = keyArr[0];
                    if ("Global".equals(channelKey)) {
                        continue;
                    }
                    if ("GXPT".equals(channelKey)) {
                        channelKey = "GXPT_DX";
                    }
                    BizChannelEnum bizChannelEnum = BizChannelEnum.valueOf(channelKey);
                    Platform platform = channleMap.get(bizChannelEnum);

                    Group group = groupMap.get(bizChannelEnum);
                    if (group == null) {
                        group = new Group();
                        String groupCode = platform.name().toLowerCase();
                        group.setGroupCode(groupCode);
                        group.setGroupName(platform.getDesc());
                        groupList.add(group);
                    }
                    String groupCode = group.getGroupCode();

                    Map<String, String> items = value.getItems();
                    List<Cfg> cfgList = group.getCfgList() == null ? Lists.newArrayListWithCapacity(items.size()) : group.getCfgList();
                    group.setCfgList(cfgList);
                    if ("McWareAdd".equals(moduleName)) {
                        items.remove("warePublishRulesName");
                        items.remove("allowDataTypes");
                        items.remove("ware_publish_process_flow");
                    }
                    items.forEach((itemKey, itemValue) -> {
                        Cfg cfg = new Cfg();
                        cfgList.add(cfg);
                        String[] moduleConfKeys = moduleConfMap.get(itemKey).split("-");
                        String cfgCode = moduleConfKeys[0];
                        String cfgName = moduleConfKeys[1];
                        if (sceneId != null) {
                            cfgCode += "-" + sceneId;
                            cfgName += "(" + sceneId + ")";
                        }
                        cfg.setCfgCode(cfgCode);
                        cfg.setCfgName(cfgName);
                        if ("true".equalsIgnoreCase(itemValue) || "false".equalsIgnoreCase(itemValue)) {
                            cfg.set__type("BooleanCfg");
                            cfg.setType("BOOLEANCFG");
                            cfg.setFlag(Boolean.parseBoolean(itemValue));
                        } else {
                            cfg.set__type("InputCfg");
                            cfg.setType("INPUTCFG");
                            String replacedValue = itemValue.replace("->", ",");
                            if (itemKey.contains("flow")) {
                                Optional<String> result = Stream.of(replacedValue.split(","))
                                        .filter(StringUtils::isNotBlank)
                                        .map(code -> code + ":" + ProcessStep.getByCode(Integer.valueOf(code)).getName())
                                        .reduce((a, b) -> a + "," + b);
                                cfg.setValue(result.orElse(""));
                            } else {
                                cfg.setValue(replacedValue);
                            }

                            StringBuffer config = new StringBuffer();
                            config.append(topic.getTopicCode())
                                    .append("->")
                                    .append(groupCode)
                                    .append("->")
                                    .append(cfgCode)
                                    .append(":")
                                    .append(replacedValue);
                            configList.add(config.toString());
                        }
                    });
                }
            });
        });
        String jsonData = JSON.toJSONString(bizCfg).replace("_type", "@type");
        StringBuilder sb = new StringBuilder()
                .append("<BizCfgXml>")
                .append("<configId>0</configId>")
                .append("<compress>false</compress>")
                .append("<json>")
                .append(jsonData)
                .append("</json>")
                .append("</BizCfgXml>");
        System.out.println(sb.toString());
//        configList.forEach(System.out::println);
    }
}
