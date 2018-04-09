package cn.breezefaith.entity;

public class Address {
    private Integer aid;
    private Integer uid;
    private String name;
    private String postCode;
    private String phone;
    private String address;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Address{" +
                "aid=" + aid +
                ", uid=" + uid +
                ", name='" + name + '\'' +
                ", postCode='" + postCode + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address1 = (Address) o;

        if (!aid.equals(address1.aid)) return false;
        if (!uid.equals(address1.uid)) return false;
        if (!name.equals(address1.name)) return false;
        if (!postCode.equals(address1.postCode)) return false;
        if (!phone.equals(address1.phone)) return false;
        return address.equals(address1.address);
    }

    @Override
    public int hashCode() {
        int result = aid.hashCode();
        result = 31 * result + uid.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + postCode.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }
}
