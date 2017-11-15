import java.util.Date;

public class Campaign {

    private int id;
    private String name;
    private Date created;
    private int clicks;
    private int impressions;

    private Banner[] banners = new Banner[10];

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Banner[] getBanners() {
        return banners;
    }

    public void setBanners(Banner[] banners) {
        this.banners = banners;
    }
}
