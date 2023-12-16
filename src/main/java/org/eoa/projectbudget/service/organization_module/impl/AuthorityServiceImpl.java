package org.eoa.projectbudget.service.organization_module.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.eoa.projectbudget.entity.Authority;
import org.eoa.projectbudget.entity.Character;
import org.eoa.projectbudget.exception.LoginException;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.mapper.AuthorityMapper;
import org.eoa.projectbudget.mapper.CharacterMapper;
import org.eoa.projectbudget.service.organization_module.AuthorityService;
import org.eoa.projectbudget.vo.Tokens;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * @Author: 张骏山
 * @Date: 2023/11/6 13:08
 * @PackageName: org.eoa.projectbudget.service.organization_module.impl
 * @ClassName: AuthorityServiceImpl
 * @Description: TODO
 * @Version: 1.0
 **/

@Service
public class AuthorityServiceImpl implements AuthorityService, InitializingBean {

    @Value("${eoa.token-short-time}")
    Integer tokenShortTime;

    @Value("${eoa.token-long-time}")
    Integer tokenLongTime;

    @Value("${eoa.token-pass}")
    String tokenPass;

    Algorithm algorithm;

    Logger log = LoggerFactory.getLogger("AuthorityModule");

    @Autowired
    AuthorityMapper authorityMapper;
    @Autowired
    CharacterMapper characterMapper;

    @Override
    public Authority getAuthorityById(Long authorityId, Long userId) {
        log.info("用户=>{}获取权限=>{}",userId,authorityId);
        Authority authority = authorityMapper.selectById(userId);
        if (authority == null) {
            throw new ParameterException("authorityId",authorityId.toString(),"不存在该权限");
        }
        return authority;
    }

    @Override
    public List<Authority> getAllAuthority(QueryWrapper<Authority> wrapper, Long userId) {
        List<Authority> authorities = authorityMapper.selectList(wrapper);
        log.info("用户=>{}获取角色列表,获取到{}个",userId,authorities.size());
        return authorities;
    }

    @Override
    public Character getCharacterById(Long characterId, Long userId) {
        log.info("用户=>{}获取角色=>{}",userId,characterId);
        Character character = characterMapper.selectById(userId);
        if (character == null) {
            throw new ParameterException("authorityId",characterId.toString(),"不存在该权限");
        }
        return character;
    }

    @Override
    public List<Character> getAllCharacter(QueryWrapper<Character> wrapper, Long userId) {
        List<Character> characters = characterMapper.selectList(wrapper);
        log.info("用户=>{}获取角色列表,获取到{}个",userId,characters.size());
        return characters;
    }

    @Override
    public Long newCharacter(Character character, Long userId) {
        character.setCreator(userId);
        character.setCreateTime(new Date());
        log.info("用户=>{}创建角色=>{}",userId,character);
        characterMapper.insert(character);
        return character.getDataId();
    }

    @Override
    public Integer updateCharacter(Character character, Long userId) {
        int update = characterMapper.updateById(character);
        log.info("用户=>{}修改角色=>{},修改行数=>{}",userId,character,update);
        return update;
    }

    @Override
    public Integer dropCharacter(Long characterId, Long userId) {
        int delete = characterMapper.deleteById(characterId);
        Integer dropHumanAll = characterMapper.dropHumanAll(characterId);
        Integer dropAuthorityAll = characterMapper.dropAuthorityAll(characterId);
        log.info("用户=>{}废弃角色=>{},修改行数=>{},废弃人员链接数量=>{},废弃权限链接数量=>{}",userId,characterId,delete,dropHumanAll,dropAuthorityAll);
        return delete;
    }

    @Override
    public Integer characterLinkAuthority(Long characterId, Long authorityId, Long userId) {
        List<Integer> authorityIds = authorityMapper.getIdsFormCharacter(characterId);
        if (authorityIds.contains(Integer.valueOf(authorityId.toString()))) {
            log.error("用户=>{}对角色=>{}增加权限=>{},已存在",userId,characterId,authorityId);
        }
        Integer linked = characterMapper.linkAuthority(characterId, authorityId);
        log.info("用户=>{}对角色=>{}增加权限=>{},修改数量=>{}",userId,characterId,authorityId,linked);
        return linked;
    }

