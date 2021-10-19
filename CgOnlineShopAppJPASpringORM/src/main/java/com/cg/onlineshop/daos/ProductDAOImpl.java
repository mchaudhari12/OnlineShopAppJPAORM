package com.cg.onlineshop.daos;

import java.util.List;
import java.util.Optional;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.onlineshop.beans.Product;


@Component(value="productDAO")
public class ProductDAOImpl implements ProductDAO{

	@Autowired
	private EntityManagerFactory entityManagerFactory; 
	
	@Override
	public Product save(Product product) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction tx=	entityManager.getTransaction();
		tx.begin();
		entityManager.merge(product);
		tx.commit();
		entityManager.close();
		return product;
	}

	@Override
	public Product update(Product product) {		
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
	    javax.persistence.Query query = em.createQuery("UPDATE Product e SET  e.name= :name WHERE e.id = :id");
	    query.setParameter("name", product.getName());
	    query.setParameter("id", product.getId());
	    int rowsUpdated = query.executeUpdate();
	    System.out.println("entities Updated: " + rowsUpdated);
	    em.getTransaction().commit();
	    em.close();
		return product;
	}

	@Override
	public void delete(int productId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		javax.persistence.Query query = entityManager.createQuery("DELETE FROM Product e WHERE e.id = :productId ");
		query.setParameter("productId", productId);
		int rowsDeleted = query.executeUpdate();
		System.out.println("entities deleted: " + rowsDeleted);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public List<Product> getAllProducts() {
		EntityManager entityManager= entityManagerFactory.createEntityManager();
		TypedQuery<Product> query  =entityManager.createQuery("FROM Product e",Product.class);
		return query.getResultList();
	}

	@Override
	public Optional<Product> getProduct(int productId) {
		EntityManager entityManager= entityManagerFactory.createEntityManager();
		Product product = entityManager.find(Product.class,productId );
		return Optional.ofNullable(product);
	}

	@Override
	public void insertBulkProducts(List<Product> products) {
		 EntityManager entityManager = entityManagerFactory.createEntityManager();
         entityManager.getTransaction().begin();
         for (Product product : products) {
			entityManager.merge(product);
		}
         entityManager.getTransaction().commit();
         entityManager.close();
		
	}

}
