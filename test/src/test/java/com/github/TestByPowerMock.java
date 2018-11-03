package com.github;

import com.github.test.UserTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.*;


/**
 * Created by feel on 16/4/21.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({UserTest.class})
public class TestByPowerMock {


    @Test
    public void test1() {
        mockStatic(UserTest.class);

        when(UserTest.add(1, 5)).thenReturn(6);

        int actual = UserTest.add(1, 5);


        assertEquals(6, actual);

        verifyStatic();
    }


}
