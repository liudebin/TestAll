package qian.ling.yi.ext.disruptor;

/**
 * TradeTransaction
 *
 * @author liuguobin
 * @date 2017/8/10
 */

public class TradeTransaction {
    private String id;//交易ID
    private double price;//交易金额

    public TradeTransaction() {
    }
    public TradeTransaction(String id, double price) {
        super();
        this.id = id;
        this.price = price;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
