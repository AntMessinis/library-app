package libraryapp.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import libraryapp.dao.dbutil.DBUtil;
import libraryapp.model.Country;

class CountryDAOImplTest {
    private static ICountryDAO countryDAO;
    
    @BeforeAll
    public static void setupClass() throws SQLException{
        countryDAO = new CountryDAOImpl();
    }
    
    @BeforeEach
    public  void setup() throws SQLException{
       // createDummyCountries();
    }
    
    @AfterAll
    public static void tearDown() throws SQLException{
        
    }
    
    @Test
    void getCountry() throws SQLException {
        Country test = countryDAO.getInstanceByStrField("country_name", "JUnitTestCountry1");
        assertNotNull(test);
    }
    
    @Test
    void updateCountry() throws SQLException{
        Country old = countryDAO.getInstanceByStrField("country_name", "JUnitTestCountry1");
        Country newCountry = new Country(old.getId(), old.getName());
        
        newCountry.setName("JUnitTestCountry1Updated");
        countryDAO.update(newCountry);
        
        Country retrieved = countryDAO.getInstanceById(newCountry.getId());
        assertEquals(retrieved.getName(), "JUnitTestCountry1Updated");
    }
    
    @Test
    void deleteCountry() throws SQLException {
        Country c = new Country();
        c.setName("JUnitTestCountry1Updated");
        countryDAO.delete(c);
        
        Country test = countryDAO.getInstanceByStrField("country_name", "JUnitTestCountry1Updated");
        assertNotNull(test);
    }
    
    public static void createDummyCountries() throws SQLException{
        Country dummy = new Country();
        dummy.setName("JUnitTestCountry1");
        countryDAO.insert(dummy);
        
        Country dummySecond = new Country();
        dummy.setName("JUnitTestCountry2");
        countryDAO.insert(dummySecond);
    }
    

}
