package com.devplatform.common.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * HTTP状态码
 * <p>
 * 使用方法：
 * </p>
 * <p>
 * 1. 请严格按照状态码范围注释进行添加
 * </p>
 * <p>
 * 2. 添加前先进行搜索，观察你的需求的code在本类中是否存在，是否可用
 * </p>
 * <p>
 * 3. 添加时请根据注释，按照顺序进行书写，不要越界，不要覆盖原有code
 * </p>
 * <p>
 * 4. 范围说明
 * </p>
 * <p>
 * xx1 ~ xx2 代表业务接口状态码范围
 * </p>
 * <p>
 * yy1 ~ yy2 代表yy接口状态码范围
 * </p>
 * <p>
 * 特别说明：以上的范围中有可能存在个别code作为公共状态码，单独拆分到本类的最后一个注释范围中，大家使用的时候留意即可
 * </p>
 * 
 * @author liuzhenkun
 * @Date 2015年02月02日
 */
public class ResponseCode {
	
	public static String OK = "200"; //成功
	
	/**
	 * 余额不足
	 */
	public static final String ERR_CODE_ACCNOTENO_BAL = "001";
	
	/**
	 * 保证金余额不足
	 */
	public static final String ERR_CODE_SECDEPNOTENO_BAL = "002";
	
	/**
	* 参数错误
	*/
	public static final String ERR_CODE_PARAMS_ERROR = "003";
	
	/**
	 * 用户登陆名被冻结三十分钟
	 */
	public static final String USER_ACCOUNT_FROZEN = "004";
	
	/**
	 * 用户已注册
	 */
	public static final String USER_WAS_EXISTS = "008";
	
	/**
	 * 用户不存在
	 */
	public static String USER_NOT_EXISTS = "009";
	
	/**
	 * 实名认证失败
	 */
	public static final String REAL_NAME_AUTHENTICATION_FAILED = "010";
	
	/**
	 * 两次输入的密码不一致
	 */
	public static final String PASSWORDS_DO_NOT_MATCH = "011";
	
	/**
	 * 交易失败
	 */
	public static final String ERR_CODE_TRANS_FAIL = "012";
	
	/**
	 * 用户名不能为空
	 */
	public static final String USERNAME_CAN_NOT_BE_EMPTY = "013";
	
	/**
	 * 用户名不可修改
	 */
	public static final String USERNAME_CAN_NOT_BE_RESET = "014";
	
	/**
	 * 流程处理中
	 */
	public static final String KEEPER_IN_WORKING = "015";
	
	/**
	 * 图形验证码过期
	 */
	public static final String IMG_CODE_TIMEOUT = "016";
	
	/**
	 * 黑名单
	 */
	public static final String ERR_CODE_BLACKLIST = "041";
	
	/**
	 * 银行响应超时
	 */
	public static final String ERR_CODE_BANK_TIMEOUT = "046";
	
	/**
	 * 密码错误
	 */
	public static final String ERR_CODE_PIN_ERROR = "055";
	
	/**
	 * 用户名或密码错误
	 */
	public static final String ERR_CODE_USERNAME_PASSWORD_ERROR = "056";
	
	/**
	 * 用户被强制登出
	 */
	public static final String FORCE_LOGOUT = "057";
	
	/**
	 * 交易金额超限
	 */
	public static final String ERR_CODE_MONEY_OVERRUN = "061";
	
	/**
	 * 超过交易限制
	 */
	public static final String ERR_CODE_TRANS_OVERRUN = "062";
	
	/**
	 * 信息推送失败
	 */
	public static final String MESSAGE_PUSH_FAILED = "063";
	
	/**
	 * 模板修改失败
	 */
	public static final String MESSAGE_TEMPLATE_UPDATE_FAILED = "064";
	
	/**
	 * 一小时内只能获取五次短信验证码
	 */
	public static final String MESSAGE_CODE_LOCKED = "065";
	
	/**
	 * 手机号格式校验失败
	 */
	public static final String PHONE_IS_MALFORMED = "066";
	
	/**
	 * 账户权限不足
	 */
	public static final String INSUFFICIENT_ACCOUNT_PERMISSIONS = "067";
	
	/**
	 * 系统异常
	 */
	public static final String ERR_CODE_SYS_EXCEPTION = "096";
	
	/**
	 * 权限不足
	 */
	public static final String NO_FONT_PERMISSION = "090";
	
	/**
	 * 用户没有通过风险评估
	 */
	public static final String NO_RISK_ASSESSMENT = "091";
	
	/**
	 * 无效的认购记录
	 */
	public static final String ERR_CODE_INVALID_APPLAY = "101";
	
	/**
	 * 无效的请求参数
	 */
	public static String PARAM_INVALID = "102";
	
	/**
	 * 用户名已存在
	 */
	public static String USER_NAME_EXISTS = "103";
	
	/**
	 * 用户已禁用
	 */
	public static String USER_IS_NOT_ENABLED = "104";
	
