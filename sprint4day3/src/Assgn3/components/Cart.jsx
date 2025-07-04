import React from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { addToCart, removeFromCart } from '../store/cartSlice'

export default function Cart() {
  const cart = useSelector((state) => state.cart.items)
  const dispatch = useDispatch()

  const total = cart.length * 100

  return (
    <div>
      <h3>Cart</h3>
      <p>Items: {cart.length}</p>
      <p>Total: ₹{total}</p>
      <button onClick={() => dispatch(addToCart({ id: Date.now(), name: 'Product X' }))}>
        Add Product
      </button>
      <button onClick={() => dispatch(removeFromCart())} disabled={cart.length === 0}>
        Remove Last Product
      </button>
    </div>
  )
}
