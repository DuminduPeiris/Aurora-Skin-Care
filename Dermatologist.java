import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dermatologist {
    private String name;
    private final Map<DayOfWeek, List<String>> schedule;

    public Dermatologist(String name) {
        this.name = name;
        this.schedule = new HashMap<>();
        setupSchedule();
    }

    private void setupSchedule() {
        schedule.put(DayOfWeek.MONDAY, List.of("10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12.00", "12.15","12.30","12.45"));
        schedule.put(DayOfWeek.WEDNESDAY, List.of("14:00", "14:15", "14:30", "14:45", "15:00", "15:15", "15:30", "15:45","16.00", "16.15","16.30","16.45"));
        schedule.put(DayOfWeek.FRIDAY, List.of("16:00", "16:15", "16:30", "16:45", "17:00", "17:15", "17:30", "17:45", "18:00", "18:15", "18:30", "18:45", "19.00", "19.15", "19.30", "19.45"));
        schedule.put(DayOfWeek.SATURDAY, List.of("09:00", "09:15", "09:30", "09:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12.00", "12.15", "12.30", "12.45"));
    }

    public String getName() {
        return name;
    }

    public Map<DayOfWeek, List<String>> getSchedule() {
        return schedule;
    }
}
