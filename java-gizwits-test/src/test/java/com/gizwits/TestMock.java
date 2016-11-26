package com.gizwits;

import com.gizwits.test.UserTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


/**
 * Created by feel on 16/4/21.
 */
public class TestMock {

    @Test
    public void test1() {
        UserTest mock = mock(UserTest.class);


        when(mock.sayHello("hello")).thenReturn("hello");


        assertEquals("hello", mock.sayHello("hello"));

        verify(mock).sayHello("hello");


    }
}
