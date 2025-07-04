import React from 'react'
import { Provider } from 'react-redux'
import store from './store/store'
import UserControls from './components/UserControls'
import Cart from './components/Cart'

export default function App3() {
  return (
    <Provider store={store}>
      <div className="container">
        <h2>Redux Cart & User</h2>
        <UserControls />
        <Cart />
      </div>
    </Provider>
  )
}
