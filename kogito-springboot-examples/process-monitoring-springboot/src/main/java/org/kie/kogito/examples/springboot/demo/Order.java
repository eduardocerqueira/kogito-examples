package org.kie.kogito.examples.springboot.demo;

public class Order implements java.io.Serializable {

    static final long serialVersionUID = 1L;

    private java.lang.String orderNumber;
    private java.lang.Boolean shipped;
    private java.lang.Double total;

    public Order() {
    }

    public java.lang.String getOrderNumber() {
        return this.orderNumber;
    }

    public void setOrderNumber(java.lang.String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public java.lang.Boolean isShipped() {
        return this.shipped;
    }

    public void setShipped(java.lang.Boolean shipped) {
        this.shipped = shipped;
    }

    public java.lang.Double getTotal() {
        return this.total;
    }

    public void setTotal(java.lang.Double total) {
        this.total = total;
    }

    public Order(java.lang.String orderNumber, java.lang.Boolean shipped,
            java.lang.Double total) {
        this.orderNumber = orderNumber;
        this.shipped = shipped;
        this.total = total;
    }

    public String toString() {
        return "Order[" + this.orderNumber + "]";
    }

}