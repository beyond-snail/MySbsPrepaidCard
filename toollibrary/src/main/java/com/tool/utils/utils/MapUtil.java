package com.tool.utils.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MapUtil {

	/**
	 * Map key 排序
	 * 
	 * @param map
	 * @return
	 */
	public static Map<String, String> order(Map<String, String> map) {
		HashMap<String, String> tempMap = new LinkedHashMap<String, String>();
		List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(map.entrySet());

		Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
			public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
				return (o1.getKey()).toString().compareTo(o2.getKey());
			}
		});

		for (int i = 0; i < infoIds.size(); i++) {
			Map.Entry<String, String> item = infoIds.get(i);
			tempMap.put(item.getKey(), item.getValue());
		}
		return tempMap;
	}

	/**
	 * map转成string
	 * 
	 * @param paramsMap
	 * @return
	 */
	public static String mapToStr(Map<String, Object> paramsMap) {
		StringBuilder stringBuilder = new StringBuilder();
		for (String key : paramsMap.keySet()) {
			stringBuilder.append(key);
			stringBuilder.append("=");
			stringBuilder.append(paramsMap.get(key));
			stringBuilder.append("&");
		}
		return stringBuilder.substring(0, stringBuilder.length() - 1);
	}

	/**
	 * map转成string，value为空不参与转成
	 * 
	 * @param paramsMap
	 * @return
	 */
	public static String mapToStrValueNotNull(Map<String, String> paramsMap) {
		StringBuilder stringBuilder = new StringBuilder();
		for (String key : paramsMap.keySet()) {
			if (!isEmpty(paramsMap.get(key))) {
				stringBuilder.append(key);
				stringBuilder.append("=");
				stringBuilder.append(paramsMap.get(key));
				stringBuilder.append("&");
			}
		}
		return stringBuilder.substring(0, stringBuilder.length() - 1);
	}

	public static boolean isEmpty(Object str) {
		if (str == null || str.equals(""))
			return true;
		else
			return false;
	}

	/**
	 * 转换对象为map
	 * 
	 * @param object
	 * @param ignore
	 * @return
	 */
	public static Map<String, String> objectToMap(Object object, String... ignore) {
		Map<String, String> tempMap = new LinkedHashMap<String, String>();
		for (Field f : object.getClass().getDeclaredFields()) {
			if (!f.isAccessible()) {
				f.setAccessible(true);
			}
			boolean ig = false;
			if (ignore != null && ignore.length > 0) {
				for (String i : ignore) {
					if (i.equals(f.getName())) {
						ig = true;
						break;
					}
				}
			}
			if (ig) {
				continue;
			} else {
				Object o = null;
				try {
					o = f.get(object);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				tempMap.put(f.getName(), o == null ? "" : o.toString());
			}
		}
		return tempMap;
	}

	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {    
        if (map == null)  
            return null;  
  
        Object obj = beanClass.newInstance();  
  
        org.apache.commons.beanutils.BeanUtils.populate(obj, map);  
  
        return obj;  
    }    

	/**
	 * url 参数串连
	 * 
	 * @param map
	 * @param keyLower
	 * @param valueUrlencode
	 * @return
	 */
	public static String mapJoin(Map<String, String> map, boolean keyLower, boolean valueUrlencode) {
		StringBuilder stringBuilder = new StringBuilder();
		for (String key : map.keySet()) {
			if (map.get(key) != null && !"".equals(map.get(key))) {
				try {
					String temp = (key.endsWith("_") && key.length() > 1) ? key.substring(0, key.length() - 1) : key;
					stringBuilder.append(keyLower ? temp.toLowerCase() : temp)
							.append("=").append(valueUrlencode
									? URLEncoder.encode(map.get(key), "utf-8").replace("+", "%20") : map.get(key))
							.append("&");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		if (stringBuilder.length() > 0) {
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		}
		return stringBuilder.toString();
	}

	/**
	 * url 参数串连
	 * 
	 * @param map
	 * @param keyLower
	 * @param valueUrlencode
	 * @return
	 */
	public static String mapBlankJoin(Map<String, String> map, boolean keyLower, boolean valueUrlencode) {
		StringBuilder stringBuilder = new StringBuilder();
		for (String key : map.keySet()) {
			if (map.get(key) != null) {
				try {
					String temp = (key.endsWith("_") && key.length() > 1) ? key.substring(0, key.length() - 1) : key;
					stringBuilder.append(keyLower ? temp.toLowerCase() : temp)
							.append("=").append(valueUrlencode
									? URLEncoder.encode(map.get(key), "utf-8").replace("+", "%20") : map.get(key))
							.append("&");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		if (stringBuilder.length() > 0) {
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		}
		return stringBuilder.toString();
	}

	/**
	 * 简单 xml 转换为 Map
	 * 
	 * @return
	 */
	public static Map<String, String> xmlToMap(String xml) {
		try {
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = (Document) documentBuilder.parse(new ByteArrayInputStream(xml.getBytes()));
			Element element = (Element) ((org.w3c.dom.Document) document).getDocumentElement();
			NodeList nodeList = ((Node) element).getChildNodes();
			Map<String, String> map = new LinkedHashMap<String, String>();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element e = (Element) nodeList.item(i);
				map.put(((Node) e).getNodeName(), ((Node) e).getNodeValue());
			}
			return map;
		} catch (DOMException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void mapToXML(Map<String, String> map, StringBuffer sb) {
		Set<String> set = map.keySet();
		for (Iterator<String> it = set.iterator(); it.hasNext();) {
			String key = (String) it.next();
			Object value = map.get(key);
			if (null == value)
				value = "";
			sb.append("<" + key + ">" + value + "</" + key + ">");
		}
	}

	public static Map<String, String> xmlStringToMap(String xmlContent) throws DocumentException {
		Document doc = DocumentHelper.parseText(xmlContent);
		Map<String, String> map = new HashMap<String, String>();
		if (doc == null)
			return map;
		Element root = doc.getRootElement();
		for (Iterator<Element> iterator = root.elementIterator(); iterator.hasNext();) {
			Element e = (Element) iterator.next();
			map.put(e.getName(), e.getText());
		}
		return map;
	}



	/**
	 * 移除map的空key
	 * @param map
	 * @return
	 */
	public static void removeNullKey(Map map){
		Set set = map.keySet();
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			Object obj = (Object) iterator.next();
			remove(obj, iterator);
		}
	}

	/**
	 * 移除map中的value空值
	 * @param map
	 * @return
	 */
	public static void removeNullValue(Map map){
		Set set = map.keySet();
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			Object obj = (Object) iterator.next();
			Object value =(Object)map.get(obj);
			remove(value, iterator);
		}
	}

	/**
	 * Iterator 是工作在一个独立的线程中，并且拥有一个 mutex 锁。
	 * Iterator 被创建之后会建立一个指向原来对象的单链索引表，当原来的对象数量发生变化时，这个索引表的内容不会同步改变，
	 * 所以当索引指针往后移动的时候就找不到要迭代的对象，所以按照 fail-fast 原则 Iterator 会马上抛出 java.util.ConcurrentModificationException 异常。
	 * 所以 Iterator 在工作的时候是不允许被迭代的对象被改变的。
	 * 但你可以使用 Iterator 本身的方法 remove() 来删除对象， Iterator.remove() 方法会在删除当前迭代对象的同时维护索引的一致性。
	 * @param obj
	 * @param iterator
	 */
	private static void remove(Object obj,Iterator iterator){
		if(obj instanceof String){
			String str = (String)obj;
			if(StringUtils.isEmpty(str)){
				iterator.remove();
			}
		}else if(obj instanceof Collection){
			Collection col = (Collection)obj;
			if(col==null||col.isEmpty()){
				iterator.remove();
			}

		}else if(obj instanceof Map){
			Map temp = (Map)obj;
			if(temp==null||temp.isEmpty()){
				iterator.remove();
			}

		}else if(obj instanceof Object[]){
			Object[] array =(Object[])obj;
			if(array==null||array.length<=0){
				iterator.remove();
			}
		}else{
			if(obj==null){
				iterator.remove();
			}
		}
	}


}
