package com.lee.test.effective._26;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Created by LIPAN on 2017/11/9.
 */
public class Stack<E> {
    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public Stack() {
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        E result = elements[--size];
        elements[size]  = null;
        return result;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        for (String arg : args) {
            stack.push(arg);
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop().toUpperCase());
        }
    }
}
