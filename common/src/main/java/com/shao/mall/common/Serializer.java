package com.shao.mall.common;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

/**
 * @author newjiang
 * @date 2019/5/16
 * @description: 序列化与反序列化工具
 */
public class Serializer<T> {

    private T t;

    private Schema<T> schema;

    public Serializer(T t) {
        this.t = t;
        this.schema = (Schema<T>) RuntimeSchema.createFrom(t.getClass());
    }

    /**
     * 序列化
     *
     * @return
     */
    public byte[] serialize() {
        final LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            return serializeInternal(t, schema, buffer);
        } catch (final Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }

    /**
     * 反序列化
     *
     * @param bytes 字节数组
     * @return
     */
    public T deserialize(final byte[] bytes) {
        try {
            T t = deserializeInternal(bytes, schema.newMessage(), schema);
            if (t != null) {
                return t;
            }
        } catch (final Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
        return null;
    }

    private <T> byte[] serializeInternal(final T source, final Schema<T> schema,
                                         final LinkedBuffer buffer) {
        return ProtostuffIOUtil.toByteArray(source, schema, buffer);
    }

    private <T> T deserializeInternal(final byte[] bytes, final T result, final Schema<T> schema) {
        ProtostuffIOUtil.mergeFrom(bytes, result, schema);
        return result;
    }
}
