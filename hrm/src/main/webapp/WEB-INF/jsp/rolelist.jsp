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
		<h2>角色列表</h2>
		<hr>
	<div  class="form-inline">
			<form action="${pageContext.request.contextPath }/roles/findRoleByPage?currPage=1" method="post" >
				<div clss="well">
					<div class="form-group">
						<input type="text" class="form-control" style="width: 300px;"
							value="" name="searchText" id="findContent"
							placeholder="输入角色名称">
					</div>
					<span class="">
						<button type="submit" class="btn btn-primary">查询</button> <a
						class="btn btn-success" onclick="$('#addrole').modal();">增加角色</a>
						<a  class="btn  btn-danger" onclick="$('#confirm-deleteChecked').modal();">批量删除</a>
					</span>
				</div>
			</form>
				
				<hr>
					<table class="table table-bordered">
						<tr>
							<th><input type="checkbox" onclick="checkAll(this)"></th>
							<th>序号</th>
							<th>角色名称</th>
							<th>操作</th>
						</tr>
						<c:if test="${rb.currPage==1 }">
							<tr>
								<td></td>
								<!-- <input value="1" check='box' type="checkbox"  /> -->
								<td>0</td>
								<td>系统管理员</td>
								<td>-</td>
						    </tr>
						</c:if>
						
						<form id="delteChecked" action="${pageContext.request.contextPath }/roles/delCheckedRoles" method="post">
							<input type="hidden" name="currPage" id="currPage" value="1">
                			<input type="hidden" name="searchText" id="searchText" value="${searchText }">
							<c:forEach items="${rb.list }" var="role" varStatus="status">
								<tr>
									<td><input value=${role.id } check='box' type="checkbox" name="rid" /></td>
									<td>${status.index+1 }</td>
									<td>${role.description}</td>
									<td>
										<i class="glyphicon glyphicon-remove"></i>
										<a data-toggle="modal" data-target="#confirm-delete" onclick="activeDeleteButton('${role.id}')">删除</a> 
										<i class="glyphicon glyphicon-pencil"></i>
										<a data-toggle="modal" data-target="#myModal" onclick="roleModal('${role.id}','${role.description}')">修改</a>
									</td>
								</tr>
							</c:forEach>
						</form>
					</table>
	</div>
	<!--分页 -->
		<div style="width:380px;margin:0 auto;margin-top:50px;">
			<ul class="pagination" style="text-align:center; margin-top:10px;">
			
			
		<!-- 判断当前页是否是首页 -->
			    <c:if test="${rb.currPage==1 }">
					<li class="disabled">
					     <a href="javascript:void(0)" aria-label="Previous">
				    	 <span aria-hidden="true">&laquo;</span></a>
					</li>
				</c:if>
				
				<c:if test="${rb.currPage!=1 }">
					<li>
					     <a href="${pageContext.request.contextPath }/roles/findRoleByPage?currPage=${rb.currPage-1}&searchText=${searchText}" aria-label="Previous">
				    	 <span aria-hidden="true">&laquo;</span></a>
					</li>
				</c:if>
		<!-- 判断当前页是否是首页 -->
				
				
		<!-- 展示所有页面 -->
				<c:forEach begin="${rb.currPage-5>0?rb.currPage-5:1 }" end="${rb.currPage+4>rb.totalPage?rb.totalPage:rb.currPage+4 }" var="n">
				<!-- 判断是否是当前页 -->
					<c:if test="${rb.currPage==n }">
					  <li class="active"><a href="javascript:void(0)">${n }</a></li>
					</c:if>
					<c:if test="${rb.currPage!=n }">
					  <li><a href="${pageContext.request.contextPath }/roles/findRoleByPage?currPage=${n}&searchText=${searchText}">${n }</a></li>
					</c:if> 
				</c:forEach>
				
				
		<!-- 判断是否是最后一页-->
				<c:if test="${rb.currPage==rb.totalPage }">
				<li class="disabled">
					<a href="javascript:void(0)" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
				</c:if>
				
				
				<c:if test="${rb.currPage!=rb.totalPage}">
				<li>
					<a href="${pageContext.request.contextPath }/roles/findRoleByPage?currPage=${rb.currPage+1}&searchText=${searchText}" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
				</c:if>
		<!-- 判断是否是最后一页-->
			</ul>
		</div>
		<!-- 分页结束=======================        -->
	



	<!-- 弹出修改角色的框========================================================= -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content" style="width:400px;height: 200px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改角色</h4>
				</div>
				<form action="${pageContext.request.contextPath }/roles/updateRole" method="post" id="updateRole">
					<div class="modal-body">
						<div class="form-group">
							<label for="txt_departmentlevel">角色名称</label> 
							<input type="hidden" name="id" class="" id="id" >
							<input type="hidden" name="currPage" id="currPage" value="${rb.currPage }">
							<input type="hidden" name="searchText" id="searchText" value="${searchText }">
							<input type="text"
								name="description" class="form-control"
								id="txt_departmentlevel">
						</div>
						<div class="modal-footer">
							<a class="btn btn-default" data-dismiss="modal"><span
								class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</a> <a
								id="btn_submit" class="btn btn-primary"  data-dismiss="modal" onclick="document:updateRole.submit();" ><span
								class="glyphicon glyphicon-floppy-disk" aria-hidden="true" ></span>保存</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
