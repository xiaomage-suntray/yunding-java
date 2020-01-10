_#_(function() {
	var tableconfig={
		url : 'dataList.do',
		queryParams : function queryParams(params) {// 查询参数，如果无自定义查询参数，可以不用写
			return {
				rows : params.pageSize,// 每页多少条
				page : params.pageNumber,// 当前页
				sort : params.sortName,// 根据哪个字段排序
				order : params.sortOrder,// 升降序
				// 以下是自定义查询项，可以将值写在这里
				parentId : _#_("#_querypid").val()
			};
		},
		columns :  [ {
			checkbox : true
		}<#list bean.fields as field>
		<#if field.fieldJavaName=="id">
		,{
			field : "id",
			title : "ID",
			width : 50,
			align : 'center',
			sortable : true
		}
		</#if>
		<#if field.fieldJavaName!="id">
		,{
			field : "${field.fieldJavaName}",
			title : "${field.fieldRemark}",
			align : 'center',
			sortable : true
		}
		</#if>
		</#list>
		]
	}
	STableUtils.initBootstrapTable(tableconfig);
});

// 删除记录
function del(){ 
	var array = STableUtils.getMultiSelect('table');// 获取选中的记录
	if(array){
		SPlatForm.confirm('您确定要删除么',{
			btn:['删除','取消'],// 如果不写，默认为 确认/取消
			confirm:function(){// 点击确定时的操作
				var postOption={
					url : "delete.do",
					// 默认按照id去找，如果使用其他主键，可以写参数(array,'id')
					data : STableUtils.getDataFromArrayAndKey(array),
					success : function(result){// 成功的回调
						SPlatForm.alert(result.msg);
						STableUtils.refreshTable();// 执行成功后刷新页面
					}
				}
				SPlatForm.post(postOption);
			}
		});
	}
}
// 添加纪录
function add(){ 
	$("#editForm").clearForm(true);
	SPlatForm.openModal('#editDiv');
}
function loadData(id) {
	var postOption={
			url : "getId.do",
			data : {id:id},
			success : function(result){//成功的回调
				SPlatForm.openModal('#editDiv');
				$("#editForm").fill(result.data); 
			}
		}
	SPlatForm.post(postOption);
}
// 修改--根据记录打开弹窗
// 修改
function update(id) {
	$("input[type='radio']").attr("checked",false);
	$( "#editForm" ).validate();
	$( "#editForm" ).clearForm(true);
	var array=STableUtils.getOneSelect('table');//获取选中的记录
	if(array){
		loadData(array[0].id);
	}
}
// 编辑
function updateSave(){
	if(_#_("#editForm").valid()){
		SPlatForm.ajaxSubmit(_#_("#editForm"),{// ajaxSubmit提交表单
			successTodo : function(result){// 成功的回调
				SPlatForm.closeModal('#editDiv');
				STableUtils.refreshTable();// 执行成功后刷新页面
			}
		}) ;
	}
}