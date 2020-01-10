package com.devplatform.common.util;

public class GenericityUtileType {
	
	/**
	 * lock锁定或者Unlock解锁
	 * @author scc
	 *
	 */
	public enum conpc_status_busi_Code {
		conpc_status_lock("锁定", "lock"), conpc_status_Unlock("解锁", "Unlock"), account_other("其他", "other");
	
	public String value;
	
	public String index;
	
	conpc_status_busi_Code(String value, String index) {
		this.value = value;
		this.index = index;
	}
	}
	
	/**
	 * 锁定数据的临时表状态
	 * @author scc
	 *
	 */
	public enum backups_status_busi_Code {
		backups_status_busi_delete("删除", "1"), backups_status_busi_part("拆分", "2"), backups_status_busi_stride("跨期", "3"), backups_status_busi_update("更改", "4"), backups_status_busi_lock("锁定", "5");
	
	public String value;
	
	public String index;
	
	backups_status_busi_Code(String value, String index) {
		this.value = value;
		this.index = index;
	}
	}
	
	/**
	 * 接口返回方法常量规定
	 * @author scc
	 *
	 */
	public enum function_type_final {
		function_type_final_planning("合约规划审批", "setContractFlowData"), function_type_final_lone_plannging("合同/合约审批", "saveOrUpdate");
	
	public String value;
	
	public String index;
	
	function_type_final(String value, String index) {
		this.value = value;
		this.index = index;
	}
	}
	
	public final static String splitChar = "_";
}
