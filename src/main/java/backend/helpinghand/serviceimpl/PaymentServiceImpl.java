package backend.helpinghand.serviceimpl;

import backend.helpinghand.entities.Payment;
import backend.helpinghand.repositories.PaymentRepository;
import backend.helpinghand.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepository paymentRepository;
    @Override
    public Payment addCPayment(Payment payment) {
        return paymentRepository.save(payment);
    }


}
