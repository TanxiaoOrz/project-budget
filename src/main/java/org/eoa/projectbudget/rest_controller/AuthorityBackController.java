package org.eoa.projectbudget.rest_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.eoa.projectbudget.dto.HumanDto;
import org.eoa.projectbudget.entity.Authority;
import org.eoa.projectbudget.entity.Character;
import org.eoa.projectbudget.exception.EoaException;
import org.eoa.projectbudget.exception.ParameterException;
import org.eoa.projectbudget.mapper.CharacterMapper;
import org.eoa.projectbudget.service.cache.CacheService;
import org.eoa.projectbudget.service.organization_module.AuthorityService;
import org.eoa.projectbudget.utils.ChangeFlagUtils;
import org.eoa.projectbudget.utils.FilterUtils;
import org.eoa.projectbudget.utils.factory.CharacterOutFactory;
import org.eoa.projectbudget.vo.in.CharacterIn;
import org.eoa.projectbudget.vo.out.CharacterOut;
import org.eoa.projectbudget.vo.out.Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2023/12/17 20:35
 * @PackageName: org.eoa.projectbudget.rest_controller
 * @ClassName: AuthorityBackController
 * @Description: 权限模块后端控制器对象
 * @Version: 1.0
 */

@RestController
@Tag(name = "权限模块后端控制器对象")
@CrossOrigin
@RequestMapping("/api/v1/authority/back")
public class AuthorityBackController {

    @Autowired
    CacheService cacheService;
    @Autowired
    ChangeFlagUtils changeFlagUtils;


    @Autowired
    AuthorityService authorityService;
    @Autowired
    CharacterOutFactory characterOutFactory;

    static final Long USER_ID_CACHE = 0L;

    @GetMapping("/character/human")
    @Operation(summary = "获取角色下人员")
    @Parameter(name = "characterId", description = "角色编号", required = true, in = ParameterIn.QUERY)
    public Vo<List<CharacterMapper.Grade>> getCharacterHuman(@RequestAttribute("HumanDto") HumanDto humanDto,
                                                             @RequestParam Long characterId) {
        List<CharacterMapper.Grade> outs;
        String method = "humanLink" + characterId;
        CharacterMapper.Grade[] cache = cacheService.getCache(ChangeFlagUtils.LINK,method, USER_ID_CACHE, CharacterMapper.Grade[].class);
        if (cache == null) {
            outs = authorityService.getHumansOfCharacter(characterId, humanDto.getDataId());
            cacheService.setCache(ChangeFlagUtils.LINK,method, USER_ID_CACHE, outs);
        } else
            outs = Arrays.asList(cache);
        return new Vo<>(outs);
    }

    @GetMapping("/character/authority")
    @Operation(summary = "获取角色下权限")
    @Parameter(name = "characterId", description = "角色编号", required = true, in = ParameterIn.QUERY)
    public Vo<List<Authority>> getCharacterAuthority(@RequestAttribute("HumanDto") HumanDto humanDto,
                                              @RequestParam Long characterId) {
        List<Authority> outs;
        String method = "authorityLink" + characterId;
        Authority[] cache = cacheService.getCache(ChangeFlagUtils.LINK,method, USER_ID_CACHE, Authority[].class);
        if (cache == null) {
            outs = authorityService.getAuthorityOfCharacter(characterId, humanDto.getDataId());
            cacheService.setCache(ChangeFlagUtils.LINK,method, USER_ID_CACHE, outs);
        } else
            outs = Arrays.asList(cache);
        return new Vo<>(outs);
    }

    @PutMapping("/link/{characterId}")
    @Operation(summary = "添加角色下属")
    @Parameters({
            @Parameter(name = "characterId", description = "角色编号", required = true, in = ParameterIn.PATH),
            @Parameter(name = "dataId", description = "对象编号", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "type", description = "链接对象类型",examples = {@ExampleObject("human"),@ExampleObject("authority")}, in = ParameterIn.QUERY),
            @Parameter(name = "grade", description = "角色链接等级", required = true, in = ParameterIn.QUERY)
    })

    public Vo<String> linkCharacterItem(@RequestAttribute("HumanDto") HumanDto humanDto,
                                             @RequestParam Long dataId,
                                             @RequestParam String type,
                                             @RequestParam(required = false) Integer grade,
                                             @PathVariable Long characterId) {
        Integer integer;
        switch (type) {
            case "human" -> integer = authorityService.characterLinkUser(characterId, dataId, grade, humanDto.getDataId());
            case "authority" -> integer = authorityService.characterLinkAuthority(characterId, dataId, humanDto.getDataId());
            default -> integer = null;
        }
        if (integer == null) {
            throw new ParameterException("type", type, "错误的类型输入");
        }
        changeFlagUtils.freshDate(ChangeFlagUtils.LINK);
        return new Vo<>("链接成功");
    }

    @PutMapping("/drop/{characterId}")
    @Operation(summary = "删除角色下属")
    @Parameters({
            @Parameter(name = "characterId", description = "角色编号", required = true, in = ParameterIn.PATH),
            @Parameter(name = "dataId", description = "对象编号", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "type", description = "链接对象类型",examples = {@ExampleObject("human"),@ExampleObject("authority")}, in = ParameterIn.QUERY)
    })

