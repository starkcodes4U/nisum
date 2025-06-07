// Symbols - for unique private property keys
const _privateId = Symbol('id');

export default class TodoApp {  // Classes + Enhanced Object Literals

  constructor() {
    this.tasks = []; // let and const
    this.taskMap = new Map(); // Map for task storage
    this.weakTaskMeta = new WeakMap(); // WeakMap for metadata storage
  }

  // Arrow Function for initialization
  init = () => {
    const addBtn = document.getElementById('addBtn'); // const usage
    addBtn.addEventListener('click', () => this.addTask()); // Arrow function in event listener
  }

  // Generator function wrapped in IIFE to create unique IDs
  generateId = (() => {
    let id = 0; // let
    return function* () {
      while (true) yield ++id;
    }();
  })();

  // Promises: simulate saving task asynchronously
  fakeSave(task) {
    return new Promise((resolve) => {
      setTimeout(() => resolve(`Saved task: ${task.text}`), 100);
    });
  }

  // Default parameter for addTask
  addTask(taskText = document.getElementById('taskInput').value) {
    if (!taskText.trim()) return;

    const id = this.generateId.next().value; // Generator usage to get next ID
    // Symbols + Enhanced Object Literals to create task object
    const task = { [_privateId]: id, text: taskText };

    this.tasks.push(task);
    this.taskMap.set(id, task); // Map set
    this.weakTaskMeta.set(task, { createdAt: new Date() }); // WeakMap set

    // Promises to simulate async save
    this.fakeSave(task).then(console.log);

    // Spread operator to clone tasks array
    const tasksCopy = [...this.tasks];
    console.log('Cloned tasks using spread:', tasksCopy);

    this.renderTasks();
    document.getElementById('taskInput').value = '';
  }

  renderTasks() {
    const taskList = document.getElementById('taskList');
    taskList.innerHTML = '';

    // Array.from to create array from Map values
    const taskArray = Array.from(this.taskMap.values());
    taskArray.forEach(original => {
      // Object.assign to clone the task objects
      const clone = Object.assign({}, original);
    });

    // Iterators + for...of loop to iterate tasks
    for (const task of this.tasks) {
      // Destructuring assignment with Symbol key
      const { [_privateId]: id, text } = task;

      // Template literals for rendering each task and button
      const li = document.createElement('li');
      li.innerHTML = `
        ${text}
        <button onclick="removeTask(${id})">❌</button>
      `;

      taskList.appendChild(li);
    }

    // Arrow function assigned globally for button to call removeTask
    window.removeTask = (id) => this.removeTask(id);
  }

  removeTask(id) {
    const index = this.tasks.findIndex(t => t[_privateId] === id);
    if (index !== -1) {
      // Destructuring assignment to get removed task from array
      const [removed] = this.tasks.splice(index, 1);
      this.taskMap.delete(id);

      // Object.is() to check if removed is same as the one in map (should be false now)
      console.log('Deleted same as map value?', Object.is(removed, this.taskMap.get(id)));

      // Array.of() to create array with removed task
      console.log('Array.of removed:', Array.of(removed));
    }
    this.renderTasks();
  }
}
