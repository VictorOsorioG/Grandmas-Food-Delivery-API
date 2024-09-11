import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.*;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;

public class Pdf {
    public static void main(String[] args) throws IOException {
        PDDocument document = new PDDocument();
        PDPage firstPage = new PDPage(PDRectangle.A4);
        document.addPage(firstPage);


        int pageWith = (int) firstPage.getMediaBox().getWidth();
        int pageHeight = (int) firstPage.getMediaBox().getHeight();

        PDPageContentStream contentStream = new PDPageContentStream(document, firstPage);
        MyTextClass myTextClass = new MyTextClass(document, contentStream);

        PDFont font = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
        PDFont italicFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA_OBLIQUE);

        PDImageXObject headImage = PDImageXObject.createFromFile("src/main/resources/img/Grands.jpg", document);
        contentStream.drawImage(headImage, 0, pageHeight - 235, pageWith, 239);

        MyTableClass myTable = new MyTableClass(document, contentStream);
        int[] cellWidths = {70, 160, 120}; // Ajuste de los anchos de las celdas
        myTable.setTable(cellWidths, 30, 25, pageHeight - 350);
        myTable.setTableFont(font, 16, Color.BLACK);

        Color tableHeadColor = new Color(240, 93, 11);
        Color tableBodyColor = new Color(219, 218, 198);

        myTable.addCell("Category_Title", tableHeadColor);
        myTable.addCell("FantasyName", tableHeadColor);
        myTable.addCell("PriceWithTax", tableHeadColor);

        // for de la lista
        myTable.addCell("item1", tableBodyColor);
        myTable.addCell("item2", tableBodyColor);
        myTable.addCell("item3", tableBodyColor);

        contentStream.close();
        document.save("test.pdf");
        document.close();
        System.out.println("PDF created");
    }

    private static class MyTextClass {
        PDDocument document;
        PDPageContentStream contentStream;

        public MyTextClass(PDDocument document, PDPageContentStream contentStream) {
            this.document = document;
            this.contentStream = contentStream;
        }

        void addSingleLineText(String text, int xPosition, int yPosition, PDFont font, float fontSize, Color color) throws IOException {
            contentStream.beginText();
            contentStream.setFont(font, fontSize);
            contentStream.setNonStrokingColor(color);
            contentStream.newLineAtOffset(xPosition, yPosition);
            contentStream.showText(text);
            contentStream.endText();
            contentStream.moveTo(0, 0);
        }

        void addMultilineText(String[] textArray, float leading, int xPosition, int yPosition, PDFont font, float fontSize, Color color) throws IOException {
            contentStream.beginText();
            contentStream.setFont(font, fontSize);
            contentStream.setNonStrokingColor(color);
            contentStream.setLeading(leading);
            contentStream.newLineAtOffset(xPosition, yPosition);
            for (String text : textArray) {
                contentStream.showText(text);
                contentStream.newLine();
            }
            contentStream.endText();
            contentStream.moveTo(0, 0);
        }

        float getTextWidth(String text, PDFont font, float fontSize) throws IOException {
            return font.getStringWidth(text) / 1000 * fontSize;
        }
    }

    private static class MyTableClass {
        PDDocument document;
        PDPageContentStream contentStream;
        private int[] colWidths;
        private int cellHeight;
        private int yPosition;
        private int xPosition;
        private int colPosition = 0;
        private int xInitialPosition;
        private float fontSize;
        private PDFont font;
        private Color fontColor;

        public MyTableClass(PDDocument document, PDPageContentStream contentStream) {
            this.document = document;
            this.contentStream = contentStream;
        }

        void setTable(int[] colWidths, int cellHeight, int xPosition, int yPosition) {
            this.colWidths = colWidths;
            this.cellHeight = cellHeight;
            this.xPosition = xPosition;
            this.yPosition = yPosition;
            this.xInitialPosition = xPosition;
        }

        void setTableFont(PDFont font, float fontSize, Color fontColor) {
            this.font = font;
            this.fontSize = fontSize;
            this.fontColor = fontColor;
        }

        void addCell(String text, Color fillColor) throws IOException {
            contentStream.setStrokingColor(1f);

            if (fillColor != null) {
                contentStream.setNonStrokingColor(fillColor);
            }

            contentStream.addRect(xPosition, yPosition, colWidths[colPosition], cellHeight);

            if (fillColor == null) {
                contentStream.stroke();
            } else {
                contentStream.fillAndStroke();
            }

            contentStream.beginText();
            contentStream.setNonStrokingColor(fontColor);
            contentStream.setFont(font, fontSize);
            contentStream.newLineAtOffset(xPosition + 5, yPosition + cellHeight / 4); // Ajuste de la posición del texto
            contentStream.showText(text);
            contentStream.endText();

            xPosition = xPosition + colWidths[colPosition];
            colPosition++;
            if (colPosition == colWidths.length) {
                colPosition = 0;
                xPosition = xInitialPosition;
                yPosition -= cellHeight; // Mover a la siguiente fila
            }
        }
    }
}