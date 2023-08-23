package com.jason.interceptor;

import com.jason.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Jason
 * @Date 2022/06/21
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private CredentialService credentialService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /// ignore auth
//        String token = request.getHeader(CommonConstant.HEADER_KEY);
//        if (StrUtil.isEmpty(token)) {
//            throw new BusinessException(ResponseCodeEnum.UNAUTHORIZED, "header token cannot be blank");
//        }
//
//        JWT jwt = JWTUtil.parseToken(token);
//        Object sub = jwt.getPayload(JWTPayload.SUBJECT);
//        Credential credential = JSONUtil.toBean(JSONUtil.toJsonStr(sub), Credential.class);
//        Credential record = credentialService.getByParam(credential);
//        if (record == null) {
//            throw new BusinessException(ResponseCodeEnum.BAD_REQUEST, "cannot found record");
//        } else {
//            try {
//                JWTValidator.of(token).validateDate();
//            } catch (ValidateException validateException) {
//                throw new BusinessException(ResponseCodeEnum.BAD_REQUEST, "token expired");
//            }
//
//            JWTSigner jwtSigner = JWTSignerUtil.createSigner(record.getArgorithm(), record.getSigner().getBytes());
//            return JWTUtil.verify(token, jwtSigner);
//        }

        return true;
    }

}
