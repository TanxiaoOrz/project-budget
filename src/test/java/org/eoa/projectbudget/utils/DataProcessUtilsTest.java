package org.eoa.projectbudget.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: 张骏山
 * @Date: 2023/11/21 21:31
 * @PackageName: IntelliJ IDEA
 * @ClassName: DataProcessUtilsTest
 * @Description: TODO
 * @Version: 1.0
 */
class DataProcessUtilsTest {

    @Test
    void splitStringArray() {
        System.out.println("DataProcessUtils.splitStringArray(\"\") = " + Arrays.toString(DataProcessUtils.splitStringArray("")));
    }
}