package libraryapp.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import libraryapp.dao.dbutil.DBUtil;
import libraryapp.model.Country;

@TestMethodOrder(OrderAnnotation.class)
class CountryDAOImplTest {
    private static ICountryDAO countryDAO;
    
    @BeforeAll
    public static void setupClass() throws SQLException{
        countryDAO = new CountryDAOImpl();
        createDummyCountries();
    }       
    
    @Test
    @Order(1)
    void getCountry() throws SQLException {
        Country test = countryDAO.getInstanceByStrField("country_name", "JUnitTestCountry1");
        assertNotNull(test);
    }
    
    @Test
    @Order(2)
    void updateFirstTestCountry() throws SQLException{
        Country old = countryDAO.getInstanceByStrField("country_name", "JUnitTestCountry1");
        Country newCountry = new Country(old.getId(), old.getName());
        
        newCountry.setName("JUnitTestCountry1Updated");
        countryDAO.update(newCountry);
        
        Country retrieved = countryDAO.getInstanceById(newCountry.getId());
        assertEquals(retrieved.getName(), "JUnitTestCountry1Updated");
    }
    
    @Test
    @Order(4)
    void updateSecondTestCountry() throws SQLException{
        Country old = countryDAO.getInstanceByStrField("country_name", "JUnitTestCountry2");
        Country newCountry = new Country(old.getId(), old.getName());
        
        newCountry.setName("JUnitTestCountry2Updated");
        countryDAO.update(newCountry);
        
        Country retrieved = countryDAO.getInstanceById(newCountry.getId());
        assertEquals(retrieved.getName(), "JUnitTestCountry2Updated");
    }
    
    @Test
    @Order(3)
    void deleteFirstTestCountry() throws SQLException {
        Country c = new Country();
        c.setName("JUnitTestCountry1Updated");
        countryDAO.delete(c);
        
        Country test = countryDAO.getInstanceByStrField("country_name", "JUnitTestCountry1Updated");
        assertNotNull(test);
    }
    
    @Test
    @Order(5)
    void deleteSecondTestCountry() throws SQLException {
        Country c = new Country();
        c.setName("JUnitTestCountry2Updated");
        countryDAO.delete(c);
        
        Country test = countryDAO.getInstanceByStrField("country_name", "JUnitTestCountry2Updated");
        assertNotNull(test);
    }
    
    
    public static void createDummyCountries() throws SQLException{
        Country dummy = new Country();
        dummy.setName("JUnitTestCountry1");
        countryDAO.insert(dummy);
        
        Country dummySecond = new Country();
        dummySecond.setName("JUnitTestCountry2");
        countryDAO.insert(dummySecond);
    }
    

}
