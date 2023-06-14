package sg.edu.np.mad;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.concurrent.locks.LockSupport;

public class CrowdReview implements Serializable {
    public String foodcourt;
    public int crowd;
    public String time;

    public CrowdReview(String foodcourt, int crowd, String time) {
        this.foodcourt = foodcourt;
        this.crowd = crowd;
        this.time = time;
    }
    public CrowdReview() {
    }
}
