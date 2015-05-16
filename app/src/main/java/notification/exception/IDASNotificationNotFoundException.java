package notification.exception;

/**
 * Created by AbS01ute on 5/16/15 AD.
 */
public class IDASNotificationNotFoundException extends Throwable {
    public IDASNotificationNotFoundException(int id) {
        super(String.valueOf(id));
    }
}
