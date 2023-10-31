package org.eoa.projectbudget.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.entity.Column;
import org.eoa.projectbudget.entity.Form;
import org.eoa.projectbudget.entity.Table;
import org.eoa.projectbudget.exception.AuthoritySolveException;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.utils.authority.AuthoritySolve;
import org.eoa.projectbudget.utils.authority.FormSolve;
import org.eoa.projectbudget.vo_in.constriant.Constraint;

/**
 * @Author 张骏山
 * @Date 2023/10/27 16:50
 * @PackageName: org.eoa.projectbudget.utils
 * @ClassName: AuthorityUtils
 * @Description: 权限处理工具
 * @Version 1.0
 */


public class AuthorityUtils {

    public static final String[] types = {"AllConstraint","CreateConstraint","CharacterConstraint","MatrixConstraint","ProposedConstraint","AuthorityConstraint"};

    public static boolean checkAuthority(HumanDto user, HumanDto creator, Constraint authorityConstraint) throws AuthoritySolveException, ClassNotFoundException {
        Integer index = authorityConstraint.index();

        switch (index) {
            case null -> throw new AuthoritySolveException(authorityConstraint.getType(), "类型设置错误");
            case -1 -> {
                boolean returns = false;
                for (int i = 1; i < types.length; i++) {
                    if (inTypeAuthority(user, creator, authorityConstraint, i))
                        return true;
                }
                return returns;
            }
            default -> {
                return inTypeAuthority(user, creator, authorityConstraint, index);
            }
        }
    }

    public static boolean checkTable(HumanDto user, Form<Column, Table> form, Constraint authorityConstraint) throws EoaException, ClassNotFoundException {
        Integer index = authorityConstraint.index();

        switch (index) {
            case null -> throw new AuthoritySolveException(authorityConstraint.getType(), "类型设置错误");
            case -1 -> {
                boolean returns = false;
                for (int i = 1; i < types.length; i++) {
                    if (inTypeTable(user, form, authorityConstraint, i))
                        return true;
                }
                return returns;
            }
            default -> {
                return inTypeTable(user, form, authorityConstraint, index);
            }
        }
    }

    public static Constraint getConstraint(String authorityString) throws AuthoritySolveException {
        Constraint authorityConstraint;
        try {
            authorityConstraint = new ObjectMapper().readValue(authorityString, Constraint.class);
        } catch (JsonProcessingException e) {
            throw new AuthoritySolveException(authorityString, "无法解析");
        }
        return authorityConstraint;
    }

    private static boolean inTypeAuthority(HumanDto user, HumanDto creator, Constraint authorityConstraint, int index) throws ClassNotFoundException, AuthoritySolveException {
        String constraint = authorityConstraint.getBody().get(types[index]);
        AuthoritySolve solve;
        try {
            solve = (AuthoritySolve) new ObjectMapper().readValue(constraint, Class.forName("org.eoa.projectbudget.utils.AuthorityUtils$" + types[index]));
        } catch (JsonProcessingException e) {
            throw new AuthoritySolveException(constraint, "无法解析");
        }
        if (solve == null) {
            return false;
        }
        return solve.solve(user, creator);
    }

    private static boolean inTypeTable(HumanDto user, Form<Column, Table> form, Constraint authorityConstraint, int index) throws ClassNotFoundException, EoaException {
        String constraint = authorityConstraint.getBody().get(types[index]);
        FormSolve solve;
        try {
            solve = (FormSolve) new ObjectMapper().readValue(constraint, Class.forName("org.eoa.projectbudget.utils.AuthorityUtils$" + types[index]));
        } catch (JsonProcessingException e) {
            throw new AuthoritySolveException(constraint, "无法解析");
        }
        if (solve == null) {
            return false;
        }
        return solve.solve(user, form);
    }


}

