package com.cxl.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author cxl
 * @Date 7/6/2023 17:02
 * @ClassReference: com.cxl.nio.NIOFileChannel02
 * @Description: 从文件里读取数据
 */
public class NIOFileChannel02 {

    public static void main(String[] args) throws IOException {

        // 创建文件的输入流
        File file = new File("d:\\file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);

        // 通过fileInputStream 获取对应的FileChannel -> 实际类型 FileChannelImpl
        FileChannel fileChannel = fileInputStream.getChannel();

        // 创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(((int) file.length()));

        // 将通道的数据读入到Buffer
        fileChannel.read(byteBuffer);

        // 将byteBuffer的字节数据转成String
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }
}
