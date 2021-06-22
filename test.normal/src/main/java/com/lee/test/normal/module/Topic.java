package com.lee.test.normal.module;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author cdlipan5
 * @create 2021-03-16 下午3:55
 **/
@Getter
@Setter
public class Topic {
    private String topicCode;
    private String topicName;
    private List<Group> groupList;
}
