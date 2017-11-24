package endpoints;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class BannerTest {
    private final Banner banner = new Banner(2,5,6);


    @org.junit.Test
    public void getId() throws Exception {
       assertEquals(banner.getId(),2);

    }



    @org.junit.Test
    public void getCreated() throws Exception {
    }



    @org.junit.Test
    public void getClicks() throws Exception {
        assertEquals(banner.getClicks(),5);
    }



    @org.junit.Test
    public void getImpressions() throws Exception {
        assertEquals(banner.getImpressions(),6);
    }



    @org.junit.Test
    public void getCreative() throws Exception {
    }

    @Test
    public void testDateCreated(){
        LocalDateTime time = LocalDateTime.now();
        Banner banner = new Banner(LocalDateTime.now());
        banner.getCreated();
        Assert.assertTrue(time.isBefore(banner.getCreated()) || time.isEqual(banner.getCreated()));
    }

}