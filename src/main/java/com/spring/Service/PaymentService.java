package com.spring.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.spring.CustomObject.PaymentEditDto;
import com.spring.CustomObject.PaymentListDto;
import com.spring.Model.Payment;
import com.spring.Model.User;
import com.spring.Model.UserAccount;
import com.spring.Repository.PaymentRepository;

@Service
@Transactional
public class PaymentService extends AbstractService {

	@Autowired
	private UserService serviceUser;

	@Autowired
	private BoxService serviceBox;

	@Autowired
	private PaymentRepository repository;

	public Collection<PaymentListDto> findPaymentsByUserLogged() {
		User user = this.serviceUser.getUserByPrincipal();
		return repository.findPaymentsByUser(user.getUserAccount().getId()).stream()
				.map(x -> new PaymentListDto(x.getExpiredDate(), x.getPaymentDate(), x.getBox().getName(),
						x.getBox().getPrice()))
				.collect(Collectors.toList());
	}

	public PaymentEditDto save(PaymentEditDto payment) {

		Payment saveTo = null;

		if (payment.getId() == 0) {
			if (payment.getExpiredDate().isAfter(LocalDate.now())) {
				saveTo = new Payment(LocalDate.now(), this.serviceBox.getOne(payment.getBox()),
						this.serviceUser.getUserByPrincipal().getUserAccount(), payment.getExpiredDate());
				saveTo = repository.saveAndFlush(saveTo);
				payment.setId(saveTo.getId());
			} else {
				throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,
						"Expired date is not later than currently");
			}

		}

		return payment;
	}

	public void flush() {
		repository.flush();
	}

	public Payment findByUserAccount(UserAccount userAccount) {
		Payment res;
		List<Payment> payments = this.repository.findByUserAccount(userAccount);
		if (payments.isEmpty()) {
			res = null;
		} else {
			res = payments.get(0);
		}
		return res;
	}

}
