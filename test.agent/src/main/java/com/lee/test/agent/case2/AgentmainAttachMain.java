package com.lee.test.agent.case2;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.util.List;

/**
 * @author cdlipan5
 * @create 2020-12-11 下午1:58
 **/
public class AgentmainAttachMain {
    public static void main(String[] args) throws Exception {
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (VirtualMachineDescriptor descriptor : list) {
            if (descriptor.displayName().endsWith("AgentTargetSample")) {
                VirtualMachine virtualMachine = VirtualMachine.attach(descriptor.id());
                virtualMachine.loadAgent("/Users/cdlipan5/IdeaProjects/practice/test.agent/target/test.agent-0.0.1-SNAPSHOT.jar", "arg1");
                virtualMachine.detach();
            }
        }
    }
}
