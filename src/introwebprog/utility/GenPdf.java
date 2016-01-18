package introwebprog.utility;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by matteo on 18/01/16.
 */
public class GenPdf {

    /** Path to the resulting PDF file. */
    private static final String RESULT = "/home/matteo/Scrivania/hello.pdf";

    public static void main(String[] args)
            throws DocumentException, IOException {
        new GenPdf().createPdf("hello", "hello world", null);
    }

    public String createPdf(String ticketName, String text, Image qr) throws DocumentException, IOException {
        //String filename = "/home/matteo/Scrivania/"+ticketName+".pdf";
        String filename = ticketName+".pdf";


        // step 1
        Document document = new Document();
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph(text));
        document.add(qr);
        // step 5
        document.close();

        return filename;
    }
}