	/**
	 * 用户未登录
	 */
	public static String USER_IS_NOT_LOGIN = "105";
	
	/**
	 * 用户未开户
	 */
	public static String USER_IS_NOT_OPEN_ACCOUNT = "106";
	
	/**
	 * 用户未成年
	 */
	public static String USER_IS_UNDER_AGE = "107";
	
	/**
	 * 邮箱非法篡改修改
	 */
	public static String EMAIL_CHANGE_ILLEGAL = "108";
	
	/**
	 * 邮箱已存在
	 */
	public static final String EMAIL_HAD_EXISTS = "109";
	
	/**
	 * 手机号码已存在
	 */
	public static final String PHONE_HAD_EXISTS = "110";
	
	/**
	 * 图片验证码输入错误
	 */
	public static String IMG_CODE_CHECK_ERROR = "401";
	
	/**
	 * 短信验证码输入错误
	 */
	public static String MESSAGE_CODE_CHECK_ERROR = "402";
	
	/**
	 * 短信验证码输入错误
	 */
	public static final String MESSAGE_CODE_EXPIRED = "406";
	
	/**
	 * http请求地址无资质
	 */
	public static final String NO_PERMISSION = "403";
	
	/**
	 * 未知错误
	 */
	public static final String ERR_CODE_UNKNOW_ERROR = "EE";
	
	/**
	 * 本地错误
	 */
	public static final String ERR_CODE_LOCAL_ERROR = "LE";
	
	/**
	 * 准备错误
	 */
	public static final String ERR_CODE_READY_ERROR = "RE";
	
	/**
	 * 线上没有启用的客户端版本
	 */
	public static final String ERR_CODE_CLIENTVERSION_NOTFOUND = "C00";
	
	/**
	 * 本地客户端版本太旧
	 */
	public static final String ERR_CODE_CLIENTVERSION_LOCALOLD = "C01";
	
	/**
	 * 债转折价比例设置不合法
	 */
	public static final String ERR_CODE_SHARE_TRANSFER_DISCOUNT = "ST00";
	
	/**
	 * 项目债转次数超过限制
	 */
	public static final String ERR_CODE_SHARE_TRANSFER_PREJECT_TIMES = "ST01";
	
	/**
	 * 项目不满足债转需求
	 */
	public static final String ERR_CODE_SHARE_TRANSFER_PROJECT = "ST02";
	
	/**
	 * 债转申请部分结束失败
	 */
	public static final String ERR_CODE_SHARE_TRANSFER_NOTCLOSE = "ST03";
	
	/**
	 * 债转申请不满足结束条件
	 */
	public static final String ERR_CODE_SHARE_TRANSFER_CLOSE_UNNATURAL = "ST04";
	
	/**
	 * 债转申请不能在23:00至24:00时间段
	 */
	public static final String ERR_CODE_SHARE_TRANSFER_IME_BAR = "ST05";
	
	/**
	 * 债转金额最低值不符合
	 */
	public static final String SHARE_TRANSFER_MIN_MONEY = "ST06";
	
	/**
	 * 当前标的产品不允许债转
	 */
	public static final String ERR_CODE_SHARE_TRANSFER_IS_TRANSFER = "ST07";
	
	/**
	 * 下一还款日之前多少天不可以还款
	 */
	public static final String ERR_CODE_SHARE_TRANSFER_MAX_DAY = "ST08";
	
	/**
	 * 项目预期不可债转
	 */
	public static final String ERR_CODE_SHARE_TRANSFER_HOLD_DAY = "ST09";
	
	/**
	 * 项目预期不可债转
	 */
	public static final String ERR_CODE_SHARE_TRANSFER_REPETITION = "ST10";
	
	/**
	 * 债权余额不足
	 */
	public static final String ERR_CODE_TANSFERD_MONEY_OVERRUN = "ST11";
	
	/**
	 * 债权转让已结束
	 */
	public static final String ERR_CODE_TANSFERD_END = "ST12";
	
	/**
	 * 用户权限不足
	 */
	public static final String ERR_REQ_GOTO_REDIRECT = "8848";
	
