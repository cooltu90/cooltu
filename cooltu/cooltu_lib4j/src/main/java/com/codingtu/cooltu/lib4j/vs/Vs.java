package com.codingtu.cooltu.lib4j.vs;

public class Vs {

    /**************************************************
     *
     **************************************************/
    public interface EachTs<T> {
        boolean each(int position, T t);
    }

    public interface IsThisOne<T> {
        boolean isThisOne(int position, T t);
    }

    /**************************************************
     *
     **************************************************/


}
