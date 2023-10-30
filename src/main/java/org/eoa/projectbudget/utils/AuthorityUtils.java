package org.eoa.projectbudget.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.exception.AuthoritySolveException;
import org.eoa.projectbudget.exception.DataException;
import org.eoa.projectbudget.exception.EoaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author 张骏山
 * @Date 2023/10/27 16:50
 * @PackageName: org.eoa.projectbudget.utils
 * @ClassName: AuthorityUtils
 * @Description: 权限处理工具
 * @Version 1.0
 */


public class AuthorityUtils {

    public static final String[] types = {"All","Create","Character","Matrix","Proposed","Authority"};

    public static boolean checkAuthority(HumanDto userId, HumanDto creator, String authorityString) throws AuthoritySolveException, ClassNotFoundException {
        AuthorityConstraint authorityConstraint;
        try {
            authorityConstraint = new ObjectMapper().readValue(authorityString, AuthorityConstraint.class);
        } catch (JsonProcessingException e) {
            throw new AuthoritySolveException(authorityString, "无法解析");
        }
        int index = authorityConstraint.index();

        switch (index) {
            case -1:
                throw new AuthoritySolveException(authorityString, "类型设置错误");
            case 0:
                boolean returns = false;

                return returns;
            default:
                String constraint = authorityConstraint.body.get(types[index]);
                AuthoritySolve solve;
                try {
                    solve = (AuthoritySolve) new ObjectMapper().readValue(constraint, Class.forName("org.eoa.projectbudget.utils.AuthorityUtils$" + types[index]));
                } catch (JsonProcessingException e) {
                    throw new AuthoritySolveException(authorityString, "无法解析");
                }
                return solve.solve(userId,creator);
        }
    }





    public static class AuthorityConstraint {
        String type;    //解析类型选择
        Map<String,String> body;

        int index() {
            for (int i = 0; i < types.length; i++) {
                if (types[i].equals(type))
                    return i;
            }
            return -1;
        }
    }

    @Data
    static class Creator implements AuthoritySolve{
        Boolean self;
        Boolean leader;
        Boolean leaderRecursion;
        Boolean depart;
        Boolean section;
        Boolean sectionRecursive;

        @Override
        public boolean solve(HumanDto user, HumanDto creator) {
            Long userId = creator.getDataId();
            if (self&&user.getDataId().equals(userId))
                return true;
            if (leader&& creator.getDirectorLeader().equals(user.getDataId()))
                return true;
            if (leaderRecursion&& creator.getLeaderRecursion().contains(userId))
                return true;
            if (depart&& creator.getDepart().equals(user.getDepart()))
                return true;
            if (section&& creator.getSection().equals(user.getSection()))
                return true;
            return sectionRecursive && creator.getSectionRecursion().contains(user.getSection());
        }
    }

    @Data
    static class Character implements AuthoritySolve{
        List<Group> characters;

        @Override
        public boolean solve(HumanDto user, HumanDto creator) {
            for (Group group:
                    characters) {
                Integer userGrade = user.getCharacters().get(group.characterId);
                if (userGrade == null) {
                    continue;
                }
                if (userGrade<=group.grade)
                    switch (userGrade) {
                        case 0:
                            return true;
                        case 1:
                            if (creator.getSectionRecursion().contains(user.getSection()))
                                return true;
                        case 2:
                            if (creator.getDepart().equals(user.getDepart()))
                                return true;
                    }
            }
            return false;
        }

        @Data
        static class Group {
            Long characterId;
            Integer grade;
        }
    }


    static class Proposed {

    }

    static class Authority extends ArrayList<Integer> {

    }

}

/**
 * 权限声明元素需要完成其判别式
 */
interface AuthoritySolve {
    boolean solve(HumanDto user,HumanDto creator);
}
