import java.util.HashMap;
import java.util.List;

public class SearchDemo {
    
    public static void main(String[] args) {
        
        HashMap<Integer, Item> items = new HashMap<>();
        items.put(1, new Book(1, "BOOK", "Java Programming", "John Doe", "TechPress", 2020));
        items.put(2, new Book(2, "BOOK", "Advanced Java", "Jane Smith", "CodeBooks", 2021));
        items.put(3, new Magazine(3, "MAGAZINE", "Tech Monthly", "Various", "TechMedia", 2023));
        items.put(4, new Book(4, "BOOK", "Python Basics", "Bob Johnson", "TechPress", 2022));
        items.put(5, new Magazine(5, "MAGAZINE", "Java World", "Various", "DevPublish", 2024));
        
        SearchServiceImpl searchService = new SearchServiceImpl();
        searchService.setItems(items);
        
        System.out.println("=== SEARCH BY ID ===");
        Item item = searchService.searchItemById(2);
        if (item != null) {
            System.out.println("Found: " + item.info());
        } else {
            System.out.println("Item not found");
        }
        
        System.out.println("\n=== SEARCH BY TITLE (contains 'java') ===");
        List<Item> byTitle = searchService.searchItemsByTitle("java");
        if (byTitle.isEmpty()) {
            System.out.println("No items found");
        } else {
            for (Item i : byTitle) {
                System.out.println(i.info());
            }
        }
        
        System.out.println("\n=== SEARCH BY TYPE (BOOK) ===");
        List<Item> byType = searchService.searchItemsByType("BOOK");
        if (byType.isEmpty()) {
            System.out.println("No items found");
        } else {
            for (Item i : byType) {
                System.out.println(i.info());
            }
        }
        
        System.out.println("\n=== COMPLEX SEARCH (title contains 'java' + type BOOK + available) ===");
        List<Item> complex = searchService.searchItemsComplex("java", "BOOK", true);
        if (complex.isEmpty()) {
            System.out.println("No items found");
        } else {
            for (Item i : complex) {
                System.out.println(i.info());
            }
        }
        
        System.out.println("\n=== COMPLEX SEARCH (only available items) ===");
        List<Item> available = searchService.searchItemsComplex(null, null, true);
        System.out.println("Available items: " + available.size());
        for (Item i : available) {
            System.out.println(i.info());
        }
        
        System.out.println("\n=== COMPLEX SEARCH (type MAGAZINE + publisher contains 'Tech') ===");
        List<Item> magazines = searchService.searchItemsByType("MAGAZINE");
        System.out.println("Magazines found: " + magazines.size());
        for (Item i : magazines) {
            System.out.println(i.info());
        }
    }
}