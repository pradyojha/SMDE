package hub.experiment.strategy;


public class TimeUtility {

  public static int convertToSeconds(String time) {
    String[] parts = time.split(":");
    if (parts.length != 3) {
      throw new IllegalArgumentException("Time must be in HH:mm:ss format");
    }
    int hours = Integer.parseInt(parts[0]);
    int minutes = Integer.parseInt(parts[1]);
    int seconds = Integer.parseInt(parts[2]);
    return hours * 3600 + minutes * 60 + seconds;
  }

  public static String convertToTime(Integer totalSecs){
    Integer hours = totalSecs / 3600;
    Integer minutes = (totalSecs % 3600) / 60;
    Integer seconds = totalSecs % 60;

    String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
    return timeString;
  }
}
