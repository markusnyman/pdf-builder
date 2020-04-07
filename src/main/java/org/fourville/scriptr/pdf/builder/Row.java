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
public class Row implements Cell{
    private int height;
    @Builder.Default
    private int border = Rectangle.NO_BORDER;
    @Singular
    private List<Cell> cells;

    public PdfPCell toCell() {
        PdfPTable table = new PdfPTable(cells.size());
        cells.stream().forEach(c -> table.addCell(c.toCell()));
        PdfPCell pdfPCell = new PdfPCell(table);
        pdfPCell.setBorder(border);
        if (height > 0) {
            pdfPCell.setFixedHeight(height);
        }
        return pdfPCell;
    }

}
