package sg.edu.np.mad;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class CrowdReview {
    String foodcourt;
    int crowd;
    LocalDateTime time;

    public CrowdReview(String foodcourt, int crowd, LocalDateTime time) {
        this.foodcourt = foodcourt;
        this.crowd = crowd;
        this.time = time;
    }
}
