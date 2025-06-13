package com.nisum;
sealed class PaymentException extends Exception
        permits InvalidPaymentMethodException, InvalidOfferAppliedException {
    PaymentException(String message) { super(message); }
}

final class InvalidPaymentMethodException extends PaymentException {
    InvalidPaymentMethodException(String method) {
        super("Invalid payment method: " + method);
    }
}

final class InvalidOfferAppliedException extends PaymentException {
    InvalidOfferAppliedException(String offer) {
        super("Invalid offer for payment method: " + offer);
    }
}

public class PaymentService {

    public void paymentMethod(String paymentType, String appliedOffer) throws PaymentException {
        if (!isValidPaymentMethod(paymentType)) {
            throw new InvalidPaymentMethodException(paymentType);
        }
        if (!isValidOffer(paymentType, appliedOffer)) {
            throw new InvalidOfferAppliedException(appliedOffer);
        }
        System.out.println("Processing " + paymentType + " with offer: " + appliedOffer);
    }

    private boolean isValidPaymentMethod(String method) {
        return method != null &&
                (method.equals("CreditCard") ||
                        method.equals("PayPal") ||
                        method.equals("UPI"));
    }

    private boolean isValidOffer(String method, String offer) {
        if (offer == null || offer.isEmpty()) return true;
        return switch (method) {
            case "CreditCard" -> offer.equals("CashBack") || offer.equals("Discount");
            case "PayPal" -> offer.equals("FreeShipping");
            case "UPI" -> offer.equals("InstantDiscount");
            default -> false;
        };
    }

    public void processPaymentMethod(String paymentType, String appliedOffer) {
        try {
            paymentMethod(paymentType, appliedOffer);
        } catch (PaymentException e) {
            handlePaymentException(e);
        }
    }

    // Java 17-compatible exception handling
    private void handlePaymentException(PaymentException e) {
        if (e instanceof InvalidPaymentMethodException ex) {
            System.err.println("Payment Method Error: " + ex.getMessage());
        }
        else if (e instanceof InvalidOfferAppliedException ex) {
            System.err.println("Offer Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        PaymentService service = new PaymentService();
        service.processPaymentMethod("CreditCard", "CashBack");  // Valid
        service.processPaymentMethod("DebitCard", null);        // Invalid method
        service.processPaymentMethod("PayPal", "Discount");      // Invalid offer
    }
}