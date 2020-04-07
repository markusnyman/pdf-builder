package org.fourville.scriptr.pdf.invoice;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.text.NumberFormat;

@Data
@Builder
public class Article {
    private int number;
    private String description;
    private String additionalDescription;
    private BigDecimal price;

    public String getArticleText() {
        return description + ". " + additionalDescription + ".";
    }
    public String getArticlePrice() {
        return NumberFormat.getCurrencyInstance().format(price);
    }


}
