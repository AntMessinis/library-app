package libraryapp.service;

import java.sql.SQLException;
import java.util.List;

import libraryapp.dao.IAuthorDAO;
import libraryapp.dto.AuthorDTO;
import libraryapp.model.Author;
import libraryapp.model.Country;

public class AuthorServiceImpl implements IAuthorService{
    
    private final IAuthorDAO authorDAO;
    
    public AuthorServiceImpl(IAuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    @Override
    public void add(AuthorDTO dto) throws SQLException {
        try {
            authorDAO.insert(extractFields(dto));
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void update(AuthorDTO dto) throws SQLException {
        try {
            authorDAO.update(extractFields(dto));
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }
        
    }

    @Override
    public Author get(AuthorDTO dto) throws SQLException {
        return authorDAO.getInstanceById(extractFields(dto).getId());
    }

    @Override
    public void delete(AuthorDTO dto) throws SQLException {
        try {
            authorDAO.delete(extractFields(dto));
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }
        
    }

    @Override
    public List<Author> getList() throws SQLException {
        try {
            return authorDAO.getAll();
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    private Author extractFields(AuthorDTO dto) {
        Author author = new Author(
                dto.getId(),
                dto.getFirstname(),
                dto.getLastname(),
                new Country(dto.getCountryOfOrigin().getId(), dto.getCountryOfOrigin().getCountryName()));
        
        return author;
    }
    
}
