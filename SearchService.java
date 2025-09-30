import java.util.List;

public interface SearchService {
    
    Item searchItemById(int id);
    
    List<Item> searchItemsByTitle(String title);
    
    List<Item> searchItemsByType(String type);
    
    List<Item> searchItemsComplex(String titleKeyword, String type, Boolean available);
    
    Object searchUserById(int id);
    
    List<Object> searchLoansByUserId(int userId, boolean activeOnly);
    
    Object searchLoanById(int id);
}