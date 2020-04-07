package org.fourville.scriptr.pdf;

import org.fourville.scriptr.pdf.invoice.Invoice;

import java.util.List;

public class Application {

	public static void main(String[] args) {
		Generator generator = new Generator();
		invoices().stream().forEach(i -> generator.generate(i));
	}


	public static List<Invoice> invoices() {
		return List.of();
	}

}
