// src/Assgn3/store/userSlice.js
import { createSlice } from '@reduxjs/toolkit'

const initialState = {
  user: null,
}

const userSlice = createSlice({
  name: 'user',
  initialState,
  reducers: {
    login: (state) => {
      state.user = { id: 1, name: 'Jagannath Tripathy', role: 'user' }
    },
    logout: (state) => {
      state.user = null
    },
  },
})

export const { login, logout } = userSlice.actions
export default userSlice.reducer
