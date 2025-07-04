// src/Assgn5/hoc/withAdminAccess.jsx
import React from 'react'

const withAdminAccess = (WrappedComponent) => {
  return function ({ user }) {
    if (!user || user.role !== 'admin') {
      return (
        <div style={{ color: 'crimson', padding: '1rem' }}>
          ❌ Access Denied: Admins only
        </div>
      )
    }
    return <WrappedComponent />
  }
}

export default withAdminAccess
