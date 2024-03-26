package com.codingtu.cooltu.bean;

import com.codingtu.cooltu.lib4j.data.symbol.Symbol;

import java.util.Objects;

public class User implements Symbol {

    public String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String obtainSymbol() {
        return name;
    }
}
