package com.codingtu.cooltu.lib4a.formbind.parse;

public interface Parse<Bean, View> {

    public View toView(Bean bean);

    public Bean toBean(Object obj);

}
