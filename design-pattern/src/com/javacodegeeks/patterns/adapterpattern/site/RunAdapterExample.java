package com.javacodegeeks.patterns.adapterpattern.site;

import com.javacodegeeks.patterns.adapterpattern.payd.PayD;
import com.javacodegeeks.patterns.adapterpattern.xpay.Xpay;

public class RunAdapterExample {
	public static void main(String[] args) {
		Xpay xpay = new XpayImpl();

		xpay.setCreditCardNo("000112213123");
		xpay.setCustomerName("Harvan Irsyadi");
		xpay.setCardExpMonth("02");
		xpay.setCardExpYear("2018");
		xpay.setCardCVVNo((short) 111);
		xpay.setAmount(2000000D);

		PayD payD = new XpayToPayDAdapter(xpay);

		testPayD(payD);
	}

	private static void testPayD(PayD payD) {
		System.out.println(payD.getCustCardNo());
		System.out.println(payD.getCardOnwerName());
		System.out.println(payD.getCardExpMonthDate());
		System.out.println(payD.getCVVNo());
		System.out.println(payD.getTotalAmount());
	}
}