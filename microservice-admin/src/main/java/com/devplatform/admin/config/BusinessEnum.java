
package com.devplatform.admin.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusinessEnum {
    
    /**
     *  流程类枚举
     * 
     * @author lu
     * 
     */
    public static enum ACTIVITIY_TYPE {
        GOODS_DETAILS("goods_details", "商品审批流程"), PURCHASE_REQUEST_ZC("purchase_request_zc", "自行采购申请审批流程"), PURCHASE_REQUEST_JC("purchase_request_jc",
                "集中采购审批"), SUPPLIER_SP("supplier_sp", "供应商审批"), COMPACT_SP_JC("compact_sp_jc", "集采合同审批流程"), COMPACT_SP("compact_sp", "合同审批流程");
    
    public String key;
    
    public String value;
    
    private ACTIVITIY_TYPE(String key, String value) {
        this.key = key;
        this.value = value;
    }
    
    public static ACTIVITIY_TYPE get(String key) {
        ACTIVITIY_TYPE[] values = ACTIVITIY_TYPE.values();
        for (ACTIVITIY_TYPE object : values) {
            if (object.key.equals(key)) {
                return object;
            }
        }
        return null;
    }
    
    }
    
    public static List<Map<String, Object>> getActivitiy_type() {
        
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        ACTIVITIY_TYPE[] values = ACTIVITIY_TYPE.values();
        for (ACTIVITIY_TYPE object : values) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("key", object.key);
            map.put("value", object.value);
            list.add(map);
        }
        return list;
    }
    
    /**
     *  消息 类枚举
     * 
     * @author lu
     * 
     */
    public static enum MESSAGE_TYPE {
        PURCHASE_REQUEST_JC(1, "集中采购审批"), ACCEPT_REQUEST(2, "验收"), COMPACT_SP_JC(3, "集采合同审批"), COMPACT_SP(4,
                "自采合同审批"), SUPPLIER_IMPROVEMENT_INFORMATION(5, "供应商完善信息"), INVITE_EXPERTS(6, "邀请专家评标"), PRESS_MESSAGE(7, "出库审批催办");
    
    public Integer key;
    
    public String value;
    
    private MESSAGE_TYPE(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
    
    public static MESSAGE_TYPE get(String key) {
        MESSAGE_TYPE[] values = MESSAGE_TYPE.values();
        for (MESSAGE_TYPE object : values) {
            if (object.key.equals(key)) {
                return object;
            }
        }
        return null;
    }
    
    }
    
    public static List<Map<String, Object>> getMessage_type() {
        
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        MESSAGE_TYPE[] values = MESSAGE_TYPE.values();
        for (MESSAGE_TYPE object : values) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("key", object.key);
            map.put("value", object.value);
            list.add(map);
        }
        return list;
    }
    
}
