	$(function(){
		var rank=document.getElementById("rankId");
		var file=document.getElementById("fileId");
		for(var i=1;i<15;i++)
			{
				rank.options.add(new Option(i));
			}
		for(var i=1;i<28;i++)
		{
			file.options.add(new Option(i));
		}
		initHTML();
		refreshHTML();
	})
	function initHTML()
	{
		var html="<table class='table table-bordered'>"
			+"<thead>"
				+"<tr>"
					+"<th>"
					+"档次"
					+'<br>'
					+"级别"
					+"</th>";
					for(var i=1;i<15;i++)
					{
						html+="<th>"
						+i
						+"</th>";
					} 
				html+="</tr>"
			+"</thead>"
			for(var i=1;i<28;i++)
				{
					html+="<tr class='fileNum"+i+"'>"
						+"<td class='rankHead' >"
						+i
						+"</td>"
				for(var j=1;j<15;j++)
					{
					html+="<td onclick='tdclick(this)' class='rankNum"+j+"' va=''>";
						html+="</td>";
					}
				html+="</tr>"
					
				}
		html+="</table>"
		document.getElementById("demo").innerHTML = html;
	}
	function refreshHTML()
	{
		
		 $.get("getfile",{},function(data){
				
				var file=data.data;
				for(i=0;i<file.length;i++){
					var item = file[i];
					initTable(item.rankNum,item.fileNum,item.salary);
				}
			}); 
	
	}
	function initTable(rankNum,fileNum,value){
		$(".fileNum"+fileNum+" .rankNum"+rankNum).html(value);
		$(".fileNum"+fileNum+" .rankNum"+rankNum).attr("va",value);
	}
	function vilidate(rankId,fileId,salary)
	{
		var reg=new RegExp("[0-9]+");
		if(rankId=="档")
			{
			alert("请输入档!");
			return false;
			}
		else if(fileId=="级")
			{
				alert("请输入级!");
				return false;
			}
		else if(salary=="")
		{
		 	alert("请输入薪资!");
		 	return false;
		}
		else if(!reg.test(salary)){
			
			alert("薪资只能输入数字!");
			return false;
		}
		else 
			return true;
	}
	$("#addPosition").click(function(){
		    var rank=document.getElementById("rankId");
			var rankId=rank.options[rank.selectedIndex].text;
			var file=document.getElementById("fileId");
			var fileId=file.options[file.selectedIndex].text;
			var salary=$("#salary").val();
			if(vilidate(rankId,fileId,salary))
			{
				$.post("/hrm/department/insertFile.do",{"salary":salary,"rankNum":rankId,"fileNum":fileId},function(d){
					if(d){
						initHTML();
						refreshHTML();
						document.getElementById("salary").text="";
						$("#salary").focus();
					}
					else{
						alert("插入失败！");
					}	
				})
			}
	})
	$("#delPosition").click(function(){
		var rank=document.getElementById("rankId");
		var rankId=rank.options[rank.selectedIndex].text;
		var file=document.getElementById("fileId");
		var fileId=file.options[file.selectedIndex].text;
		var salary=$("#salary").val();
		if(rankId=="档")
		{
		alert("请输入档!");
		}
		else if(fileId=="级")
		{
			alert("请输入级!");
		}
		else
		{
			$.post("/hrm/department/delFile.do",{"salary":salary,"rankNum":rankId,"fileNum":fileId},function(){
				initHTML();
				refreshHTML();
				$("#salary").focus();
			})
		}
	})
	$("#setPosition").click(function(){
		var rank=document.getElementById("rankId");
		var rankId=rank.options[rank.selectedIndex].text;
		var file=document.getElementById("fileId");
		var fileId=file.options[file.selectedIndex].text;
		var salary=$("#salary").val();
		if(vilidate(rankId,fileId,salary))
		{
			$.post("/hrm/department/setFile.do",{"salary":salary,"rankNum":rankId,"fileNum":fileId},function(d){
				if(d)
					{
						initHTML();		
						refreshHTML();
						$("#salary").focus();
					}
				else
					alert("请先插入数据");
			})
		}
	})
	
	function tdclick(obj){
		var rankNum = $(obj).attr("class").replace("rankNum","");
		var fileNum = $(obj).parent().attr("class").replace("fileNum","");
		$("#rankId").val(rankNum);
		$("#fileId").val(fileNum);
		$("#salary").val($(".fileNum"+fileNum+" .rankNum"+rankNum).attr("va"));
		$("#salary").focus();
       /*  alert("rankNum:"+rankNum+"\n fileNum:"+fileNum);  */
		
	}