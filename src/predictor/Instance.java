package predictor;

import java.util.LinkedHashMap;
import java.lang.String;
import java.lang.Object;
import java.util.Set;

/**
 * インスタンスを管理する。
 * LinkedHashMapとの違いは,
 * 属性値にはintかstringかdoubleのみが許されること。
 * Keyは文字列であること。の二つ。
 * InstanceIDはこれに持たせる必要がないかもしれないので、持たせていない。
 * @author artul
 *
 */
public class Instance extends LinkedHashMap<String, Object> {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public Instance() {}

	public Object put(String attribute_name, Object value) {
		try {
			if (!(value instanceof Integer) && !(value instanceof String) && !(value instanceof Double)) {
				throw new Exception();
			}
			else {
				return super.put(attribute_name, value);
			}
		} catch(Exception e) {
			System.err.println("Error, integer");
		}
		return super.put(attribute_name, value);
	}
	public Set<String> attributeNames() {
		return this.keySet();
	}
}
