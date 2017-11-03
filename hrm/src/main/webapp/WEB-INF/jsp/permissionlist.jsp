<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd 

">
<html xmlns="http://www.w3.org/1999/xhtml 

">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>人力资源管理系统</title>
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
	<script
		src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="col-md-10">
    <h2>权限列表</h2>
    <hr>
    <div  class="form-inline">
    	<form action="${pageContext.request.contextPath }/permission/permissionList?currPage=1" method="post" >
	        <div clss="well">
	            <div class="form-group">
	                <input type="text" class="form-control" style="width: 300px;" 
	                value="" name="searchText" id="searchText" placeholder="输入权限名称/权限资源">
	            </div>
				<span class=""> 
					  <button type="submit" class="btn btn-primary">查询</button>
					  <a class="btn btn-success" onclick="$('#addPermission').modal();">增加权限</a>
					  <a  id="deleteAll" class="btn  btn-danger" onclick="$('#confirm-deleteChecked').modal();">删除权限</a>
				</span>
	        </div>
        </form>
        <hr>
        <table class="table table-bordered">
            <tbody>
                <tr>
                    <th><input type="checkbox" onclick="checkAll(this)"></th>
                    <th>权限名称</th>
                    <th>权限资源</th>
                    <th>操作</th>
                </tr>
                <form id="delteChecked" action="${pageContext.request.contextPath }/permission/delCheckedRoles" method="post">
                	<input type="hidden" name="currPage" id="currPage" value="1">
                	<input type="hidden" name="searchText" id="searchText" value="${searchText }">
	                <c:forEach items="${pb.list }" var="permission" varStatus="status">
		                <tr>
		                    <td><input value=${permission.id } check='box' type="checkbox" name="pid" /></td>
		                    <td>${permission.name }</td>
		                    <td>${permission.url }</td>
		                    <td>
		                        <i class="glyphicon glyphicon-remove"></i>
		                        <a data-toggle="modal" data-target="#confirm-delete" onclick="activeDeleteButton('${permission.id}')">删除</a>
		                        <i class="glyphicon glyphicon-pencil"></i>
								<a data-toggle="modal" data-target="#myModal" onclick="permissionModal('${permission.id}','${permission.name}','${permission.url}')">修改</a>
		                    </td>
		                </tr>
	                </c:forEach>
                </form>
            </tbody>
        </table>
    </div>
    
    <!--分页 -->
		<div style="width:380px;margin:0 auto;margin-top:50px;">
			<ul class="pagination" style="text-align:center; margin-top:10px;">
			
			
		<!-- 判断当前页是否是首页 -->
			    <c:if test="${pb.currPage==1 }">
					<li class="disabled">
					     <a href="javascript:void(0)" aria-label="Previous">
				    	 <span aria-hidden="true">&laquo;</span></a>
					</li>
				</c:if>
				
				<c:if test="${pb.currPage!=1 }">
					<li>
					     <a href="${pageContext.request.contextPath }/permission/permissionList?currPage=${pb.currPage-1}&searchText=${searchText}" aria-label="Previous">
				    	 <span aria-hidden="true">&laquo;</span></a>
					</li>
				</c:if>
		<!-- 判断当前页是否是首页 -->
				
				
		<!-- 展示所有页面 -->
				<c:forEach begin="${pb.currPage-5>0?pb.currPage-5:1 }" end="${pb.currPage+4>pb.totalPage?pb.totalPage:pb.currPage+4 }" var="n">
				<!-- 判断是否是当前页 -->
					<c:if test="${pb.currPage==n }">
					  <li class="active"><a href="javascript:void(0)">${n }</a></li>
					</c:if>
					<c:if test="${pb.currPage!=n }">
					  <li><a href="${pageContext.request.contextPath }/permission/permissionList?currPage=${n}&searchText=${searchText}">${n }</a></li>
					</c:if> 
				</c:forEach>
				
				
		<!-- 判断是否是最后一页-->
				<c:if test="${pb.currPage==pb.totalPage }">
				<li class="disabled">
					<a href="javascript:void(0)" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
				</c:if>
				
				
				<c:if test="${pb.currPage!=pb.totalPage}">
				<li>
					<a href="${pageContext.request.contextPath }/permission/permissionList?currPage=${pb.currPage+1}&searchText=${searchText}" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
				</c:if>
		<!-- 判断是否是最后一页-->
			</ul>
		</div>
		<!-- 分页结束=======================        -->
</div>

