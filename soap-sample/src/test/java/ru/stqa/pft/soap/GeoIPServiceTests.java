package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Пользователь on 17.12.2016.
 */
public class GeoIPServiceTests {

    @Test
    public void testMyIP (){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("212.164.184.159");
        Assert.assertEquals(geoIP.getCountryCode(), "RUS");
    }
}
