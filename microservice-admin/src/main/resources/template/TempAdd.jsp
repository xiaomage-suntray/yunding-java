<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/view/include.jsp"%>

<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->

<style type="text/css">
	#preview{width:100px;height:80px;overflow:hidden;}
	#imghead {filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);}
	.manage_unit.clearfix.mtb15{width:50%;float:left;margin-bottom:5px;}
        .manage_unit.clearfix.mtb15  label{width:20%;float:left;text-align: center;line-height:42px;}
        .manage_unit.clearfix.mtb15  input{width:60%;float:left}
</style>  
<link rel="stylesheet" href="${msUrl}/css/bcss/bootstraptable/reset.css">
<link rel="stylesheet" href="${msUrl}/css/bcss/bootstraptable/common.css">
<script type="text/javascript">
$(document).ready(function() {
	//初始化校验
	$.validator.setDefaults( {
		ignore : []
	});//如果有display:none的情况，加上这个，会验证display none的必填项
	$("#editForm").validate();
	
	
	#foreach ($col in $columnDatas)
	#if($col.dataType=='java.util.Date')
		laydate({
		    elem: '#$col.columnName', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
		    event: 'focus', //响应事件。如果没有传入event，则按照默认的click
		    format: 'YYYY/MM/DD hh:mm:ss',
		    istime: true,
		    istoday: true,
		    choose: function(datas){ //选择日期完毕的回调
		    	$("#editForm").validate().element($('#$col.columnName'))
		    }
		});
	#end
	#end

});
		$(document).ready(function() {
			 //load数据
			 var loading=layer.load(2);
			$.post("getId.do",{id:'${id}'},function(result){
				if(result.success){
					$("#editForm").fill(result.data); 
					layer.close(loading);
				}else{
					layer.msg(result.msg, {tips:2});
					layer.close(loading);
				}
			},"json").error(function(xhr, status, text){
				if (xhr.status == "504") {
					layer.msg("登录超时，请重新登录", {
						tips : 2
					}, function() {
						location.reload();
					});
				}else{
					layer.msg("系统错误，请稍后再试", {
						tips : 2
					});
				}
			})
		});
	
//编辑
function updateSave(){
	if($("#editForm").valid()){
	var option = {
			dataType:'json',
			success:function(data){ 
				data=eval(data);
				if(data.success){
					layer.msg(data.msg, {
					    icon: -1,
					}, function(){
						window.location="${msUrl}/${lowerName}/list.shtml";
					});  				
				}else{
					layer.msg(data.msg, {tips:2});
				}
			},
			error: function(xhr, status, text){ 
				if (xhr.status == "504") {
					layer.msg("登录超时，请重新登录", {
						tips : 2
					}, function() {
						location.reload();
					});
				}else{
					layer.msg("系统错误，请稍后再试", {
						tips : 2
					});
				} 
			},
		};
		$("#editForm").ajaxSubmit(option);
	}
	}
	//关闭窗口
	function closePop(){
		SPlatForm.closeTag();
	}
	
</script>
    
<body>
 			<div class="sy_r_top_bottom pr">
                <h2>首页</h2>
                <button onclick="closePop()"  class="pa buttons revokes">返回</button>
                <button onclick="updateSave()"  class="pa buttons querys">保存</button>
            </div>
  <form action="${msUrl}/${lowerName}/save.do" id="editForm" method="post" enctype="multipart/form-data">
					#foreach ($col in $columnDatas)
					#if($col.columnName=='id')
						<input id="id" name="id" type="hidden" class="gldint texwd160">
					#elseif($col.dataType=='java.util.Date')
						
						<p class="manage_unit clearfix mtb15">
								<label class="control-label">$col.columnComment:</label>
								<input id="$col.columnName" name="$col.columnName" type="text"
									 data-rule-required="true" class="form-control">
						</p>
							
					#else
					       <p class="manage_unit clearfix mtb15">
								<label class="control-label">$col.columnComment:</label>
								<input id="$col.columnName" name="$col.columnName" type="text"
									 data-rule-required="true" class="form-control">
							</p>
					#end
					
					#end 
	</form>
	<div class="manage_bot">
		<p class="copyright ft12">All Rights Reserved. Copyright 2015 XX科技有限股份有限公司</p>
	</div>
	 
</body>