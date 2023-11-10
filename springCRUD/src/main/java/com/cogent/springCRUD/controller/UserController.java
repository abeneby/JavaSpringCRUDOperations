
package com.cogent.springCRUD.controller;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cogent.springCRUD.entity.User;

@RestController
@CrossOrigin
public class UserController {
   @Autowired	
   SessionFactory sessionFactory;	
	
	@GetMapping("/gettest")
	public String test() {
		
		return "Success";
		
	}
	
	

	@GetMapping("/getdata")    //tried to connect hibernate with boot
	public String readData() {
		//sessionFactory.getCurrentSession();
		//System.out.println(sessionFactory.getCurrentSession());
		System.out.println(sessionFactory);
		
		Session session= sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		//User u1=new User(1, "sam", "123456789", "sam@gmail.com");
		User u2=new User(2, "charles", "0988907675", "charles@gmail.com");
		User u3=new User(3, "hannah", "1324567243", "hannah@gmail.com");
		User u4=new User(4, "franky", "556655776", "franky@gmail.com");
		User u5=new User(5, "jeffery", "34993903", "jeffery@gmail.com");
		session.save(u2);
		session.save(u3); 
		session.save(u4); 
		session.save(u5); 
		t.commit();
		session.close();
		
		return "Success";
		
	}
	
	//create : session.save() Method 
	
	@PostMapping("/adddata")    //tried to connect hibernate with boot
	public String createData(@RequestBody User user) {
		//sessionFactory.getCurrentSession();
		//System.out.println(sessionFactory.getCurrentSession());
		Session session= sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		//User u1=new User(2, "ABi", "1920383", "abi@gmail.com");
		session.save(user);
		System.out.println(user);
		t.commit();
		session.close();
		
		return "success"; 
	
	}
	
	
	//read 	 : hql --> Query (Object form) 
	
	@GetMapping("/readdata")
	public String read() {
		Session session = sessionFactory.openSession(); 
		Transaction t = session.beginTransaction(); 
		Query<User> query = session.createQuery("from User"); 
		List<User> userList = query.list(); 
		t.commit();
		session.close();
		return userList.toString(); 
	}
	
	@GetMapping("/readuser")    //tried to connect hibernate with boot
	public ResponseEntity<List<User>> readAllData() {
		//sessionFactory.getCurrentSession();
		//System.out.println(sessionFactory.getCurrentSession());
		System.out.println(sessionFactory);
		Session session= sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		
		Query query = session.createQuery("from User");
		
		//User cust = (User) query.getResultList();
		List<User> custList = ((org.hibernate.query.Query) query).list();
				
  		for(User cust : custList){
  			System.out.println("List of User::" + cust.getId() + "," + cust.getName() + " " + cust.getPhone() + " "
  					+ cust.getPhone());
  			  		
  		}
		
		t.commit();
		session.close();				
		
		return ResponseEntity.ok().body(custList);  ///converts into JSON : JS Object Notation

		
	}



	

	//update : hql --> Query 
	//delete : hql --> Query 
	

}

