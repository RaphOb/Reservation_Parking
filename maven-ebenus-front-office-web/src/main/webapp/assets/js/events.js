window.onload = function(){
  $(document.getElementById('user_list'))[0].style.display = 'none';
};

$(document.getElementById('user_list_title')).click(function(){
	
	console.log($(document.getElementById('user_list')));
	
	if ($(document.getElementById('user_list'))[0].style.display !== 'none')
	{
		$(document.getElementById('user_list'))[0].style.display = 'none';
	}
	else
	{
		$(document.getElementById('user_list'))[0].style.display = 'inline-block';
	}
});

