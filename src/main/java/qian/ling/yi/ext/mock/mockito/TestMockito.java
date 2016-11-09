package qian.ling.yi.ext.mock.mockito;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by liuguobin on 2016/9/26.
 */
public class TestMockito {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Test
    public void simpleTest(){

        //创建mock对象，参数可以是类，也可以是接口
        List<String> list = mock(LinkedList.class);

        //不能当真实的对象，那么操作？
        list.add("1");
        list.add("1");
        list.add("1");
        logger.info("{}", list.size());
        list.forEach(string -> logger.info(string));

        //设置方法的预期返回值
        when(list.get(0))
                .thenReturn("1","2","3");
//                .thenReturn("helloworld");

        String result = list.get(0);
        System.out.println(result);

        //验证方法调用(是否调用了get(0))
        verify(list).get(0);

        //junit测试
        Assert.assertEquals("helloworld", result);
    }


    @Test
    public void verifyInvocate(){

        List<String> mockedList = mock(List.class);
        //using mock
        mockedList.add("once");
        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        /**
         * 基本的验证方法
         * verify方法验证mock对象是否有没有调用mockedList.add("once")方法
         * 不关心其是否有返回值，如果没有调用测试失败。
         */
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");//默认调用一次,times(1)可以省略


        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");

        //never()等同于time(0),一次也没有调用
        verify(mockedList, times(0)).add("never happened");

        //atLeastOnece/atLeast()/atMost()
        verify(mockedList, atLeastOnce()).add("three times");
        verify(mockedList, atLeast(2)).add("twice");
        verify(mockedList, atMost(3)).add("three times");

    }
}
