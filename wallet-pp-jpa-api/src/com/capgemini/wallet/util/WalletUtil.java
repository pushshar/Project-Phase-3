package com.capgemini.wallet.util;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class WalletUtil {

	EntityManagerFactory emfactory=null;

	
	public EntityManagerFactory Load() 
	{
       
        	 emfactory = Persistence.createEntityManagerFactory("Customer_Details");
	
		return emfactory;
	}

}
