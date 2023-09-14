package firstproject.util;

import java.util.List;
import java.util.Map;

public class GapUtil {
	
	private static GapUtil instance = null;
	private GapUtil() {}
	public static GapUtil getInstance() {
		if(instance==null) instance = new GapUtil();
		return instance;
	}

	public int gapCnt(String name) {
		int len = 0;
		String nameStr = name;
		len = nameStr.length();
		return len;
	}
	
	public int gapCnt(Map<String, Object> param, String name) {
		int len = 0;
		String nameStr = String.valueOf(param.get(name));
		len = nameStr.length();
		return len;
	}
	
	public int gapFullCnt(String name) {
		int len = 0;
		int nameLen = name.length();
		if(nameLen > len) {
			len = nameLen;
		}
		len += 2;
		return len;
	}
	
	public int gapFullCnt(List<Map<String, Object>> param, String name) {
		int len = 0;
		for(Map<String, Object> item : param) {
			String nameStr = String.valueOf(item.get(name));
			int nameLen = nameStr.length();
			if(nameLen > len) {
				len = nameLen;
			}
		}
		return len;
	}
	
	public StringBuilder gapFullSpace(List<Map<String, Object>> param, String name, int len) {
		StringBuilder strLeng = null;
		for(Map<String, Object> item : param) {
			String str = String.valueOf(item.get(name));
			strLeng = new StringBuilder(str);
			for(int i=1; i<len; i++) {
				strLeng.append("\0");
//				strLeng.append(" ");
			}
		}
		return strLeng;
	}
	
	public StringBuilder gapFullSpace(String param, int maxlen, int len) {
		StringBuilder strLeng = null;
		strLeng = new StringBuilder(param);
		int minuslen = 0;
		if(maxlen < len || maxlen == len) {
			minuslen = 2;
		}else {
			minuslen = (maxlen - len) + 2;
		}
		for(int i=0; i<minuslen; i++) {
			strLeng.append("\0");
//			strLeng.append(" ");
		}
		return strLeng;
	}
	
}
