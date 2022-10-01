package libraryapp.service;

import java.sql.SQLException;
import java.util.List;

import libraryapp.dao.ILanguageDAO;
import libraryapp.dto.LanguageDTO;
import libraryapp.model.Language;

public class LanguageServiceImpl implements ILanguageService{
    private final ILanguageDAO languageDAO;
    
    public LanguageServiceImpl(ILanguageDAO languageDAO) {
        super();
        this.languageDAO = languageDAO;
    }

    @Override
    public void add(LanguageDTO dto) throws SQLException {
        try {
            languageDAO.insert(extractFields(dto));
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        
    }

    @Override
    public void update(LanguageDTO dto) throws SQLException {
       try {
           languageDAO.update(extractFields(dto));
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        
    }

    @Override
    public Language get(LanguageDTO dto) throws SQLException {
        try {
            return languageDAO.getInstanceById(dto.getId());
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void delete(LanguageDTO dto) throws SQLException {
        try {
            languageDAO.delete(extractFields(dto));
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        
    }

    @Override
    public List<Language> getList() throws SQLException {
       try {
            return languageDAO.getAll();
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    private Language extractFields(LanguageDTO dto) {
        Language language = new Language(dto.getId(), dto.getLanguageName());
        return language;
    }

}
