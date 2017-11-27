package schedule;

public enum Month{
	JANUARY(0, 31, 0, 1), 
	FEBRUARY(1, 28, 3, 1), 
	MARCH(2, 31, 3, 1), 
	APRIL(3, 30, 6, 1), 
	MAY(4, 31, 1, 2), 
	JUNE(5, 30, 4, 2), 
	JULY(6, 31, 6, 2), 
	AUGUST(7, 31, 2, 2), 
	SEPTEMBER(8, 30, 5, 3), 
	OCTOBER(9, 31, 0, 3), 
	NOVEMBER(10, 30, 3, 3), 
	DECEMBER(11, 31, 5, 3);
	
	private final int monthIndex;
	private final int days;
	private final int indexStartDay;
	private final int semester;
	
	Month(int monthIndex, int days, int indexStartDay, int semester) {
		this.monthIndex = monthIndex;
		this.days = days;
		this.indexStartDay = indexStartDay;
		this.semester = semester;
	}

	public int getMonthIndex() {
		return monthIndex;
	}

	public int getDays() {
		return days;
	}

	public int getIndexStartDay() {
		return indexStartDay;
	}

	public int getSemester() {
		return semester;
	}
	
}