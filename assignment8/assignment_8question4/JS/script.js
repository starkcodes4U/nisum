let car = null;

class Car {
  constructor(brand, speed) {
    this.brand = brand;
    this.speed = speed;
  }

  accelerate() {
    this.speed += 10;
    return `${this.brand} accelerated to ${this.speed} km/h`;
  }
}

function createCar() {
  const brand = document.getElementById("brand").value.trim();
  const speed = parseInt(document.getElementById("speed").value);
  const output = document.getElementById("output");

  if (brand && !isNaN(speed) && speed >= 0) {
    car = new Car(brand, speed);
    output.textContent = `Created ${brand} with speed ${speed} km/h`;
    output.className = 'success';
  } else {
    output.textContent = "Please enter a valid brand and starting speed (0 or more).";
    output.className = 'error';
  }
}

function accelerateCar() {
  const output = document.getElementById("output");
  if (car) {
    const result = car.accelerate();
    output.textContent = result;
    output.className = 'success';
  } else {
    output.textContent = "Please create a car first.";
    output.className = 'error';
  }
}

document.getElementById("createBtn").addEventListener("click", createCar);
document.getElementById("accelerateBtn").addEventListener("click", accelerateCar);