    @Override
    public Integer characterLinkUser(Long characterId, Long humanId, Integer grade, Long userId) {
        List<CharacterMapper.Grade> characterIds = characterMapper.getCharacterIdFromHuman(humanId);
        if (characterIds.stream().anyMatch(gradeExist -> gradeExist.getCharacterId().equals(characterId))) {
            log.error("用户=>{}对角色=>{}增加人员=>{},等级=>{},已存在",userId,characterId,humanId,grade);
        }
        Integer linked = characterMapper.linkHuman(characterId, humanId,grade);
        log.info("用户=>{}对角色=>{}增加人员=>{},等级=>{},修改数量=>{}",userId,characterId,humanId,grade,linked);
        return linked;
    }

    @Override
    public Integer characterDropAuthority(Long characterId, Long authorityId, Long userId) {
        List<Integer> authorityIds = authorityMapper.getIdsFormCharacter(characterId);
        if (!authorityIds.contains(Integer.valueOf(authorityId.toString()))) {
            log.error("用户=>{}对角色=>{}删除权限=>{},不存在",userId,characterId,authorityId);
        }
        Integer dropped = characterMapper.dropAuthority(characterId, authorityId);
        log.info("用户=>{}对角色=>{}删除权限=>{},修改数量=>{}",userId,characterId,authorityId,dropped);
        return dropped;
    }

    @Override
    public Integer characterDropUser(Long characterId, Long humanId, Long userId) {
        List<CharacterMapper.Grade> characterIds = characterMapper.getCharacterIdFromHuman(humanId);
        if (characterIds.stream().noneMatch(gradeExist -> gradeExist.getCharacterId().equals(characterId))) {
            log.error("用户=>{}对角色=>{}废弃人员=>{},不存在",userId,characterId,humanId);
        }
        Integer dropped = characterMapper.dropHuman(characterId, humanId);
        log.info("用户=>{}对角色=>{}增加人员=>{},修改数量=>{}",userId,characterId,humanId,dropped);
        return dropped;
    }

    @Override
    public Tokens getTokens(Long userId) {
        log.info("用户=>{}申请tokens",userId);
        Calendar shortToken = Calendar.getInstance();
        shortToken.add(Calendar.MINUTE,tokenShortTime);
        Calendar longTime = Calendar.getInstance();
        longTime.add(Calendar.MINUTE,tokenLongTime);

        Tokens tokens = new Tokens();

        tokens.setTimeStamp(new Date());
        tokens.setShortToken(JWT.create()
                    .withClaim("dataId",userId)
                    .withClaim("type","short")
                    .withExpiresAt(shortToken.getTime())
                    .sign(algorithm))
        .setLongToken(JWT.create()
                    .withClaim("dataId",userId)
                    .withClaim("type","long")
                    .withExpiresAt(longTime.getTime())
                    .sign(algorithm));


        return tokens;
    }

    @Override
    public UserWithToken getUser(Tokens tokens) throws ParameterException, LoginException {
        JWTVerifier build = JWT.require(algorithm).build();
        DecodedJWT decodeShort = null;
        DecodedJWT decodeLong;
        boolean shortOut = false;

        try {
            decodeLong = build.verify(tokens.getLongToken());
        } catch (TokenExpiredException e) {
            throw new LoginException(true);
        } catch (JWTVerificationException e) {
            throw new LoginException(false);
        }

        try {
            decodeShort = build.verify(tokens.getShortToken());
        } catch (TokenExpiredException e) {
            shortOut = true;
        } catch (JWTVerificationException e) {
            throw new LoginException(false);
        }
        Long userId = decodeLong.getClaim("dataId").asLong();
        log.info("token解析成功,获取用户=>{},是否更新=>{}",userId,shortOut);
        return new UserWithToken().setUserId(userId).setUpdate(shortOut);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        algorithm = Algorithm.HMAC256(tokenPass);
    }
}
