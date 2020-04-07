package org.fourville.scriptr.pdf;

import org.fourville.scriptr.pdf.invoice.Article;
import org.fourville.scriptr.pdf.invoice.Company;
import org.fourville.scriptr.pdf.invoice.Invoice;
import org.fourville.scriptr.pdf.invoice.Invoicee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Application {

	private static AtomicInteger invoiceNumber = new AtomicInteger(62); // sista fakturan 2019 var nr 62

	public static void main(String[] args) {
		Generator generator = new Generator();
		invoices().stream().forEach(i -> generator.generate(i));
	}


	public static List<Invoice> invoices() {
		return List.of(
				Invoice.builder()
						.company(MYMAN_CONSULTING)
						.invoicee(FREDRIK_JOHANSSON)
						.article(from("The Auld Dub Stockholm, St Paddys Weekend", 5833))
						.invoiceDate(LocalDate.parse("2020-04-07"))
						.dueDays(10)
						.invoiceNumber(invoiceNumber.incrementAndGet())
						.build()
		);
	}


	public static Article from(String venue, int price) {
		return Article.builder()
				.number(1)
				.description("Artistframträdande Stamp and go Shanty")
				.additionalDescription(venue)
				.price(BigDecimal.valueOf(price))
				.build();
	}

	public static Company MYMAN_CONSULTING = Company.builder()
			.reference("Markus Nyman")
			.name("myman music - Markus Nyman")
			.address1("Viktor Sjöströms väg 5")
			.address2("16940 Solna")
			.phoneNumber("+46707516899")
			.email("markusnyman@gmail.com")
			.bankgiro("5317-2011")
			.organisationNumber("750918-0118")
			.vatNumber("SE750918011801")
			.build();

	public static Invoicee FREDRIK_JOHANSSON = Invoicee.builder()
			.reference("Fredde Jii")
			.name("Fredrik Johansson")
			.street("Spikvägen 45")
			.postalCode("12638")
			.location("Hägersten")
			.build();
}
