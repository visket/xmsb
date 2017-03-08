
/**
 * 封装需要判断的流程类型
 * 
 */
var Process_type = {
	dsl : '61a3bdf5-73f9-4285-b8c0-6bca56d0da6d', //待受理
	ysl : '2239265d-bea4-4f81-b0f3-4cb7366e5558', //已受理
	sjsp : '0d4b90c6-6c39-4799-a1c4-7a84e4686f40', //市级已审批
	dffcs : '6ac1ea68-e8b2-4497-8930-9da3b744acf6',//待分发处室
	cssp : '70db0f46-5140-45f2-acf3-17ce759c3639', //处室已审批
	zjsp : 'f381bea9-71be-4d3f-997d-54529e7b1b4a',//专家已审核

	//重置所有按钮
	init_linkButton_show : function(toolbar) {
		$('#'+toolbar+' .easyui-linkbutton').show();
	},
	
	set_linkButton_show : function(row) {
		if(this.dsl == row.statusId || this.ysl == row.statusId) {
			$('#equi_edit').hide();
			$('#equi_declare').hide();
			$('#equi_del').hide();
		}
	}
};




