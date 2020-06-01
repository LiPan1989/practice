package com.lee.test.jdk8.optional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author LEE_PAN
 * @description
 * @date 2020/2/13 0:14
 */
public class OptionalTest {
    public static void main(String[] args) {
        User user1 = new User("null", 1);
        User user2 = new User("bbb", 2);
        User user3 = null;
        Optional.ofNullable(user1.getUser()).ifPresent(user2::setUser);
        String s = Optional.ofNullable(user3).map(User::getUser).get();
        System.out.println("result:" + s);
    }

    @Test
    public void test1() {
        User user3 = null;
        Optional.ofNullable(user3).flatMap(user -> Optional.ofNullable(user.getAge())).ifPresent(integer -> System.out.println("------------------"));
    }
}

@Getter
@Setter
@AllArgsConstructor
@ToString
class User{
    String user;
    int age;
}
