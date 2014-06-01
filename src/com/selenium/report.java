package com.selenium.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.io.FileUtils;

public class report {
	static String html = "";
	static String htmlmain = "";
	static String htmlresult = "";
	static List ip = new ArrayList<String>();
	static List op = new ArrayList<String>();
	static List exp = new ArrayList<String>();
	static List res = new ArrayList<String>();
	int row = 0;
	static int countP = 0;
	static int countF = 0;

	public void Addinput(String input, int r) {
		// Method to get the input along with index
		ip.add(r, input);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.print(dateFormat.format(date));
		System.out.println(" - " + ip.get(r));
	}

	public static void Output(String output, int r) {
		// Method to get the output along with index
		op.add(r, output);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.print(dateFormat.format(date));
		System.out.println(" - " + op.get(r));
	}

	public static void Expectedoutput(String expect, int r) {
		// Method to get the expected output along with index
		exp.add(r, expect);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.print(dateFormat.format(date));
		System.out.println(" - " + exp.get(r));
	}

	public static void Generatereport(int r) throws IOException {
		// Method to generate output as html report
		for (int i = 0; i <= r; i++) {
			if (op.get(i).equals(exp.get(i))) {
				res.add(i, "Pass");
				countP++;
			}

			else {
				res.add(i, "Fail");
				countF++;
			}
		}
		float re = 0;
		re = (countP / (countP + countF)) * 100;
		htmlresult += "<tr><td>" + countP + "</td> <td>" + countF
				+ "</td> <td>" + re + "</td></tr>";

		for (int i = 0; i <= r; i++) {
			html += "<tr> <td>" + ip.get(i) + "</td> <td>" + exp.get(i)
					+ "</td> <td>" + op.get(i) + "</td> <td>" + res.get(i)
					+ "</td> </tr>";
		}
		htmlmain += "<html><head><title>SRF Report</title><script type=\"text/javascript\" src=\"https://www.google.com/jsapi\"></script><script type=\"text/javascript\">google.load(\"visualization\", \"1\", {packages:[\"corechart\"]});google.setOnLoadCallback(drawChart);function drawChart() {var data = google.visualization.arrayToDataTable([['Task', 'Hours per Day'],['Work',     0],['Fail',      3],['Skipped',  1],['Pass', 10],['Sleep',    0]]);var options = {title: 'Selenium Pie Chart Report', is3D: true,};var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));chart.draw(data, options);}</script><style>html, body{padding:15;margin:0;position:relative;	background-repeat:repeat;	color:#000;	letter-spacing:1px;	font-family:Georgia, "
				+ "Times New Roman"
				+ ", Times, serif;}.zebra caption{	font-size:20px;	font-weight:normal;	background-repeat:no-repeat;background-position: 130px center;padding-top: 20px;height:50px;}#container{	padding-top:20px;	width:960px;	margin:0 auto;}table {border-collapse: collapse;border-spacing: 0;width:100%;-webkit-box-shadow:  0px 2px 1px 5px rgba(242, 242, 242, 0.1);box-shadow:  0px 2px 1px 5px rgba(242, 242, 242, 0.1);}.zebra {border: 1px solid #555;}.zebra td {border-left: 1px solid #555;border-top: 1px solid #555;padding: 10px;text-align: left;}.zebra th, .zebra th:hover {border-left: 1px solid #555;border-bottom: 1px solid #828282;padding: 20px;  background-color:#151515 !important;background-image: -webkit-gradient(linear, left top, left bottom, from(#151515), to(#404040)) !important;background-image: -webkit-linear-gradient(top, #151515, #404040) !important;background-image:    -moz-linear-gradient(top, #151515, #404040) !important;background-image:     -ms-linear-gradient(top, #151515, #404040) !important;background-image:      -o-linear-gradient(top, #151515, #404040) !important;background-image:         linear-gradient(top, #151515, #404040) !important;color:#fff !important;font-weight:normal;}.zebra tbody tr:nth-child(even) {background: #000 !important;color:#fff;}.zebra tr:hover *{background: #eeeeee;color:#000;}.zebra tr {background:#404040;color:#fff;}	</style></head><body style="
				+ "background-color: rgb(219, 217, 217);"
				+ "><div id="
				+ "container"
				+ "> <div style="
				+ "margin-bottom:50px;"
				+ "><table class="
				+ "zebra"
				+ "><caption>Selenium Reporting Framework - Test Results</caption> <tbody id=summary><tr><td>No. Of Test Passed</td> <td>No. Of Test Failed</td> <td>Pass %</td></tr><tr>$summary</tr></table></div>" +"<div id=\"piechart_3d\" style=\"width: 750px; height: 350px;\"></div>"+"<table class="
				+ "zebra"
				+ ">"				
				+ "<p>Detailed Report</p>"
				+ "<tbody id="
				+ "theBody"
				+ "><tr> <td>Input</td> <td>Expected Output</td> <td>Output</td> <td>Result</td> </tr>$body </tbody></table><p><a style="
				+ "color:#000000;"
				+ ">Copyright © 2014 Seleniumworks. All rights reserved. | </a> <a href="
				+ "http://www.seleniumworks.com"
				+ "> Blog</a></p></body>	</html>";
		String htmlw = "";
		File testHtml = new File("test.html");
		htmlw = htmlw.replace("", htmlmain);
		FileUtils.writeStringToFile(testHtml, htmlw);

		// Create template html
		File htmlTemplateFile = new File("test.html");
		String htmlString = FileUtils.readFileToString(htmlTemplateFile);
		// Create result html
		htmlString = htmlString.replace("$body", html);
		htmlString = htmlString.replace("$summary", htmlresult);
		File newHtmlFile = new File("Report.html");
		FileUtils.writeStringToFile(newHtmlFile, htmlString);
		// delete sample html
		File file = new File("test.html");
		file.delete();

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.print(dateFormat.format(date));
		System.out.println(" - Report Generated");

	}

