package com.example.demo.linkUtils.mapper;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * 简单封装Jackson，实现JSON String&lt;-&gt;Java Object转换的Mapper.
 * <p>
 * 可以直接使用公共示例JsonMapper.INSTANCE, 也可以使用不同的builder函数创建实例，封装不同的输出风格,
 * <p>
 * 不要使用GSON, 在对象稍大时非常缓慢.
 * <p>
 * 注意: 需要参考本模块的POM文件，显式引用jackson.
 *
 */
@Slf4j
public class JsonMapper {

    public static final JsonMapper INSTANCE = new JsonMapper();

    private static final String PARSE_ERROR_TEMPLATE = "parse json string error: %s";

    private ObjectMapper mapper;

    public JsonMapper() {
        this(null);
    }

    public JsonMapper(Include include) {
        mapper = new ObjectMapper();
        // add java8 modules register
        mapper.findAndRegisterModules();
        // 设置输出时包含属性的风格
        if (include != null) {
            mapper.setSerializationInclusion(include);
        }
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    /**
     * 创建只输出非Null的属性到Json字符串的Mapper.
     */
    public static JsonMapper nonNullMapper() {
        return new JsonMapper(Include.NON_NULL);
    }

    /**
     * 创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Mapper.
     * <p>
     * 注意，要小心使用, 特别留意empty的情况.
     */
    public static JsonMapper nonEmptyMapper() {
        return new JsonMapper(Include.NON_EMPTY);
    }

    /**
     * 默认的全部输出的Mapper, 区别于INSTANCE，可以做进一步的配置
     */
    public static JsonMapper defaultMapper() {
        return new JsonMapper();
    }

    /**
     * Object可以是POJO，也可以是Collection或数组。 如果对象为Null, 返回"null". 如果集合为空集合, 返回"[]".
     */
    public String toJson(Object object) {

        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            log.error(String.format("write to json string error: %s", object), e);
            return null;
        }
    }

    /**
     * Object可以是POJO，也可以是Collection或数组。 返回byte[]数组.
     */
    public byte[] toJsonBytes(Object object) {

        try {
            return mapper.writeValueAsBytes(object);
        } catch (IOException e) {
            log.error(String.format("write to json string error: %s", object), e);
            return new byte[0];
        }
    }

    /**
     * 反序列化POJO或简单Collection如List&lt;String&gt;.
     * <p>
     * 如果JSON字符串为Null或"null"字符串, 返回Null. 如果JSON字符串为"[]", 返回空集合.
     * <p>
     * 如需反序列化复杂Collection如List&lt;MyBean&gt;, 请使用fromJson(String, JavaType)
     *
     * @see #fromJson(String, JavaType)
     */
    public <T> T fromJson(@Nullable String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            return mapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            log.error(String.format(PARSE_ERROR_TEMPLATE , jsonString), e);
            return null;
        }
    }

    /**
     * 反序列化POJO或简单Collection如List&lt;String&gt;.
     *
     * 如果jsonByte为null则返回null
     *
     * 如需反序列化复杂Collection如List&lt;MyBean&gt;, 请使用fromJson(String, JavaType)
     *
     * @see #fromJson(String, JavaType)
     */
    public <T> T fromJson(@Nullable byte[] jsonByte, Class<T> clazz) {
        if (jsonByte == null) {
            return null;
        }
        try {
            return mapper.readValue(jsonByte, clazz);
        } catch (IOException e) {
            log.error(String.format(PARSE_ERROR_TEMPLATE , jsonByte), e);
            return null;
        }
    }

    /**
     * 反序列化复杂Collection如List&lt;Bean&gt;, contructCollectionType()或contructMapType()构造类型, 然后调用本函数.
     *
     * @see #buildCollectionType
     */
    public <T> T fromJson(@Nullable String jsonString, JavaType javaType) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }

        try {
            return mapper.readValue(jsonString, javaType);
        } catch (IOException e) {
            log.error(String.format(PARSE_ERROR_TEMPLATE, jsonString), e);
            return null;
        }
    }

    /**
     * 反序列化 {@code List<T>}对象
     * @param jsonString json字符串
     * @param clazz List 元素class类型
     * @param <T> 模板元素
     * @return 返回List对象
     */
    public <T> List<T> getListFromJson(@Nullable String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return Collections.emptyList();
        }
        try {
            return mapper.readValue(jsonString, buildCollectionType(List.class, clazz));
        } catch (IOException e) {
            log.error(String.format(PARSE_ERROR_TEMPLATE , jsonString), e);
            return Collections.emptyList();
        }
    }

    /**
     * 反序列化 {@code Map<String, V>} 对象
     *
     * @param jsonString json字符串
     * @param valueClazz map value class类型
     * @return 返回Map对象
     */
    public <V> Map<String, V> getMapFromJson(@Nullable String jsonString, Class<V> valueClazz) {
        return getMapFromJson(jsonString, String.class, valueClazz);
    }

    /**
     * 反序列化 {@code Map<K, V>}对象
     * @param jsonString json字符串
     * @param keyClazz map key class type
     * @param valueClazz map value class类型
     * @return 返回Map对象
     */
    public <K, V> Map<K, V> getMapFromJson(@Nullable String jsonString, Class<K> keyClazz, Class<V> valueClazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return Collections.emptyMap();
        }
        try {
            return mapper.readValue(jsonString, buildMapType(Map.class, keyClazz, valueClazz));
        } catch (IOException e) {
            log.error(String.format(PARSE_ERROR_TEMPLATE , jsonString), e);
            return Collections.emptyMap();
        }
    }

    /**
     * 构造Collection类型.
     */
    public JavaType buildCollectionType(Class<? extends Collection> collectionClass, Class<?> elementClass) {
        return mapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
    }

    /**
     * 构造Map类型.
     */
    public JavaType buildMapType(Class<? extends Map> mapClass, Class<?> keyClass, Class<?> valueClass) {
        return mapper.getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
    }

    /**
     * 当JSON里只含有Bean的部分属性時，更新一個已存在Bean，只覆盖該部分的属性.
     */
    public void update(String jsonString, Object object) {
        try {
            mapper.readerForUpdating(object).readValue(jsonString);
        } catch (IOException e) {
            log.error(String.format("update json string: %s to object: %s error.",jsonString, object), e);
        }
    }

    /**
     * 輸出JSONP格式數據.
     */
    public String toJsonP(String functionName, Object object) {
        return toJson(new JSONPObject(functionName, object));
    }

    /**
     * 設定是否使用Enum的toString函數來讀寫Enum, 為False時時使用Enum的name()函數來讀寫Enum, 默認為False. 注意本函數一定要在Mapper創建後, 所有的讀寫動作之前調用.
     */
    public void enableEnumUseToString() {
        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
    }

    /**
     * 取出Mapper做进一步的设置或使用其他序列化API.
     */
    public ObjectMapper getMapper() {
        return mapper;
    }
}
