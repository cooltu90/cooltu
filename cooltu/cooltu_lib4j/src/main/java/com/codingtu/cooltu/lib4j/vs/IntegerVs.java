package com.codingtu.cooltu.lib4j.vs;

public class IntegerVs extends CoreVs<Integer, IntegerVs> {
    @Override
    protected String valueSymbol(Integer integer) {
        return String.valueOf(integer);
    }

    /**************************************************
     * get
     **************************************************/
    @Deprecated
    @Override
    public Integer getFirstByValueSymbol(String valueId) {
        return super.getFirstByValueSymbol(valueId);
    }

    @Deprecated
    @Override
    public Integer getFirst(Integer integer) {
        return super.getFirst(integer);
    }

    @Deprecated
    @Override
    public Integer getFirst(Vs.IsThisOne<Integer> isThisOne) {
        return super.getFirst(isThisOne);
    }

    @Deprecated
    @Override
    public IntegerVs getAll(Integer integer) {
        return super.getAll(integer);
    }

    @Deprecated
    @Override
    public IntegerVs getAllByValueSymbol(String valueSymbol) {
        return super.getAllByValueSymbol(valueSymbol);
    }

    @Deprecated
    @Override
    public IntegerVs getAll(Vs.IsThisOne<Integer> isThisOne) {
        return super.getAll(isThisOne);
    }

    /**************************************************
     * has
     **************************************************/
    @Deprecated
    @Override
    public boolean hasByValueSymbol(String valueId) {
        return super.hasByValueSymbol(valueId);
    }

    @Override
    public boolean has(Integer integer) {
        int count = count();
        for (int i = 0; i < count; i++) {
            if (getByIndex(i) == integer) {
                return true;
            }
        }
        return false;
    }


    /**************************************************
     * index
     **************************************************/

    @Deprecated
    @Override
    public int firstIndexByValueSymbol(String valueSymbol) {
        return super.firstIndexByValueSymbol(valueSymbol);
    }

    @Override
    public int firstIndex(Integer integer) {
        int count = count();
        for (int i = 0; i < count; i++) {
            if (getByIndex(i) == integer) {
                return i;
            }
        }
        return -1;
    }

    @Deprecated
    @Override
    public IntegerVs allIndexByValueSymbol(String valueSymbol) {
        return super.allIndexByValueSymbol(valueSymbol);
    }

    @Override
    public IntegerVs allIndex(Integer integer) {
        IntegerVs integerVs = new IntegerVs();
        int count = count();
        for (int i = 0; i < count; i++) {
            Integer integer1 = getByIndex(i);
            if (integer1 == integer) {
                integerVs.add(i);
            }
        }
        return integerVs;
    }
}
