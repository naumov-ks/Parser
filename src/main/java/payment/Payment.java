package payment;

public class Payment {
	private Long orderid;
	private Long amount;
	private String comment;
	private String currency;
	private String fileName;
	private int line;
	private String result;

	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "{\"id\":" + orderid + ", \"amount\":" + amount + ", \"comment\":" + "\"" + comment + "\""
				+ ", \"filename\":\"" + fileName + "\"" + ", \"line\":" + line + ",\"result\":" + "\"" + result + "\""
				+ "}";
	}

}
