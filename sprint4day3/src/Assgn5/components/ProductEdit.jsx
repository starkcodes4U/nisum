// src/Assgn5/components/ProductEdit.jsx
import React from 'react'

const ProductEdit = () => {
  return (
    <div style={{ padding: '1rem' }}>
      <h2>Edit Product</h2>
      <form>
        <input type="text" placeholder="Product name" />
        <br />
        <textarea placeholder="Product description"></textarea>
        <br />
        <button type="submit">Update</button>
      </form>
    </div>
  )
}

export default ProductEdit
