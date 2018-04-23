package cn.breezefaith.entity;

public class Record {
    private Integer rid;
    private User user;
    private Address address;
    private Integer counts;
    private Double cost;
    private String courierNumber;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getCourierNumber() {
        return courierNumber;
    }

    public void setCourierNumber(String courierNumber) {
        this.courierNumber = courierNumber;
    }

    @Override
    public String toString() {
        return "Record{" +
                "rid=" + rid +
                ", user=" + user +
                ", address=" + address +
                ", counts=" + counts +
                ", cost=" + cost +
                ", courierNumber='" + courierNumber + '\'' +
                '}';
    }
}
