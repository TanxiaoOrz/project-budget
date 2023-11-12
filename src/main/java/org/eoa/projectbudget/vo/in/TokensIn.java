package org.eoa.projectbudget.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import org.eoa.projectbudget.exception.ParameterException;

/**
 * @Author: 张骏山
 * @Date: 2023/11/12 21:11
 * @PackageName: IntelliJ IDEA
 * @ClassName: TokensIn
 * @Description: tokens传入检查
 * @Version: 1.0
 */

@Schema(name = "TokensIn", description = "需要检查是否合法登录的tokens字符串")
public class TokensIn implements CheckParameter{

    @Schema(description = "tokens字符串")
    String tokens;

    public String getTokens() {
        return tokens;
    }

    public TokensIn setTokens(String tokens) {
        this.tokens = tokens;
        return this;
    }

    @Override
    public void checkSelf(int type) throws ParameterException {
    }

    @Override
    public void checkSelf() throws ParameterException {
    }
}
