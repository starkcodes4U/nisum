// src/Assgn4/components/UserControls.jsx
import React from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { login, logout } from '../store/userSlice'

export default function UserControls() {
  const user = useSelector((state) => state.user.user)
  const dispatch = useDispatch()

  return (
    <div style={{ marginBottom: '1rem' }}>
      {user ? (
        <>
          <p>Welcome, <strong>{user.name}</strong></p>
          <button onClick={() => dispatch(logout())}>Logout</button>
        </>
      ) : (
        <button onClick={() => dispatch(login())}>Login</button>
      )}
    </div>
  )
}
