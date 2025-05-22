let usersData = [];

const cityDropdown = document.getElementById('cityDropdown');
const userList = document.getElementById('userList');
const errorMsg = document.getElementById('errorMsg');

function renderUsers(users) {
  userList.innerHTML = '';
  if (users.length === 0) {
    userList.innerHTML = '<li>No users found for this city.</li>';
    return;
  }

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
}

function populateCityDropdown(users) {
  const cities = [...new Set(users.map(user => user.address.city))];
  cities.sort();

  cities.forEach(city => {
    const option = document.createElement('option');
    option.value = city;
    option.textContent = city;
    cityDropdown.appendChild(option);
  });
}

cityDropdown.addEventListener('change', () => {
  const selectedCity = cityDropdown.value;
  if (selectedCity === '') {
    renderUsers(usersData);
  } else {
    const filteredUsers = usersData.filter(user => user.address.city === selectedCity);
    renderUsers(filteredUsers);
  }
});

fetch('https://jsonplaceholder.typicode.com/users')
  .then(response => {
    if (!response.ok) throw new Error('Network response was not ok');
    return response.json();
  })
  .then(users => {
    usersData = users;
    populateCityDropdown(usersData);
    renderUsers(usersData);
  })
  .catch(error => {
    errorMsg.textContent = 'Failed to load user data.';
    console.error('Error fetching users:', error);
  });
