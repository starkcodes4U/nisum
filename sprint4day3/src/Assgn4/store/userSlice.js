// src/Assgn4/store/userSlice.js
import { createSlice } from '@reduxjs/toolkit'

const userSlice = createSlice({
  name: 'user',
  initialState: {
    user: null,
  },
  reducers: {
    login: (state) => {
      state.user = { id: 101, name: 'ToolkitUser', role: 'user' }
    },
    logout: (state) => {
      state.user = null
    },
  },
})

export const { login, logout } = userSlice.actions
export default userSlice.reducer
