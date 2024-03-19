package com.codingtu.cooltu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.codingtu.cooltu.bean.User;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import core.path.CheckPath;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckPath checkPath = CheckPath.obtain("xx", "xxx");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Supplier supplier = new Supplier() {
                @Override
                public Object get() {
                    return null;
                }
            };

            Consumer consumer = new Consumer() {
                @Override
                public void accept(Object o) {

                }
            };

            Predicate<User> predicate = new Predicate<User>() {
                @Override
                public boolean test(User user) {
                    return false;
                }
            };
            User user = new User();
            user.name = "lisi";

            User user1 = new User();
            user1.name = "xxx";

            Predicate<User> equal = Predicate.<User>isEqual(user);
            boolean test = equal.test(user1);
        }

    }
}