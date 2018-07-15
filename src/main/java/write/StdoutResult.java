package write;

import payment.Payment;

public class StdoutResult implements ResultWriteInterface {

	public void write(Payment payment) {
		System.out.println(payment.toString());
	}

}
