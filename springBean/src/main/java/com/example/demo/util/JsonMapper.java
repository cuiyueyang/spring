package com.example.demo.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: json工具类</p>
 * <p>Date: 2021/7/27 13:18 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */
public class JsonMapper {
    private static final Logger log = LoggerFactory.getLogger(JsonMapper.class);
    public static final JsonMapper INSTANCE = new JsonMapper();
    private static final String PARSE_ERROR_TEMPLATE = "parse json string error: %s";
    private ObjectMapper mapper;

    public JsonMapper() {
        this((JsonInclude.Include)null);
    }

    public JsonMapper(JsonInclude.Include include) {
        this.mapper = new ObjectMapper();
        this.mapper.findAndRegisterModules();
        if (include != null) {
            this.mapper.setSerializationInclusion(include);
        }

        this.mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static JsonMapper nonNullMapper() {
        return new JsonMapper(JsonInclude.Include.NON_NULL);
    }

    public static JsonMapper nonEmptyMapper() {
        return new JsonMapper(JsonInclude.Include.NON_EMPTY);
    }

    public static JsonMapper defaultMapper() {
        return new JsonMapper();
    }

    public String toJson(Object object) {
        try {
            return this.mapper.writeValueAsString(object);
        } catch (IOException var3) {
            log.error(String.format("write to json string error: %s", object), var3);
            return null;
        }
    }

    public byte[] toJsonBytes(Object object) {
        try {
            return this.mapper.writeValueAsBytes(object);
        } catch (IOException var3) {
            log.error(String.format("write to json string error: %s", object), var3);
            return null;
        }
    }

    public <T> T fromJson(@Nullable String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        } else {
            try {
                return this.mapper.readValue(jsonString, clazz);
            } catch (IOException var4) {
                log.error(String.format("parse json string error: %s", jsonString), var4);
                return null;
            }
        }
    }

    public <T> T fromJson(@Nullable byte[] jsonByte, Class<T> clazz) {
        if (jsonByte == null) {
            return null;
        } else {
            try {
                return this.mapper.readValue(jsonByte, clazz);
            } catch (IOException var4) {
                log.error(String.format("parse json string error: %s", jsonByte), var4);
                return null;
            }
        }
    }

    public <T> T fromJson(@Nullable String jsonString, JavaType javaType) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        } else {
            try {
                return this.mapper.readValue(jsonString, javaType);
            } catch (IOException var4) {
                log.error(String.format("parse json string error: %s", jsonString), var4);
                return null;
            }
        }
    }

    public <T> List<T> getListFromJson(@Nullable String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return Collections.emptyList();
        } else {
            try {
                return (List)this.mapper.readValue(jsonString, this.buildCollectionType(List.class, clazz));
            } catch (IOException var4) {
                log.error(String.format("parse json string error: %s", jsonString), var4);
                return Collections.emptyList();
            }
        }
    }

    public <V> Map<String, V> getMapFromJson(@Nullable String jsonString, Class<V> valueClazz) {
        return this.getMapFromJson(jsonString, String.class, valueClazz);
    }

    public <K, V> Map<K, V> getMapFromJson(@Nullable String jsonString, Class<K> keyClazz, Class<V> valueClazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return Collections.emptyMap();
        } else {
            try {
                return (Map)this.mapper.readValue(jsonString, this.buildMapType(Map.class, keyClazz, valueClazz));
            } catch (IOException var5) {
                log.error(String.format("parse json string error: %s", jsonString), var5);
                return Collections.emptyMap();
            }
        }
    }

    public JavaType buildCollectionType(Class<? extends Collection> collectionClass, Class<?> elementClass) {
        return this.mapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
    }

    public JavaType buildMapType(Class<? extends Map> mapClass, Class<?> keyClass, Class<?> valueClass) {
        return this.mapper.getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
    }

    public void update(String jsonString, Object object) {
        try {
            this.mapper.readerForUpdating(object).readValue(jsonString);
        } catch (IOException var4) {
            log.error(String.format("update json string: %s to object: %s error.", jsonString, object), var4);
        }

    }

    public String toJsonP(String functionName, Object object) {
        return this.toJson(new JSONPObject(functionName, object));
    }

    public void enableEnumUseToString() {
        this.mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        this.mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
    }

    public ObjectMapper getMapper() {
        return this.mapper;
    }
}
