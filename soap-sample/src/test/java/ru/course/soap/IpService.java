package ru.course.soap;

import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class IpService {

    @Test
    public void testIpService()
    {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("176.116.142.125");
        System.out.println(ipLocation);
        assertNotEquals(ipLocation, "null");
    }
}
