package org.eoa.projectbudget.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eoa.projectbudget.vo.constraint.AuthorityConstraint;
import org.eoa.projectbudget.vo.constraint.CharacterConstraint;
import org.eoa.projectbudget.vo.constraint.Constraint;
import org.eoa.projectbudget.vo.constraint.MatrixConstraint;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

/**
 * @Author: 张骏山
 * @Date: 2023/11/1 15:53
 * @PackageName: org.eoa.projectbudget.utils
 * @ClassName: AuthorityUtilsTest
 * @Description: 权限限制测试方法
 * @Version: 1.0
 **/

@SpringBootTest
class AuthorityUtilsTest {

    Constraint constraint;
    CharacterConstraint characterConstraint;
    AuthorityConstraint authorityConstraint;
    MatrixConstraint matrixConstraint;

    @Test
    void create() {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<CharacterConstraint.Group> characters = new ArrayList<>();
        characterConstraint = new CharacterConstraint()
                .setCharacters(characters);
        characters.add(new CharacterConstraint.Group().setCharacterId(1L).setGrade(0));


    }

    @Test
    void checkAuthority() {
    }

    @Test
    void checkTable() {
    }

    @Test
    void getConstraint() {
    }
}