package backend.helpinghand.services;
import java.util.List;
import backend.helpinghand.entities.Category;
import backend.helpinghand.entities.Comment;

public interface CategoryService {
    public List<Category> listAllCategorys();
    public Category addCategory(Category category);
}
