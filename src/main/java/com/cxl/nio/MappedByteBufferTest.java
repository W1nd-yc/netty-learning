package com.cxl.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author cxl
 * @Date 10/6/2023 13:27
 * @ClassReference: com.cxl.nio.MappedByteBuffer
 * @Description:
 * 1.MappedByteBuffer可以让文件直接在内存（堆外内存）修改，操作系统不需要拷贝一次
 */
public class MappedByteBufferTest {

    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt", "rw");
        // 获取对应的通道
        FileChannel channel = randomAccessFile.getChannel();

        // 参数1：FileChannel.MapMode.READ_WRITE 使用的读写模式
        // 参数2： 0 :可以直接修改的起始位置
        // 参数3： 5 :映射到内存的大小，即将 1.txt的多少个字节映射到内存
        // 可以直接修改的范围就是0-5
        // 直接类型是DirectByteBuffer
        MappedByteBuffer mapByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mapByteBuffer.put(0, ((byte) 'H'));
        mapByteBuffer.put(3, ((byte) '9'));
        mapByteBuffer.put(5, ((byte) 'Y')); // IndexOutOfBoundsException

        randomAccessFile.close();
        System.out.println("修改成功");
    }
}
