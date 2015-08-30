package com.dxsfw.common.util;

import java.util.UUID;

/**
 * @author 
 */
public class PrimaryKeyGeneration {
	   
	/**
     * getPK,获得数据库使用的一个long型唯一主键
     * 16位，同一微秒内3000个不会重复
     */
    private static long[] ls = new long[3000];
    private static int li = 0;
    private synchronized static long getPK(){
        long lo = getpk();
        for (int i = 0; i < 3000; i++){
            long lt = ls[i];
            if (lt == lo){
                lo = getPK();
                break;
            }
        }
        ls[li] = lo;
        li++;
        if(li == 3000){
         li = 0;
        }
        return lo;
    }
    /**
     * 获得数据库使用的一个long型唯一主键
     * 16位，同一微秒内3000个不会重复
     * 修改者名字 修改日期
     * 修改内容   
     * @return String  
     */
    public synchronized static String getPrimaryKey(){
    	return String.valueOf(getPK());
    }
    
    
    private synchronized static long getpk(){
        String a = (String.valueOf(System.currentTimeMillis())).substring(3, 13);
        String d = (String.valueOf(Math.random())).substring(2, 8);
        return Long.parseLong(a + d);
    }

   /**
    * 生成36位通用唯一识别码，主要用于主键值的生成
    * 修改者名字 修改日期
    * 修改内容   
    * @return String  
    */
    public static String getLongUUID() {
    	UUID uuid = UUID.randomUUID();
    	String str = uuid.toString();
    	return str;
    }

	public static void main(String args[]){
		
	   for(int i=1;i<=10000;i++){
		  long primaryKey=getPK();
		  System.out.println(primaryKey);
	   }
	}
}
