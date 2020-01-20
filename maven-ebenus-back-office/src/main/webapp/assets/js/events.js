//For Lists data
function hide_and_show(class_name_to_click, class_name_to_show) {
	$(document.getElementById(class_name_to_click)).click(function(){
		
		if ($(document.getElementById(class_name_to_show))[0].style.display !== 'none')
		{
			$("#" + class_name_to_show).css("display", "none");
			localStorage.setItem(class_name_to_click, "false")
		}
		else
		{
			$("#" + class_name_to_show).css("display", "inline-block");
			localStorage.setItem(class_name_to_click, "true")
		}
		
	});
}

function change_sign_listener(class_name_to_click, id_icone){
	$(document.getElementById(class_name_to_click)).click(function(){
		change_sign(id_icone);
	});
}

function change_sign(id_icone) {
	if ($(document.getElementById(id_icone))[0].classList.contains('fa-plus')){
		$(document.getElementById(id_icone))[0].classList.remove('fa-plus');
		$(document.getElementById(id_icone))[0].classList.add('fa-minus');
	}
	else
	{
		$(document.getElementById(id_icone))[0].classList.remove('fa-minus');
		$(document.getElementById(id_icone))[0].classList.add('fa-plus');
	}
}

function retrieveDisplayState(item, element_id, icon_id)
{
	if (localStorage.getItem(item) === "true")
	{
		$(document.getElementById(element_id))[0].style.display = 'inline-block';
		change_sign(icon_id);
	}
	else
	{
		document.getElementById(element_id).style.display = 'none';
	}
}
//- - - - - - - - - - - - - - - - - - - - - - - - - - - -

window.onload = function(){
	//Lists management
	retrieveDisplayState('user_list_title', "user_list", "icon-plus-1");
	retrieveDisplayState('parking_list_title', "parking_list", "icon-plus-2");
	retrieveDisplayState('voiture_list_title', "voiture_list", "icon-plus-3");
	
	hide_and_show("user_list_title", "user_list");
	hide_and_show("parking_list_title", "parking_list");
	hide_and_show("voiture_list_title", "voiture_list");
	
	change_sign_listener("user_list_title", "icon-plus-1");
	change_sign_listener("parking_list_title", "icon-plus-2");
	change_sign_listener("voiture_list_title", "icon-plus-3");
	
	$(document).ready(function() {
	    $('#table-user').DataTable();
	} );
	
	$(document).ready(function() {
	    $('#table-place').DataTable();
	} );
	
	$(document).ready(function() {
	    $('#table-voiture').DataTable();
	} );
};