	public static String[][] ExcelfileReader(String path, String sheetname,
			int row, int column) throws BiffException, IOException {

		Sheet s = null;
		String[][] value = new String[row][column];
		FileInputStream fi = null;
		try {
			fi = new FileInputStream(path);
		} catch (Exception e) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			System.out.print(dateFormat.format(date));
			System.out.println(" - Excel File not found in the given path");
			System.exit(0);
		}
		Workbook w = Workbook.getWorkbook(fi);
		s = w.getSheet(sheetname);

		for (int acolumn = 0; acolumn <= column - 1; acolumn++) {
			for (int arow = 0; arow <= row - 1; arow++) {
				try {
					String h = s.getCell(acolumn, arow).getContents();
					value[arow][acolumn] = h;
					DateFormat dateFormat = new SimpleDateFormat(
							"yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
					System.out.print(dateFormat.format(date));
					System.out.println(" - Value of (" + arow + "," + acolumn
							+ ") - " + value[arow][acolumn]);
				} catch (Exception e) {
					System.out
							.println("-------------------------------------------------------------------------------------");
					DateFormat dateFormat = new SimpleDateFormat(
							"yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
					System.out.print(dateFormat.format(date));
					System.out
							.println(" - Error : Sheet name not found / Cell value may be out of bounce or Empty cell ...!!");
					System.out
							.println("-------------------------------------------------------------------------------------");
					System.exit(0);
				}
			}
		}

		return (value);
	}

	/*
	 * public static void EmailResults(String From, String To, String Password,
	 * String Subject, String Content) {
	 * 
	 * final String username = From; final String password = Password;
	 * 
	 * Properties props = new Properties();
	 * props.put("mail.smtp.starttls.enable", "true");
	 * props.put("mail.smtp.auth", "true"); props.put("mail.smtp.host",
	 * "smtp.gmail.com"); props.put("mail.smtp.port", "587");
	 * 
	 * Session session = Session.getInstance(props, new
	 * javax.mail.Authenticator() {
	 * 
	 * @Override protected PasswordAuthentication getPasswordAuthentication() {
	 * return new PasswordAuthentication(username, password); } });
	 * 
	 * try {
	 * 
	 * Message message = new MimeMessage(session); message.setFrom(new
	 * InternetAddress(From)); message.setRecipients(Message.RecipientType.TO,
	 * InternetAddress.parse(To)); message.setSubject(Subject);
	 * message.setText("Dear " + To + "," + "\n\n " + Content);
	 * 
	 * System.out.println("Sending Final Report Email");
	 * 
	 * Transport.send(message);
	 * 
	 * System.out.println("Email sent");
	 * 
	 * } catch (MessagingException e) { throw new RuntimeException(e); } }
	 * 
	 * public static void EmailResultsAsAttachment(String From, String To,
	 * String Password, String Subject, String Content, String Path) {
	 * 
	 * final String username = From; final String password = Password;
	 * 
	 * Properties props = new Properties();
	 * props.put("mail.smtp.starttls.enable", "true");
	 * props.put("mail.smtp.auth", "true"); props.put("mail.smtp.host",
	 * "smtp.gmail.com"); props.put("mail.smtp.port", "587");
	 * 
	 * Session session = Session.getInstance(props, new
	 * javax.mail.Authenticator() {
	 * 
	 * @Override protected PasswordAuthentication getPasswordAuthentication() {
	 * return new PasswordAuthentication(username, password); } });
	 * 
	 * try {
	 * 
	 * Message message = new MimeMessage(session); message.setFrom(new
	 * InternetAddress(From)); message.setRecipients(Message.RecipientType.TO,
	 * InternetAddress.parse(To)); message.setSubject(Subject);
	 * message.setText("Dear " + To + "," + "\n\n " + Content);
	 * 
	 * MimeBodyPart messageBodyPart = new MimeBodyPart();
	 * 
	 * Multipart multipart = new MimeMultipart();
	 * 
	 * messageBodyPart = new MimeBodyPart(); String fileName = Path; DataSource
	 * source = new FileDataSource(fileName); messageBodyPart.setDataHandler(new
	 * DataHandler(source)); messageBodyPart.setFileName(fileName);
	 * multipart.addBodyPart(messageBodyPart);
	 * 
	 * message.setContent(multipart);
	 * 
	 * System.out.println("Sending Final Report Email");
	 * 
	 * Transport.send(message);
	 * 
	 * System.out.println("Email sent");
	 * 
	 * } catch (MessagingException e) { throw new RuntimeException(e); } }
	 */
}