package test;

import paging.Paging;

import java.util.LinkedList;

/**
 * EqualsTest
 *
 * @author wz
 * @date 15/12/1.
 */
public class EqualsTest {

    public static void main(String[] args) {

        LinkedList<Paging> pages = new LinkedList<>();
//        pages.add(new Paging(1,0));
        System.out.println(pages.contains(new Integer(1)));



    }

}
