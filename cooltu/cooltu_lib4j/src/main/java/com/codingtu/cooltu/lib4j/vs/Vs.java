package com.codingtu.cooltu.lib4j.vs;

import java.util.List;

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

    public interface Convert<S, T> {
        T convert(int index, S s);
    }

    public interface ConvertList<S, T> {
        void convert(List<T> list, int index, S s);
    }

    /**************************************************
     *
     **************************************************/


}
