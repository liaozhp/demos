package com.test;
/**
 * Created by lzp on 2017/9/4.
 */

/**
 * 测试Java引用传递和值传递
 */
public class TestMemory {

    public static void main(String[] args) {
        TestMemory testMemory=new TestMemory();
        int number1=9527;
        int number2=1314;
        System.out.println("main方法中，数据交换前：number1="+number1+" , number2="+number2);
        testMemory.swapData(number1, number2);
        System.out.println("main方法中，数据交换后：number1="+number1+" , number2="+number2);
    }

    private void swapData(int a,int b) {
        System.out.println("swapData方法中，数据交换前：a="+a+" , b="+b);
        int temp=a;
        a=b;
        b=temp;
        System.out.println("swapData方法中，数据交换后：a="+a+" , b="+b);
    }
}
