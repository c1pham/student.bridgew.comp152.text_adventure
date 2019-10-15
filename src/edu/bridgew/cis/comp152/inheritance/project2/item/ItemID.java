package edu.bridgew.cis.comp152.inheritance.project2.item;

public class ItemID {
	// attributes of itemID
	private String itemType;
	private String subItemType1;
	private String subItemType2;
	private String itemName;

	// constructor
	public ItemID (String itemType, String subItemType1, String subItemType2) {	
		// arrays with characters to use
		this.itemType = itemType;
		this.subItemType1 = subItemType1;
		this.subItemType2 = subItemType2;
	}
	
	// getter and setter

	public String getItemType() {
		return itemType;
	}


	public String getSubItemType1() {
		return subItemType1;
	}

	public String getSubItemType2() {
		return subItemType2;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}



}
