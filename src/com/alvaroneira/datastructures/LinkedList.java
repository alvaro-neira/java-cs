package com.alvaroneira.datastructures;

import java.io.*;
import java.util.*;

class LinkedList {
    Node first;
    Node last;

    public LinkedList() {
        this.first = null;
        this.last = null;
    }

    class Node {
        int data;
        Node next;

        public Node() {
            this.next = null;
        }

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public boolean isEmpty() {
        return this.first == null && this.last == null;
    }

    public void insert(int data) {
        if (this.first == null) {
            this.first = new Node(data);
            this.last = this.first;
            return;
        }
        this.last.next = new Node(data);
        this.last = this.last.next;
    }

    public void print() {
        Node aux = this.first;
        String str = "";
        while (aux != null) {
            str += aux.data + "->";
            aux = aux.next;
        }
        System.out.println("" + str);
    }


//         LinkedList.prototype.removeFirst = function(){
//             var retVal = this.first;
//             if(this.first.next){
//                 this.first = this.first.next
//             }else{
//                 this.first = null;
//                 this.last = null;
//             }
//             return retVal;
//         }

    public Node removeLast() {
        if (this.isEmpty()) {
            System.err.println("ERROR: empty linked list");
            return null;
        }
        Node retVal = this.last;
        Node aux = this.first;
        if (aux.next == null) {
            this.first = null;
            this.last = null;
        } else {
            while (aux.next.next != null) {
                aux = aux.next;
            }
            aux.next = null;
            this.last = aux;
        }
        return retVal;
    }


    public Node swap(Node node) {
        if (node == null || node.next == null) {
            return node;
        }
        Node tmp = node.next;
        node.next = swap(tmp.next);
        tmp.next = node;
        return tmp;
    }

    public static Node reverse(Node node) {
        Node a = node;
        Node b = a.next;
        Node c = b != null ? b.next : null;
        node.next = null;
        while (b != null) {
            b.next = a;
            a = b;
            b = c;
            c = c != null ? c.next : null;
        }
        return b != null ? b : a;
    }

    private static Node getMiddle(Node node) {
        if (node == null) {
            return node;
        }
        Node slow = node;
        Node fast = node;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * Given two sorted linked lists of integers write an algorithm to merge the two linked lists
     * such that the resulting linked list is in sorted order. You are expected to define the data
     * structure for linked list as well. Analyze the time and space complexity of the merge algorithm.
     */
    public Node merge(Node node1, Node node2) {
        Node dummyHead = new Node();
        Node curr = dummyHead;
        while (node1 != null && node2 != null) {
            if (node1.data < node2.data) {
                curr.next = node1;
                node1 = node1.next;
            } else {
                curr.next = node2;
                node2 = node2.next;
            }
            curr = curr.next;
        }
        if (node1 == null) {
            curr.next = node2;
        } else {
            curr.next = node1;
        }
        return dummyHead.next;
    }

    /**
     * Floyd’s Cycle-Finding Algorithm: This is the fastest method and has been described below:
     * Traverse linked list using two pointers.
     * Move one pointer(slow_p) by one and another pointer(fast_p) by two.
     * If these pointers meet at the same node then there is a loop. If pointers do not meet then linked list doesn’t have a loop
     */
    public static int detectLoop(Node head) {
        Node slow_p = head, fast_p = head;
        while (slow_p != null && fast_p != null && fast_p.next != null) {
            slow_p = slow_p.next;
            fast_p = fast_p.next.next;
            if (slow_p == fast_p) {
                System.out.println("Found loop");
                return 1;
            }
        }
        return 0;
    }

    public Node mergeSort(Node node) {
        if (node == null || node.next == null) {
            return node;
        }
        Node middle = getMiddle(node);
        Node half = middle.next;
        middle.next = null;
        return merge(mergeSort(node), mergeSort(half));
    }
}

class Solution {
    public static void main(String[] args) {
        LinkedList list1 = new LinkedList();
        list1.insert(2);
        list1.insert(4);
        list1.insert(4);
        list1.insert(6);
        list1.insert(666);
        list1.print();

        LinkedList list2 = new LinkedList();
        list2.insert(3);
        list2.insert(5);
        list2.insert(7);
        list2.insert(7);
        list2.insert(6661);
        list2.print();

        LinkedList res = new LinkedList();
        // res.first=LinkedList.merge(list1.first,list2.first);
        res.print();
        System.out.println("OK");
    }
}
