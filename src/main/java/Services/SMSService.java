package Services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMSService {
    private static final String ACCOUNT_SID = "your_account_sid";
    private static final String AUTH_TOKEN = "your_auth_token";
    private static final String FROM_PHONE_NUMBER = "your_twilio_phone_number";

    static {
        Twilio.init("AC3788dff27b6661da6ca889608c31c6ad", "737a1fdeee4dfd3b08158d090d73c083");
    }


    public void sendSMS(String toPhoneNumber, String messageBody) {
        Message message = Message.creator(
                new PhoneNumber("+21654629015"),
                new PhoneNumber("+21654629015"),
                messageBody
        ).create();
        System.out.println("SMS sent: " + message.getSid());
    }
}
