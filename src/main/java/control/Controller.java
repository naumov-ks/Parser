package control;

import java.io.File;
import payment.Payment;
import read.Reader;
import write.ResultWriteInterface;

public class Controller {

	private ResultWriteInterface resultWriter;
	private Reader reader;
	private File file;

	public Controller() {
	};

	public Controller(ResultWriteInterface resultWriter, Reader reader, File file) {
		this.resultWriter = resultWriter;
		this.reader = reader;
		this.file = file;
	}

	public ResultWriteInterface getResultWriter() {
		return resultWriter;
	}

	public void setResultWriter(ResultWriteInterface resultWriter) {
		this.resultWriter = resultWriter;
	}

	public Reader getReader() {
		return reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
		reader.setFile(file);
	}

	public boolean hasNextPayment() {
		if (reader.hasNextPayment()) {
			return true;
		} else
			return false;
	}

	public Payment read() {
		Payment payment = reader.read();
		return payment;
	}

	public void write(Payment payment) {
		resultWriter.write(payment);
	}

}
