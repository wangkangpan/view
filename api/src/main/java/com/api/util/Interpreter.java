package com.api.util;

import com.vo.OutData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class Interpreter {
    //      python解析器的路径
  private static String interpreterPath = "F:\\anaconda\\envs\\project\\python.exe";


  public List<OutData> RunScript(String path, String url, String tag, String key, String value) {

        try {
            String[] args = new String[]{"python", path, url, tag, key, value};
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
            List<OutData> result = doReader(in, tag, key, value);

            in.close();
            isError.close();
            //InterruptedException
            pr.waitFor();
            return result;

        }
        catch(IOException e){
            log.error("IOException:" + e.getMessage());
            return null;
        }catch (InterruptedException e){
            log.error("InterruptedException:" + e.getMessage());
            return null;
        }catch (Exception e){
            log.error("Exception:" + e.getMessage());
            return null;
        }
  }


  //获取缓冲对象存储的字符串结果
  private List<OutData> doReader(BufferedReader br, String tag, String key, String attr){
      List<OutData> result = new ArrayList<>();
      String line;
      try{
          while ((line = br.readLine()) != null) {
              OutData outData = new OutData();
              outData.setContent(line);
              outData.setTag(tag);
              outData.setKey(key);
              outData.setAttr(attr);
              result.add(outData);
          }
      }catch(IOException e){
          log.error("IOException:" + e.getMessage());
      }catch (Exception e){
          log.error("Exception:" + e.getMessage());
      }
      return result;
  }




}
