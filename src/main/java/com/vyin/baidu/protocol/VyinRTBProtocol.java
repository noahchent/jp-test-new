package com.vyin.baidu.protocol;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.vyin.baidu.rtb.vo.Device;
import com.vyin.baidu.rtb.vo.Juping;
import com.vyin.baidu.rtb.vo.Network;
import com.vyin.baidu.rtb.vo.Size;
import com.vyin.baidu.rtb.vo.SlotInfo;
import com.vyin.baidu.rtb.vo.TsApiResponse;
import com.vyin.baidu.rtb.vo.UdId;
import com.vyin.baidu.rtb.vo.Version;
import com.vyin.baidu.rtb.vo.VyinApiRequest;
import com.vyin.baidu.utils.ByteStringConvertor;
import com.vyin.baidu.utils.JuPingUtil;

/**
 * @ author liu qihang:
 * @version 创建时间 2018年5月9日下午2:25:49
 */
public class VyinRTBProtocol {

	public static Juping.TsApiRequest serverPack(VyinApiRequest vyinApiRequest) {

		Juping.TsApiRequest.Builder tsApiRequestBuilder = Juping.TsApiRequest.newBuilder();

		tsApiRequestBuilder.setRequestId(ByteStringConvertor.getByteString(vyinApiRequest.requestId));
		tsApiRequestBuilder.setApiVersion(vyinApiRequest.version.getVersion());
		tsApiRequestBuilder.setAppId(ByteStringConvertor.getByteString(vyinApiRequest.appId));
		tsApiRequestBuilder.setSlot(vyinApiRequest.slotInfo.getSlotInfo());
		tsApiRequestBuilder.setNetwork(vyinApiRequest.network.getNetWork());
		tsApiRequestBuilder.setDevice(vyinApiRequest.device.getDevice());
		return tsApiRequestBuilder.build();
	}
	
	public static VyinApiRequest serverUnPack(InputStream inputStream) throws IOException {

		Juping.TsApiRequest tsApiRequest = Juping.TsApiRequest.parseFrom(inputStream);
		
		VyinApiRequest vyinApiRequest = new VyinApiRequest();
		vyinApiRequest.requestId = ByteStringConvertor.getString(tsApiRequest.getRequestId());
		vyinApiRequest.version = unpackVersion(tsApiRequest.getApiVersion());
		vyinApiRequest.appId = ByteStringConvertor.getString(tsApiRequest.getAppId());
		vyinApiRequest.slotInfo = unpackSlotInfo(tsApiRequest.getSlot());
		vyinApiRequest.device = unpackDevice(tsApiRequest.getDevice());
		vyinApiRequest.network = unpackNetwork(tsApiRequest.getNetwork());
		return vyinApiRequest;
	}
	
	public static Version unpackVersion(Juping.Version j_version){
		Version version = new Version();
		version.major = j_version.getMajor();
		version.minor = j_version.getMinor();
		version.micro = j_version.getMicro();
		return version;
	}
	
	public static SlotInfo unpackSlotInfo(Juping.SlotInfo j_slotInfo){
		SlotInfo slotInfo = new SlotInfo();
		slotInfo.adslot_id = ByteStringConvertor.getString(j_slotInfo.getAdslotId());
		slotInfo.base_price = j_slotInfo.getBasePrice();
		slotInfo.multi_show = j_slotInfo.getMultiShow();
		return slotInfo;
	}
	
	public static Device unpackDevice(Juping.Device j_device){
		Device device = new Device();
		device.udId = unpackUdId(j_device.getUdid());
		device.osType = j_device.getOsType().getNumber();
		device.os_version = unpackVersion(j_device.getOsVersion());
		device.vendor = ByteStringConvertor.getString(j_device.getVendor());
		device.model = ByteStringConvertor.getString(j_device.getModel());
		device.screen_size = unpackSize(j_device.getScreenSize());
		return device;
	}
	
	public static UdId unpackUdId(Juping.UdId j_udId){
		UdId udId = new UdId();
		udId.UdIdType = j_udId.getIdType().getNumber();
		udId.id = ByteStringConvertor.getString(j_udId.getId());
		return udId;
	}
	
	public static Size unpackSize(Juping.Size j_size){
		Size size = new Size();
		size.width = j_size.getWidth();
		size.height = j_size.getHeight();
		return size;
	}
	
	public static Network unpackNetwork(Juping.Network j_network){
		Network network = new Network();
		network.ipv4 = ByteStringConvertor.getString(j_network.getIpv4());
		network.connection_type = j_network.getConnectionType().getNumber();
		network.operator_type = j_network.getOperatorType().getNumber();
		return network;
	}

	
	/**
	 * 测试
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
//		System.out.println(JuPingUtil.generateRequestId());
		for (int i = 0; i < 1; i++) {
			test();
		}
	}
	
	public static void test(){
		Version version = new Version();
		version.major = 6;
		version.minor = 0;
		version.micro = 0;

		SlotInfo slotInfo = new SlotInfo();
		slotInfo.adslot_id = "5844685";
//		slotInfo.base_price = 90;

		UdId udId = new UdId();
		udId.UdIdType = 1;
		udId.id = "00:1C:11:E5:54:CD";

		Size screen_size = new Size();//非必填
//		screen_size.width = 1920;
//		screen_size.height = 1080;

		Device device = new Device();
		device.udId = udId;
		device.osType = 3;
		device.os_version = version;
		device.vendor = "";
		device.model = "";
		device.screen_size = screen_size;

		Network network = new Network();
		network.ipv4 = "223.64.152.69";
		network.connection_type = 0; // MOBILE_4G;
		network.operator_type = 0; // ISP_CHINA_TELECOM;

		VyinApiRequest vyinApiRequest = new VyinApiRequest();
		vyinApiRequest.requestId = JuPingUtil.generateRequestId();
		vyinApiRequest.version = version;
		vyinApiRequest.appId = "c03dd227";
		vyinApiRequest.slotInfo = slotInfo;
		vyinApiRequest.device = device;
		vyinApiRequest.network = network;

		Juping.TsApiRequest tsApiRequest = serverPack(vyinApiRequest);
		

		System.out.println("before :" + tsApiRequest.toString());
		
		/*byte[] bytes = tsApiRequest.toByteArray();
		for (int i = 0; i < bytes.length; i++) {
			System.out.print(bytes[i]);
		}
		System.out.println("\n" + "bytes长度" + tsApiRequest.toByteString().size());
		
		System.out.println("===== 使用tsApiRequest 反序列化生成对象开始 =====");
		Juping.TsApiRequest tar = null;
		try {
			tar = Juping.TsApiRequest.parseFrom(bytes);
			System.out.print(tar.toString());
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}*/
		
		/*请求*/
		InputStream input = null;
//		String url = "https://jpad.baidu.com/api_6";
		String url = "https://jpaccess.baidu.com/api_6";
		try {
			input = JuPingUtil.sendHTTPSReqToBaidu(tsApiRequest, url);
			byte[] bb = readInputStream(input);
			TsApiResponse tt = TsApiRTBProtocol.serverUnpack(bb);
			System.out.println("聚屏返回信息：" + tt.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}

}
