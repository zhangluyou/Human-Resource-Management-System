package com.jointem.hrm.utils;

import java.util.Random;
import java.util.UUID;

//下面就是实现为数据库获取一个唯一的主键id的代码    
public class UUIDGenerator {    
    public UUIDGenerator() {    
    }    
    /**   
     * 获得一个UUID   
     * @return String UUID   
     */    
    public static String getUUID(){    
        String s = UUID.randomUUID().toString();    
        //去掉“-”符号    
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);    
    } 
    
    /**
	 * 随机生成名称（图片，视频等）
	 * @return 当前的毫秒+3位随机数
	 */
	public static String generateName() {
		//取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
		//加上三位随机数
		Random random = new Random();
		int end3 = random.nextInt(999);
		//如果不足三位前面补0
		String str = millis + String.format("%03d", end3);
		return str;
	}
  
    
    
    /**   
     * 获得指定数目的UUID   
     * @param number int 需要获得的UUID数量   
     * @return String[] UUID数组   
     */    
    public static String[] getUUID(int number){    
        if(number < 1){    
            return null;    
        }    
        String[] ss = new String[number];    
        for(int i=0;i<number;i++){    
            ss[i] = getUUID();    
        }    
        return ss;    
    }
}    

