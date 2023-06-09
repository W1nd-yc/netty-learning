package com.cxl.nio;

import java.nio.IntBuffer;

/**
 * @Author cxl
 * @Date 7/6/2023 15:58
 * @ClassReference: com.cxl.nio.BasicBuffer
 * @Description:
 */
public class BasicBuffer {

    public static void main(String[] args) {
        // 举例说明Buffer 的使用（简单说明）
        // 创建一个Buffer，大小为5，即可以存放5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);

        // 向buffer 存放数据
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }

        // 如何从buffer读取数据
        // 将buffer转换，读写切换
        intBuffer.flip();
        intBuffer.position(2);
        intBuffer.limit(3);
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
