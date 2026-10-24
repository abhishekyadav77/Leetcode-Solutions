class Solution {

    private final int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int startA = convertToDayOfYear(arriveAlice);
        int endA = convertToDayOfYear(leaveAlice);
        int startB = convertToDayOfYear(arriveBob);
        int endB = convertToDayOfYear(leaveBob);

        // Find the overlapping interval
        int latestStart = Math.max(startA, startB);
        int earliestEnd = Math.min(endA, endB);

        // Calculate overlap length if valid
        if (latestStart <= earliestEnd) {
            return earliestEnd - latestStart + 1;
        }
        
        return 0;
    }

    private int convertToDayOfYear(String date) {
        // Parse month and day from "MM-DD"
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        int totalDays = 0;
        // Sum up days of all full months before the current month
        for (int i = 0; i < month - 1; i++) {
            totalDays += daysInMonth[i];
        }
        // Add the remaining days in the current month
        totalDays += day;

        return totalDays;
    }
}