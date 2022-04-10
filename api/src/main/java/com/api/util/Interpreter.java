package com.api.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Interpreter {
    //      python解析器的路径
  private static String interpreterPath = "F:\\anaconda\\envs\\project\\python.exe";


  public String RunScript(String path, String url, String key, String value) {

        try {
            String[] args = new String[]{"python", path, url, key, value};
            Process pr = Runtime.getRuntime().exec(args);

            //用输入输出流来截取结果
            //InputStreamReader类是从字节流到字符流的桥接器：
            //它使用指定的字符集读取字节并将它们解 码为字符
            //BufferedReader 中只有用GBK和GB2312才能解决乱码
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(pr.getInputStream(), "GBK"));
            //接受错误流
            BufferedReader isError = new BufferedReader(
                    new InputStreamReader(pr.getErrorStream(),"GBK")
            );
            //  String result = new String(
            //          doReader(in).getBytes("ISO-8859-1"), "UTF-8"
            //  );
//            String error = doReader(in);
             String result = doReader(in);

            in.close();
            isError.close();
            //InterruptedException
            pr.waitFor();
            return result;
        }
        catch(IOException e){
            return "执行程序异常";
        }catch (InterruptedException e){
            return "进程中断异常";
        }catch (Exception e){
            return "未知异常";
        }
  }


  //获取缓冲对象存储的字符串结果
  private String doReader(BufferedReader br) throws IOException {
      String line = null;
      String result = "";
      while ((line = br.readLine()) != null) {
          System.out.println(line);
          result += line;
      }
      return result;
  }


}
