

package com.vyin.baidu.rtb.vo;

import java.io.Serializable;

/**
* @ author liu qihang:
* @version 创建时间 2018年5月9日下午2:00:23
* 
*/
//设备屏幕宽高 	,二维尺寸信息
public class Size implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int width;	//宽度 单位是像素
	public int height;	//高度 单位是像素
	
	public Juping.Size getSize(){
		Juping.Size.Builder sizeBuilder = Juping.Size.newBuilder();
		sizeBuilder.setWidth(width);
		sizeBuilder.setHeight(height);
		return sizeBuilder.build();
	}
	
	public Size() {
		// TODO Auto-generated constructor stub
	}

	public Size(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}
	
	
}
