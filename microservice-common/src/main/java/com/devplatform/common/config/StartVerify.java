package com.devplatform.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.devplatform.common.base.exception.RRException;
import com.devplatform.common.util.license.LicenseAuth;

@Component
public class StartVerify  implements CommandLineRunner{

	@Value("${environment}")
	private String environment;
	
	@Override
	public void run(String... args) throws Exception {
		String boo = LicenseAuth.authLicense(environment);//校验license
		if(!"YES".equals(boo)){//失败则停止系统运行
			ShutdownContext.showdown();
			throw new RRException(boo);
		}
	}
	
}
