// src/Assgn4/components/Cart.jsx
import React from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { addToCart, removeFromCart } from '../store/cartSlice'

export default function Cart() {
  const items = useSelector((state) => state.cart.items)
  const dispatch = useDispatch()

  return (
    <div>
      <h3>Cart</h3>
      <p>Items: {items.length}</p>
      <p>Total: ₹{items.length * 100}</p>
      <button onClick={() => dispatch(addToCart({ name: 'Jagannath Tripathy' }))}>
        Add Item
      </button>
      <button onClick={() => dispatch(removeFromCart())} disabled={items.length === 0}>
        Remove Last Item
      </button>
    </div>
  )
}
