package com.lee.test.normal.module;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author cdlipan5
 * @create 2021-03-16 上午11:54
 **/
@Getter
@Setter
public class ModuleItem {
    private Integer id;
    private String identity;
    private String domain;
    private Integer version;
    private Map<String, String> items;
}
