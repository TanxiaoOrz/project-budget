package org.eoa.projectbudget.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.exception.AuthoritySolveException;
import org.eoa.projectbudget.utils.authority.AuthoritySolve;

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

    public static final String[] types = {"All","Create","Character","Matrix","Proposed"};

    public static boolean checkAuthority(HumanDto user, HumanDto creator, String authorityString) throws AuthoritySolveException, ClassNotFoundException {
        AuthorityConstraint authorityConstraint;
        try {
            authorityConstraint = new ObjectMapper().readValue(authorityString, AuthorityConstraint.class);
        } catch (JsonProcessingException e) {
            throw new AuthoritySolveException(authorityString, "无法解析");
        }
        int index = authorityConstraint.index();

        switch (index) {
            case -1 -> throw new AuthoritySolveException(authorityString, "类型设置错误");
            case 0 -> {
                boolean returns = false;
                for (int i = 1; i < types.length; i++) {
                    if (inType(user,creator,authorityString,authorityConstraint,index))
                        return true;
                }
                return returns;
            }
            default -> {
                return inType(user, creator, authorityString, authorityConstraint, index);
            }
        }
    }

    private static boolean inType(HumanDto user, HumanDto creator, String authorityString, AuthorityConstraint authorityConstraint, int index) throws ClassNotFoundException, AuthoritySolveException {
        String constraint = authorityConstraint.body.get(types[index]);
        AuthoritySolve solve;
        try {
            solve = (AuthoritySolve) new ObjectMapper().readValue(constraint, Class.forName("org.eoa.projectbudget.utils.AuthorityUtils$" + types[index]));
        } catch (JsonProcessingException e) {
            throw new AuthoritySolveException(authorityString, "无法解析");
        }
        if (solve == null) {
            return false;
        }
        return solve.solve(user, creator);
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


}

