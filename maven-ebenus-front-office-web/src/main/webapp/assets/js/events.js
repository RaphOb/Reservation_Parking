function hide_and_show(class_name_to_click, class_name_to_show) {
	$(document.getElementById(class_name_to_click)).click(function(){
		
		if ($(document.getElementById(class_name_to_show))[0].style.display !== 'none')
		{
			$("#" + class_name_to_show).css("display", "none");
		}
		else
		{
			$("#" + class_name_to_show).css("display", "inline-block");
		}
	});
}

function change_sign(class_name_to_click, id_icone){
	$(document.getElementById(class_name_to_click)).click(function(){
		if ($(document.getElementById(id_icone))[0].classList.contains('fa-plus')){
			$(document.getElementById(id_icone))[0].classList.remove('fa-plus');
			$(document.getElementById(id_icone))[0].classList.add('fa-minus');
		}
		else
		{
			$(document.getElementById(id_icone))[0].classList.remove('fa-minus');
			$(document.getElementById(id_icone))[0].classList.add('fa-plus');
		}
	});
}


window.onload = function(){
	$(document.getElementById('user_list'))[0].style.display = 'none';
	$(document.getElementById('parking_list'))[0].style.display = 'none';
	$(document.getElementById('voiture_list'))[0].style.display = 'none';
	hide_and_show("user_list_title", "user_list");
	hide_and_show("parking_list_title", "parking_list");
	hide_and_show("voiture_list_title", "voiture_list");
	change_sign("user_list_title", "icon-plus-1");
	change_sign("parking_list_title", "icon-plus-2");
	change_sign("voiture_list_title", "icon-plus-3");
};



