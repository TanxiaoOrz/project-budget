package org.eoa.projectbudget.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.exception.AuthoritySolveException;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.mapper.HumanMapper;
import org.eoa.projectbudget.mapper.HumanViewMapper;
import org.eoa.projectbudget.service.organization_module.OrganizationService;
import org.eoa.projectbudget.vo.constraint.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

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

    @Autowired
    HumanViewMapper humanMapper;
    @Autowired
    OrganizationService organizationService;

    Constraint constraint;
    CharacterConstraint characterConstraint;
    AuthorityConstraint authorityConstraint;
//    MatrixConstraint matrixConstraint;

    AllConstraint allConstraint;
    CreatorConstraint creatorConstraint;
    ProposedConstraint proposedConstraint;

    String constraintStr = """
            {
                "bodyType":"allConstraint,createConstraint,characterConstraint,proposedConstraint,authorityConstraint",
                "body":{
                    "characterConstraint":"{\\"characters\\":[{\\"characterId\\":2,\\"grade\\":0}]}",
                    "proposedConstraint":"{\\"humans\\":[0,2],\\"departs\\":[1,2],\\"sections\\":[1,2]}",
                    "allConstraint":"{\\"start\\":5,\\"end\\":100}",
                    "authorityConstraint":"{\\"authorities\\":[1]}",
                    "createConstraint":"{\\"self\\":true,\\"leader\\":false,\\"leaderRecursion\\":false,\\"depart\\":false,\\"section\\":false,\\"sectionRecursive\\":false}"
                },
                "tableType":null,
                "table":null
            }
            """;

    @Test
    void create() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<CharacterConstraint.Group> characters = new ArrayList<>();
        characterConstraint = new CharacterConstraint()
                .setCharacters(characters);
        characters.add(new CharacterConstraint.Group().setCharacterId(1L).setGrade(0));

//        matrixConstraint = new MatrixConstraint();

        ArrayList<Long> authorities = new ArrayList<>();
        authorities.add(1L);
        authorityConstraint = new AuthorityConstraint().setAuthorities(authorities);

        allConstraint = new AllConstraint().setStart(0L).setEnd(100L);

        creatorConstraint = new CreatorConstraint().setSelf(true).setDepart(true).setSection(true).setLeaderRecursion(true).setSectionRecursive(true).setLeader(true);

        List<Long> longList = Arrays.asList(0L, 1L, 2L);
        proposedConstraint = new ProposedConstraint().setHumans(longList).setDeparts(longList).setSections(longList);

        String allStr = objectMapper.writeValueAsString(allConstraint);
        System.out.println("allStr = " + allStr);
        String creatorStr =  objectMapper.writeValueAsString(creatorConstraint);
        System.out.println("creatorStr = " + creatorStr);
        String characterStr =  objectMapper.writeValueAsString(characterConstraint);
        System.out.println("characterStr = " + characterStr);
//        String matrixStr =  objectMapper.writeValueAsString(matrixConstraint);
//        System.out.println("matrixStr = " + matrixStr);
        String proposedStr =  objectMapper.writeValueAsString(proposedConstraint);
        System.out.println("proposedStr = " + proposedStr);
        String authorityStr =  objectMapper.writeValueAsString(authorityConstraint);
        System.out.println("authorityStr = " + authorityStr);

        constraint = new Constraint();
        HashMap<String, String> body = new HashMap<>();
        body.put("allConstraint",allStr);
        body.put("createConstraint",creatorStr);
        body.put("characterConstraint",characterStr);
//        body.put("matrixConstraint",matrixStr);
        body.put("proposedConstraint",proposedStr);
        body.put("authorityConstraint",authorityStr);
        constraint.setBodyType("allConstraint,createConstraint,characterConstraint,matrixConstraint,proposedConstraint,authorityConstraint")
                .setBody(body);

        String constraintStr = objectMapper.writeValueAsString(constraint);

        System.out.println("constraintStr = " + constraintStr);
    }

    @Test
    void getConstraint() throws AuthoritySolveException, ParameterException {
        constraint = AuthorityUtils.getConstraint(constraintStr);
//        List<Long> longList = Arrays.asList(0L, 1L, 2L);
//
//        HashMap<Long, Integer> character = new HashMap<>();
//        HashSet<Integer> authorities = new HashSet<>();
//        authorities.add(1);
//        HashSet<Long> leaderRecursion = new HashSet<>(Arrays.asList(1L, 2L, 3L));
//        HashSet<Long> sectionRecursion = new HashSet<>(Arrays.asList(1L, 2L, 3L));

    }

    @Test
    void checkAuthority() throws AuthoritySolveException, ParameterException {
        HumanDto sysadmin = organizationService.getHumanDto(1L, 1L);
        HumanDto tourist = organizationService.getHumanDto(2L,1L);
        System.out.println("AuthorityUtils.checkAuthority(sysadmin,tourist,constraint) = " + AuthorityUtils.checkAuthority(sysadmin, tourist, constraint));
    }

    @Test
    void checkTable() {
    }


}