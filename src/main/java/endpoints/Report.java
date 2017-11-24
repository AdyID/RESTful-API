package endpoints;
import java.time.LocalDateTime;
import java.util.Date;

public class Report {

    private int id;
    private int clicks;
    private int impressions;
    private LocalDateTime created;
    private int banner_id;

    public Report(){
    }

    public Report(int id, int clicks, int impressions, int banner_id){
        this.id = id;
        this.clicks=clicks;
        this.impressions=impressions;
        this.banner_id=banner_id;
    }

    public Report(LocalDateTime created){
        this.created=LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public int getImpressions() {
        return impressions;
    }

    public void setImpressions(int impressions) {
        this.impressions = impressions;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public int getBanner_id() {
        return banner_id;
    }

    public void setBanner_id(int banner_id) {
        this.banner_id = banner_id;
    }
}