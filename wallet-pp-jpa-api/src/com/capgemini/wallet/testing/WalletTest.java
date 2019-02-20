package com.capgemini.wallet.testing;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.wallet.beans.Customer;
import com.capgemini.wallet.beans.Wallet;
import com.capgemini.wallet.bl.WalletService;
import com.capgemini.wallet.bl.WalletServiceImpl;
import com.capgemini.wallet.exception.AlreadyRegisteredMobileException;
import com.capgemini.wallet.exception.MobileNotFoundException;
import com.capgemini.wallet.exception.NegativeWithdrwalException;
import com.capgemini.wallet.repo.WalletRepo;
import com.capgemini.wallet.repo.WalletRepoImpl;

public class WalletTest {

	WalletRepo repo;
	WalletService service;
	
	@Before
	public void setUp() throws Exception {
		repo=new WalletRepoImpl();
		service=new WalletServiceImpl(repo);
		
	}

	@Test(expected=AlreadyRegisteredMobileException.class)
	public void WhenMobileAlreadyRegisteredSystemThrowException() throws AlreadyRegisteredMobileException, ClassNotFoundException, MobileNotFoundException {
	
		service.createAccount("pulkit","7417700336",new BigDecimal("600"));
		service.createAccount("raju","7417700336",new BigDecimal("6050"));
	}
	
	@Test
	public void WhenAccountSuccessfullyCreated() throws AlreadyRegisteredMobileException, ClassNotFoundException, MobileNotFoundException  {
		
		Customer customer=new Customer();
		Wallet wallet=new Wallet();
		
		customer.setMobileno("9259357200");
		customer.setName("devi");
		wallet.setBalance(new BigDecimal("700"));
		customer.setWallet(wallet);
		
		assertEquals(customer,service.createAccount("devi","9259357200",new BigDecimal("700")));
		
	}
	
	@Test(expected=MobileNotFoundException.class)
	public void WhenShowingDetailsMobileIsNotRegistered() throws  MobileNotFoundException {
	
		service.showBalance("7417701336");
		
	}
	
	@Test
	public void WhenBalanceSuccessfullyDisplayed() throws MobileNotFoundException, AlreadyRegisteredMobileException, ClassNotFoundException    {
		
		Customer customer=new Customer();
		Wallet wallet=new Wallet();
		
		customer.setMobileno("9548071012");
		customer.setName("rakesh");
		wallet.setBalance(new BigDecimal("8000"));
		customer.setWallet(wallet);
		repo.save(customer);
		assertEquals(customer,service.showBalance("9548071012"));
		
	}
	
	@Test(expected=NegativeWithdrwalException.class)
	public void WhenAfterWithdrawalAmountBecomesLessThan0() throws NegativeWithdrwalException, MobileNotFoundException, AlreadyRegisteredMobileException, ClassNotFoundException    {
		
		
		service.createAccount("rancho","8000000000",new BigDecimal("700"));
		service.withdrawAmount("8000000000", new BigDecimal("900"));
		
	}

	
	@Test(expected=MobileNotFoundException.class)
	public void DuringWithdrawingMobileNotRegisteredSystemThrowException() throws NegativeWithdrwalException, MobileNotFoundException, AlreadyRegisteredMobileException, ClassNotFoundException    {
		
		
		service.createAccount("shahrukh","9000000000",new BigDecimal("700"));
		service.withdrawAmount("9000000020", new BigDecimal("300"));
		
	}
	
	@Test
	public void whenfundTransferSuccessfully() throws NegativeWithdrwalException, MobileNotFoundException, AlreadyRegisteredMobileException, ClassNotFoundException    {
		
		Customer customer1=new Customer();
		Wallet wallet1=new Wallet();
		Customer customer2=new Customer();
		Wallet wallet2=new Wallet();
		
		customer1.setMobileno("7635241890");
		customer1.setName("vivek");
		wallet1.setBalance(new BigDecimal("600"));
		customer1.setWallet(wallet1);
		repo.save(customer1);
		
		customer2.setMobileno("8456321790");
		customer2.setName("sanjay");
		wallet2.setBalance(new BigDecimal("600"));
		customer2.setWallet(wallet2);
		repo.save(customer2);
		
		assertEquals(new BigDecimal("300"), service.fundTransfer("7635241890","8456321790", new BigDecimal("300")).getWallet().getBalance());
		assertEquals(new BigDecimal("900"), service.showBalance("8456321790").getWallet().getBalance());
		
	}
	
	@Test(expected=MobileNotFoundException.class)
	public void DuringFundTransferSourceMobileNotRegisteredSystemThrowException() throws NegativeWithdrwalException, MobileNotFoundException, AlreadyRegisteredMobileException, ClassNotFoundException    {
		
		service.createAccount("akshay","9321654870",new BigDecimal("600"));
		service.createAccount("shivam","9123854760",new BigDecimal("700"));
		service.fundTransfer("9635241807","9123854760", new BigDecimal("200"));
		
	}
	
	@Test(expected=MobileNotFoundException.class)
	public void DuringFundTransferTargetMobileNotRegisteredSystemThrowException() throws NegativeWithdrwalException, MobileNotFoundException, AlreadyRegisteredMobileException, ClassNotFoundException    {
		
		service.createAccount("vipin","9000000001",new BigDecimal("600"));
		service.createAccount("vijay","9111111111",new BigDecimal("700"));
		service.fundTransfer("9000000001","6762454124", new BigDecimal("300"));
		
	}
	
	@Test(expected=NegativeWithdrwalException.class)
	public void DuringFundTransferBalanceReachesToNegativeSystemThrowException() throws NegativeWithdrwalException, MobileNotFoundException, AlreadyRegisteredMobileException, ClassNotFoundException    {
		
		service.createAccount("harman","8000000001",new BigDecimal("600"));
		service.createAccount("priyanka","8111111111",new BigDecimal("700"));
		service.fundTransfer("8000000001","8111111111", new BigDecimal("700"));
		
	}
	
	@Test(expected=MobileNotFoundException.class)
	public void DuringDepositingAmountMobileIsNotRegisteredSystemThrowException() throws NegativeWithdrwalException, MobileNotFoundException, AlreadyRegisteredMobileException, ClassNotFoundException    {
		
		service.createAccount("yanshu","7000000001",new BigDecimal("600"));
		service.depositAmount("7000000002", new BigDecimal("700"));
		
	}
	
	@Test
	public void whenMoneyDepositedSuccessfully() throws NegativeWithdrwalException, MobileNotFoundException, AlreadyRegisteredMobileException, ClassNotFoundException    {
		
		Customer customer=new Customer();
		Wallet wallet=new Wallet();
		
		customer.setMobileno("7222222222");
		customer.setName("kiran");
		wallet.setBalance(new BigDecimal("600"));
		customer.setWallet(wallet);
		repo.save(customer);
		assertEquals(new BigDecimal("1200"),service.depositAmount("7222222222", new BigDecimal("600")).getWallet().getBalance());
		
	}
}

