import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class SearchServiceImpl implements SearchService {
    
    private HashMap<Integer, Item> items;
    private HashMap<Integer, Object> users;
    private HashMap<Integer, Object> loans;
    
    public SearchServiceImpl() {
        this.items = new HashMap<>();
        this.users = new HashMap<>();
        this.loans = new HashMap<>();
    }
    
    public void setItems(HashMap<Integer, Item> items) {
        this.items = items;
    }
    
    public void setUsers(HashMap<Integer, Object> users) {
        this.users = users;
    }
    
    public void setLoans(HashMap<Integer, Object> loans) {
        this.loans = loans;
    }
    
    @Override
    public Item searchItemById(int id) {
        return items.get(id);
    }
    
    @Override
    public List<Item> searchItemsByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        String searchTerm = title.toLowerCase().trim();
        
        return items.values().stream()
                .filter(item -> item.getTitle().toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Item> searchItemsByType(String type) {
        if (type == null || type.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        String searchType = type.toUpperCase().trim();
        
        return items.values().stream()
                .filter(item -> item.getType().equals(searchType))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Item> searchItemsComplex(String titleKeyword, String type, Boolean available) {
        List<Item> results = new ArrayList<>(items.values());
        
        if (titleKeyword != null && !titleKeyword.trim().isEmpty()) {
            String searchTerm = titleKeyword.toLowerCase().trim();
            results = results.stream()
                    .filter(item -> item.getTitle().toLowerCase().contains(searchTerm))
                    .collect(Collectors.toList());
        }
        
        if (type != null && !type.trim().isEmpty()) {
            String searchType = type.toUpperCase().trim();
            results = results.stream()
                    .filter(item -> item.getType().equals(searchType))
                    .collect(Collectors.toList());
        }
        
        if (available != null) {
            results = results.stream()
                    .filter(item -> isItemAvailable(item.getId()) == available)
                    .collect(Collectors.toList());
        }
        
        return results;
    }
    
    @Override
    public Object searchUserById(int id) {
        return users.get(id);
    }
    
    @Override
    public List<Object> searchLoansByUserId(int userId, boolean activeOnly) {
        return new ArrayList<>();
    }
    
    @Override
    public Object searchLoanById(int id) {
        return loans.get(id);
    }
    
    private boolean isItemAvailable(int itemId) {
        for (Object loan : loans.values()) {
            if (loan != null) {
                return false;
            }
        }
        return true;
    }
    
    public List<Item> getAllItems() {
        return new ArrayList<>(items.values());
    }
}