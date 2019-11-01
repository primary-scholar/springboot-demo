package com.mimu.simple.java.leetcode;

/**
 * author: mimu
 * date: 2019/9/30
 */

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Example:
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
public class LC2Test {

    @Test
    public void info() {
        ListNode<Integer> first = new ListNode<>();
        first.addLast(2);
        first.addLast(4);
        first.addLast(3);
        ListNode<Integer> second = new ListNode<>();
        second.addLast(5);
        second.addLast(6);
        second.addLast(9);
        second.addLast(3);

        ListNode<Integer> result = caculate(first, second);
        ListNode<Integer>.ListNodeIterator iterator = result.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

    public ListNode<Integer> caculate(ListNode<Integer> first, ListNode<Integer> second) {
        ListNode<Integer> result = new ListNode<>();
        ListNode<Integer>.ListNodeIterator firstIterator = first.iterator();
        ListNode<Integer>.ListNodeIterator secondIterator = second.iterator();
        int adding = 0;
        while (firstIterator.hasNext() && secondIterator.hasNext()) {
            int firstValue = firstIterator.next();
            int secondValue = secondIterator.next();
            int mod = (firstValue + secondValue) % 10;
            result.addBefore(mod + adding);
            adding = (firstValue + secondValue) / 10;
        }
        while (firstIterator.hasNext()) {
            int firstValue = firstIterator.next();
            result.addBefore(firstValue + adding);
        }
        while (secondIterator.hasNext()) {
            int secondValue = secondIterator.next();
            result.addBefore(secondValue + adding);
        }
        return result;
    }

    private static class ListNode<E> {
        transient Node<E> first;
        transient Node<E> last;
        transient int size;

        public ListNode() {
        }

        public boolean addLast(E data) {
            linkAfter(data);
            return true;
        }

        public boolean addBefore(E data) {
            linkBefore(data);
            return true;
        }

        void linkAfter(E data) {
            final Node<E> l = last;
            final Node<E> newNode = new Node<>(data, l, null);
            last = newNode;
            if (l == null)
                first = newNode;
            else
                l.next = newNode;
            size++;
        }

        void linkBefore(E data) {
            final Node<E> pred = first;
            final Node<E> newNode = new Node<>(data, null, pred);
            first = newNode;
            if (pred == null)
                last = newNode;
            else
                pred.pre = newNode;
            size++;
        }

        public ListNodeIterator iterator() {
            return new ListNodeIterator(0);
        }

        private class ListNodeIterator implements IListNodeIterator<E> {
            private Node<E> lastReturned;
            private Node<E> next;
            private int nextIndex;

            ListNodeIterator(int index) {
                next = (index == size) ? null : node(index);
                nextIndex = index;
            }

            public boolean hasNext() {
                return nextIndex < size;
            }

            public E next() {
                if (!hasNext())
                    throw new NoSuchElementException();

                lastReturned = next;
                next = next.next;
                nextIndex++;
                return lastReturned.data;
            }
        }

        private Node<E> node(int index) {
            if (index < (size >> 1)) {
                Node<E> x = first;
                for (int i = 0; i < index; i++)
                    x = x.next;
                return x;
            } else {
                Node<E> x = last;
                for (int i = size - 1; i > index; i--)
                    x = x.pre;
                return x;
            }
        }

        private interface IListNodeIterator<E> extends Iterator<E> {
            boolean hasNext();

            E next();
        }

        private class Node<E> {
            private E data;
            private Node<E> pre;
            private Node<E> next;

            Node(E data, Node<E> pre, Node<E> next) {
                this.data = data;
                this.pre = pre;
                this.next = next;
            }
        }
    }


}
