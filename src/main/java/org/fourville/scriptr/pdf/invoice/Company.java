package org.fourville.scriptr.pdf.invoice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Company {
    private String reference;
    private String name;
    private String address1;
    private String address2;
    private String phoneNumber;
    private String email;
    private String bankgiro;
    private String organisationNumber;
    private String vatNumber;

}
