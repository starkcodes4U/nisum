package com.nisum;

// Custom exception for data access layer
class DataAccessException extends Exception {
    public DataAccessException(String message) {
        super(message);
    }
}

// Custom exception for service layer
class ServiceException extends Exception {
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

// DAO Layer
class UserDao {
    public String getUserById(int id) throws DataAccessException {
        if (id <= 0) {
            throw new DataAccessException("User ID must be positive. Provided: " + id);
        }
        // Simulate DB logic
        return "User#" + id;
    }
}

// Service Layer
class UserService {
    private final UserDao userDao = new UserDao();

    public String fetchUser(int id) throws ServiceException {
        try {
            return userDao.getUserById(id);
        } catch (DataAccessException e) {
            // Wrap and rethrow with more context
            throw new ServiceException("Failed to fetch user from DB", e);
        }
    }
}

// Controller Layer
class UserController {
    private final UserService userService = new UserService();

    public void handleRequest(int userId) {
        try {
            String user = userService.fetchUser(userId);
            System.out.println("Fetched User: " + user);
        } catch (ServiceException e) {
            // Final handling/logging
            System.err.println("ERROR: " + e.getMessage());
            System.err.println("CAUSE: " + e.getCause().getMessage());
        }
    }
}

// Main Class
public class LayeredExceptionDemo {
    public static void main(String[] args) {
        UserController controller = new UserController();

        controller.handleRequest(5);   // Valid input
        controller.handleRequest(-1);  // Will cause error to bubble up
    }
}
