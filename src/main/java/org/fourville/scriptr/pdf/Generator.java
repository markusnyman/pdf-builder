package org.fourville.scriptr.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.fourville.scriptr.pdf.builder.*;
import org.fourville.scriptr.pdf.invoice.Invoice;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;

@Component
public class Generator {

    public void generate(Invoice invoice) {
        try {
            Document document = new Document(PageSize.A4, 0,0,18,18);
            PdfWriter.getInstance(document, new FileOutputStream(invoice.getInvoiceReference() + " " + invoice.getInvoiceDate() + " " + invoice.getInvoicee().getName() + ".pdf"));
            document.open();
            PdfPTable table = new PdfPTable(1);

            table.addCell(
                    Row.builder()
                            .cell(Text.builder().text(invoice.getCompany().getName()).font(FontFactory.HELVETICA_BOLD).fontSize(10).build())
                            .cell(Text.builder().text(invoice.getInvoiceReference()).capitalize(true).font(FontFactory.HELVETICA_BOLD).fontSize(10).build())
                            .build().toCell());
            table.addCell(Row.builder().height(30).cell(EmptyCell.builder().build()).build().toCell());
            table.addCell(Row.builder().height(70)
                    .cell(EmptyCell.builder().build())
                    .cell(TextTable.builder().columns(1)
                            .entry(Text.builder().text(invoice.getInvoicee().getName()).fontSize(10).capitalize(true).build())
                            .entry(Text.builder().text(invoice.getInvoicee().getStreet()).fontSize(10).capitalize(true).build())
                            .entry(Text.builder().text(invoice.getInvoicee().getPostalCode() + " " + invoice.getInvoicee().getLocation()).fontSize(10).capitalize(true).build())
                            .border(Rectangle.BOX)
                            .build())
                    .build().toCell());
            table.addCell(Row.builder().height(100)
                    .cell(TextTable.builder()
                            .entry(Text.builder().text("Vår referens").font(FontFactory.HELVETICA_BOLD).build())
                            .entry(Text.builder().text(invoice.getCompany().getReference()).build())
                            .entry(Text.builder().text("Er referens").font(FontFactory.HELVETICA_BOLD).build())
                            .entry(Text.builder().text(invoice.getInvoicee().getReference()).build())
                            .entry(Text.builder().text("Fakturadatum").font(FontFactory.HELVETICA_BOLD).build())
                            .entry(Text.builder().text(invoice.getInvoiceDate().toString()).build())
                            .entry(Text.builder().text("Förfallodatum").font(FontFactory.HELVETICA_BOLD).build())
                            .entry(Text.builder().text(invoice.getDueDate().toString()).build())
                            .entry(Text.builder().text("Dröjsmålsränta").font(FontFactory.HELVETICA_BOLD).build())
                            .entry(Text.builder().text("Vid betalning efter förfallodagen debiteras ränta enligt räntelagen").build())
                            .border(Rectangle.NO_BORDER)
                            .build())
                    .cell(EmptyCell.builder().build())
                    .build().toCell());
            table.addCell(Row.builder().height(300).border(Rectangle.BOX).cell(
                    Table.builder()
                            .entry(Text.builder().text("Artikelnr").font(FontFactory.HELVETICA_BOLD).paddingBottom(10).border(Rectangle.BOTTOM).build())
                            .entry(Text.builder().text("Benämning").font(FontFactory.HELVETICA_BOLD).paddingBottom(10).border(Rectangle.BOTTOM).build())
                            .entry(Text.builder().text("Summa").font(FontFactory.HELVETICA_BOLD).paddingBottom(10).border(Rectangle.BOTTOM).build())
                            .entry(Text.builder().text(String.valueOf(invoice.getArticle().getNumber())).build())
                            .entry(Text.builder().text(invoice.getArticle().getArticleText()).build())
                            .entry(Text.builder().text(invoice.getArticle().getArticlePrice()).alignment(Rectangle.ALIGN_RIGHT).build())
                            .border(Rectangle.NO_BORDER)
                            .build()
            ).build().toCell());
            table.addCell(Row.builder()
                    .cell(EmptyCell.builder().build())
                    .cell(TextTable.builder()
                            .border(Rectangle.BOX)
                            .entry(Text.builder().text("Exkl moms").font(FontFactory.HELVETICA_BOLD).build())
                            .entry(Text.builder().text(invoice.getArticle().getArticlePrice()).alignment(Rectangle.ALIGN_RIGHT).build())
                            .entry(Text.builder().text("Moms (0%)").font(FontFactory.HELVETICA_BOLD).build())
                            .entry(Text.builder().text("0,00 kr").alignment(Rectangle.ALIGN_RIGHT).build())
                            .entry(Text.builder().text("Totalt").font(FontFactory.HELVETICA_BOLD).build())
                            .entry(Text.builder().text(invoice.getArticle().getArticlePrice()).alignment(Rectangle.ALIGN_RIGHT).build())
                            .entry(Text.builder().text(" ").build())
                            .entry(Text.builder().text(" ").build())
                            .entry(Text.builder().text(" ").build())
                            .entry(Text.builder().text(" ").build())
                            .entry(Text.builder().text("Att betala").fontSize(14).font(FontFactory.HELVETICA_BOLD).build())
                            .entry(Text.builder().text(invoice.getArticle().getArticlePrice()).fontSize(14).alignment(Rectangle.ALIGN_RIGHT).font(FontFactory.HELVETICA_BOLD).build())
                            .build())
                    .build().toCell());
            table.addCell(Row.builder()
                    .cell(EmptyCell.builder().build())
                    .cell(TextTable.builder()
                            .border(Rectangle.BOX)
                            .entry(Text.builder().text("Förfallodatum").font(FontFactory.HELVETICA_BOLD).build())
                            .entry(Text.builder().text(invoice.getDueDate().toString()).alignment(Rectangle.ALIGN_RIGHT).build())
                            .entry(Text.builder().text("Bankgiro").font(FontFactory.HELVETICA_BOLD).build())
                            .entry(Text.builder().text(invoice.getCompany().getBankgiro()).alignment(Rectangle.ALIGN_RIGHT).build())
                            .entry(Text.builder().text("Betalningsreferens").font(FontFactory.HELVETICA_BOLD).build())
                            .entry(Text.builder().text(invoice.getInvoiceReference()).alignment(Rectangle.ALIGN_RIGHT).build())
                            .build())
                    .build().toCell());
            table.addCell(Row.builder().height(50).cell(EmptyCell.builder().build()).build().toCell());
            table.addCell(Row.builder().height(20).cell(Text.builder().text("Artistframträdanden är momsfria").build()).build().toCell());
            table.addCell(Row.builder().border(Rectangle.TOP) // .height(100)
                    .cell(TextTable.builder().columns(1)
                            .entry(Text.builder().text("Adress").font(FontFactory.HELVETICA_BOLD).build())
                            .entry(Text.builder().text(invoice.getCompany().getName()).build())
                            .entry(Text.builder().text(invoice.getCompany().getAddress1()).build())
                            .entry(Text.builder().text(invoice.getCompany().getAddress2()).build())
                            .build())
                    .cell(TextTable.builder().columns(1)
                            .entry(Text.builder().text("Telefon").font(FontFactory.HELVETICA_BOLD).build())
                            .entry(Text.builder().text(invoice.getCompany().getPhoneNumber()).build())
                            .entry(Text.builder().text("Email").font(FontFactory.HELVETICA_BOLD).build())
                            .entry(Text.builder().text(invoice.getCompany().getEmail()).build())
                            .build())
                    .cell(TextTable.builder().columns(1)
                            .entry(Text.builder().text("Organisationsnr.").font(FontFactory.HELVETICA_BOLD).build())
                            .entry(Text.builder().text(invoice.getCompany().getOrganisationNumber()).build())
                            .entry(Text.builder().text("Godkänd för F-skatt").font(FontFactory.HELVETICA_BOLD).build())
                            .build())
                    .cell(TextTable.builder().columns(1)
                            .entry(Text.builder().text("Momsreg. nr.").font(FontFactory.HELVETICA_BOLD).build())
                            .entry(Text.builder().text(invoice.getCompany().getVatNumber()).build())
                            .entry(Text.builder().text("Bankgiro").font(FontFactory.HELVETICA_BOLD).build())
                            .entry(Text.builder().text(invoice.getCompany().getBankgiro()).build())
                            .build())
                    .build().toCell());

            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
