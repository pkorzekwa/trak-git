     $(function () {
    	  	$('body').on('click', '.list-group .list-group-item', function () {
    	  	$(this).toggleClass('active');
    	  	}
        $('.list-arrows button').click(function () {        	
        	var $appmod='sectionemployees';
        	var $section=$('#section').val();
        	var $team=$("select#json_teams").val();
        	var $mode='';
        	var $module = $("select#json_unit").val();
        	var $button = $(this), actives = '';
            if ($button.hasClass('move-left')) {
                actives = $('.list-right ul li.active');
                actives.clone().appendTo('.list-left ul');
                actives.remove();
                $mode='del';
                $.post('JsonServlet',{appmodule:$appmod,section:$section,team:$team,mode:$mode,elist:$('.list-left ul li.active').text()});
                actives = $('.list-left ul li.active');
                actives.removeClass('active');
                actives = '';
            } else if ($button.hasClass('move-right')) {
                actives = $('.list-left ul li.active');
                actives.clone().appendTo('.list-right ul');
                actives.remove();
                $mode='add';
                $.post('JsonServlet',{appmodule:$appmod,section:$section,team:$team,mode:$mode,elist:$('.list-right ul li.active').text()});
                actives = $('.list-right ul li.active');
                actives.removeClass('active');
                actives = '';
                
            }
        }
        $('.dual-list .selector').click(function () {
            var $checkBox = $(this);
            if (!$checkBox.hasClass('selected')) {
                $checkBox.addClass('selected').closest('.well').find('ul li:not(.active)').addClass('active');
                $checkBox.children('i').removeClass('glyphicon-unchecked').addClass('glyphicon-check');
            } else {
                $checkBox.removeClass('selected').closest('.well').find('ul li.active').removeClass('active');
                $checkBox.children('i').removeClass('glyphicon-check').addClass('glyphicon-unchecked');
            }
        }
        $('[name="SearchDualList"]').keyup(function (e) {
            var code = e.keyCode || e.which;
            if (code == '9') return;
            if (code == '27') $(this).val(null);
            var $rows = $(this).closest('.dual-list').find('.list-group li');
            var val = $.trim($(this).val()).replace(/ +/g, ' ').toLowerCase();
            $rows.show().filter(function () {
                var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
                return !~text.indexOf(val);
            }
        }
    }
   