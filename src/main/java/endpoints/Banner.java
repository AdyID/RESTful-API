package endpoints;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;

public class Banner {
    private int id;
    private LocalDateTime created;
    private int clicks;
    private int impressions;
    private URL creative;

    public Banner(){}

    public Banner(LocalDateTime created){
        this.created=LocalDateTime.now();
    }

    public Banner(int id, int clicks, int impressions){
        this.id=id;
        this.clicks=clicks;
        this.impressions=impressions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
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

    public URL getCreative() {
        return creative;
    }

    public void setCreative(URL creative) {
        this.creative = creative;
    }
}