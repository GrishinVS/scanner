package com.grishinvs.scanner;

import java.util.concurrent.TimeUnit;

public class Runner {

    public static class A {

        synchronized void synchronizedMethodA() {
            try {
                System.out.println("Выполняется метод synchronizedMethodA");
                Thread.sleep(TimeUnit.SECONDS.toMillis(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized void synchronizedMethodB() {
            try {
                System.out.println("Выполняется метод synchronizedMethodB");
                Thread.sleep(TimeUnit.SECONDS.toMillis(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static class ThreadA extends Thread {

        private A targetClass;

        public ThreadA(String name, A a) {
            super(name);
            this.targetClass = a;
        }

        @Override
        public void run() {
            System.out.println("Поток " + this.getName() + " вызвал метод synchronizedMethodA");
            targetClass.synchronizedMethodA();
            System.out.println("Поток " + this.getName() + " завершился");
        }

    }

    public static class ThreadB extends Thread {

        private A targetClass;

        public ThreadB(String name, A a) {
            super(name);
            this.targetClass = a;
        }

        @Override
        public void run() {
            System.out.println("Поток " + this.getName() + " вызвал метод synchronizedMethodB");
            targetClass.synchronizedMethodB();
            System.out.println("Поток " + this.getName() + " завершился");
        }

    }

    public static void main(String[] args) {

         A a = new A();
        new ThreadA("первый", a).start();
        new ThreadB("второй", a).start();

    }

}
