package com.codingtu.cooltu.lib4j.vs;

import com.codingtu.cooltu.lib4j.data.bean.CoreBean;

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

    public interface NowMax<T> {
        boolean isNowMax(T last, T now);
    }

    public interface IsNow<T> {
        boolean isNow(T last, T now);
    }

    /**************************************************
     *
     **************************************************/
    public static class NearByIndex extends CoreBean {
        public int currentIndex;
        public int nearByIndex;

        public boolean isNextOne() {
            return nearByIndex > currentIndex;
        }
    }


}
