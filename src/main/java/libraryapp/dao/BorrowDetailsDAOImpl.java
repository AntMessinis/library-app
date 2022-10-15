package libraryapp.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import libraryapp.dao.dbutil.DBUtil;
import libraryapp.model.Author;
import libraryapp.model.Book;
import libraryapp.model.BorrowDetails;
import libraryapp.model.Country;
import libraryapp.model.Language;
import libraryapp.model.Subcategory;

public class BorrowDetailsDAOImpl implements IBorrowDetailsDAO{

    @Override
    public void insert(BorrowDetails m) throws SQLException {
        String sql = "insert into borrow_details ("
                + "user_id, "
                + "book_id, "
                + "borrow_date, return_date, actual_return, returned) "
                + "values (?, "
                + "?, ?, ?, ?, ?)";
        
        try(PreparedStatement pst = DBUtil.openConnection().prepareStatement(sql)){
            pst.setLong(1, m.getMemberId());
            pst.setLong(2, m.getBook().getId());
            pst.setDate(3, new Date(m.getBorrowDate().getTime()));
            pst.setDate(4, new Date(m.getReturnDate().getTime()));
            pst.setDate(5, new Date(m.getBorrowDate().getTime()));
            pst.setBoolean(6, false);
            
            pst.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void update(BorrowDetails m) throws SQLException {
        String sql =  "update borrow_details set actual_return=?, returned=?";
        try(PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
            ps.setDate(1, new Date(m.getActualReturn().getTime()));
            ps.setBoolean(2, true);
            ps.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }
        
    }

    @Override
    public void delete(BorrowDetails m) throws SQLException {
        String sql = "delete from borrow_details where id=?";
        try(PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
            ps.setLong(1, m.getId());
            ps.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public BorrowDetails getInstanceById(long id) throws SQLException {
        String sql = "select * from borrow_details "
                + "inner join books on book_id = books.id "
                + "inner join authors on books.author=authors.id "
                + "inner join countries on authors.country=countries.id "
                + "inner join languages on language=languages.id "
                + "inner join subcategories on books.category=subcategories.id "
                + "inner join categories on subcategories.category=categories.id  where id=?";
        try(PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
            ps.setLong(1, id);
            
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    Country country = new Country(rs.getLong(21), rs.getString(22));
                    Author author = new Author(rs.getLong(17), rs.getString(18),rs.getString(19), country);
                    Language language = new Language(rs.getLong(23), rs.getString(24));
                    Subcategory category = new Subcategory(rs.getLong(25), rs.getString(26), rs.getString(29));
                    Book book = new Book(rs.getLong(8),rs.getString(9), rs.getString(10), author, language, category, rs.getString(14), rs.getInt(15), rs.getInt(16));
                    BorrowDetails details = new BorrowDetails(rs.getLong(1), rs.getLong(2), book, 
                            new java.util.Date(rs.getDate(4).getTime()), 
                            new java.util.Date(rs.getDate(5).getTime()), 
                            new java.util.Date(rs.getDate(6).getTime()));
                    
                    return details;
                }
                return null;
            }catch(SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public BorrowDetails getInstanceByStrField(String fieldName, String value) throws SQLException {
        String sql = "select * from borrow_details "
                + "inner join books on book_id = books.id "
                + "inner join authors on books.author=authors.id "
                + "inner join countries on authors.country=countries.id "
                + "inner join languages on language=languages.id "
                + "inner join subcategories on books.category=subcategories.id "
                + "inner join categories on subcategories.category=categories.id  where " + fieldName + "=?";
        try(PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
            ps.setString(1, value);
            
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    Country country = new Country(rs.getLong(21), rs.getString(22));
                    Author author = new Author(rs.getLong(17), rs.getString(18),rs.getString(19), country);
                    Language language = new Language(rs.getLong(23), rs.getString(24));
                    Subcategory category = new Subcategory(rs.getLong(25), rs.getString(26), rs.getString(29));
                    Book book = new Book(rs.getLong(8),rs.getString(9), rs.getString(10), author, language, category, rs.getString(14), rs.getInt(15), rs.getInt(16));
                    BorrowDetails details = new BorrowDetails(rs.getLong(1), rs.getLong(2), book, 
                            new java.util.Date(rs.getDate(4).getTime()), 
                            new java.util.Date(rs.getDate(5).getTime()), 
                            new java.util.Date(rs.getDate(6).getTime()));
                    
                    return details;
                }
                return null;
            }catch(SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<BorrowDetails> getAll() throws SQLException {
        String sql = "select * from borrow_details "
                + "inner join books on book_id = books.id "
                + "inner join authors on books.author=authors.id "
                + "inner join countries on authors.country=countries.id "
                + "inner join languages on language=languages.id "
                + "inner join subcategories on books.category=subcategories.id "
                + "inner join categories on subcategories.category=categories.id";
        
        List<BorrowDetails> detailsList= new ArrayList<>();
        
        try(PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
            
            try(ResultSet rs = ps.executeQuery()){
                
                while (rs.next()) {
                    Country country = new Country(rs.getLong(21), rs.getString(22));
                    Author author = new Author(rs.getLong(17), rs.getString(18),rs.getString(19), country);
                    Language language = new Language(rs.getLong(23), rs.getString(24));
                    Subcategory category = new Subcategory(rs.getLong(25), rs.getString(26), rs.getString(29));
                    Book book = new Book(rs.getLong(8),rs.getString(9), rs.getString(10), author, language, category, rs.getString(14), rs.getInt(15), rs.getInt(16));
                    BorrowDetails details = new BorrowDetails(rs.getLong(1), rs.getLong(2), book, 
                            new java.util.Date(rs.getDate(4).getTime()), 
                            new java.util.Date(rs.getDate(5).getTime()), 
                            new java.util.Date(rs.getDate(6).getTime()));
                    
                    detailsList.add(details);
                }
                return detailsList;
                
            }catch(SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<BorrowDetails> getListByField(String fieldName, String value) throws SQLException {
        String sql = "select * from borrow_details "
                + "inner join books on book_id = books.id "
                + "inner join authors on books.author=authors.id "
                + "inner join countries on authors.country=countries.id "
                + "inner join languages on language=languages.id "
                + "inner join subcategories on books.category=subcategories.id "
                + "inner join categories on subcategories.category=categories.id where " + fieldName + "=?";
        
        List<BorrowDetails> detailsList= new ArrayList<>();
        
        try(PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
            ps.setString(1, value);
            try(ResultSet rs = ps.executeQuery()){
                
                while (rs.next()) {
                    Country country = new Country(rs.getLong(21), rs.getString(22));
                    Author author = new Author(rs.getLong(17), rs.getString(18),rs.getString(19), country);
                    Language language = new Language(rs.getLong(23), rs.getString(24));
                    Subcategory category = new Subcategory(rs.getLong(25), rs.getString(26), rs.getString(29));
                    Book book = new Book(rs.getLong(8),rs.getString(9), rs.getString(10), author, language, category, rs.getString(14), rs.getInt(15), rs.getInt(16));
                    BorrowDetails details = new BorrowDetails(rs.getLong(1), rs.getLong(2), book, 
                            new java.util.Date(rs.getDate(4).getTime()), 
                            new java.util.Date(rs.getDate(5).getTime()), 
                            new java.util.Date(rs.getDate(6).getTime()));
                    
                    detailsList.add(details);
                }
                return detailsList;
                
            }catch(SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<BorrowDetails> getListByField(String fieldName, long value) throws SQLException {
        String sql = "select * from borrow_details "
                + "inner join books on book_id = books.id "
                + "inner join authors on books.author=authors.id "
                + "inner join countries on authors.country=countries.id "
                + "inner join languages on language=languages.id "
                + "inner join subcategories on books.category=subcategories.id "
                + "inner join categories on subcategories.category=categories.id where " + fieldName + "=?";
        
        List<BorrowDetails> detailsList= new ArrayList<>();
        
        try(PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
            ps.setLong(1, value);
            try(ResultSet rs = ps.executeQuery()){
                
                while (rs.next()) {
                    Country country = new Country(rs.getLong(21), rs.getString(22));
                    Author author = new Author(rs.getLong(17), rs.getString(18),rs.getString(19), country);
                    Language language = new Language(rs.getLong(23), rs.getString(24));
                    Subcategory category = new Subcategory(rs.getLong(25), rs.getString(26), rs.getString(29));
                    Book book = new Book(rs.getLong(8),rs.getString(9), rs.getString(10), author, language, category, rs.getString(14), rs.getInt(15), rs.getInt(16));
                    BorrowDetails details = new BorrowDetails(rs.getLong(1), rs.getLong(2), book, 
                            new java.util.Date(rs.getDate(4).getTime()), 
                            new java.util.Date(rs.getDate(5).getTime()), 
                            new java.util.Date(rs.getDate(6).getTime()));
                    
                    detailsList.add(details);
                }
                return detailsList;
                
            }catch(SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<BorrowDetails> getListByField(String fieldName, java.util.Date value) throws SQLException {
        String sql = "select * from borrow_details "
                + "inner join books on book_id = books.id "
                + "inner join authors on books.author=authors.id "
                + "inner join countries on authors.country=countries.id "
                + "inner join languages on language=languages.id "
                + "inner join subcategories on books.category=subcategories.id "
                + "inner join categories on subcategories.category=categories.id where " + fieldName + "=?";
        
        List<BorrowDetails> detailsList= new ArrayList<>();
        
        try(PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
            ps.setDate(1, (Date) value);
            try(ResultSet rs = ps.executeQuery()){
                
                while (rs.next()) {
                    Country country = new Country(rs.getLong(21), rs.getString(22));
                    Author author = new Author(rs.getLong(17), rs.getString(18),rs.getString(19), country);
                    Language language = new Language(rs.getLong(23), rs.getString(24));
                    Subcategory category = new Subcategory(rs.getLong(25), rs.getString(26), rs.getString(29));
                    Book book = new Book(rs.getLong(8),rs.getString(9), rs.getString(10), author, language, category, rs.getString(14), rs.getInt(15), rs.getInt(16));
                    BorrowDetails details = new BorrowDetails(rs.getLong(1), rs.getLong(2), book, 
                            new java.util.Date(rs.getDate(4).getTime()), 
                            new java.util.Date(rs.getDate(5).getTime()), 
                            new java.util.Date(rs.getDate(6).getTime()));
                    
                    detailsList.add(details);
                }
                return detailsList;
                
            }catch(SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<BorrowDetails> getListByField(String fieldName, boolean value) throws SQLException {
        String sql = "select * from borrow_details "
                + "inner join books on book_id = books.id "
                + "inner join authors on books.author=authors.id "
                + "inner join countries on authors.country=countries.id "
                + "inner join languages on language=languages.id "
                + "inner join subcategories on books.category=subcategories.id "
                + "inner join categories on subcategories.category=categories.id where " + fieldName + "=?";
        
        List<BorrowDetails> detailsList= new ArrayList<>();
        
        try(PreparedStatement ps = DBUtil.openConnection().prepareStatement(sql)){
            ps.setBoolean(1, value);
            try(ResultSet rs = ps.executeQuery()){
                
                while (rs.next()) {
                    Country country = new Country(rs.getLong(21), rs.getString(22));
                    Author author = new Author(rs.getLong(17), rs.getString(18),rs.getString(19), country);
                    Language language = new Language(rs.getLong(23), rs.getString(24));
                    Subcategory category = new Subcategory(rs.getLong(25), rs.getString(26), rs.getString(29));
                    Book book = new Book(rs.getLong(8),rs.getString(9), rs.getString(10), author, language, category, rs.getString(14), rs.getInt(15), rs.getInt(16));
                    BorrowDetails details = new BorrowDetails(rs.getLong(1), rs.getLong(2), book, 
                            new java.util.Date(rs.getDate(4).getTime()), 
                            new java.util.Date(rs.getDate(5).getTime()), 
                            new java.util.Date(rs.getDate(6).getTime()));
                    
                    detailsList.add(details);
                }
                return detailsList;
                
            }catch(SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    
}
