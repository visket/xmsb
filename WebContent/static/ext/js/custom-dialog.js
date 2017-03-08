



$.extend($.fn.dialog.methods, {
	addButton : function(jq, items) {
		return jq.each(function() {
			var buttonbar = $(this).children("div.dialog-button");
			for(var i = 0; i < items.length; i++) {
				var item = items[i];
				var btn = $('<a href="javascript:void(0)"></a>');
				btn[0].onclick = eval(item.handler || function() {});
				
				btn.css("float","left").appendTo(buttonbar).linkbutton($.extend({},item,{plain:false}));
			}
			buttonbar = null;
		});
	}
});