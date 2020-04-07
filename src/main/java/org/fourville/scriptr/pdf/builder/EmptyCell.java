package org.fourville.scriptr.pdf.builder;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmptyCell implements Cell {

    @Builder.Default
    private int border = Rectangle.NO_BORDER;

    @Override
    public PdfPCell toCell() {
        PdfPCell pdfPCell = new PdfPCell();
        pdfPCell.setBorder(border);
        return pdfPCell;
    }
}
