package cn.breezefaith.util;

class A{
    public void mA(){
        System.out.println("A.mA");
    }
}
class B extends A{
    public void mB(){
        System.out.println("B.mB");
    }
    @Override
    public void mA(){
        System.out.println("B.mA");
    }
}
public class Test {
    public synchronized static void main(String[] args) {
        A a=new B();
        ((B)a).mB();
    }
}
