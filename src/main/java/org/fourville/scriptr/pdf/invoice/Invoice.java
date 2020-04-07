package org.fourville.scriptr.pdf.invoice;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@Builder
public class Invoice {

    private Company company;
    private Invoicee invoicee;
    private Article article;
    private LocalDate invoiceDate;
    private int dueDays;
    private int invoiceNumber;

    public String getInvoiceReference() {
        return "Faktura " + invoiceNumber;
    }

    public LocalDate getDueDate() {
        return invoiceDate.plus(dueDays, ChronoUnit.DAYS);
    }

}
