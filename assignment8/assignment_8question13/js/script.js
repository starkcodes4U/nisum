async function fetchData() {
  const list = document.getElementById("apiList");
  const errorMsg = document.getElementById("errorMsg");
  list.innerHTML = "";
  errorMsg.textContent = "";

  try {
    const response = await fetch("https://api.publicapis.org/entries");
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }

    const data = await response.json();
    const entries = data.entries.slice(0, 10);

    entries.forEach(api => {
      const li = document.createElement("li");
      li.textContent = `${api.API}: ${api.Description}`;
      list.appendChild(li);
    });

  } catch (error) {
    errorMsg.textContent = "Failed to fetch data. Please try again.";
    console.error("Fetch error:", error);
  }
}
