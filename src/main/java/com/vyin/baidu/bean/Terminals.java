package com.vyin.baidu.bean;

import java.io.Serializable;

import lombok.Data;

/**
* @ author liu qihang:
* @version 创建时间 2018年6月25日上午10:56:16
* 
*/
@Data
public class Terminals implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer termId; 
	private String tMacId; 
	private Integer osType; 
	private String osVersion; 
	private String vendor; 
	private String model; 
	private Integer screenWidth; 
	private Integer screenHeight; 
	private String ipv4; 
	private Integer connectionType; 
	private Integer operatorType;
	
	
	


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Terminals other = (Terminals) obj;
		if (connectionType == null) {
			if (other.connectionType != null)
				return false;
		} else if (!connectionType.equals(other.connectionType))
			return false;
		if (ipv4 == null) {
			if (other.ipv4 != null)
				return false;
		} else if (!ipv4.equals(other.ipv4))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (operatorType == null) {
			if (other.operatorType != null)
				return false;
		} else if (!operatorType.equals(other.operatorType))
			return false;
		if (osType == null) {
			if (other.osType != null)
				return false;
		} else if (!osType.equals(other.osType))
			return false;
		if (osVersion == null) {
			if (other.osVersion != null)
				return false;
		} else if (!osVersion.equals(other.osVersion))
			return false;
		if (screenHeight == null) {
			if (other.screenHeight != null)
				return false;
		} else if (!screenHeight.equals(other.screenHeight))
			return false;
		if (screenWidth == null) {
			if (other.screenWidth != null)
				return false;
		} else if (!screenWidth.equals(other.screenWidth))
			return false;
		if (tMacId == null) {
			if (other.tMacId != null)
				return false;
		} else if (!tMacId.equals(other.tMacId))
			return false;
		if (termId == null) {
			if (other.termId != null)
				return false;
		} else if (!termId.equals(other.termId))
			return false;
		if (vendor == null) {
			if (other.vendor != null)
				return false;
		} else if (!vendor.equals(other.vendor))
			return false;
		return true;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((connectionType == null) ? 0 : connectionType.hashCode());
		result = prime * result + ((ipv4 == null) ? 0 : ipv4.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((operatorType == null) ? 0 : operatorType.hashCode());
		result = prime * result + ((osType == null) ? 0 : osType.hashCode());
		result = prime * result + ((osVersion == null) ? 0 : osVersion.hashCode());
		result = prime * result + ((screenHeight == null) ? 0 : screenHeight.hashCode());
		result = prime * result + ((screenWidth == null) ? 0 : screenWidth.hashCode());
		result = prime * result + ((tMacId == null) ? 0 : tMacId.hashCode());
		result = prime * result + ((termId == null) ? 0 : termId.hashCode());
		result = prime * result + ((vendor == null) ? 0 : vendor.hashCode());
		return result;
	}

}
