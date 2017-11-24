package endpoints;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import static org.junit.Assert.*;

public class ReportTest {

    private Report report = new Report(23,145,3456,54);

    @Test
    public void getId() throws Exception {
        assertEquals(report.getId(),23);
    }

    @Test
    public void getClicks() throws Exception {
        assertEquals(report.getClicks(), 145);
    }

    @Test
    public void getImpressions() throws Exception {
        assertEquals(report.getImpressions(), 3456);
    }

    @Test
    public void getCreated() throws Exception {
    }

    @Test
    public void getBanner_id() throws Exception {
        assertEquals(report.getBanner_id(),54);
    }

    @Test
    public void testDateCreated(){
        LocalDateTime time = LocalDateTime.now();
        Report report = new Report(LocalDateTime.now());
        report.getCreated();
        Assert.assertTrue(time.isBefore(report.getCreated()) || time.isEqual(report.getCreated()));
    }

}