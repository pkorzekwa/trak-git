
function modObj() {
			this.type = "";
			this.tittle = "";
			this.content = "";
			this.form_action = "";
			this.form_target = "";
			this.cancel_action = "";
			this.form_inputs;
	};


function openModal(input){

	var modal = document.createElement("div");
	modal.setAttribute("class", "modal");
	modal.setAttribute("id", "myModal");

	var modalContent = document.createElement("div");
	modalContent.setAttribute("class", "modal-content");
	modal.appendChild(modalContent);

	var modalHeader = document.createElement("div");
	modalHeader.setAttribute("class", "modal-header");
	modalHeader.setAttribute("id", "modalHeader");
	var close = document.createElement("span");
	close.setAttribute("class", "close");
	close.setAttribute("id", "closeModal");
	close.innerHTML = "x";
	var modalTittle = document.createElement("h2");
	modalTittle.setAttribute("id", "modalTittle");
	modalTittle.appendChild(document.createTextNode(input.tittle));
	modalHeader.appendChild(close);
	modalHeader.appendChild(modalTittle);
	modalContent.appendChild(modalHeader);

	var modalBody = document.createElement("div");
	modalBody.setAttribute("class", "modal-body");
	modalBody.setAttribute("id", "modalBody");
	modalBody.style.padding = "10px";
	modalBody.appendChild(document.createTextNode(input.content));
	modalContent.appendChild(modalBody);
	

	var modalFooter = document.createElement("div");
	modalFooter.setAttribute("class", "modal-footer");
	modalFooter.setAttribute("id", "modalFooter");
	
	var saveButton = document.createElement("input");
	saveButton.setAttribute("type", "button");
	saveButton.setAttribute("value", "OK");
	saveButton.setAttribute("id", "saveModal");
	saveButton.style.display = "none";
	saveButton.style.margin = "5px";
	modalFooter.appendChild(saveButton);
	
	var okButton = document.createElement("input");
	okButton.setAttribute("type", "button");
	okButton.setAttribute("value", "OK");
	okButton.setAttribute("id", "okModal");
	okButton.style.margin = "5px";
	okButton.style.display = "none";
	modalFooter.appendChild(okButton);
	
	var cancelButton = document.createElement("input");
	cancelButton.setAttribute("type", "button");
	cancelButton.setAttribute("value", "Anuluj");
	cancelButton.setAttribute("id", "cancelModal");
	cancelButton.style.margin = "5px";
	cancelButton.style.display = "none";
	modalFooter.appendChild(cancelButton);
	
	modalContent.appendChild(modalFooter);
	
	modal.style.display = "block";
	
	var frm = document.createElement("FORM");

	document.body.appendChild(modal);
	
	if(input.type==="form"){
		saveButton.style.display = "inline-block";
		cancelButton.style.display = "inline-block";
		
		frm.setAttribute('method',"post");
		frm.setAttribute('action',input.form_action);
		frm.setAttribute('id',"modalForm");
		frm.setAttribute('target',input.form_target);
		frm.acceptCharset= "UTF-8";
		var formInner = '<table><tbody><tr><td></td><td id=\'modaljs-form-warning\'></td><td></td></tr>';
		frm.innerHTML+='<table><tbody>';
		for(var i = 0; i<input.form_inputs.length;i++){
			var man = "";
			var required = "";
			if(input.form_inputs[i].mandatory==true){man="*";required="required";}
			var disabled = "";
			if(input.form_inputs[i].disabled==true){disabled="disabled";}
			switch(input.form_inputs[i].type){
			case "select":
				var multiple = "";
				if(input.form_inputs[i].select.multiple==true){multiple="multiple";}
				formInner+='<tr><td><span>'+input.form_inputs[i].description+man+'</span></td>';
				formInner+='<td><select name=\''+input.form_inputs[i].name+'\' '+multiple+' class=\'modaljs-input\' '+disabled+' '+required+'>';
				for(var j = 0;j<input.form_inputs[i].select.values.length;j++){
					var selected="";
					if(input.form_inputs[i].select.values[j][0]===input.form_inputs[i].select.selected){selected="selected";}
					formInner+='<option value=\''+input.form_inputs[i].select.values[j][0]+'\' '+selected+'>'+input.form_inputs[i].select.values[j][1]+'</option>';
				}
				formInner+='</select></td>';
				formInner+='<td><span class=\'modaljs-hint\'>'+input.form_inputs[i].hint+'</span></td>';
				formInner+='</tr>';
				break;
			case "textarea":
				formInner+='<tr><td><span>'+input.form_inputs[i].description+man+'</span></td>';
				formInner+='<td><textarea name=\''+input.form_inputs[i].name+'\' class=\'modaljs-input\' cols=\'30\' rows=\'3\' '+disabled+' '+required+'>'+input.form_inputs[i].value+'</textarea></td>';
				formInner+='<td><span class=\'modaljs-hint\'>'+input.form_inputs[i].hint+'</span></td>';
				formInner+='</tr>';
				break;
			case "hidden":
				formInner+='<input type=\'hidden\' name=\''+input.form_inputs[i].name+'\' value=\''+input.form_inputs[i].value+'\'>';
				break;
			case "number":
				var min = "";
				var max = "";
				if(input.form_inputs[i].number.min!=null){min = 'min=\''+input.form_inputs[i].number.min+'\'';}
				if(input.form_inputs[i].number.max!=null){max = 'max=\''+input.form_inputs[i].number.max+'\'';}
				formInner+='<tr><td><span>'+input.form_inputs[i].description+man+'</span></td>';
				formInner+='<td><input type=\'number\' name=\''+input.form_inputs[i].name+'\' class=\'modaljs-input\' value=\''+input.form_inputs[i].value+'\' '+min+' '+max+' '+disabled+' '+required+'></td>';
				formInner+='<td><span class=\'modaljs-hint\'>'+input.form_inputs[i].hint+'</span></td>';
				formInner+='</tr>';
				break;
			case "checkbox":
				var checked = "";
				if(input.form_inputs[i].checkbox.checked==true){checked="checked";}
				formInner+='<tr><td><span>'+input.form_inputs[i].description+man+'</span></td>';
				formInner+='<td><input type=\'checkbox\' name=\''+input.form_inputs[i].name+'\' class=\'modaljs-input\' value=\'1\' '+disabled+' '+required+' '+checked+' >';
				formInner+='<input type=\'hidden\' name=\''+input.form_inputs[i].name+'\' class=\'modaljs-input\' value=\'0\' ></td>';
				formInner+='<td><span class=\'modaljs-hint\'>'+input.form_inputs[i].hint+'</span></td>';
				formInner+='</tr>';
				break;
			case "radio":
				var checked = "";
				if(input.form_inputs[i].radio.checked==true){checked="checked";}
				formInner+='<tr><td><span>'+input.form_inputs[i].description+man+'</span></td>';
				formInner+='<td><input type=\'radio\' name=\''+input.form_inputs[i].name+'\' class=\'modaljs-input\' value=\''+input.form_inputs[i].value+'\' '+disabled+' '+required+' '+checked+' >';
				formInner+='<td><span class=\'modaljs-hint\'>'+input.form_inputs[i].hint+'</span></td>';
				formInner+='</tr>';
				break;
			case "null":
				formInner+='<tr><td><span>'+input.form_inputs[i].description+man+'</span></td>';
				formInner+='<td><span name=\''+input.form_inputs[i].name+'\' class=\'modaljs-input\'>'+input.form_inputs[i].value+'</span></td>';
				formInner+='<td><span class=\'modaljs-hint\'>'+input.form_inputs[i].hint+'</span></td>';
				formInner+='</tr>';
				break;
				default:
					formInner+='<tr><td><span>'+input.form_inputs[i].description+man+'</span></td>';
					formInner+='<td><input type=\''+input.form_inputs[i].type+'\' name=\''+input.form_inputs[i].name+'\' class=\'modaljs-input\' value=\''+input.form_inputs[i].value+'\' '+disabled+' '+required+'></td>';
					formInner+='<td><span class=\'modaljs-hint\'>'+input.form_inputs[i].hint+'</span></td>';
					formInner+='</tr>';
					break;
			}
		}
		formInner+='</table></tbody>';
		frm.innerHTML=formInner;
		modalBody.appendChild(frm);
	}else if(input.type==="info"){
		okButton.style.display = "inline-block";
	}else if(input.type==="warning"){
		okButton.style.display = "inline-block";
	}else{
		okButton.style.display = "inline-block";
	}

	close.onclick = function() {
		if(input.cancel_action==""){
			closeModal();
		}else{
			document.location = input.cancel_action;
		}
		};
	okButton.onclick = function() {closeModal();};
	cancelButton.onclick = function() {
		if(input.cancel_action==""){
			closeModal();
		}else{
			document.location = input.cancel_action;
		}
		};
	
	saveButton.onclick = function() {
		var frmWarning = document.getElementById('modaljs-form-warning');
		frmWarning.innerHTML="";
		var emptyValidation = true;
		var frmElements = frm.elements;
		for (var i=0, element;element=frmElements[i++];){
			element.style.backgroundColor="white";
			if(element.required==true){
				if (element.value == null || element.value === "") {
					emptyValidation = false;
					element.style.backgroundColor="#ffcccc";
				}
			}
		}
		if(emptyValidation==true){
			frm.submit(); 
			closeModal();
		}else{
			frmWarning.style.color="red";
			frmWarning.innerHTML="Wype&#322;nij pola oznaczone gwiazdk&#261 (*)";
		}
		
		};

	window.onclick = function(event) {
		if (event.target == modal) {
			if(input.cancel_action==""){
				closeModal();
			}else{
				document.location = input.cancel_action;
			}
			}
		};
}

function closeModal(){
var mod = document.getElementById('myModal');
mod.parentNode.removeChild(mod);
}