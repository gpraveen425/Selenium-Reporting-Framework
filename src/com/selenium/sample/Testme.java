package com.selenium.sample;

import java.io.IOException;

public class Testme {

	public static void main(String Args[]) {
		int i = 0;
		report_may03 o = new report_may03();
		String alph[] = { "3", "2", "6", "6", "6", "6", "6" };
		for (i = 0; i <= 4; i++) {
			o.Addinput(alph[i], i);
			o.Expectedoutput(alph[i+2], i);
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