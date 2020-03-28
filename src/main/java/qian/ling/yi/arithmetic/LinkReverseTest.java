package qian.ling.yi.arithmetic;

import org.junit.Test;
import qian.ling.yi.collection.ListNode;

public class LinkReverseTest {


    public ListNode ReverseList(ListNode head) {
        ListNode next = null;
        ListNode pre = null;
        while (head != null) {
            //保存要反转到头来的那个节点
            next = head.next;
            //要反转的那个节点指向已经反转的上⼀个节点
            head.next = pre;
            //上⼀个已经反转到头部的节点
            pre = head;
            //⼀直向链表尾⾛
            head = next;
        }
        return pre;
    }

    @Test
    public void testReverse() {
        ListNode head = new ListNode(1);
        ListNode head1 = new ListNode(2);
        ListNode head2 = new ListNode(3);
        ListNode head3 = new ListNode(4);
        ListNode head4 = new ListNode(5);
        head.setNext(head1);
        head1.setNext(head2);
        head2.setNext(head3);
        head3.setNext(head4);
        ListNode tmp = head;
        while (null !=tmp) {

            System.out.println(tmp.getV());
            tmp = tmp.getNext();
        }

        System.out.println("-----");
        ListNode listNode = ReverseList(head);
        while (null!=listNode) {

            System.out.println(listNode.getV());
            listNode = listNode.getNext();
        }
    }

}
