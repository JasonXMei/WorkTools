package com.jason.util;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jason.enums.ResponseCodeEnum;
import com.jason.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author Jason
 * @Date 2022/06/21
 */
@Slf4j
public class ObjectMapperUtil {

    private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.registerModule(new JavaTimeModule())
                // 是否需要排序
                .configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true)
                // 忽略空 bean 转 json 的错误
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                // 取消默认转换 timestamps 形式
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                // 序列化时，过滤 null 属性
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                // 忽略在 json 字符串中存在，但在 java 对象中不存在对应属性情况，防止错误
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                // 忽略空 bean 转 json 的错误
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                // date 转 string
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static <T> Map<String, String> obj2Map(T obj) {
        try {
            if (Objects.isNull(obj)) {
                return new HashMap<>();
            }

            Map<String, Object> convertMap = OBJECT_MAPPER.readValue(JSONUtil.toJsonStr(obj), Map.class);
            Map<String, String> result = new HashMap<>(convertMap.size());
            for (Map.Entry<String, Object> entry : convertMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (Objects.nonNull(value)) {
                    result.put(key, String.valueOf(value));
                }
            }
            return result;
        } catch (IOException e) {
            log.error("obj to map failed", e);
            throw new BusinessException(ResponseCodeEnum.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
