package com.selenium.sample;

import java.io.IOException;

public class Testme {

	public static void main(String Args[]) {
		int i = 0;
		report_imran o = new report_imran();
		String alph[] = { "7", "5", "5", "5", "7", "5", "5","5", "7", "5", "5","5", "7", "5", "5", "6", "4"};
		for (i = 0; i <= 14; i++) {
			o.Addinput(alph[i], i);
			o.Expectedoutput(alph[i+2], i);
			o.Output(alph[i], i);
			
		}
		try {
			o.Generatereport(14);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}