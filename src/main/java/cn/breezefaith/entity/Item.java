package cn.breezefaith.entity;

public class Item {
    private Integer iid;
    private String itemName;
    private String itemCategory;
    private String itemDescription;
    private String itemImage;
    private Double itemPrice;

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public String toString() {
        return "Item{" +
                "iid=" + iid +
                ", itemName='" + itemName + '\'' +
                ", itemCategory='" + itemCategory + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemImage='" + itemImage + '\'' +
                ", itemPrice=" + itemPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (!getIid().equals(item.getIid())) return false;
        if (!getItemName().equals(item.getItemName())) return false;
        if (getItemCategory() != null ? !getItemCategory().equals(item.getItemCategory()) : item.getItemCategory() != null)
            return false;
        if (getItemDescription() != null ? !getItemDescription().equals(item.getItemDescription()) : item.getItemDescription() != null)
            return false;
        if (getItemImage() != null ? !getItemImage().equals(item.getItemImage()) : item.getItemImage() != null)
            return false;
        return getItemPrice().equals(item.getItemPrice());
    }

    @Override
    public int hashCode() {
        int result = getIid().hashCode();
        result = 31 * result + getItemName().hashCode();
        result = 31 * result + (getItemCategory() != null ? getItemCategory().hashCode() : 0);
        result = 31 * result + (getItemDescription() != null ? getItemDescription().hashCode() : 0);
        result = 31 * result + (getItemImage() != null ? getItemImage().hashCode() : 0);
        result = 31 * result + getItemPrice().hashCode();
        return result;
    }
}
