package com.jason.interceptor;

import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.jason.constant.CommonConstant;
import com.jason.entity.Credential;
import com.jason.enums.ResponseCodeEnum;
import com.jason.exception.BusinessException;
import com.jason.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private CredentialService credentialService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(CommonConstant.HEADER_KEY);

        JWT jwt = JWTUtil.parseToken(token);
        Object sub = jwt.getPayload(JWTPayload.SUBJECT);
        Credential credential = JSONUtil.toBean(JSONUtil.toJsonStr(sub), Credential.class);
        Credential record = credentialService.getByParam(credential);
        if (record == null) {
            throw new BusinessException(ResponseCodeEnum.BAD_REQUEST, "cannot found record");
        } else {
            try {
                JWTValidator.of(token).validateDate();
            } catch (ValidateException validateException) {
                throw new BusinessException(ResponseCodeEnum.BAD_REQUEST, "token expired");
            }

            JWTSigner jwtSigner = JWTSignerUtil.createSigner(record.getArgorithm(), record.getSigner().getBytes());
            return JWTUtil.verify(token, jwtSigner);
        }
    }

}
