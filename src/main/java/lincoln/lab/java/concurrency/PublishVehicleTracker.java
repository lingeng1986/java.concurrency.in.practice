package lincoln.lab.java.concurrency;

import java.util.Collections;
import java.util.Map;

/**
 * getLocations时返回一个不可修改的map副本，调用者不能增删车辆
 * 
 * @author geng.lin
 * 
 */
public class PublishVehicleTracker {

    private final Map<String, String> locations;

    private final Map<String, String> unmodifiableMap;

    public PublishVehicleTracker(Map<String, String> locations) {
	this.locations = locations;
	this.unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, String> getlocations() {
	return unmodifiableMap;
    }

    public String getLocation(String id) {
	return locations.get(id);
    }

    public void setLocation(String id, String location) {
	if (!locations.containsKey(id)) {
	    throw new IllegalArgumentException("invalid vehicle name:" + id);
	}
	locations.put(id, location);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	// TODO Auto-generated method stub

    }

}
