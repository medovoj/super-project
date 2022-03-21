package app.service;

import app.dao.BookDao;
import app.dao.util.DaoManager;
import app.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookService {
    private BookDao bookDao;

    public List<Book> getAllBooks() {
        return DaoManager.getInstance().transaction(x -> bookDao.getAll(x));
    }
}
