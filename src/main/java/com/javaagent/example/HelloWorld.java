package com.javaagent.example;

import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.util.List;

public class HelloWorld {


    //Agent Main


    //Pre main
    public static void premain(String argument, Instrumentation inst) {

        /*inst.addTransformer(new ClassFileTransformer() {
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

                System.out.println(classBeingRedefined);
                System.out.println(new String(classfileBuffer));

                return classfileBuffer;
            }
        });*/

        final AgentBuilder.Identified.Extendable transform = new AgentBuilder.Default().type(ElementMatchers.<TypeDescription>nameStartsWith("JDBCUtil"))
                .transform(new AgentBuilder.Transformer() {
                    public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule) {
                        System.out.println("Transforming " + typeDescription);
                        return builder.
                                visit(Advice.to(MyAdvice.class,MyExitAdvice.class).on(ElementMatchers.<MethodDescription>any()));

                    }
                });

        transform.installOn(inst);


    }


}
