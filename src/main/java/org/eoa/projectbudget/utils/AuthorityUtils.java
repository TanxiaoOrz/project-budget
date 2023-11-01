package org.eoa.projectbudget.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.entity.Column;
import org.eoa.projectbudget.dto.Form;
import org.eoa.projectbudget.entity.Table;
import org.eoa.projectbudget.exception.AuthoritySolveException;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.utils.authority.AuthoritySolve;
import org.eoa.projectbudget.utils.authority.FormSolve;
import org.eoa.projectbudget.vo.constraint.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Author 张骏山
 * @Date 2023/10/27 16:50
 * @PackageName: org.eoa.projectbudget.utils
 * @ClassName: AuthorityUtils
 * @Description: 权限处理工具
 * @Version 1.0
 */


public class AuthorityUtils {

    public static final String[] types = {
            "allConstraint",
            "createConstraint",
            "characterConstraint",
            "matrixConstraint",
            "proposedConstraint",
            "authorityConstraint"
    };

    public static final Class<?>[] clazz = {
            AllConstraint.class,
            CreatorConstraint.class,
            CharacterConstraint.class,
            MatrixConstraint.class,
            ProposedConstraint.class,
            AuthorityConstraint.class
    };
    public static final List<String> typeList = Arrays.asList(types);


    public static boolean checkAuthority(HumanDto user, HumanDto creator, Constraint authorityConstraint) throws AuthoritySolveException {
        String bodyType = authorityConstraint.getBodyType();
        if (bodyType == null || bodyType.equals("")) {
            return false;
        }
        for (String type:
                bodyType.split(",")) {
            if (!typeList.contains(type))
                throw new AuthoritySolveException(authorityConstraint.getBodyType(), "类型设置错误");
            else if (inTypeAuthority(user, creator, authorityConstraint, typeList.indexOf(type)))
                return true;
        }
        return false;
    }

    public static boolean checkTable(HumanDto user, Form<Column, Table> form, Constraint authorityConstraint) throws EoaException {
        String tableType = authorityConstraint.getBodyType();
        if (tableType == null || tableType.equals("")) {
            return false;
        }
        for (String type:
             tableType.split(",")) {
            if (!typeList.contains(type))
                throw new AuthoritySolveException(authorityConstraint.getBodyType(), "类型设置错误");
            else if (inTypeTable(user, form, authorityConstraint, typeList.indexOf(type)))
                return true;
        }
        return false;
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

    private static boolean inTypeAuthority(HumanDto user, HumanDto creator, Constraint authorityConstraint, int index) throws AuthoritySolveException {
        String constraint = authorityConstraint.getBody().get(types[index]);
        AuthoritySolve solve;
        try {
            solve = (AuthoritySolve) new ObjectMapper().readValue(constraint,clazz[index]);
        } catch (JsonProcessingException e) {
            throw new AuthoritySolveException(constraint, "无法解析");
        }
        if (solve == null) {
            return false;
        }
        return solve.solve(user, creator);
    }

    private static boolean inTypeTable(HumanDto user, Form<Column, Table> form, Constraint authorityConstraint, int index) throws EoaException {
        String constraint = authorityConstraint.getBody().get(types[index]);
        FormSolve solve;
        try {
            solve = (FormSolve) new ObjectMapper().readValue(constraint, clazz[index]);
        } catch (JsonProcessingException e) {
            throw new AuthoritySolveException(constraint, "无法解析");
        }
        if (solve == null) {
            return false;
        }
        return solve.solve(user, form);
    }


}

