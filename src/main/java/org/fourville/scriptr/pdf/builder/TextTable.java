package org.fourville.scriptr.pdf.builder;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder
public class TextTable implements Cell {

    @Builder.Default
    private int columns = 2;
    @Builder.Default
    private float padding = 2;
    // TODO markusnyman 2020-04-07: NO_BORDER as default
    @Builder.Default
    private int border = Rectangle.NO_BORDER; //Rectangle.NO_BORDER;

    @Singular
    private List<Text> entries;

    public PdfPCell toCell() {
        PdfPCell pdfPCell = new PdfPCell();
        pdfPCell.setPadding(padding);
        pdfPCell.setBorder(border);
        PdfPTable table = new PdfPTable(columns);
        // TODO markusnyman 2020-04-07: Should this be configurable?
        table.setWidthPercentage(100);
        entries.stream().forEach(e ->  {
            table.addCell(e.toCell());
        });
        pdfPCell.addElement(table);
        return pdfPCell;

    }

}
