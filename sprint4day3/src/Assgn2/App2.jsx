// src/Assgn2/App2.jsx
import React from 'react'
import { ThemeProvider } from './context/ThemeContext'
import Header from './components/Header'
import ThemedComponent from './components/ThemedComponent'

export default function App2() {
  return (
    <ThemeProvider>
      <div style={{ padding: '1rem' }}>
        <Header />
        <ThemedComponent />
      </div>
    </ThemeProvider>
  )
}
