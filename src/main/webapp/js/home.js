$(document).ready(function() {
	// 紫 蓝 黄 橙 桃红 棕
	// var colors = ["#CECEFF","#ACD6FF","#FFFF93","#FFC78E","#D2A2CC", "#C2C287"];
	$(".tag").each(function() {
		// var index = Math.round(Math.random() * 5);
		var tag = $(this);
		// tag.css("background-color", colors[tag.attr("data-color")]);
		tag.css("background-color", "#FFFF93");
	});
});