package com.codingtu.cooltu.lib4a.formbind.check;

public interface Check<Bean, BEANFIELD> {

    public boolean check(Bean bean, BEANFIELD beanfield);
}
