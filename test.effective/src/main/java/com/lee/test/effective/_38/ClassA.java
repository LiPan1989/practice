package com.lee.test.effective._38;

import java.util.List;
import java.util.Map;

public class ClassA {
    private String str;
    private int i;
    private List<String> strList;
    private List<ClassB> classBList;
    private Map<String ,ClassB> map;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public List<String> getStrList() {
        return strList;
    }

    public void setStrList(List<String> strList) {
        this.strList = strList;
    }

    public List<ClassB> getClassBList() {
        return classBList;
    }

    public void setClassBList(List<ClassB> classBList) {
        this.classBList = classBList;
    }

    public Map<String, ClassB> getMap() {
        return map;
    }

    public void setMap(Map<String, ClassB> map) {
        this.map = map;
    }
}



class ClassB {
    private String strb;
    private Integer intb;

    public String getStrb() {
        return strb;
    }

    public void setStrb(String strb) {
        this.strb = strb;
    }
}

class ClassC {
    private String str;
    private int i;
    private List<String> strList;
    private List<ClassB> classBList;
    private Map<String ,ClassB> map;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public List<String> getStrList() {
        return strList;
    }

    public void setStrList(List<String> strList) {
        this.strList = strList;
    }

    public List<ClassB> getClassBList() {
        return classBList;
    }

    public void setClassBList(List<ClassB> classBList) {
        this.classBList = classBList;
    }

    public Map<String, ClassB> getMap() {
        return map;
    }

    public void setMap(Map<String, ClassB> map) {
        this.map = map;
    }
}