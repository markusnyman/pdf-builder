package org.fourville.scriptr.pdf.builder;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Text implements Cell {
    @Builder.Default
    private String font = FontFactory.HELVETICA;
    @Builder.Default
    private int fontSize = 8;
    @Builder.Default
    private BaseColor fontcolor = BaseColor.BLACK;
    @Builder.Default
    private float padding = 2;
    @Builder.Default
    private float paddingBottom = 2;
    // TODO markusnyman 2020-04-07: NO_BORDER as default
    @Builder.Default
    private int border = Rectangle.NO_BORDER; //Rectangle.NO_BORDER;
    @Builder.Default
    private int alignment = Rectangle.ALIGN_LEFT; //Rectangle.NO_BORDER;
    @Builder.Default
    private boolean capitalize = false;

    private String text;

    public PdfPCell toCell() {
        String theText = capitalize ? text.toUpperCase() : text;
        Chunk element = new Chunk(theText, FontFactory.getFont(font, fontSize, fontcolor));
        Paragraph paragraph = new Paragraph(element);
        PdfPCell cell = new PdfPCell(paragraph);
        cell.setPadding(padding);
        cell.setPaddingBottom(paddingBottom);
        cell.setBorder(border);
        cell.setUseBorderPadding(false);
        cell.setHorizontalAlignment(alignment);

        return cell;
    }
}
