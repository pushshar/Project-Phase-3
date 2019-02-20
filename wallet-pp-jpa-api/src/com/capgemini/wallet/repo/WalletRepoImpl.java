package com.capgemini.wallet.repo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.capgemini.wallet.beans.Customer;
import com.capgemini.wallet.beans.Wallet;
import com.capgemini.wallet.exception.AlreadyRegisteredMobileException;
import com.capgemini.wallet.exception.MobileNotFoundException;
import com.capgemini.wallet.util.WalletUtil;

public class WalletRepoImpl implements WalletRepo{

	
//	Map<String, Customer> map=new HashMap<>();
	WalletUtil load =new WalletUtil();
	EntityManagerFactory emfactory=load.Load();;
	EntityManager entitymanager=null;
	
	

	@Override
	public boolean save(Customer customer) throws AlreadyRegisteredMobileException, MobileNotFoundException  {
		
		
		

	     entitymanager = emfactory.createEntityManager();
	
		entitymanager.getTransaction( ).begin();
		
		if(entitymanager.find(Customer.class, customer.getMobileno())==null) {
		
		 entitymanager.persist(customer);
		 
		 entitymanager.getTransaction().commit();

		   entitymanager.close();
		   
		
		   return true;
		}
		throw new AlreadyRegisteredMobileException();
		   
	}

	@Override
	public Customer findOne(String mobileno) throws MobileNotFoundException {

		
	     entitymanager = emfactory.createEntityManager();

	//	EntityManager entitymanager=load.Load();
		
		entitymanager.getTransaction( ).begin();
		
		Customer c = entitymanager.find(Customer.class, mobileno);
		
		
		  entitymanager.close();
		  
		  if(c==null)
		  {
			  throw new MobileNotFoundException();
		  }
		  
		
		return c;
		
		
		
		// TODO Auto-generated method stub
		
		/*if(map.containsKey(mobileno))
		{
			return map.get(mobileno);
		}
		throw new MobileNotFoundException();
		*/
		
	}
	
	public void update(Customer customer)
	{
		
		
	     entitymanager = emfactory.createEntityManager();
		Customer c=new Customer();
	
		
		entitymanager.getTransaction( ).begin();
		
	      c=entitymanager.find(Customer.class, customer.getMobileno());
		
	     
		c.setWallet(customer.getWallet());
		entitymanager.getTransaction().commit();
		
		entitymanager.close();
		
		
		
		
		
		
		
	}

}
	
	

