package com.cxl.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author cxl
 * @Date 7/6/2023 17:09
 * @ClassReference: com.cxl.nio.NIOFileChannel03
 * @Description: 从一个文件读取数据输出到另一个文件中
 */
public class NIOFileChannel03 {

    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("1.txt");
        FileChannel fileChannel01 = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");
        FileChannel fileChannel02 = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        // 循环读取
        while (true) {
            // 清空byteBuffer
            byteBuffer.clear();

            int read = fileChannel01.read(byteBuffer);
            System.out.println(read);
            // 表示读完
            if (read == -1) {
                break;
            }
            // 将buffer 中的数据写入到 fileChannel02 -- 2.txt
            byteBuffer.flip();
            fileChannel02.write(byteBuffer);
        }

        fileInputStream.close();
        fileOutputStream.close();
    }
}
