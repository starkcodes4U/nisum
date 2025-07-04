// src/Assgn4/App4.jsx
import React from 'react'
import { Provider } from 'react-redux'
import store from './store/store'
import UserControls from './components/UserControls'
import Cart from './components/Cart'

export default function App4() {
  return (
    <Provider store={store}>
      <div className="container">
        <h2>Redux Toolkit - User + Cart</h2>
        <UserControls />
        <Cart />
      </div>
    </Provider>
  )
}
