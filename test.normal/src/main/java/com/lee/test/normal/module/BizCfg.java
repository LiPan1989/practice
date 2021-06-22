package com.lee.test.normal.module;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author cdlipan5
 * @create 2021-03-16 下午3:53
 **/
@Getter
@Setter
public class BizCfg {
    private String __type = "BizCfg";
    private String bizCfgCode = "b2b-ware-biz-svr";
    private List<String> bizDicIdLst = Lists.newArrayList();
    private boolean compress = false;
    private List<Topic> topicList;
}
