package kr.ac.hansung.cse.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hansung.cse.model.Cart;
import kr.ac.hansung.cse.model.CartItem;

@Repository
@Transactional
public class CartItemDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void addCartItem(CartItem cartItem) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(cartItem);
		session.flush();
	}
	
	public void removeCartItem(CartItem cartItem) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(cartItem);
		session.flush();
	}
	
	public void editQuantity(CartItem cartItem) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(cartItem);
		session.flush();
	}
	
	public void removeAllCartItems(Cart cart) {
		List<CartItem> cartItems = cart.getCartItems();
		
		for(CartItem item : cartItems) {
			removeCartItem(item);
		}
	}
	
	public CartItem getCartItemByProductId(int cartId, int productId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from CartItem where cart.cartId =:cid and product.id =:pid");
		
		query.setInteger("cid", cartId);
		query.setInteger("pid", productId);
		
		return (CartItem) query.uniqueResult();
	}

}
