import endpoints.Campaign;

import java.util.Collection;

public interface CampaignService {

    public void addCampaign(Campaign campaign);

    public Collection<Campaign> getCampaigns();
    public Campaign getCampaign(int id);

    public Campaign editCampaign(Campaign campaign);

    public void deleteCampaign(Campaign campaign);

    public boolean campaignExist(int id);
}
