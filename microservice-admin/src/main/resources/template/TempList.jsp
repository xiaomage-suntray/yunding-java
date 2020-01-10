<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/view/include.jsp"%>
<style>
body{ackground: #e4e7ea;}
</style>
<script type="text/javascript" src="_#_{msUrl}/js/${bean.lowerName}/${bean.lowerName}.js"></script>

<script type="text/javascript">
	_#_(document).ready(function() {
			//初始化校验
			_#_.validator.setDefaults( {
				ignore : []
			});//如果有display:none的情况，加上这个，会验证display none的必填项
			_#_("#editForm").validate();
			<#list bean.fields as field>
				<#if field.fieldType=="datetime">
				laydate({
					elem: '#${field.fieldJavaName}', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
					event: 'focus', //响应事件。如果没有传入event，则按照默认的click
					format: 'YYYY/MM/DD hh:mm:ss',
					istime: true,
					istoday: true,
					choose: function(datas){ //选择日期完毕的回调
						_#_("#editForm").validate().element(_#_('#${field.fieldJavaName}'))
					}
				});
				</#if>
				<#if field.fieldType=="date">
				laydate({
					elem: '#${field.fieldJavaName}', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
					event: 'focus', //响应事件。如果没有传入event，则按照默认的click
					format: 'YYYY/MM/DD hh:mm:ss',
					istime: true,
					istoday: true,
					choose: function(datas){ //选择日期完毕的回调
						_#_("#editForm").validate().element(_#_('#${field.fieldJavaName}'))
					}
				});
				</#if>
			</#list>

		});
</script>
				
<!--查询条件  -->
<div class="panel" style=" background:#e4e7ea;">
	<div class="panel-heading" style=" margin-bottom:20px;">
		<form action="" class="form-inline" id="searchForm">
			<#list bean.fields as field>
			<label class="control-label">${field.fieldRemark}:</label><input type="text" id="${field.fieldJavaName}" class="form-control"> 
			</#list>
			<button type="button"  onclick="STableUtils.reloadTable()" class="btn btn-primary">查询</button>
		</form>
		<div class="dashed-line"></div>
		<!--toolbar-->
		<div class="tacz" id="toolbar">
			<span class="btn btn-primary" onclick="add()" fun="add"><i class="fa glyphicon-plus"></i> 增加</span>
			<span class="btn btn-primary" onclick="update()" fun="edit"><i class="fa glyphicon-pencil"></i>修改</span> 
			<span class="btn btn-maroon" onclick="del()" fun="remove"><i class="fa fa-trash-o"></i>删除</span>
		</div>
	</div>
<!-- 编辑窗口    ----->
<div class="modal fade" id="editDiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">信息</h4>
			</div>
			<div class="modal-body">
				<form action="_#_{msUrl}/${bean.lowerName}/save.do" method="post" class="form-horizontal form-bordered" id="editForm">
				<#list bean.fields as field>
					<#if field.fieldJavaName=="id">
					<input id="id" name="id" type="hidden" class="gldint texwd160">
					</#if>
					<#if field.fieldJavaName!="id">
					<p class="manage_unit clearfix mtb15">
						<label class="control-label">${field.fieldRemark}:</label>
						<input id="${field.fieldJavaName}" name="${field.fieldJavaName}" type="text" <#if field.canBeNull =="true"> data-rule-required="true" </#if><#if field.isIdNo?? && field.isIdNo == "true"> data-rule-idCard="true" </#if><#if field.isMobile?? && field.isMobile == "true"> data-rule-tel="true" </#if><#if field.isEmail?? && field.isEmail == "true"> data-rule-email="true" </#if><#if field.fieldType =="varchar"><#if field.maxLength??> maxlength=${field.maxLength} </#if><#if field.minLength??> minLength=${field.minLength} </#if></#if><#if field.fieldType =="bigint" || field.fieldType == "int"> data-rule-digits=true <#if field.maxLength??> max=${field.maxLength} </#if><#if field.minLength??> min=${field.minLength} </#if></#if><#if field.fieldType =="double"> data-rule-number=true <#if field.maxLength??> max=${field.maxLength} </#if> <#if field.minLength??> min=${field.minLength} </#if></#if> class="form-control" >
					</p>
					</#if>
				</#list>
				</form>
			</div>
		 	<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" >关闭</button>
				<button type="button" onclick="updateSave()" class="btn btn-primary">提交更改</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<div class="panel-body" style="background: #fcfcfc;">
	<table id="table" class="table_data"></table>
</div>
</div>

