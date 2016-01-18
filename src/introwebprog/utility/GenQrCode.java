package introwebprog.utility;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import java.io.*;

/**
 * Created by matteo on 18/01/16.
 */
public class GenQrCode {

    public ByteArrayOutputStream genQR(String message)
    {
        ByteArrayOutputStream out = QRCode.from(message).to(ImageType.PNG).stream();
        return out;
    }
}
