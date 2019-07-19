package com.vyin.baidu.utils;

import java.io.IOException;

/**
* @ author liu qihang:
* @version 创建时间 2018年6月11日下午2:37:42
* 
*/
public class ProtofulCompile {
	
	public static void main(String[] args) {
		String protoFile = "juping.proto";//
		String strCmd = "protoc --java_out=./src/main/java ./proto/"
				+ protoFile;
		/*String strCmd = "C:/Users/Administrator/Downloads/protobuf-master/src/protoc-2.5.0.exe -I=./proto --java_out=./src/main/java ./proto/"
				+ protoFile;*/
		try {
			Runtime.getRuntime().exec(strCmd);
		} catch (IOException e) {
			e.printStackTrace();
		} // 通过执行cmd命令调用protoc.exe程序
	}

}
