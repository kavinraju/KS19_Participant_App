package dpi.ks19.participantapp.CallbackInterface;

import android.graphics.Bitmap;

public interface QrResponse {
    void getQRCode(boolean success, Bitmap qr);
}
