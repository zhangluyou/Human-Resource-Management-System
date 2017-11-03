package test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jointem.hrm.dao.RolesMapper;
import com.jointem.hrm.dao.UsersDao;
import com.jointem.hrm.entity.Educate;
import com.jointem.hrm.entity.PageBean;
import com.jointem.hrm.entity.Permissions;
import com.jointem.hrm.entity.Roles;
import com.jointem.hrm.entity.Users;
import com.jointem.hrm.service.EducateService;
import com.jointem.hrm.service.PermissionService;
import com.jointem.hrm.service.RoleService;
import com.jointem.hrm.service.UserService;

/**
 * Created by dartagnan on 17/7/28.
 */
public class UserTest extends Junit4Test {
    @Autowired
    private UserService userService;
    @Autowired
    private EducateService educateService;
    @Autowired
    private UsersDao usesDao;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RolesMapper rolesMapper;
    @Autowired
    private PermissionService permissionService;
    
    @Test
    public void test(){
        Users users = userService.getByUsername("admin");

//		Users users = userService.selectUser();
		System.out.println(users);

	}

	@Test
	public void test1() throws ParseException{
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate1 = dateFormat1.parse("2009-06-01");
		Educate educate1=new Educate();
		educate1.setName("abc");
		educate1.setPurpose("提高英语水平");
		educate1.setBegintime(myDate1);
		Date date=new Date();
		educate1.setEndtime(myDate1);
		educate1.setCreatetime(new Date());
		educate1.setDatum("《spring讲义》");
		Byte b=new Byte("1");
		educate1.setEducate(b);
		educate1.setTeacher("尚硅谷");
		educate1.setStudent("崔晓东");
		educate1.setEffect("好");
		educate1.setSummarize("sd");
		System.out.println(educateService.saveEducate(educate1));
//        System.out.println(educateService.findEducate());
	}
	@Test
	public void testFindByName()
	{
//    	String name="培";
//		List<Educate> list=educateService.getEducateByName(name);
//		for (Educate educate : list) {
//			System.err.println(educate);
//		}

	}
	//测试通过姓名查询用户角色以及权限集合
	@Test
	public void testFindUsersByName()
	{
		try {
//    		List<Users> list = usesDao.findUsersByName("testAA");
//        	System.out.println(list);
			Users user=usesDao.findUsersByName("TestAA");
			for(Roles role:user.getRolesList())
			{
				System.out.println(role.getDescription());
				System.out.println("==========");
				for(Permissions permission:role.getPermissionsList())
				{
					System.out.println(permission.getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void finUsersAndRolesList()
	{
		try {
//    	roleService
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("start", 0);
			params.put("pageSize", 5);


		List<Users> list=rolesMapper.finUsersAndRolesList(params);
		
		System.out.println(list);
    	} catch (Exception e) {

			e.printStackTrace();
		}

	}
    @Test
    public void RoleAndPermissionList()
    {
    	PageBean<Roles> bean=null;
    	try {
			bean=permissionService.findRolesAndPermissionByPage(1, 4, "");
			System.out.println(bean.getList());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