<!-- 弹出删除确认框==================================================================== -->
<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 280px">
            <div class="modal-header">
               	 请确认
            </div>
            <form action="${pageContext.request.contextPath }/permission/deletePermission" method="post" id="deletePermissionForm">
                <input type="hidden" name="id" id="permissionid">
                <!-- 如果删除的数据只有一个，则跳转到上一页，如果数据多于一个则跳转到当页 -->
                <c:if test="${pb.list.size()<=1 }">
                	<input type="hidden" name="currPage" id="currPage" value="${pb.currPage-1 }">
                </c:if>
                <c:if test="${pb.list.size()>1 }">
                	<input type="hidden" name="currPage" id="currPage" value="${pb.currPage }">
                </c:if>
                
                <input type="hidden" name="searchText" id="searchText" value="${searchText }">
                <div class="modal-body">
                    	确认删除该权限吗？
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <a class="btn btn-danger btn-ok" onclick="document:deletePermissionForm.submit();">删除</a>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 弹出删除确认框==================================================================== -->


<!-- 弹出修改权限的框========================================================= -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content" style="width:400px;height: 260px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改权限</h4>
				</div>
				<form action="${pageContext.request.contextPath }/permission/updatePermission" method="post" id="updatePermission">
					<div class="modal-body">
						<div class="form-group">
							<label for="txt_departmentlevel">角色名称</label> 
							<input type="hidden" name="id" class="" id="id" >
							<input type="hidden" name="currPage" id="currPage" value="${pb.currPage }">
							<input type="hidden" name="searchText" id="searchText" value="${searchText }">
							<input type="text"
								name="name" class="form-control"
								id="txt_departmentlevel">
							<label for="txt_departmentlevel">权限资源</label>
							<input type="text"
								name="url" class="form-control"
								id="txt_url">
						</div>
						<div class="modal-footer">
							<a class="btn btn-default" data-dismiss="modal"><span
								class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</a> <a
								id="btn_submit" class="btn btn-primary"  data-dismiss="modal" onclick="document:updatePermission.submit();" ><span
								class="glyphicon glyphicon-floppy-disk" aria-hidden="true" ></span>保存</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
<!--  弹出修改权限的框====================================================================-->


<!-- 弹出添加权限的框========================================================= -->
	<div class="modal fade" id="addPermission" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content" style="width:400px;height: 260px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加权限</h4>
				</div>
				<form action="${pageContext.request.contextPath }/permission/addPermission" method="post" id="addPermissionForm">
					<div class="modal-body">
						<div class="form-group">
							<label for=""name"">权限名称</label> 
							<input type="text"
								name="name" class="form-control"
								id="name">
							<label for="url">权限资源</label> 
							<input type="text"
								name="url" class="form-control"
								id="url">
							<!-- 显示添加后的页面 -->
							<c:if test="${pb.list.size()+1>pb.pageSize}">
								<input type="hidden" id="currPage" name="currPage" value="${pb.totalPage+1 }">
							</c:if>
							<c:if test="${pb.list.size()+1<=pb.pageSize}">
								<input type="hidden" id="currPage" name="currPage" value="${pb.totalPage }">
							</c:if>
							
							<input type="hidden" id="searchText" name="searchText" value="${searchText }">
						</div>
						<div class="modal-footer">
							<a class="btn btn-default" data-dismiss="modal"><span
								class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</a> <a
								id="btn_submit" class="btn btn-primary"  data-dismiss="modal" onclick="document:addPermissionForm.submit();" ><span
								class="glyphicon glyphicon-floppy-disk" aria-hidden="true" ></span>添加</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
<!-- 弹出添加权限的框====================================================================-->

<!-- 弹出批量删除确认框==================================================================== -->
<div class="modal fade" id="confirm-deleteChecked" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 280px">
            <div class="modal-header">
               	 请确认
            </div>
                <input type="hidden" name="id" id="roleid">
                <input type="hidden" name="currPage" id="currPage" value="1">
                <input type="hidden" name="searchText" id="searchText" value="${searchText }">
                <div class="modal-body">
                    	确认删除所选权限吗？
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <a class="btn btn-danger btn-ok" onclick="delCheck()">删除权限</a>
                </div>
        </div>
    </div>
</div>

<!-- 弹出批量删除确认框==================================================================== -->
</body>

<script>

	//弹出修改按钮确认传值
	function permissionModal(id,name,url)
	{
		document.getElementById("txt_departmentlevel").value=name;
        document.getElementById("id").value=id;
        document.getElementById("txt_url").value=url;
	}
	//弹出删除确认按钮传id
    function activeDeleteButton(permissionid)
    {
       	document.getElementById("permissionid").value=permissionid;
    }
    //批量删除
	function delCheck()
    {
		document.getElementById("delteChecked").submit();
    }
    
    //全选复选框
    //全选
    function checkAll(obj){
	//获取所有的复选框
	var arr=document.getElementsByName("pid");
		
	//遍历数组 修改选中状态
	for(var i=0;i<arr.length;i++){
		arr[i].checked=obj.checked;
	}
	}
		
       
</script>
</html>
