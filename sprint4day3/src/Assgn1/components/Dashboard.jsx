import React from 'react'
import { useUser } from '../context/UserContext'

const Dashboard = () => {
  const { user, login, logout } = useUser()

  if (!user) {
    return (
      <div className="container">
        <h2>Role-Based Access Control</h2>
        <p>Please login to access the dashboard.</p>
        <button onClick={login}>Login as Admin</button>
      </div>
    )
  }

  return (
    <div className="container">
      <h2>Welcome, {user.name} ({user.role})</h2>
      <button onClick={logout}>Logout</button>

      <div style={{ marginTop: '1rem' }}>
        <p>You have access to basic dashboard features.</p>
        {user.role === 'admin' && (
          <button className="admin">Add Product (Admin Only)</button>
        )}
      </div>
    </div>
  )
}

export default Dashboard
