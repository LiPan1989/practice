package com.lee.test.effective._38;

import java.util.List;
import java.util.Map;

/**
 *
 * @author LEE_PAN
 * @version $Id: ClassAConverter.java, v 0.1 2019-06-11 01:40:55 LEE_PAN Exp $$
 */
class ClassAConverter {

    /**
     * Convert ClassC to ClassA
     * @param classC
     * @return
     */
    public static ClassA convertToClassA(ClassC classC) {
        if (classC == null) {
            return null;
        }
        ClassA classA = new ClassA();

        classA.setStr(classC.getStr());
        classA.setI(classC.getI());
        classA.setStrList(classC.getStrList());
        classA.setClassBList(classC.getClassBList());
        classA.setMap(classC.getMap());

        return classA;
    }

    /**
     * Convert ClassA to ClassC
     * @param classA
     * @return
     */
    public static ClassC convertToClassC(ClassA classA) {
        if (classA == null) {
            return null;
        }
        ClassC classC = new ClassC();

        classC.setStr(classA.getStr());
        classC.setI(classA.getI());
        classC.setStrList(classA.getStrList());
        classC.setClassBList(classA.getClassBList());
        classC.setMap(classA.getMap());

        return classC;
    }
}
