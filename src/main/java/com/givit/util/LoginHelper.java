package com.givit.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.internal.LinkedTreeMap;


public class LoginHelper {
	
	public static Map<String, String> getParamMap(List<LinkedTreeMap<String, String>> params) {
		Map<String, String> map = new HashMap<String, String>();
        LinkedTreeMap<String, String> treeMap = params.get(0);
        for(Map.Entry<String, String> entry :treeMap.entrySet()){
        		map.put(entry.getKey(), entry.getValue());
        }
        return map;
	}
}
