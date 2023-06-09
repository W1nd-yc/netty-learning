package com.cxl.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @Author cxl
 * @Date 9/6/2023 09:03
 * @ClassReference: com.cxl.nio.NIOFileChannel04
 * @Description:
 */
public class NIOFileChannel04 {

    public static void main(String[] args) throws IOException {

        // 创建相关流
        FileInputStream fileInputStream = new FileInputStream("D:\\a.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\b.jpg");

        // 获取各个流对应的Channel
        FileChannel sourceCh = fileInputStream.getChannel();
        FileChannel destCh = fileOutputStream.getChannel();

        // 使用transFrom完成拷贝
        destCh.transferFrom(sourceCh, 0, sourceCh.size());

        // 关闭相关通道和流
        sourceCh.close();
        destCh.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
