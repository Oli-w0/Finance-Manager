package financeproject;

class Transaction {
    private int id;
    private String type; // "Income" or "Expense"
    private String category;
    private double amount;
    private String description;

    public Transaction(int id, String type, String category, double amount, String description) {
        this.id = id;
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | %s | %s | €%.2f | %s", id, type, category, amount, description);
    }
}