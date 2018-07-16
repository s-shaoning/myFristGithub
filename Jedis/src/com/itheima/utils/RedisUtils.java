package com.itheima.utils;

import java.util.ResourceBundle;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {

	private static JedisPool pool;
	
	static{
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(100);
		
		ResourceBundle bundle = ResourceBundle.getBundle("redis");
		
		String host = bundle.getString("host");
		int port =Integer.valueOf(bundle.getString("port"));
		
		pool = new JedisPool(config, host, port);
		
	}
	
	
	
	public static Jedis getResource(){
		
		return pool.getResource();
	}
}
