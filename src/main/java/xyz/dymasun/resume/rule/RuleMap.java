package xyz.dymasun.resume.rule;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class RuleMap extends BaseRule{
	private Map<String, Object> map = null;

	private RuleMap(){
		map = new HashMap<String, Object>();
	}

	/**
	 * 创建实例
	 * @return
	 */
	public static RuleMap create(){	return new RuleMap(); }

	/**
	 * 将键和值放入map中
	 * @param k
	 * @param v
	 * @return
	 */
	public RuleMap put(String k,Object v){
		if(v!=null)this.map.put(k, v);
		return this;
	}

	/**
	 * 将另一个实例作为map的值来存储
	 * @param k
	 * @param map
	 * @return
	 */
	public RuleMap put(String k,RuleMap map){
		this.put(k, map.getMap());
		return this;
	}

	/**
	 * 合并两个map
	 * @param pmap
	 * @return
	 */
	public RuleMap put(Map<String,Object> pmap){
		for (String key:pmap.keySet()) {
			this.put(key,pmap.get(key));
		}
		return this;
	}

	/**
	 * 合并两个rulemap
	 * @param ruleMap
	 * @return
	 */
	public RuleMap put(RuleMap ruleMap){
		Map<String,Object> rmap = ruleMap.getMap();
		this.put(rmap);
		return this;
	}
	public RuleMap put(String key,RuleList list){
		this.map.put(key,list.toArray());
		return this;
	}
	/**
	 * 转化为RuleResult
	 * @return
	 */
	public RuleResult result(){
	    return RuleResult.success(map);
    }

	/**
	 * 先转化为ruleresult再转化为json
	 * @return
	 */
	@Override
	public String toJSON() {
		return result().toJSON();
	}

	/**
	 * toString 转化为json
	 * @return
	 */
	@Override
	public String toString() {
		return JSONObject.toJSONString(map);
	}

	/**
	 * 将obj作为Map合并进map中
	 * @param obj
	 * @return
	 */
	public RuleMap put(Object obj){
		try {
			Map<String, Object> map = RuleMap.objectToMap(obj);
			this.put(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	/**
	 * 供访问其他实例的map使用
	 * @return
	 */
	public Map<String, Object> getMap(){
		return this.map;
	}

	/**
	 * 对象转map
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> objectToMap(Object obj) throws Exception {
		if(obj == null)return null;
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] declaredFields = obj.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			field.setAccessible(true);
			map.put(field.getName(), field.get(obj));
		}
		return map;
	}
	/**
	 * map转对象
	 * @param map
	 * @param beanClass
	 * @return
	 * @throws Exception
	 */
	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
		if (map == null)return null;
		Object obj = beanClass.newInstance();
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			int mod = field.getModifiers();
			if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){
				continue;
			}
			field.setAccessible(true);
			field.set(obj, map.get(field.getName()));
		}
		return obj;
	}
	public Object get(String key){
		return map.get(key);
	}
}
