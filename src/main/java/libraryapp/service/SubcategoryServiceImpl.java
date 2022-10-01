package libraryapp.service;

import java.sql.SQLException;
import java.util.List;

import libraryapp.dao.ISubcategoryDAO;
import libraryapp.dto.SubcategoryDTO;
import libraryapp.model.Subcategory;

public class SubcategoryServiceImpl implements ISubcategoryService{
    private final ISubcategoryDAO subcategoryDAO;
    
    
    public SubcategoryServiceImpl(ISubcategoryDAO subcategoryDAO) {
        this.subcategoryDAO = subcategoryDAO;
    }

    @Override
    public void add(SubcategoryDTO dto) throws SQLException {
        try {
            subcategoryDAO.insert(extractFields(dto));
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        
    }

    @Override
    public void update(SubcategoryDTO dto) throws SQLException {
        try {
            subcategoryDAO.update(extractFields(dto));
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } 
        
    }

    @Override
    public Subcategory get(SubcategoryDTO dto) throws SQLException {
        try {
            return subcategoryDAO.getInstanceById(dto.getId());
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void delete(SubcategoryDTO dto) throws SQLException {
        try {
            subcategoryDAO.delete(extractFields(dto));
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }        
    }

    @Override
    public List<Subcategory> getList() throws SQLException {
        try {
            List<Subcategory> bookCategories = subcategoryDAO.getAll();
            return bookCategories;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    private Subcategory extractFields(SubcategoryDTO dto) {
        Subcategory bookCategory = new Subcategory(
                dto.getId(),
                dto.getSubcategoryName(),
                dto.getCategoryName());
        
        return bookCategory;
    }
}
