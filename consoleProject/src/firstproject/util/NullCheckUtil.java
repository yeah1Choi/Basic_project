package firstproject.util;

import java.util.List;
import java.util.Map;

public class NullCheckUtil {

	/**
	 * Null-safe check if the specified map is empty.
	 * <p>
	 * Null returns true.
	 *
	 * @param map  the map to check, may be null
	 * @return true if empty or null
	 * @since 3.2
	 */
	public static boolean isEmpty (final Map<?, ?> map){
	    return map == null || map.isEmpty();
	}
	
	public static boolean isEmpty (final List<?> list){
	    return list == null || list.isEmpty();
	}
	
}

