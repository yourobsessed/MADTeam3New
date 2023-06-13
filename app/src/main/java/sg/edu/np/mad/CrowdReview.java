package sg.edu.np.mad;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class CrowdReview {
    String foodcourt;
    int crowd;
    int time;

    public CrowdReview(String foodcourt, int crowd, int time) {
        this.foodcourt = foodcourt;
        this.crowd = crowd;
        this.time = time;
    }
}
