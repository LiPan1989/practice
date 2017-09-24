package com.lee.test.jdk8.lambda.functional;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by LIPAN on 2017/9/23.
 */
public class PredicateConsumerDemo {
    public static Student updateStudentFee(Student student, Predicate<Student> predicate, Consumer<Student> consumer) {
        if (predicate.test(student)) {
            consumer.accept(student);
        }
        return student;
    }

    public static void main(String[] args) {
        Student student1 = new Student("LI", "PAN", 9.5);
        student1 = updateStudentFee(student1, student -> student.grade > 8.5, student -> student.feeDiscount = 30.0);
        student1.printFee();

        Student student2 = new Student("BAI", "YUNJIA", 8.0);
        student2 = updateStudentFee(student2, student -> student.grade >= 8.0, student -> student.feeDiscount = 20.0);
        student2.printFee();
    }
}
