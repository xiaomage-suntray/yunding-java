package com.devplatform.common.util.license;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ClassUtils;

import sun.misc.BASE64Encoder;

import com.devplatform.common.util.StringUtil;

import java.io.*;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.*;


public class LicenseAuth {
	public static BASE64Encoder encode=new BASE64Encoder();

    /**
     * 获取服务器相关信息组成机器码
     * @return
     * @throws Exception
     */
    public static String getMachineCode(String environment) throws Exception{
        Set<String> result = new HashSet<>();
        String mac = null;
		if("docker".equals(environment)){
        	mac = getMac_docker();
        }else{
        	mac = getMac();
        }

        if(null==mac || "".equals(mac)){
        	throw new Exception("获取机器码异常");
        }		
        result.add(mac);
        Properties props = System.getProperties();
        String javaVersion = props.getProperty("java.version");
        result.add(javaVersion);
        String javaVMVersion = props.getProperty("java.vm.version");
        result.add(javaVMVersion);
        String osVersion = props.getProperty("os.version");
        result.add(osVersion);
        String machineCode = result.toString().replace(" ", "").trim();
        String code = Encrpt.GetMD5Code(machineCode.replace("\n", ""));
        return getSplitString(code, "-", 4);
    	
    	
    }


    private static HashMap<String, String> genDataFromArrayByte(byte[] b) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(new ByteArrayInputStream(b)));
        HashMap<String, String> data = new HashMap<String, String>();
        String str =  null;
        while((str = br.readLine()) != null){
            if(StringUtil.checkNotNull(str)){
                str = str.trim();
                int pos = str.indexOf("=");
                if(pos <= 0 ) continue;
                if(str.length() > pos + 1){
                    data.put(str.substring(0, pos).trim().toUpperCase(), str.substring( pos + 1).trim()) ;
                }else{
                    data.put(str.substring(0, pos).trim().toUpperCase(), "") ;
                }
            }
        }
        return data;
    }


    private static String getSplitString(String str, String split, int length) {
        int len = str.length();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (i % length == 0 && i > 0) {
                temp.append(split);
            }
            temp.append(str.charAt(i));
        }
        String[] attrs = temp.toString().split(split);
        StringBuilder finalMachineCode = new StringBuilder();
        for (String attr : attrs) {
            if (attr.length() == length) {
                finalMachineCode.append(attr).append(split);
            }
        }
        String result = finalMachineCode.toString().substring(0,
                finalMachineCode.toString().length() - 1);
        return result;
    }

    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    
    /**
     * 获取mac address
     * @return
     */
    private static String getMac() {
        try {
            Enumeration<NetworkInterface> el = NetworkInterface
                    .getNetworkInterfaces();
            while (el.hasMoreElements()) {
                byte[] mac = el.nextElement().getHardwareAddress();
                if (mac == null)
                    continue;
                String hexstr = bytesToHexString(mac);
                return getSplitString(hexstr, "-", 2).toUpperCase();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
    
    private static String getMac_docker() {
    	String output = ""; 
        try {
            File file = new File("/opt/address");
            if(file.exists()){
                if(file.isFile()){
                    try{
                        BufferedReader input = new BufferedReader (new FileReader(file));
                        StringBuffer buffer = new StringBuffer();
                        String text;
                           
                        while((text = input.readLine()) != null)
                            buffer.append(text);
                           
                        output = buffer.toString();                    
                    }
                    catch(IOException ioException){
                        System.err.println("File Error!");
                    }
                }
                else if(file.isDirectory()){
                	System.out.println("***********wodaodizoulemei!**********");
                    String[] dir = file.list();
                    output += "Directory contents:/n";
                    
                    for(int i=0; i<dir.length; i++){
                        output += dir[i];
                    }
                }
            }else{
                System.err.println("Does not exist!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return output;
    }
    
    public static String authLicense(String environment){
    	try (
		        InputStream cerStream = ClassUtils.getDefaultClassLoader().getResourceAsStream("suntray.cer");
		        
		        InputStream licStream = ClassUtils.getDefaultClassLoader().getResourceAsStream("suntray.lic");
    			
    			){
		        
		        if(null!=cerStream&&licStream!=null){
//		        	File readfile = new File("license.lic");
//		        	FileUtils.copyInputStreamToFile(licStream, readfile);
//		        	if (readfile.isFile()) {
		        		String liccontent = FileUtil.readFileToString(licStream);
		        		String decriptliccontent = Encrpt.DecriptWithRSA_Pub(liccontent,cerStream);
		        		HashMap<String, String> props = genDataFromArrayByte(decriptliccontent.getBytes());
		        		String licenseid = props.get("LICENSEID");
		        		String licensename= props.get("LICENSENAME");
		        		String licensetype = props.get("LICENSETYPE");
		        		String liclimit = props.get("EXPIREDAY");
		        		String machinecode = props.get("MACHINECODE");
//		        		System.out.println("license文件中的机器码："+machinecode);
		        		String lincensesign = props.get("LICENSESIGN");
		        		//验证签名
		        		String allinfogroup = "LICENSEID="+licenseid+"txrcsuntray"+"LICENSENAME="+licensename+"txrcsuntray"+
		        				"LICENSETYPE="+licensetype+"txrcsuntray"+"EXPIREDAY="+liclimit+"txrcsuntray"+"MACHINECODE="+machinecode+"txrcsuntraytxrcsuntray";
		        		if (lincensesign.equals(Encrpt.GetMD5Code(allinfogroup))){
		        			//验证机器码
//		        			System.out.println("***************机器码验证开始：");
//                            System.out.println("***************目前环境是："+environment);
//		        			System.out.println("获取到的机器码："+getMachineCode(environment));
//		        			System.out.println("验证结果："+getMachineCode(environment).equals(machinecode));
		        			if (getMachineCode(environment).equals(machinecode)){
		        				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		        				Date bt=new Date();
		        				Date et=sdf.parse(liclimit);
		        				//验证时间
		        				if(bt.compareTo(et)<=0){
		        					return "YES";
		        				}else{
		        					return"系统证书过期,请联系管理员！" ;
		        				}
		        			}else{
		        				return "系统机器码不一致,请联系管理员！";
		        			}
		        		}else{
		        			return "系统签名不一致,请联系管理员！";
		        		}
//		        	}
		        }else{
		        	return "密钥或证书未上传,请联系管理员！";
		        }
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return "证书信息异常,请联系管理员！";
    }
}
