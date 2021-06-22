package com.lee.test.normal.module;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * @author cdlipan5
 * @create 2021-03-16 上午11:52
 **/
@Getter
@Setter
public class Module {
    private String name;
    private String alias;
    private String type;
    private List<Map<String, ModuleItem>> items;
}
