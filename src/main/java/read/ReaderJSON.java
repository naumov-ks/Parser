package read;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import payment.Payment;

public class ReaderJSON extends Reader {
	private Scanner scn;
	private int count = 1;
	JSONParser parser = new JSONParser();

	@Override
	public Payment read() {
		if (!scn.hasNextLine()) {
			scn.close();
			return null;
		}
		Payment payment = new Payment();
		String str = scn.nextLine();
		String error = null;
		try {
			JSONObject paid = (JSONObject) parser.parse(str);
			if (paid.size() != 4 || !paid.containsKey("orderid") || !paid.containsKey("amount")
					|| !paid.containsKey("comment") || !paid.containsKey("currency")) {
				error = "Fail. Payment don't include full information.";
			} else {
				try {
					Long orderid = (Long) paid.get("orderid");
					payment.setOrderid(orderid);
				} catch (ClassCastException e) {
					error = "Fail. Payment's id does not contain a number.";
				}
				try {
					Long amount = (Long) paid.get("amount");
					payment.setAmount(amount);
				} catch (ClassCastException e) {
					if (error == null) {
						error = "Fail. Payment's amount does not contain a number.";
					} else {
						error = error + " Payment's amount does not contain a number.";
					}
				}
				try {
					String comment = (String) paid.get("comment");
					payment.setComment(comment);
				} catch (ClassCastException e) {
					if (error == null) {
						error = "Fail. Payment's comment does not contain a string.";
					} else {
						error = error + " Payment's comment does not contain a string.";
					}
				}
				try {
					String currency = (String) paid.get("currency");
					payment.setCurrency(currency);
				} catch (ClassCastException e) {
					if (error == null) {
						error = "Fail. Payment's currency does not contain a string.";
					} else {
						error = error + " Payment's currency does not contain a string.";
					}
				}
			}
		} catch (ParseException e) {
			payment.setResult("Fail. Format of parsing line is not JSON.");
		}
		payment.setLine(count);
		payment.setFileName(file.toString());
		count++;
		if (error == null)
			payment.setResult("OK");
		else
			payment.setResult(error);
		return payment;
	}

	@Override
	public boolean hasNextPayment() {
		if (scn.hasNextLine()) {
			return true;
		} else
			scn.close();
		return false;
	}

	private void addScanner(File file) {
		Scanner scn;
		try {
			scn = new Scanner(file);
			this.scn = scn;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setFile(File file) {
		this.file = file;
		addScanner(file);
	}
}
