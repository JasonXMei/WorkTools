package com.jason.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.jason.dto.RespDTO;
import com.jason.dto.TokenDTO;
import com.jason.entity.Credential;
import com.jason.enums.ResponseCodeEnum;
import com.jason.exception.BusinessException;
import com.jason.service.CredentialService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jason
 * @since 2022-06-14
 */
@RestController
@RequestMapping("/credential")
public class CredentialController {

    @Resource
    private CredentialService credentialService;

    @GetMapping("/list")
    public RespDTO<List<Credential>> list() {
        return RespDTO.success(credentialService.list());
    }

    @PostMapping("/upsert")
    @Transactional
    public RespDTO<Credential> upsert(@RequestBody Credential credential) {
        credentialService.saveOrUpdate(credential);
        return RespDTO.success(credentialService.getById(credential.getId()));
    }

    @PostMapping("/createToken")
    public RespDTO<TokenDTO> createToken(Credential credential) {
        Credential record = credentialService.getByParam(credential);
        if (record == null) {
            return RespDTO.fail(ResponseCodeEnum.BAD_REQUEST, "cannot found record");
        } else {
            JWTSigner jwtSigner = JWTSignerUtil.createSigner(record.getArgorithm(), record.getSigner().getBytes());

            Map<String, Object> padload = new HashMap<String, Object>() {
                {
                    put(JWTPayload.ISSUED_AT, DateUtil.currentSeconds()); // 签发时间
                    put(JWTPayload.NOT_BEFORE, DateUtil.currentSeconds()); // 生效时间
                    put(JWTPayload.EXPIRES_AT, DateUtil.currentSeconds() + record.getExpireAt()); // 失效时间
                    put(JWTPayload.AUDIENCE, "Jason");

                    Credential sub = new Credential();
                    sub.setAppId(record.getAppId());
                    put(JWTPayload.SUBJECT, JSONUtil.toJsonStr(sub));
                }
            };

            String token = JWTUtil.createToken(padload, jwtSigner);
            TokenDTO tokenDTO = TokenDTO.builder()
                    .token(token)
                    .issuedAt(DateUtil.currentSeconds())
                    .expiredAt(DateUtil.currentSeconds() + record.getExpireAt())
                    .build();

            return RespDTO.success(tokenDTO);
        }
    }


    @PostMapping("/verifyToken")
    public RespDTO<Boolean> verifyToken(TokenDTO tokenDTO) {
        String token = tokenDTO.getToken();

        JWT jwt = JWTUtil.parseToken(token);
        Object sub = jwt.getPayload(JWTPayload.SUBJECT);
        Credential credential = JSONUtil.toBean(JSONUtil.toJsonStr(sub), Credential.class);
        Credential record = credentialService.getByParam(credential);
        if (record == null) {
            return RespDTO.fail(ResponseCodeEnum.BAD_REQUEST, "cannot found record");
        } else {
            try {
                JWTValidator.of(token).validateDate();
            } catch (ValidateException validateException) {
                throw new BusinessException(ResponseCodeEnum.BAD_REQUEST, "token expired");
            }
            JWTSigner jwtSigner = JWTSignerUtil.createSigner(record.getArgorithm(), record.getSigner().getBytes());
            return RespDTO.success(JWTUtil.verify(token, jwtSigner));
        }
    }

}
