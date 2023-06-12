package com.cxl.nio;

import java.nio.ByteBuffer;

/**
 * @Author cxl
 * @Date 10/6/2023 13:23
 * @ClassReference: com.cxl.nio.ReadOnlyBuffer
 * @Description:
 */
public class ReadOnlyBuffer {

    public static void main(String[] args) {
        // 创建一个Buffer
        ByteBuffer buffer = ByteBuffer.allocate(64);

        for (int i = 0; i < 64; i++) {
            buffer.put(((byte) i));
        }

        // 读取
        buffer.flip();

        // 得到一个只读的Buffer
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());

        // 读取
        while (readOnlyBuffer.hasRemaining()) {
            System.out.println(readOnlyBuffer.get());
        }

        readOnlyBuffer.put(((byte) 100)); // ReadOnlyBufferException
    }
}
