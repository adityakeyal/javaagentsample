package com.javaagent.example;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bytecode.assign.Assigner;

public class MyAdvice {

    @Advice.OnMethodEnter
    public static void enter(@Advice.Origin Class klass, @Advice.Origin("#m") String m, @Advice.AllArguments Object[] arguments){

        System.out.println(klass.getProtectionDomain().getCodeSource().getLocation().getFile());
        System.out.println("Calling  " + m + " from " + klass);
        for (Object argument : arguments) {
            System.out.println(argument);
        }


        System.out.println("--------------------------------");


    }


//    @Advice.OnMethodExit
//    private static void exit(@Advice.Return(readOnly = true) String value) {
//        System.out.println(value);
//    }


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
