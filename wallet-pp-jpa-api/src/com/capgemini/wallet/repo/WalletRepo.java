package com.capgemini.wallet.repo;

import java.sql.SQLException;

import com.capgemini.wallet.beans.Customer;
import com.capgemini.wallet.exception.AlreadyRegisteredMobileException;
import com.capgemini.wallet.exception.MobileNotFoundException;

public interface WalletRepo {
	
	public boolean save(Customer customer) throws AlreadyRegisteredMobileException, ClassNotFoundException, MobileNotFoundException;
	public Customer findOne(String mobileno) throws  MobileNotFoundException;

	public void update(Customer enroll);
	
	

}
