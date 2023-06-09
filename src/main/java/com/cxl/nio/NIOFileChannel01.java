package com.cxl.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author cxl
 * @Date 7/6/2023 16:51
 * @ClassReference: com.cxl.nio.NIOFileChannel01
 * @Description: 输出文字到一个文件上
 */
public class NIOFileChannel01 {

    public static void main(String[] args) throws IOException {
        String str = "hello, cxl";
        // 创建一个输出流->channel
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\file01.txt");

        // 通过 fileOutputStream 获取 对应的 FileChannel
        // 这个 fileChannel 真实类型是 FileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();

        // 创建一个缓冲区 ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 将 str 放入 byteBuffer
        byteBuffer.put(str.getBytes());

        // 对 byteBuffer 进行flip
        byteBuffer.flip();

        // 将byteBuffer 数据写入到 fileChannel
        fileChannel.write(byteBuffer);
        fileOutputStream.close();
    }
}
