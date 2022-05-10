package com.api.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
public class ZipTool {
    File f0; //被压缩的目标
    File f1;    //压缩后的目录及文件名

    public ZipTool(File f0, File f1) {
        this.f0 = f0;
        this.f1 = f1;
        start();
    }

    public void start() {
        try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(f1))) {
            File[] flist = f0.listFiles();//对输入目录进行查看
            for (File o : flist) {
                writeFile(o, out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(File f, ZipOutputStream out) {
        try {
            if (judgeFile(f)) {
                File[] flists = f.listFiles();//判断输入的文件夹是否有文件
                if (flists.length == 0) {
                    String s = f.toString().substring(3);
                    out.putNextEntry(new ZipEntry(s + " " + "/"));//空文件仅仅创建文件夹
                } else {//非空文件则遍历后创建文件
                    for (File oo : flists) {
                        writeFile(oo, out); //递归方法
                    }

                }
            } else {
                InputStream in = new BufferedInputStream(new FileInputStream(f));
                int len;
                byte[] b = new byte[8192];
                String s = f.toString().substring(3);
                out.putNextEntry(new ZipEntry(s));
                while ((len = in.read(b)) != -1) {
                    out.write(b, 0, len);
                }
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//写文件

    public boolean judgeFile(File f) {
        if (f.isDirectory()) {
            return true;//是文件夹就返回
        } else {
            return false;//不是就不返回
        }
    }//对文件夹处理

    /**
     * 将文件打包成ZIP压缩文件,main方法测试
     * @param args
     */
    public static void main(String[] args) throws FileNotFoundException {
        String sourceFilePath = "D:\\project\\view\\script";
        String zipFilePath = "D:\\project\\view\\script\\src\\main\\resources\\download";
        File f0 = new File(sourceFilePath);
        File f1 = new File(zipFilePath);


//
//        String projectURL = ResourceUtils.getURL("classpath:").getPath();
//
//        System.out.println(str);
    }
}