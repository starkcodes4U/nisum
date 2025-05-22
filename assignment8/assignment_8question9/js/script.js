const taskInput = document.getElementById('taskInput');
const addBtn = document.getElementById('addBtn');
const taskList = document.getElementById('taskList');

let tasks = [];


function loadTasks() {
  const storedTasks = localStorage.getItem('tasks');
  if (storedTasks) {
    tasks = JSON.parse(storedTasks);
  }
}


function saveTasks() {
  localStorage.setItem('tasks', JSON.stringify(tasks));
}


function renderTasks() {
  taskList.innerHTML = '';

  if (tasks.length === 0) {
    taskList.innerHTML = '<li style="text-align:center; color:#888;">No tasks added yet.</li>';
    return;
  }

  tasks.forEach((task, index) => {
    const li = document.createElement('li');
    li.className = 'task-item';
    if (task.completed) {
      li.classList.add('completed');
    }

    const checkbox = document.createElement('input');
    checkbox.type = 'checkbox';
    checkbox.checked = task.completed;
    checkbox.addEventListener('change', () => {
      tasks[index].completed = checkbox.checked;
      saveTasks();
      renderTasks();
    });

    const span = document.createElement('span');
    span.textContent = task.text;
    span.className = 'task-text';

    const deleteBtn = document.createElement('button');
    deleteBtn.textContent = 'Delete';
    deleteBtn.className = 'delete-btn';
    deleteBtn.addEventListener('click', () => {
      tasks.splice(index, 1);
      saveTasks();
      renderTasks();
    });

    li.appendChild(checkbox);
    li.appendChild(span);
    li.appendChild(deleteBtn);
    taskList.appendChild(li);
  });
}


addBtn.addEventListener('click', () => {
  const text = taskInput.value.trim();
  if (text === '') {
    alert('Please enter a task');
    return;
  }
  tasks.push({ text, completed: false });
  taskInput.value = '';
  saveTasks();
  renderTasks();
});


taskInput.addEventListener('keypress', e => {
  if (e.key === 'Enter') {
    addBtn.click();
  }
});

function printStoredTasks() {
  const stored = localStorage.getItem('tasks');
  if (stored) {
    console.log('Tasks in localStorage:', JSON.parse(stored));
  } else {
    console.log('No tasks stored yet.');
  }
}

// Call this anytime to see what's saved
printStoredTasks();



loadTasks();
renderTasks();
