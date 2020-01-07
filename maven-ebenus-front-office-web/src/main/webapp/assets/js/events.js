function hide_and_show(class_name_to_click, class_name_to_show) {
	$(document.getElementById(class_name_to_click)).click(function(){
		
		if ($(document.getElementById(class_name_to_show))[0].style.display !== 'none')
		{
			$(document.getElementById(class_name_to_show))[0].style.display = 'none';
		}
		else
		{
			$(document.getElementById(class_name_to_show))[0].style.display = 'inline-block';
		}
	});
}

window.onload = function(){
	$(document.getElementById('user_list'))[0].style.display = 'none';
	$(document.getElementById('parking_list'))[0].style.display = 'none';
	$(document.getElementById('voiture_list'))[0].style.display = 'none';
	hide_and_show("user_list_title", "user_list")
	hide_and_show("parking_list_title", "parking_list")
	hide_and_show("voiture_list_title", "voiture_list")
};



