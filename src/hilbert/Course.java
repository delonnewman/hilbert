package hilbert;

/**
 * Represents a course
 * 
 * @author Delon Newman
 */
public class Course {
	private final String name;
	private final String building;
	private final String room;
	private final String days;
	private final String startTime;
	private final String endTime;
	private final int    capacity;
	private final String faculty;
	
	public Course(String name, String building, String room, String days,
			String startTime, String endTime, int capacity, String faculty) {
		this.name      = name;
		this.building  = building;
		this.room      = room;
		this.days      = days;
		this.startTime = startTime;
		this.endTime   = endTime;
		this.capacity  = capacity;
		this.faculty   = faculty;
	}
	
	public String getName() { return this.name; }
	public String getBuilding() { return this.building; }
	public String getRoom() { return this.room; }
	public String getDays() { return this.days; }
	public String getStartTime() { return this.startTime; }
	public String getEndTime() { return this.endTime; }
	public int    getCapacity() { return this.capacity; }
	public String getFaculty() { return this.faculty; }
}
