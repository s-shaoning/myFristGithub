package com.itheima.Jedis;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.itheima.utils.RedisUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Redis_Jedis {

	@SuppressWarnings("resource")
	@Test
	public void test01(){
		
		Jedis jedis = new Jedis("192.168.87.128", 6379);
		
		List<String> list = jedis.lrange("mylist", 0, -1);
		
		System.out.println(list);
	}
	
	@SuppressWarnings("resource")
	@Test
	public void test02(){
		//连接池的详细配置
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(100);
		
		//获取连接池对象
		JedisPool pool = new JedisPool(poolConfig, "192.168.87.128", 6379);
		
		//通过连接池对象获取jedis对象
		Jedis jedis = pool.getResource();
		
		try {
			
			Set<String> set = jedis.smembers("myset");
			
			for (String string : set) {
				System.out.println(string);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(jedis!=null){
				jedis.close();
			}
			if(pool!=null){
				pool.close();
			}
		}
	}
	
	@Test
	public void test03(){
		
		Jedis jedis = RedisUtils.getResource();
		
		try {
			Set<String> set = jedis.zrange("myzset", 0, -1);
			
			jedis.zadd("myzset", 55, "6666666666");
			for (String string : set) {
				
				System.out.println(string);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			if(jedis!=null){
				jedis.close();
			}
			
		}
		
	}
	
}






