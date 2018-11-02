package com.javaagent.example;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bytecode.assign.Assigner;

public class MyExitAdvice {


    @Advice.OnMethodExit()
    private static void exit(@Advice.Return(typing = Assigner.Typing.DYNAMIC) Object value) {
        System.out.println("Exit : "+value);
    }


  /*  @Advice.OnMethodExit
    public static Object processCachedValue(
            @Advice.Return(readOnly = false, typing = Assigner.Typing.DYNAMIC) Object returned,
            @Advice.Enter Object enter) {
        System.out.println("***************************************");
        System.out.println(enter);
        System.out.println(returned);
        System.out.println("##################################");
        return returned;
    }*/



}
