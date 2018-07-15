package read;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import payment.Payment;

public class ReaderCSV extends Reader {
	private Scanner scn;
	private int count = 1;

	public ReaderCSV(File file) {
		super(file);
		try {
			scn = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ReaderCSV() {
		super();
	}

	@Override
	public Payment read() {
		if (!scn.hasNextLine()) {
			scn.close();
			return null;
		}
		Payment payment = new Payment();
		String str = scn.nextLine();
		String[] strPayment = str.split(",");
		if (strPayment.length != 4) {
			payment.setResult("Fail. Payment don't include full information.");
		} else {
			try {
				payment.setOrderid(Long.parseLong(strPayment[0]));
			} catch (NumberFormatException e) {
				payment.setResult("Fail. Payment's id does not contain a number.");
			}
			try {
				payment.setAmount(Long.parseLong(strPayment[1]));
			} catch (NumberFormatException e) {
				if (payment.getResult() == null) {
					payment.setResult("Fail. Payment's amount does not contain a number.");
				} else {
					String error = payment.getResult();
					payment.setResult(error + " Payment's amount does not contain a number.");
				}
			}
			payment.setCurrency(strPayment[2]);
			payment.setComment(strPayment[3]);
			if (payment.getResult() == null)
				payment.setResult("OK");
		}
		payment.setLine(count);
		payment.setFileName(file.toString());
		count++;
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
