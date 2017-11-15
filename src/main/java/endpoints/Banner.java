package endpoints;

import java.net.URL;
import java.util.Date;

public class Banner {
    private int id;
    private Date created;
    private int clicks;
    private int impressions;
    private URL creative;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
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