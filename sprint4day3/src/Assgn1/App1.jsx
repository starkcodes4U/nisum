import React from 'react'
import { UserProvider } from './context/UserContext'
import Dashboard from './components/Dashboard'

export default function App1() {
  return (
    <UserProvider>
      <Dashboard />
    </UserProvider>
  )
}
