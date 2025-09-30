import java.time.LocalDate;

public class Loan {
    private int id;
    private int userId;
    private int itemId;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private boolean active;
    
    public Loan(int id, int userId, int itemId) {
        this.id = id;
        this.userId = userId;
        this.itemId = itemId;
        this.loanDate = LocalDate.now();
        this.returnDate = null;
        this.active = true;
    }
    
    public int getId() {
        return id;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public int getItemId() {
        return itemId;
    }
    
    public LocalDate getLoanDate() {
        return loanDate;
    }
    
    public LocalDate getReturnDate() {
        return returnDate;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
        this.active = false;
    }
    
    public String info() {
        String status = active ? "Active" : "Returned";
        String returnInfo = returnDate != null ? ", Return Date: " + returnDate : "";
        return "Loan ID: " + id + ", User ID: " + userId + ", Item ID: " + itemId + 
               ", Loan Date: " + loanDate + returnInfo + ", Status: " + status;
    }
}