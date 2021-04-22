package com.atguigu.compress;

import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;

import java.io.*;

public class TestCompress {

    public static void main(String[] args) throws IOException {
        //压缩方法
//        compress("D:\\input\\word.txt","org.apache.hadoop.io.compress.BZip2Codec");
        //解压方法
        decompress("D:\\input\\word.txt.bz2","org.apache.hadoop.io.compress.BZip2Codec");
    }

    private static void decompress(String path, String method) throws IOException {
        //获取编码器工厂
        CompressionCodecFactory codecFactory = new CompressionCodecFactory(new Configuration());
        //获取压缩编码器
        CompressionCodec codec = codecFactory.getCodecByName(method);
        //开启普通输入流
        FileInputStream fis  = new FileInputStream(path);
        //开启压缩输入流
        CompressionInputStream decompress = codec.createInputStream(fis);
        //开启普通输出流
        FileOutputStream fos = new FileOutputStream("D:\\input\\word.txt");
        //流的对拷
        IOUtils.copyBytes(decompress, fos, new Configuration());
        //关闭流
        IOUtils.closeStreams(fos,decompress,fis);
    }

    private static void compress(String fileName, String method) throws IOException {
        Configuration conf = new Configuration();
        //获取编码器工厂
        CompressionCodecFactory codecFactory = new CompressionCodecFactory(conf);
        //根据对应压缩方法的类,获取压缩编码器
        CompressionCodec codec = codecFactory.getCodecByName(method);
        //开启普通输入流
        FileInputStream fis = new FileInputStream(fileName);
        //开启普松输出流
        FileOutputStream fos = new FileOutputStream(new File(fileName+codec.getDefaultExtension()));
        //开启压缩输出流
        CompressionOutputStream compress = codec.createOutputStream(fos);
        //流的对拷
        IOUtils.copyBytes(fis, compress, conf);
        //关闭资源
        IOUtils.closeStreams(compress,fos, fis);
    }
}
