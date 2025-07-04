// src/Assgn5/App5.jsx
import React, { useState } from 'react'
import ProductEdit from './components/ProductEdit'
import withAdminAccess from './hoc/withAdminAccess'

const ProtectedProductEdit = withAdminAccess(ProductEdit)

export default function App5() {
  const [user, setUser] = useState(null)

  return (
    <div className="container">
      <h2>HOC Admin Access Control</h2>
      {!user ? (
        <>
          <p>Please login as a user:</p>
          <button onClick={() => setUser({ name: 'Rahul', role: 'user' })}>Login as User</button>
          <button onClick={() => setUser({ name: 'Jagannath', role: 'admin' })}>Login as Admin</button>
        </>
      ) : (
        <>
          <p>
            Logged in as: <strong>{user.name}</strong> ({user.role})
          </p>
          <button onClick={() => setUser(null)}>Logout</button>
          <hr />
          <ProtectedProductEdit user={user} />
        </>
      )}
    </div>
  )
}
