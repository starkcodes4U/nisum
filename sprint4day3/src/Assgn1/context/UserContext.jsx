import { createContext, useContext, useState } from 'react'

const UserContext = createContext()

const mockUser = {
  id: 1,
  name: 'JAGANNATH TRIPATHY',
  role: 'admin',
}

export const UserProvider = ({ children }) => {
  const [user, setUser] = useState(null)

  const login = () => setUser(mockUser)
  const logout = () => setUser(null)

  return (
    <UserContext.Provider value={{ user, login, logout }}>
      {children}
    </UserContext.Provider>
  )
}

export const useUser = () => useContext(UserContext)