<!-- 弹出修改角色的框====================================================================-->


<!-- 弹出添加角色的框========================================================= -->
	<div class="modal fade" id="addrole" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content" style="width:400px;height: 200px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加角色</h4>
				</div>
				<form action="${pageContext.request.contextPath }/roles/addRole" method="post" id="addRole">
					<div class="modal-body">
						<div class="form-group">
							<label for="txt_departmentlevel">角色名称</label> 
							<input type="text"
								name="description" class="form-control"
								id="txt_departmentlevel">
								
							<c:if test="${rb.list.size()+1>rb.pageSize}">
								<input type="hidden" id="currPage" name="currPage" value="${rb.totalPage+1 }">
							</c:if>
							<c:if test="${rb.list.size()+1<=rb.pageSize}">
								<input type="hidden" id="currPage" name="currPage" value="${rb.totalPage }">
							</c:if>
							<input type="hidden" id="searchText" name="searchText" value="${searchText }">
						</div>
						<div class="modal-footer">
							<a class="btn btn-default" data-dismiss="modal"><span
								class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</a> <a
								id="btn_submit" class="btn btn-primary"  data-dismiss="modal" onclick="document:addRole.submit();" ><span
								class="glyphicon glyphicon-floppy-disk" aria-hidden="true" ></span>添加</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
<!-- 弹出添加角色的框====================================================================-->



<!-- 弹出删除确认框==================================================================== -->
<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 280px">
            <div class="modal-header">
               	 请确认
            </div>
            <form action="${pageContext.request.contextPath }/roles/deleteRole" method="post" id="deleteRoleFromId">
            	 <c:if test="${rb.list.size()<=1 }">
                	<input type="hidden" name="currPage" id="currPage" value="${rb.currPage-1 }">
                </c:if>
                <c:if test="${rb.list.size()>1 }">
                	<input type="hidden" name="currPage" id="currPage" value="${rb.currPage }">
                </c:if>
                
                <input type="hidden" name="id" id="roleid">
                <input type="hidden" name="searchText" id="searchText" value="${searchText }">
                <div class="modal-body">
                    	确认删除该角色吗？
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <a class="btn btn-danger btn-ok" onclick="document:deleteRoleFromId.submit();">删除角色</a>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 弹出删除确认框==================================================================== -->



<!-- 弹出批量删除确认框==================================================================== -->
<div class="modal fade" id="confirm-deleteChecked" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 280px">
            <div class="modal-header">
               	 请确认
            </div>
<%--             <form action="${pageContext.request.contextPath }/roles/deleteRole" method="post" id="deleteRole"> --%>
                <input type="hidden" name="id" id="roleid">
                <input type="hidden" name="currPage" id="currPage" value="1">
                <input type="hidden" name="searchText" id="searchText" value="${searchText }">
                <div class="modal-body">
                    	确认删除所选角色吗？
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <a class="btn btn-danger btn-ok" onclick="delCheck()">删除角色</a>
                </div>
<!--             </form> -->
        </div>
    </div>
</div>

<!-- 弹出批量删除确认框==================================================================== -->
</body>


<script>
	
		//弹出修改按钮确认传值
       function roleModal(id,description)
       {
            document.getElementById("txt_departmentlevel").value=description;
           	document.getElementById("id").value=id;
           	
       }
       
       //弹出删除确认按钮传id
       function activeDeleteButton(roleid)
       {
       		document.getElementById("roleid").value=roleid;
       }
       
       //全选复选框
       //全选
		function checkAll(obj){
			//获取所有的复选框
			var arr=document.getElementsByName("rid");
		
			//遍历数组 修改选中状态
			for(var i=0;i<arr.length;i++){
			arr[i].checked=obj.checked;
		}
		}
		
		//批量删除
		function delCheck()
       {
			document.getElementById("delteChecked").submit();
       }
       
</script>
</html>
