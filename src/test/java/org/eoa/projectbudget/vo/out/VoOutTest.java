package org.eoa.projectbudget.vo.out;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: 张骏山
 * @Date: 2023/12/20 9:33
 * @PackageName: org.eoa.projectbudget.vo.out
 * @ClassName: VoOutTest
 * @Description: TODO
 * @Version: 1.0
 **/
class VoOutTest {

    @Test
    void toBrowser() {
        Long browserId = null;
        VoOut voOut = new ContentOut();
        voOut.toBrowser(browserId);
        System.out.println("browserId = " + browserId);

    }
}