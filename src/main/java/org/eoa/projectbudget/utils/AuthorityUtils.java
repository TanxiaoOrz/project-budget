package org.eoa.projectbudget.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eoa.projectbudget.dto.FormOutDto;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.dto.constraint.*;
import org.eoa.projectbudget.exception.AuthoritySolveException;
import org.eoa.projectbudget.exception.EoaException;

import java.util.Arrays;
import java.util.List;

/**
 * @Author 张骏山
 * @Date 2023/10/27 16:50
 * @PackageName: org.eoa.projectbudget.utils
 * @ClassName: AuthorityUtils
 * @Description: 权限处理工具
 * @Version 1.1
 */


public class AuthorityUtils {

    public static final String[] types = {
            "allConstraint",
            "createConstraint",
            "characterConstraint",
            // "matrixConstraint",
            "proposedConstraint",
            "authorityConstraint"
    };

    public static final Class<?>[] clazz = {
            AllConstraint.class,
            CreatorConstraint.class,
            CharacterConstraint.class,
            // MatrixConstraint.class,
            ProposedConstraint.class,
            AuthorityConstraint.class
    };
    public static final List<String> typeList = Arrays.asList(types);


    /**
     * 检验user对象是否在限制结构的creator限制内,如果限制对象传入的是null直接返回false
     * @param user 检测对象
     * @param creator 创建者对象
     * @param authorityConstraint 限制结构体
     * @return 是否在限制内
     * @throws AuthoritySolveException 权限字符串包裹异常
     */
    public static boolean checkAuthority(HumanDto user, HumanDto creator, Constraint authorityConstraint) throws AuthoritySolveException {
        if (authorityConstraint == null) {
            return false;
        }
        String bodyType = authorityConstraint.getBodyType();
        if (bodyType == null || bodyType.equals("")) {
            return false;
        }
        for (String type:
                bodyType.split(",")) {
            if (typeList.contains(type) && inTypeAuthority(user, creator, authorityConstraint, typeList.indexOf(type)))
                return true;
        }
        return false;
    }

    public static boolean checkTable(HumanDto user, FormOutDto formOutDto, Constraint authorityConstraint) throws EoaException {
        if (authorityConstraint == null) {
            return false;
        }
        String tableType = authorityConstraint.getBodyType();
        if (tableType == null || tableType.equals("")) {
            return false;
        }
        for (String type:
             tableType.split(",")) {
            if (!typeList.contains(type))
                throw new AuthoritySolveException(authorityConstraint.getBodyType(), "类型设置错误");
            else if (inTypeTable(user, formOutDto, authorityConstraint, typeList.indexOf(type)))
                return true;
        }
        return false;
    }

    /**
     * 获取限制对象,如果空字符串传入,返回null
     * @param authorityString 限制对象存储字符串
     * @return 限制对象
     * @throws AuthoritySolveException json字符串出错
     */
    public static Constraint getConstraint(String authorityString) throws AuthoritySolveException {
        if (DataProcessUtils.isEmpty(authorityString))
            return null;
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

    private static boolean inTypeTable(HumanDto user, FormOutDto formOutDto, Constraint authorityConstraint, int index) throws EoaException {
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
        return solve.solve(user, formOutDto);
    }


}

