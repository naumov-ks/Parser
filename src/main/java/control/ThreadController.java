package control;

import java.io.File;

import payment.Payment;

public class ThreadController implements Runnable {
	private Controller controller;

	public ThreadController() {
		// TODO Auto-generated constructor stub
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public void setFile(File file) {
		controller.setFile(file);
	}

	public ThreadController(Controller controller) {
		super();
		this.controller = controller;
	}

	public void run() {
		while (controller.hasNextPayment()) {
			Payment payment = controller.read();
			controller.write(payment);
		}
	}

}
