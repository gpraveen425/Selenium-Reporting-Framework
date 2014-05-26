package com.selenium;

import java.io.IOException;

public class Testme {

	public static void main(String Args[]) {
		int i = 0;
		report o = new report();
		String alph[] = { "1", "2", "3", "4", "5", "6" };
		for (i = 0; i <= 4; i++) {
			o.Addinput(alph[i], i);
			o.Expectedoutput(alph[i + 1], i);
			o.Output(alph[i], i);
		}
		try {
			o.Generatereport(4);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}