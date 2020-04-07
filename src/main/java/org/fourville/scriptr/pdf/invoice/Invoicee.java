package org.fourville.scriptr.pdf.invoice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Invoicee {
    private String reference;
    private String name;
    private String street;
    private String postalCode;
    private String location;

}
