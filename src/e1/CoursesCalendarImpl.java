package e1;

import java.util.*;
import java.util.stream.Collectors;

public class CoursesCalendarImpl implements CoursesCalendar {
    private static final List<Integer> HOURS = Arrays.asList(9, 10, 11, 12, 14, 15, 16, 17);
    private static Map<Pair<Day, Room>, Map<Integer, String>> bookingMap;

    public CoursesCalendarImpl() {
        bookingMap = new HashMap<>();
    }

    @Override
    public List<Integer> possibleSlots() {
        return HOURS;
    }

    @Override
    public void bookRoom(Day d, Room r, int start, int duration, String course) {
        if (bookingMap.containsKey(new Pair<>(d, r))) {
            Map<Integer, String> bookingHours = bookingMap.get(new Pair<>(d, r));
            bookingMap.put(new Pair<>(d, r), addToMap(bookingHours, start, duration, course));
        } else {
            bookingMap.put(new Pair<>(d, r), addToMap(new HashMap<>(), start, duration, course));
        }

    }

    private Map<Integer, String> addToMap(Map<Integer, String> bookingHours, int start, int duration, String course) {
        Map<Integer, String> returnMap = new HashMap<>(bookingHours);
        for (int i = HOURS.indexOf(start); i < HOURS.indexOf(start) + duration; i++) {

            if (bookingHours.containsKey(HOURS.get(i))) throw new IllegalStateException();
            returnMap.put(HOURS.get(i), course);
        }
        return returnMap;
    }

    @Override
    public Set<Pair<Integer, String>> dayRoomSlots(Day d, Room r) {
        Map<Integer, String> hours = bookingMap.get(new Pair<>(d, r));
        Set<Pair<Integer, String>> returnSet = new HashSet<>();
        if (hours != null)
            returnSet = hours.entrySet().stream().map(el -> new Pair<>(el.getKey(), el.getValue())).collect(Collectors.toSet());
        return returnSet;
    }

    @Override
    public Map<Pair<Day, Room>, Set<Integer>> courseSlots(String course) {
        return bookingMap.entrySet().stream().filter(el -> el.getValue().containsValue(course))
                .collect(Collectors.toMap(Map.Entry::getKey, v -> v.getValue().keySet()));

    }
}
