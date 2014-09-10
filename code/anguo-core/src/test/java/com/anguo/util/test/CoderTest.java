package com.anguo.util.test;

import junit.framework.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.anguo.util.AnguoEncryptUtil;


/**
 * 
 * @author 梁栋
 * @version 1.0
 * @since 1.0
 */
public class CoderTest {

    
    private AnguoEncryptUtil rSACoder;
  
    @BeforeTest  
    public void setUp() throws Exception {  
//        Map<String, Object> keyMap = RSACoder.initKey();  
//  
//        publicKey = RSACoder.getPublicKey(keyMap);  
//        privateKey = RSACoder.getPrivateKey(keyMap);  
//        System.err.println("公钥: \n\r" + publicKey);  
//        System.err.println("私钥： \n\r" + privateKey);  
    	
    	rSACoder=new AnguoEncryptUtil();
    	System.out.println("载入密钥对");
        
        rSACoder.loadPublicKey("D:/ssh/rsa_public_key.pem");
        
        rSACoder.loadPrivateKey("D:/ssh/rsa_private_key.pem");
    }  
  
    @Test  
    public void test() throws Exception {  
    	
    	
        System.err.println("公钥加密——私钥解密");  
        String inputStr = "abc";  
        byte[] data = inputStr.getBytes();  
        
  
        byte[] encodedData = AnguoEncryptUtil.encryptByPublicKey(data, rSACoder.getPublicKey());  
  
        byte[] decodedData = AnguoEncryptUtil.decryptByPrivateKey(encodedData,rSACoder.getPrivateKey());  
  
        String outputStr = new String(decodedData);  
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);  
        Assert.assertEquals(inputStr, outputStr);  
  
    }  
  
    @Test  
    public void testSign() throws Exception {  
        System.err.println("私钥加密——公钥解密");  
        String inputStr = "sign";  
        byte[] data = inputStr.getBytes();  
  
        byte[] encodedData = AnguoEncryptUtil.encryptByPrivateKey(data, rSACoder.getPrivateKey());  
  
        byte[] decodedData = AnguoEncryptUtil.decryptByPublicKey(encodedData, rSACoder.getPublicKey());  
  
        String outputStr = new String(decodedData);  
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);  
        Assert.assertEquals(inputStr, outputStr);  
  
        System.err.println("私钥签名——公钥验证签名");  
        // 产生签名  
        String sign = AnguoEncryptUtil.sign(decodedData, rSACoder.getPrivateKey());  
        
        System.err.println("签名:\r" + sign);  
  
        // 验证签名  
        boolean status = AnguoEncryptUtil.verify(decodedData, rSACoder.getPublicKey(), sign);  
        System.err.println("状态:\r" + status);  
        Assert.assertEquals(true, status);  
  
    }  
    
    
    @Test  
    public void test2() throws Exception {  
    	   String DEFAULT_PUBLIC_KEY=
    			        "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDveyqk24CbH/mAgEbUD03pVYQ1"+"\r"
    					+"pTbhZf+9hnVJMtVfYEHf33i53Myq/qVkPsAI6/6iCHgOXhR1RGRmCXtZnTELoPVn"+"\r"
    					+"K3nRQJVFpeVmH3s4i6q6BXaTt6i0RTfWQzeP9SgUtfSPzoAxoGM4DfRw3U5wYvzH"+"\r"
    					+"v60/Mvd4IvWafQeYvQIDAQAB"+"\r";
    	   
    	   String DEFAULT_PRIVATE_KEY=
    			"MIICXAIBAAKBgQDveyqk24CbH/mAgEbUD03pVYQ1pTbhZf+9hnVJMtVfYEHf33i5"+"\r"
    			+"3Myq/qVkPsAI6/6iCHgOXhR1RGRmCXtZnTELoPVnK3nRQJVFpeVmH3s4i6q6BXaT"+"\r"
    			+"t6i0RTfWQzeP9SgUtfSPzoAxoGM4DfRw3U5wYvzHv60/Mvd4IvWafQeYvQIDAQAB"+"\r"
    			+"AoGAO5hl+1KYhYIGgADsH1eTpu5eEU+FAcB1TP/J7iZVTP/SRNkC3RXiZOcr1296"+"\r"
    			+"MH4yBrae0cx9wNT9Oxs+9AUXL/dmPnJh36+4Rg4P1aHl65Q0hvuq7UUwCqPXG+AT"+"\r"
    			+"RjIP57htK/E/0dyvMicXfMr1Ip6+gUFcYgy3nChm9phfz+0CQQD6+79KR82d6Amg"+"\r"
    			+"1Rk8yhXMND8eO8t2CzqBeZlDJlmOmBwJhLjOglTcCeDrecAUQgcq+euQUB0Mca4l"+"\r"
    			+"Z48u1iOvAkEA9ESQRwgSNWFP8xOqDPBvBFbE/UT4caB9/SqJDHMgDwj/XvV6IXoN"+"\r"
    			+"29yprg+z3uJJi2ANZZIalUDpxsFXbg0pUwJBANV6zHqiGIL3mzjyCUVrnp7S0d0l"+"\r"
    			+"fyoo4tq+U16KgCKJv09ZVNhSg1umC2o/ZOHWR8KGUZeujQbIqxelvmRYQIECQEC7"+"\r"
    			+"yuCIKwsqdt2cYHx9W25y8FGObajvN3RYSWmbOOvDHqozs+IbToDtwan3T1vJ7GOb"+"\r"
    			+"WPGJ/rF/OnUJ1/m6UzECQGxVPKOcSUsQI1NKTLHvATgGxAdvQNii1b3DjjqNLFDN"+"\r"
    			+"aBtbG4svFr0t7HLkilUQnvex3oJkTsp/VYBxTbSSLVU="+"\r";

    	
        System.err.println("公钥加密——私钥解密");  
        String inputStr = "abc";  
        byte[] data = inputStr.getBytes();  
        
  
        byte[] encodedData = AnguoEncryptUtil.encryptByPublicKey(data, DEFAULT_PUBLIC_KEY);  
  
        byte[] decodedData = AnguoEncryptUtil.decryptByPrivateKey(encodedData,DEFAULT_PRIVATE_KEY);  
  
        String outputStr = new String(decodedData);  
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);  
        Assert.assertEquals(inputStr, outputStr);  
  
    }  
}