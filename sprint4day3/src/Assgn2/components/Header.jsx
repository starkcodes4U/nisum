// src/Assgn2/components/Header.jsx
import React from 'react'
import { useTheme } from '../context/ThemeContext'

const Header = () => {
  const { theme, toggleTheme } = useTheme()

  return (
    <header
      style={{
        padding: '1rem',
        backgroundColor: theme === 'light' ? '#eee' : '#222',
        color: theme === 'light' ? '#000' : '#fff',
        textAlign: 'center',
        borderRadius: '8px',
      }}
    >
      <h1>Theme Context Demo</h1>
      <button onClick={toggleTheme}>
        Switch to {theme === 'light' ? 'Dark' : 'Light'} Mode
      </button>
    </header>
  )
}

export default Header
