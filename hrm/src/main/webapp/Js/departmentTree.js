	var setting = {
		async : {
			enable : true
		},
		view : {
			addHoverDom : addHoverDom,
			removeHoverDom : removeHoverDom,
			selectedMulti : false
		},
		edit : {
			enable : true,
			editNameSelectAll : true,
			showRemoveBtn : showRemoveBtn,
		},
		data : {
			simpleData : {
				enable : true
			},
			keep : {
				parent : true
			},
			key : {
				name : "departmentName",
				url : "1"
			}
		},
		callback : {
			beforeDrag : beforeDrag,
			beforeEditName : beforeEditName,
			beforeRemove : beforeRemove,
			beforeRename : beforeRename,
			/* onNodeCreated: zTreeOnNodeCreated, */
			onDblClick : zTreeOnDblClick,
			onRemove : onRemove,
			onRename : onRename
	}
	};

	var log, className = "dark";
	function beforeDrag(treeId, treeNodes) {
		return false;
	}
	function beforeEditName(treeId, treeNode) {

		className = (className === "dark" ? "" : "dark");
		showLog("[ " + getTime() + " beforeEditName ]&nbsp;&nbsp;&nbsp;&nbsp; "
				+ treeNode.departmentName);
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.selectNode(treeNode);
		setTimeout(function() {
			if (confirm("进入部门" + treeNode.departmentName + " 的编辑状态吗？")) {
				setTimeout(function() {
					zTree.editName(treeNode);
				}, 0);
			}
		}, 0);
		return false;
	}
	function beforeRemove(treeId, treeNode) {
		className = (className === "dark" ? "" : "dark");
		showLog("[ " + getTime() + " beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; "
				+ treeNode.departmentName);
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.selectNode(treeNode);
		return confirm("确认删除部门" + treeNode.departmentName + " 吗？");
	}
	function onRemove(e, treeId, treeNode) {
		showLog("[ " + getTime() + " onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; "
				+ treeNode.departmentName);
		$.post("delete.do", {
			id : treeNode.id
		})
	}
	function beforeRename(treeId, treeNode, newName, isCancel) {
		className = (className === "dark" ? "" : "dark");
		showLog((isCancel ? "<span style='color:red'>" : "") + "[ " + getTime()
				+ " beforeRename ]&nbsp;&nbsp;&nbsp;&nbsp; "
				+ treeNode.departmentName + (isCancel ? "</span>" : ""));
		if (newName.length == 0) {
			setTimeout(function() {
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				zTree.cancelEditName();
				alert("节点名称不能为空.");
			}, 0);
			return false;
		}
		return true;
	}

	function onRename(e, treeId, treeNode, isCancel) {
		$.post("updatedepartment.do", {
			"departmentName" : treeNode.departmentName,
			"id" : treeNode.id
		})
		showLog((isCancel ? "<span style='color:red'>" : "") + "[ " + getTime()
				+ " onRename ]&nbsp;&nbsp;&nbsp;&nbsp; "
				+ treeNode.departmentName + (isCancel ? "</span>" : ""));
	}
	function showRemoveBtn(treeId, treeNode) {
		return !treeNode.isFirstNode;
	}
	function showRenameBtn(treeId, treeNode) {
		return !treeNode.isLastNode;
	}
	function showLog(str) {
		if (!log)
			log = $("#log");
		log.append("<li class='"+className+"'>" + str + "</li>");
		if (log.children("li").length > 8) {
			log.get(0).removeChild(log.children("li")[0]);
		}
	}
	function getTime() {
		var now = new Date(), h = now.getHours(), m = now.getMinutes(), s = now
				.getSeconds(), ms = now.getMilliseconds();
		return (h + ":" + m + ":" + s + " " + ms);
	}

	var newCount = 1;
	function addHoverDom(treeId, treeNode) {

		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0)
			return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
				+ "' title='add node' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var btn = $("#addBtn_" + treeNode.tId);
		if (btn)
			btn.bind("click", function() {
				/* confirm("添加新节点吗？") */
				$("#addConfirm").attr("departId", treeNode.id);
				$("#addConfirm").attr("treeNode", treeNode.tId);
				$("#addConfirm").off('.data-api');
				$("#addConfirm").modal("show");
				/* var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				zTree.addNodes(treeNode, {
					id : (100 + newCount),
					pId : treeNode.id,
					name : "new node" + (newCount++)
				}); */
				return false;
			});
	};
	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_" + treeNode.tId).unbind().remove();
	};

	function selectAll() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.setting.edit.editNameSelectAll = $("#selectAll").attr("checked");
	}

	$(document).ready(function() {
		getTree();
		$.get("/hrm/department/selectRankNum", {}, function(data) {
			var se = document.getElementById('rankId');
			var file = data.data;
			for (var i = 0; i < file.length; i++) {
				se.options.add(new Option(file[i].rankNum));
			}
		})
	});

	function getTree() {
		$.getJSON("/hrm/department/getTree", function(json) {
			var zNodes = json.data;
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			$("#selectAll").bind("click", selectAll);
			$("#positionName").hide();
			$("#addPosition").hide();
			$("#rankId").hide();
			$("#fileId").hide();
		});
	}
	function addDepart() {
		var departId = $("#addConfirm").attr("departId");
		var name = $("#departmentName").val();
		if (!name) {
			alert("请输入部门");
			$("#departmentName").focus();
		} else {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			var treeNode = zTree
					.getNodeByTId($("#addConfirm").attr("treeNode"));
			/* 		alert("name="+name+" id="+departId);
			 */
			$.post("insertdepartment.do", {
				departmentName : name,
				pId : departId
			}, function() {
				zTree.addNodes(treeNode, {
					id : (100 + newCount),
					pId : departId,
					departmentName : name
				});
			})
			$("#addConfirm").modal("hide");
		}

	}

	function cancel() {
		$("#addConfirm").modal("hide");
	}
	function zTreeOnDblClick(event, treeId, treeNode) {

		if (!treeNode.isParent) {
			$("#positionName").attr("departmentid", treeNode.id);
			var id = treeNode.id;
			$("#positionName").show();
			$("#addPosition").show();
			$("#rankId").show();
			$("#fileId").show();
			refreshpo(id);
		} else {
			$("#positionName").hide();
			$("#addPosition").hide();
			$("#rankId").hide();
			$("#fileId").hide();
		}
	}
	function refreshpo(id) {
		$.get("/hrm/department/getPosition?",
						{
							did : id
						},
						function(json) {
							var zNodes = json.data;
							var html = "<table class='table'>"
									+ "<tr ><td height='22' align='center' >"
									+ "职位" + "</td>"
									+ "<td height='22' align='center'>" + "档"
									+ "</td>"
									+ "<td height='22' align='center'>" + "级"
									+ "</td>"
									+ "<td height='22' align='center'>" + "操作"
									+ "</td>" + "</tr>"
							for (i = 0; i < zNodes.length; i++) {
								html += "<tr><td height='22' align='center'>"
										+ zNodes[i].positionName
										+ "</td>"
										+ "<td height='22' align='center'>"
										+ zNodes[i].rankNum
										+ "</td>"
										+ "<td height='22' align='center'>"
										+ zNodes[i].fileNum
										+ "</td>"
										+ "<td height='22' align='center'><input type='button' onclick='deletePosition("
										+ zNodes[i].id + "," + id
										+ ")'value='删除'/></td></tr>";

							}
							html += "</table>"
							document.getElementById("demo").innerHTML = html;
						});
	}
	function deletePosition(pid, id) {
		$.post("/hrm/department/deletePosition.do", {
			"id" : pid
		}, function() {
			refreshpo(id);
		})
	}
	$("#addPosition").click(function() {
		var obj = $("#positionName")
		var name = obj.val();
		var did = obj.attr("departmentId");
		var rank = document.getElementById("rankId");
		var file = document.getElementById("fileId");
		var rankNum = rank.options[rank.selectedIndex].text;
		var fileNum = file.options[file.selectedIndex].text;
		if (!name) {
			alert("请输入职位");
			obj.focus();
		} else if (rankNum == "档")
			alert("请选择档!");
		else if (fileNum == "级")
			alert("请选择级!");
		else {
			$.post("/hrm/department/insertPosition.do", {
				did : did,
				"positionName" : name,
				"rankNum" : rankNum,
				"fileNum" : fileNum
			}, function() {
				refreshpo(did);
			})
		}
	})
	$("#rankId").change(function() {
		var rank = $("#rankId");
		var num = rank.val();
		$.get("/hrm/department/selectFileNum", {
			"num" : num
		}, function(data) {
			var se = $('#fileId');
			var file = data.data;
			console.info(se)
			se.empty();
			se.append("<option value='级'>级</option>");
			console.info(JSON.stringify(file));
			for (var i = 0; i < file.length; i++) {
				var temp = file[i].fileNum;
				se.append("<option value='"+temp+"'>" + temp + "</option>");
			}
		})
	})
	function showDepartment() {
		var se = document.getElementById('rankId');
		var se1 = document.getElementById('fileId');
		$.get("/hrm/department/getfile", function(data) {
			var file = data.data;

			for (var i = 0; i < file.length; i++) {
				se.options.add(new Option(file[i].rankNum));
				se1.options.add(new Option(file[i].fileNum));
			}
		})
	}