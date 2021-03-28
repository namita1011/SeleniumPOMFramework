package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	
	public static final String LOGIN_PAGE_TITLE ="Account Login";
	public static final String ACCOUNT_PAGE_TITLE="My Account";
	public static final int TOTAL_HEADERS_COUNTS=4;
	public static final int PRODUCT_DETAILS_IMAGES=4;
	public static final String REGISTARTION_SUCCESS_MSG ="Your Account Has Been Created!";
	public static final String REGSITER_DATA_SHEET ="Register";
	public static final String PRODUCT_DETTAILS_HEADER="iMac";
	public static final String SUCCESS_ADDCART_MESSAGE="Success: You have added iMac to your shopping cart!";
	public static final String SUCCESS_CART_IS_NOT_EMPTY="Cart is not empty";
	
    public static List<String> expHeaderList()
    {
    	List<String>expList=new ArrayList<String>();
    	expList.add("My Account");
    	expList.add("My Orders");
    	expList.add("My Affiliate Account");
    	expList.add("Newsletter");
    	
    	
    	return expList;
    }

}
