package com.devplatform.admin.common.utils;

import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.devplatform.admin.modules.sys.bean.SysUserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtUtil {
	
	public static final String JWT_ID = "jwt";
	public static final String JWT_SECRET = "bm9dIzAsNzIreyBQ";
	public static final int JWT_TTL = 20*1000; // 60*60*1000;  //millisecond
	public static final int JWT_REFRESH_INTERVAL = 18*1000; //55*60*1000;  //millisecond
	public static final int JWT_REFRESH_TTL = 60*1000; // 12*60*60*1000;  //millisecond

	/**
	 * 由字符串生成加密key
	 * @return
	 */
	public static SecretKey generalKey(){
		String stringKey = JWT_SECRET;//本地配置文件中加密的密文bm9dIzAsNzIreyBQ
		byte[] encodedKey = Base64.decodeBase64(stringKey);//本地的密码解码[B@67b64c45
		System.out.println(encodedKey);//[B@67b64c45
		System.out.println(Base64.encodeBase64URLSafeString(encodedKey));//bm9dIzAsNzIreyBQ
	    SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");// 根据给定的字节数组使用AES加密算法构造一个密钥，使用 encodedKey中的始于且包含 0 到前 leng 个字节这是当然是所有。
	    return key;
	}
	
	/**
	 * 创建jwt
	 * @param id
	 * @param subject
	 * @param ttlMillis 过期的时间长度
	 * @return
	 * @throws Exception
	 */
	public static String createJWT(String id, String subject, long ttlMillis) throws Exception {
		//指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; 
		long nowMillis = System.currentTimeMillis();//生成JWT的时间
		Date now = new Date(nowMillis);
//		//创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
//		Map<String,Object> claims = new HashMap<String,Object>();
//		claims.put("uid", "DSSFAWDWADAS...");
//		claims.put("user_name", "admin");
//		claims.put("nick_name","DASDA121");
		//生成签名的时候使用的秘钥secret,这个方法本地封装了的，一般可以从本地配置文件中读取，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
		SecretKey key = generalKey();		
		//下面就是在为payload添加各种标准声明和私有声明了
		JwtBuilder builder = Jwts.builder() //这里其实就是new一个JwtBuilder，设置jwt的body
//				.setClaims(claims) 			//如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
				.setId(id)					//设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
				.setIssuedAt(now)			//iat: jwt的签发时间
				.setSubject(subject)		//sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
			    .signWith(signatureAlgorithm, key);//设置签名使用的签名算法和签名使用的秘钥
		if (ttlMillis >= 0) {
		    long expMillis = nowMillis + ttlMillis;
		    Date exp = new Date(expMillis);
		    builder.setExpiration(exp);		//设置过期时间
		}
		return builder.compact();			//就开始压缩为xxxxxxxxxxxxxx.xxxxxxxxxxxxxxx.xxxxxxxxxxxxx这样的jwt
		//打印了一哈哈确实是下面的这个样子
		//eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiJEU1NGQVdEV0FEQVMuLi4iLCJzdWIiOiIiLCJ1c2VyX25hbWUiOiJhZG1pbiIsIm5pY2tfbmFtZSI6IkRBU0RBMTIxIiwiZXhwIjoxNTE3ODI4MDE4LCJpYXQiOjE1MTc4Mjc5NTgsImp0aSI6Imp3dCJ9.xjIvBbdPbEMBMurmwW6IzBkS3MPwicbqQa2Y5hjHSyo
	}
//	public static void main(String[] args) throws Exception {
//		JwtUtil util=	new JwtUtil();
//		String ab=util.createJWT("jwt", "{id:100,name:xiaohong}", 600000);
//		System.out.println(ab);
//		//eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiJEU1NGQVdEV0FEQVMuLi4iLCJzdWIiOiJ7aWQ6MTAwLG5hbWU6eGlhb2hvbmd9IiwidXNlcl9uYW1lIjoiYWRtaW4iLCJuaWNrX25hbWUiOiJEQVNEQTEyMSIsImV4cCI6MTUxNzgzNTE0NiwiaWF0IjoxNTE3ODM1MDg2LCJqdGkiOiJqd3QifQ.ncVrqdXeiCfrB9v6BulDRWUDDdROB7f-_Hg5N0po980
////		String jwt="eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiJEU1NGQVdEV0FEQVMuLi4iLCJzdWIiOiJ7aWQ6MTAwLG5hbWU6eGlhb2hvbmd9IiwidXNlcl9uYW1lIjoiYWRtaW4iLCJuaWNrX25hbWUiOiJEQVNEQTEyMSIsImV4cCI6MTUxNzgzNTEwOSwiaWF0IjoxNTE3ODM1MDQ5LCJqdGkiOiJqd3QifQ.G_ovXAVTlB4WcyD693VxRRjOxa4W5Z-fklOp_iHj3Fg";
//		Claims c=util.parseJWT(ab);//注意：如果jwt已经过期了，这里会抛出jwt过期异常。
//		System.out.println(c.getId());//jwt
//		System.out.println(c.getIssuedAt());//Mon Feb 05 20:50:49 CST 2018
//		System.out.println(c.getSubject());//{id:100,name:xiaohong}
//		System.out.println(c.getIssuer());//null
//		System.out.println(c.get("uid", String.class));//DSSFAWDWADAS...
//	}
	/**
	 * 解密jwt
	 * @param jwt
	 * @return
	 * @throws Exception
	 */
	public static Claims parseJWT(String jwt) throws SignatureException{
		SecretKey key = generalKey();  //签名秘钥，和生成的签名的秘钥一模一样
		Claims claims = Jwts.parser()  //得到DefaultJwtParser
		   .setSigningKey(key)         //设置签名的秘钥
		   .parseClaimsJws(jwt).getBody();//设置需要解析的jwt
		return claims;
	}
	
	/**
	 * 生成subject信息
	 * @param user
	 * @return
	 */
	public static String generalSubject(SysUserEntity user){
		JSONObject jo = new JSONObject();
		jo.put("userId", user.getUserId());
		jo.put("roleId", JSON.toJSONString(user.getRoleIdList()));
		return jo.toJSONString();
	}
}
