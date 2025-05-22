fetch('https://jsonplaceholder.typicode.com/users')
  .then(response => {
    if (!response.ok) throw new Error('Network response was not ok');
    return response.json();
  })
  .then(users => {
    const userList = document.getElementById('userList');
    users.forEach(user => {
      const li = document.createElement('li');

      const nameSpan = document.createElement('span');
      nameSpan.textContent = user.name;

      const emailSpan = document.createElement('span');
      emailSpan.textContent = user.email;
      emailSpan.className = 'email';

      li.appendChild(nameSpan);
      li.appendChild(emailSpan);

      userList.appendChild(li);
    });
  })
  .catch(error => {
    document.getElementById('errorMsg').textContent = 'Failed to load user data.';
    console.error('Error fetching users:', error);
  });