	public static Map<String, String> codeMap = new ConcurrentHashMap<String, String>();
	static {
		codeMap.put(ERR_CODE_READY_ERROR, "准备错误");
		codeMap.put(ERR_CODE_LOCAL_ERROR, "本地错误");
		codeMap.put(ERR_CODE_UNKNOW_ERROR, "未知错误");
		codeMap.put(IMG_CODE_CHECK_ERROR, "图形验证码输入有误，请重新输入");
		codeMap.put(MESSAGE_CODE_CHECK_ERROR, "短信验证码错误");
		codeMap.put(USER_IS_NOT_LOGIN, "用户未登录");
		codeMap.put(USER_IS_NOT_ENABLED, "用户已禁用");
		codeMap.put(USER_NAME_EXISTS, "用户名已存在");
		codeMap.put(PARAM_INVALID, "无效的请求参数/参数不能为空");
		codeMap.put(ERR_CODE_INVALID_APPLAY, "无效的认购记录");
		codeMap.put(ERR_CODE_SYS_EXCEPTION, "系统异常");
		codeMap.put(ERR_CODE_TRANS_OVERRUN, "超过交易限制");
		codeMap.put(ERR_CODE_MONEY_OVERRUN, "交易金额超限");
		codeMap.put(ERR_CODE_PIN_ERROR, "密码错误");
		codeMap.put(ERR_CODE_BANK_TIMEOUT, "银行响应超时");
		codeMap.put(ERR_CODE_TRANS_FAIL, "交易失败");
		codeMap.put(ERR_CODE_PARAMS_ERROR, "参数错误");
		codeMap.put(USER_NOT_EXISTS, "用户不存在");
		codeMap.put(NO_FONT_PERMISSION, "权限不足");
		codeMap.put(NO_PERMISSION, "请求地址无效");
		codeMap.put(OK, "成功");
		codeMap.put(USER_IS_NOT_OPEN_ACCOUNT, "用户未开户");
		codeMap.put(USER_IS_UNDER_AGE, "用户未成年");
		codeMap.put(EMAIL_CHANGE_ILLEGAL, "认证邮箱不可修改");
		codeMap.put(ERR_CODE_USERNAME_PASSWORD_ERROR, "用户名或密码错误");
		codeMap.put(USER_WAS_EXISTS, "用户已注册");
		codeMap.put(MESSAGE_CODE_EXPIRED, "短信验证码已过期");
		codeMap.put(REAL_NAME_AUTHENTICATION_FAILED, "实名认证失败");
		codeMap.put(USER_ACCOUNT_FROZEN, "用户账户已被冻结30分钟");
		codeMap.put(FORCE_LOGOUT, "账户被冻结,请三十分钟后重新登录");
		codeMap.put(ERR_CODE_CLIENTVERSION_NOTFOUND, "服务器中没有启用的版本");
		codeMap.put(ERR_CODE_CLIENTVERSION_LOCALOLD, "发现新版本");
		codeMap.put(PASSWORDS_DO_NOT_MATCH, "两次输入的密码不一致");
		codeMap.put(USERNAME_CAN_NOT_BE_EMPTY, "登陆用户名不能为空");
		codeMap.put(MESSAGE_PUSH_FAILED, "信息推送失败");
		codeMap.put(USERNAME_CAN_NOT_BE_RESET, "用户名不可修改");
		codeMap.put(MESSAGE_TEMPLATE_UPDATE_FAILED, "模板修改失败");
		codeMap.put(MESSAGE_CODE_LOCKED, "短信验证码获取次数过多,请半小时后重试");
		codeMap.put(PHONE_IS_MALFORMED, "暂不支持该号段");
		codeMap.put(INSUFFICIENT_ACCOUNT_PERMISSIONS, "账户权限不足");
		codeMap.put(ERR_CODE_SHARE_TRANSFER_DISCOUNT, "债转折价比例设置不合法");
		codeMap.put(ERR_CODE_SHARE_TRANSFER_PREJECT_TIMES, "项目债转次数超过限制");
		codeMap.put(ERR_CODE_SHARE_TRANSFER_PROJECT, "项目不满足债转需求");
		codeMap.put(ERR_CODE_SHARE_TRANSFER_NOTCLOSE, "一部分债转申请结束出现异常");
		codeMap.put(ERR_CODE_SHARE_TRANSFER_CLOSE_UNNATURAL, "债转申请不满足结束条件");
		codeMap.put(ERR_CODE_SHARE_TRANSFER_IS_TRANSFER, "标地不允许债转");
		codeMap.put(SHARE_TRANSFER_MIN_MONEY, "债转金额最低值不符合");
		codeMap.put(ERR_CODE_SHARE_TRANSFER_IME_BAR, "债转申请不能在23:00至24:00时间段");
		codeMap.put(ERR_CODE_SHARE_TRANSFER_HOLD_DAY, "项目预期不可债转");
		codeMap.put(ERR_CODE_SHARE_TRANSFER_REPETITION, "项目已经出让不可重复债转");
		codeMap.put(ERR_CODE_TANSFERD_MONEY_OVERRUN, "所购债权余额不足");
		codeMap.put(ERR_CODE_TANSFERD_END, "债转已结束");
		codeMap.put(EMAIL_HAD_EXISTS, "邮箱已存在");
		codeMap.put(PHONE_HAD_EXISTS, "手机号已存在");
		codeMap.put(ERR_REQ_GOTO_REDIRECT, "权限不足");
		codeMap.put(NO_RISK_ASSESSMENT, "用户未通过风险评估");
		codeMap.put(KEEPER_IN_WORKING, "流程处理中");
		codeMap.put(ERR_CODE_ACCNOTENO_BAL, "余额不足");
		codeMap.put(IMG_CODE_TIMEOUT, "图形验证码过期,请重新验证");
	}
}
