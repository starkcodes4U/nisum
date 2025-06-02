// Hoisting example
welcome();

function welcome() {
  console.log("Welcome to your To-Do List!");
}

let tasks = [];

window.onload = function () {
  const addBtn = document.getElementById("addTaskBtn");
  addBtn.addEventListener("click", handleAddTask);
};

function handleAddTask() {
  const taskInput = document.getElementById("taskInput");
  const taskText = taskInput.value.trim();

  if (taskText === "") {
    alert("Task cannot be empty!");
    return;
  }

  addTask(taskText);
  taskInput.value = "";
  renderTasks();
}

function addTask(text) {
  const task = {
    text: text,
    completed: false,
  };

  tasks.push(task);
}

function renderTasks() {
  const taskList = document.getElementById("taskList");
  taskList.innerHTML = "";

  tasks.forEach((task, index) => {
    const li = document.createElement("li");
    li.style.margin = "6px 0";
    li.style.display = "flex";
    li.style.alignItems = "center";
    li.style.gap = "10px"; // Adds space between text and button

    const span = document.createElement("span");
    span.textContent = task.text;
    span.style.cursor = "pointer";

    if (task.completed) {
      span.style.textDecoration = "line-through";
      span.style.color = "gray";
    }

    span.addEventListener("click", () => {
      task.completed = !task.completed;
      renderTasks();
    });

    const deleteBtn = document.createElement("button");
    deleteBtn.textContent = "Delete";
    deleteBtn.style.padding = "6px 10px";
    deleteBtn.style.fontSize = "14px";
    deleteBtn.style.backgroundColor = "#dc3545";
    deleteBtn.style.color = "white";
    deleteBtn.style.border = "none";
    deleteBtn.style.cursor = "pointer";

    deleteBtn.addEventListener("click", () => {
      deleteTask(index);
    });

    li.appendChild(span);
    li.appendChild(deleteBtn);
    taskList.appendChild(li);
  });
}

function deleteTask(index) {
  tasks.splice(index, 1);
  renderTasks();
}
