package bai9_auction;

import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private double startingPrice;
    private double currentPrice;
    private String highestBidder;
    private String sellerName;
    private int bidCount;

    public Product(int id, String name, double startingPrice, String sellerName) {
        this.id = id;
        this.name = name;
        this.startingPrice = startingPrice;
        this.currentPrice = startingPrice;
        this.highestBidder = "Chua co";
        this.sellerName = sellerName;
        this.bidCount = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    public synchronized double getCurrentPrice() {
        return currentPrice;
    }

    public synchronized String getHighestBidder() {
        return highestBidder;
    }

    public String getSellerName() {
        return sellerName;
    }

    public synchronized int getBidCount() {
        return bidCount;
    }

    public synchronized boolean placeBid(String bidder, double amount) {
        if (amount > this.currentPrice) {
            this.currentPrice = amount;
            this.highestBidder = bidder;
            this.bidCount++;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("[ID: %-3d | San pham: %-22s | Gia hien tai: %,12.2f VND | Nguoi tra cao nhat: %-15s | Luot bid: %2d | Nguoi ban: %s]",
                id, name, currentPrice, highestBidder, bidCount, sellerName);
    }
}
