const dateElem = document.getElementById("date");
const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric', timeZone: 'Asia/Kolkata' };
const now = new Date().toLocaleDateString('en-IN', options);
dateElem.innerText = now;


const weatherData = {
    india: {
        location: "New Delhi, India",
        icon: "☀️",
        temp: "32°C",
        desc: "Sunny",
        wind: "10 km/h",
        humidity: "58%",
        pressure: "1009 hPa",
        visibility: "8 km"
    },
    usa: {
        location: "New York, USA",
        icon: "🌧️",
        temp: "24°C",
        desc: "Rainy",
        wind: "15 km/h",
        humidity: "80%",
        pressure: "1003 hPa",
        visibility: "5 km"
    },
    uk: {
        location: "London, UK",
        icon: "⛅",
        temp: "18°C",
        desc: "Cloudy",
        wind: "8 km/h",
        humidity: "70%",
        pressure: "1011 hPa",
        visibility: "7 km"
    },
    japan: {
        location: "Tokyo, Japan",
        icon: "🌦️",
        temp: "26°C",
        desc: "Drizzle",
        wind: "12 km/h",
        humidity: "75%",
        pressure: "1007 hPa",
        visibility: "6 km"
    }
};


const inputField = document.querySelector(".search-box input");

inputField.addEventListener("keydown", function (event) {
    if (event.key === "Enter") {
        const country = inputField.value.trim().toLowerCase();
        const data = weatherData[country];
        if (data) {
            document.querySelector(".location").innerText = data.location;
            document.querySelector(".weather-icon").innerText = data.icon;
            document.querySelector(".temperature").innerText = data.temp;
            document.querySelector(".description").innerText = data.desc;

            const details = document.querySelectorAll(".detail-value");
            details[0].innerText = data.wind;
            details[1].innerText = data.humidity;
            details[2].innerText = data.pressure;
            details[3].innerText = data.visibility;
        } else {
            alert("Weather data not available for this country.");
        }
    }
});
