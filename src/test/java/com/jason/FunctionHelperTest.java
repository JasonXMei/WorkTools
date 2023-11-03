package com.jason;

import com.jason.util.FunctionHelper;
import org.junit.Test;

public class FunctionHelperTest {

    @Test
    public void test() {
        System.out.println(FunctionHelper.implicit(() -> "hello"));
    }
}