    public Vo<String> dropCharacterItem(@RequestAttribute("HumanDto") HumanDto humanDto,
                                             @RequestParam Long dataId,
                                             @RequestParam String type,
                                             @PathVariable Long characterId) {
        Integer integer;
        switch (type) {
            case "human" -> integer = authorityService.characterDropUser(characterId, dataId, humanDto.getDataId());
            case "authority" -> integer = authorityService.characterDropAuthority(characterId, dataId, humanDto.getDataId());
            default -> integer = null;
        }
        if (integer == null) {
            throw new ParameterException("type", type, "错误的类型输入");
        }
        changeFlagUtils.freshDate(ChangeFlagUtils.LINK);
        return new Vo<>("链接成功");
    }

    @GetMapping("/character/{dataId}")
    @Operation(summary = "获取具体角色")
    @Parameter(name = "dataId", description = "角色编号", required = true, in = ParameterIn.PATH)
    public Vo<CharacterOut> getCharacter(@RequestAttribute("HumanDto") HumanDto humanDto,
                                         @PathVariable("dataId")Long dataId) {
        CharacterOut out = cacheService.getCache(ChangeFlagUtils.CHARACTER,dataId.toString(), USER_ID_CACHE, CharacterOut.class);
        if (out == null) {
            out = characterOutFactory.out(authorityService.getCharacterById(dataId, humanDto.getDataId()));
            cacheService.setCache(ChangeFlagUtils.CHARACTER,dataId.toString(),USER_ID_CACHE,out);
        }
        return new Vo<>(out);
    }

    @PostMapping("/character")
    @Operation(summary = "新建角色")
    public Vo<Long> newCharacter(@RequestAttribute("HumanDto") HumanDto humanDto,
                                 @RequestBody CharacterIn character) {
        Long id = authorityService.newCharacter(character.toEntity(null), humanDto.getDataId());
        if (id != null) {
            changeFlagUtils.freshDate(ChangeFlagUtils.CHARACTER);
            return new Vo<>(id);
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行新建,请联系管理员");
    }

    @PutMapping("/character/{dataId}")
    @Operation(summary = "修改角色")
    @Parameter(name = "dataId", description = "角色编号", required = true, in = ParameterIn.PATH)
    public Vo<String> updateCharacter(@RequestAttribute("HumanDto") HumanDto humanDto,
                                      @PathVariable("dataId")Long dataId,
                                      @RequestBody CharacterIn character) {
       Integer update = authorityService.updateCharacter(character.toEntity(dataId), humanDto.getDataId());
        if (update==1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.CHARACTER);
            return new Vo<>("修改成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行修改,没有变动项");
    }

    @DeleteMapping("/character/{dataId}")
    @Operation(summary = "删除角色")
    @Parameter(name = "dataId", description = "角色编号", required = true, in = ParameterIn.PATH)
    public Vo<String> deleteCharacter(@RequestAttribute("HumanDto") HumanDto humanDto,
                                      @PathVariable("dataId")Long dataId) {
        Integer deletes = authorityService.dropCharacter(dataId, humanDto.getDataId());
        if (deletes==1) {
            changeFlagUtils.freshDate(ChangeFlagUtils.CHARACTER);
            return new Vo<>("删除成功");
        } else
            return new Vo<>(Vo.SERVER_ERROR,"未进行删除,该数据不存在");
    }

    @GetMapping("/character")
    @Operation(summary = "获取角色列表")
    public Vo<List<CharacterOut>> getCharacterList(@RequestAttribute("HumanDto") HumanDto humanDto,
                                                   HttpServletRequest request) throws EoaException {
        FilterUtils<Character> filter = new FilterUtils<>(request.getParameterMap(),Character.class);
        String method = filter.getDescription();
        List<CharacterOut> outs;
        CharacterOut[] cache = cacheService.getCache(ChangeFlagUtils.CHARACTER, method, USER_ID_CACHE, CharacterOut[].class);
        if (cache == null) {
            outs = characterOutFactory.outs(authorityService.getAllCharacter(filter.getWrapper(), humanDto.getDataId()));
            cacheService.setCache(ChangeFlagUtils.CHARACTER,method, USER_ID_CACHE, outs);
        } else {
            outs = List.of(cache);
        }
        return new Vo<>(filter.filt(outs),outs.size());
    }

    @GetMapping("/authority")
    @Operation(summary = "获取权限列表")
    public Vo<List<Authority>> getAuthorityList(@RequestAttribute("HumanDto")HumanDto humanDto,
                                                HttpServletRequest request) throws EoaException {
        FilterUtils<Authority> filter = new FilterUtils<>(request.getParameterMap(),Authority.class);
        String method = filter.getDescription();
        List<Authority> outs;
        Authority[] cache = cacheService.getCache(ChangeFlagUtils.AUTHORITY, method, USER_ID_CACHE, Authority[].class);
        if (cache == null) {
            outs = authorityService.getAllAuthority(filter.getWrapper(), humanDto.getDataId());
            cacheService.setCache(ChangeFlagUtils.AUTHORITY,method, USER_ID_CACHE, outs);
        } else {
            outs = List.of(cache);
        }
        return new Vo<>(filter.filt(outs),outs.size());
    }
}
