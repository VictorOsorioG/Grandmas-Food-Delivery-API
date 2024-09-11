import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class word {
    public static void main(String[] args) {
        // Create a new document
        XWPFDocument document = new XWPFDocument();

        // Create a title
        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.setText("GRANDMAS FOOD DELIVERY API");
        titleRun.setBold(true);
        titleRun.setFontSize(20);

        // Create a paragraph
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun paragraphRun = paragraph.createRun();
        paragraphRun.setText("This is a sample paragraph in the Word document created using Apache POI.");

        // Write the document to a file
        try (FileOutputStream out = new FileOutputStream("example.docx")) {
            document.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
