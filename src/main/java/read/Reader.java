package read;

import java.io.File;

import payment.Payment;

public abstract class Reader {
	protected File file;

	public Reader() {
	};

	public Reader(File file) {
		super();
		this.file = file;
	}

	public abstract Payment read();

	public abstract boolean hasNextPayment();

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
