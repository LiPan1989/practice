package com.lee.test.normal.module;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author cdlipan5
 * @create 2021-03-16 下午3:56
 **/
@Getter
@Setter
public class Group {

    private String groupCode;
    private String groupName;
    private List<Cfg> cfgList;
}
