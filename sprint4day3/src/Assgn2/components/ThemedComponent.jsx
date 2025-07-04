// src/Assgn2/components/ThemedComponent.jsx
import React from 'react'
import { useTheme } from '../context/ThemeContext'

const ThemedComponent = () => {
  const { theme } = useTheme()

  const style = {
    padding: '2rem',
    marginTop: '1rem',
    borderRadius: '10px',
    backgroundColor: theme === 'light' ? '#fff' : '#333',
    color: theme === 'light' ? '#111' : '#f5f5f5',
    boxShadow: '0 0 10px rgba(0,0,0,0.1)',
  }

  return (
    <div style={style}>
      <h2>This box adapts to the theme!</h2>
      <p>
        Current Theme: <strong>{theme}</strong>
      </p>
    </div>
  )
}

export default ThemedComponent
